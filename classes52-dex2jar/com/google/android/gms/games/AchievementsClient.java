// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.support.annotation.IntRange;
import com.google.android.gms.common.api.internal.TaskApiCall;
import android.content.Intent;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.PendingResult;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.games.internal.zzr;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.internal.games.zzu;

public class AchievementsClient extends zzu
{
    private static final PendingResultUtil$ResultConverter<Achievements.LoadAchievementsResult, AchievementBuffer> zze;
    private static final PendingResultUtil$ResultConverter<Achievements.UpdateAchievementResult, Void> zzf;
    private static final PendingResultUtil$ResultConverter<Achievements.UpdateAchievementResult, Boolean> zzg;
    private static final zzr zzh;
    
    static {
        zze = (PendingResultUtil$ResultConverter)new zzb();
        zzf = (PendingResultUtil$ResultConverter)new zzc();
        zzg = (PendingResultUtil$ResultConverter)new zzd();
        zzh = new zze();
    }
    
    AchievementsClient(@NonNull final Activity activity, @NonNull final Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }
    
    AchievementsClient(@NonNull final Context context, @NonNull final Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
    
    private static Task<Void> zza(@NonNull final PendingResult<Achievements.UpdateAchievementResult> pendingResult) {
        return zzi.zza(pendingResult, AchievementsClient.zzh, AchievementsClient.zzf);
    }
    
    private static Task<Boolean> zzb(@NonNull final PendingResult<Achievements.UpdateAchievementResult> pendingResult) {
        return zzi.zza(pendingResult, AchievementsClient.zzh, AchievementsClient.zzg);
    }
    
    public Task<Intent> getAchievementsIntent() {
        return (Task<Intent>)this.doRead((TaskApiCall)new zza(this));
    }
    
    public void increment(@NonNull final String s, @IntRange(from = 0L) final int n) {
        Games.Achievements.increment(this.asGoogleApiClient(), s, n);
    }
    
    public Task<Boolean> incrementImmediate(@NonNull final String s, @IntRange(from = 0L) final int n) {
        return zzb(Games.Achievements.incrementImmediate(this.asGoogleApiClient(), s, n));
    }
    
    public Task<AnnotatedData<AchievementBuffer>> load(final boolean b) {
        return zzi.zzb(Games.Achievements.load(this.asGoogleApiClient(), b), AchievementsClient.zze);
    }
    
    public void reveal(@NonNull final String s) {
        Games.Achievements.reveal(this.asGoogleApiClient(), s);
    }
    
    public Task<Void> revealImmediate(@NonNull final String s) {
        return zza(Games.Achievements.revealImmediate(this.asGoogleApiClient(), s));
    }
    
    public void setSteps(@NonNull final String s, @IntRange(from = 0L) final int n) {
        Games.Achievements.setSteps(this.asGoogleApiClient(), s, n);
    }
    
    public Task<Boolean> setStepsImmediate(@NonNull final String s, @IntRange(from = 0L) final int n) {
        return zzb(Games.Achievements.setStepsImmediate(this.asGoogleApiClient(), s, n));
    }
    
    public void unlock(@NonNull final String s) {
        Games.Achievements.unlock(this.asGoogleApiClient(), s);
    }
    
    public Task<Void> unlockImmediate(@NonNull final String s) {
        return zza(Games.Achievements.unlockImmediate(this.asGoogleApiClient(), s));
    }
}
