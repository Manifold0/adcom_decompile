// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import com.google.android.gms.internal.base.zac;
import android.os.IBinder;
import com.google.android.gms.internal.base.zaa;

public final class zam extends zaa implements zal
{
    zam(final IBinder binder) {
        super(binder, "com.google.android.gms.common.internal.service.ICommonService");
    }
    
    @Override
    public final void zaa(final zaj zaj) throws RemoteException {
        final Parcel zaa = this.zaa();
        zac.zaa(zaa, (IInterface)zaj);
        this.zac(1, zaa);
    }
}
