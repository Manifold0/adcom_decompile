// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.video.Videos;

public final class zzdy implements Videos
{
    @Override
    public final PendingResult<CaptureCapabilitiesResult> getCaptureCapabilities(final GoogleApiClient googleApiClient) {
        return (PendingResult<CaptureCapabilitiesResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzdz(this, googleApiClient));
    }
    
    @Override
    public final Intent getCaptureOverlayIntent(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzay();
    }
    
    @Override
    public final PendingResult<CaptureStateResult> getCaptureState(final GoogleApiClient googleApiClient) {
        return (PendingResult<CaptureStateResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzea(this, googleApiClient));
    }
    
    @Override
    public final PendingResult<CaptureAvailableResult> isCaptureAvailable(final GoogleApiClient googleApiClient, final int n) {
        return (PendingResult<CaptureAvailableResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzeb(this, googleApiClient, n));
    }
    
    @Override
    public final boolean isCaptureSupported(final GoogleApiClient googleApiClient) {
        return Games.zza(googleApiClient).zzba();
    }
    
    @Override
    public final void registerCaptureOverlayStateChangedListener(final GoogleApiClient googleApiClient, final CaptureOverlayStateListener captureOverlayStateListener) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzh((ListenerHolder<CaptureOverlayStateListener>)googleApiClient.registerListener((Object)captureOverlayStateListener));
        }
    }
    
    @Override
    public final void unregisterCaptureOverlayStateChangedListener(final GoogleApiClient googleApiClient) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzbc();
        }
    }
}
