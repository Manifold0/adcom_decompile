package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "ConnectionInfoCreator")
public final class zzb extends AbstractSafeParcelable {
    public static final Creator<zzb> CREATOR = new zzc();
    @Field(id = 1)
    Bundle zzda;
    @Field(id = 2)
    Feature[] zzdb;

    @Constructor
    zzb(@Param(id = 1) Bundle bundle, @Param(id = 2) Feature[] featureArr) {
        this.zzda = bundle;
        this.zzdb = featureArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzda, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzdb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
