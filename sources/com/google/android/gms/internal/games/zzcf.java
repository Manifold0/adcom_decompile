package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.Requests.LoadRequestsResult;

final class zzcf implements LoadRequestsResult {
    private final /* synthetic */ Status zzbc;

    zzcf(zzce zzce, Status status) {
        this.zzbc = status;
    }

    public final GameRequestBuffer getRequests(int i) {
        return new GameRequestBuffer(DataHolder.empty(this.zzbc.getStatusCode()));
    }

    public final Status getStatus() {
        return this.zzbc;
    }

    public final void release() {
    }
}
