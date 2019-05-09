// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzzr implements zzatd<zzate, Object>
{
    private final /* synthetic */ zzxt zzbvk;
    private final /* synthetic */ zzzh zzbvl;
    private final /* synthetic */ zzzp zzbvm;
    
    zzzr(final zzzp zzbvm, final zzzh zzbvl, final zzxt zzbvk) {
        this.zzbvm = zzbvm;
        this.zzbvl = zzbvl;
        this.zzbvk = zzbvk;
    }
    
    @Override
    public final void zzau(final String s) {
        try {
            this.zzbvl.zzbr(s);
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
        }
    }
}
