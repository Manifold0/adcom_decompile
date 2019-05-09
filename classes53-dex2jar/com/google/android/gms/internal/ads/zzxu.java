// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzxu extends zzek implements zzxt
{
    public zzxu() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
    }
    
    public static zzxt zzs(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
        if (queryLocalInterface instanceof zzxt) {
            return (zzxt)queryLocalInterface;
        }
        return new zzxv(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.onAdClicked();
                break;
            }
            case 2: {
                this.onAdClosed();
                break;
            }
            case 3: {
                this.onAdFailedToLoad(parcel.readInt());
                break;
            }
            case 4: {
                this.onAdLeftApplication();
                break;
            }
            case 5: {
                this.onAdOpened();
                break;
            }
            case 6: {
                this.onAdLoaded();
                break;
            }
            case 7: {
                final IBinder strongBinder = parcel.readStrongBinder();
                zzxw zzxw;
                if (strongBinder == null) {
                    zzxw = null;
                }
                else {
                    final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationResponseMetadata");
                    if (queryLocalInterface instanceof zzxw) {
                        zzxw = (zzxw)queryLocalInterface;
                    }
                    else {
                        zzxw = new zzxy(strongBinder);
                    }
                }
                this.zza(zzxw);
                break;
            }
            case 8: {
                this.onAdImpression();
                break;
            }
            case 9: {
                this.onAppEvent(parcel.readString(), parcel.readString());
                break;
            }
            case 10: {
                this.zzb(zzqt.zzk(parcel.readStrongBinder()), parcel.readString());
                break;
            }
            case 11: {
                this.onVideoEnd();
                break;
            }
            case 12: {
                this.zzbj(parcel.readString());
                break;
            }
        }
        parcel2.writeNoException();
        return true;
    }
}
