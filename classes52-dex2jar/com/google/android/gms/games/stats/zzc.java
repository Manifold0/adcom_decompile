// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.stats;

import com.google.android.gms.common.internal.Asserts;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import com.google.android.gms.common.data.DataBufferRef;

public final class zzc extends DataBufferRef implements PlayerStats
{
    private Bundle zzrl;
    
    zzc(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final boolean equals(final Object o) {
        return zza.zza(this, o);
    }
    
    public final /* synthetic */ Object freeze() {
        return new zza(this);
    }
    
    public final float getAverageSessionLength() {
        return this.getFloat("ave_session_length_minutes");
    }
    
    public final float getChurnProbability() {
        return this.getFloat("churn_probability");
    }
    
    public final int getDaysSinceLastPlayed() {
        return this.getInteger("days_since_last_played");
    }
    
    public final float getHighSpenderProbability() {
        if (!this.hasColumn("high_spender_probability")) {
            return -1.0f;
        }
        return this.getFloat("high_spender_probability");
    }
    
    public final int getNumberOfPurchases() {
        return this.getInteger("num_purchases");
    }
    
    public final int getNumberOfSessions() {
        return this.getInteger("num_sessions");
    }
    
    public final float getSessionPercentile() {
        return this.getFloat("num_sessions_percentile");
    }
    
    public final float getSpendPercentile() {
        return this.getFloat("spend_percentile");
    }
    
    public final float getSpendProbability() {
        if (!this.hasColumn("spend_probability")) {
            return -1.0f;
        }
        return this.getFloat("spend_probability");
    }
    
    public final float getTotalSpendNext28Days() {
        if (!this.hasColumn("total_spend_next_28_days")) {
            return -1.0f;
        }
        return this.getFloat("total_spend_next_28_days");
    }
    
    public final int hashCode() {
        return zza.zza(this);
    }
    
    public final String toString() {
        return zza.zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((zza)this.freeze()).writeToParcel(parcel, n);
    }
    
    public final Bundle zzcn() {
        int i = 0;
        if (this.zzrl != null) {
            return this.zzrl;
        }
        this.zzrl = new Bundle();
        final String string = this.getString("unknown_raw_keys");
        final String string2 = this.getString("unknown_raw_values");
        if (string != null && string2 != null) {
            final String[] split = string.split(",");
            final String[] split2 = string2.split(",");
            Asserts.checkState(split.length <= split2.length, (Object)"Invalid raw arguments!");
            while (i < split.length) {
                this.zzrl.putString(split[i], split2[i]);
                ++i;
            }
        }
        return this.zzrl;
    }
}
