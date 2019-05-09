// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.IBinder;

public final class zzahg extends zzej implements zzahe
{
    zzahg(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
    }
    
    public final void onRewardedVideoAdClosed() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(4, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onRewardedVideoAdFailedToLoad(final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(n);
        this.transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }
    
    public final void onRewardedVideoAdLeftApplication() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(6, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onRewardedVideoAdLoaded() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(1, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onRewardedVideoAdOpened() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(2, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onRewardedVideoCompleted() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(8, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onRewardedVideoStarted() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(3, this.obtainAndWriteInterfaceToken());
    }
    
    public final void zza(final zzagu zzagu) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzagu);
        this.transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
}
