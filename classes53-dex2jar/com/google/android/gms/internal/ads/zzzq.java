// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzzq implements zzatd<Object, Object>
{
    private final /* synthetic */ zzzf zzbvj;
    private final /* synthetic */ zzxt zzbvk;
    
    zzzq(final zzzp zzzp, final zzzf zzbvj, final zzxt zzbvk) {
        this.zzbvj = zzbvj;
        this.zzbvk = zzbvk;
    }
    
    @Override
    public final void zzau(final String s) {
        try {
            this.zzbvj.zzbr(s);
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
        }
    }
}
