// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.util.DataUtils;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.games.Player;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;

public final class LeaderboardScoreEntity implements LeaderboardScore
{
    private final long rawScore;
    private final String scoreTag;
    private final long zznc;
    private final String zznd;
    private final String zzne;
    private final long zznf;
    private final String zzng;
    private final Uri zznh;
    private final Uri zzni;
    private final PlayerEntity zznj;
    private final String zznk;
    private final String zznl;
    
    public LeaderboardScoreEntity(final LeaderboardScore leaderboardScore) {
        this.zznc = leaderboardScore.getRank();
        this.zznd = (String)Preconditions.checkNotNull((Object)leaderboardScore.getDisplayRank());
        this.zzne = (String)Preconditions.checkNotNull((Object)leaderboardScore.getDisplayScore());
        this.rawScore = leaderboardScore.getRawScore();
        this.zznf = leaderboardScore.getTimestampMillis();
        this.zzng = leaderboardScore.getScoreHolderDisplayName();
        this.zznh = leaderboardScore.getScoreHolderIconImageUri();
        this.zzni = leaderboardScore.getScoreHolderHiResImageUri();
        final Player scoreHolder = leaderboardScore.getScoreHolder();
        PlayerEntity zznj;
        if (scoreHolder == null) {
            zznj = null;
        }
        else {
            zznj = (PlayerEntity)scoreHolder.freeze();
        }
        this.zznj = zznj;
        this.scoreTag = leaderboardScore.getScoreTag();
        this.zznk = leaderboardScore.getScoreHolderIconImageUrl();
        this.zznl = leaderboardScore.getScoreHolderHiResImageUrl();
    }
    
    static int zza(final LeaderboardScore leaderboardScore) {
        return Objects.hashCode(new Object[] { leaderboardScore.getRank(), leaderboardScore.getDisplayRank(), leaderboardScore.getRawScore(), leaderboardScore.getDisplayScore(), leaderboardScore.getTimestampMillis(), leaderboardScore.getScoreHolderDisplayName(), leaderboardScore.getScoreHolderIconImageUri(), leaderboardScore.getScoreHolderHiResImageUri(), leaderboardScore.getScoreHolder() });
    }
    
    static boolean zza(final LeaderboardScore leaderboardScore, final Object o) {
        if (o instanceof LeaderboardScore) {
            if (leaderboardScore == o) {
                return true;
            }
            final LeaderboardScore leaderboardScore2 = (LeaderboardScore)o;
            if (Objects.equal((Object)leaderboardScore2.getRank(), (Object)leaderboardScore.getRank()) && Objects.equal((Object)leaderboardScore2.getDisplayRank(), (Object)leaderboardScore.getDisplayRank()) && Objects.equal((Object)leaderboardScore2.getRawScore(), (Object)leaderboardScore.getRawScore()) && Objects.equal((Object)leaderboardScore2.getDisplayScore(), (Object)leaderboardScore.getDisplayScore()) && Objects.equal((Object)leaderboardScore2.getTimestampMillis(), (Object)leaderboardScore.getTimestampMillis()) && Objects.equal((Object)leaderboardScore2.getScoreHolderDisplayName(), (Object)leaderboardScore.getScoreHolderDisplayName()) && Objects.equal((Object)leaderboardScore2.getScoreHolderIconImageUri(), (Object)leaderboardScore.getScoreHolderIconImageUri()) && Objects.equal((Object)leaderboardScore2.getScoreHolderHiResImageUri(), (Object)leaderboardScore.getScoreHolderHiResImageUri()) && Objects.equal((Object)leaderboardScore2.getScoreHolder(), (Object)leaderboardScore.getScoreHolder()) && Objects.equal((Object)leaderboardScore2.getScoreTag(), (Object)leaderboardScore.getScoreTag())) {
                return true;
            }
        }
        return false;
    }
    
    static String zzb(final LeaderboardScore leaderboardScore) {
        final Objects$ToStringHelper add = Objects.toStringHelper((Object)leaderboardScore).add("Rank", (Object)leaderboardScore.getRank()).add("DisplayRank", (Object)leaderboardScore.getDisplayRank()).add("Score", (Object)leaderboardScore.getRawScore()).add("DisplayScore", (Object)leaderboardScore.getDisplayScore()).add("Timestamp", (Object)leaderboardScore.getTimestampMillis()).add("DisplayName", (Object)leaderboardScore.getScoreHolderDisplayName()).add("IconImageUri", (Object)leaderboardScore.getScoreHolderIconImageUri()).add("IconImageUrl", (Object)leaderboardScore.getScoreHolderIconImageUrl()).add("HiResImageUri", (Object)leaderboardScore.getScoreHolderHiResImageUri()).add("HiResImageUrl", (Object)leaderboardScore.getScoreHolderHiResImageUrl());
        Object scoreHolder;
        if (leaderboardScore.getScoreHolder() == null) {
            scoreHolder = null;
        }
        else {
            scoreHolder = leaderboardScore.getScoreHolder();
        }
        return add.add("Player", scoreHolder).add("ScoreTag", (Object)leaderboardScore.getScoreTag()).toString();
    }
    
    @Override
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    @Override
    public final String getDisplayRank() {
        return this.zznd;
    }
    
    @Override
    public final void getDisplayRank(final CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zznd, charArrayBuffer);
    }
    
    @Override
    public final String getDisplayScore() {
        return this.zzne;
    }
    
    @Override
    public final void getDisplayScore(final CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzne, charArrayBuffer);
    }
    
    @Override
    public final long getRank() {
        return this.zznc;
    }
    
    @Override
    public final long getRawScore() {
        return this.rawScore;
    }
    
    @Override
    public final Player getScoreHolder() {
        return this.zznj;
    }
    
    @Override
    public final String getScoreHolderDisplayName() {
        if (this.zznj == null) {
            return this.zzng;
        }
        return this.zznj.getDisplayName();
    }
    
    @Override
    public final void getScoreHolderDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.zznj == null) {
            DataUtils.copyStringToBuffer(this.zzng, charArrayBuffer);
            return;
        }
        this.zznj.getDisplayName(charArrayBuffer);
    }
    
    @Override
    public final Uri getScoreHolderHiResImageUri() {
        if (this.zznj == null) {
            return this.zzni;
        }
        return this.zznj.getHiResImageUri();
    }
    
    @Override
    public final String getScoreHolderHiResImageUrl() {
        if (this.zznj == null) {
            return this.zznl;
        }
        return this.zznj.getHiResImageUrl();
    }
    
    @Override
    public final Uri getScoreHolderIconImageUri() {
        if (this.zznj == null) {
            return this.zznh;
        }
        return this.zznj.getIconImageUri();
    }
    
    @Override
    public final String getScoreHolderIconImageUrl() {
        if (this.zznj == null) {
            return this.zznk;
        }
        return this.zznj.getIconImageUrl();
    }
    
    @Override
    public final String getScoreTag() {
        return this.scoreTag;
    }
    
    @Override
    public final long getTimestampMillis() {
        return this.zznf;
    }
    
    @Override
    public final int hashCode() {
        return zza(this);
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    @Override
    public final String toString() {
        return zzb(this);
    }
}
