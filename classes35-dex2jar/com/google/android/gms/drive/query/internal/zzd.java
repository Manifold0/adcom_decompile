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
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;

@SafeParcelable$Class(creator = "FieldOnlyFilterCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzd extends zza
{
    public static final Parcelable$Creator<zzd> CREATOR;
    @SafeParcelable$Field(id = 1)
    private final MetadataBundle zzlk;
    private final MetadataField<?> zzll;
    
    static {
        CREATOR = (Parcelable$Creator)new zze();
    }
    
    public zzd(final SearchableMetadataField<?> searchableMetadataField) {
        this(MetadataBundle.zza(searchableMetadataField, null));
    }
    
    @SafeParcelable$Constructor
    zzd(@SafeParcelable$Param(id = 1) final MetadataBundle zzlk) {
        this.zzlk = zzlk;
        this.zzll = zzi.zza(zzlk);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.zzlk, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final <T> T zza(final zzj<T> zzj) {
        return zzj.zze(this.zzll);
    }
}
