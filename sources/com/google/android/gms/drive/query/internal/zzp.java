package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.metadata.zzb;
import java.util.Collection;
import java.util.Collections;

@Class(creator = "InFilterCreator")
@Reserved({1000})
public final class zzp<T> extends zza {
    public static final zzq CREATOR = new zzq();
    @Field(id = 1)
    private final MetadataBundle zzlk;
    private final zzb<T> zzlx;

    public zzp(SearchableCollectionMetadataField<T> searchableCollectionMetadataField, T t) {
        this(MetadataBundle.zza(searchableCollectionMetadataField, Collections.singleton(t)));
    }

    @Constructor
    zzp(@Param(id = 1) MetadataBundle metadataBundle) {
        this.zzlk = metadataBundle;
        this.zzlx = (zzb) zzi.zza(metadataBundle);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzlk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final <F> F zza(zzj<F> zzj) {
        return zzj.zza(this.zzlx, ((Collection) this.zzlk.zza(this.zzlx)).iterator().next());
    }
}
