package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.DriveId;
import java.util.Locale;

@Class(creator = "ChangeEventCreator")
@Reserved({1})
public final class ChangeEvent extends AbstractSafeParcelable implements ResourceEvent {
    public static final Creator<ChangeEvent> CREATOR = new zza();
    @Field(id = 3)
    private final int zzbs;
    @Field(id = 2)
    private final DriveId zzk;

    @Constructor
    public ChangeEvent(@Param(id = 2) DriveId driveId, @Param(id = 3) int i) {
        this.zzk = driveId;
        this.zzbs = i;
    }

    public final DriveId getDriveId() {
        return this.zzk;
    }

    public final int getType() {
        return 1;
    }

    public final boolean hasBeenDeleted() {
        return (this.zzbs & 4) != 0;
    }

    public final boolean hasContentChanged() {
        return (this.zzbs & 2) != 0;
    }

    public final boolean hasMetadataChanged() {
        return (this.zzbs & 1) != 0;
    }

    public final String toString() {
        return String.format(Locale.US, "ChangeEvent [id=%s,changeFlags=%x]", new Object[]{this.zzk, Integer.valueOf(this.zzbs)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzk, i, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbs);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
