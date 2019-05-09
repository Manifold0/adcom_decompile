// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.Parcelable;
import com.google.android.gms.internal.common.zzc;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.common.zzk;
import android.os.IBinder;
import com.google.android.gms.internal.common.zza;

public final class zzo extends zza implements zzm
{
    zzo(final IBinder binder) {
        super(binder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }
    
    @Override
    public final boolean zza(final zzk zzk, final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zza(zza, (Parcelable)zzk);
        zzc.zza(zza, (IInterface)objectWrapper);
        final Parcel zza2 = this.zza(5, zza);
        final boolean zza3 = zzc.zza(zza2);
        zza2.recycle();
        return zza3;
    }
}
