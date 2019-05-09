// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ActivityTransitionEventCreator")
@SafeParcelable$Reserved({ 1000 })
public class ActivityTransitionEvent extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<ActivityTransitionEvent> CREATOR;
    @SafeParcelable$Field(getter = "getActivityType", id = 1)
    private final int zzi;
    @SafeParcelable$Field(getter = "getTransitionType", id = 2)
    private final int zzj;
    @SafeParcelable$Field(getter = "getElapsedRealTimeNanos", id = 3)
    private final long zzk;
    
    static {
        CREATOR = (Parcelable$Creator)new zzd();
    }
    
    @SafeParcelable$Constructor
    public ActivityTransitionEvent(@SafeParcelable$Param(id = 1) final int zzi, @SafeParcelable$Param(id = 2) final int zzj, @SafeParcelable$Param(id = 3) final long zzk) {
        DetectedActivity.zzb(zzi);
        ActivityTransition.zza(zzj);
        this.zzi = zzi;
        this.zzj = zzj;
        this.zzk = zzk;
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof ActivityTransitionEvent)) {
                return false;
            }
            final ActivityTransitionEvent activityTransitionEvent = (ActivityTransitionEvent)o;
            if (this.zzi != activityTransitionEvent.zzi || this.zzj != activityTransitionEvent.zzj || this.zzk != activityTransitionEvent.zzk) {
                return false;
            }
        }
        return true;
    }
    
    public int getActivityType() {
        return this.zzi;
    }
    
    public long getElapsedRealTimeNanos() {
        return this.zzk;
    }
    
    public int getTransitionType() {
        return this.zzj;
    }
    
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.zzi, this.zzj, this.zzk });
    }
    
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(new StringBuilder(24).append("ActivityType ").append(this.zzi).toString());
        sb.append(" ");
        sb.append(new StringBuilder(26).append("TransitionType ").append(this.zzj).toString());
        sb.append(" ");
        sb.append(new StringBuilder(41).append("ElapsedRealTimeNanos ").append(this.zzk).toString());
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.getActivityType());
        SafeParcelWriter.writeInt(parcel, 2, this.getTransitionType());
        SafeParcelWriter.writeLong(parcel, 3, this.getElapsedRealTimeNanos());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
