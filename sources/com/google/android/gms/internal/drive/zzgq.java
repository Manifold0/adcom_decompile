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
import java.util.List;

@Class(creator = "SetResourceParentsRequestCreator")
@Reserved({1})
public final class zzgq extends AbstractSafeParcelable {
    public static final Creator<zzgq> CREATOR = new zzgr();
    @Field(id = 2)
    private final DriveId zzic;
    @Field(id = 3)
    private final List<DriveId> zzid;

    @Constructor
    @VisibleForTesting
    public zzgq(@Param(id = 2) DriveId driveId, @Param(id = 3) List<DriveId> list) {
        this.zzic = driveId;
        this.zzid = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzic, i, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzid, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
