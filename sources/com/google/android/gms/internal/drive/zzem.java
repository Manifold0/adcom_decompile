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
import com.google.android.gms.drive.zzr;
import java.util.List;

@Class(creator = "GetPermissionsResponseCreator")
@Reserved({1})
public final class zzem extends AbstractSafeParcelable {
    public static final Creator<zzem> CREATOR = new zzen();
    @Field(id = 3)
    private final int responseCode;
    @Field(id = 2)
    private final List<zzr> zzgz;

    @Constructor
    public zzem(@Param(id = 2) List<zzr> list, @Param(id = 3) int i) {
        this.zzgz = list;
        this.responseCode = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzgz, false);
        SafeParcelWriter.writeInt(parcel, 3, this.responseCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
