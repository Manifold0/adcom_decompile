// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztc extends zzlb
{
    private final /* synthetic */ zzst zzbnw;
    
    zztc(final zzst zzbnw) {
        this.zzbnw = zzbnw;
    }
    
    public final void onAppEvent(final String s, final String s2) throws RemoteException {
        this.zzbnw.zzxo.add(new zztd(this, s, s2));
    }
}
