// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.Collections;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.zzh;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnDownloadProgressResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzff extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzff> CREATOR;
    private static final List<zzh> zzhh;
    @SafeParcelable$Field(id = 4)
    private final int status;
    @SafeParcelable$Field(id = 2)
    final long zzhi;
    @SafeParcelable$Field(id = 3)
    final long zzhj;
    @Nullable
    @SafeParcelable$Field(id = 5)
    private final List<zzh> zzhk;
    
    static {
        zzhh = Collections.emptyList();
        CREATOR = (Parcelable$Creator)new zzfg();
    }
    
    @SafeParcelable$Constructor
    public zzff(@SafeParcelable$Param(id = 2) final long zzhi, @SafeParcelable$Param(id = 3) final long zzhj, @SafeParcelable$Param(id = 4) final int status, @SafeParcelable$Param(id = 5) final List<zzh> zzhk) {
        this.zzhi = zzhi;
        this.zzhj = zzhj;
        this.status = status;
        this.zzhk = zzhk;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, this.zzhi);
        SafeParcelWriter.writeLong(parcel, 3, this.zzhj);
        SafeParcelWriter.writeInt(parcel, 4, this.status);
        SafeParcelWriter.writeTypedList(parcel, 5, (List)this.zzhk, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
