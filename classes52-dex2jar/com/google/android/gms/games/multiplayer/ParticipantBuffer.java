// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.AbstractDataBuffer;

public final class ParticipantBuffer extends AbstractDataBuffer<Participant>
{
    public final Participant get(final int n) {
        return new ParticipantRef(this.mDataHolder, n);
    }
}
