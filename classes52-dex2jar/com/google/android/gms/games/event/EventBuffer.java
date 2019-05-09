// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.event;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.AbstractDataBuffer;

public final class EventBuffer extends AbstractDataBuffer<Event>
{
    public EventBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    public final Event get(final int n) {
        return new EventRef(this.mDataHolder, n);
    }
}
