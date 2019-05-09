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

@Class(creator = "OnBandwidthChangedParamsCreator")
public final class zzef extends AbstractSafeParcelable {
    public static final Creator<zzef> CREATOR = new zzeg();
    @Field(getter = "getQuality", id = 2)
    private int quality;
    @Field(getter = "getRemoteEndpointId", id = 1)
    private String zzat;

    private zzef() {
    }

    @Constructor
    zzef(@Param(id = 1) String str, @Param(id = 2) int i) {
        this.zzat = str;
        this.quality = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzef)) {
            return false;
        }
        zzef zzef = (zzef) obj;
        return Objects.equal(this.zzat, zzef.zzat) && Objects.equal(Integer.valueOf(this.quality), Integer.valueOf(zzef.quality));
    }

    public final int getQuality() {
        return this.quality;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzat, Integer.valueOf(this.quality)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzat, false);
        SafeParcelWriter.writeInt(parcel, 2, this.quality);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzg() {
        return this.zzat;
    }
}
