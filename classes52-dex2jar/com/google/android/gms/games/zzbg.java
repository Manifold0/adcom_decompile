// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.zzu;
import com.google.android.gms.games.internal.zzy;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.internal.games.zzah;

final class zzbg extends zzah<String>
{
    private final /* synthetic */ String zzdg;
    
    zzbg(final RealTimeMultiplayerClient realTimeMultiplayerClient, final String zzdg) {
        this.zzdg = zzdg;
    }
    
    @Override
    protected final void zza(final zze zze, final TaskCompletionSource<String> taskCompletionSource) throws RemoteException {
        ((zzy)zze.getService()).zza(new zzbh(this, taskCompletionSource), this.zzdg);
    }
}
