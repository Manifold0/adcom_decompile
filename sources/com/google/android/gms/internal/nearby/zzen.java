package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.Arrays;

@Class(creator = "OnConnectionResultParamsCreator")
public final class zzen extends AbstractSafeParcelable {
    public static final Creator<zzen> CREATOR = new zzeo();
    @Field(getter = "getStatusCode", id = 2)
    private int statusCode;
    @Field(getter = "getRemoteEndpointId", id = 1)
    private String zzat;
    @Nullable
    @Field(getter = "getHandshakeData", id = 3)
    private byte[] zzau;

    private zzen() {
    }

    @Constructor
    zzen(@Param(id = 1) String str, @Param(id = 2) int i, @Nullable @Param(id = 3) byte[] bArr) {
        this.zzat = str;
        this.statusCode = i;
        this.zzau = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzen)) {
            return false;
        }
        zzen zzen = (zzen) obj;
        return Objects.equal(this.zzat, zzen.zzat) && Objects.equal(Integer.valueOf(this.statusCode), Integer.valueOf(zzen.statusCode)) && Arrays.equals(this.zzau, zzen.zzau);
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzat, Integer.valueOf(this.statusCode), Integer.valueOf(Arrays.hashCode(this.zzau))});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzat, false);
        SafeParcelWriter.writeInt(parcel, 2, this.statusCode);
        SafeParcelWriter.writeByteArray(parcel, 3, this.zzau, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzg() {
        return this.zzat;
    }
}
