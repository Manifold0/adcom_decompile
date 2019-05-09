// 
// Decompiled by Procyon v0.5.34
// 

package rx.schedulers;

import rx.internal.schedulers.SchedulerLifecycle;
import rx.internal.schedulers.TrampolineScheduler;
import rx.internal.util.RxRingBuffer;
import rx.internal.schedulers.GenericScheduledExecutorService;
import rx.annotations.Experimental;
import rx.internal.schedulers.ImmediateScheduler;
import rx.internal.schedulers.ExecutorScheduler;
import java.util.concurrent.Executor;
import rx.plugins.RxJavaHooks;
import rx.plugins.RxJavaSchedulersHook;
import rx.plugins.RxJavaPlugins;
import rx.Scheduler;
import java.util.concurrent.atomic.AtomicReference;

public final class Schedulers
{
    private static final AtomicReference<Schedulers> INSTANCE;
    private final Scheduler computationScheduler;
    private final Scheduler ioScheduler;
    private final Scheduler newThreadScheduler;
    
    static {
        INSTANCE = new AtomicReference<Schedulers>();
    }
    
    private Schedulers() {
        final RxJavaSchedulersHook schedulersHook = RxJavaPlugins.getInstance().getSchedulersHook();
        final Scheduler computationScheduler = schedulersHook.getComputationScheduler();
        if (computationScheduler != null) {
            this.computationScheduler = computationScheduler;
        }
        else {
            this.computationScheduler = RxJavaSchedulersHook.createComputationScheduler();
        }
        final Scheduler ioScheduler = schedulersHook.getIOScheduler();
        if (ioScheduler != null) {
            this.ioScheduler = ioScheduler;
        }
        else {
            this.ioScheduler = RxJavaSchedulersHook.createIoScheduler();
        }
        final Scheduler newThreadScheduler = schedulersHook.getNewThreadScheduler();
        if (newThreadScheduler != null) {
            this.newThreadScheduler = newThreadScheduler;
            return;
        }
        this.newThreadScheduler = RxJavaSchedulersHook.createNewThreadScheduler();
    }
    
    public static Scheduler computation() {
        return RxJavaHooks.onComputationScheduler(getInstance().computationScheduler);
    }
    
    public static Scheduler from(final Executor executor) {
        return new ExecutorScheduler(executor);
    }
    
    private static Schedulers getInstance() {
        Schedulers schedulers;
        while (true) {
            schedulers = Schedulers.INSTANCE.get();
            if (schedulers != null) {
                break;
            }
            final Schedulers schedulers2 = schedulers = new Schedulers();
            if (Schedulers.INSTANCE.compareAndSet(null, schedulers2)) {
                break;
            }
            schedulers2.shutdownInstance();
        }
        return schedulers;
    }
    
    public static Scheduler immediate() {
        return ImmediateScheduler.INSTANCE;
    }
    
    public static Scheduler io() {
        return RxJavaHooks.onIOScheduler(getInstance().ioScheduler);
    }
    
    public static Scheduler newThread() {
        return RxJavaHooks.onNewThreadScheduler(getInstance().newThreadScheduler);
    }
    
    @Experimental
    public static void reset() {
        final Schedulers schedulers = Schedulers.INSTANCE.getAndSet(null);
        if (schedulers != null) {
            schedulers.shutdownInstance();
        }
    }
    
    public static void shutdown() {
        final Schedulers instance = getInstance();
        instance.shutdownInstance();
        synchronized (instance) {
            GenericScheduledExecutorService.INSTANCE.shutdown();
            RxRingBuffer.SPSC_POOL.shutdown();
            RxRingBuffer.SPMC_POOL.shutdown();
        }
    }
    
    public static void start() {
        final Schedulers instance = getInstance();
        instance.startInstance();
        synchronized (instance) {
            GenericScheduledExecutorService.INSTANCE.start();
            RxRingBuffer.SPSC_POOL.start();
            RxRingBuffer.SPMC_POOL.start();
        }
    }
    
    public static TestScheduler test() {
        return new TestScheduler();
    }
    
    public static Scheduler trampoline() {
        return TrampolineScheduler.INSTANCE;
    }
    
    void shutdownInstance() {
        synchronized (this) {
            if (this.computationScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle)this.computationScheduler).shutdown();
            }
            if (this.ioScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle)this.ioScheduler).shutdown();
            }
            if (this.newThreadScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle)this.newThreadScheduler).shutdown();
            }
        }
    }
    
    void startInstance() {
        synchronized (this) {
            if (this.computationScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle)this.computationScheduler).start();
            }
            if (this.ioScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle)this.ioScheduler).start();
            }
            if (this.newThreadScheduler instanceof SchedulerLifecycle) {
                ((SchedulerLifecycle)this.newThreadScheduler).start();
            }
        }
    }
}
