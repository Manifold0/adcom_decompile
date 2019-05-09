// 
// Decompiled by Procyon v0.5.34
// 

package rx.schedulers;

import rx.Scheduler;

@Deprecated
public final class NewThreadScheduler extends Scheduler
{
    private NewThreadScheduler() {
        throw new IllegalStateException("No instances!");
    }
    
    @Override
    public Worker createWorker() {
        return null;
    }
}
