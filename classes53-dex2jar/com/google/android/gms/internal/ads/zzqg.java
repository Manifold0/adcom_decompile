// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzqg extends zzek implements zzqf
{
    public zzqg() {
        super("com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegate");
    }
    
    public static zzqf zzj(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegate");
        if (queryLocalInterface instanceof zzqf) {
            return (zzqf)queryLocalInterface;
        }
        return new zzqh(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zza(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                break;
            }
            case 2: {
                this.unregisterNativeAd();
                break;
            }
            case 3: {
                this.zzc(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                break;
            }
        }
        parcel2.writeNoException();
        return true;
    }
}
