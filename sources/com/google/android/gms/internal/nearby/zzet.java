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

@Class(creator = "OnEndpointLostParamsCreator")
@Reserved({1000})
public final class zzet extends AbstractSafeParcelable {
    public static final Creator<zzet> CREATOR = new zzeu();
    @Field(getter = "getEndpointId", id = 1)
    private String zzca;

    private zzet() {
    }

    @Constructor
    zzet(@Param(id = 1) String str) {
        this.zzca = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzet)) {
            return false;
        }
        return Objects.equal(this.zzca, ((zzet) obj).zzca);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzca});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzca, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zze() {
        return this.zzca;
    }
}
