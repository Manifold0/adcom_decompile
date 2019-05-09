// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.achievement.Achievements;

final class zzq implements LoadAchievementsResult
{
    private final /* synthetic */ Status zzbc;
    
    zzq(final zzp zzp, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final AchievementBuffer getAchievements() {
        return new AchievementBuffer(DataHolder.empty(14));
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
    
    public final void release() {
    }
}
