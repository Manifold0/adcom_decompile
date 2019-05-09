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
import com.google.android.gms.common.data.DataHolder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.drive.zzu;

@SafeParcelable$Class(creator = "OnListParentsResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzfp extends zzu
{
    public static final Parcelable$Creator<zzfp> CREATOR;
    @SafeParcelable$Field(id = 2)
    final DataHolder zzht;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfq();
    }
    
    @SafeParcelable$Constructor
    public zzfp(@SafeParcelable$Param(id = 2) final DataHolder zzht) {
        this.zzht = zzht;
    }
    
    @Override
    protected final void zza(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzht, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final DataHolder zzam() {
        return this.zzht;
    }
}
