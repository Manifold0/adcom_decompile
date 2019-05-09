package com.google.android.gms.games;

import android.support.annotation.NonNull;
import com.google.android.gms.games.internal.zzq;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult;

final class zzcf implements zzq<LoadMatchesResult> {
    zzcf() {
    }

    public final /* synthetic */ void release(@NonNull Object obj) {
        LoadMatchesResult loadMatchesResult = (LoadMatchesResult) obj;
        if (loadMatchesResult.getMatches() != null) {
            loadMatchesResult.getMatches().release();
        }
    }
}
