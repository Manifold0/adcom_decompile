package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

@Class(creator = "ComparisonFilterCreator")
@Reserved({1000})
public final class zzb<T> extends zza {
    public static final zzc CREATOR = new zzc();
    @Field(id = 1)
    private final zzx zzlj;
    @Field(id = 2)
    private final MetadataBundle zzlk;
    private final MetadataField<T> zzll;

    public zzb(zzx zzx, SearchableMetadataField<T> searchableMetadataField, T t) {
        this(zzx, MetadataBundle.zza(searchableMetadataField, t));
    }

    @Constructor
    zzb(@Param(id = 1) zzx zzx, @Param(id = 2) MetadataBundle metadataBundle) {
        this.zzlj = zzx;
        this.zzlk = metadataBundle;
        this.zzll = zzi.zza(metadataBundle);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzlj, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzlk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final <F> F zza(zzj<F> zzj) {
        return zzj.zza(this.zzlj, this.zzll, this.zzlk.zza(this.zzll));
    }
}
