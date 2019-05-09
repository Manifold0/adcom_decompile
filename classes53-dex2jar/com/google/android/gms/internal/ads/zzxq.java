// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.List;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzxq extends IInterface
{
    void destroy() throws RemoteException;
    
    Bundle getInterstitialAdapterInfo() throws RemoteException;
    
    zzlo getVideoController() throws RemoteException;
    
    IObjectWrapper getView() throws RemoteException;
    
    boolean isInitialized() throws RemoteException;
    
    void pause() throws RemoteException;
    
    void resume() throws RemoteException;
    
    void setImmersiveMode(final boolean p0) throws RemoteException;
    
    void showInterstitial() throws RemoteException;
    
    void showVideo() throws RemoteException;
    
    void zza(final IObjectWrapper p0, final zzaic p1, final List<String> p2) throws RemoteException;
    
    void zza(final IObjectWrapper p0, final zzjj p1, final String p2, final zzaic p3, final String p4) throws RemoteException;
    
    void zza(final IObjectWrapper p0, final zzjj p1, final String p2, final zzxt p3) throws RemoteException;
    
    void zza(final IObjectWrapper p0, final zzjj p1, final String p2, final String p3, final zzxt p4) throws RemoteException;
    
    void zza(final IObjectWrapper p0, final zzjj p1, final String p2, final String p3, final zzxt p4, final zzpl p5, final List<String> p6) throws RemoteException;
    
    void zza(final IObjectWrapper p0, final zzjn p1, final zzjj p2, final String p3, final zzxt p4) throws RemoteException;
    
    void zza(final IObjectWrapper p0, final zzjn p1, final zzjj p2, final String p3, final String p4, final zzxt p5) throws RemoteException;
    
    void zza(final zzjj p0, final String p1, final String p2) throws RemoteException;
    
    void zzc(final zzjj p0, final String p1) throws RemoteException;
    
    void zzi(final IObjectWrapper p0) throws RemoteException;
    
    zzxz zzmo() throws RemoteException;
    
    zzyc zzmp() throws RemoteException;
    
    Bundle zzmq() throws RemoteException;
    
    Bundle zzmr() throws RemoteException;
    
    boolean zzms() throws RemoteException;
    
    zzqs zzmt() throws RemoteException;
    
    zzyf zzmu() throws RemoteException;
}
