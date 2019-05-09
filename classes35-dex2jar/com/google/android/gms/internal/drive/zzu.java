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
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "CreateFileIntentSenderRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzu extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzu> CREATOR;
    @SafeParcelable$Field(id = 4)
    private final String zzay;
    @SafeParcelable$Field(id = 5)
    private final DriveId zzbb;
    @SafeParcelable$Field(id = 2)
    private final MetadataBundle zzdl;
    @SafeParcelable$Field(id = 6)
    private final Integer zzdm;
    @SafeParcelable$Field(id = 3)
    private final int zzj;
    
    static {
        CREATOR = (Parcelable$Creator)new zzv();
    }
    
    @SafeParcelable$Constructor
    @VisibleForTesting
    public zzu(@SafeParcelable$Param(id = 2) final MetadataBundle zzdl, @SafeParcelable$Param(id = 3) final int zzj, @SafeParcelable$Param(id = 4) final String zzay, @SafeParcelable$Param(id = 5) final DriveId zzbb, @SafeParcelable$Param(id = 6) final Integer zzdm) {
        this.zzdl = zzdl;
        this.zzj = zzj;
        this.zzay = zzay;
        this.zzbb = zzbb;
        this.zzdm = zzdm;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzdl, n, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzj);
        SafeParcelWriter.writeString(parcel, 4, this.zzay, false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzbb, n, false);
        SafeParcelWriter.writeIntegerObject(parcel, 6, this.zzdm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
