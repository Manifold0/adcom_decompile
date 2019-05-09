package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.DriveSpace;
import java.util.List;

@Class(creator = "ChangesAvailableOptionsCreator")
@Reserved({1})
public final class zze extends AbstractSafeParcelable {
    public static final Creator<zze> CREATOR = new zzf();
    @Field(id = 2)
    private final int zzbu;
    @Field(id = 3)
    private final boolean zzbv;
    @Field(id = 4)
    private final List<DriveSpace> zzbw;

    @Constructor
    zze(@Param(id = 2) int i, @Param(id = 3) boolean z, @Param(id = 4) @NonNull List<DriveSpace> list) {
        this.zzbu = i;
        this.zzbv = z;
        this.zzbw = list;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        zze zze = (zze) obj;
        return Objects.equal(this.zzbw, zze.zzbw) && this.zzbu == zze.zzbu && this.zzbv == zze.zzbv;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzbw, Integer.valueOf(this.zzbu), Boolean.valueOf(this.zzbv)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzbu);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzbv);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzbw, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
