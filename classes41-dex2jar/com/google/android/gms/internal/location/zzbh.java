// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Locale;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ParcelableGeofenceCreator")
@SafeParcelable$Reserved({ 1000 })
@VisibleForTesting
public final class zzbh extends AbstractSafeParcelable implements Geofence
{
    public static final Parcelable$Creator<zzbh> CREATOR;
    @SafeParcelable$Field(getter = "getRequestId", id = 1)
    private final String zzad;
    @SafeParcelable$Field(getter = "getTransitionTypes", id = 7)
    private final int zzae;
    @SafeParcelable$Field(getter = "getType", id = 3)
    private final short zzag;
    @SafeParcelable$Field(getter = "getLatitude", id = 4)
    private final double zzah;
    @SafeParcelable$Field(getter = "getLongitude", id = 5)
    private final double zzai;
    @SafeParcelable$Field(getter = "getRadius", id = 6)
    private final float zzaj;
    @SafeParcelable$Field(defaultValue = "0", getter = "getNotificationResponsiveness", id = 8)
    private final int zzak;
    @SafeParcelable$Field(defaultValue = "-1", getter = "getLoiteringDelay", id = 9)
    private final int zzal;
    @SafeParcelable$Field(getter = "getExpirationTime", id = 2)
    private final long zzdo;
    
    static {
        CREATOR = (Parcelable$Creator)new zzbi();
    }
    
    @SafeParcelable$Constructor
    public zzbh(@SafeParcelable$Param(id = 1) String zzad, @SafeParcelable$Param(id = 7) final int n, @SafeParcelable$Param(id = 3) final short zzag, @SafeParcelable$Param(id = 4) final double zzah, @SafeParcelable$Param(id = 5) final double zzai, @SafeParcelable$Param(id = 6) final float zzaj, @SafeParcelable$Param(id = 2) final long zzdo, @SafeParcelable$Param(id = 8) final int zzak, @SafeParcelable$Param(id = 9) final int zzal) {
        if (zzad == null || zzad.length() > 100) {
            zzad = String.valueOf(zzad);
            if (zzad.length() != 0) {
                zzad = "requestId is null or too long: ".concat(zzad);
            }
            else {
                zzad = new String("requestId is null or too long: ");
            }
            throw new IllegalArgumentException(zzad);
        }
        if (zzaj <= 0.0f) {
            throw new IllegalArgumentException(new StringBuilder(31).append("invalid radius: ").append(zzaj).toString());
        }
        if (zzah > 90.0 || zzah < -90.0) {
            throw new IllegalArgumentException(new StringBuilder(42).append("invalid latitude: ").append(zzah).toString());
        }
        if (zzai > 180.0 || zzai < -180.0) {
            throw new IllegalArgumentException(new StringBuilder(43).append("invalid longitude: ").append(zzai).toString());
        }
        final int zzae = n & 0x7;
        if (zzae == 0) {
            throw new IllegalArgumentException(new StringBuilder(46).append("No supported transition specified: ").append(n).toString());
        }
        this.zzag = zzag;
        this.zzad = zzad;
        this.zzah = zzah;
        this.zzai = zzai;
        this.zzaj = zzaj;
        this.zzdo = zzdo;
        this.zzae = zzae;
        this.zzak = zzak;
        this.zzal = zzal;
    }
    
    public static zzbh zza(final byte[] array) {
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(array, 0, array.length);
        obtain.setDataPosition(0);
        final zzbh zzbh = (zzbh)com.google.android.gms.internal.location.zzbh.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return zzbh;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof zzbh)) {
                return false;
            }
            final zzbh zzbh = (zzbh)o;
            if (this.zzaj != zzbh.zzaj) {
                return false;
            }
            if (this.zzah != zzbh.zzah) {
                return false;
            }
            if (this.zzai != zzbh.zzai) {
                return false;
            }
            if (this.zzag != zzbh.zzag) {
                return false;
            }
        }
        return true;
    }
    
    public final String getRequestId() {
        return this.zzad;
    }
    
    public final int hashCode() {
        final long doubleToLongBits = Double.doubleToLongBits(this.zzah);
        final int n = (int)(doubleToLongBits ^ doubleToLongBits >>> 32);
        final long doubleToLongBits2 = Double.doubleToLongBits(this.zzai);
        return ((((n + 31) * 31 + (int)(doubleToLongBits2 ^ doubleToLongBits2 >>> 32)) * 31 + Float.floatToIntBits(this.zzaj)) * 31 + this.zzag) * 31 + this.zzae;
    }
    
    public final String toString() {
        final Locale us = Locale.US;
        Object o = null;
        switch (this.zzag) {
            default: {
                o = null;
                break;
            }
            case 1: {
                o = "CIRCLE";
                break;
            }
        }
        return String.format(us, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", o, this.zzad.replaceAll("\\p{C}", "?"), this.zzae, this.zzah, this.zzai, this.zzaj, this.zzak / 1000, this.zzal, this.zzdo);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.getRequestId(), false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzdo);
        SafeParcelWriter.writeShort(parcel, 3, this.zzag);
        SafeParcelWriter.writeDouble(parcel, 4, this.zzah);
        SafeParcelWriter.writeDouble(parcel, 5, this.zzai);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzaj);
        SafeParcelWriter.writeInt(parcel, 7, this.zzae);
        SafeParcelWriter.writeInt(parcel, 8, this.zzak);
        SafeParcelWriter.writeInt(parcel, 9, this.zzal);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
