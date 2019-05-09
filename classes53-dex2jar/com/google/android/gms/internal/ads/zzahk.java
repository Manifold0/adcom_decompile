// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "RewardedVideoAdRequestParcelCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzahk extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzahk> CREATOR;
    @SafeParcelable$Field(id = 3)
    public final String zzacp;
    @SafeParcelable$Field(id = 2)
    public final zzjj zzccv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzahl();
    }
    
    @SafeParcelable$Constructor
    public zzahk(@SafeParcelable$Param(id = 2) final zzjj zzccv, @SafeParcelable$Param(id = 3) final String zzacp) {
        this.zzccv = zzccv;
        this.zzacp = zzacp;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzccv, n, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzacp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
