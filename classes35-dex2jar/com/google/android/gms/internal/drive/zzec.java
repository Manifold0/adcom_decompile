// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "DriveServiceResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzec extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzec> CREATOR;
    @SafeParcelable$Field(id = 2)
    final IBinder zzgq;
    
    static {
        CREATOR = (Parcelable$Creator)new zzed();
    }
    
    @SafeParcelable$Constructor
    zzec(@SafeParcelable$Param(id = 2) final IBinder zzgq) {
        this.zzgq = zzgq;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzgq, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
