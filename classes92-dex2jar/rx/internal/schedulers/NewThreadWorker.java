// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.schedulers;

import rx.subscriptions.CompositeSubscription;
import rx.internal.util.SubscriptionList;
import java.util.concurrent.Future;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;
import rx.internal.util.RxThreadFactory;
import rx.internal.util.SuppressAnimalSniffer;
import java.util.Iterator;
import rx.plugins.RxJavaHooks;
import rx.exceptions.Exceptions;
import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import rx.internal.util.PlatformDependent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ConcurrentHashMap;
import rx.Subscription;
import rx.Scheduler;

public class NewThreadWorker extends Worker implements Subscription
{
    private static final ConcurrentHashMap<ScheduledThreadPoolExecutor, ScheduledThreadPoolExecutor> EXECUTORS;
    private static final String FREQUENCY_KEY = "rx.scheduler.jdk6.purge-frequency-millis";
    private static final AtomicReference<ScheduledExecutorService> PURGE;
    private static final String PURGE_FORCE_KEY = "rx.scheduler.jdk6.purge-force";
    public static final int PURGE_FREQUENCY;
    private static final String PURGE_THREAD_PREFIX = "RxSchedulerPurge-";
    private static final Object SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED;
    private static final boolean SHOULD_TRY_ENABLE_CANCEL_POLICY;
    private static volatile Object cachedSetRemoveOnCancelPolicyMethod;
    private final ScheduledExecutorService executor;
    volatile boolean isUnsubscribed;
    
    static {
        SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED = new Object();
        EXECUTORS = new ConcurrentHashMap<ScheduledThreadPoolExecutor, ScheduledThreadPoolExecutor>();
        PURGE = new AtomicReference<ScheduledExecutorService>();
        PURGE_FREQUENCY = Integer.getInteger("rx.scheduler.jdk6.purge-frequency-millis", 1000);
        final boolean boolean1 = Boolean.getBoolean("rx.scheduler.jdk6.purge-force");
        final int androidApiVersion = PlatformDependent.getAndroidApiVersion();
        SHOULD_TRY_ENABLE_CANCEL_POLICY = (!boolean1 && (androidApiVersion == 0 || androidApiVersion >= 21));
    }
    
    public NewThreadWorker(final ThreadFactory threadFactory) {
        final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        if (!tryEnableCancelPolicy(scheduledThreadPool) && scheduledThreadPool instanceof ScheduledThreadPoolExecutor) {
            registerExecutor((ScheduledThreadPoolExecutor)scheduledThreadPool);
        }
        this.executor = scheduledThreadPool;
    }
    
    public static void deregisterExecutor(final ScheduledExecutorService scheduledExecutorService) {
        NewThreadWorker.EXECUTORS.remove(scheduledExecutorService);
    }
    
    static Method findSetRemoveOnCancelPolicyMethod(final ScheduledExecutorService scheduledExecutorService) {
        final Method[] methods = scheduledExecutorService.getClass().getMethods();
        for (int length = methods.length, i = 0; i < length; ++i) {
            final Method method = methods[i];
            if (method.getName().equals("setRemoveOnCancelPolicy")) {
                final Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0] == Boolean.TYPE) {
                    return method;
                }
            }
        }
        return null;
    }
    
    @SuppressAnimalSniffer
    static void purgeExecutors() {
        while (true) {
            while (true) {
                Label_0055: {
                    try {
                        for (final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor : NewThreadWorker.EXECUTORS.keySet()) {
                            if (scheduledThreadPoolExecutor.isShutdown()) {
                                break Label_0055;
                            }
                            scheduledThreadPoolExecutor.purge();
                        }
                    }
                    catch (Throwable t) {
                        Exceptions.throwIfFatal(t);
                        RxJavaHooks.onError(t);
                    }
                    break;
                }
                final Iterator<ScheduledThreadPoolExecutor> iterator;
                iterator.remove();
                continue;
            }
        }
    }
    
    public static void registerExecutor(final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        while (NewThreadWorker.PURGE.get() == null) {
            final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge-"));
            if (NewThreadWorker.PURGE.compareAndSet(null, scheduledThreadPool)) {
                scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        NewThreadWorker.purgeExecutors();
                    }
                }, NewThreadWorker.PURGE_FREQUENCY, NewThreadWorker.PURGE_FREQUENCY, TimeUnit.MILLISECONDS);
                break;
            }
            scheduledThreadPool.shutdownNow();
        }
        NewThreadWorker.EXECUTORS.putIfAbsent(scheduledThreadPoolExecutor, scheduledThreadPoolExecutor);
    }
    
    public static boolean tryEnableCancelPolicy(final ScheduledExecutorService scheduledExecutorService) {
        if (!NewThreadWorker.SHOULD_TRY_ENABLE_CANCEL_POLICY) {
            goto Label_0096;
        }
        Label_0083: {
            if (!(scheduledExecutorService instanceof ScheduledThreadPoolExecutor)) {
                break Label_0083;
            }
            final Object cachedSetRemoveOnCancelPolicyMethod = NewThreadWorker.cachedSetRemoveOnCancelPolicyMethod;
            if (cachedSetRemoveOnCancelPolicyMethod == NewThreadWorker.SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED) {
                return false;
            }
            Label_0075: {
                if (cachedSetRemoveOnCancelPolicyMethod != null) {
                    break Label_0075;
                }
                Method method = findSetRemoveOnCancelPolicyMethod(scheduledExecutorService);
                Label_0068: {
                    if (method == null) {
                        break Label_0068;
                    }
                    Object set_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED = method;
                Label_0045_Outer:
                    while (true) {
                        NewThreadWorker.cachedSetRemoveOnCancelPolicyMethod = set_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED;
                        while (true) {
                            if (method == null) {
                                goto Label_0096;
                            }
                            try {
                                method.invoke(scheduledExecutorService, true);
                                return true;
                                set_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED = NewThreadWorker.SET_REMOVE_ON_CANCEL_POLICY_METHOD_NOT_SUPPORTED;
                                continue Label_0045_Outer;
                                method = (Method)cachedSetRemoveOnCancelPolicyMethod;
                                continue;
                                method = findSetRemoveOnCancelPolicyMethod(scheduledExecutorService);
                                continue;
                            }
                            catch (InvocationTargetException ex) {
                                RxJavaHooks.onError(ex);
                            }
                            catch (IllegalAccessException ex2) {
                                RxJavaHooks.onError(ex2);
                                goto Label_0096;
                            }
                            catch (IllegalArgumentException ex3) {
                                RxJavaHooks.onError(ex3);
                                goto Label_0096;
                            }
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public boolean isUnsubscribed() {
        return this.isUnsubscribed;
    }
    
    @Override
    public Subscription schedule(final Action0 action0) {
        return this.schedule(action0, 0L, null);
    }
    
    @Override
    public Subscription schedule(final Action0 action0, final long n, final TimeUnit timeUnit) {
        if (this.isUnsubscribed) {
            return Subscriptions.unsubscribed();
        }
        return this.scheduleActual(action0, n, timeUnit);
    }
    
    public ScheduledAction scheduleActual(final Action0 action0, final long n, final TimeUnit timeUnit) {
        final ScheduledAction scheduledAction = new ScheduledAction(RxJavaHooks.onScheduledAction(action0));
        Future<?> future;
        if (n <= 0L) {
            future = this.executor.submit(scheduledAction);
        }
        else {
            future = this.executor.schedule(scheduledAction, n, timeUnit);
        }
        scheduledAction.add(future);
        return scheduledAction;
    }
    
    public ScheduledAction scheduleActual(final Action0 action0, final long n, final TimeUnit timeUnit, final SubscriptionList list) {
        final ScheduledAction scheduledAction = new ScheduledAction(RxJavaHooks.onScheduledAction(action0), list);
        list.add(scheduledAction);
        Future<?> future;
        if (n <= 0L) {
            future = this.executor.submit(scheduledAction);
        }
        else {
            future = this.executor.schedule(scheduledAction, n, timeUnit);
        }
        scheduledAction.add(future);
        return scheduledAction;
    }
    
    public ScheduledAction scheduleActual(final Action0 action0, final long n, final TimeUnit timeUnit, final CompositeSubscription compositeSubscription) {
        final ScheduledAction scheduledAction = new ScheduledAction(RxJavaHooks.onScheduledAction(action0), compositeSubscription);
        compositeSubscription.add(scheduledAction);
        Future<?> future;
        if (n <= 0L) {
            future = this.executor.submit(scheduledAction);
        }
        else {
            future = this.executor.schedule(scheduledAction, n, timeUnit);
        }
        scheduledAction.add(future);
        return scheduledAction;
    }
    
    @Override
    public void unsubscribe() {
        this.isUnsubscribed = true;
        this.executor.shutdownNow();
        deregisterExecutor(this.executor);
    }
}
