// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Arrays;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnConnectionInitiatedParamsCreator")
public final class zzeh extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzeh> CREATOR;
    @SafeParcelable$Field(getter = "getRemoteEndpointId", id = 1)
    private String zzat;
    @Nullable
    @SafeParcelable$Field(getter = "getHandshakeData", id = 5)
    private byte[] zzau;
    @SafeParcelable$Field(getter = "getRemoteEndpointName", id = 2)
    private String zzdj;
    @SafeParcelable$Field(getter = "getAuthenticationToken", id = 3)
    private String zzr;
    @SafeParcelable$Field(getter = "getIsIncomingConnection", id = 4)
    private boolean zzs;
    
    static {
        CREATOR = (Parcelable$Creator)new zzei();
    }
    
    private zzeh() {
    }
    
    @SafeParcelable$Constructor
    zzeh(@SafeParcelable$Param(id = 1) final String zzat, @SafeParcelable$Param(id = 2) final String zzdj, @SafeParcelable$Param(id = 3) final String zzr, @SafeParcelable$Param(id = 4) final boolean zzs, @Nullable @SafeParcelable$Param(id = 5) final byte[] zzau) {
        this.zzat = zzat;
        this.zzdj = zzdj;
        this.zzr = zzr;
        this.zzs = zzs;
        this.zzau = zzau;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzeh)) {
                return false;
            }
            final zzeh zzeh = (zzeh)o;
            if (!Objects.equal((Object)this.zzat, (Object)zzeh.zzat) || !Objects.equal((Object)this.zzdj, (Object)zzeh.zzdj) || !Objects.equal((Object)this.zzr, (Object)zzeh.zzr) || !Objects.equal((Object)this.zzs, (Object)zzeh.zzs) || !Arrays.equals(this.zzau, zzeh.zzau)) {
                return false;
            }
        }
        return true;
    }
    
    public final String getAuthenticationToken() {
        return this.zzr;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzat, this.zzdj, this.zzr, this.zzs, Arrays.hashCode(this.zzau) });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzat, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzdj, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzr, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzs);
        SafeParcelWriter.writeByteArray(parcel, 5, this.zzau, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final String zzg() {
        return this.zzat;
    }
    
    public final String zzh() {
        return this.zzdj;
    }
    
    public final boolean zzi() {
        return this.zzs;
    }
}
