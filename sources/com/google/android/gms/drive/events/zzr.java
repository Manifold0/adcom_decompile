package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.drive.zzh;

@Class(creator = "TransferProgressEventCreator")
@Reserved({1})
public final class zzr extends AbstractSafeParcelable implements DriveEvent {
    public static final Creator<zzr> CREATOR = new zzs();
    @Field(id = 2)
    private final zzh zzcq;

    @Constructor
    public zzr(@Param(id = 2) zzh zzh) {
        this.zzcq = zzh;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return Objects.equal(this.zzcq, ((zzr) obj).zzcq);
    }

    public final int getType() {
        return 8;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzcq});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzcq, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzh zzab() {
        return this.zzcq;
    }
}
