// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import android.os.IBinder;

public final class zzlt extends zzej implements zzlr
{
    zzlt(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
    }
    
    public final void onVideoEnd() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(4, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onVideoMute(final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
    
    public final void onVideoPause() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(3, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onVideoPlay() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(2, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onVideoStart() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(1, this.obtainAndWriteInterfaceToken());
    }
}
