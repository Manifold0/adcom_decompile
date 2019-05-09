package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.games.internal.zza;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbh extends zza {
    private final /* synthetic */ TaskCompletionSource zzdk;

    zzbh(zzbg zzbg, TaskCompletionSource taskCompletionSource) {
        this.zzdk = taskCompletionSource;
    }

    public final void onLeftRoom(int i, String str) {
        TaskUtil.setResultOrApiException(GamesClientStatusCodes.zza(GamesClientStatusCodes.zzb(i)), str, this.zzdk);
    }
}
