package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.drive.zzh;
import java.util.List;

@Class(creator = "TransferStateEventCreator")
@Reserved({1, 2})
public final class zzv extends AbstractSafeParcelable implements DriveEvent {
    public static final Creator<zzv> CREATOR = new zzw();
    @Field(id = 3)
    private final List<zzh> zzcs;

    @Constructor
    public zzv(@Param(id = 3) List<zzh> list) {
        this.zzcs = list;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return Objects.equal(this.zzcs, ((zzv) obj).zzcs);
    }

    public final int getType() {
        return 7;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzcs});
    }

    public final String toString() {
        return String.format("TransferStateEvent[%s]", new Object[]{TextUtils.join("','", this.zzcs)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzcs, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
