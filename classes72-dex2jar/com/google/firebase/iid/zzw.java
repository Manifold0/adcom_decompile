// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Message;
import android.os.IBinder;

public final class zzw implements zzv
{
    private final IBinder zzbo;
    
    zzw(final IBinder zzbo) {
        this.zzbo = zzbo;
    }
    
    public final IBinder asBinder() {
        return this.zzbo;
    }
    
    @Override
    public final void send(final Message message) throws RemoteException {
        final Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
        obtain.writeInt(1);
        message.writeToParcel(obtain, 0);
        try {
            this.zzbo.transact(1, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
}
