// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.IBinder;

public final class zzkg extends zzej implements zzke
{
    zzkg(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.client.IAdClickListener");
    }
    
    public final void onAdClicked() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(1, this.obtainAndWriteInterfaceToken());
    }
}
