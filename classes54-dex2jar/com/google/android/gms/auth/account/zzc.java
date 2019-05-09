// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.account;

import android.os.RemoteException;
import android.accounts.Account;
import android.os.IInterface;

public interface zzc extends IInterface
{
    void zza(final zza p0, final Account p1) throws RemoteException;
    
    void zza(final zza p0, final String p1) throws RemoteException;
    
    void zzb(final boolean p0) throws RemoteException;
}
