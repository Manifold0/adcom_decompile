// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.achievement.Achievements;

public final class zzf implements Achievements
{
    @Override
    public final Intent getAchievementsIntent(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzx();
    }
    
    @Override
    public final void increment(final GoogleApiClient googleApiClient, final String s, final int n) {
        googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzl(this, s, googleApiClient, s, n));
    }
    
    @Override
    public final PendingResult<UpdateAchievementResult> incrementImmediate(final GoogleApiClient googleApiClient, final String s, final int n) {
        return (PendingResult<UpdateAchievementResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzm(this, s, googleApiClient, s, n));
    }
    
    @Override
    public final PendingResult<LoadAchievementsResult> load(final GoogleApiClient googleApiClient, final boolean b) {
        return (PendingResult<LoadAchievementsResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzg(this, googleApiClient, b));
    }
    
    @Override
    public final void reveal(final GoogleApiClient googleApiClient, final String s) {
        googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzh(this, s, googleApiClient, s));
    }
    
    @Override
    public final PendingResult<UpdateAchievementResult> revealImmediate(final GoogleApiClient googleApiClient, final String s) {
        return (PendingResult<UpdateAchievementResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzi(this, s, googleApiClient, s));
    }
    
    @Override
    public final void setSteps(final GoogleApiClient googleApiClient, final String s, final int n) {
        googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzn(this, s, googleApiClient, s, n));
    }
    
    @Override
    public final PendingResult<UpdateAchievementResult> setStepsImmediate(final GoogleApiClient googleApiClient, final String s, final int n) {
        return (PendingResult<UpdateAchievementResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzo(this, s, googleApiClient, s, n));
    }
    
    @Override
    public final void unlock(final GoogleApiClient googleApiClient, final String s) {
        googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzj(this, s, googleApiClient, s));
    }
    
    @Override
    public final PendingResult<UpdateAchievementResult> unlockImmediate(final GoogleApiClient googleApiClient, final String s) {
        return (PendingResult<UpdateAchievementResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzk(this, s, googleApiClient, s));
    }
}
