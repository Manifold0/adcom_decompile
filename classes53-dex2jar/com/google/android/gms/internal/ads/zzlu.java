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
@SafeParcelable$Class(creator = "IconAdOptionsParcelCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzlu extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzlu> CREATOR;
    @SafeParcelable$Field(id = 2)
    public final int zzasj;
    
    static {
        CREATOR = (Parcelable$Creator)new zzlv();
    }
    
    @SafeParcelable$Constructor
    public zzlu(@SafeParcelable$Param(id = 2) final int zzasj) {
        this.zzasj = zzasj;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzasj);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
