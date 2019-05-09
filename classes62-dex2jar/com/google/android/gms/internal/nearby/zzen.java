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

@SafeParcelable$Class(creator = "OnConnectionResultParamsCreator")
public final class zzen extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzen> CREATOR;
    @SafeParcelable$Field(getter = "getStatusCode", id = 2)
    private int statusCode;
    @SafeParcelable$Field(getter = "getRemoteEndpointId", id = 1)
    private String zzat;
    @Nullable
    @SafeParcelable$Field(getter = "getHandshakeData", id = 3)
    private byte[] zzau;
    
    static {
        CREATOR = (Parcelable$Creator)new zzeo();
    }
    
    private zzen() {
    }
    
    @SafeParcelable$Constructor
    zzen(@SafeParcelable$Param(id = 1) final String zzat, @SafeParcelable$Param(id = 2) final int statusCode, @Nullable @SafeParcelable$Param(id = 3) final byte[] zzau) {
        this.zzat = zzat;
        this.statusCode = statusCode;
        this.zzau = zzau;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzen)) {
                return false;
            }
            final zzen zzen = (zzen)o;
            if (!Objects.equal((Object)this.zzat, (Object)zzen.zzat) || !Objects.equal((Object)this.statusCode, (Object)zzen.statusCode) || !Arrays.equals(this.zzau, zzen.zzau)) {
                return false;
            }
        }
        return true;
    }
    
    public final int getStatusCode() {
        return this.statusCode;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzat, this.statusCode, Arrays.hashCode(this.zzau) });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzat, false);
        SafeParcelWriter.writeInt(parcel, 2, this.statusCode);
        SafeParcelWriter.writeByteArray(parcel, 3, this.zzau, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final String zzg() {
        return this.zzat;
    }
}
