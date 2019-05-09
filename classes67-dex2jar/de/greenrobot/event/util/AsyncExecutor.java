// 
// Decompiled by Procyon v0.5.34
// 

package de.greenrobot.event.util;

import java.util.concurrent.Executors;
import android.app.Activity;
import android.util.Log;
import java.util.concurrent.Executor;
import java.lang.reflect.Constructor;
import de.greenrobot.event.EventBus;

public class AsyncExecutor
{
    private final EventBus eventBus;
    private final Constructor<?> failureEventConstructor;
    private Object scope;
    private final Executor threadPool;
    
    private AsyncExecutor(final Executor threadPool, final EventBus eventBus, final Class<?> clazz, final Object scope) {
        this.threadPool = threadPool;
        this.eventBus = eventBus;
        this.scope = scope;
        try {
            this.failureEventConstructor = clazz.getConstructor(Throwable.class);
        }
        catch (NoSuchMethodException ex) {
            throw new RuntimeException("Failure event class must have a constructor with one parameter of type Throwable", ex);
        }
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static AsyncExecutor create() {
        return new Builder().build();
    }
    
    public void execute(final RunnableEx runnableEx) {
        this.threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    runnableEx.run();
                }
                catch (Exception ex) {
                    try {
                        final HasExecutionScope instance = AsyncExecutor.this.failureEventConstructor.newInstance(ex);
                        if (instance instanceof HasExecutionScope) {
                            instance.setExecutionScope(AsyncExecutor.this.scope);
                        }
                        AsyncExecutor.this.eventBus.post(instance);
                    }
                    catch (Exception ex2) {
                        Log.e(EventBus.TAG, "Original exception:", (Throwable)ex);
                        throw new RuntimeException("Could not create failure event", ex2);
                    }
                }
            }
        });
    }
    
    public static class Builder
    {
        private EventBus eventBus;
        private Class<?> failureEventType;
        private Executor threadPool;
        
        private Builder() {
        }
        
        public AsyncExecutor build() {
            return this.buildForScope(null);
        }
        
        public AsyncExecutor buildForActivityScope(final Activity activity) {
            return this.buildForScope(activity.getClass());
        }
        
        public AsyncExecutor buildForScope(final Object o) {
            if (this.eventBus == null) {
                this.eventBus = EventBus.getDefault();
            }
            if (this.threadPool == null) {
                this.threadPool = Executors.newCachedThreadPool();
            }
            if (this.failureEventType == null) {
                this.failureEventType = ThrowableFailureEvent.class;
            }
            return new AsyncExecutor(this.threadPool, this.eventBus, this.failureEventType, o, null);
        }
        
        public Builder eventBus(final EventBus eventBus) {
            this.eventBus = eventBus;
            return this;
        }
        
        public Builder failureEventType(final Class<?> failureEventType) {
            this.failureEventType = failureEventType;
            return this;
        }
        
        public Builder threadPool(final Executor threadPool) {
            this.threadPool = threadPool;
            return this;
        }
    }
    
    public interface RunnableEx
    {
        void run() throws Exception;
    }
}
