// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zzc;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.internal.common.zzb;

public abstract class zzj extends zzb implements zzi
{
    public zzj() {
        super("com.google.android.gms.common.internal.ICertData");
    }
    
    public static zzi zzb(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.ICertData");
        if (queryLocalInterface instanceof zzi) {
            return (zzi)queryLocalInterface;
        }
        return new zzk(binder);
    }
    
    @Override
    protected final boolean zza(int zzc, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
        switch (zzc) {
            default: {
                return false;
            }
            case 1: {
                final IObjectWrapper zzb = this.zzb();
                parcel2.writeNoException();
                zzc.zza(parcel2, (IInterface)zzb);
                break;
            }
            case 2: {
                zzc = this.zzc();
                parcel2.writeNoException();
                parcel2.writeInt(zzc);
                break;
            }
        }
        return true;
    }
}
