// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.IAccountAccessor;
import android.os.IInterface;

public interface zaf extends IInterface
{
    void zaa(final IAccountAccessor p0, final int p1, final boolean p2) throws RemoteException;
    
    void zaa(final zah p0, final zad p1) throws RemoteException;
    
    void zam(final int p0) throws RemoteException;
}
