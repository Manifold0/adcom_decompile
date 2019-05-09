// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "VideoOptionsParcelCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzmu extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzmu> CREATOR;
    @SafeParcelable$Field(id = 2)
    public final boolean zzato;
    @SafeParcelable$Field(id = 3)
    public final boolean zzatp;
    @SafeParcelable$Field(id = 4)
    public final boolean zzatq;
    
    static {
        CREATOR = (Parcelable$Creator)new zzmv();
    }
    
    public zzmu(final VideoOptions videoOptions) {
        this(videoOptions.getStartMuted(), videoOptions.getCustomControlsRequested(), videoOptions.getClickToExpandRequested());
    }
    
    @SafeParcelable$Constructor
    public zzmu(@SafeParcelable$Param(id = 2) final boolean zzato, @SafeParcelable$Param(id = 3) final boolean zzatp, @SafeParcelable$Param(id = 4) final boolean zzatq) {
        this.zzato = zzato;
        this.zzatp = zzatp;
        this.zzatq = zzatq;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzato);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzatp);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzatq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
