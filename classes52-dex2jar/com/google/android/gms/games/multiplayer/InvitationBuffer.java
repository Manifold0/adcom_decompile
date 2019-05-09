// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;

public final class InvitationBuffer extends EntityBuffer<Invitation>
{
    public InvitationBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    protected final String getPrimaryDataMarkerColumn() {
        return "external_invitation_id";
    }
}
