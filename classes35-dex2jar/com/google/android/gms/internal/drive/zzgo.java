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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "SetFileUploadPreferencesRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzgo extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzgo> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final zzei zzhg;
    
    static {
        CREATOR = (Parcelable$Creator)new zzgp();
    }
    
    @SafeParcelable$Constructor
    @VisibleForTesting
    public zzgo(@SafeParcelable$Param(id = 2) final zzei zzhg) {
        this.zzhg = zzhg;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzhg, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
