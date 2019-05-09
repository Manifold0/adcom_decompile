// 
// Decompiled by Procyon v0.5.34
// 

package bolts;

import java.io.Closeable;

public class CancellationTokenRegistration implements Closeable
{
    private Runnable action;
    private boolean closed;
    private final Object lock;
    private CancellationTokenSource tokenSource;
    
    CancellationTokenRegistration(final CancellationTokenSource tokenSource, final Runnable action) {
        this.lock = new Object();
        this.tokenSource = tokenSource;
        this.action = action;
    }
    
    private void throwIfClosed() {
        if (this.closed) {
            throw new IllegalStateException("Object already closed");
        }
    }
    
    @Override
    public void close() {
        synchronized (this.lock) {
            if (this.closed) {
                return;
            }
            this.closed = true;
            this.tokenSource.unregister(this);
            this.tokenSource = null;
            this.action = null;
        }
    }
    
    void runAction() {
        synchronized (this.lock) {
            this.throwIfClosed();
            this.action.run();
            this.close();
        }
    }
}
