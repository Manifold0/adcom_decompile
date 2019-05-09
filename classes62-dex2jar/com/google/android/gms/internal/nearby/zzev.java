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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnPayloadReceivedParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzev extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzev> CREATOR;
    @SafeParcelable$Field(getter = "getRemoteEndpointId", id = 1)
    private String zzat;
    @SafeParcelable$Field(getter = "getPayload", id = 2)
    private zzfh zzdk;
    @SafeParcelable$Field(getter = "getIsReliable", id = 3)
    private boolean zzdl;
    
    static {
        CREATOR = (Parcelable$Creator)new zzew();
    }
    
    private zzev() {
    }
    
    @SafeParcelable$Constructor
    zzev(@SafeParcelable$Param(id = 1) final String zzat, @SafeParcelable$Param(id = 2) final zzfh zzdk, @SafeParcelable$Param(id = 3) final boolean zzdl) {
        this.zzat = zzat;
        this.zzdk = zzdk;
        this.zzdl = zzdl;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzev)) {
                return false;
            }
            final zzev zzev = (zzev)o;
            if (!Objects.equal((Object)this.zzat, (Object)zzev.zzat) || !Objects.equal((Object)this.zzdk, (Object)zzev.zzdk) || !Objects.equal((Object)this.zzdl, (Object)zzev.zzdl)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzat, this.zzdk, this.zzdl });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzat, false);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzdk, n, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzdl);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final String zzg() {
        return this.zzat;
    }
    
    public final zzfh zzl() {
        return this.zzdk;
    }
    
    public final boolean zzm() {
        return this.zzdl;
    }
}
