// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.drive.zza;
import com.google.android.gms.drive.DriveId;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.data.DataHolder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.drive.zzu;

@SafeParcelable$Class(creator = "OnChangesResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzez extends zzu
{
    public static final Parcelable$Creator<zzez> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final DataHolder zzhb;
    @SafeParcelable$Field(id = 3)
    private final List<DriveId> zzhc;
    @SafeParcelable$Field(id = 4)
    private final zza zzhd;
    @SafeParcelable$Field(id = 5)
    private final boolean zzhe;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfa();
    }
    
    @SafeParcelable$Constructor
    public zzez(@SafeParcelable$Param(id = 2) final DataHolder zzhb, @SafeParcelable$Param(id = 3) final List<DriveId> zzhc, @SafeParcelable$Param(id = 4) final zza zzhd, @SafeParcelable$Param(id = 5) final boolean zzhe) {
        this.zzhb = zzhb;
        this.zzhc = zzhc;
        this.zzhd = zzhd;
        this.zzhe = zzhe;
    }
    
    @Override
    protected final void zza(final Parcel parcel, int n) {
        n |= 0x1;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzhb, n, false);
        SafeParcelWriter.writeTypedList(parcel, 3, (List)this.zzhc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzhd, n, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzhe);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
