// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.data.EntityBuffer;

@Deprecated
@VisibleForTesting
public final class QuestBuffer extends EntityBuffer<Quest>
{
    public QuestBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    protected final String getPrimaryDataMarkerColumn() {
        return "external_quest_id";
    }
}
