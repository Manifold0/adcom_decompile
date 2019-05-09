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

@SafeParcelable$Class(creator = "OnSyncMoreResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzgb extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzgb> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final boolean zzdy;
    
    static {
        CREATOR = (Parcelable$Creator)new zzgc();
    }
    
    @SafeParcelable$Constructor
    public zzgb(@SafeParcelable$Param(id = 2) final boolean zzdy) {
        this.zzdy = zzdy;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzdy);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
