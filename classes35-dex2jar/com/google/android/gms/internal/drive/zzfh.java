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
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnDriveIdResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzfh extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfh> CREATOR;
    @SafeParcelable$Field(id = 2)
    DriveId zzdb;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfi();
    }
    
    @SafeParcelable$Constructor
    public zzfh(@SafeParcelable$Param(id = 2) final DriveId zzdb) {
        this.zzdb = zzdb;
    }
    
    public final DriveId getDriveId() {
        return this.zzdb;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzdb, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
