// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Arrays;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "AdvertisingOptionsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class AdvertisingOptions extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<AdvertisingOptions> CREATOR;
    @SafeParcelable$Field(getter = "getStrategy", id = 1)
    private Strategy zzh;
    @SafeParcelable$Field(defaultValue = "true", getter = "getAutoUpgradeBandwidth", id = 2)
    private boolean zzi;
    @SafeParcelable$Field(defaultValue = "true", getter = "getEnforceTopologyConstraints", id = 3)
    private boolean zzj;
    @SafeParcelable$Field(defaultValue = "true", getter = "getEnableBluetooth", id = 4)
    private boolean zzk;
    @SafeParcelable$Field(defaultValue = "true", getter = "getEnableBle", id = 5)
    private boolean zzl;
    @Nullable
    @SafeParcelable$Field(getter = "getNearbyNotificationsBeaconData", id = 6)
    private byte[] zzm;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    private AdvertisingOptions() {
        this.zzi = true;
        this.zzj = true;
        this.zzk = true;
        this.zzl = true;
    }
    
    @Deprecated
    public AdvertisingOptions(final Strategy zzh) {
        this.zzi = true;
        this.zzj = true;
        this.zzk = true;
        this.zzl = true;
        this.zzh = zzh;
    }
    
    @SafeParcelable$Constructor
    AdvertisingOptions(@SafeParcelable$Param(id = 1) final Strategy zzh, @SafeParcelable$Param(id = 2) final boolean zzi, @SafeParcelable$Param(id = 3) final boolean zzj, @SafeParcelable$Param(id = 4) final boolean zzk, @SafeParcelable$Param(id = 5) final boolean zzl, @Nullable @SafeParcelable$Param(id = 6) final byte[] zzm) {
        this.zzi = true;
        this.zzj = true;
        this.zzk = true;
        this.zzl = true;
        this.zzh = zzh;
        this.zzi = zzi;
        this.zzj = zzj;
        this.zzk = zzk;
        this.zzl = zzl;
        this.zzm = zzm;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof AdvertisingOptions)) {
                return false;
            }
            final AdvertisingOptions advertisingOptions = (AdvertisingOptions)o;
            if (!Objects.equal((Object)this.zzh, (Object)advertisingOptions.zzh) || !Objects.equal((Object)this.zzi, (Object)advertisingOptions.zzi) || !Objects.equal((Object)this.zzj, (Object)advertisingOptions.zzj) || !Objects.equal((Object)this.zzk, (Object)advertisingOptions.zzk) || !Objects.equal((Object)this.zzl, (Object)advertisingOptions.zzl) || !Arrays.equals(this.zzm, advertisingOptions.zzm)) {
                return false;
            }
        }
        return true;
    }
    
    public final Strategy getStrategy() {
        return this.zzh;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, Arrays.hashCode(this.zzm) });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.getStrategy(), n, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzi);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzj);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzk);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzl);
        SafeParcelWriter.writeByteArray(parcel, 6, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public static final class Builder
    {
        private final AdvertisingOptions zzn;
        
        public Builder() {
            this.zzn = new AdvertisingOptions((zza)null);
        }
        
        public Builder(final AdvertisingOptions advertisingOptions) {
            (this.zzn = new AdvertisingOptions((zza)null)).zzh = advertisingOptions.zzh;
            this.zzn.zzi = advertisingOptions.zzi;
            this.zzn.zzj = advertisingOptions.zzj;
            this.zzn.zzk = advertisingOptions.zzk;
            this.zzn.zzl = advertisingOptions.zzl;
            this.zzn.zzm = advertisingOptions.zzm;
        }
        
        public final AdvertisingOptions build() {
            return this.zzn;
        }
        
        public final Builder setStrategy(final Strategy strategy) {
            this.zzn.zzh = strategy;
            return this;
        }
    }
}
