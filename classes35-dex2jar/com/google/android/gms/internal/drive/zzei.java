// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.drive.TransferPreferences;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.drive.FileUploadPreferences;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@Deprecated
@SafeParcelable$Class(creator = "FileUploadPreferencesImplCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzei extends AbstractSafeParcelable implements FileUploadPreferences
{
    public static final Parcelable$Creator<zzei> CREATOR;
    @SafeParcelable$Field(id = 3)
    private int zzbl;
    @SafeParcelable$Field(id = 2)
    private int zzgw;
    @SafeParcelable$Field(id = 4)
    private boolean zzgx;
    
    static {
        CREATOR = (Parcelable$Creator)new zzej();
    }
    
    @SafeParcelable$Constructor
    public zzei(@SafeParcelable$Param(id = 2) final int zzgw, @SafeParcelable$Param(id = 3) final int zzbl, @SafeParcelable$Param(id = 4) final boolean zzgx) {
        this.zzgw = zzgw;
        this.zzbl = zzbl;
        this.zzgx = zzgx;
    }
    
    public zzei(final TransferPreferences transferPreferences) {
        this(transferPreferences.getNetworkPreference(), transferPreferences.getBatteryUsagePreference(), transferPreferences.isRoamingAllowed());
    }
    
    private static boolean zzh(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 1:
            case 2: {
                return true;
            }
        }
    }
    
    private static boolean zzi(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 256:
            case 257: {
                return true;
            }
        }
    }
    
    public final int getBatteryUsagePreference() {
        if (!zzi(this.zzbl)) {
            return 0;
        }
        return this.zzbl;
    }
    
    public final int getNetworkTypePreference() {
        if (!zzh(this.zzgw)) {
            return 0;
        }
        return this.zzgw;
    }
    
    public final boolean isRoamingAllowed() {
        return this.zzgx;
    }
    
    public final void setBatteryUsagePreference(final int zzbl) {
        if (!zzi(zzbl)) {
            throw new IllegalArgumentException("Invalid battery usage preference value.");
        }
        this.zzbl = zzbl;
    }
    
    public final void setNetworkTypePreference(final int zzgw) {
        if (!zzh(zzgw)) {
            throw new IllegalArgumentException("Invalid data connection preference value.");
        }
        this.zzgw = zzgw;
    }
    
    public final void setRoamingAllowed(final boolean zzgx) {
        this.zzgx = zzgx;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzgw);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbl);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzgx);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
