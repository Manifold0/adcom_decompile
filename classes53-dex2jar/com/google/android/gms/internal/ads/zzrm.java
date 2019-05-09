// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzrm extends zzek implements zzrl
{
    public zzrm() {
        super("com.google.android.gms.ads.internal.formats.client.IOnUnifiedNativeAdLoadedListener");
    }
    
    public static zzrl zzq(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnUnifiedNativeAdLoadedListener");
        if (queryLocalInterface instanceof zzrl) {
            return (zzrl)queryLocalInterface;
        }
        return new zzrn(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            final IBinder strongBinder = parcel.readStrongBinder();
            zzrr zzrr;
            if (strongBinder == null) {
                zzrr = null;
            }
            else {
                final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
                if (queryLocalInterface instanceof zzrr) {
                    zzrr = (zzrr)queryLocalInterface;
                }
                else {
                    zzrr = new zzrt(strongBinder);
                }
            }
            this.zza(zzrr);
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
