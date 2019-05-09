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

@Class(creator = "OpenContentsRequestCreator")
@Reserved({1})
public final class zzgd extends AbstractSafeParcelable {
    public static final Creator<zzgd> CREATOR = new zzge();
    @Field(id = 3)
    private final int mode;
    @Field(id = 2)
    private final DriveId zzdb;
    @Field(id = 4)
    private final int zzhz;

    @Constructor
    @VisibleForTesting
    public zzgd(@Param(id = 2) DriveId driveId, @Param(id = 3) int i, @Param(id = 4) int i2) {
        this.zzdb = driveId;
        this.mode = i;
        this.zzhz = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdb, i, false);
        SafeParcelWriter.writeInt(parcel, 3, this.mode);
        SafeParcelWriter.writeInt(parcel, 4, this.zzhz);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
