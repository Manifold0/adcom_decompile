// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.RemoteException;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.IBinder;
import com.google.android.gms.internal.common.zza;

public final class zzk extends zza implements zzi
{
    zzk(final IBinder binder) {
        super(binder, "com.google.android.gms.common.internal.ICertData");
    }
    
    @Override
    public final IObjectWrapper zzb() throws RemoteException {
        final Parcel zza = this.zza(1, this.zza());
        final IObjectWrapper interface1 = IObjectWrapper.Stub.asInterface(zza.readStrongBinder());
        zza.recycle();
        return interface1;
    }
    
    @Override
    public final int zzc() throws RemoteException {
        final Parcel zza = this.zza(2, this.zza());
        final int int1 = zza.readInt();
        zza.recycle();
        return int1;
    }
}
