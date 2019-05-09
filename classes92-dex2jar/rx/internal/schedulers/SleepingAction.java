// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.schedulers;

import rx.exceptions.Exceptions;
import rx.Scheduler;
import rx.functions.Action0;

class SleepingAction implements Action0
{
    private final long execTime;
    private final Scheduler.Worker innerScheduler;
    private final Action0 underlying;
    
    public SleepingAction(final Action0 underlying, final Scheduler.Worker innerScheduler, final long execTime) {
        this.underlying = underlying;
        this.innerScheduler = innerScheduler;
        this.execTime = execTime;
    }
    
    @Override
    public void call() {
        if (!this.innerScheduler.isUnsubscribed()) {
            final long n = this.execTime - this.innerScheduler.now();
            while (true) {
                if (n <= 0L) {
                    break Label_0034;
                }
                try {
                    Thread.sleep(n);
                    if (!this.innerScheduler.isUnsubscribed()) {
                        this.underlying.call();
                    }
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    Exceptions.propagate(ex);
                    continue;
                }
                break;
            }
        }
    }
}
