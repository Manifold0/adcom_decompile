// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.Contents;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnContentsResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzfb extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfb> CREATOR;
    @SafeParcelable$Field(id = 2)
    final Contents zzeq;
    @SafeParcelable$Field(id = 3)
    final boolean zzhf;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfc();
    }
    
    @SafeParcelable$Constructor
    public zzfb(@SafeParcelable$Param(id = 2) final Contents zzeq, @SafeParcelable$Param(id = 3) final boolean zzhf) {
        this.zzeq = zzeq;
        this.zzhf = zzhf;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzeq, n, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzhf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final Contents zzai() {
        return this.zzeq;
    }
}
