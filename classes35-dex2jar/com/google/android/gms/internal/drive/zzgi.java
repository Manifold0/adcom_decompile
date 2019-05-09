// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.drive.TransferPreferences;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ParcelableTransferPreferencesCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzgi extends AbstractSafeParcelable implements TransferPreferences
{
    public static final Parcelable$Creator<zzgi> CREATOR;
    @SafeParcelable$Field(id = 4)
    private final boolean zzbk;
    @SafeParcelable$Field(id = 3)
    private final int zzbl;
    @SafeParcelable$Field(id = 2)
    private final int zzgw;
    
    static {
        CREATOR = (Parcelable$Creator)new zzgj();
    }
    
    @SafeParcelable$Constructor
    zzgi(@SafeParcelable$Param(id = 2) final int zzgw, @SafeParcelable$Param(id = 3) final int zzbl, @SafeParcelable$Param(id = 4) final boolean zzbk) {
        this.zzgw = zzgw;
        this.zzbl = zzbl;
        this.zzbk = zzbk;
    }
    
    public final int getBatteryUsagePreference() {
        return this.zzbl;
    }
    
    public final int getNetworkPreference() {
        return this.zzgw;
    }
    
    public final boolean isRoamingAllowed() {
        return this.zzbk;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzgw);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbl);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzbk);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
