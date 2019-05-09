// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;
import android.support.annotation.Nullable;
import com.google.android.gms.drive.events.zzt;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "RemoveEventListenerRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzgm extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzgm> CREATOR;
    @SafeParcelable$Field(id = 3)
    private final int zzcy;
    @Nullable
    @SafeParcelable$Field(id = 4)
    private final zzt zzda;
    @SafeParcelable$Field(id = 2)
    private final DriveId zzk;
    
    static {
        CREATOR = (Parcelable$Creator)new zzgn();
    }
    
    @VisibleForTesting
    public zzgm(@Nullable final DriveId driveId, final int n) {
        this(driveId, n, null);
    }
    
    @SafeParcelable$Constructor
    zzgm(@SafeParcelable$Param(id = 2) final DriveId zzk, @SafeParcelable$Param(id = 3) final int zzcy, @SafeParcelable$Param(id = 4) final zzt zzda) {
        this.zzk = zzk;
        this.zzcy = zzcy;
        this.zzda = zzda;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzk, n, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzcy);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzda, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
