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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnBandwidthChangedParamsCreator")
public final class zzef extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzef> CREATOR;
    @SafeParcelable$Field(getter = "getQuality", id = 2)
    private int quality;
    @SafeParcelable$Field(getter = "getRemoteEndpointId", id = 1)
    private String zzat;
    
    static {
        CREATOR = (Parcelable$Creator)new zzeg();
    }
    
    private zzef() {
    }
    
    @SafeParcelable$Constructor
    zzef(@SafeParcelable$Param(id = 1) final String zzat, @SafeParcelable$Param(id = 2) final int quality) {
        this.zzat = zzat;
        this.quality = quality;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzef)) {
                return false;
            }
            final zzef zzef = (zzef)o;
            if (!Objects.equal((Object)this.zzat, (Object)zzef.zzat) || !Objects.equal((Object)this.quality, (Object)zzef.quality)) {
                return false;
            }
        }
        return true;
    }
    
    public final int getQuality() {
        return this.quality;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzat, this.quality });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzat, false);
        SafeParcelWriter.writeInt(parcel, 2, this.quality);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final String zzg() {
        return this.zzat;
    }
}
