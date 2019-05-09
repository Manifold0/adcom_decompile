// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.RejectedExecutionException;
import rx.plugins.RxJavaHooks;
import java.util.concurrent.TimeUnit;
import rx.internal.schedulers.GenericScheduledExecutorService;
import java.util.concurrent.ConcurrentLinkedQueue;
import rx.internal.util.unsafe.MpmcArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import java.util.Queue;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.schedulers.SchedulerLifecycle;

public abstract class ObjectPool<T> implements SchedulerLifecycle
{
    final int maxSize;
    final int minSize;
    private final AtomicReference<Future<?>> periodicTask;
    Queue<T> pool;
    private final long validationInterval;
    
    public ObjectPool() {
        this(0, 0, 67L);
    }
    
    private ObjectPool(final int minSize, final int maxSize, final long validationInterval) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.validationInterval = validationInterval;
        this.periodicTask = new AtomicReference<Future<?>>();
        this.initialize(minSize);
        this.start();
    }
    
    private void initialize(final int n) {
        if (UnsafeAccess.isUnsafeAvailable()) {
            this.pool = new MpmcArrayQueue<T>(Math.max(this.maxSize, 1024));
        }
        else {
            this.pool = new ConcurrentLinkedQueue<T>();
        }
        for (int i = 0; i < n; ++i) {
            this.pool.add(this.createObject());
        }
    }
    
    public T borrowObject() {
        T t;
        if ((t = this.pool.poll()) == null) {
            t = this.createObject();
        }
        return t;
    }
    
    protected abstract T createObject();
    
    public void returnObject(final T t) {
        if (t == null) {
            return;
        }
        this.pool.offer(t);
    }
    
    @Override
    public void shutdown() {
        final Future<?> future = this.periodicTask.getAndSet(null);
        if (future != null) {
            future.cancel(false);
        }
    }
    
    @Override
    public void start() {
        while (this.periodicTask.get() == null) {
            final ScheduledExecutorService instance = GenericScheduledExecutorService.getInstance();
            try {
                final ScheduledFuture<?> scheduleAtFixedRate = instance.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        final int size = ObjectPool.this.pool.size();
                        if (size < ObjectPool.this.minSize) {
                            for (int maxSize = ObjectPool.this.maxSize, i = 0; i < maxSize - size; ++i) {
                                ObjectPool.this.pool.add(ObjectPool.this.createObject());
                            }
                        }
                        else if (size > ObjectPool.this.maxSize) {
                            for (int maxSize2 = ObjectPool.this.maxSize, j = 0; j < size - maxSize2; ++j) {
                                ObjectPool.this.pool.poll();
                            }
                        }
                    }
                }, this.validationInterval, this.validationInterval, TimeUnit.SECONDS);
                if (this.periodicTask.compareAndSet(null, scheduleAtFixedRate)) {
                    break;
                }
                scheduleAtFixedRate.cancel(false);
            }
            catch (RejectedExecutionException ex) {
                RxJavaHooks.onError(ex);
            }
        }
    }
}
