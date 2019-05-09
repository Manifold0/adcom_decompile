// 
// Decompiled by Procyon v0.5.34
// 

package rx.plugins;

import rx.functions.Action0;
import rx.internal.schedulers.NewThreadScheduler;
import rx.internal.schedulers.CachedThreadScheduler;
import rx.internal.schedulers.EventLoopsScheduler;
import rx.annotations.Experimental;
import java.util.concurrent.ThreadFactory;
import rx.internal.util.RxThreadFactory;
import rx.Scheduler;

public class RxJavaSchedulersHook
{
    private static final RxJavaSchedulersHook DEFAULT_INSTANCE;
    
    static {
        DEFAULT_INSTANCE = new RxJavaSchedulersHook();
    }
    
    @Experimental
    public static Scheduler createComputationScheduler() {
        return createComputationScheduler(new RxThreadFactory("RxComputationScheduler-"));
    }
    
    @Experimental
    public static Scheduler createComputationScheduler(final ThreadFactory threadFactory) {
        if (threadFactory == null) {
            throw new NullPointerException("threadFactory == null");
        }
        return new EventLoopsScheduler(threadFactory);
    }
    
    @Experimental
    public static Scheduler createIoScheduler() {
        return createIoScheduler(new RxThreadFactory("RxIoScheduler-"));
    }
    
    @Experimental
    public static Scheduler createIoScheduler(final ThreadFactory threadFactory) {
        if (threadFactory == null) {
            throw new NullPointerException("threadFactory == null");
        }
        return new CachedThreadScheduler(threadFactory);
    }
    
    @Experimental
    public static Scheduler createNewThreadScheduler() {
        return createNewThreadScheduler(new RxThreadFactory("RxNewThreadScheduler-"));
    }
    
    @Experimental
    public static Scheduler createNewThreadScheduler(final ThreadFactory threadFactory) {
        if (threadFactory == null) {
            throw new NullPointerException("threadFactory == null");
        }
        return new NewThreadScheduler(threadFactory);
    }
    
    public static RxJavaSchedulersHook getDefaultInstance() {
        return RxJavaSchedulersHook.DEFAULT_INSTANCE;
    }
    
    public Scheduler getComputationScheduler() {
        return null;
    }
    
    public Scheduler getIOScheduler() {
        return null;
    }
    
    public Scheduler getNewThreadScheduler() {
        return null;
    }
    
    @Deprecated
    public Action0 onSchedule(final Action0 action0) {
        return action0;
    }
}
