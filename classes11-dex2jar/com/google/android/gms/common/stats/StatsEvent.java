// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.stats;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
public abstract class StatsEvent extends AbstractSafeParcelable implements ReflectedParcelable
{
    public abstract int getEventType();
    
    public abstract long getTimeMillis();
    
    @Override
    public String toString() {
        final long timeMillis = this.getTimeMillis();
        final int eventType = this.getEventType();
        final long zzu = this.zzu();
        final String zzv = this.zzv();
        return new StringBuilder(String.valueOf(zzv).length() + 53).append(timeMillis).append("\t").append(eventType).append("\t").append(zzu).append(zzv).toString();
    }
    
    public abstract long zzu();
    
    public abstract String zzv();
    
    @KeepForSdk
    public interface Types
    {
        @KeepForSdk
        public static final int EVENT_TYPE_ACQUIRE_WAKE_LOCK = 7;
        @KeepForSdk
        public static final int EVENT_TYPE_RELEASE_WAKE_LOCK = 8;
    }
}
