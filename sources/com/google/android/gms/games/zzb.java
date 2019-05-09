package com.google.android.gms.games;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult;

final class zzb implements ResultConverter<LoadAchievementsResult, AchievementBuffer> {
    zzb() {
    }

    public final /* synthetic */ Object convert(@Nullable Result result) {
        LoadAchievementsResult loadAchievementsResult = (LoadAchievementsResult) result;
        return loadAchievementsResult == null ? null : loadAchievementsResult.getAchievements();
    }
}
