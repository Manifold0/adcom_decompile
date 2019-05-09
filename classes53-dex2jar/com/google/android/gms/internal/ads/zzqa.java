// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzqa extends IInterface
{
    void destroy() throws RemoteException;
    
    void zza(final IObjectWrapper p0) throws RemoteException;
    
    IObjectWrapper zzak(final String p0) throws RemoteException;
    
    void zzb(final IObjectWrapper p0, final int p1) throws RemoteException;
    
    void zzb(final String p0, final IObjectWrapper p1) throws RemoteException;
    
    void zzc(final IObjectWrapper p0) throws RemoteException;
}
