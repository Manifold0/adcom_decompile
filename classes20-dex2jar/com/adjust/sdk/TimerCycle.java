// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledFuture;

public class TimerCycle
{
    private Runnable command;
    private long cycleDelay;
    private CustomScheduledExecutor executor;
    private long initialDelay;
    private boolean isPaused;
    private ILogger logger;
    private String name;
    private ScheduledFuture waitingTask;
    
    public TimerCycle(final Runnable command, final long initialDelay, final long cycleDelay, final String name) {
        this.executor = new CustomScheduledExecutor(name, true);
        this.name = name;
        this.command = command;
        this.initialDelay = initialDelay;
        this.cycleDelay = cycleDelay;
        this.isPaused = true;
        (this.logger = AdjustFactory.getLogger()).verbose("%s configured to fire after %s seconds of starting and cycles every %s seconds", name, Util.SecondsDisplayFormat.format(initialDelay / 1000.0), Util.SecondsDisplayFormat.format(cycleDelay / 1000.0));
    }
    
    private void cancel(final boolean b) {
        if (this.waitingTask != null) {
            this.waitingTask.cancel(b);
        }
        this.waitingTask = null;
    }
    
    public void start() {
        if (!this.isPaused) {
            this.logger.verbose("%s is already started", this.name);
            return;
        }
        this.logger.verbose("%s starting", this.name);
        this.waitingTask = this.executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                TimerCycle.this.logger.verbose("%s fired", TimerCycle.this.name);
                TimerCycle.this.command.run();
            }
        }, this.initialDelay, this.cycleDelay, TimeUnit.MILLISECONDS);
        this.isPaused = false;
    }
    
    public void suspend() {
        if (this.isPaused) {
            this.logger.verbose("%s is already suspended", this.name);
            return;
        }
        this.initialDelay = this.waitingTask.getDelay(TimeUnit.MILLISECONDS);
        this.waitingTask.cancel(false);
        this.logger.verbose("%s suspended with %s seconds left", this.name, Util.SecondsDisplayFormat.format(this.initialDelay / 1000.0));
        this.isPaused = true;
    }
    
    public void teardown() {
        this.cancel(true);
        while (true) {
            if (this.executor == null) {
                break Label_0019;
            }
            try {
                this.executor.shutdownNow();
                this.executor = null;
            }
            catch (SecurityException ex) {
                continue;
            }
            break;
        }
    }
}
