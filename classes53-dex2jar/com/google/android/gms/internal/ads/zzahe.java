// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.IInterface;

public interface zzahe extends IInterface
{
    void onRewardedVideoAdClosed() throws RemoteException;
    
    void onRewardedVideoAdFailedToLoad(final int p0) throws RemoteException;
    
    void onRewardedVideoAdLeftApplication() throws RemoteException;
    
    void onRewardedVideoAdLoaded() throws RemoteException;
    
    void onRewardedVideoAdOpened() throws RemoteException;
    
    void onRewardedVideoCompleted() throws RemoteException;
    
    void onRewardedVideoStarted() throws RemoteException;
    
    void zza(final zzagu p0) throws RemoteException;
}
