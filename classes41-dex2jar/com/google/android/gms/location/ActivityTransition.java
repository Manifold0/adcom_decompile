// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ActivityTransitionCreator")
@SafeParcelable$Reserved({ 1000 })
public class ActivityTransition extends AbstractSafeParcelable
{
    public static final int ACTIVITY_TRANSITION_ENTER = 0;
    public static final int ACTIVITY_TRANSITION_EXIT = 1;
    public static final Parcelable$Creator<ActivityTransition> CREATOR;
    @SafeParcelable$Field(getter = "getActivityType", id = 1)
    private final int zzi;
    @SafeParcelable$Field(getter = "getTransitionType", id = 2)
    private final int zzj;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    @SafeParcelable$Constructor
    ActivityTransition(@SafeParcelable$Param(id = 1) final int zzi, @SafeParcelable$Param(id = 2) final int zzj) {
        this.zzi = zzi;
        this.zzj = zzj;
    }
    
    public static void zza(final int n) {
        boolean b = true;
        if (n < 0 || n > 1) {
            b = false;
        }
        Preconditions.checkArgument(b, (Object)new StringBuilder(41).append("Transition type ").append(n).append(" is not valid.").toString());
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof ActivityTransition)) {
                return false;
            }
            final ActivityTransition activityTransition = (ActivityTransition)o;
            if (this.zzi != activityTransition.zzi || this.zzj != activityTransition.zzj) {
                return false;
            }
        }
        return true;
    }
    
    public int getActivityType() {
        return this.zzi;
    }
    
    public int getTransitionType() {
        return this.zzj;
    }
    
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.zzi, this.zzj });
    }
    
    public String toString() {
        return new StringBuilder(75).append("ActivityTransition [mActivityType=").append(this.zzi).append(", mTransitionType=").append(this.zzj).append(']').toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.getActivityType());
        SafeParcelWriter.writeInt(parcel, 2, this.getTransitionType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public static class Builder
    {
        private int zzi;
        private int zzj;
        
        public Builder() {
            this.zzi = -1;
            this.zzj = -1;
        }
        
        public ActivityTransition build() {
            final boolean b = true;
            Preconditions.checkState(this.zzi != -1, (Object)"Activity type not set.");
            Preconditions.checkState(this.zzj != -1 && b, (Object)"Activity transition type not set.");
            return new ActivityTransition(this.zzi, this.zzj);
        }
        
        public Builder setActivityTransition(final int zzj) {
            ActivityTransition.zza(zzj);
            this.zzj = zzj;
            return this;
        }
        
        public Builder setActivityType(final int zzi) {
            DetectedActivity.zzb(zzi);
            this.zzi = zzi;
            return this;
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface SupportedActivityTransition {
    }
}
