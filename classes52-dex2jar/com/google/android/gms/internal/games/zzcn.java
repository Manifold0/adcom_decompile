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
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;

final class zzcn extends zzcu
{
    private final /* synthetic */ SnapshotMetadataChange zzkd;
    private final /* synthetic */ String zzkf;
    private final /* synthetic */ String zzkg;
    private final /* synthetic */ SnapshotContents zzkh;
    
    zzcn(final zzci zzci, final GoogleApiClient googleApiClient, final String zzkf, final String zzkg, final SnapshotMetadataChange zzkd, final SnapshotContents zzkh) {
        this.zzkf = zzkf;
        this.zzkg = zzkg;
        this.zzkd = zzkd;
        this.zzkh = zzkh;
        super(googleApiClient, null);
    }
}
