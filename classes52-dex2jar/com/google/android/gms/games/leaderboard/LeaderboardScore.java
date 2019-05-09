// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.annotation.KeepName;
import android.net.Uri;
import com.google.android.gms.games.Player;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.data.Freezable;

@VisibleForTesting
public interface LeaderboardScore extends Freezable<LeaderboardScore>
{
    public static final int LEADERBOARD_RANK_UNKNOWN = -1;
    
    String getDisplayRank();
    
    void getDisplayRank(final CharArrayBuffer p0);
    
    String getDisplayScore();
    
    void getDisplayScore(final CharArrayBuffer p0);
    
    long getRank();
    
    long getRawScore();
    
    Player getScoreHolder();
    
    String getScoreHolderDisplayName();
    
    void getScoreHolderDisplayName(final CharArrayBuffer p0);
    
    Uri getScoreHolderHiResImageUri();
    
    @Deprecated
    @KeepName
    String getScoreHolderHiResImageUrl();
    
    Uri getScoreHolderIconImageUri();
    
    @Deprecated
    @KeepName
    String getScoreHolderIconImageUrl();
    
    String getScoreTag();
    
    long getTimestampMillis();
}
