// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzoa extends IInterface
{
    String getContent() throws RemoteException;
    
    void recordClick() throws RemoteException;
    
    void recordImpression() throws RemoteException;
    
    void zzg(final IObjectWrapper p0) throws RemoteException;
    
    String zzjn() throws RemoteException;
}
