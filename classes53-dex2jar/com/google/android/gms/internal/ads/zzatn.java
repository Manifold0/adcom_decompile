// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzatn extends IInterface
{
    String getVersion() throws RemoteException;
    
    IObjectWrapper zza(final String p0, final IObjectWrapper p1, final String p2, final String p3, final String p4) throws RemoteException;
    
    void zza(final IObjectWrapper p0, final IObjectWrapper p1) throws RemoteException;
    
    void zzm(final IObjectWrapper p0) throws RemoteException;
    
    void zzn(final IObjectWrapper p0) throws RemoteException;
    
    boolean zzy(final IObjectWrapper p0) throws RemoteException;
}
