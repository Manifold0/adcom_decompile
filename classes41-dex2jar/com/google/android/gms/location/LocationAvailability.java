// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import java.util.Arrays;
import android.content.Intent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "LocationAvailabilityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class LocationAvailability extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<LocationAvailability> CREATOR;
    @Deprecated
    @SafeParcelable$Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", id = 1)
    private int zzar;
    @Deprecated
    @SafeParcelable$Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", id = 2)
    private int zzas;
    @SafeParcelable$Field(defaultValueUnchecked = "0", id = 3)
    private long zzat;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNSUCCESSFUL", id = 4)
    private int zzau;
    @SafeParcelable$Field(id = 5)
    private zzaj[] zzav;
    
    static {
        CREATOR = (Parcelable$Creator)new zzaa();
    }
    
    @SafeParcelable$Constructor
    LocationAvailability(@SafeParcelable$Param(id = 4) final int zzau, @SafeParcelable$Param(id = 1) final int zzar, @SafeParcelable$Param(id = 2) final int zzas, @SafeParcelable$Param(id = 3) final long zzat, @SafeParcelable$Param(id = 5) final zzaj[] zzav) {
        this.zzau = zzau;
        this.zzar = zzar;
        this.zzas = zzas;
        this.zzat = zzat;
        this.zzav = zzav;
    }
    
    public static LocationAvailability extractLocationAvailability(final Intent intent) {
        if (!hasLocationAvailability(intent)) {
            return null;
        }
        return (LocationAvailability)intent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
    }
    
    public static boolean hasLocationAvailability(final Intent intent) {
        return intent != null && intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final LocationAvailability locationAvailability = (LocationAvailability)o;
            if (this.zzar != locationAvailability.zzar || this.zzas != locationAvailability.zzas || this.zzat != locationAvailability.zzat || this.zzau != locationAvailability.zzau || !Arrays.equals(this.zzav, locationAvailability.zzav)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzau, this.zzar, this.zzas, this.zzat, this.zzav });
    }
    
    public final boolean isLocationAvailable() {
        return this.zzau < 1000;
    }
    
    public final String toString() {
        return new StringBuilder(48).append("LocationAvailability[isLocationAvailable: ").append(this.isLocationAvailable()).append("]").toString();
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzar);
        SafeParcelWriter.writeInt(parcel, 2, this.zzas);
        SafeParcelWriter.writeLong(parcel, 3, this.zzat);
        SafeParcelWriter.writeInt(parcel, 4, this.zzau);
        SafeParcelWriter.writeTypedArray(parcel, 5, (Parcelable[])this.zzav, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
