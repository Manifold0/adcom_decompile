package com.google.android.gms.games;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.internal.zzr;

final class zze implements zzr {
    zze() {
    }

    public final boolean zza(@NonNull Status status) {
        return status.isSuccess() || status.getStatusCode() == 3003;
    }
}
