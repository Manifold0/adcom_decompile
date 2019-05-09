package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.Contents;

@Class(creator = "CloseContentsRequestCreator")
@Reserved({1})
public final class zzo extends AbstractSafeParcelable {
    public static final Creator<zzo> CREATOR = new zzp();
    @Nullable
    @Field(id = 2)
    private final Contents zzdd;
    @Field(id = 4)
    private final int zzdf;
    @Field(id = 3)
    private final Boolean zzdh;

    @VisibleForTesting
    public zzo(int i, boolean z) {
        this(null, Boolean.valueOf(false), i);
    }

    @Constructor
    zzo(@Param(id = 2) Contents contents, @Param(id = 3) Boolean bool, @Param(id = 4) int i) {
        this.zzdd = contents;
        this.zzdh = bool;
        this.zzdf = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdd, i, false);
        SafeParcelWriter.writeBooleanObject(parcel, 3, this.zzdh, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzdf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
