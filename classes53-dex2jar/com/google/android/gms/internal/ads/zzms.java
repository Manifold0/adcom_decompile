// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;

@zzadh
public final class zzms extends zzjn
{
    public zzms(final zzjn zzjn) {
        super(zzjn.zzarb, zzjn.height, zzjn.heightPixels, zzjn.zzarc, zzjn.width, zzjn.widthPixels, zzjn.zzard, zzjn.zzare, zzjn.zzarf, zzjn.zzarg);
    }
    
    @Override
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzarb, false);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 6, this.width);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
