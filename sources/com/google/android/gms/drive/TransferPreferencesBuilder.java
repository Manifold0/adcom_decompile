package com.google.android.gms.drive;

import com.google.android.gms.common.internal.Objects;

public class TransferPreferencesBuilder {
    public static final TransferPreferences DEFAULT_PREFERENCES = new zza(1, true, 256);
    private int zzbj;
    private boolean zzbk;
    private int zzbl;

    static class zza implements TransferPreferences {
        private final int zzbj;
        private final boolean zzbk;
        private final int zzbl;

        zza(int i, boolean z, int i2) {
            this.zzbj = i;
            this.zzbk = z;
            this.zzbl = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            zza zza = (zza) obj;
            return zza.zzbj == this.zzbj && zza.zzbk == this.zzbk && zza.zzbl == this.zzbl;
        }

        public final int getBatteryUsagePreference() {
            return this.zzbl;
        }

        public final int getNetworkPreference() {
            return this.zzbj;
        }

        public final int hashCode() {
            return Objects.hashCode(new Object[]{Integer.valueOf(this.zzbj), Boolean.valueOf(this.zzbk), Integer.valueOf(this.zzbl)});
        }

        public final boolean isRoamingAllowed() {
            return this.zzbk;
        }

        public final String toString() {
            return String.format("NetworkPreference: %s, IsRoamingAllowed %s, BatteryUsagePreference %s", new Object[]{Integer.valueOf(this.zzbj), Boolean.valueOf(this.zzbk), Integer.valueOf(this.zzbl)});
        }
    }

    public TransferPreferencesBuilder() {
        this(DEFAULT_PREFERENCES);
    }

    public TransferPreferencesBuilder(FileUploadPreferences fileUploadPreferences) {
        this.zzbj = fileUploadPreferences.getNetworkTypePreference();
        this.zzbk = fileUploadPreferences.isRoamingAllowed();
        this.zzbl = fileUploadPreferences.getBatteryUsagePreference();
    }

    public TransferPreferencesBuilder(TransferPreferences transferPreferences) {
        this.zzbj = transferPreferences.getNetworkPreference();
        this.zzbk = transferPreferences.isRoamingAllowed();
        this.zzbl = transferPreferences.getBatteryUsagePreference();
    }

    public TransferPreferences build() {
        return new zza(this.zzbj, this.zzbk, this.zzbl);
    }

    public TransferPreferencesBuilder setBatteryUsagePreference(int i) {
        this.zzbl = i;
        return this;
    }

    public TransferPreferencesBuilder setIsRoamingAllowed(boolean z) {
        this.zzbk = z;
        return this;
    }

    public TransferPreferencesBuilder setNetworkPreference(int i) {
        this.zzbj = i;
        return this;
    }
}
