// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzoe extends zzek implements zzod
{
    public zzoe() {
        super("com.google.android.gms.ads.internal.customrenderedad.client.IOnCustomRenderedAdLoadedListener");
    }
    
    public static zzod zzf(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.customrenderedad.client.IOnCustomRenderedAdLoadedListener");
        if (queryLocalInterface instanceof zzod) {
            return (zzod)queryLocalInterface;
        }
        return new zzof(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            final IBinder strongBinder = parcel.readStrongBinder();
            zzoa zzoa;
            if (strongBinder == null) {
                zzoa = null;
            }
            else {
                final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
                if (queryLocalInterface instanceof zzoa) {
                    zzoa = (zzoa)queryLocalInterface;
                }
                else {
                    zzoa = new zzoc(strongBinder);
                }
            }
            this.zza(zzoa);
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
