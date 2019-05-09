package com.google.android.gms.internal.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult;
import com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult;

public final class zzf implements Achievements {
    public final Intent getAchievementsIntent(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzx();
    }

    public final void increment(GoogleApiClient googleApiClient, String str, int i) {
        googleApiClient.execute(new zzl(this, str, googleApiClient, str, i));
    }

    public final PendingResult<UpdateAchievementResult> incrementImmediate(GoogleApiClient googleApiClient, String str, int i) {
        return googleApiClient.execute(new zzm(this, str, googleApiClient, str, i));
    }

    public final PendingResult<LoadAchievementsResult> load(GoogleApiClient googleApiClient, boolean z) {
        return googleApiClient.enqueue(new zzg(this, googleApiClient, z));
    }

    public final void reveal(GoogleApiClient googleApiClient, String str) {
        googleApiClient.execute(new zzh(this, str, googleApiClient, str));
    }

    public final PendingResult<UpdateAchievementResult> revealImmediate(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzi(this, str, googleApiClient, str));
    }

    public final void setSteps(GoogleApiClient googleApiClient, String str, int i) {
        googleApiClient.execute(new zzn(this, str, googleApiClient, str, i));
    }

    public final PendingResult<UpdateAchievementResult> setStepsImmediate(GoogleApiClient googleApiClient, String str, int i) {
        return googleApiClient.execute(new zzo(this, str, googleApiClient, str, i));
    }

    public final void unlock(GoogleApiClient googleApiClient, String str) {
        googleApiClient.execute(new zzj(this, str, googleApiClient, str));
    }

    public final PendingResult<UpdateAchievementResult> unlockImmediate(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzk(this, str, googleApiClient, str));
    }
}
