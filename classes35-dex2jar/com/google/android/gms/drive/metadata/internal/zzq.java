// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "PartialDriveIdCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzq extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzq> CREATOR;
    @Nullable
    @SafeParcelable$Field(id = 2)
    final String zzab;
    @SafeParcelable$Field(id = 3)
    final long zzac;
    @SafeParcelable$Field(defaultValueUnchecked = "com.google.android.gms.drive.DriveId.RESOURCE_TYPE_UNKNOWN", id = 4)
    final int zzad;
    
    static {
        CREATOR = (Parcelable$Creator)new zzr();
    }
    
    @SafeParcelable$Constructor
    public zzq(@SafeParcelable$Param(id = 2) final String zzab, @SafeParcelable$Param(id = 3) final long zzac, @SafeParcelable$Param(id = 4) final int zzad) {
        this.zzab = zzab;
        this.zzac = zzac;
        this.zzad = zzad;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzab, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzac);
        SafeParcelWriter.writeInt(parcel, 4, this.zzad);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
