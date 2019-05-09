package com.google.android.gms.internal.drive;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "OnStartStreamSessionCreator")
@Reserved({1})
public final class zzfz extends AbstractSafeParcelable {
    public static final Creator<zzfz> CREATOR = new zzga();
    @Field(id = 2)
    private final ParcelFileDescriptor zzhx;
    @Field(id = 3)
    private final IBinder zzhy;
    @Field(id = 4)
    private final String zzm;

    @Constructor
    zzfz(@Param(id = 2) ParcelFileDescriptor parcelFileDescriptor, @Param(id = 3) IBinder iBinder, @Param(id = 4) String str) {
        this.zzhx = parcelFileDescriptor;
        this.zzhy = iBinder;
        this.zzm = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i | 1;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzhx, i2, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzhy, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
