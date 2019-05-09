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
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@ParametersAreNonnullByDefault
@SafeParcelable$Class(creator = "RtbVersionInfoParcelCreator")
public final class zzzt extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzzt> CREATOR;
    @SafeParcelable$Field(id = 1)
    private final int major;
    @SafeParcelable$Field(id = 2)
    private final int minor;
    @SafeParcelable$Field(id = 3)
    private final int zzbvo;
    
    static {
        CREATOR = (Parcelable$Creator)new zzzu();
    }
    
    @SafeParcelable$Constructor
    zzzt(@SafeParcelable$Param(id = 1) final int major, @SafeParcelable$Param(id = 2) final int minor, @SafeParcelable$Param(id = 3) final int zzbvo) {
        this.major = major;
        this.minor = minor;
        this.zzbvo = zzbvo;
    }
    
    public static zzzt zza(final zzatk zzatk) {
        return new zzzt(zzatk.zzdgt, zzatk.zzdgu, zzatk.zzdgv);
    }
    
    public final String toString() {
        return new StringBuilder(35).append(this.major).append(".").append(this.minor).append(".").append(this.zzbvo).toString();
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.major);
        SafeParcelWriter.writeInt(parcel, 2, this.minor);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbvo);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
