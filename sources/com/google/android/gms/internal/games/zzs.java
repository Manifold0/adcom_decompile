package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult;

final class zzs implements UpdateAchievementResult {
    private final /* synthetic */ Status zzbc;
    private final /* synthetic */ zzr zzjj;

    zzs(zzr zzr, Status status) {
        this.zzjj = zzr;
        this.zzbc = status;
    }

    public final String getAchievementId() {
        return this.zzjj.zzji;
    }

    public final Status getStatus() {
        return this.zzbc;
    }
}
