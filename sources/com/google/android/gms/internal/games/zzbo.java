package com.google.android.gms.internal.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.quest.Quests.AcceptQuestResult;
import com.google.android.gms.games.quest.Quests.ClaimMilestoneResult;
import com.google.android.gms.games.quest.Quests.LoadQuestsResult;

public final class zzbo implements Quests {
    public final PendingResult<AcceptQuestResult> accept(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzbp(this, googleApiClient, str));
    }

    public final PendingResult<ClaimMilestoneResult> claim(GoogleApiClient googleApiClient, String str, String str2) {
        return googleApiClient.execute(new zzbq(this, googleApiClient, str, str2));
    }

    public final Intent getQuestIntent(GoogleApiClient googleApiClient, String str) {
        return Games.zza(googleApiClient).zzd(str);
    }

    public final Intent getQuestsIntent(GoogleApiClient googleApiClient, int[] iArr) {
        return Games.zza(googleApiClient).zza(iArr);
    }

    public final PendingResult<LoadQuestsResult> load(GoogleApiClient googleApiClient, int[] iArr, int i, boolean z) {
        return googleApiClient.enqueue(new zzbr(this, googleApiClient, iArr, i, z));
    }

    public final PendingResult<LoadQuestsResult> loadByIds(GoogleApiClient googleApiClient, boolean z, String... strArr) {
        return googleApiClient.enqueue(new zzbs(this, googleApiClient, z, strArr));
    }

    public final void registerQuestUpdateListener(GoogleApiClient googleApiClient, QuestUpdateListener questUpdateListener) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zze(googleApiClient.registerListener(questUpdateListener));
        }
    }

    public final void showStateChangedPopup(GoogleApiClient googleApiClient, String str) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zze(str);
        }
    }

    public final void unregisterQuestUpdateListener(GoogleApiClient googleApiClient) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzae();
        }
    }
}
