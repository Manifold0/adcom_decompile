package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "OnPayloadReceivedParamsCreator")
@Reserved({1000})
public final class zzev extends AbstractSafeParcelable {
    public static final Creator<zzev> CREATOR = new zzew();
    @Field(getter = "getRemoteEndpointId", id = 1)
    private String zzat;
    @Field(getter = "getPayload", id = 2)
    private zzfh zzdk;
    @Field(getter = "getIsReliable", id = 3)
    private boolean zzdl;

    private zzev() {
    }

    @Constructor
    zzev(@Param(id = 1) String str, @Param(id = 2) zzfh zzfh, @Param(id = 3) boolean z) {
        this.zzat = str;
        this.zzdk = zzfh;
        this.zzdl = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzev)) {
            return false;
        }
        zzev zzev = (zzev) obj;
        return Objects.equal(this.zzat, zzev.zzat) && Objects.equal(this.zzdk, zzev.zzdk) && Objects.equal(Boolean.valueOf(this.zzdl), Boolean.valueOf(zzev.zzdl));
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzat, this.zzdk, Boolean.valueOf(this.zzdl)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzat, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdk, i, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzdl);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzg() {
        return this.zzat;
    }

    public final zzfh zzl() {
        return this.zzdk;
    }

    public final boolean zzm() {
        return this.zzdl;
    }
}
