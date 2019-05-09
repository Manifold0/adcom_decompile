// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import javax.annotation.concurrent.Immutable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "StrategyCreator")
@Immutable
public class Strategy extends AbstractSafeParcelable
{
    public static final Strategy BLE_ONLY;
    public static final Parcelable$Creator<Strategy> CREATOR;
    public static final Strategy DEFAULT;
    public static final int DISCOVERY_MODE_BROADCAST = 1;
    public static final int DISCOVERY_MODE_DEFAULT = 3;
    public static final int DISCOVERY_MODE_SCAN = 2;
    public static final int DISTANCE_TYPE_DEFAULT = 0;
    public static final int DISTANCE_TYPE_EARSHOT = 1;
    public static final int TTL_SECONDS_DEFAULT = 300;
    public static final int TTL_SECONDS_INFINITE = Integer.MAX_VALUE;
    public static final int TTL_SECONDS_MAX = 86400;
    @Deprecated
    private static final Strategy zzfm;
    @SafeParcelable$VersionField(id = 1000)
    private final int zzex;
    @Deprecated
    @SafeParcelable$Field(id = 1)
    private final int zzfn;
    @SafeParcelable$Field(id = 2)
    private final int zzfo;
    @SafeParcelable$Field(id = 3)
    private final int zzfp;
    @Deprecated
    @SafeParcelable$Field(id = 4)
    private final boolean zzfq;
    @SafeParcelable$Field(getter = "getDiscoveryMedium", id = 5)
    private final int zzfr;
    @SafeParcelable$Field(getter = "getDiscoveryMode", id = 6)
    private final int zzfs;
    @SafeParcelable$Field(getter = "getBackgroundScanMode", id = 7)
    private final int zzft;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
        DEFAULT = new Builder().build();
        zzfm = (BLE_ONLY = new Builder().zze(2).setTtlSeconds(Integer.MAX_VALUE).build());
    }
    
    @SafeParcelable$Constructor
    Strategy(@SafeParcelable$Param(id = 1000) final int zzex, @SafeParcelable$Param(id = 1) final int zzfn, @SafeParcelable$Param(id = 2) final int zzfo, @SafeParcelable$Param(id = 3) final int zzfp, @SafeParcelable$Param(id = 4) final boolean zzfq, @SafeParcelable$Param(id = 5) final int zzfr, @SafeParcelable$Param(id = 6) final int zzfs, @SafeParcelable$Param(id = 7) final int zzft) {
        this.zzex = zzex;
        this.zzfn = zzfn;
        if (zzfn == 0) {
            this.zzfs = zzfs;
        }
        else {
            switch (zzfn) {
                default: {
                    this.zzfs = 3;
                    break;
                }
                case 2: {
                    this.zzfs = 1;
                    break;
                }
                case 3: {
                    this.zzfs = 2;
                    break;
                }
            }
        }
        this.zzfp = zzfp;
        this.zzfq = zzfq;
        if (zzfq) {
            this.zzfr = 2;
            this.zzfo = Integer.MAX_VALUE;
        }
        else {
            this.zzfo = zzfo;
            switch (zzfr) {
                default: {
                    this.zzfr = zzfr;
                    break;
                }
                case -1:
                case 0:
                case 1:
                case 6: {
                    this.zzfr = -1;
                    break;
                }
            }
        }
        this.zzft = zzft;
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof Strategy)) {
                return false;
            }
            final Strategy strategy = (Strategy)o;
            if (this.zzex != strategy.zzex || this.zzfs != strategy.zzfs || this.zzfo != strategy.zzfo || this.zzfp != strategy.zzfp || this.zzfr != strategy.zzfr || this.zzft != strategy.zzft) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        return ((((this.zzex * 31 + this.zzfs) * 31 + this.zzfo) * 31 + this.zzfp) * 31 + this.zzfr) * 31 + this.zzft;
    }
    
    public String toString() {
        final int zzfo = this.zzfo;
        final int zzfp = this.zzfp;
        String string = null;
        switch (zzfp) {
            default: {
                string = new StringBuilder(19).append("UNKNOWN:").append(zzfp).toString();
                break;
            }
            case 0: {
                string = "DEFAULT";
                break;
            }
            case 1: {
                string = "EARSHOT";
                break;
            }
        }
        final int zzfr = this.zzfr;
        String s;
        if (zzfr == -1) {
            s = "DEFAULT";
        }
        else {
            final ArrayList<String> list = new ArrayList<String>();
            if ((zzfr & 0x4) > 0) {
                list.add("ULTRASOUND");
            }
            if ((zzfr & 0x2) > 0) {
                list.add("BLE");
            }
            if (list.isEmpty()) {
                s = new StringBuilder(19).append("UNKNOWN:").append(zzfr).toString();
            }
            else {
                s = list.toString();
            }
        }
        final int zzfs = this.zzfs;
        String s2;
        if (zzfs == 3) {
            s2 = "DEFAULT";
        }
        else {
            final ArrayList<String> list2 = new ArrayList<String>();
            if ((zzfs & 0x1) > 0) {
                list2.add("BROADCAST");
            }
            if ((zzfs & 0x2) > 0) {
                list2.add("SCAN");
            }
            if (list2.isEmpty()) {
                s2 = new StringBuilder(19).append("UNKNOWN:").append(zzfs).toString();
            }
            else {
                s2 = list2.toString();
            }
        }
        final int zzft = this.zzft;
        String string2 = null;
        switch (zzft) {
            default: {
                string2 = new StringBuilder(20).append("UNKNOWN: ").append(zzft).toString();
                break;
            }
            case 0: {
                string2 = "DEFAULT";
                break;
            }
            case 1: {
                string2 = "ALWAYS_ON";
                break;
            }
        }
        return new StringBuilder(String.valueOf(string).length() + 102 + String.valueOf(s).length() + String.valueOf(s2).length() + String.valueOf(string2).length()).append("Strategy{ttlSeconds=").append(zzfo).append(", distanceType=").append(string).append(", discoveryMedium=").append(s).append(", discoveryMode=").append(s2).append(", backgroundScanMode=").append(string2).append('}').toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzfn);
        SafeParcelWriter.writeInt(parcel, 2, this.zzfo);
        SafeParcelWriter.writeInt(parcel, 3, this.zzfp);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzfq);
        SafeParcelWriter.writeInt(parcel, 5, this.zzfr);
        SafeParcelWriter.writeInt(parcel, 6, this.zzfs);
        SafeParcelWriter.writeInt(parcel, 7, this.zzft);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzex);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final int zzae() {
        return this.zzft;
    }
    
    public static class Builder
    {
        private int zzfu;
        private int zzfv;
        private int zzfw;
        private int zzfx;
        private int zzfy;
        
        public Builder() {
            this.zzfu = 3;
            this.zzfv = 300;
            this.zzfw = 0;
            this.zzfx = -1;
            this.zzfy = 0;
        }
        
        public Strategy build() {
            if (this.zzfx == 2 && this.zzfw == 1) {
                throw new IllegalStateException("Cannot set EARSHOT with BLE only mode.");
            }
            return new Strategy(2, 0, this.zzfv, this.zzfw, false, this.zzfx, this.zzfu, 0);
        }
        
        public Builder setDiscoveryMode(final int zzfu) {
            this.zzfu = zzfu;
            return this;
        }
        
        public Builder setDistanceType(final int zzfw) {
            this.zzfw = zzfw;
            return this;
        }
        
        public Builder setTtlSeconds(final int zzfv) {
            Preconditions.checkArgument(zzfv == Integer.MAX_VALUE || (zzfv > 0 && zzfv <= 86400), "mTtlSeconds(%d) must either be TTL_SECONDS_INFINITE, or it must be between 1 and TTL_SECONDS_MAX(%d) inclusive", new Object[] { zzfv, 86400 });
            this.zzfv = zzfv;
            return this;
        }
        
        public final Builder zze(final int n) {
            this.zzfx = 2;
            return this;
        }
    }
}
