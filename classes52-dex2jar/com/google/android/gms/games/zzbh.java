// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zza;

final class zzbh extends zza
{
    private final /* synthetic */ TaskCompletionSource zzdk;
    
    zzbh(final zzbg zzbg, final TaskCompletionSource zzdk) {
        this.zzdk = zzdk;
    }
    
    @Override
    public final void onLeftRoom(final int n, final String s) {
        TaskUtil.setResultOrApiException(GamesClientStatusCodes.zza(GamesClientStatusCodes.zzb(n)), (Object)s, this.zzdk);
    }
}
