// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzahf extends zzek implements zzahe
{
    public zzahf() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
    }
    
    public static zzahe zzz(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        if (queryLocalInterface instanceof zzahe) {
            return (zzahe)queryLocalInterface;
        }
        return new zzahg(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.onRewardedVideoAdLoaded();
                break;
            }
            case 2: {
                this.onRewardedVideoAdOpened();
                break;
            }
            case 3: {
                this.onRewardedVideoStarted();
                break;
            }
            case 4: {
                this.onRewardedVideoAdClosed();
                break;
            }
            case 5: {
                final IBinder strongBinder = parcel.readStrongBinder();
                zzagu zzagu;
                if (strongBinder == null) {
                    zzagu = null;
                }
                else {
                    final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardItem");
                    if (queryLocalInterface instanceof zzagu) {
                        zzagu = (zzagu)queryLocalInterface;
                    }
                    else {
                        zzagu = new zzagw(strongBinder);
                    }
                }
                this.zza(zzagu);
                break;
            }
            case 6: {
                this.onRewardedVideoAdLeftApplication();
                break;
            }
            case 7: {
                this.onRewardedVideoAdFailedToLoad(parcel.readInt());
                break;
            }
            case 8: {
                this.onRewardedVideoCompleted();
                break;
            }
        }
        parcel2.writeNoException();
        return true;
    }
}
