// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "UpdateMetadataRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzgz extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzgz> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final DriveId zzdb;
    @SafeParcelable$Field(id = 3)
    private final MetadataBundle zzdc;
    
    static {
        CREATOR = (Parcelable$Creator)new zzha();
    }
    
    @SafeParcelable$Constructor
    @VisibleForTesting
    public zzgz(@SafeParcelable$Param(id = 2) final DriveId zzdb, @SafeParcelable$Param(id = 3) final MetadataBundle zzdc) {
        this.zzdb = zzdb;
        this.zzdc = zzdc;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzdb, n, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzdc, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
