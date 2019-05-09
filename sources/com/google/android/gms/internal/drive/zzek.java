package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;

@Class(creator = "GetMetadataRequestCreator")
@Reserved({1})
public final class zzek extends AbstractSafeParcelable {
    public static final Creator<zzek> CREATOR = new zzel();
    @Field(id = 2)
    private final DriveId zzdb;
    @Field(id = 3)
    private final boolean zzgy;

    @Constructor
    @VisibleForTesting
    public zzek(@Param(id = 2) DriveId driveId, @Param(id = 3) boolean z) {
        this.zzdb = driveId;
        this.zzgy = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdb, i, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzgy);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
