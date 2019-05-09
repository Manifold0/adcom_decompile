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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "StringParcelCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzafj extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzafj> CREATOR;
    @SafeParcelable$Field(id = 2)
    String zzbgu;
    
    static {
        CREATOR = (Parcelable$Creator)new zzafk();
    }
    
    @SafeParcelable$Constructor
    public zzafj(@SafeParcelable$Param(id = 2) final String zzbgu) {
        this.zzbgu = zzbgu;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzbgu, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
