package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.Arrays;

@Class(creator = "BleFilterCreator")
public final class zzgp extends AbstractSafeParcelable {
    public static final Creator<zzgp> CREATOR = new zzgq();
    @VersionField(getter = "getVersionCode", id = 1)
    private final int zzex;
    @Nullable
    @Field(getter = "getServiceUuid", id = 4)
    private final ParcelUuid zzge;
    @Nullable
    @Field(getter = "getServiceUuidMask", id = 5)
    private final ParcelUuid zzgf;
    @Nullable
    @Field(getter = "getServiceDataUuid", id = 6)
    private final ParcelUuid zzgg;
    @Nullable
    @Field(getter = "getServiceData", id = 7)
    private final byte[] zzgh;
    @Nullable
    @Field(getter = "getServiceDataMask", id = 8)
    private final byte[] zzgi;
    @Field(getter = "getManufacturerId", id = 9)
    private final int zzgj;
    @Nullable
    @Field(getter = "getManufacturerData", id = 10)
    private final byte[] zzgk;
    @Nullable
    @Field(getter = "getManufacturerDataMask", id = 11)
    private final byte[] zzgl;

    @Constructor
    zzgp(@Param(id = 1) int i, @Param(id = 4) ParcelUuid parcelUuid, @Param(id = 5) ParcelUuid parcelUuid2, @Param(id = 6) ParcelUuid parcelUuid3, @Param(id = 7) byte[] bArr, @Param(id = 8) byte[] bArr2, @Param(id = 9) int i2, @Param(id = 10) byte[] bArr3, @Param(id = 11) byte[] bArr4) {
        this.zzex = i;
        this.zzge = parcelUuid;
        this.zzgf = parcelUuid2;
        this.zzgg = parcelUuid3;
        this.zzgh = bArr;
        this.zzgi = bArr2;
        this.zzgj = i2;
        this.zzgk = bArr3;
        this.zzgl = bArr4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzgp zzgp = (zzgp) obj;
        return this.zzgj == zzgp.zzgj && Arrays.equals(this.zzgk, zzgp.zzgk) && Arrays.equals(this.zzgl, zzgp.zzgl) && Objects.equal(this.zzgg, zzgp.zzgg) && Arrays.equals(this.zzgh, zzgp.zzgh) && Arrays.equals(this.zzgi, zzgp.zzgi) && Objects.equal(this.zzge, zzgp.zzge) && Objects.equal(this.zzgf, zzgp.zzgf);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzgj), Integer.valueOf(Arrays.hashCode(this.zzgk)), Integer.valueOf(Arrays.hashCode(this.zzgl)), this.zzgg, Integer.valueOf(Arrays.hashCode(this.zzgh)), Integer.valueOf(Arrays.hashCode(this.zzgi)), this.zzge, this.zzgf});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzex);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzge, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzgf, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzgg, i, false);
        SafeParcelWriter.writeByteArray(parcel, 7, this.zzgh, false);
        SafeParcelWriter.writeByteArray(parcel, 8, this.zzgi, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzgj);
        SafeParcelWriter.writeByteArray(parcel, 10, this.zzgk, false);
        SafeParcelWriter.writeByteArray(parcel, 11, this.zzgl, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
