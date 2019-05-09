// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.RemoteException;
import android.os.IInterface;

public interface zzu extends IInterface
{
    void onLocationAvailability(final LocationAvailability p0) throws RemoteException;
    
    void onLocationResult(final LocationResult p0) throws RemoteException;
}
