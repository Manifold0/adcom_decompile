// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.events;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import com.google.android.gms.common.data.DataHolder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.drive.zzu;

@SafeParcelable$Class(creator = "QueryResultEventParcelableCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzo extends zzu implements DriveEvent
{
    public static final Parcelable$Creator<zzo> CREATOR;
    @Nullable
    @SafeParcelable$Field(id = 2)
    private final DataHolder zzat;
    @SafeParcelable$Field(id = 3)
    private final boolean zzco;
    @SafeParcelable$Field(id = 4)
    private final int zzcp;
    
    static {
        CREATOR = (Parcelable$Creator)new zzp();
    }
    
    @SafeParcelable$Constructor
    public zzo(@Nullable @SafeParcelable$Param(id = 2) final DataHolder zzat, @SafeParcelable$Param(id = 3) final boolean zzco, @SafeParcelable$Param(id = 4) final int zzcp) {
        this.zzat = zzat;
        this.zzco = zzco;
        this.zzcp = zzcp;
    }
    
    @Override
    public final int getType() {
        return 3;
    }
    
    public final void zza(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzat, n, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzco);
        SafeParcelWriter.writeInt(parcel, 4, this.zzcp);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final int zzaa() {
        return this.zzcp;
    }
    
    @Nullable
    public final DataHolder zzy() {
        return this.zzat;
    }
    
    public final boolean zzz() {
        return this.zzco;
    }
}
