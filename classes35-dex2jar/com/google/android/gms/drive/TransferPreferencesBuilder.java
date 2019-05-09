// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.Objects;

public class TransferPreferencesBuilder
{
    public static final TransferPreferences DEFAULT_PREFERENCES;
    private int zzbj;
    private boolean zzbk;
    private int zzbl;
    
    static {
        DEFAULT_PREFERENCES = new zza(1, true, 256);
    }
    
    public TransferPreferencesBuilder() {
        this(TransferPreferencesBuilder.DEFAULT_PREFERENCES);
    }
    
    public TransferPreferencesBuilder(final FileUploadPreferences fileUploadPreferences) {
        this.zzbj = fileUploadPreferences.getNetworkTypePreference();
        this.zzbk = fileUploadPreferences.isRoamingAllowed();
        this.zzbl = fileUploadPreferences.getBatteryUsagePreference();
    }
    
    public TransferPreferencesBuilder(final TransferPreferences transferPreferences) {
        this.zzbj = transferPreferences.getNetworkPreference();
        this.zzbk = transferPreferences.isRoamingAllowed();
        this.zzbl = transferPreferences.getBatteryUsagePreference();
    }
    
    public TransferPreferences build() {
        return new zza(this.zzbj, this.zzbk, this.zzbl);
    }
    
    public TransferPreferencesBuilder setBatteryUsagePreference(final int zzbl) {
        this.zzbl = zzbl;
        return this;
    }
    
    public TransferPreferencesBuilder setIsRoamingAllowed(final boolean zzbk) {
        this.zzbk = zzbk;
        return this;
    }
    
    public TransferPreferencesBuilder setNetworkPreference(final int zzbj) {
        this.zzbj = zzbj;
        return this;
    }
    
    static final class zza implements TransferPreferences
    {
        private final int zzbj;
        private final boolean zzbk;
        private final int zzbl;
        
        zza(final int zzbj, final boolean zzbk, final int zzbl) {
            this.zzbj = zzbj;
            this.zzbk = zzbk;
            this.zzbl = zzbl;
        }
        
        @Override
        public final boolean equals(final Object o) {
            if (this != o) {
                if (o == null || this.getClass() != o.getClass()) {
                    return false;
                }
                final zza zza = (zza)o;
                if (zza.zzbj != this.zzbj || zza.zzbk != this.zzbk || zza.zzbl != this.zzbl) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public final int getBatteryUsagePreference() {
            return this.zzbl;
        }
        
        @Override
        public final int getNetworkPreference() {
            return this.zzbj;
        }
        
        @Override
        public final int hashCode() {
            return Objects.hashCode(new Object[] { this.zzbj, this.zzbk, this.zzbl });
        }
        
        @Override
        public final boolean isRoamingAllowed() {
            return this.zzbk;
        }
        
        @Override
        public final String toString() {
            return String.format("NetworkPreference: %s, IsRoamingAllowed %s, BatteryUsagePreference %s", this.zzbj, this.zzbk, this.zzbl);
        }
    }
}
