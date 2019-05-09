// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.IInterface;

public interface zzro extends IInterface
{
    void onUnconfirmedClickCancelled() throws RemoteException;
    
    void onUnconfirmedClickReceived(final String p0) throws RemoteException;
}
