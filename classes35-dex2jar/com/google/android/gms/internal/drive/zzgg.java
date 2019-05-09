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
import com.google.android.gms.drive.query.internal.FilterHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OpenFileIntentSenderRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzgg extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzgg> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final String zzay;
    @SafeParcelable$Field(id = 3)
    private final String[] zzaz;
    @SafeParcelable$Field(id = 4)
    private final DriveId zzbb;
    @SafeParcelable$Field(id = 5)
    private final FilterHolder zzbc;
    
    static {
        CREATOR = (Parcelable$Creator)new zzgh();
    }
    
    @SafeParcelable$Constructor
    @VisibleForTesting
    public zzgg(@SafeParcelable$Param(id = 2) final String zzay, @SafeParcelable$Param(id = 3) final String[] zzaz, @SafeParcelable$Param(id = 4) final DriveId zzbb, @SafeParcelable$Param(id = 5) final FilterHolder zzbc) {
        this.zzay = zzay;
        this.zzaz = zzaz;
        this.zzbb = zzbb;
        this.zzbc = zzbc;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzay, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzaz, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzbb, n, false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzbc, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
