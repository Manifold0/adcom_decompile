package com.google.android.gms.internal.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.games.video.Videos.CaptureAvailableResult;
import com.google.android.gms.games.video.Videos.CaptureCapabilitiesResult;
import com.google.android.gms.games.video.Videos.CaptureOverlayStateListener;
import com.google.android.gms.games.video.Videos.CaptureStateResult;

public final class zzdy implements Videos {
    public final PendingResult<CaptureCapabilitiesResult> getCaptureCapabilities(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzdz(this, googleApiClient));
    }

    public final Intent getCaptureOverlayIntent(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzay();
    }

    public final PendingResult<CaptureStateResult> getCaptureState(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzea(this, googleApiClient));
    }

    public final PendingResult<CaptureAvailableResult> isCaptureAvailable(GoogleApiClient googleApiClient, int i) {
        return googleApiClient.enqueue(new zzeb(this, googleApiClient, i));
    }

    public final boolean isCaptureSupported(GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzba();
    }

    public final void registerCaptureOverlayStateChangedListener(GoogleApiClient googleApiClient, CaptureOverlayStateListener captureOverlayStateListener) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzh(googleApiClient.registerListener(captureOverlayStateListener));
        }
    }

    public final void unregisterCaptureOverlayStateChangedListener(GoogleApiClient googleApiClient) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzbc();
        }
    }
}
