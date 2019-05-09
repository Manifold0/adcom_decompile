package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.zzu;

@Class(creator = "OnListParentsResponseCreator")
@Reserved({1})
public final class zzfp extends zzu {
    public static final Creator<zzfp> CREATOR = new zzfq();
    @Field(id = 2)
    final DataHolder zzht;

    @Constructor
    public zzfp(@Param(id = 2) DataHolder dataHolder) {
        this.zzht = dataHolder;
    }

    protected final void zza(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzht, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final DataHolder zzam() {
        return this.zzht;
    }
}
