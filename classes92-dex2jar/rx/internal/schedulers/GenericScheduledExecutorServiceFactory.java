// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import rx.functions.Func0;
import rx.plugins.RxJavaHooks;
import java.util.concurrent.ScheduledExecutorService;
import rx.internal.util.RxThreadFactory;

enum GenericScheduledExecutorServiceFactory
{
    static final RxThreadFactory THREAD_FACTORY;
    static final String THREAD_NAME_PREFIX = "RxScheduledExecutorPool-";
    
    static {
        THREAD_FACTORY = new RxThreadFactory("RxScheduledExecutorPool-");
    }
    
    public static ScheduledExecutorService create() {
        final Func0<? extends ScheduledExecutorService> onGenericScheduledExecutorService = RxJavaHooks.getOnGenericScheduledExecutorService();
        if (onGenericScheduledExecutorService == null) {
            return createDefault();
        }
        return (ScheduledExecutorService)onGenericScheduledExecutorService.call();
    }
    
    static ScheduledExecutorService createDefault() {
        return Executors.newScheduledThreadPool(1, threadFactory());
    }
    
    static ThreadFactory threadFactory() {
        return GenericScheduledExecutorServiceFactory.THREAD_FACTORY;
    }
}
