// 
// Decompiled by Procyon v0.5.34
// 

package rx.schedulers;

import rx.Scheduler;

@Deprecated
public final class ImmediateScheduler extends Scheduler
{
    private ImmediateScheduler() {
        throw new IllegalStateException("No instances!");
    }
    
    @Override
    public Worker createWorker() {
        return null;
    }
}
