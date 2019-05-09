package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.Quests.LoadQuestsResult;

final class zzby implements LoadQuestsResult {
    private final /* synthetic */ Status zzbc;

    zzby(zzbx zzbx, Status status) {
        this.zzbc = status;
    }

    public final QuestBuffer getQuests() {
        return new QuestBuffer(DataHolder.empty(this.zzbc.getStatusCode()));
    }

    public final Status getStatus() {
        return this.zzbc;
    }

    public final void release() {
    }
}
