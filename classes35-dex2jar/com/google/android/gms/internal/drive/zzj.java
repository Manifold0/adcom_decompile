// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.zzt;
import com.google.android.gms.drive.events.zzx;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import com.google.android.gms.drive.events.zze;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "AddEventListenerRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzj extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzj> CREATOR;
    @Nullable
    @SafeParcelable$Field(id = 4)
    private final zze zzbt;
    @SafeParcelable$Field(id = 3)
    final int zzcy;
    @Nullable
    @SafeParcelable$Field(id = 5)
    private final zzx zzcz;
    @Nullable
    @SafeParcelable$Field(id = 6)
    private final zzt zzda;
    @Nullable
    @SafeParcelable$Field(id = 2)
    final DriveId zzk;
    
    static {
        CREATOR = (Parcelable$Creator)new zzk();
    }
    
    public zzj(final int n, final DriveId driveId) {
        this((DriveId)Preconditions.checkNotNull((Object)driveId), 1, null, null, null);
    }
    
    @SafeParcelable$Constructor
    zzj(@SafeParcelable$Param(id = 2) final DriveId zzk, @SafeParcelable$Param(id = 3) final int zzcy, @SafeParcelable$Param(id = 4) final zze zzbt, @SafeParcelable$Param(id = 5) final zzx zzcz, @SafeParcelable$Param(id = 6) final zzt zzda) {
        this.zzk = zzk;
        this.zzcy = zzcy;
        this.zzbt = zzbt;
        this.zzcz = zzcz;
        this.zzda = zzda;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzk, n, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzcy);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzbt, n, false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzcz, n, false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzda, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
