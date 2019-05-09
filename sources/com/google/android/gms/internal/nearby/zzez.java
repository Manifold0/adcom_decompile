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

@Class(creator = "OnStartAdvertisingResultParamsCreator")
@Reserved({1000})
public final class zzez extends AbstractSafeParcelable {
    public static final Creator<zzez> CREATOR = new zzfa();
    @Field(getter = "getStatusCode", id = 1)
    private int statusCode;
    @Field(getter = "getLocalEndpointName", id = 2)
    private String zzcc;

    private zzez() {
    }

    @Constructor
    zzez(@Param(id = 1) int i, @Param(id = 2) String str) {
        this.statusCode = i;
        this.zzcc = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzez)) {
            return false;
        }
        zzez zzez = (zzez) obj;
        return Objects.equal(Integer.valueOf(this.statusCode), Integer.valueOf(zzez.statusCode)) && Objects.equal(this.zzcc, zzez.zzcc);
    }

    public final String getLocalEndpointName() {
        return this.zzcc;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.statusCode), this.zzcc});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.statusCode);
        SafeParcelWriter.writeString(parcel, 2, this.zzcc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
