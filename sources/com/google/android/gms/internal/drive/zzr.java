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
import com.google.android.gms.drive.DriveFile;

@Class(creator = "CreateContentsRequestCreator")
@Reserved({1})
public final class zzr extends AbstractSafeParcelable {
    public static final Creator<zzr> CREATOR = new zzs();
    @Field(defaultValueUnchecked = "com.google.android.gms.drive.DriveFile.MODE_WRITE_ONLY", id = 2)
    private final int mode;

    @Constructor
    public zzr(@Param(id = 2) int i) {
        boolean z = i == DriveFile.MODE_WRITE_ONLY || i == DriveFile.MODE_READ_WRITE;
        Preconditions.checkArgument(z, "Cannot create a new read-only contents!");
        this.mode = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.mode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
