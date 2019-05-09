// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.drive.zzu;

@SafeParcelable$Class(creator = "OnListEntriesResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzfn extends zzu
{
    public static final Parcelable$Creator<zzfn> CREATOR;
    @SafeParcelable$Field(id = 3)
    final boolean zzdy;
    @SafeParcelable$Field(id = 2)
    final DataHolder zzhs;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfo();
    }
    
    @SafeParcelable$Constructor
    public zzfn(@SafeParcelable$Param(id = 2) final DataHolder zzhs, @SafeParcelable$Param(id = 3) final boolean zzdy) {
        this.zzhs = zzhs;
        this.zzdy = zzdy;
    }
    
    @Override
    protected final void zza(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzhs, n, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzdy);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final DataHolder zzal() {
        return this.zzhs;
    }
}
