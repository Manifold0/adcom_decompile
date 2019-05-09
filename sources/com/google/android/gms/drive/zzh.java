package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "DriveFileRangeCreator")
@Reserved({1})
public final class zzh extends AbstractSafeParcelable {
    public static final Creator<zzh> CREATOR = new zzi();
    @Field(id = 3)
    private final long zzaa;
    @Field(id = 2)
    private final long zzz;

    @Constructor
    public zzh(@Param(id = 2) long j, @Param(id = 3) long j2) {
        this.zzz = j;
        this.zzaa = j2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, this.zzz);
        SafeParcelWriter.writeLong(parcel, 3, this.zzaa);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
