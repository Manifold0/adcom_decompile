// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import com.google.android.gms.auth.api.credentials.Credential;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import android.os.IInterface;

public interface zzu extends IInterface
{
    void zzc(final Status p0) throws RemoteException;
    
    void zzc(final Status p0, final Credential p1) throws RemoteException;
    
    void zzc(final Status p0, final String p1) throws RemoteException;
}
