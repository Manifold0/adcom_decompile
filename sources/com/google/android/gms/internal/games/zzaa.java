package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events.LoadEventsResult;

final class zzaa implements LoadEventsResult {
    private final /* synthetic */ Status zzbc;

    zzaa(zzz zzz, Status status) {
        this.zzbc = status;
    }

    public final EventBuffer getEvents() {
        return new EventBuffer(DataHolder.empty(14));
    }

    public final Status getStatus() {
        return this.zzbc;
    }

    public final void release() {
    }
}
