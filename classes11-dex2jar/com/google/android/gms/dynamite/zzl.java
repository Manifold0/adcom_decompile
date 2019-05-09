// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamite;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import com.google.android.gms.internal.common.zzc;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.IBinder;
import com.google.android.gms.internal.common.zza;

public final class zzl extends zza implements zzk
{
    zzl(final IBinder binder) {
        super(binder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }
    
    @Override
    public final IObjectWrapper zza(final IObjectWrapper objectWrapper, final String s, final int n, final IObjectWrapper objectWrapper2) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zza(zza, (IInterface)objectWrapper);
        zza.writeString(s);
        zza.writeInt(n);
        zzc.zza(zza, (IInterface)objectWrapper2);
        final Parcel zza2 = this.zza(2, zza);
        final IObjectWrapper interface1 = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return interface1;
    }
    
    @Override
    public final IObjectWrapper zzb(final IObjectWrapper objectWrapper, final String s, final int n, final IObjectWrapper objectWrapper2) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zza(zza, (IInterface)objectWrapper);
        zza.writeString(s);
        zza.writeInt(n);
        zzc.zza(zza, (IInterface)objectWrapper2);
        final Parcel zza2 = this.zza(3, zza);
        final IObjectWrapper interface1 = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return interface1;
    }
}
