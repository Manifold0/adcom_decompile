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

@SafeParcelable$Class(creator = "OnEndpointLostParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzet extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzet> CREATOR;
    @SafeParcelable$Field(getter = "getEndpointId", id = 1)
    private String zzca;
    
    static {
        CREATOR = (Parcelable$Creator)new zzeu();
    }
    
    private zzet() {
    }
    
    @SafeParcelable$Constructor
    zzet(@SafeParcelable$Param(id = 1) final String zzca) {
        this.zzca = zzca;
    }
    
    public final boolean equals(final Object o) {
        return this == o || (o instanceof zzet && Objects.equal((Object)this.zzca, (Object)((zzet)o).zzca));
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzca });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzca, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final String zze() {
        return this.zzca;
    }
}
