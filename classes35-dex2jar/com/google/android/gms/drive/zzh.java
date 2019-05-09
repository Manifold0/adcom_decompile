// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "DriveFileRangeCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzh extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzh> CREATOR;
    @SafeParcelable$Field(id = 3)
    private final long zzaa;
    @SafeParcelable$Field(id = 2)
    private final long zzz;
    
    static {
        CREATOR = (Parcelable$Creator)new zzi();
    }
    
    @SafeParcelable$Constructor
    public zzh(@SafeParcelable$Param(id = 2) final long zzz, @SafeParcelable$Param(id = 3) final long zzaa) {
        this.zzz = zzz;
        this.zzaa = zzaa;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, this.zzz);
        SafeParcelWriter.writeLong(parcel, 3, this.zzaa);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
