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

@Class(creator = "DisconnectFromEndpointParamsCreator")
@Reserved({1000})
public final class zzcz extends AbstractSafeParcelable {
    public static final Creator<zzcz> CREATOR = new zzdc();
    @Field(getter = "getRemoteEndpointId", id = 1)
    private String zzat;

    private zzcz() {
    }

    @Constructor
    zzcz(@Param(id = 1) String str) {
        this.zzat = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcz)) {
            return false;
        }
        return Objects.equal(this.zzat, ((zzcz) obj).zzat);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzat});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzat, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
