// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import android.os.Parcel;
import com.google.android.gms.internal.base.zab;

public abstract class zak extends zab implements zaj
{
    public zak() {
        super("com.google.android.gms.common.internal.service.ICommonCallbacks");
    }
    
    @Override
    protected boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            this.zaj(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
