// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.RemoteException;
import android.app.PendingIntent;
import android.os.IInterface;

public interface zzam extends IInterface
{
    void zza(final int p0, final PendingIntent p1) throws RemoteException;
    
    void zza(final int p0, final String[] p1) throws RemoteException;
    
    void zzb(final int p0, final String[] p1) throws RemoteException;
}
