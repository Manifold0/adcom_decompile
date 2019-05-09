// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBufferRef;

public final class zzc extends DataBufferRef implements LeaderboardVariant
{
    zzc(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    public final boolean equals(final Object o) {
        return zzb.zza(this, o);
    }
    
    public final int getCollection() {
        return this.getInteger("collection");
    }
    
    public final String getDisplayPlayerRank() {
        return this.getString("player_display_rank");
    }
    
    public final String getDisplayPlayerScore() {
        return this.getString("player_display_score");
    }
    
    public final long getNumScores() {
        if (this.hasNull("total_scores")) {
            return -1L;
        }
        return this.getLong("total_scores");
    }
    
    public final long getPlayerRank() {
        if (this.hasNull("player_rank")) {
            return -1L;
        }
        return this.getLong("player_rank");
    }
    
    public final String getPlayerScoreTag() {
        return this.getString("player_score_tag");
    }
    
    public final long getRawPlayerScore() {
        if (this.hasNull("player_raw_score")) {
            return -1L;
        }
        return this.getLong("player_raw_score");
    }
    
    public final int getTimeSpan() {
        return this.getInteger("timespan");
    }
    
    public final boolean hasPlayerInfo() {
        return !this.hasNull("player_raw_score");
    }
    
    public final int hashCode() {
        return zzb.zza(this);
    }
    
    public final String toString() {
        return zzb.zzb(this);
    }
    
    public final String zzcd() {
        return this.getString("top_page_token_next");
    }
    
    public final String zzce() {
        return this.getString("window_page_token_prev");
    }
    
    public final String zzcf() {
        return this.getString("window_page_token_next");
    }
}
