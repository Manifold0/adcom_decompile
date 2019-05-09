// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.TransferPreferences;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnPinnedDownloadPreferencesResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzfu extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfu> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final zzgi zzhv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfv();
    }
    
    @SafeParcelable$Constructor
    zzfu(@SafeParcelable$Param(id = 2) final zzgi zzhv) {
        this.zzhv = zzhv;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzhv, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final TransferPreferences zzao() {
        return this.zzhv;
    }
}
