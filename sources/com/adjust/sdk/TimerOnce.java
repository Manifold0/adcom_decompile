package com.adjust.sdk;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TimerOnce {
    private Runnable command;
    private CustomScheduledExecutor executor;
    private ILogger logger = AdjustFactory.getLogger();
    private String name;
    private ScheduledFuture waitingTask;

    /* renamed from: com.adjust.sdk.TimerOnce$1 */
    class C01021 implements Runnable {
        C01021() {
        }

        public void run() {
            TimerOnce.this.logger.verbose("%s fired", TimerOnce.this.name);
            TimerOnce.this.command.run();
            TimerOnce.this.waitingTask = null;
        }
    }

    public TimerOnce(Runnable command, String name) {
        this.name = name;
        this.executor = new CustomScheduledExecutor(name, true);
        this.command = command;
    }

    public void startIn(long fireIn) {
        cancel(false);
        String fireInSeconds = Util.SecondsDisplayFormat.format(((double) fireIn) / 1000.0d);
        this.logger.verbose("%s starting. Launching in %s seconds", this.name, fireInSeconds);
        this.waitingTask = this.executor.schedule(new C01021(), fireIn, TimeUnit.MILLISECONDS);
    }

    public long getFireIn() {
        if (this.waitingTask == null) {
            return 0;
        }
        return this.waitingTask.getDelay(TimeUnit.MILLISECONDS);
    }

    private void cancel(boolean mayInterruptIfRunning) {
        if (this.waitingTask != null) {
            this.waitingTask.cancel(mayInterruptIfRunning);
        }
        this.waitingTask = null;
        this.logger.verbose("%s canceled", this.name);
    }

    public void cancel() {
        cancel(false);
    }

    public void teardown() {
        cancel(true);
        this.executor = null;
    }
}
