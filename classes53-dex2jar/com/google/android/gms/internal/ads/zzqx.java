// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzqx extends zzek implements zzqw
{
    public zzqx() {
        super("com.google.android.gms.ads.internal.formats.client.IOnAppInstallAdLoadedListener");
    }
    
    public static zzqw zzl(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnAppInstallAdLoadedListener");
        if (queryLocalInterface instanceof zzqw) {
            return (zzqw)queryLocalInterface;
        }
        return new zzqy(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            final IBinder strongBinder = parcel.readStrongBinder();
            zzqk zzqk;
            if (strongBinder == null) {
                zzqk = null;
            }
            else {
                final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                if (queryLocalInterface instanceof zzqk) {
                    zzqk = (zzqk)queryLocalInterface;
                }
                else {
                    zzqk = new zzqm(strongBinder);
                }
            }
            this.zza(zzqk);
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
