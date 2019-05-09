// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.IInterface;

public interface zzkk extends IInterface
{
    String getMediationAdapterClassName() throws RemoteException;
    
    boolean isLoading() throws RemoteException;
    
    void zza(final zzjj p0, final int p1) throws RemoteException;
    
    String zzck() throws RemoteException;
    
    void zzd(final zzjj p0) throws RemoteException;
}
