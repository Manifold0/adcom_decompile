// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztg extends zzkf
{
    private final /* synthetic */ zzst zzbnw;
    
    zztg(final zzst zzbnw) {
        this.zzbnw = zzbnw;
    }
    
    public final void onAdClicked() throws RemoteException {
        this.zzbnw.zzxo.add(new zzth(this));
    }
}
