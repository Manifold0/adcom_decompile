// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzxl implements Runnable
{
    private final /* synthetic */ zzxe zzbun;
    
    zzxl(final zzxk zzxk, final zzxe zzbun) {
        this.zzbun = zzbun;
    }
    
    @Override
    public final void run() {
        try {
            this.zzbun.zzbtx.destroy();
        }
        catch (RemoteException ex) {
            zzakb.zzc("Could not destroy mediation adapter.", (Throwable)ex);
        }
    }
}
