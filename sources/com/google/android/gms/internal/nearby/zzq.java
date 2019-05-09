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

@Class(creator = "CancelPayloadParamsCreator")
public final class zzq extends AbstractSafeParcelable {
    public static final Creator<zzq> CREATOR = new zzt();
    @Field(getter = "getPayloadId", id = 2)
    private long zzaf;
    @Nullable
    @Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzdz zzar;

    private zzq() {
    }

    @Constructor
    zzq(@Nullable @Param(id = 1) IBinder iBinder, @Param(id = 2) long j) {
        zzdz zzdz;
        if (iBinder == null) {
            zzdz = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IResultListener");
            zzdz = queryLocalInterface instanceof zzdz ? (zzdz) queryLocalInterface : new zzeb(iBinder);
        }
        this(zzdz, j);
    }

    private zzq(@Nullable zzdz zzdz, long j) {
        this.zzar = zzdz;
        this.zzaf = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzq)) {
            return false;
        }
        zzq zzq = (zzq) obj;
        return Objects.equal(this.zzar, zzq.zzar) && Objects.equal(Long.valueOf(this.zzaf), Long.valueOf(zzq.zzaf));
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzar, Long.valueOf(this.zzaf)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzar == null ? null : this.zzar.asBinder(), false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzaf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
