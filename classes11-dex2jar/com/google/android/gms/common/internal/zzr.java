// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@Deprecated
@Class(creator = "ValidateAccountRequestCreator")
public final class zzr extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzr> CREATOR;
    @VersionField(id = 1)
    private final int zzg;
    
    static {
        CREATOR = (Parcelable$Creator)new zzs();
    }
    
    @Constructor
    zzr(@Param(id = 1) final int zzg) {
        this.zzg = zzg;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
