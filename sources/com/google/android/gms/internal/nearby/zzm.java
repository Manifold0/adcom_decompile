package com.google.android.gms.internal.nearby;

import android.os.IBinder;
import android.os.IInterface;
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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Arrays;

@Class(creator = "AcceptConnectionRequestParamsCreator")
@Reserved({1000})
public final class zzm extends AbstractSafeParcelable {
    public static final Creator<zzm> CREATOR = new zzp();
    @Nullable
    @Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzdz zzar;
    @Nullable
    @Field(getter = "getConnectionEventListenerAsBinder", id = 2, type = "android.os.IBinder")
    private zzdg zzas;
    @Field(getter = "getRemoteEndpointId", id = 3)
    private String zzat;
    @Nullable
    @Field(getter = "getHandshakeData", id = 4)
    private byte[] zzau;
    @Nullable
    @Field(getter = "getPayloadListenerAsBinder", id = 5, type = "android.os.IBinder")
    private zzdw zzav;

    private zzm() {
    }

    @Constructor
    zzm(@Nullable @Param(id = 1) IBinder iBinder, @Nullable @Param(id = 2) IBinder iBinder2, @Param(id = 3) String str, @Nullable @Param(id = 4) byte[] bArr, @Nullable @Param(id = 5) IBinder iBinder3) {
        zzdz zzdz;
        zzdg zzdg;
        zzdw zzdw = null;
        if (iBinder == null) {
            zzdz = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IResultListener");
            zzdz = queryLocalInterface instanceof zzdz ? (zzdz) queryLocalInterface : new zzeb(iBinder);
        }
        if (iBinder2 == null) {
            zzdg = null;
        } else {
            queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IConnectionEventListener");
            zzdg = queryLocalInterface instanceof zzdg ? (zzdg) queryLocalInterface : new zzdi(iBinder2);
        }
        if (iBinder3 != null) {
            queryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IPayloadListener");
            zzdw = queryLocalInterface instanceof zzdw ? (zzdw) queryLocalInterface : new zzdy(iBinder3);
        }
        this(zzdz, zzdg, str, bArr, zzdw);
    }

    private zzm(@Nullable zzdz zzdz, @Nullable zzdg zzdg, String str, @Nullable byte[] bArr, @Nullable zzdw zzdw) {
        this.zzar = zzdz;
        this.zzas = zzdg;
        this.zzat = str;
        this.zzau = bArr;
        this.zzav = zzdw;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzm)) {
            return false;
        }
        zzm zzm = (zzm) obj;
        return Objects.equal(this.zzar, zzm.zzar) && Objects.equal(this.zzas, zzm.zzas) && Objects.equal(this.zzat, zzm.zzat) && Arrays.equals(this.zzau, zzm.zzau) && Objects.equal(this.zzav, zzm.zzav);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzar, this.zzas, this.zzat, Integer.valueOf(Arrays.hashCode(this.zzau)), this.zzav});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder = null;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzar == null ? null : this.zzar.asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzas == null ? null : this.zzas.asBinder(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzat, false);
        SafeParcelWriter.writeByteArray(parcel, 4, this.zzau, false);
        if (this.zzav != null) {
            iBinder = this.zzav.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 5, iBinder, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
