// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnPayloadTransferUpdateParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzex extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzex> CREATOR;
    @SafeParcelable$Field(getter = "getRemoteEndpointId", id = 1)
    private String zzat;
    @SafeParcelable$Field(getter = "getUpdate", id = 2)
    private PayloadTransferUpdate zzdm;
    
    static {
        CREATOR = (Parcelable$Creator)new zzey();
    }
    
    private zzex() {
    }
    
    @SafeParcelable$Constructor
    zzex(@SafeParcelable$Param(id = 1) final String zzat, @SafeParcelable$Param(id = 2) final PayloadTransferUpdate zzdm) {
        this.zzat = zzat;
        this.zzdm = zzdm;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzex)) {
                return false;
            }
            final zzex zzex = (zzex)o;
            if (!Objects.equal((Object)this.zzat, (Object)zzex.zzat) || !Objects.equal((Object)this.zzdm, (Object)zzex.zzdm)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzat, this.zzdm });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzat, false);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzdm, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final String zzg() {
        return this.zzat;
    }
    
    public final PayloadTransferUpdate zzn() {
        return this.zzdm;
    }
}
