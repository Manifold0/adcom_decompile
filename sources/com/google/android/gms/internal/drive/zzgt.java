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

@Class(creator = "StringListResponseCreator")
@Reserved({1})
public final class zzgt extends AbstractSafeParcelable {
    public static final Creator<zzgt> CREATOR = new zzgu();
    @Field(getter = "getList", id = 2)
    private final List<String> zzie;

    @Constructor
    public zzgt(@Param(id = 2) List<String> list) {
        this.zzie = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 2, this.zzie, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
