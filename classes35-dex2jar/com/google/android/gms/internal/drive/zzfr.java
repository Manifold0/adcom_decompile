// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnRealtimeLoadedResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzfr extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfr> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final boolean zzhu;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfw();
    }
    
    @SafeParcelable$Constructor
    public zzfr(@SafeParcelable$Param(id = 2) final boolean zzhu) {
        this.zzhu = zzhu;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzhu);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
