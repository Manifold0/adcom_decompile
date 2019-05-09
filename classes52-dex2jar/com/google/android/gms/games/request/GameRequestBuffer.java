// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.data.EntityBuffer;

@Deprecated
@VisibleForTesting
public final class GameRequestBuffer extends EntityBuffer<GameRequest>
{
    public GameRequestBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    protected final String getPrimaryDataMarkerColumn() {
        return "external_request_id";
    }
}
