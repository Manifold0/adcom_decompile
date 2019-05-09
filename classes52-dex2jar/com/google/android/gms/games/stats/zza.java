// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.stats;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "PlayerStatsEntityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zza extends zzd implements PlayerStats
{
    public static final Parcelable$Creator<zza> CREATOR;
    @SafeParcelable$Field(getter = "getAverageSessionLength", id = 1)
    private final float zzre;
    @SafeParcelable$Field(getter = "getChurnProbability", id = 2)
    private final float zzrf;
    @SafeParcelable$Field(getter = "getDaysSinceLastPlayed", id = 3)
    private final int zzrg;
    @SafeParcelable$Field(getter = "getNumberOfPurchases", id = 4)
    private final int zzrh;
    @SafeParcelable$Field(getter = "getNumberOfSessions", id = 5)
    private final int zzri;
    @SafeParcelable$Field(getter = "getSessionPercentile", id = 6)
    private final float zzrj;
    @SafeParcelable$Field(getter = "getSpendPercentile", id = 7)
    private final float zzrk;
    @SafeParcelable$Field(getter = "getRawValues", id = 8)
    private final Bundle zzrl;
    @SafeParcelable$Field(getter = "getSpendProbability", id = 9)
    private final float zzrm;
    @SafeParcelable$Field(getter = "getHighSpenderProbability", id = 10)
    private final float zzrn;
    @SafeParcelable$Field(getter = "getTotalSpendNext28Days", id = 11)
    private final float zzro;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    @SafeParcelable$Constructor
    zza(@SafeParcelable$Param(id = 1) final float zzre, @SafeParcelable$Param(id = 2) final float zzrf, @SafeParcelable$Param(id = 3) final int zzrg, @SafeParcelable$Param(id = 4) final int zzrh, @SafeParcelable$Param(id = 5) final int zzri, @SafeParcelable$Param(id = 6) final float zzrj, @SafeParcelable$Param(id = 7) final float zzrk, @SafeParcelable$Param(id = 8) final Bundle zzrl, @SafeParcelable$Param(id = 9) final float zzrm, @SafeParcelable$Param(id = 10) final float zzrn, @SafeParcelable$Param(id = 11) final float zzro) {
        this.zzre = zzre;
        this.zzrf = zzrf;
        this.zzrg = zzrg;
        this.zzrh = zzrh;
        this.zzri = zzri;
        this.zzrj = zzrj;
        this.zzrk = zzrk;
        this.zzrl = zzrl;
        this.zzrm = zzrm;
        this.zzrn = zzrn;
        this.zzro = zzro;
    }
    
    public zza(final PlayerStats playerStats) {
        this.zzre = playerStats.getAverageSessionLength();
        this.zzrf = playerStats.getChurnProbability();
        this.zzrg = playerStats.getDaysSinceLastPlayed();
        this.zzrh = playerStats.getNumberOfPurchases();
        this.zzri = playerStats.getNumberOfSessions();
        this.zzrj = playerStats.getSessionPercentile();
        this.zzrk = playerStats.getSpendPercentile();
        this.zzrm = playerStats.getSpendProbability();
        this.zzrn = playerStats.getHighSpenderProbability();
        this.zzro = playerStats.getTotalSpendNext28Days();
        this.zzrl = playerStats.zzcn();
    }
    
    static int zza(final PlayerStats playerStats) {
        return Objects.hashCode(new Object[] { playerStats.getAverageSessionLength(), playerStats.getChurnProbability(), playerStats.getDaysSinceLastPlayed(), playerStats.getNumberOfPurchases(), playerStats.getNumberOfSessions(), playerStats.getSessionPercentile(), playerStats.getSpendPercentile(), playerStats.getSpendProbability(), playerStats.getHighSpenderProbability(), playerStats.getTotalSpendNext28Days() });
    }
    
    static boolean zza(final PlayerStats playerStats, final Object o) {
        if (o instanceof PlayerStats) {
            if (playerStats == o) {
                return true;
            }
            final PlayerStats playerStats2 = (PlayerStats)o;
            if (Objects.equal((Object)playerStats2.getAverageSessionLength(), (Object)playerStats.getAverageSessionLength()) && Objects.equal((Object)playerStats2.getChurnProbability(), (Object)playerStats.getChurnProbability()) && Objects.equal((Object)playerStats2.getDaysSinceLastPlayed(), (Object)playerStats.getDaysSinceLastPlayed()) && Objects.equal((Object)playerStats2.getNumberOfPurchases(), (Object)playerStats.getNumberOfPurchases()) && Objects.equal((Object)playerStats2.getNumberOfSessions(), (Object)playerStats.getNumberOfSessions()) && Objects.equal((Object)playerStats2.getSessionPercentile(), (Object)playerStats.getSessionPercentile()) && Objects.equal((Object)playerStats2.getSpendPercentile(), (Object)playerStats.getSpendPercentile()) && Objects.equal((Object)playerStats2.getSpendProbability(), (Object)playerStats.getSpendProbability()) && Objects.equal((Object)playerStats2.getHighSpenderProbability(), (Object)playerStats.getHighSpenderProbability()) && Objects.equal((Object)playerStats2.getTotalSpendNext28Days(), (Object)playerStats.getTotalSpendNext28Days())) {
                return true;
            }
        }
        return false;
    }
    
    static String zzb(final PlayerStats playerStats) {
        return Objects.toStringHelper((Object)playerStats).add("AverageSessionLength", (Object)playerStats.getAverageSessionLength()).add("ChurnProbability", (Object)playerStats.getChurnProbability()).add("DaysSinceLastPlayed", (Object)playerStats.getDaysSinceLastPlayed()).add("NumberOfPurchases", (Object)playerStats.getNumberOfPurchases()).add("NumberOfSessions", (Object)playerStats.getNumberOfSessions()).add("SessionPercentile", (Object)playerStats.getSessionPercentile()).add("SpendPercentile", (Object)playerStats.getSpendPercentile()).add("SpendProbability", (Object)playerStats.getSpendProbability()).add("HighSpenderProbability", (Object)playerStats.getHighSpenderProbability()).add("TotalSpendNext28Days", (Object)playerStats.getTotalSpendNext28Days()).toString();
    }
    
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    @Override
    public final float getAverageSessionLength() {
        return this.zzre;
    }
    
    @Override
    public final float getChurnProbability() {
        return this.zzrf;
    }
    
    @Override
    public final int getDaysSinceLastPlayed() {
        return this.zzrg;
    }
    
    @Override
    public final float getHighSpenderProbability() {
        return this.zzrn;
    }
    
    @Override
    public final int getNumberOfPurchases() {
        return this.zzrh;
    }
    
    @Override
    public final int getNumberOfSessions() {
        return this.zzri;
    }
    
    @Override
    public final float getSessionPercentile() {
        return this.zzrj;
    }
    
    @Override
    public final float getSpendPercentile() {
        return this.zzrk;
    }
    
    @Override
    public final float getSpendProbability() {
        return this.zzrm;
    }
    
    @Override
    public final float getTotalSpendNext28Days() {
        return this.zzro;
    }
    
    public final int hashCode() {
        return zza(this);
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    public final String toString() {
        return zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 1, this.getAverageSessionLength());
        SafeParcelWriter.writeFloat(parcel, 2, this.getChurnProbability());
        SafeParcelWriter.writeInt(parcel, 3, this.getDaysSinceLastPlayed());
        SafeParcelWriter.writeInt(parcel, 4, this.getNumberOfPurchases());
        SafeParcelWriter.writeInt(parcel, 5, this.getNumberOfSessions());
        SafeParcelWriter.writeFloat(parcel, 6, this.getSessionPercentile());
        SafeParcelWriter.writeFloat(parcel, 7, this.getSpendPercentile());
        SafeParcelWriter.writeBundle(parcel, 8, this.zzrl, false);
        SafeParcelWriter.writeFloat(parcel, 9, this.getSpendProbability());
        SafeParcelWriter.writeFloat(parcel, 10, this.getHighSpenderProbability());
        SafeParcelWriter.writeFloat(parcel, 11, this.getTotalSpendNext28Days());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @Override
    public final Bundle zzcn() {
        return this.zzrl;
    }
}
