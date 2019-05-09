// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Locale;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "StrategyCreator")
@SafeParcelable$Reserved({ 1000 })
public final class Strategy extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<Strategy> CREATOR;
    public static final Strategy P2P_CLUSTER;
    public static final Strategy P2P_POINT_TO_POINT;
    public static final Strategy P2P_STAR;
    @SafeParcelable$Field(getter = "getConnectionType", id = 3)
    private final int zzaj;
    @SafeParcelable$Field(getter = "getTopology", id = 4)
    private final int zzak;
    
    static {
        CREATOR = (Parcelable$Creator)new zzj();
        P2P_CLUSTER = new Strategy(1, 3);
        P2P_STAR = new Strategy(1, 2);
        P2P_POINT_TO_POINT = new Strategy(1, 1);
    }
    
    @SafeParcelable$Constructor
    Strategy(@SafeParcelable$Param(id = 3) final int zzaj, @SafeParcelable$Param(id = 4) final int zzak) {
        this.zzaj = zzaj;
        this.zzak = zzak;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof Strategy)) {
                return false;
            }
            final Strategy strategy = (Strategy)o;
            if (this.zzaj != strategy.zzaj || this.zzak != strategy.zzak) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzaj, this.zzak });
    }
    
    public final String toString() {
        final Locale us = Locale.US;
        String s;
        if (Strategy.P2P_CLUSTER.equals(this)) {
            s = "P2P_CLUSTER";
        }
        else if (Strategy.P2P_STAR.equals(this)) {
            s = "P2P_STAR";
        }
        else if (Strategy.P2P_POINT_TO_POINT.equals(this)) {
            s = "P2P_POINT_TO_POINT";
        }
        else {
            s = "UNKNOWN";
        }
        return String.format(us, "Strategy(%s){connectionType=%d, topology=%d}", s, this.zzaj, this.zzak);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 3, this.zzaj);
        SafeParcelWriter.writeInt(parcel, 4, this.zzak);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
