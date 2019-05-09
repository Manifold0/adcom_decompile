// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Locale;
import com.google.android.gms.common.internal.Objects;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.nearby.messages.Distance;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "DistanceImplCreator")
public final class zze extends AbstractSafeParcelable implements Distance
{
    public static final Parcelable$Creator<zze> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final int accuracy;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @SafeParcelable$Field(id = 3)
    private final double zzhg;
    
    static {
        CREATOR = (Parcelable$Creator)new zzf();
    }
    
    public zze(final int n, final double n2) {
        this(1, 1, Double.NaN);
    }
    
    @SafeParcelable$Constructor
    zze(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final int accuracy, @SafeParcelable$Param(id = 3) final double zzhg) {
        this.versionCode = versionCode;
        this.accuracy = accuracy;
        this.zzhg = zzhg;
    }
    
    public final int compareTo(@NonNull final Distance distance) {
        if (Double.isNaN(this.getMeters()) && Double.isNaN(distance.getMeters())) {
            return 0;
        }
        return Double.compare(this.getMeters(), distance.getMeters());
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zze)) {
                return false;
            }
            final zze zze = (zze)o;
            if (this.getAccuracy() != zze.getAccuracy() || this.compareTo(zze) != 0) {
                return false;
            }
        }
        return true;
    }
    
    public final int getAccuracy() {
        return this.accuracy;
    }
    
    public final double getMeters() {
        return this.zzhg;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.getAccuracy(), this.getMeters() });
    }
    
    public final String toString() {
        final Locale us = Locale.US;
        final double zzhg = this.zzhg;
        String s = null;
        switch (this.accuracy) {
            default: {
                s = "UNKNOWN";
                break;
            }
            case 1: {
                s = "LOW";
                break;
            }
        }
        return String.format(us, "(%.1fm, %s)", zzhg, s);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel, 2, this.accuracy);
        SafeParcelWriter.writeDouble(parcel, 3, this.zzhg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
