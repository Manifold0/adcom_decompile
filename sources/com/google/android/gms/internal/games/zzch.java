package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests.UpdateRequestsResult;
import java.util.Set;

final class zzch implements UpdateRequestsResult {
    private final /* synthetic */ Status zzbc;

    zzch(zzcg zzcg, Status status) {
        this.zzbc = status;
    }

    public final Set<String> getRequestIds() {
        return null;
    }

    public final int getRequestOutcome(String str) {
        String str2 = "Unknown request ID ";
        String valueOf = String.valueOf(str);
        throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }

    public final Status getStatus() {
        return this.zzbc;
    }

    public final void release() {
    }
}
