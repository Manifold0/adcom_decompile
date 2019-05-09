// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzrd extends zzek implements zzrc
{
    public zzrd() {
        super("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }
    
    public static zzrc zzn(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
        if (queryLocalInterface instanceof zzrc) {
            return (zzrc)queryLocalInterface;
        }
        return new zzre(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            final IBinder strongBinder = parcel.readStrongBinder();
            zzqs zzqs;
            if (strongBinder == null) {
                zzqs = null;
            }
            else {
                final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                if (queryLocalInterface instanceof zzqs) {
                    zzqs = (zzqs)queryLocalInterface;
                }
                else {
                    zzqs = new zzqu(strongBinder);
                }
            }
            this.zzb(zzqs, parcel.readString());
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
