// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import android.os.IInterface;

public interface zzs extends IInterface
{
    void zzc(final GoogleSignInAccount p0, final Status p1) throws RemoteException;
    
    void zze(final Status p0) throws RemoteException;
    
    void zzf(final Status p0) throws RemoteException;
}
