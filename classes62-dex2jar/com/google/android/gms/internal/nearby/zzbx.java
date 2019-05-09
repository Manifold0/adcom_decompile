// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.ConnectionResolution;
import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;

final class zzbx extends ConnectionLifecycleCallback
{
    private final /* synthetic */ zzbd zzcq;
    private final ConnectionLifecycleCallback zzct;
    
    zzbx(final zzbd zzcq, final ConnectionLifecycleCallback zzct) {
        this.zzcq = zzcq;
        this.zzct = zzct;
    }
    
    @Override
    public final void onConnectionInitiated(final String s, final ConnectionInfo connectionInfo) {
        if (connectionInfo.isIncomingConnection()) {
            this.zzcq.zzb(s);
        }
        this.zzct.onConnectionInitiated(s, connectionInfo);
    }
    
    @Override
    public final void onConnectionResult(final String s, final ConnectionResolution connectionResolution) {
        if (!connectionResolution.getStatus().isSuccess()) {
            this.zzcq.zzc(s);
        }
        this.zzct.onConnectionResult(s, connectionResolution);
    }
    
    @Override
    public final void onDisconnected(final String s) {
        this.zzcq.zzc(s);
        this.zzct.onDisconnected(s);
    }
}
