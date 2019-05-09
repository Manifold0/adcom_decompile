// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.Parcelable;
import android.os.RemoteException;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.IInterface;
import com.google.android.gms.internal.base.zac;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.IBinder;
import com.google.android.gms.internal.base.zaa;

public final class zah extends zaa implements ISignInButtonCreator
{
    zah(final IBinder binder) {
        super(binder, "com.google.android.gms.common.internal.ISignInButtonCreator");
    }
    
    @Override
    public final IObjectWrapper newSignInButton(final IObjectWrapper objectWrapper, final int n, final int n2) throws RemoteException {
        final Parcel zaa = this.zaa();
        zac.zaa(zaa, (IInterface)objectWrapper);
        zaa.writeInt(n);
        zaa.writeInt(n2);
        final Parcel zaa2 = this.zaa(1, zaa);
        final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(zaa2.readStrongBinder());
        zaa2.recycle();
        return interface1;
    }
    
    @Override
    public final IObjectWrapper newSignInButtonFromConfig(final IObjectWrapper objectWrapper, final SignInButtonConfig signInButtonConfig) throws RemoteException {
        final Parcel zaa = this.zaa();
        zac.zaa(zaa, (IInterface)objectWrapper);
        zac.zaa(zaa, (Parcelable)signInButtonConfig);
        final Parcel zaa2 = this.zaa(2, zaa);
        final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(zaa2.readStrongBinder());
        zaa2.recycle();
        return interface1;
    }
}
