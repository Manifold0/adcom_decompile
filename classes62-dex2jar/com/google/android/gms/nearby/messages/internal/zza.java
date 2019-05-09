// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.nearby.messages.BleSignal;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "BleSignalImplCreator")
public final class zza extends AbstractSafeParcelable implements BleSignal
{
    public static final Parcelable$Creator<zza> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @SafeParcelable$Field(id = 2)
    private final int zzhb;
    @SafeParcelable$Field(id = 3)
    private final int zzhc;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    @SafeParcelable$Constructor
    zza(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final int zzhb, @SafeParcelable$Param(id = 3) int zzhc) {
        this.versionCode = versionCode;
        this.zzhb = zzhb;
        if (-169 >= zzhc || zzhc >= 87) {
            zzhc = Integer.MIN_VALUE;
        }
        this.zzhc = zzhc;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof BleSignal)) {
                return false;
            }
            final BleSignal bleSignal = (BleSignal)o;
            if (this.zzhb != bleSignal.getRssi() || this.zzhc != bleSignal.getTxPower()) {
                return false;
            }
        }
        return true;
    }
    
    public final int getRssi() {
        return this.zzhb;
    }
    
    public final int getTxPower() {
        return this.zzhc;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzhb, this.zzhc });
    }
    
    public final String toString() {
        return new StringBuilder(48).append("BleSignal{rssi=").append(this.zzhb).append(", txPower=").append(this.zzhc).append('}').toString();
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel, 2, this.zzhb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzhc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
