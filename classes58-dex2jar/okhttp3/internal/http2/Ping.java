// 
// Decompiled by Procyon v0.5.34
// 

package okhttp3.internal.http2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;

final class Ping
{
    private final CountDownLatch latch;
    private long received;
    private long sent;
    
    Ping() {
        this.latch = new CountDownLatch(1);
        this.sent = -1L;
        this.received = -1L;
    }
    
    void cancel() {
        if (this.received != -1L || this.sent == -1L) {
            throw new IllegalStateException();
        }
        this.received = this.sent - 1L;
        this.latch.countDown();
    }
    
    void receive() {
        if (this.received != -1L || this.sent == -1L) {
            throw new IllegalStateException();
        }
        this.received = System.nanoTime();
        this.latch.countDown();
    }
    
    public long roundTripTime() throws InterruptedException {
        this.latch.await();
        return this.received - this.sent;
    }
    
    public long roundTripTime(final long n, final TimeUnit timeUnit) throws InterruptedException {
        if (this.latch.await(n, timeUnit)) {
            return this.received - this.sent;
        }
        return -2L;
    }
    
    void send() {
        if (this.sent != -1L) {
            throw new IllegalStateException();
        }
        this.sent = System.nanoTime();
    }
}
