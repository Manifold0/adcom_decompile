package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult;

final class zzd implements ResultConverter<UpdateAchievementResult, Boolean> {
    zzd() {
    }

    public final /* synthetic */ Object convert(Result result) {
        UpdateAchievementResult updateAchievementResult = (UpdateAchievementResult) result;
        boolean z = updateAchievementResult != null && updateAchievementResult.getStatus().getStatusCode() == 3003;
        return Boolean.valueOf(z);
    }
}
