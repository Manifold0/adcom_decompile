// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;

public abstract class zzki extends zzek implements zzkh
{
    public zzki() {
        super("com.google.android.gms.ads.internal.client.IAdListener");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.onAdClosed();
                break;
            }
            case 2: {
                this.onAdFailedToLoad(parcel.readInt());
                break;
            }
            case 3: {
                this.onAdLeftApplication();
                break;
            }
            case 4: {
                this.onAdLoaded();
                break;
            }
            case 5: {
                this.onAdOpened();
                break;
            }
            case 6: {
                this.onAdClicked();
                break;
            }
            case 7: {
                this.onAdImpression();
                break;
            }
        }
        parcel2.writeNoException();
        return true;
    }
}
