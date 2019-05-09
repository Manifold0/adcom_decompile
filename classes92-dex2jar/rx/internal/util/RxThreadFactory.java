// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public final class RxThreadFactory extends AtomicLong implements ThreadFactory
{
    public static final ThreadFactory NONE;
    private static final long serialVersionUID = -8841098858898482335L;
    final String prefix;
    
    static {
        NONE = new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable runnable) {
                throw new AssertionError((Object)"No threads allowed.");
            }
        };
    }
    
    public RxThreadFactory(final String prefix) {
        this.prefix = prefix;
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        final Thread thread = new Thread(runnable, this.prefix + this.incrementAndGet());
        thread.setDaemon(true);
        return thread;
    }
}
