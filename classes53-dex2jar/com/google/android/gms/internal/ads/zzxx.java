// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;

public abstract class zzxx extends zzek implements zzxw
{
    public zzxx() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
    }
    
    protected final boolean dispatchTransaction(int zzmm, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
        if (zzmm == 1) {
            zzmm = this.zzmm();
            parcel2.writeNoException();
            parcel2.writeInt(zzmm);
            return true;
        }
        return false;
    }
}
