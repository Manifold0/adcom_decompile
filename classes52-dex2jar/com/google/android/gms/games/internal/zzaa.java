// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;

@SafeParcelable$Class(creator = "PopupLocationInfoParcelableCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzaa extends zzd
{
    public static final Parcelable$Creator<zzaa> CREATOR;
    @SafeParcelable$Field(getter = "getInfoBundle", id = 1)
    private final Bundle zzja;
    @SafeParcelable$Field(getter = "getWindowToken", id = 2)
    private final IBinder zzjb;
    
    static {
        CREATOR = (Parcelable$Creator)new zzab();
    }
    
    @SafeParcelable$Constructor
    zzaa(@SafeParcelable$Param(id = 1) final Bundle zzja, @SafeParcelable$Param(id = 2) final IBinder zzjb) {
        this.zzja = zzja;
        this.zzjb = zzjb;
    }
    
    public zzaa(final zzae zzae) {
        this.zzja = zzae.zzbk();
        this.zzjb = zzae.zzjb;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzja, false);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzjb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
