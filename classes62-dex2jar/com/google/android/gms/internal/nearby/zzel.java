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

@SafeParcelable$Class(creator = "OnConnectionResponseParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzel extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzel> CREATOR;
    @SafeParcelable$Field(getter = "getStatusCode", id = 2)
    private int statusCode;
    @SafeParcelable$Field(getter = "getRemoteEndpointId", id = 1)
    private String zzat;
    @Nullable
    @SafeParcelable$Field(getter = "getHandshakeData", id = 3)
    private byte[] zzau;
    
    static {
        CREATOR = (Parcelable$Creator)new zzem();
    }
    
    private zzel() {
    }
    
    @SafeParcelable$Constructor
    zzel(@SafeParcelable$Param(id = 1) final String zzat, @SafeParcelable$Param(id = 2) final int statusCode, @Nullable @SafeParcelable$Param(id = 3) final byte[] zzau) {
        this.zzat = zzat;
        this.statusCode = statusCode;
        this.zzau = zzau;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzel)) {
                return false;
            }
            final zzel zzel = (zzel)o;
            if (!Objects.equal((Object)this.zzat, (Object)zzel.zzat) || !Objects.equal((Object)this.statusCode, (Object)zzel.statusCode) || !Arrays.equals(this.zzau, zzel.zzau)) {
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
    
    @Nullable
    public final byte[] zzj() {
        return this.zzau;
    }
}
