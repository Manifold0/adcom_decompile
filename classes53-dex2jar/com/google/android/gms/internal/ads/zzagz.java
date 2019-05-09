// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzagz extends IInterface
{
    void destroy() throws RemoteException;
    
    String getMediationAdapterClassName() throws RemoteException;
    
    boolean isLoaded() throws RemoteException;
    
    void pause() throws RemoteException;
    
    void resume() throws RemoteException;
    
    void setImmersiveMode(final boolean p0) throws RemoteException;
    
    void setUserId(final String p0) throws RemoteException;
    
    void show() throws RemoteException;
    
    void zza(final zzagx p0) throws RemoteException;
    
    void zza(final zzahe p0) throws RemoteException;
    
    void zza(final zzahk p0) throws RemoteException;
    
    void zza(final zzkx p0) throws RemoteException;
    
    Bundle zzba() throws RemoteException;
    
    void zzd(final IObjectWrapper p0) throws RemoteException;
    
    void zze(final IObjectWrapper p0) throws RemoteException;
    
    void zzf(final IObjectWrapper p0) throws RemoteException;
}
