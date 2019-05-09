// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "DeviceOrientationRequestCreator")
public final class zzj extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzj> CREATOR;
    @SafeParcelable$Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SHOULD_USE_MAG", id = 1)
    private boolean zzt;
    @SafeParcelable$Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_MINIMUM_SAMPLING_PERIOD_MS", id = 2)
    private long zzu;
    @SafeParcelable$Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SMALLEST_ANGLE_CHANGE_RADIANS", id = 3)
    private float zzv;
    @SafeParcelable$Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_EXPIRE_AT_MS", id = 4)
    private long zzw;
    @SafeParcelable$Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_NUM_UPDATES", id = 5)
    private int zzx;
    
    static {
        CREATOR = (Parcelable$Creator)new zzk();
    }
    
    public zzj() {
        this(true, 50L, 0.0f, Long.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    @SafeParcelable$Constructor
    zzj(@SafeParcelable$Param(id = 1) final boolean zzt, @SafeParcelable$Param(id = 2) final long zzu, @SafeParcelable$Param(id = 3) final float zzv, @SafeParcelable$Param(id = 4) final long zzw, @SafeParcelable$Param(id = 5) final int zzx) {
        this.zzt = zzt;
        this.zzu = zzu;
        this.zzv = zzv;
        this.zzw = zzw;
        this.zzx = zzx;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzj)) {
                return false;
            }
            final zzj zzj = (zzj)o;
            if (this.zzt != zzj.zzt || this.zzu != zzj.zzu || Float.compare(this.zzv, zzj.zzv) != 0 || this.zzw != zzj.zzw || this.zzx != zzj.zzx) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzt, this.zzu, this.zzv, this.zzw, this.zzx });
    }
    
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceOrientationRequest[mShouldUseMag=").append(this.zzt);
        sb.append(" mMinimumSamplingPeriodMs=").append(this.zzu);
        sb.append(" mSmallestAngleChangeRadians=").append(this.zzv);
        if (this.zzw != Long.MAX_VALUE) {
            final long zzw = this.zzw;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(zzw - elapsedRealtime).append("ms");
        }
        if (this.zzx != Integer.MAX_VALUE) {
            sb.append(" num=").append(this.zzx);
        }
        sb.append(']');
        return sb.toString();
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzt);
        SafeParcelWriter.writeLong(parcel, 2, this.zzu);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzv);
        SafeParcelWriter.writeLong(parcel, 4, this.zzw);
        SafeParcelWriter.writeInt(parcel, 5, this.zzx);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
