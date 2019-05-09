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

@Class(creator = "SendConnectionRequestParamsCreator")
@Reserved({1000})
public final class zzfq extends AbstractSafeParcelable {
    public static final Creator<zzfq> CREATOR = new zzft();
    @Nullable
    @Field(getter = "getName", id = 4)
    private String name;
    @Nullable
    @Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzdz zzar;
    @Nullable
    @Field(getter = "getConnectionEventListenerAsBinder", id = 2, type = "android.os.IBinder")
    private zzdg zzas;
    @Field(getter = "getRemoteEndpointId", id = 5)
    private String zzat;
    @Nullable
    @Field(getter = "getHandshakeData", id = 6)
    private byte[] zzau;
    @Nullable
    @Field(getter = "getConnectionResponseListenerAsBinder", id = 3, type = "android.os.IBinder")
    private zzdm zzeb;
    @Nullable
    @Field(getter = "getConnectionLifecycleListenerAsBinder", id = 7, type = "android.os.IBinder")
    private zzdj zzec;

    private zzfq() {
    }

    @Constructor
    zzfq(@Nullable @Param(id = 1) IBinder iBinder, @Nullable @Param(id = 2) IBinder iBinder2, @Nullable @Param(id = 3) IBinder iBinder3, @Nullable @Param(id = 4) String str, @Param(id = 5) String str2, @Nullable @Param(id = 6) byte[] bArr, @Nullable @Param(id = 7) IBinder iBinder4) {
        zzdz zzdz;
        zzdg zzdg;
        zzdm zzdm;
        zzdj zzdj = null;
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
        if (iBinder3 == null) {
            zzdm = null;
        } else {
            queryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IConnectionResponseListener");
            zzdm = queryLocalInterface instanceof zzdm ? (zzdm) queryLocalInterface : new zzdo(iBinder3);
        }
        if (iBinder4 != null) {
            queryLocalInterface = iBinder4.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener");
            zzdj = queryLocalInterface instanceof zzdj ? (zzdj) queryLocalInterface : new zzdl(iBinder4);
        }
        this(zzdz, zzdg, zzdm, str, str2, bArr, zzdj);
    }

    private zzfq(@Nullable zzdz zzdz, @Nullable zzdg zzdg, @Nullable zzdm zzdm, @Nullable String str, String str2, @Nullable byte[] bArr, @Nullable zzdj zzdj) {
        this.zzar = zzdz;
        this.zzas = zzdg;
        this.zzeb = zzdm;
        this.name = str;
        this.zzat = str2;
        this.zzau = bArr;
        this.zzec = zzdj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfq)) {
            return false;
        }
        zzfq zzfq = (zzfq) obj;
        return Objects.equal(this.zzar, zzfq.zzar) && Objects.equal(this.zzas, zzfq.zzas) && Objects.equal(this.zzeb, zzfq.zzeb) && Objects.equal(this.name, zzfq.name) && Objects.equal(this.zzat, zzfq.zzat) && Arrays.equals(this.zzau, zzfq.zzau) && Objects.equal(this.zzec, zzfq.zzec);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzar, this.zzas, this.zzeb, this.name, this.zzat, Integer.valueOf(Arrays.hashCode(this.zzau)), this.zzec});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder = null;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzar == null ? null : this.zzar.asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzas == null ? null : this.zzas.asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzeb == null ? null : this.zzeb.asBinder(), false);
        SafeParcelWriter.writeString(parcel, 4, this.name, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzat, false);
        SafeParcelWriter.writeByteArray(parcel, 6, this.zzau, false);
        if (this.zzec != null) {
            iBinder = this.zzec.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 7, iBinder, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
