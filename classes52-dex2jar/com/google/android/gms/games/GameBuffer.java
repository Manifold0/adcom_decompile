// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.AbstractDataBuffer;

public final class GameBuffer extends AbstractDataBuffer<Game>
{
    public GameBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    public final Game get(final int n) {
        return new GameRef(this.mDataHolder, n);
    }
}
