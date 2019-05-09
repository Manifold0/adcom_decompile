// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import rx.Scheduler;

public final class NewThreadScheduler extends Scheduler
{
    private final ThreadFactory threadFactory;
    
    public NewThreadScheduler(final ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }
    
    @Override
    public Worker createWorker() {
        return new NewThreadWorker(this.threadFactory);
    }
}
