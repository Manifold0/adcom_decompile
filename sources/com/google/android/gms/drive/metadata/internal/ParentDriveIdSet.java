package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.ArrayList;
import java.util.List;

@Class(creator = "ParentDriveIdSetCreator")
@Reserved({1})
public class ParentDriveIdSet extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<ParentDriveIdSet> CREATOR = new zzn();
    @Field(id = 2)
    final List<zzq> zzit;

    public ParentDriveIdSet() {
        this(new ArrayList());
    }

    @Constructor
    ParentDriveIdSet(@Param(id = 2) List<zzq> list) {
        this.zzit = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzit, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
