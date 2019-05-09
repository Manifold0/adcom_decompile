// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnResourceIdSetResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzfx extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfx> CREATOR;
    @SafeParcelable$Field(getter = "getResourceIds", id = 2)
    private final List<String> zzhw;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfy();
    }
    
    @SafeParcelable$Constructor
    zzfx(@SafeParcelable$Param(id = 2) final List<String> zzhw) {
        this.zzhw = zzhw;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 2, (List)this.zzhw, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}