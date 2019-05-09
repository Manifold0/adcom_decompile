// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.PendingResult;
import android.annotation.SuppressLint;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.games.Games;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.event.Events;

public final class zzv implements Events
{
    @SuppressLint({ "MissingRemoteException" })
    @Override
    public final void increment(final GoogleApiClient googleApiClient, final String s, final int n) {
        final zze zzb = Games.zzb(googleApiClient, false);
        if (zzb == null) {
            return;
        }
        if (zzb.isConnected()) {
            zzb.zza(s, n);
            return;
        }
        googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzy(this, googleApiClient, s, n));
    }
    
    @Override
    public final PendingResult<LoadEventsResult> load(final GoogleApiClient googleApiClient, final boolean b) {
        return (PendingResult<LoadEventsResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzx(this, googleApiClient, b));
    }
    
    @Override
    public final PendingResult<LoadEventsResult> loadByIds(final GoogleApiClient googleApiClient, final boolean b, final String... array) {
        return (PendingResult<LoadEventsResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzw(this, googleApiClient, b, array));
    }
}
