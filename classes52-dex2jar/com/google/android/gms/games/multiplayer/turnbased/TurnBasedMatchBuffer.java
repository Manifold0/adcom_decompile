// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;

public final class TurnBasedMatchBuffer extends EntityBuffer<TurnBasedMatch>
{
    public TurnBasedMatchBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    protected final String getPrimaryDataMarkerColumn() {
        return "external_match_id";
    }
}
