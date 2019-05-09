package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Asserts;

public final class zzc extends DataBufferRef implements PlayerStats {
    private Bundle zzrl;

    zzc(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return zza.zza(this, obj);
    }

    public final /* synthetic */ Object freeze() {
        return new zza(this);
    }

    public final float getAverageSessionLength() {
        return getFloat("ave_session_length_minutes");
    }

    public final float getChurnProbability() {
        return getFloat("churn_probability");
    }

    public final int getDaysSinceLastPlayed() {
        return getInteger("days_since_last_played");
    }

    public final float getHighSpenderProbability() {
        return !hasColumn("high_spender_probability") ? -1.0f : getFloat("high_spender_probability");
    }

    public final int getNumberOfPurchases() {
        return getInteger("num_purchases");
    }

    public final int getNumberOfSessions() {
        return getInteger("num_sessions");
    }

    public final float getSessionPercentile() {
        return getFloat("num_sessions_percentile");
    }

    public final float getSpendPercentile() {
        return getFloat("spend_percentile");
    }

    public final float getSpendProbability() {
        return !hasColumn("spend_probability") ? -1.0f : getFloat("spend_probability");
    }

    public final float getTotalSpendNext28Days() {
        return !hasColumn("total_spend_next_28_days") ? -1.0f : getFloat("total_spend_next_28_days");
    }

    public final int hashCode() {
        return zza.zza(this);
    }

    public final String toString() {
        return zza.zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((zza) ((PlayerStats) freeze())).writeToParcel(parcel, i);
    }

    public final Bundle zzcn() {
        int i = 0;
        if (this.zzrl != null) {
            return this.zzrl;
        }
        this.zzrl = new Bundle();
        String string = getString("unknown_raw_keys");
        String string2 = getString("unknown_raw_values");
        if (!(string == null || string2 == null)) {
            String[] split = string.split(",");
            String[] split2 = string2.split(",");
            Asserts.checkState(split.length <= split2.length, "Invalid raw arguments!");
            while (i < split.length) {
                this.zzrl.putString(split[i], split2[i]);
                i++;
            }
        }
        return this.zzrl;
    }
}
