package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "StopAdvertisingParamsCreator")
@Reserved({1000})
public final class zzgg extends AbstractSafeParcelable {
    public static final Creator<zzgg> CREATOR = new zzgi();

    @Constructor
    zzgg() {
    }

    public final boolean equals(Object obj) {
        return this == obj || (obj instanceof zzgg);
    }

    public final int hashCode() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        SafeParcelWriter.finishObjectHeader(parcel, SafeParcelWriter.beginObjectHeader(parcel));
    }
}
