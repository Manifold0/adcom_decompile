package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.Quests.AcceptQuestResult;

final class zzbu implements AcceptQuestResult {
    private final /* synthetic */ Status zzbc;

    zzbu(zzbt zzbt, Status status) {
        this.zzbc = status;
    }

    public final Quest getQuest() {
        return null;
    }

    public final Status getStatus() {
        return this.zzbc;
    }
}
