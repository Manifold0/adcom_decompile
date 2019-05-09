package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "DiscoveryOptionsCreator")
@Reserved({1000})
public final class DiscoveryOptions extends AbstractSafeParcelable {
    public static final Creator<DiscoveryOptions> CREATOR = new zzg();
    @Field(getter = "getStrategy", id = 1)
    private Strategy zzh;
    @Field(defaultValue = "true", getter = "getEnableBluetooth", id = 3)
    private boolean zzk;
    @Field(defaultValue = "true", getter = "getEnableBle", id = 4)
    private boolean zzl;
    @Field(defaultValue = "false", getter = "getForwardUnrecognizedBluetoothDevices", id = 2)
    private boolean zzw;

    public static final class Builder {
        private final DiscoveryOptions zzx = new DiscoveryOptions();

        public Builder(DiscoveryOptions discoveryOptions) {
            this.zzx.zzh = discoveryOptions.zzh;
            this.zzx.zzw = discoveryOptions.zzw;
            this.zzx.zzk = discoveryOptions.zzk;
            this.zzx.zzl = discoveryOptions.zzl;
        }

        public final DiscoveryOptions build() {
            return this.zzx;
        }

        public final Builder setStrategy(Strategy strategy) {
            this.zzx.zzh = strategy;
            return this;
        }
    }

    private DiscoveryOptions() {
        this.zzw = false;
        this.zzk = true;
        this.zzl = true;
    }

    @Deprecated
    public DiscoveryOptions(Strategy strategy) {
        this.zzw = false;
        this.zzk = true;
        this.zzl = true;
        this.zzh = strategy;
    }

    @Constructor
    DiscoveryOptions(@Param(id = 1) Strategy strategy, @Param(id = 2) boolean z, @Param(id = 3) boolean z2, @Param(id = 4) boolean z3) {
        this.zzw = false;
        this.zzk = true;
        this.zzl = true;
        this.zzh = strategy;
        this.zzw = z;
        this.zzk = z2;
        this.zzl = z3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DiscoveryOptions)) {
            return false;
        }
        DiscoveryOptions discoveryOptions = (DiscoveryOptions) obj;
        return Objects.equal(this.zzh, discoveryOptions.zzh) && Objects.equal(Boolean.valueOf(this.zzw), Boolean.valueOf(discoveryOptions.zzw)) && Objects.equal(Boolean.valueOf(this.zzk), Boolean.valueOf(discoveryOptions.zzk)) && Objects.equal(Boolean.valueOf(this.zzl), Boolean.valueOf(discoveryOptions.zzl));
    }

    public final Strategy getStrategy() {
        return this.zzh;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzh, Boolean.valueOf(this.zzw), Boolean.valueOf(this.zzk), Boolean.valueOf(this.zzl)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStrategy(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzw);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzk);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzl);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
