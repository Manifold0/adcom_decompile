// 
// Decompiled by Procyon v0.5.34
// 

package rx.plugins;

import rx.internal.operators.OnSubscribeOnAssemblyCompletable;
import rx.internal.operators.OnSubscribeOnAssemblySingle;
import rx.internal.operators.OnSubscribeOnAssembly;
import rx.Single;
import rx.functions.Action0;
import rx.Subscription;
import rx.Observable;
import java.util.concurrent.ScheduledExecutorService;
import rx.functions.Func0;
import rx.functions.Action1;
import rx.Scheduler;
import rx.functions.Func2;
import rx.Completable;
import rx.functions.Func1;
import rx.annotations.Experimental;

@Experimental
public final class RxJavaHooks
{
    static volatile boolean lockdown;
    static volatile Func1<Completable.OnSubscribe, Completable.OnSubscribe> onCompletableCreate;
    static volatile Func1<Completable.Operator, Completable.Operator> onCompletableLift;
    static volatile Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe> onCompletableStart;
    static volatile Func1<Throwable, Throwable> onCompletableSubscribeError;
    static volatile Func1<Scheduler, Scheduler> onComputationScheduler;
    static volatile Action1<Throwable> onError;
    static volatile Func0<? extends ScheduledExecutorService> onGenericScheduledExecutorService;
    static volatile Func1<Scheduler, Scheduler> onIOScheduler;
    static volatile Func1<Scheduler, Scheduler> onNewThreadScheduler;
    static volatile Func1<Observable.OnSubscribe, Observable.OnSubscribe> onObservableCreate;
    static volatile Func1<Observable.Operator, Observable.Operator> onObservableLift;
    static volatile Func1<Subscription, Subscription> onObservableReturn;
    static volatile Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> onObservableStart;
    static volatile Func1<Throwable, Throwable> onObservableSubscribeError;
    static volatile Func1<Action0, Action0> onScheduleAction;
    static volatile Func1<Single.OnSubscribe, Single.OnSubscribe> onSingleCreate;
    static volatile Func1<Observable.Operator, Observable.Operator> onSingleLift;
    static volatile Func1<Subscription, Subscription> onSingleReturn;
    static volatile Func2<Single, Observable.OnSubscribe, Observable.OnSubscribe> onSingleStart;
    static volatile Func1<Throwable, Throwable> onSingleSubscribeError;
    
    static {
        init();
    }
    
    private RxJavaHooks() {
        throw new IllegalStateException("No instances!");
    }
    
    public static void clear() {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onError = null;
        RxJavaHooks.onObservableCreate = null;
        RxJavaHooks.onObservableStart = null;
        RxJavaHooks.onObservableReturn = null;
        RxJavaHooks.onObservableSubscribeError = null;
        RxJavaHooks.onObservableLift = null;
        RxJavaHooks.onSingleCreate = null;
        RxJavaHooks.onSingleStart = null;
        RxJavaHooks.onSingleReturn = null;
        RxJavaHooks.onSingleSubscribeError = null;
        RxJavaHooks.onSingleLift = null;
        RxJavaHooks.onCompletableCreate = null;
        RxJavaHooks.onCompletableStart = null;
        RxJavaHooks.onCompletableSubscribeError = null;
        RxJavaHooks.onCompletableLift = null;
        RxJavaHooks.onComputationScheduler = null;
        RxJavaHooks.onIOScheduler = null;
        RxJavaHooks.onNewThreadScheduler = null;
        RxJavaHooks.onScheduleAction = null;
        RxJavaHooks.onGenericScheduledExecutorService = null;
    }
    
    public static void clearAssemblyTracking() {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onObservableCreate = null;
        RxJavaHooks.onSingleCreate = null;
        RxJavaHooks.onCompletableCreate = null;
    }
    
    public static void enableAssemblyTracking() {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onObservableCreate = new Func1<Observable.OnSubscribe, Observable.OnSubscribe>() {
            @Override
            public Observable.OnSubscribe call(final Observable.OnSubscribe onSubscribe) {
                return new OnSubscribeOnAssembly(onSubscribe);
            }
        };
        RxJavaHooks.onSingleCreate = new Func1<Single.OnSubscribe, Single.OnSubscribe>() {
            @Override
            public Single.OnSubscribe call(final Single.OnSubscribe onSubscribe) {
                return new OnSubscribeOnAssemblySingle(onSubscribe);
            }
        };
        RxJavaHooks.onCompletableCreate = new Func1<Completable.OnSubscribe, Completable.OnSubscribe>() {
            @Override
            public Completable.OnSubscribe call(final Completable.OnSubscribe onSubscribe) {
                return new OnSubscribeOnAssemblyCompletable<Object>(onSubscribe);
            }
        };
    }
    
    public static Func1<Completable.OnSubscribe, Completable.OnSubscribe> getOnCompletableCreate() {
        return RxJavaHooks.onCompletableCreate;
    }
    
    public static Func1<Completable.Operator, Completable.Operator> getOnCompletableLift() {
        return RxJavaHooks.onCompletableLift;
    }
    
    public static Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe> getOnCompletableStart() {
        return RxJavaHooks.onCompletableStart;
    }
    
    public static Func1<Throwable, Throwable> getOnCompletableSubscribeError() {
        return RxJavaHooks.onCompletableSubscribeError;
    }
    
    public static Func1<Scheduler, Scheduler> getOnComputationScheduler() {
        return RxJavaHooks.onComputationScheduler;
    }
    
    public static Action1<Throwable> getOnError() {
        return RxJavaHooks.onError;
    }
    
    public static Func0<? extends ScheduledExecutorService> getOnGenericScheduledExecutorService() {
        return RxJavaHooks.onGenericScheduledExecutorService;
    }
    
    public static Func1<Scheduler, Scheduler> getOnIOScheduler() {
        return RxJavaHooks.onIOScheduler;
    }
    
    public static Func1<Scheduler, Scheduler> getOnNewThreadScheduler() {
        return RxJavaHooks.onNewThreadScheduler;
    }
    
    public static Func1<Observable.OnSubscribe, Observable.OnSubscribe> getOnObservableCreate() {
        return RxJavaHooks.onObservableCreate;
    }
    
    public static Func1<Observable.Operator, Observable.Operator> getOnObservableLift() {
        return RxJavaHooks.onObservableLift;
    }
    
    public static Func1<Subscription, Subscription> getOnObservableReturn() {
        return RxJavaHooks.onObservableReturn;
    }
    
    public static Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> getOnObservableStart() {
        return RxJavaHooks.onObservableStart;
    }
    
    public static Func1<Throwable, Throwable> getOnObservableSubscribeError() {
        return RxJavaHooks.onObservableSubscribeError;
    }
    
    public static Func1<Action0, Action0> getOnScheduleAction() {
        return RxJavaHooks.onScheduleAction;
    }
    
    public static Func1<Single.OnSubscribe, Single.OnSubscribe> getOnSingleCreate() {
        return RxJavaHooks.onSingleCreate;
    }
    
    public static Func1<Observable.Operator, Observable.Operator> getOnSingleLift() {
        return RxJavaHooks.onSingleLift;
    }
    
    public static Func1<Subscription, Subscription> getOnSingleReturn() {
        return RxJavaHooks.onSingleReturn;
    }
    
    public static Func2<Single, Observable.OnSubscribe, Observable.OnSubscribe> getOnSingleStart() {
        return RxJavaHooks.onSingleStart;
    }
    
    public static Func1<Throwable, Throwable> getOnSingleSubscribeError() {
        return RxJavaHooks.onSingleSubscribeError;
    }
    
    static void init() {
        RxJavaHooks.onError = new Action1<Throwable>() {
            @Override
            public void call(final Throwable t) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(t);
            }
        };
        RxJavaHooks.onObservableStart = new Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe>() {
            @Override
            public Observable.OnSubscribe call(final Observable observable, final Observable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeStart(observable, (Observable.OnSubscribe<Object>)onSubscribe);
            }
        };
        RxJavaHooks.onObservableReturn = new Func1<Subscription, Subscription>() {
            @Override
            public Subscription call(final Subscription subscription) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeReturn(subscription);
            }
        };
        RxJavaHooks.onSingleStart = new Func2<Single, Observable.OnSubscribe, Observable.OnSubscribe>() {
            @Override
            public Observable.OnSubscribe call(final Single single, final Observable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeStart(single, (Observable.OnSubscribe<Object>)onSubscribe);
            }
        };
        RxJavaHooks.onSingleReturn = new Func1<Subscription, Subscription>() {
            @Override
            public Subscription call(final Subscription subscription) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeReturn(subscription);
            }
        };
        RxJavaHooks.onCompletableStart = new Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe>() {
            @Override
            public Completable.OnSubscribe call(final Completable completable, final Completable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeStart(completable, onSubscribe);
            }
        };
        RxJavaHooks.onScheduleAction = new Func1<Action0, Action0>() {
            @Override
            public Action0 call(final Action0 action0) {
                return RxJavaPlugins.getInstance().getSchedulersHook().onSchedule(action0);
            }
        };
        RxJavaHooks.onObservableSubscribeError = new Func1<Throwable, Throwable>() {
            @Override
            public Throwable call(final Throwable t) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeError(t);
            }
        };
        RxJavaHooks.onObservableLift = new Func1<Observable.Operator, Observable.Operator>() {
            @Override
            public Observable.Operator call(final Observable.Operator operator) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onLift((Observable.Operator<?, ? super Object>)operator);
            }
        };
        RxJavaHooks.onSingleSubscribeError = new Func1<Throwable, Throwable>() {
            @Override
            public Throwable call(final Throwable t) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeError(t);
            }
        };
        RxJavaHooks.onSingleLift = new Func1<Observable.Operator, Observable.Operator>() {
            @Override
            public Observable.Operator call(final Observable.Operator operator) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onLift((Observable.Operator<?, ? super Object>)operator);
            }
        };
        RxJavaHooks.onCompletableSubscribeError = new Func1<Throwable, Throwable>() {
            @Override
            public Throwable call(final Throwable t) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeError(t);
            }
        };
        RxJavaHooks.onCompletableLift = new Func1<Completable.Operator, Completable.Operator>() {
            @Override
            public Completable.Operator call(final Completable.Operator operator) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onLift(operator);
            }
        };
        initCreate();
    }
    
    static void initCreate() {
        RxJavaHooks.onObservableCreate = new Func1<Observable.OnSubscribe, Observable.OnSubscribe>() {
            @Override
            public Observable.OnSubscribe call(final Observable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onCreate((Observable.OnSubscribe<Object>)onSubscribe);
            }
        };
        RxJavaHooks.onSingleCreate = new Func1<Single.OnSubscribe, Single.OnSubscribe>() {
            @Override
            public Single.OnSubscribe call(final Single.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getSingleExecutionHook().onCreate((Single.OnSubscribe<Object>)onSubscribe);
            }
        };
        RxJavaHooks.onCompletableCreate = new Func1<Completable.OnSubscribe, Completable.OnSubscribe>() {
            @Override
            public Completable.OnSubscribe call(final Completable.OnSubscribe onSubscribe) {
                return RxJavaPlugins.getInstance().getCompletableExecutionHook().onCreate(onSubscribe);
            }
        };
    }
    
    public static boolean isLockdown() {
        return RxJavaHooks.lockdown;
    }
    
    public static void lockdown() {
        RxJavaHooks.lockdown = true;
    }
    
    public static Throwable onCompletableError(final Throwable t) {
        final Func1<Throwable, Throwable> onCompletableSubscribeError = RxJavaHooks.onCompletableSubscribeError;
        if (onCompletableSubscribeError != null) {
            return onCompletableSubscribeError.call(t);
        }
        return t;
    }
    
    public static <T, R> Completable.Operator onCompletableLift(final Completable.Operator operator) {
        final Func1<Completable.Operator, Completable.Operator> onCompletableLift = RxJavaHooks.onCompletableLift;
        if (onCompletableLift != null) {
            return onCompletableLift.call(operator);
        }
        return operator;
    }
    
    public static <T> Completable.OnSubscribe onCompletableStart(final Completable completable, final Completable.OnSubscribe onSubscribe) {
        final Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe> onCompletableStart = RxJavaHooks.onCompletableStart;
        if (onCompletableStart != null) {
            return onCompletableStart.call(completable, onSubscribe);
        }
        return onSubscribe;
    }
    
    public static Scheduler onComputationScheduler(final Scheduler scheduler) {
        final Func1<Scheduler, Scheduler> onComputationScheduler = RxJavaHooks.onComputationScheduler;
        if (onComputationScheduler != null) {
            return onComputationScheduler.call(scheduler);
        }
        return scheduler;
    }
    
    public static Completable.OnSubscribe onCreate(final Completable.OnSubscribe onSubscribe) {
        final Func1<Completable.OnSubscribe, Completable.OnSubscribe> onCompletableCreate = RxJavaHooks.onCompletableCreate;
        if (onCompletableCreate != null) {
            return onCompletableCreate.call(onSubscribe);
        }
        return onSubscribe;
    }
    
    public static <T> Observable.OnSubscribe<T> onCreate(final Observable.OnSubscribe<T> onSubscribe) {
        final Func1<Observable.OnSubscribe, Observable.OnSubscribe> onObservableCreate = RxJavaHooks.onObservableCreate;
        if (onObservableCreate != null) {
            return (Observable.OnSubscribe<T>)onObservableCreate.call(onSubscribe);
        }
        return onSubscribe;
    }
    
    public static <T> Single.OnSubscribe<T> onCreate(final Single.OnSubscribe<T> onSubscribe) {
        final Func1<Single.OnSubscribe, Single.OnSubscribe> onSingleCreate = RxJavaHooks.onSingleCreate;
        if (onSingleCreate != null) {
            return (Single.OnSubscribe<T>)onSingleCreate.call(onSubscribe);
        }
        return onSubscribe;
    }
    
    public static void onError(final Throwable t) {
        final Action1<Throwable> onError = RxJavaHooks.onError;
        if (onError != null) {
            try {
                onError.call(t);
                return;
            }
            catch (Throwable t2) {
                System.err.println("The onError handler threw an Exception. It shouldn't. => " + t2.getMessage());
                t2.printStackTrace();
                signalUncaught(t2);
            }
        }
        signalUncaught(t);
    }
    
    public static Scheduler onIOScheduler(final Scheduler scheduler) {
        final Func1<Scheduler, Scheduler> onIOScheduler = RxJavaHooks.onIOScheduler;
        if (onIOScheduler != null) {
            return onIOScheduler.call(scheduler);
        }
        return scheduler;
    }
    
    public static Scheduler onNewThreadScheduler(final Scheduler scheduler) {
        final Func1<Scheduler, Scheduler> onNewThreadScheduler = RxJavaHooks.onNewThreadScheduler;
        if (onNewThreadScheduler != null) {
            return onNewThreadScheduler.call(scheduler);
        }
        return scheduler;
    }
    
    public static Throwable onObservableError(final Throwable t) {
        final Func1<Throwable, Throwable> onObservableSubscribeError = RxJavaHooks.onObservableSubscribeError;
        if (onObservableSubscribeError != null) {
            return onObservableSubscribeError.call(t);
        }
        return t;
    }
    
    public static <T, R> Observable.Operator<R, T> onObservableLift(final Observable.Operator<R, T> operator) {
        final Func1<Observable.Operator, Observable.Operator> onObservableLift = RxJavaHooks.onObservableLift;
        if (onObservableLift != null) {
            return (Observable.Operator<R, T>)onObservableLift.call(operator);
        }
        return operator;
    }
    
    public static Subscription onObservableReturn(final Subscription subscription) {
        final Func1<Subscription, Subscription> onObservableReturn = RxJavaHooks.onObservableReturn;
        if (onObservableReturn != null) {
            return onObservableReturn.call(subscription);
        }
        return subscription;
    }
    
    public static <T> Observable.OnSubscribe<T> onObservableStart(final Observable<T> observable, final Observable.OnSubscribe<T> onSubscribe) {
        final Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> onObservableStart = RxJavaHooks.onObservableStart;
        if (onObservableStart != null) {
            return (Observable.OnSubscribe<T>)onObservableStart.call(observable, onSubscribe);
        }
        return onSubscribe;
    }
    
    public static Action0 onScheduledAction(final Action0 action0) {
        final Func1<Action0, Action0> onScheduleAction = RxJavaHooks.onScheduleAction;
        if (onScheduleAction != null) {
            return onScheduleAction.call(action0);
        }
        return action0;
    }
    
    public static Throwable onSingleError(final Throwable t) {
        final Func1<Throwable, Throwable> onSingleSubscribeError = RxJavaHooks.onSingleSubscribeError;
        if (onSingleSubscribeError != null) {
            return onSingleSubscribeError.call(t);
        }
        return t;
    }
    
    public static <T, R> Observable.Operator<R, T> onSingleLift(final Observable.Operator<R, T> operator) {
        final Func1<Observable.Operator, Observable.Operator> onSingleLift = RxJavaHooks.onSingleLift;
        if (onSingleLift != null) {
            return (Observable.Operator<R, T>)onSingleLift.call(operator);
        }
        return operator;
    }
    
    public static Subscription onSingleReturn(final Subscription subscription) {
        final Func1<Subscription, Subscription> onSingleReturn = RxJavaHooks.onSingleReturn;
        if (onSingleReturn != null) {
            return onSingleReturn.call(subscription);
        }
        return subscription;
    }
    
    public static <T> Observable.OnSubscribe<T> onSingleStart(final Single<T> single, final Observable.OnSubscribe<T> onSubscribe) {
        final Func2<Single, Observable.OnSubscribe, Observable.OnSubscribe> onSingleStart = RxJavaHooks.onSingleStart;
        if (onSingleStart != null) {
            return (Observable.OnSubscribe<T>)onSingleStart.call(single, onSubscribe);
        }
        return onSubscribe;
    }
    
    public static void reset() {
        if (RxJavaHooks.lockdown) {
            return;
        }
        init();
        RxJavaHooks.onComputationScheduler = null;
        RxJavaHooks.onIOScheduler = null;
        RxJavaHooks.onNewThreadScheduler = null;
        RxJavaHooks.onGenericScheduledExecutorService = null;
    }
    
    public static void resetAssemblyTracking() {
        if (RxJavaHooks.lockdown) {
            return;
        }
        initCreate();
    }
    
    public static void setOnCompletableCreate(final Func1<Completable.OnSubscribe, Completable.OnSubscribe> onCompletableCreate) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onCompletableCreate = onCompletableCreate;
    }
    
    public static void setOnCompletableLift(final Func1<Completable.Operator, Completable.Operator> onCompletableLift) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onCompletableLift = onCompletableLift;
    }
    
    public static void setOnCompletableStart(final Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe> onCompletableStart) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onCompletableStart = onCompletableStart;
    }
    
    public static void setOnCompletableSubscribeError(final Func1<Throwable, Throwable> onCompletableSubscribeError) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onCompletableSubscribeError = onCompletableSubscribeError;
    }
    
    public static void setOnComputationScheduler(final Func1<Scheduler, Scheduler> onComputationScheduler) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onComputationScheduler = onComputationScheduler;
    }
    
    public static void setOnError(final Action1<Throwable> onError) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onError = onError;
    }
    
    public static void setOnGenericScheduledExecutorService(final Func0<? extends ScheduledExecutorService> onGenericScheduledExecutorService) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onGenericScheduledExecutorService = onGenericScheduledExecutorService;
    }
    
    public static void setOnIOScheduler(final Func1<Scheduler, Scheduler> onIOScheduler) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onIOScheduler = onIOScheduler;
    }
    
    public static void setOnNewThreadScheduler(final Func1<Scheduler, Scheduler> onNewThreadScheduler) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onNewThreadScheduler = onNewThreadScheduler;
    }
    
    public static void setOnObservableCreate(final Func1<Observable.OnSubscribe, Observable.OnSubscribe> onObservableCreate) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onObservableCreate = onObservableCreate;
    }
    
    public static void setOnObservableLift(final Func1<Observable.Operator, Observable.Operator> onObservableLift) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onObservableLift = onObservableLift;
    }
    
    public static void setOnObservableReturn(final Func1<Subscription, Subscription> onObservableReturn) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onObservableReturn = onObservableReturn;
    }
    
    public static void setOnObservableStart(final Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> onObservableStart) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onObservableStart = onObservableStart;
    }
    
    public static void setOnObservableSubscribeError(final Func1<Throwable, Throwable> onObservableSubscribeError) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onObservableSubscribeError = onObservableSubscribeError;
    }
    
    public static void setOnScheduleAction(final Func1<Action0, Action0> onScheduleAction) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onScheduleAction = onScheduleAction;
    }
    
    public static void setOnSingleCreate(final Func1<Single.OnSubscribe, Single.OnSubscribe> onSingleCreate) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onSingleCreate = onSingleCreate;
    }
    
    public static void setOnSingleLift(final Func1<Observable.Operator, Observable.Operator> onSingleLift) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onSingleLift = onSingleLift;
    }
    
    public static void setOnSingleReturn(final Func1<Subscription, Subscription> onSingleReturn) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onSingleReturn = onSingleReturn;
    }
    
    public static void setOnSingleStart(final Func2<Single, Observable.OnSubscribe, Observable.OnSubscribe> onSingleStart) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onSingleStart = onSingleStart;
    }
    
    public static void setOnSingleSubscribeError(final Func1<Throwable, Throwable> onSingleSubscribeError) {
        if (RxJavaHooks.lockdown) {
            return;
        }
        RxJavaHooks.onSingleSubscribeError = onSingleSubscribeError;
    }
    
    static void signalUncaught(final Throwable t) {
        final Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, t);
    }
}
