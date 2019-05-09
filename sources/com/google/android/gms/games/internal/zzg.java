package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks;

final class zzg implements ResultHolder<Status> {
    private final /* synthetic */ SignOutCallbacks zzgb;

    zzg(zze zze, SignOutCallbacks signOutCallbacks) {
        this.zzgb = signOutCallbacks;
    }

    public final void setFailedResult(Status status) {
        this.zzgb.onSignOutComplete();
    }

    public final /* synthetic */ void setResult(Object obj) {
        this.zzgb.onSignOutComplete();
    }
}
