// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzra extends zzek implements zzqz
{
    public zzra() {
        super("com.google.android.gms.ads.internal.formats.client.IOnContentAdLoadedListener");
    }
    
    public static zzqz zzm(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnContentAdLoadedListener");
        if (queryLocalInterface instanceof zzqz) {
            return (zzqz)queryLocalInterface;
        }
        return new zzrb(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            final IBinder strongBinder = parcel.readStrongBinder();
            zzqo zzqo;
            if (strongBinder == null) {
                zzqo = null;
            }
            else {
                final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                if (queryLocalInterface instanceof zzqo) {
                    zzqo = (zzqo)queryLocalInterface;
                }
                else {
                    zzqo = new zzqq(strongBinder);
                }
            }
            this.zza(zzqo);
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
