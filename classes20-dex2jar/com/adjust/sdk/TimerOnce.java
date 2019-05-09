// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledFuture;

public class TimerOnce
{
    private Runnable command;
    private CustomScheduledExecutor executor;
    private ILogger logger;
    private String name;
    private ScheduledFuture waitingTask;
    
    public TimerOnce(final Runnable command, final String name) {
        this.name = name;
        this.executor = new CustomScheduledExecutor(name, true);
        this.command = command;
        this.logger = AdjustFactory.getLogger();
    }
    
    private void cancel(final boolean b) {
        if (this.waitingTask != null) {
            this.waitingTask.cancel(b);
        }
        this.waitingTask = null;
        this.logger.verbose("%s canceled", this.name);
    }
    
    public void cancel() {
        this.cancel(false);
    }
    
    public long getFireIn() {
        if (this.waitingTask == null) {
            return 0L;
        }
        return this.waitingTask.getDelay(TimeUnit.MILLISECONDS);
    }
    
    public void startIn(final long n) {
        this.cancel(false);
        this.logger.verbose("%s starting. Launching in %s seconds", this.name, Util.SecondsDisplayFormat.format(n / 1000.0));
        this.waitingTask = this.executor.schedule(new Runnable() {
            @Override
            public void run() {
                TimerOnce.this.logger.verbose("%s fired", TimerOnce.this.name);
                TimerOnce.this.command.run();
                TimerOnce.this.waitingTask = null;
            }
        }, n, TimeUnit.MILLISECONDS);
    }
    
    public void teardown() {
        this.cancel(true);
        this.executor = null;
    }
}
