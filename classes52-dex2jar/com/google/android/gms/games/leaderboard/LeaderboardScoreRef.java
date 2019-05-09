// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.leaderboard;

import android.net.Uri;
import com.google.android.gms.games.Player;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.common.data.DataBufferRef;

public final class LeaderboardScoreRef extends DataBufferRef implements LeaderboardScore
{
    private final PlayerRef zznm;
    
    LeaderboardScoreRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
        this.zznm = new PlayerRef(dataHolder, n);
    }
    
    public final boolean equals(final Object o) {
        return LeaderboardScoreEntity.zza(this, o);
    }
    
    public final String getDisplayRank() {
        return this.getString("display_rank");
    }
    
    public final void getDisplayRank(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("display_rank", charArrayBuffer);
    }
    
    public final String getDisplayScore() {
        return this.getString("display_score");
    }
    
    public final void getDisplayScore(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("display_score", charArrayBuffer);
    }
    
    public final long getRank() {
        return this.getLong("rank");
    }
    
    public final long getRawScore() {
        return this.getLong("raw_score");
    }
    
    public final Player getScoreHolder() {
        if (this.hasNull("external_player_id")) {
            return null;
        }
        return this.zznm;
    }
    
    public final String getScoreHolderDisplayName() {
        if (this.hasNull("external_player_id")) {
            return this.getString("default_display_name");
        }
        return this.zznm.getDisplayName();
    }
    
    public final void getScoreHolderDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.hasNull("external_player_id")) {
            this.copyToBuffer("default_display_name", charArrayBuffer);
            return;
        }
        this.zznm.getDisplayName(charArrayBuffer);
    }
    
    public final Uri getScoreHolderHiResImageUri() {
        if (this.hasNull("external_player_id")) {
            return null;
        }
        return this.zznm.getHiResImageUri();
    }
    
    public final String getScoreHolderHiResImageUrl() {
        if (this.hasNull("external_player_id")) {
            return null;
        }
        return this.zznm.getHiResImageUrl();
    }
    
    public final Uri getScoreHolderIconImageUri() {
        if (this.hasNull("external_player_id")) {
            return this.parseUri("default_display_image_uri");
        }
        return this.zznm.getIconImageUri();
    }
    
    public final String getScoreHolderIconImageUrl() {
        if (this.hasNull("external_player_id")) {
            return this.getString("default_display_image_url");
        }
        return this.zznm.getIconImageUrl();
    }
    
    public final String getScoreTag() {
        return this.getString("score_tag");
    }
    
    public final long getTimestampMillis() {
        return this.getLong("achieved_timestamp");
    }
    
    public final int hashCode() {
        return LeaderboardScoreEntity.zza(this);
    }
    
    public final String toString() {
        return LeaderboardScoreEntity.zzb(this);
    }
}
