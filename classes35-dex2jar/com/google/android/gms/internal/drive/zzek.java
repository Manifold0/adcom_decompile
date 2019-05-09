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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "GetMetadataRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzek extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzek> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final DriveId zzdb;
    @SafeParcelable$Field(id = 3)
    private final boolean zzgy;
    
    static {
        CREATOR = (Parcelable$Creator)new zzel();
    }
    
    @SafeParcelable$Constructor
    @VisibleForTesting
    public zzek(@SafeParcelable$Param(id = 2) final DriveId zzdb, @SafeParcelable$Param(id = 3) final boolean zzgy) {
        this.zzdb = zzdb;
        this.zzgy = zzgy;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzdb, n, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzgy);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
