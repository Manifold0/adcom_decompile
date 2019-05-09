// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.achievement.Achievements;

final class zzs implements UpdateAchievementResult
{
    private final /* synthetic */ Status zzbc;
    private final /* synthetic */ zzr zzjj;
    
    zzs(final zzr zzjj, final Status zzbc) {
        this.zzjj = zzjj;
        this.zzbc = zzbc;
    }
    
    @Override
    public final String getAchievementId() {
        return this.zzjj.zzji;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
}
