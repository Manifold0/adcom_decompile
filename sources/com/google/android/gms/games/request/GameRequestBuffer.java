package com.google.android.gms.games.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;
import com.google.android.gms.common.util.VisibleForTesting;

@Deprecated
@VisibleForTesting
public final class GameRequestBuffer extends EntityBuffer<GameRequest> {
    public GameRequestBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    protected final /* synthetic */ Object getEntry(int i, int i2) {
        return new zzb(this.mDataHolder, i, i2);
    }

    protected final String getPrimaryDataMarkerColumn() {
        return "external_request_id";
    }
}
