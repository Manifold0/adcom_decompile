// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshot;

final class zzcl extends zzco
{
    private final /* synthetic */ Snapshot zzef;
    private final /* synthetic */ SnapshotMetadataChange zzkd;
    
    zzcl(final zzci zzci, final GoogleApiClient googleApiClient, final Snapshot zzef, final SnapshotMetadataChange zzkd) {
        this.zzef = zzef;
        this.zzkd = zzkd;
        super(googleApiClient, null);
    }
}
