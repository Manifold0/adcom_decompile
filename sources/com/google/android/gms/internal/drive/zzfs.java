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
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

@Class(creator = "OnMetadataResponseCreator")
@Reserved({1})
public final class zzfs extends AbstractSafeParcelable {
    public static final Creator<zzfs> CREATOR = new zzft();
    @Field(id = 2)
    final MetadataBundle zzdl;

    @Constructor
    public zzfs(@Param(id = 2) MetadataBundle metadataBundle) {
        this.zzdl = metadataBundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdl, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final MetadataBundle zzan() {
        return this.zzdl;
    }
}
