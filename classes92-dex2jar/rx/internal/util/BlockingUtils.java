// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import rx.Subscription;
import java.util.concurrent.CountDownLatch;
import rx.annotations.Experimental;

@Experimental
public final class BlockingUtils
{
    private BlockingUtils() {
    }
    
    @Experimental
    public static void awaitForComplete(final CountDownLatch countDownLatch, final Subscription subscription) {
        if (countDownLatch.getCount() == 0L) {
            return;
        }
        try {
            countDownLatch.await();
        }
        catch (InterruptedException ex) {
            subscription.unsubscribe();
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for subscription to complete.", ex);
        }
    }
}
