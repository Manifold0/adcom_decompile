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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;

@SafeParcelable$Class(creator = "HasFilterCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzn<T> extends zza
{
    public static final zzo CREATOR;
    @SafeParcelable$Field(id = 1)
    private final MetadataBundle zzlk;
    private final MetadataField<T> zzll;
    
    static {
        CREATOR = new zzo();
    }
    
    public zzn(final SearchableMetadataField<T> searchableMetadataField, final T t) {
        this(MetadataBundle.zza(searchableMetadataField, t));
    }
    
    @SafeParcelable$Constructor
    zzn(@SafeParcelable$Param(id = 1) final MetadataBundle zzlk) {
        this.zzlk = zzlk;
        this.zzll = (MetadataField<T>)zzi.zza(zzlk);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.zzlk, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final <F> F zza(final zzj<F> zzj) {
        return zzj.zzc(this.zzll, (T)this.zzlk.zza((MetadataField<T>)this.zzll));
    }
}
