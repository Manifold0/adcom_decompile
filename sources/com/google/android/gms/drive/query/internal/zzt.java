package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "MatchAllFilterCreator")
@Reserved({1000})
public final class zzt extends zza {
    public static final Creator<zzt> CREATOR = new zzu();

    public final void writeToParcel(Parcel parcel, int i) {
        SafeParcelWriter.finishObjectHeader(parcel, SafeParcelWriter.beginObjectHeader(parcel));
    }

    public final <F> F zza(zzj<F> zzj) {
        return zzj.zzbc();
    }
}
