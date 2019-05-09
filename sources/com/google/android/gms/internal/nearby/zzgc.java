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
import com.google.android.gms.nearby.connection.DiscoveryOptions;

@Class(creator = "StartDiscoveryParamsCreator")
@Reserved({1000})
public final class zzgc extends AbstractSafeParcelable {
    public static final Creator<zzgc> CREATOR = new zzgf();
    @Field(getter = "getDurationMillis", id = 4)
    private long durationMillis;
    @Nullable
    @Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzdz zzar;
    @Nullable
    @Field(getter = "getCallbackAsBinder", id = 2, type = "android.os.IBinder")
    private zzdp zzel;
    @Field(getter = "getOptions", id = 5)
    private DiscoveryOptions zzem;
    @Nullable
    @Field(getter = "getDiscoveryListenerAsBinder", id = 6, type = "android.os.IBinder")
    private zzdr zzen;
    @Field(getter = "getServiceId", id = 3)
    private String zzu;

    private zzgc() {
    }

    @Constructor
    zzgc(@Nullable @Param(id = 1) IBinder iBinder, @Nullable @Param(id = 2) IBinder iBinder2, @Param(id = 3) String str, @Param(id = 4) long j, @Param(id = 5) DiscoveryOptions discoveryOptions, @Nullable @Param(id = 6) IBinder iBinder3) {
        zzdz zzdz;
        zzdp zzdp;
        zzdr zzdr = null;
        if (iBinder == null) {
            zzdz = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IResultListener");
            zzdz = queryLocalInterface instanceof zzdz ? (zzdz) queryLocalInterface : new zzeb(iBinder);
        }
        if (iBinder2 == null) {
            zzdp = null;
        } else {
            queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IDiscoveryCallback");
            zzdp = queryLocalInterface instanceof zzdp ? (zzdp) queryLocalInterface : new zzdq(iBinder2);
        }
        if (iBinder3 != null) {
            queryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IDiscoveryListener");
            zzdr = queryLocalInterface instanceof zzdr ? (zzdr) queryLocalInterface : new zzdt(iBinder3);
        }
        this(zzdz, zzdp, str, j, discoveryOptions, zzdr);
    }

    private zzgc(@Nullable zzdz zzdz, @Nullable zzdp zzdp, String str, long j, DiscoveryOptions discoveryOptions, @Nullable zzdr zzdr) {
        this.zzar = zzdz;
        this.zzel = zzdp;
        this.zzu = str;
        this.durationMillis = j;
        this.zzem = discoveryOptions;
        this.zzen = zzdr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgc)) {
            return false;
        }
        zzgc zzgc = (zzgc) obj;
        return Objects.equal(this.zzar, zzgc.zzar) && Objects.equal(this.zzel, zzgc.zzel) && Objects.equal(this.zzu, zzgc.zzu) && Objects.equal(Long.valueOf(this.durationMillis), Long.valueOf(zzgc.durationMillis)) && Objects.equal(this.zzem, zzgc.zzem) && Objects.equal(this.zzen, zzgc.zzen);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzar, this.zzel, this.zzu, Long.valueOf(this.durationMillis), this.zzem, this.zzen});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder = null;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzar == null ? null : this.zzar.asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzel == null ? null : this.zzel.asBinder(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzu, false);
        SafeParcelWriter.writeLong(parcel, 4, this.durationMillis);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzem, i, false);
        if (this.zzen != null) {
            iBinder = this.zzen.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 6, iBinder, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
