package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.games.internal.zze;

public final class zzbd implements Notifications {
    public final void clear(GoogleApiClient googleApiClient, int i) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzl(i);
        }
    }

    public final void clearAll(GoogleApiClient googleApiClient) {
        clear(googleApiClient, 63);
    }
}
