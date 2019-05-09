// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.leaderboard;

import java.util.ArrayList;
import com.google.android.gms.common.annotation.KeepName;
import android.net.Uri;
import com.google.android.gms.games.Game;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.data.Freezable;

@VisibleForTesting
public interface Leaderboard extends Freezable<Leaderboard>
{
    public static final int SCORE_ORDER_LARGER_IS_BETTER = 1;
    public static final int SCORE_ORDER_SMALLER_IS_BETTER = 0;
    
    String getDisplayName();
    
    void getDisplayName(final CharArrayBuffer p0);
    
    Game getGame();
    
    Uri getIconImageUri();
    
    @Deprecated
    @KeepName
    String getIconImageUrl();
    
    String getLeaderboardId();
    
    int getScoreOrder();
    
    ArrayList<LeaderboardVariant> getVariants();
}
