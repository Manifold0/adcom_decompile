package com.google.android.gms.nearby.connection;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;

public final class ConnectionResolution {
    private final Status zzt;

    @Deprecated
    public ConnectionResolution(Status status) {
        this.zzt = status;
    }

    @NonNull
    public final Status getStatus() {
        return this.zzt;
    }
}
