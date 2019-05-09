package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games.zza;
import com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult;

abstract class zzr extends zza<UpdateAchievementResult> {
    private final String zzji;

    public zzr(String str, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzji = str;
    }

    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzs(this, status);
    }
}
