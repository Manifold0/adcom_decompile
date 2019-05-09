package com.google.android.gms.internal.drive;

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
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.zzt;

@Class(creator = "RemoveEventListenerRequestCreator")
@Reserved({1})
public final class zzgm extends AbstractSafeParcelable {
    public static final Creator<zzgm> CREATOR = new zzgn();
    @Field(id = 3)
    private final int zzcy;
    @Nullable
    @Field(id = 4)
    private final zzt zzda;
    @Field(id = 2)
    private final DriveId zzk;

    @VisibleForTesting
    public zzgm(@Nullable DriveId driveId, int i) {
        this(driveId, i, null);
    }

    @Constructor
    zzgm(@Param(id = 2) DriveId driveId, @Param(id = 3) int i, @Param(id = 4) zzt zzt) {
        this.zzk = driveId;
        this.zzcy = i;
        this.zzda = zzt;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzk, i, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzcy);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzda, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
