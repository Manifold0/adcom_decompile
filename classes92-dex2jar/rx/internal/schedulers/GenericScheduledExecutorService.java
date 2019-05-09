// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.schedulers;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.ScheduledExecutorService;

public final class GenericScheduledExecutorService implements SchedulerLifecycle
{
    public static final GenericScheduledExecutorService INSTANCE;
    private static final ScheduledExecutorService[] NONE;
    private static final ScheduledExecutorService SHUTDOWN;
    private static int roundRobin;
    private final AtomicReference<ScheduledExecutorService[]> executor;
    
    static {
        NONE = new ScheduledExecutorService[0];
        (SHUTDOWN = Executors.newScheduledThreadPool(0)).shutdown();
        INSTANCE = new GenericScheduledExecutorService();
    }
    
    private GenericScheduledExecutorService() {
        this.executor = new AtomicReference<ScheduledExecutorService[]>(GenericScheduledExecutorService.NONE);
        this.start();
    }
    
    public static ScheduledExecutorService getInstance() {
        final ScheduledExecutorService[] array = GenericScheduledExecutorService.INSTANCE.executor.get();
        if (array == GenericScheduledExecutorService.NONE) {
            return GenericScheduledExecutorService.SHUTDOWN;
        }
        int roundRobin;
        if ((roundRobin = GenericScheduledExecutorService.roundRobin + 1) >= array.length) {
            roundRobin = 0;
        }
        GenericScheduledExecutorService.roundRobin = roundRobin;
        return array[roundRobin];
    }
    
    @Override
    public void shutdown() {
        ScheduledExecutorService[] array;
        do {
            array = this.executor.get();
            if (array == GenericScheduledExecutorService.NONE) {
                return;
            }
        } while (!this.executor.compareAndSet(array, GenericScheduledExecutorService.NONE));
        for (int length = array.length, i = 0; i < length; ++i) {
            final ScheduledExecutorService scheduledExecutorService = array[i];
            NewThreadWorker.deregisterExecutor(scheduledExecutorService);
            scheduledExecutorService.shutdownNow();
        }
    }
    
    @Override
    public void start() {
        int availableProcessors;
        final int n = availableProcessors = Runtime.getRuntime().availableProcessors();
        if (n > 4) {
            availableProcessors = n / 2;
        }
        int n2;
        if ((n2 = availableProcessors) > 8) {
            n2 = 8;
        }
        final ScheduledExecutorService[] array = new ScheduledExecutorService[n2];
        for (int i = 0; i < n2; ++i) {
            array[i] = GenericScheduledExecutorServiceFactory.create();
        }
        if (this.executor.compareAndSet(GenericScheduledExecutorService.NONE, array)) {
            for (int length = array.length, j = 0; j < length; ++j) {
                final ScheduledExecutorService scheduledExecutorService = array[j];
                if (!NewThreadWorker.tryEnableCancelPolicy(scheduledExecutorService) && scheduledExecutorService instanceof ScheduledThreadPoolExecutor) {
                    NewThreadWorker.registerExecutor((ScheduledThreadPoolExecutor)scheduledExecutorService);
                }
            }
        }
        else {
            for (int length2 = array.length, k = 0; k < length2; ++k) {
                array[k].shutdownNow();
            }
        }
    }
}
