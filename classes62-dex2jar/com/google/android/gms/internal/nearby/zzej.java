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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnConnectionRequestParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzej extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzej> CREATOR;
    @SafeParcelable$Field(getter = "getRemoteEndpointId", id = 1)
    private String zzat;
    @Nullable
    @SafeParcelable$Field(getter = "getHandshakeData", id = 3)
    private byte[] zzau;
    @SafeParcelable$Field(getter = "getRemoteEndpointName", id = 2)
    private String zzdj;
    
    static {
        CREATOR = (Parcelable$Creator)new zzek();
    }
    
    private zzej() {
    }
    
    @SafeParcelable$Constructor
    zzej(@SafeParcelable$Param(id = 1) final String zzat, @SafeParcelable$Param(id = 2) final String zzdj, @Nullable @SafeParcelable$Param(id = 3) final byte[] zzau) {
        this.zzat = zzat;
        this.zzdj = zzdj;
        this.zzau = zzau;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzej)) {
                return false;
            }
            final zzej zzej = (zzej)o;
            if (!Objects.equal((Object)this.zzat, (Object)zzej.zzat) || !Objects.equal((Object)this.zzdj, (Object)zzej.zzdj) || !Arrays.equals(this.zzau, zzej.zzau)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzat, this.zzdj, Arrays.hashCode(this.zzau) });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzat, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzdj, false);
        SafeParcelWriter.writeByteArray(parcel, 3, this.zzau, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final String zzg() {
        return this.zzat;
    }
    
    public final String zzh() {
        return this.zzdj;
    }
    
    @Nullable
    public final byte[] zzj() {
        return this.zzau;
    }
}
