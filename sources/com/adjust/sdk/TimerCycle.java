package com.adjust.sdk;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TimerCycle {
    private Runnable command;
    private long cycleDelay;
    private CustomScheduledExecutor executor;
    private long initialDelay;
    private boolean isPaused = true;
    private ILogger logger = AdjustFactory.getLogger();
    private String name;
    private ScheduledFuture waitingTask;

    /* renamed from: com.adjust.sdk.TimerCycle$1 */
    class C01011 implements Runnable {
        C01011() {
        }

        public void run() {
            TimerCycle.this.logger.verbose("%s fired", TimerCycle.this.name);
            TimerCycle.this.command.run();
        }
    }

    public TimerCycle(Runnable command, long initialDelay, long cycleDelay, String name) {
        this.executor = new CustomScheduledExecutor(name, true);
        this.name = name;
        this.command = command;
        this.initialDelay = initialDelay;
        this.cycleDelay = cycleDelay;
        String cycleDelaySecondsString = Util.SecondsDisplayFormat.format(((double) cycleDelay) / 1000.0d);
        String initialDelaySecondsString = Util.SecondsDisplayFormat.format(((double) initialDelay) / 1000.0d);
        this.logger.verbose("%s configured to fire after %s seconds of starting and cycles every %s seconds", name, initialDelaySecondsString, cycleDelaySecondsString);
    }

    public void start() {
        if (this.isPaused) {
            this.logger.verbose("%s starting", this.name);
            this.waitingTask = this.executor.scheduleWithFixedDelay(new C01011(), this.initialDelay, this.cycleDelay, TimeUnit.MILLISECONDS);
            this.isPaused = false;
            return;
        }
        this.logger.verbose("%s is already started", this.name);
    }

    public void suspend() {
        if (this.isPaused) {
            this.logger.verbose("%s is already suspended", this.name);
            return;
        }
        this.initialDelay = this.waitingTask.getDelay(TimeUnit.MILLISECONDS);
        this.waitingTask.cancel(false);
        String initialDelaySeconds = Util.SecondsDisplayFormat.format(((double) this.initialDelay) / 1000.0d);
        this.logger.verbose("%s suspended with %s seconds left", this.name, initialDelaySeconds);
        this.isPaused = true;
    }

    private void cancel(boolean mayInterruptIfRunning) {
        if (this.waitingTask != null) {
            this.waitingTask.cancel(mayInterruptIfRunning);
        }
        this.waitingTask = null;
    }

    public void teardown() {
        cancel(true);
        if (this.executor != null) {
            try {
                this.executor.shutdownNow();
            } catch (SecurityException e) {
            }
        }
        this.executor = null;
    }
}
