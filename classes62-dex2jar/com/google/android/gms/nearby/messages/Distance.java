// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.support.annotation.NonNull;
import com.google.android.gms.nearby.messages.internal.zze;

public interface Distance extends Comparable<Distance>
{
    public static final Distance UNKNOWN = new zze(1, Double.NaN);
    
    int compareTo(@NonNull final Distance p0);
    
    int getAccuracy();
    
    double getMeters();
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface Accuracy {
        public static final int LOW = 1;
    }
}
