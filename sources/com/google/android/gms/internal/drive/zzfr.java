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

@Class(creator = "OnRealtimeLoadedResponseCreator")
@Reserved({1})
public final class zzfr extends AbstractSafeParcelable {
    public static final Creator<zzfr> CREATOR = new zzfw();
    @Field(id = 2)
    private final boolean zzhu;

    @Constructor
    public zzfr(@Param(id = 2) boolean z) {
        this.zzhu = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzhu);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
