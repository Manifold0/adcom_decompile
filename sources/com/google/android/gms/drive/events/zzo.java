package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.zzu;

@Class(creator = "QueryResultEventParcelableCreator")
@Reserved({1})
public final class zzo extends zzu implements DriveEvent {
    public static final Creator<zzo> CREATOR = new zzp();
    @Nullable
    @Field(id = 2)
    private final DataHolder zzat;
    @Field(id = 3)
    private final boolean zzco;
    @Field(id = 4)
    private final int zzcp;

    @Constructor
    public zzo(@Nullable @Param(id = 2) DataHolder dataHolder, @Param(id = 3) boolean z, @Param(id = 4) int i) {
        this.zzat = dataHolder;
        this.zzco = z;
        this.zzcp = i;
    }

    public final int getType() {
        return 3;
    }

    public final void zza(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzat, i, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzco);
        SafeParcelWriter.writeInt(parcel, 4, this.zzcp);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zzaa() {
        return this.zzcp;
    }

    @Nullable
    public final DataHolder zzy() {
        return this.zzat;
    }

    public final boolean zzz() {
        return this.zzco;
    }
}
