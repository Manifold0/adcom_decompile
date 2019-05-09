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
import com.google.android.gms.games.snapshot.SnapshotMetadata;

final class zzcm extends zzcq
{
    private final /* synthetic */ SnapshotMetadata zzke;
    
    zzcm(final zzci zzci, final GoogleApiClient googleApiClient, final SnapshotMetadata zzke) {
        this.zzke = zzke;
        super(googleApiClient, null);
    }
}
