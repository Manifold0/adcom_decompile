// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;

public abstract class zzkf extends zzek implements zzke
{
    public zzkf() {
        super("com.google.android.gms.ads.internal.client.IAdClickListener");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            this.onAdClicked();
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
