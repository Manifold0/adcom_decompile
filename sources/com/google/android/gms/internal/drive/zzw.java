package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

@Class(creator = "CreateFileRequestCreator")
@Reserved({1, 10})
public final class zzw extends AbstractSafeParcelable {
    public static final Creator<zzw> CREATOR = new zzx();
    @Field(id = 7)
    private final String zzal;
    @Field(id = 4)
    private final Contents zzdd;
    @Field(id = 3)
    private final MetadataBundle zzdl;
    @Field(id = 5)
    private final Integer zzdm;
    @Field(id = 2)
    private final DriveId zzdn;
    @Field(id = 6)
    private final boolean zzdo;
    @Field(id = 8)
    private final int zzdp;
    @Field(id = 9)
    private final int zzdq;

    @VisibleForTesting
    public zzw(DriveId driveId, MetadataBundle metadataBundle, int i, int i2, ExecutionOptions executionOptions) {
        this(driveId, metadataBundle, null, i2, executionOptions.zzl(), executionOptions.zzk(), executionOptions.zzm(), i);
    }

    @Constructor
    zzw(@Param(id = 2) DriveId driveId, @Param(id = 3) MetadataBundle metadataBundle, @Param(id = 4) Contents contents, @Param(id = 5) int i, @Param(id = 6) boolean z, @Param(id = 7) String str, @Param(id = 8) int i2, @Param(id = 9) int i3) {
        if (!(contents == null || i3 == 0)) {
            Preconditions.checkArgument(contents.getRequestId() == i3, "inconsistent contents reference");
        }
        if (i == 0 && contents == null && i3 == 0) {
            throw new IllegalArgumentException("Need a valid contents");
        }
        this.zzdn = (DriveId) Preconditions.checkNotNull(driveId);
        this.zzdl = (MetadataBundle) Preconditions.checkNotNull(metadataBundle);
        this.zzdd = contents;
        this.zzdm = Integer.valueOf(i);
        this.zzal = str;
        this.zzdp = i2;
        this.zzdo = z;
        this.zzdq = i3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdn, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdl, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzdd, i, false);
        SafeParcelWriter.writeIntegerObject(parcel, 5, this.zzdm, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzdo);
        SafeParcelWriter.writeString(parcel, 7, this.zzal, false);
        SafeParcelWriter.writeInt(parcel, 8, this.zzdp);
        SafeParcelWriter.writeInt(parcel, 9, this.zzdq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
