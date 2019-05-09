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
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OpenContentsRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzgd extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzgd> CREATOR;
    @SafeParcelable$Field(id = 3)
    private final int mode;
    @SafeParcelable$Field(id = 2)
    private final DriveId zzdb;
    @SafeParcelable$Field(id = 4)
    private final int zzhz;
    
    static {
        CREATOR = (Parcelable$Creator)new zzge();
    }
    
    @SafeParcelable$Constructor
    @VisibleForTesting
    public zzgd(@SafeParcelable$Param(id = 2) final DriveId zzdb, @SafeParcelable$Param(id = 3) final int mode, @SafeParcelable$Param(id = 4) final int zzhz) {
        this.zzdb = zzdb;
        this.mode = mode;
        this.zzhz = zzhz;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzdb, n, false);
        SafeParcelWriter.writeInt(parcel, 3, this.mode);
        SafeParcelWriter.writeInt(parcel, 4, this.zzhz);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
