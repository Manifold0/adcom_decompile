// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import com.google.android.gms.auth.api.credentials.CredentialRequest;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzw extends IInterface
{
    void zzc(final zzu p0) throws RemoteException;
    
    void zzc(final zzu p0, final CredentialRequest p1) throws RemoteException;
    
    void zzc(final zzu p0, final zzs p1) throws RemoteException;
    
    void zzc(final zzu p0, final zzy p1) throws RemoteException;
}
