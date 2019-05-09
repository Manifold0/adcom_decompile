package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class LeaderboardScoreRef extends DataBufferRef implements LeaderboardScore {
    private final PlayerRef zznm;

    LeaderboardScoreRef(DataHolder dataHolder, int i) {
        super(dataHolder, i);
        this.zznm = new PlayerRef(dataHolder, i);
    }

    public final boolean equals(Object obj) {
        return LeaderboardScoreEntity.zza(this, obj);
    }

    public final /* synthetic */ Object freeze() {
        return new LeaderboardScoreEntity(this);
    }

    public final String getDisplayRank() {
        return getString("display_rank");
    }

    public final void getDisplayRank(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("display_rank", charArrayBuffer);
    }

    public final String getDisplayScore() {
        return getString("display_score");
    }

    public final void getDisplayScore(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("display_score", charArrayBuffer);
    }

    public final long getRank() {
        return getLong("rank");
    }

    public final long getRawScore() {
        return getLong("raw_score");
    }

    public final Player getScoreHolder() {
        return hasNull("external_player_id") ? null : this.zznm;
    }

    public final String getScoreHolderDisplayName() {
        return hasNull("external_player_id") ? getString("default_display_name") : this.zznm.getDisplayName();
    }

    public final void getScoreHolderDisplayName(CharArrayBuffer charArrayBuffer) {
        if (hasNull("external_player_id")) {
            copyToBuffer("default_display_name", charArrayBuffer);
        } else {
            this.zznm.getDisplayName(charArrayBuffer);
        }
    }

    public final Uri getScoreHolderHiResImageUri() {
        return hasNull("external_player_id") ? null : this.zznm.getHiResImageUri();
    }

    public final String getScoreHolderHiResImageUrl() {
        return hasNull("external_player_id") ? null : this.zznm.getHiResImageUrl();
    }

    public final Uri getScoreHolderIconImageUri() {
        return hasNull("external_player_id") ? parseUri("default_display_image_uri") : this.zznm.getIconImageUri();
    }

    public final String getScoreHolderIconImageUrl() {
        return hasNull("external_player_id") ? getString("default_display_image_url") : this.zznm.getIconImageUrl();
    }

    public final String getScoreTag() {
        return getString("score_tag");
    }

    public final long getTimestampMillis() {
        return getLong("achieved_timestamp");
    }

    public final int hashCode() {
        return LeaderboardScoreEntity.zza(this);
    }

    public final String toString() {
        return LeaderboardScoreEntity.zzb(this);
    }
}
