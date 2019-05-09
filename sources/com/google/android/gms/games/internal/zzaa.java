package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "PopupLocationInfoParcelableCreator")
@Reserved({1000})
public final class zzaa extends zzd {
    public static final Creator<zzaa> CREATOR = new zzab();
    @Field(getter = "getInfoBundle", id = 1)
    private final Bundle zzja;
    @Field(getter = "getWindowToken", id = 2)
    private final IBinder zzjb;

    @Constructor
    zzaa(@Param(id = 1) Bundle bundle, @Param(id = 2) IBinder iBinder) {
        this.zzja = bundle;
        this.zzjb = iBinder;
    }

    public zzaa(zzae zzae) {
        this.zzja = zzae.zzbk();
        this.zzjb = zzae.zzjb;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzja, false);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzjb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
