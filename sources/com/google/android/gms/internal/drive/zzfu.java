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
import com.google.android.gms.drive.TransferPreferences;

@Class(creator = "OnPinnedDownloadPreferencesResponseCreator")
@Reserved({1})
public final class zzfu extends AbstractSafeParcelable {
    public static final Creator<zzfu> CREATOR = new zzfv();
    @Field(id = 2)
    private final zzgi zzhv;

    @Constructor
    zzfu(@Param(id = 2) zzgi zzgi) {
        this.zzhv = zzgi;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzhv, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final TransferPreferences zzao() {
        return this.zzhv;
    }
}
