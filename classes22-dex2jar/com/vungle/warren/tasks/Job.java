// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.tasks;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.os.Bundle;

public interface Job
{
    int onRunJob(final Bundle p0, final JobRunner p1);
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
        public static final int FAILURE = 1;
        public static final int RESCHEDULE = 2;
        public static final int SUCCESS = 0;
    }
}
