// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.tasks;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.util.Log;
import android.support.annotation.NonNull;
import android.os.Bundle;

public class JobInfo implements Cloneable
{
    private static final String TAG = "JobInfo";
    private long delay;
    private Bundle extras;
    private long nextRescheduleTimeout;
    private int priority;
    private int reschedulePolicy;
    private long rescheduleTimeout;
    private final String tag;
    private boolean updateCurrent;
    
    public JobInfo(@NonNull final String tag) {
        this.delay = 0L;
        this.extras = new Bundle();
        this.reschedulePolicy = 1;
        this.priority = 2;
        this.tag = tag;
    }
    
    public JobInfo copy() {
        try {
            return (JobInfo)super.clone();
        }
        catch (CloneNotSupportedException ex) {
            Log.e("JobInfo", Log.getStackTraceString((Throwable)ex));
            return null;
        }
    }
    
    public long getDelay() {
        return this.delay;
    }
    
    public Bundle getExtras() {
        return this.extras;
    }
    
    public String getJobTag() {
        return this.tag;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public boolean getUpdateCurrent() {
        return this.updateCurrent;
    }
    
    public long makeNextRescedule() {
        if (this.rescheduleTimeout == 0L) {
            return 0L;
        }
        if (this.nextRescheduleTimeout == 0L) {
            this.nextRescheduleTimeout = this.rescheduleTimeout;
        }
        else if (this.reschedulePolicy == 1) {
            this.nextRescheduleTimeout *= 2L;
        }
        return this.nextRescheduleTimeout;
    }
    
    public JobInfo setDelay(final long delay) {
        this.delay = delay;
        return this;
    }
    
    public JobInfo setExtras(@NonNull final Bundle extras) {
        if (extras != null) {
            this.extras = extras;
        }
        return this;
    }
    
    public JobInfo setPriority(final int priority) {
        this.priority = priority;
        return this;
    }
    
    public JobInfo setReschedulePolicy(final long rescheduleTimeout, final int reschedulePolicy) {
        this.rescheduleTimeout = rescheduleTimeout;
        this.reschedulePolicy = reschedulePolicy;
        return this;
    }
    
    public JobInfo setUpdateCurrent(final boolean updateCurrent) {
        this.updateCurrent = updateCurrent;
        return this;
    }
    
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
}
