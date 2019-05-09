package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Arrays;

@Class(creator = "AdvertisingOptionsCreator")
@Reserved({1000})
public final class AdvertisingOptions extends AbstractSafeParcelable {
    public static final Creator<AdvertisingOptions> CREATOR = new zzb();
    @Field(getter = "getStrategy", id = 1)
    private Strategy zzh;
    @Field(defaultValue = "true", getter = "getAutoUpgradeBandwidth", id = 2)
    private boolean zzi;
    @Field(defaultValue = "true", getter = "getEnforceTopologyConstraints", id = 3)
    private boolean zzj;
    @Field(defaultValue = "true", getter = "getEnableBluetooth", id = 4)
    private boolean zzk;
    @Field(defaultValue = "true", getter = "getEnableBle", id = 5)
    private boolean zzl;
    @Nullable
    @Field(getter = "getNearbyNotificationsBeaconData", id = 6)
    private byte[] zzm;

    public static final class Builder {
        private final AdvertisingOptions zzn = new AdvertisingOptions();

        public Builder(AdvertisingOptions advertisingOptions) {
            this.zzn.zzh = advertisingOptions.zzh;
            this.zzn.zzi = advertisingOptions.zzi;
            this.zzn.zzj = advertisingOptions.zzj;
            this.zzn.zzk = advertisingOptions.zzk;
            this.zzn.zzl = advertisingOptions.zzl;
            this.zzn.zzm = advertisingOptions.zzm;
        }

        public final AdvertisingOptions build() {
            return this.zzn;
        }

        public final Builder setStrategy(Strategy strategy) {
            this.zzn.zzh = strategy;
            return this;
        }
    }

    private AdvertisingOptions() {
        this.zzi = true;
        this.zzj = true;
        this.zzk = true;
        this.zzl = true;
    }

    @Deprecated
    public AdvertisingOptions(Strategy strategy) {
        this.zzi = true;
        this.zzj = true;
        this.zzk = true;
        this.zzl = true;
        this.zzh = strategy;
    }

    @Constructor
    AdvertisingOptions(@Param(id = 1) Strategy strategy, @Param(id = 2) boolean z, @Param(id = 3) boolean z2, @Param(id = 4) boolean z3, @Param(id = 5) boolean z4, @Nullable @Param(id = 6) byte[] bArr) {
        this.zzi = true;
        this.zzj = true;
        this.zzk = true;
        this.zzl = true;
        this.zzh = strategy;
        this.zzi = z;
        this.zzj = z2;
        this.zzk = z3;
        this.zzl = z4;
        this.zzm = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdvertisingOptions)) {
            return false;
        }
        AdvertisingOptions advertisingOptions = (AdvertisingOptions) obj;
        return Objects.equal(this.zzh, advertisingOptions.zzh) && Objects.equal(Boolean.valueOf(this.zzi), Boolean.valueOf(advertisingOptions.zzi)) && Objects.equal(Boolean.valueOf(this.zzj), Boolean.valueOf(advertisingOptions.zzj)) && Objects.equal(Boolean.valueOf(this.zzk), Boolean.valueOf(advertisingOptions.zzk)) && Objects.equal(Boolean.valueOf(this.zzl), Boolean.valueOf(advertisingOptions.zzl)) && Arrays.equals(this.zzm, advertisingOptions.zzm);
    }

    public final Strategy getStrategy() {
        return this.zzh;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzh, Boolean.valueOf(this.zzi), Boolean.valueOf(this.zzj), Boolean.valueOf(this.zzk), Boolean.valueOf(this.zzl), Integer.valueOf(Arrays.hashCode(this.zzm))});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStrategy(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzi);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzj);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzk);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzl);
        SafeParcelWriter.writeByteArray(parcel, 6, this.zzm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
