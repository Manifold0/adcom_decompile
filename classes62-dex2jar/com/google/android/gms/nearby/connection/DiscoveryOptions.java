// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "DiscoveryOptionsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class DiscoveryOptions extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<DiscoveryOptions> CREATOR;
    @SafeParcelable$Field(getter = "getStrategy", id = 1)
    private Strategy zzh;
    @SafeParcelable$Field(defaultValue = "true", getter = "getEnableBluetooth", id = 3)
    private boolean zzk;
    @SafeParcelable$Field(defaultValue = "true", getter = "getEnableBle", id = 4)
    private boolean zzl;
    @SafeParcelable$Field(defaultValue = "false", getter = "getForwardUnrecognizedBluetoothDevices", id = 2)
    private boolean zzw;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    private DiscoveryOptions() {
        this.zzw = false;
        this.zzk = true;
        this.zzl = true;
    }
    
    @Deprecated
    public DiscoveryOptions(final Strategy zzh) {
        this.zzw = false;
        this.zzk = true;
        this.zzl = true;
        this.zzh = zzh;
    }
    
    @SafeParcelable$Constructor
    DiscoveryOptions(@SafeParcelable$Param(id = 1) final Strategy zzh, @SafeParcelable$Param(id = 2) final boolean zzw, @SafeParcelable$Param(id = 3) final boolean zzk, @SafeParcelable$Param(id = 4) final boolean zzl) {
        this.zzw = false;
        this.zzk = true;
        this.zzl = true;
        this.zzh = zzh;
        this.zzw = zzw;
        this.zzk = zzk;
        this.zzl = zzl;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof DiscoveryOptions)) {
                return false;
            }
            final DiscoveryOptions discoveryOptions = (DiscoveryOptions)o;
            if (!Objects.equal((Object)this.zzh, (Object)discoveryOptions.zzh) || !Objects.equal((Object)this.zzw, (Object)discoveryOptions.zzw) || !Objects.equal((Object)this.zzk, (Object)discoveryOptions.zzk) || !Objects.equal((Object)this.zzl, (Object)discoveryOptions.zzl)) {
                return false;
            }
        }
        return true;
    }
    
    public final Strategy getStrategy() {
        return this.zzh;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzh, this.zzw, this.zzk, this.zzl });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.getStrategy(), n, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzw);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzk);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzl);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public static final class Builder
    {
        private final DiscoveryOptions zzx;
        
        public Builder() {
            this.zzx = new DiscoveryOptions((zzf)null);
        }
        
        public Builder(final DiscoveryOptions discoveryOptions) {
            (this.zzx = new DiscoveryOptions((zzf)null)).zzh = discoveryOptions.zzh;
            this.zzx.zzw = discoveryOptions.zzw;
            this.zzx.zzk = discoveryOptions.zzk;
            this.zzx.zzl = discoveryOptions.zzl;
        }
        
        public final DiscoveryOptions build() {
            return this.zzx;
        }
        
        public final Builder setStrategy(final Strategy strategy) {
            this.zzx.zzh = strategy;
            return this;
        }
    }
}
