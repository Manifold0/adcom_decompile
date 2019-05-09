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

@Class(creator = "SendPayloadParamsCreator")
@Reserved({1000})
public final class zzfu extends AbstractSafeParcelable {
    public static final Creator<zzfu> CREATOR = new zzfx();
    @Nullable
    @Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzdz zzar;
    @Nullable
    @Field(getter = "getPayload", id = 3)
    private zzfh zzdk;
    @Field(getter = "getRemoteEndpointIds", id = 2)
    private String[] zzee;
    @Field(getter = "getSendReliably", id = 4)
    private boolean zzef;

    private zzfu() {
    }

    @Constructor
    zzfu(@Nullable @Param(id = 1) IBinder iBinder, @Param(id = 2) String[] strArr, @Nullable @Param(id = 3) zzfh zzfh, @Param(id = 4) boolean z) {
        zzdz zzdz;
        if (iBinder == null) {
            zzdz = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IResultListener");
            zzdz = queryLocalInterface instanceof zzdz ? (zzdz) queryLocalInterface : new zzeb(iBinder);
        }
        this(zzdz, strArr, zzfh, z);
    }

    private zzfu(@Nullable zzdz zzdz, String[] strArr, @Nullable zzfh zzfh, boolean z) {
        this.zzar = zzdz;
        this.zzee = strArr;
        this.zzdk = zzfh;
        this.zzef = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfu)) {
            return false;
        }
        zzfu zzfu = (zzfu) obj;
        return Objects.equal(this.zzar, zzfu.zzar) && Arrays.equals(this.zzee, zzfu.zzee) && Objects.equal(this.zzdk, zzfu.zzdk) && Objects.equal(Boolean.valueOf(this.zzef), Boolean.valueOf(zzfu.zzef));
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzar, Integer.valueOf(Arrays.hashCode(this.zzee)), this.zzdk, Boolean.valueOf(this.zzef)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzar == null ? null : this.zzar.asBinder(), false);
        SafeParcelWriter.writeStringArray(parcel, 2, this.zzee, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdk, i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzef);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
