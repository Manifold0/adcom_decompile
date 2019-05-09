// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnStartStreamSessionCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzfz extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfz> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final ParcelFileDescriptor zzhx;
    @SafeParcelable$Field(id = 3)
    private final IBinder zzhy;
    @SafeParcelable$Field(id = 4)
    private final String zzm;
    
    static {
        CREATOR = (Parcelable$Creator)new zzga();
    }
    
    @SafeParcelable$Constructor
    zzfz(@SafeParcelable$Param(id = 2) final ParcelFileDescriptor zzhx, @SafeParcelable$Param(id = 3) final IBinder zzhy, @SafeParcelable$Param(id = 4) final String zzm) {
        this.zzhx = zzhx;
        this.zzhy = zzhy;
        this.zzm = zzm;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzhx, n | 0x1, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzhy, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
