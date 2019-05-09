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

@Class(creator = "OnStoppedDiscoveryParamsCreator")
@Reserved({1000})
public final class zzfd extends AbstractSafeParcelable {
    public static final Creator<zzfd> CREATOR = new zzfe();
    @Field(getter = "getReason", id = 1)
    private int zzdn;

    private zzfd() {
    }

    @Constructor
    zzfd(@Param(id = 1) int i) {
        this.zzdn = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfd)) {
            return false;
        }
        return Objects.equal(Integer.valueOf(this.zzdn), Integer.valueOf(((zzfd) obj).zzdn));
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzdn)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzdn);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
