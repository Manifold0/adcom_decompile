// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.Games;

abstract class zzay extends zza<Leaderboards.LoadScoresResult>
{
    private zzay(final GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }
}
