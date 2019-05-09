package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.zze;
import com.google.android.gms.drive.events.zzt;
import com.google.android.gms.drive.events.zzx;

@Class(creator = "AddEventListenerRequestCreator")
@Reserved({1})
public final class zzj extends AbstractSafeParcelable {
    public static final Creator<zzj> CREATOR = new zzk();
    @Nullable
    @Field(id = 4)
    private final zze zzbt;
    @Field(id = 3)
    final int zzcy;
    @Nullable
    @Field(id = 5)
    private final zzx zzcz;
    @Nullable
    @Field(id = 6)
    private final zzt zzda;
    @Nullable
    @Field(id = 2)
    final DriveId zzk;

    public zzj(int i, DriveId driveId) {
        this((DriveId) Preconditions.checkNotNull(driveId), 1, null, null, null);
    }

    @Constructor
    zzj(@Param(id = 2) DriveId driveId, @Param(id = 3) int i, @Param(id = 4) zze zze, @Param(id = 5) zzx zzx, @Param(id = 6) zzt zzt) {
        this.zzk = driveId;
        this.zzcy = i;
        this.zzbt = zze;
        this.zzcz = zzx;
        this.zzda = zzt;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzk, i, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzcy);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbt, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzcz, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzda, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
