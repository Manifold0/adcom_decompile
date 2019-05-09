// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.RemoteException;
import android.location.Location;
import android.os.IInterface;

public interface zzx extends IInterface
{
    void onLocationChanged(final Location p0) throws RemoteException;
}
