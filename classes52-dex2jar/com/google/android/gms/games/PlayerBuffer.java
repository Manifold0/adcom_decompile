// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.AbstractDataBuffer;

public final class PlayerBuffer extends AbstractDataBuffer<Player>
{
    public PlayerBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    public final Player get(final int n) {
        return new PlayerRef(this.mDataHolder, n);
    }
}
