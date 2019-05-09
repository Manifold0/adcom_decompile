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

@SafeParcelable$Class(creator = "OnStoppedAdvertisingParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzfb extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfb> CREATOR;
    @SafeParcelable$Field(getter = "getReason", id = 1)
    private int zzdn;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfc();
    }
    
    private zzfb() {
    }
    
    @SafeParcelable$Constructor
    zzfb(@SafeParcelable$Param(id = 1) final int zzdn) {
        this.zzdn = zzdn;
    }
    
    public final boolean equals(final Object o) {
        return this == o || (o instanceof zzfb && Objects.equal((Object)this.zzdn, (Object)((zzfb)o).zzdn));
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
