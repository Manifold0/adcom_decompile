package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.games.internal.zzd;

@Class(creator = "PlayerStatsEntityCreator")
@Reserved({1000})
public final class zza extends zzd implements PlayerStats {
    public static final Creator<zza> CREATOR = new zzb();
    @Field(getter = "getAverageSessionLength", id = 1)
    private final float zzre;
    @Field(getter = "getChurnProbability", id = 2)
    private final float zzrf;
    @Field(getter = "getDaysSinceLastPlayed", id = 3)
    private final int zzrg;
    @Field(getter = "getNumberOfPurchases", id = 4)
    private final int zzrh;
    @Field(getter = "getNumberOfSessions", id = 5)
    private final int zzri;
    @Field(getter = "getSessionPercentile", id = 6)
    private final float zzrj;
    @Field(getter = "getSpendPercentile", id = 7)
    private final float zzrk;
    @Field(getter = "getRawValues", id = 8)
    private final Bundle zzrl;
    @Field(getter = "getSpendProbability", id = 9)
    private final float zzrm;
    @Field(getter = "getHighSpenderProbability", id = 10)
    private final float zzrn;
    @Field(getter = "getTotalSpendNext28Days", id = 11)
    private final float zzro;

    @Constructor
    zza(@Param(id = 1) float f, @Param(id = 2) float f2, @Param(id = 3) int i, @Param(id = 4) int i2, @Param(id = 5) int i3, @Param(id = 6) float f3, @Param(id = 7) float f4, @Param(id = 8) Bundle bundle, @Param(id = 9) float f5, @Param(id = 10) float f6, @Param(id = 11) float f7) {
        this.zzre = f;
        this.zzrf = f2;
        this.zzrg = i;
        this.zzrh = i2;
        this.zzri = i3;
        this.zzrj = f3;
        this.zzrk = f4;
        this.zzrl = bundle;
        this.zzrm = f5;
        this.zzrn = f6;
        this.zzro = f7;
    }

    public zza(PlayerStats playerStats) {
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

    static int zza(PlayerStats playerStats) {
        return Objects.hashCode(new Object[]{Float.valueOf(playerStats.getAverageSessionLength()), Float.valueOf(playerStats.getChurnProbability()), Integer.valueOf(playerStats.getDaysSinceLastPlayed()), Integer.valueOf(playerStats.getNumberOfPurchases()), Integer.valueOf(playerStats.getNumberOfSessions()), Float.valueOf(playerStats.getSessionPercentile()), Float.valueOf(playerStats.getSpendPercentile()), Float.valueOf(playerStats.getSpendProbability()), Float.valueOf(playerStats.getHighSpenderProbability()), Float.valueOf(playerStats.getTotalSpendNext28Days())});
    }

    static boolean zza(PlayerStats playerStats, Object obj) {
        if (!(obj instanceof PlayerStats)) {
            return false;
        }
        if (playerStats == obj) {
            return true;
        }
        PlayerStats playerStats2 = (PlayerStats) obj;
        return Objects.equal(Float.valueOf(playerStats2.getAverageSessionLength()), Float.valueOf(playerStats.getAverageSessionLength())) && Objects.equal(Float.valueOf(playerStats2.getChurnProbability()), Float.valueOf(playerStats.getChurnProbability())) && Objects.equal(Integer.valueOf(playerStats2.getDaysSinceLastPlayed()), Integer.valueOf(playerStats.getDaysSinceLastPlayed())) && Objects.equal(Integer.valueOf(playerStats2.getNumberOfPurchases()), Integer.valueOf(playerStats.getNumberOfPurchases())) && Objects.equal(Integer.valueOf(playerStats2.getNumberOfSessions()), Integer.valueOf(playerStats.getNumberOfSessions())) && Objects.equal(Float.valueOf(playerStats2.getSessionPercentile()), Float.valueOf(playerStats.getSessionPercentile())) && Objects.equal(Float.valueOf(playerStats2.getSpendPercentile()), Float.valueOf(playerStats.getSpendPercentile())) && Objects.equal(Float.valueOf(playerStats2.getSpendProbability()), Float.valueOf(playerStats.getSpendProbability())) && Objects.equal(Float.valueOf(playerStats2.getHighSpenderProbability()), Float.valueOf(playerStats.getHighSpenderProbability())) && Objects.equal(Float.valueOf(playerStats2.getTotalSpendNext28Days()), Float.valueOf(playerStats.getTotalSpendNext28Days()));
    }

    static String zzb(PlayerStats playerStats) {
        return Objects.toStringHelper(playerStats).add("AverageSessionLength", Float.valueOf(playerStats.getAverageSessionLength())).add("ChurnProbability", Float.valueOf(playerStats.getChurnProbability())).add("DaysSinceLastPlayed", Integer.valueOf(playerStats.getDaysSinceLastPlayed())).add("NumberOfPurchases", Integer.valueOf(playerStats.getNumberOfPurchases())).add("NumberOfSessions", Integer.valueOf(playerStats.getNumberOfSessions())).add("SessionPercentile", Float.valueOf(playerStats.getSessionPercentile())).add("SpendPercentile", Float.valueOf(playerStats.getSpendPercentile())).add("SpendProbability", Float.valueOf(playerStats.getSpendProbability())).add("HighSpenderProbability", Float.valueOf(playerStats.getHighSpenderProbability())).add("TotalSpendNext28Days", Float.valueOf(playerStats.getTotalSpendNext28Days())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final /* bridge */ /* synthetic */ Object freeze() {
        if (this != null) {
            return this;
        }
        throw null;
    }

    public final float getAverageSessionLength() {
        return this.zzre;
    }

    public final float getChurnProbability() {
        return this.zzrf;
    }

    public final int getDaysSinceLastPlayed() {
        return this.zzrg;
    }

    public final float getHighSpenderProbability() {
        return this.zzrn;
    }

    public final int getNumberOfPurchases() {
        return this.zzrh;
    }

    public final int getNumberOfSessions() {
        return this.zzri;
    }

    public final float getSessionPercentile() {
        return this.zzrj;
    }

    public final float getSpendPercentile() {
        return this.zzrk;
    }

    public final float getSpendProbability() {
        return this.zzrm;
    }

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

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 1, getAverageSessionLength());
        SafeParcelWriter.writeFloat(parcel, 2, getChurnProbability());
        SafeParcelWriter.writeInt(parcel, 3, getDaysSinceLastPlayed());
        SafeParcelWriter.writeInt(parcel, 4, getNumberOfPurchases());
        SafeParcelWriter.writeInt(parcel, 5, getNumberOfSessions());
        SafeParcelWriter.writeFloat(parcel, 6, getSessionPercentile());
        SafeParcelWriter.writeFloat(parcel, 7, getSpendPercentile());
        SafeParcelWriter.writeBundle(parcel, 8, this.zzrl, false);
        SafeParcelWriter.writeFloat(parcel, 9, getSpendProbability());
        SafeParcelWriter.writeFloat(parcel, 10, getHighSpenderProbability());
        SafeParcelWriter.writeFloat(parcel, 11, getTotalSpendNext28Days());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final Bundle zzcn() {
        return this.zzrl;
    }
}
