// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;

public abstract class zzrp extends zzek implements zzro
{
    public zzrp() {
        super("com.google.android.gms.ads.internal.formats.client.IUnconfirmedClickListener");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.onUnconfirmedClickReceived(parcel.readString());
                break;
            }
            case 2: {
                this.onUnconfirmedClickCancelled();
                break;
            }
        }
        parcel2.writeNoException();
        return true;
    }
}
