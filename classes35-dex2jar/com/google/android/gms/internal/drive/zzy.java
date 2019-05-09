// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "CreateFolderRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzy extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzy> CREATOR;
    @SafeParcelable$Field(id = 3)
    private final MetadataBundle zzdl;
    @SafeParcelable$Field(id = 2)
    private final DriveId zzdn;
    
    static {
        CREATOR = (Parcelable$Creator)new zzz();
    }
    
    @SafeParcelable$Constructor
    @VisibleForTesting
    public zzy(@SafeParcelable$Param(id = 2) final DriveId driveId, @SafeParcelable$Param(id = 3) final MetadataBundle metadataBundle) {
        this.zzdn = (DriveId)Preconditions.checkNotNull((Object)driveId);
        this.zzdl = (MetadataBundle)Preconditions.checkNotNull((Object)metadataBundle);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzdn, n, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzdl, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
