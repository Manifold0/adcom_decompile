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
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

@Class(creator = "UpdateMetadataRequestCreator")
@Reserved({1})
public final class zzgz extends AbstractSafeParcelable {
    public static final Creator<zzgz> CREATOR = new zzha();
    @Field(id = 2)
    private final DriveId zzdb;
    @Field(id = 3)
    private final MetadataBundle zzdc;

    @Constructor
    @VisibleForTesting
    public zzgz(@Param(id = 2) DriveId driveId, @Param(id = 3) MetadataBundle metadataBundle) {
        this.zzdb = driveId;
        this.zzdc = metadataBundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdb, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdc, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
