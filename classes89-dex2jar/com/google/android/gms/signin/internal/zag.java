// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.signin.internal;

import android.os.Parcelable;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import com.google.android.gms.internal.base.zac;
import com.google.android.gms.common.internal.IAccountAccessor;
import android.os.IBinder;
import com.google.android.gms.internal.base.zaa;

public final class zag extends zaa implements zaf
{
    zag(final IBinder binder) {
        super(binder, "com.google.android.gms.signin.internal.ISignInService");
    }
    
    @Override
    public final void zaa(final IAccountAccessor accountAccessor, final int n, final boolean b) throws RemoteException {
        final Parcel zaa = this.zaa();
        zac.zaa(zaa, (IInterface)accountAccessor);
        zaa.writeInt(n);
        zac.writeBoolean(zaa, b);
        this.zab(9, zaa);
    }
    
    @Override
    public final void zaa(final zah zah, final zad zad) throws RemoteException {
        final Parcel zaa = this.zaa();
        zac.zaa(zaa, (Parcelable)zah);
        zac.zaa(zaa, (IInterface)zad);
        this.zab(12, zaa);
    }
    
    @Override
    public final void zam(final int n) throws RemoteException {
        final Parcel zaa = this.zaa();
        zaa.writeInt(n);
        this.zab(7, zaa);
    }
}
