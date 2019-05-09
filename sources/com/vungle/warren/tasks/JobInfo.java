package com.vungle.warren.tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class JobInfo implements Cloneable {
    private static final String TAG = "JobInfo";
    private long delay = 0;
    private Bundle extras = new Bundle();
    private long nextRescheduleTimeout;
    private int priority = 2;
    private int reschedulePolicy = 1;
    private long rescheduleTimeout;
    private final String tag;
    private boolean updateCurrent;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Priority {
        public static final int CRITICAL = 5;
        public static final int HIGH = 3;
        public static final int HIGHEST = 4;
        public static final int LOW = 1;
        public static final int LOWEST = 0;
        public static final int NORMAL = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ReschedulePolicy {
        public static final int EXPONENTIAL = 1;
        public static final int LINEAR = 0;
    }

    public JobInfo(@NonNull String tag) {
        this.tag = tag;
    }

    public JobInfo setUpdateCurrent(boolean updateCurrent) {
        this.updateCurrent = updateCurrent;
        return this;
    }

    public JobInfo setExtras(@NonNull Bundle extras) {
        if (extras != null) {
            this.extras = extras;
        }
        return this;
    }

    public JobInfo setDelay(long delay) {
        this.delay = delay;
        return this;
    }

    public JobInfo setReschedulePolicy(long rescheduleTimeout, int delayCriteria) {
        this.rescheduleTimeout = rescheduleTimeout;
        this.reschedulePolicy = delayCriteria;
        return this;
    }

    public JobInfo setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public String getJobTag() {
        return this.tag;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public long getDelay() {
        return this.delay;
    }

    public long makeNextRescedule() {
        if (this.rescheduleTimeout == 0) {
            return 0;
        }
        if (this.nextRescheduleTimeout == 0) {
            this.nextRescheduleTimeout = this.rescheduleTimeout;
        } else if (this.reschedulePolicy == 1) {
            this.nextRescheduleTimeout *= 2;
        }
        return this.nextRescheduleTimeout;
    }

    public boolean getUpdateCurrent() {
        return this.updateCurrent;
    }

    public int getPriority() {
        return this.priority;
    }

    public JobInfo copy() {
        try {
            return (JobInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return null;
        }
    }
}
