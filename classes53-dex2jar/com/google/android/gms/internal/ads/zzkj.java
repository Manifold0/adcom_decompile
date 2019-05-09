// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import android.os.IBinder;

public final class zzkj extends zzej implements zzkh
{
    zzkj(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.client.IAdListener");
    }
    
    public final void onAdClicked() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(6, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onAdClosed() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(1, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onAdFailedToLoad(final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(n);
        this.transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
    
    public final void onAdImpression() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(7, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onAdLeftApplication() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(3, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onAdLoaded() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(4, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onAdOpened() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(5, this.obtainAndWriteInterfaceToken());
    }
}
