// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "HttpResponseParcelCreator")
public final class zzsi extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzsi> CREATOR;
    @SafeParcelable$Field(id = 4)
    public final byte[] data;
    @SafeParcelable$Field(id = 3)
    public final int statusCode;
    @SafeParcelable$Field(id = 7)
    public final boolean zzac;
    @SafeParcelable$Field(id = 8)
    public final long zzad;
    @SafeParcelable$Field(id = 5)
    public final String[] zzbnh;
    @SafeParcelable$Field(id = 6)
    public final String[] zzbni;
    @SafeParcelable$Field(id = 1)
    public final boolean zzbnj;
    @SafeParcelable$Field(id = 2)
    public final String zzbnk;
    
    static {
        CREATOR = (Parcelable$Creator)new zzsj();
    }
    
    @SafeParcelable$Constructor
    zzsi(@SafeParcelable$Param(id = 1) final boolean zzbnj, @SafeParcelable$Param(id = 2) final String zzbnk, @SafeParcelable$Param(id = 3) final int statusCode, @SafeParcelable$Param(id = 4) final byte[] data, @SafeParcelable$Param(id = 5) final String[] zzbnh, @SafeParcelable$Param(id = 6) final String[] zzbni, @SafeParcelable$Param(id = 7) final boolean zzac, @SafeParcelable$Param(id = 8) final long zzad) {
        this.zzbnj = zzbnj;
        this.zzbnk = zzbnk;
        this.statusCode = statusCode;
        this.data = data;
        this.zzbnh = zzbnh;
        this.zzbni = zzbni;
        this.zzac = zzac;
        this.zzad = zzad;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzbnj);
        SafeParcelWriter.writeString(parcel, 2, this.zzbnk, false);
        SafeParcelWriter.writeInt(parcel, 3, this.statusCode);
        SafeParcelWriter.writeByteArray(parcel, 4, this.data, false);
        SafeParcelWriter.writeStringArray(parcel, 5, this.zzbnh, false);
        SafeParcelWriter.writeStringArray(parcel, 6, this.zzbni, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzac);
        SafeParcelWriter.writeLong(parcel, 8, this.zzad);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
