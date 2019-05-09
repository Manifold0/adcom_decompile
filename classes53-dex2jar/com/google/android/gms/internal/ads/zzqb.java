// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzqb extends zzek implements zzqa
{
    public zzqb() {
        super("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
    }
    
    public static zzqa zzi(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
        if (queryLocalInterface instanceof zzqa) {
            return (zzqa)queryLocalInterface;
        }
        return new zzqc(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zzb(parcel.readString(), IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 2: {
                final IObjectWrapper zzak = this.zzak(parcel.readString());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzak);
                break;
            }
            case 3: {
                this.zza(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 4: {
                this.destroy();
                parcel2.writeNoException();
                break;
            }
            case 5: {
                this.zzb(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                break;
            }
            case 6: {
                this.zzc(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
        }
        return true;
    }
}
