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

@SafeParcelable$Class(creator = "OnStoppedDiscoveryParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzfd extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfd> CREATOR;
    @SafeParcelable$Field(getter = "getReason", id = 1)
    private int zzdn;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfe();
    }
    
    private zzfd() {
    }
    
    @SafeParcelable$Constructor
    zzfd(@SafeParcelable$Param(id = 1) final int zzdn) {
        this.zzdn = zzdn;
    }
    
    public final boolean equals(final Object o) {
        return this == o || (o instanceof zzfd && Objects.equal((Object)this.zzdn, (Object)((zzfd)o).zzdn));
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzdn });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzdn);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
