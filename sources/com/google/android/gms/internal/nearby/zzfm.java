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

@Class(creator = "RejectConnectionRequestParamsCreator")
@Reserved({1000})
public final class zzfm extends AbstractSafeParcelable {
    public static final Creator<zzfm> CREATOR = new zzfp();
    @Nullable
    @Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzdz zzar;
    @Field(getter = "getRemoteEndpointId", id = 2)
    private String zzat;

    private zzfm() {
    }

    @Constructor
    zzfm(@Nullable @Param(id = 1) IBinder iBinder, @Param(id = 2) String str) {
        zzdz zzdz;
        if (iBinder == null) {
            zzdz = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IResultListener");
            zzdz = queryLocalInterface instanceof zzdz ? (zzdz) queryLocalInterface : new zzeb(iBinder);
        }
        this(zzdz, str);
    }

    private zzfm(@Nullable zzdz zzdz, String str) {
        this.zzar = zzdz;
        this.zzat = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfm)) {
            return false;
        }
        zzfm zzfm = (zzfm) obj;
        return Objects.equal(this.zzar, zzfm.zzar) && Objects.equal(this.zzat, zzfm.zzat);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzar, this.zzat});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzar == null ? null : this.zzar.asBinder(), false);
        SafeParcelWriter.writeString(parcel, 2, this.zzat, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
