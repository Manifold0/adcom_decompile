// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public interface GamesMetadata
{
    Game getCurrentGame(final GoogleApiClient p0);
    
    PendingResult<LoadGamesResult> loadGame(final GoogleApiClient p0);
    
    @Deprecated
    public interface LoadGamesResult extends Releasable, Result
    {
        GameBuffer getGames();
    }
}
