// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.net.Uri;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzpx extends zzek implements zzpw
{
    public zzpx() {
        super("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
    }
    
    public static zzpw zzh(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
        if (queryLocalInterface instanceof zzpw) {
            return (zzpw)queryLocalInterface;
        }
        return new zzpy(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                final IObjectWrapper zzjy = this.zzjy();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzjy);
                break;
            }
            case 2: {
                final Uri uri = this.getUri();
                parcel2.writeNoException();
                zzel.zzb(parcel2, (Parcelable)uri);
                break;
            }
            case 3: {
                final double scale = this.getScale();
                parcel2.writeNoException();
                parcel2.writeDouble(scale);
                break;
            }
        }
        return true;
    }
}
