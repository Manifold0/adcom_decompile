// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.events;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "TransferProgressOptionsCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzt extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzt> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final int zzcr;
    
    static {
        CREATOR = (Parcelable$Creator)new zzu();
    }
    
    @SafeParcelable$Constructor
    public zzt(@SafeParcelable$Param(id = 2) final int zzcr) {
        this.zzcr = zzcr;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzcr);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
