// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnDisconnectedParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzep extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzep> CREATOR;
    @SafeParcelable$Field(getter = "getRemoteEndpointId", id = 1)
    private String zzat;
    
    static {
        CREATOR = (Parcelable$Creator)new zzeq();
    }
    
    private zzep() {
    }
    
    @SafeParcelable$Constructor
    zzep(@SafeParcelable$Param(id = 1) final String zzat) {
        this.zzat = zzat;
    }
    
    public final boolean equals(final Object o) {
        return this == o || (o instanceof zzep && Objects.equal((Object)this.zzat, (Object)((zzep)o).zzat));
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzat });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzat, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final String zzg() {
        return this.zzat;
    }
}
