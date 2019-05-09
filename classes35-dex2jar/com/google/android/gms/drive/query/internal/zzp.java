// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query.internal;

import java.util.Collection;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.Set;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.Collections;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.zzb;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;

@SafeParcelable$Class(creator = "InFilterCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzp<T> extends zza
{
    public static final zzq CREATOR;
    @SafeParcelable$Field(id = 1)
    private final MetadataBundle zzlk;
    private final zzb<T> zzlx;
    
    static {
        CREATOR = new zzq();
    }
    
    public zzp(final SearchableCollectionMetadataField<T> searchableCollectionMetadataField, final T t) {
        this(MetadataBundle.zza(searchableCollectionMetadataField, Collections.singleton(t)));
    }
    
    @SafeParcelable$Constructor
    zzp(@SafeParcelable$Param(id = 1) final MetadataBundle zzlk) {
        this.zzlk = zzlk;
        this.zzlx = (zzb<T>)(zzb)zzi.zza(zzlk);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.zzlk, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final <F> F zza(final zzj<F> zzj) {
        return zzj.zza(this.zzlx, (T)this.zzlk.zza(this.zzlx).iterator().next());
    }
}
