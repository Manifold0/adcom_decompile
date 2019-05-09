// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.events;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.internal.drive.zzh;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "TransferProgressEventCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzr extends AbstractSafeParcelable implements DriveEvent
{
    public static final Parcelable$Creator<zzr> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final zzh zzcq;
    
    static {
        CREATOR = (Parcelable$Creator)new zzs();
    }
    
    @SafeParcelable$Constructor
    public zzr(@SafeParcelable$Param(id = 2) final zzh zzcq) {
        this.zzcq = zzcq;
    }
    
    public final boolean equals(final Object o) {
        return o != null && o.getClass() == this.getClass() && (o == this || Objects.equal((Object)this.zzcq, (Object)((zzr)o).zzcq));
    }
    
    public final int getType() {
        return 8;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzcq });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzcq, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final zzh zzab() {
        return this.zzcq;
    }
}
