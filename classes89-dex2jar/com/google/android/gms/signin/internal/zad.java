// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import android.os.IInterface;

public interface zad extends IInterface
{
    void zaa(final ConnectionResult p0, final zaa p1) throws RemoteException;
    
    void zaa(final Status p0, final GoogleSignInAccount p1) throws RemoteException;
    
    void zab(final zaj p0) throws RemoteException;
    
    void zag(final Status p0) throws RemoteException;
    
    void zah(final Status p0) throws RemoteException;
}
