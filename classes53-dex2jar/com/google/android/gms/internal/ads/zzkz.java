// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.IBinder;

public final class zzkz extends zzej implements zzkx
{
    zzkz(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.client.IAdMetadataListener");
    }
    
    public final void zzt() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(1, this.obtainAndWriteInterfaceToken());
    }
}
