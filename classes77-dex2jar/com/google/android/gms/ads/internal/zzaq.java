// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "InterstitialAdParameterParcelCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzaq extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzaq> CREATOR;
    @SafeParcelable$Field(id = 2)
    public final boolean zzze;
    @SafeParcelable$Field(id = 3)
    public final boolean zzzf;
    @SafeParcelable$Field(id = 4)
    private final String zzzg;
    @SafeParcelable$Field(id = 5)
    public final boolean zzzh;
    @SafeParcelable$Field(id = 6)
    public final float zzzi;
    @SafeParcelable$Field(id = 7)
    public final int zzzj;
    @SafeParcelable$Field(id = 8)
    public final boolean zzzk;
    @SafeParcelable$Field(id = 9)
    public final boolean zzzl;
    @SafeParcelable$Field(id = 10)
    public final boolean zzzm;
    
    static {
        CREATOR = (Parcelable$Creator)new zzar();
    }
    
    @SafeParcelable$Constructor
    zzaq(@SafeParcelable$Param(id = 2) final boolean zzze, @SafeParcelable$Param(id = 3) final boolean zzzf, @SafeParcelable$Param(id = 4) final String zzzg, @SafeParcelable$Param(id = 5) final boolean zzzh, @SafeParcelable$Param(id = 6) final float zzzi, @SafeParcelable$Param(id = 7) final int zzzj, @SafeParcelable$Param(id = 8) final boolean zzzk, @SafeParcelable$Param(id = 9) final boolean zzzl, @SafeParcelable$Param(id = 10) final boolean zzzm) {
        this.zzze = zzze;
        this.zzzf = zzzf;
        this.zzzg = zzzg;
        this.zzzh = zzzh;
        this.zzzi = zzzi;
        this.zzzj = zzzj;
        this.zzzk = zzzk;
        this.zzzl = zzzl;
        this.zzzm = zzzm;
    }
    
    public zzaq(final boolean b, final boolean b2, final boolean b3, final float n, final int n2, final boolean b4, final boolean b5, final boolean b6) {
        this(b, b2, null, b3, n, n2, b4, b5, b6);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzze);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzzf);
        SafeParcelWriter.writeString(parcel, 4, this.zzzg, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzzh);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzzi);
        SafeParcelWriter.writeInt(parcel, 7, this.zzzj);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzzk);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzzl);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzzm);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
