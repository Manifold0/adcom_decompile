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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "NetworkLocationStatusCreator")
public final class zzaj extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzaj> CREATOR;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", id = 2)
    private final int zzar;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", id = 1)
    private final int zzas;
    @SafeParcelable$Field(defaultValueUnchecked = "NetworkLocationStatus.STATUS_INVALID_TIMESTAMP", id = 4)
    private final long zzat;
    @SafeParcelable$Field(defaultValueUnchecked = "NetworkLocationStatus.STATUS_INVALID_TIMESTAMP", id = 3)
    private final long zzbt;
    
    static {
        CREATOR = (Parcelable$Creator)new zzak();
    }
    
    @SafeParcelable$Constructor
    zzaj(@SafeParcelable$Param(id = 1) final int zzas, @SafeParcelable$Param(id = 2) final int zzar, @SafeParcelable$Param(id = 3) final long zzbt, @SafeParcelable$Param(id = 4) final long zzat) {
        this.zzas = zzas;
        this.zzar = zzar;
        this.zzbt = zzbt;
        this.zzat = zzat;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final zzaj zzaj = (zzaj)o;
            if (this.zzas != zzaj.zzas || this.zzar != zzaj.zzar || this.zzbt != zzaj.zzbt || this.zzat != zzaj.zzat) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzar, this.zzas, this.zzat, this.zzbt });
    }
    
    public final String toString() {
        final StringBuilder sb = new StringBuilder("NetworkLocationStatus:");
        sb.append(" Wifi status: ").append(this.zzas).append(" Cell status: ").append(this.zzar).append(" elapsed time NS: ").append(this.zzat).append(" system time ms: ").append(this.zzbt);
        return sb.toString();
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzas);
        SafeParcelWriter.writeInt(parcel, 2, this.zzar);
        SafeParcelWriter.writeLong(parcel, 3, this.zzbt);
        SafeParcelWriter.writeLong(parcel, 4, this.zzat);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
