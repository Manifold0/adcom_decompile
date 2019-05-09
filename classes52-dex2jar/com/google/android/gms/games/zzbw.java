// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import android.content.Intent;
import com.google.android.gms.internal.games.zzah;

final class zzbw extends zzah<Intent>
{
    private final /* synthetic */ String zzeb;
    private final /* synthetic */ boolean zzec;
    private final /* synthetic */ boolean zzed;
    private final /* synthetic */ int zzee;
    
    zzbw(final SnapshotsClient snapshotsClient, final String zzeb, final boolean zzec, final boolean zzed, final int zzee) {
        this.zzeb = zzeb;
        this.zzec = zzec;
        this.zzed = zzed;
        this.zzee = zzee;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult((Object)zze.zza(this.zzeb, this.zzec, this.zzed, this.zzee));
    }
}
