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

@SafeParcelable$Class(creator = "StringListResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzgt extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzgt> CREATOR;
    @SafeParcelable$Field(getter = "getList", id = 2)
    private final List<String> zzie;
    
    static {
        CREATOR = (Parcelable$Creator)new zzgu();
    }
    
    @SafeParcelable$Constructor
    public zzgt(@SafeParcelable$Param(id = 2) final List<String> zzie) {
        this.zzie = zzie;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 2, (List)this.zzie, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
