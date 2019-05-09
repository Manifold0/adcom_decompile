// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "SetResourceParentsRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzgq extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzgq> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final DriveId zzic;
    @SafeParcelable$Field(id = 3)
    private final List<DriveId> zzid;
    
    static {
        CREATOR = (Parcelable$Creator)new zzgr();
    }
    
    @SafeParcelable$Constructor
    @VisibleForTesting
    public zzgq(@SafeParcelable$Param(id = 2) final DriveId zzic, @SafeParcelable$Param(id = 3) final List<DriveId> zzid) {
        this.zzic = zzic;
        this.zzid = zzid;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzic, n, false);
        SafeParcelWriter.writeTypedList(parcel, 3, (List)this.zzid, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
