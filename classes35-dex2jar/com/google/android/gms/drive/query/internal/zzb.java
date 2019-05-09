// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;

@SafeParcelable$Class(creator = "ComparisonFilterCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzb<T> extends zza
{
    public static final zzc CREATOR;
    @SafeParcelable$Field(id = 1)
    private final zzx zzlj;
    @SafeParcelable$Field(id = 2)
    private final MetadataBundle zzlk;
    private final MetadataField<T> zzll;
    
    static {
        CREATOR = new zzc();
    }
    
    public zzb(final zzx zzx, final SearchableMetadataField<T> searchableMetadataField, final T t) {
        this(zzx, MetadataBundle.zza(searchableMetadataField, t));
    }
    
    @SafeParcelable$Constructor
    zzb(@SafeParcelable$Param(id = 1) final zzx zzlj, @SafeParcelable$Param(id = 2) final MetadataBundle zzlk) {
        this.zzlj = zzlj;
        this.zzlk = zzlk;
        this.zzll = (MetadataField<T>)zzi.zza(zzlk);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.zzlj, n, false);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzlk, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final <F> F zza(final zzj<F> zzj) {
        return zzj.zza(this.zzlj, this.zzll, (T)this.zzlk.zza((MetadataField<T>)this.zzll));
    }
}
