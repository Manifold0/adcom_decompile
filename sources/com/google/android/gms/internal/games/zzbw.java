package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.Quests.ClaimMilestoneResult;

final class zzbw implements ClaimMilestoneResult {
    private final /* synthetic */ Status zzbc;

    zzbw(zzbv zzbv, Status status) {
        this.zzbc = status;
    }

    public final Milestone getMilestone() {
        return null;
    }

    public final Quest getQuest() {
        return null;
    }

    public final Status getStatus() {
        return this.zzbc;
    }
}
