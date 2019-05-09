package com.google.android.gms.internal.drive;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "DriveServiceResponseCreator")
@Reserved({1})
public final class zzec extends AbstractSafeParcelable {
    public static final Creator<zzec> CREATOR = new zzed();
    @Field(id = 2)
    final IBinder zzgq;

    @Constructor
    zzec(@Param(id = 2) IBinder iBinder) {
        this.zzgq = iBinder;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzgq, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
