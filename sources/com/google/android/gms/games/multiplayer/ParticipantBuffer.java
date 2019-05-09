package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.AbstractDataBuffer;

public final class ParticipantBuffer extends AbstractDataBuffer<Participant> {
    public final Participant get(int i) {
        return new ParticipantRef(this.mDataHolder, i);
    }

    /* renamed from: get */
    public final /* synthetic */ Object m8274get(int i) {
        throw new NoSuchMethodError();
    }
}
