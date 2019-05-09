// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzzj extends IInterface
{
    zzlo getVideoController() throws RemoteException;
    
    void showInterstitial() throws RemoteException;
    
    void zza(final IObjectWrapper p0, final String p1, final Bundle p2, final zzzm p3) throws RemoteException;
    
    void zza(final byte[] p0, final String p1, final Bundle p2, final IObjectWrapper p3, final zzzf p4, final zzxt p5, final zzjn p6) throws RemoteException;
    
    void zza(final byte[] p0, final String p1, final Bundle p2, final IObjectWrapper p3, final zzzh p4, final zzxt p5) throws RemoteException;
    
    zzzt zznc() throws RemoteException;
    
    zzzt zznd() throws RemoteException;
}
