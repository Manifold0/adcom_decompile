// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "FusedLocationProviderResultCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzad extends AbstractSafeParcelable implements Result
{
    public static final Parcelable$Creator<zzad> CREATOR;
    private static final zzad zzcr;
    @SafeParcelable$Field(getter = "getStatus", id = 1)
    private final Status zzbl;
    
    static {
        zzcr = new zzad(Status.RESULT_SUCCESS);
        CREATOR = (Parcelable$Creator)new zzae();
    }
    
    @SafeParcelable$Constructor
    public zzad(@SafeParcelable$Param(id = 1) final Status zzbl) {
        this.zzbl = zzbl;
    }
    
    public final Status getStatus() {
        return this.zzbl;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.getStatus(), n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
