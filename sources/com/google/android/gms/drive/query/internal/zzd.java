package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

@Class(creator = "FieldOnlyFilterCreator")
@Reserved({1000})
public final class zzd extends zza {
    public static final Creator<zzd> CREATOR = new zze();
    @Field(id = 1)
    private final MetadataBundle zzlk;
    private final MetadataField<?> zzll;

    public zzd(SearchableMetadataField<?> searchableMetadataField) {
        this(MetadataBundle.zza(searchableMetadataField, null));
    }

    @Constructor
    zzd(@Param(id = 1) MetadataBundle metadataBundle) {
        this.zzlk = metadataBundle;
        this.zzll = zzi.zza(metadataBundle);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzlk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final <T> T zza(zzj<T> zzj) {
        return zzj.zze(this.zzll);
    }
}
