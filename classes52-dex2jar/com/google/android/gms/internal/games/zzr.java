// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.Games;

abstract class zzr extends zza<Achievements.UpdateAchievementResult>
{
    private final String zzji;
    
    public zzr(final String zzji, final GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzji = zzji;
    }
}
