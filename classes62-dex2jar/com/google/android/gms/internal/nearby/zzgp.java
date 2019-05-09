// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import java.util.Arrays;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import android.os.ParcelUuid;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "BleFilterCreator")
public final class zzgp extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzgp> CREATOR;
    @SafeParcelable$VersionField(getter = "getVersionCode", id = 1)
    private final int zzex;
    @Nullable
    @SafeParcelable$Field(getter = "getServiceUuid", id = 4)
    private final ParcelUuid zzge;
    @Nullable
    @SafeParcelable$Field(getter = "getServiceUuidMask", id = 5)
    private final ParcelUuid zzgf;
    @Nullable
    @SafeParcelable$Field(getter = "getServiceDataUuid", id = 6)
    private final ParcelUuid zzgg;
    @Nullable
    @SafeParcelable$Field(getter = "getServiceData", id = 7)
    private final byte[] zzgh;
    @Nullable
    @SafeParcelable$Field(getter = "getServiceDataMask", id = 8)
    private final byte[] zzgi;
    @SafeParcelable$Field(getter = "getManufacturerId", id = 9)
    private final int zzgj;
    @Nullable
    @SafeParcelable$Field(getter = "getManufacturerData", id = 10)
    private final byte[] zzgk;
    @Nullable
    @SafeParcelable$Field(getter = "getManufacturerDataMask", id = 11)
    private final byte[] zzgl;
    
    static {
        CREATOR = (Parcelable$Creator)new zzgq();
    }
    
    @SafeParcelable$Constructor
    zzgp(@SafeParcelable$Param(id = 1) final int zzex, @SafeParcelable$Param(id = 4) final ParcelUuid zzge, @SafeParcelable$Param(id = 5) final ParcelUuid zzgf, @SafeParcelable$Param(id = 6) final ParcelUuid zzgg, @SafeParcelable$Param(id = 7) final byte[] zzgh, @SafeParcelable$Param(id = 8) final byte[] zzgi, @SafeParcelable$Param(id = 9) final int zzgj, @SafeParcelable$Param(id = 10) final byte[] zzgk, @SafeParcelable$Param(id = 11) final byte[] zzgl) {
        this.zzex = zzex;
        this.zzge = zzge;
        this.zzgf = zzgf;
        this.zzgg = zzgg;
        this.zzgh = zzgh;
        this.zzgi = zzgi;
        this.zzgj = zzgj;
        this.zzgk = zzgk;
        this.zzgl = zzgl;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final zzgp zzgp = (zzgp)o;
            if (this.zzgj != zzgp.zzgj || !Arrays.equals(this.zzgk, zzgp.zzgk) || !Arrays.equals(this.zzgl, zzgp.zzgl) || !Objects.equal((Object)this.zzgg, (Object)zzgp.zzgg) || !Arrays.equals(this.zzgh, zzgp.zzgh) || !Arrays.equals(this.zzgi, zzgp.zzgi) || !Objects.equal((Object)this.zzge, (Object)zzgp.zzge) || !Objects.equal((Object)this.zzgf, (Object)zzgp.zzgf)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzgj, Arrays.hashCode(this.zzgk), Arrays.hashCode(this.zzgl), this.zzgg, Arrays.hashCode(this.zzgh), Arrays.hashCode(this.zzgi), this.zzge, this.zzgf });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzex);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzge, n, false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzgf, n, false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzgg, n, false);
        SafeParcelWriter.writeByteArray(parcel, 7, this.zzgh, false);
        SafeParcelWriter.writeByteArray(parcel, 8, this.zzgi, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzgj);
        SafeParcelWriter.writeByteArray(parcel, 10, this.zzgk, false);
        SafeParcelWriter.writeByteArray(parcel, 11, this.zzgl, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
