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
import java.util.List;

@Class(creator = "OnResourceIdSetResponseCreator")
@Reserved({1})
public final class zzfx extends AbstractSafeParcelable {
    public static final Creator<zzfx> CREATOR = new zzfy();
    @Field(getter = "getResourceIds", id = 2)
    private final List<String> zzhw;

    @Constructor
    zzfx(@Param(id = 2) List<String> list) {
        this.zzhw = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 2, this.zzhw, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
