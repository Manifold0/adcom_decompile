// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzlj extends IInterface
{
    void setAppMuted(final boolean p0) throws RemoteException;
    
    void setAppVolume(final float p0) throws RemoteException;
    
    void zza() throws RemoteException;
    
    void zza(final String p0, final IObjectWrapper p1) throws RemoteException;
    
    void zzb(final IObjectWrapper p0, final String p1) throws RemoteException;
    
    float zzdo() throws RemoteException;
    
    boolean zzdp() throws RemoteException;
    
    void zzt(final String p0) throws RemoteException;
}
