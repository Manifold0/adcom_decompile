// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.games.Games;

abstract class zzco extends zza<Snapshots.CommitSnapshotResult>
{
    private zzco(final GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }
}
