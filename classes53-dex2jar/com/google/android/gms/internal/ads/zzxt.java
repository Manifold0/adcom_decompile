// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.IInterface;

public interface zzxt extends IInterface
{
    void onAdClicked() throws RemoteException;
    
    void onAdClosed() throws RemoteException;
    
    void onAdFailedToLoad(final int p0) throws RemoteException;
    
    void onAdImpression() throws RemoteException;
    
    void onAdLeftApplication() throws RemoteException;
    
    void onAdLoaded() throws RemoteException;
    
    void onAdOpened() throws RemoteException;
    
    void onAppEvent(final String p0, final String p1) throws RemoteException;
    
    void onVideoEnd() throws RemoteException;
    
    void zza(final zzxw p0) throws RemoteException;
    
    void zzb(final zzqs p0, final String p1) throws RemoteException;
    
    void zzbj(final String p0) throws RemoteException;
}
