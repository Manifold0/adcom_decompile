// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnMetadataResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzfs extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfs> CREATOR;
    @SafeParcelable$Field(id = 2)
    final MetadataBundle zzdl;
    
    static {
        CREATOR = (Parcelable$Creator)new zzft();
    }
    
    @SafeParcelable$Constructor
    public zzfs(@SafeParcelable$Param(id = 2) final MetadataBundle zzdl) {
        this.zzdl = zzdl;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzdl, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final MetadataBundle zzan() {
        return this.zzdl;
    }
}
