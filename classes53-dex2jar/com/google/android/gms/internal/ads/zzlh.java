// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;

public abstract class zzlh extends zzek implements zzlg
{
    public zzlh() {
        super("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        if (n == 1) {
            final long value = this.getValue();
            parcel2.writeNoException();
            parcel2.writeLong(value);
            return true;
        }
        return false;
    }
}
