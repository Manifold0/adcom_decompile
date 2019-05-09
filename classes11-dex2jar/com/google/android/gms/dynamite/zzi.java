// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamite;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.IInterface;

public interface zzi extends IInterface
{
    int zza(final IObjectWrapper p0, final String p1, final boolean p2) throws RemoteException;
    
    IObjectWrapper zza(final IObjectWrapper p0, final String p1, final int p2) throws RemoteException;
    
    int zzak() throws RemoteException;
    
    int zzb(final IObjectWrapper p0, final String p1, final boolean p2) throws RemoteException;
    
    IObjectWrapper zzb(final IObjectWrapper p0, final String p1, final int p2) throws RemoteException;
}
