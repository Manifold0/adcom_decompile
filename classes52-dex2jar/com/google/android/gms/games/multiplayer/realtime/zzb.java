// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.realtime;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;

public final class zzb extends EntityBuffer<Room>
{
    public zzb(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    protected final String getPrimaryDataMarkerColumn() {
        return "external_match_id";
    }
}
