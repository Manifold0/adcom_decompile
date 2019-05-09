package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "PartialDriveIdCreator")
@Reserved({1})
public final class zzq extends AbstractSafeParcelable {
    public static final Creator<zzq> CREATOR = new zzr();
    @Nullable
    @Field(id = 2)
    final String zzab;
    @Field(id = 3)
    final long zzac;
    @Field(defaultValueUnchecked = "com.google.android.gms.drive.DriveId.RESOURCE_TYPE_UNKNOWN", id = 4)
    final int zzad;

    @Constructor
    public zzq(@Param(id = 2) String str, @Param(id = 3) long j, @Param(id = 4) int i) {
        this.zzab = str;
        this.zzac = j;
        this.zzad = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzab, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzac);
        SafeParcelWriter.writeInt(parcel, 4, this.zzad);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
