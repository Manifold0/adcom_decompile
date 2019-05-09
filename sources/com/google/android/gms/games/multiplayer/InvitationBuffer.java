package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;

public final class InvitationBuffer extends EntityBuffer<Invitation> {
    public InvitationBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    protected final /* synthetic */ Object getEntry(int i, int i2) {
        return new zzb(this.mDataHolder, i, i2);
    }

    protected final String getPrimaryDataMarkerColumn() {
        return "external_invitation_id";
    }
}
