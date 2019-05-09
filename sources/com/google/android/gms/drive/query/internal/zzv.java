package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.query.Filter;

@Class(creator = "NotFilterCreator")
@Reserved({1000})
public final class zzv extends zza {
    public static final Creator<zzv> CREATOR = new zzw();
    @Field(id = 1)
    private final FilterHolder zzlz;

    public zzv(Filter filter) {
        this(new FilterHolder(filter));
    }

    @Constructor
    zzv(@Param(id = 1) FilterHolder filterHolder) {
        this.zzlz = filterHolder;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzlz, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final <T> T zza(zzj<T> zzj) {
        return zzj.zza(this.zzlz.getFilter().zza(zzj));
    }
}
