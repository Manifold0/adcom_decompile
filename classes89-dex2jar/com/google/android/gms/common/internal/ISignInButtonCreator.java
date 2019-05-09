// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.IInterface;

public interface ISignInButtonCreator extends IInterface
{
    IObjectWrapper newSignInButton(final IObjectWrapper p0, final int p1, final int p2) throws RemoteException;
    
    IObjectWrapper newSignInButtonFromConfig(final IObjectWrapper p0, final SignInButtonConfig p1) throws RemoteException;
}
