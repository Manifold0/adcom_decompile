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

public final class zzj extends zza implements zzi
{
    zzj(final IBinder binder) {
        super(binder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }
    
    @Override
    public final int zza(final IObjectWrapper objectWrapper, final String s, final boolean b) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zza(zza, (IInterface)objectWrapper);
        zza.writeString(s);
        zzc.writeBoolean(zza, b);
        final Parcel zza2 = this.zza(3, zza);
        final int int1 = zza2.readInt();
        zza2.recycle();
        return int1;
    }
    
    @Override
    public final IObjectWrapper zza(final IObjectWrapper objectWrapper, final String s, final int n) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zza(zza, (IInterface)objectWrapper);
        zza.writeString(s);
        zza.writeInt(n);
        final Parcel zza2 = this.zza(2, zza);
        final IObjectWrapper interface1 = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return interface1;
    }
    
    @Override
    public final int zzak() throws RemoteException {
        final Parcel zza = this.zza(6, this.zza());
        final int int1 = zza.readInt();
        zza.recycle();
        return int1;
    }
    
    @Override
    public final int zzb(final IObjectWrapper objectWrapper, final String s, final boolean b) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zza(zza, (IInterface)objectWrapper);
        zza.writeString(s);
        zzc.writeBoolean(zza, b);
        final Parcel zza2 = this.zza(5, zza);
        final int int1 = zza2.readInt();
        zza2.recycle();
        return int1;
    }
    
    @Override
    public final IObjectWrapper zzb(final IObjectWrapper objectWrapper, final String s, final int n) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zza(zza, (IInterface)objectWrapper);
        zza.writeString(s);
        zza.writeInt(n);
        final Parcel zza2 = this.zza(4, zza);
        final IObjectWrapper interface1 = IObjectWrapper.Stub.asInterface(zza2.readStrongBinder());
        zza2.recycle();
        return interface1;
    }
}
