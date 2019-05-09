package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;
import com.google.android.gms.common.util.VisibleForTesting;

@Deprecated
@VisibleForTesting
public final class QuestBuffer extends EntityBuffer<Quest> {
    public QuestBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    protected final /* synthetic */ Object getEntry(int i, int i2) {
        return new QuestRef(this.mDataHolder, i, i2);
    }

    protected final String getPrimaryDataMarkerColumn() {
        return "external_quest_id";
    }
}
