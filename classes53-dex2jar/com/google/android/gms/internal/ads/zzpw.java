// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import android.net.Uri;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzpw extends IInterface
{
    double getScale() throws RemoteException;
    
    Uri getUri() throws RemoteException;
    
    IObjectWrapper zzjy() throws RemoteException;
}
