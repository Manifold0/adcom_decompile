// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.Games;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Notifications;

public final class zzbd implements Notifications
{
    @Override
    public final void clear(final GoogleApiClient googleApiClient, final int n) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzl(n);
        }
    }
    
    @Override
    public final void clearAll(final GoogleApiClient googleApiClient) {
        this.clear(googleApiClient, 63);
    }
}
