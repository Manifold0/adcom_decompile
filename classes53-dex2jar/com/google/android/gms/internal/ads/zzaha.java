// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzaha extends zzek implements zzagz
{
    public zzaha() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }
    
    public static zzagz zzy(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        if (queryLocalInterface instanceof zzagz) {
            return (zzagz)queryLocalInterface;
        }
        return new zzahb(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        final zzagx zzagx = null;
        final zzahe zzahe = null;
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zza((zzahk)zzel.zza(parcel, (Parcelable$Creator)zzahk.CREATOR));
                parcel2.writeNoException();
                break;
            }
            case 2: {
                this.show();
                parcel2.writeNoException();
                break;
            }
            case 3: {
                final IBinder strongBinder = parcel.readStrongBinder();
                zzahe zzahe2;
                if (strongBinder == null) {
                    zzahe2 = zzahe;
                }
                else {
                    final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
                    if (queryLocalInterface instanceof zzahe) {
                        zzahe2 = (zzahe)queryLocalInterface;
                    }
                    else {
                        zzahe2 = new zzahg(strongBinder);
                    }
                }
                this.zza(zzahe2);
                parcel2.writeNoException();
                break;
            }
            case 5: {
                final boolean loaded = this.isLoaded();
                parcel2.writeNoException();
                zzel.zza(parcel2, loaded);
                break;
            }
            case 6: {
                this.pause();
                parcel2.writeNoException();
                break;
            }
            case 7: {
                this.resume();
                parcel2.writeNoException();
                break;
            }
            case 8: {
                this.destroy();
                parcel2.writeNoException();
                break;
            }
            case 9: {
                this.zzd(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 10: {
                this.zze(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 11: {
                this.zzf(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 12: {
                final String mediationAdapterClassName = this.getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            }
            case 13: {
                this.setUserId(parcel.readString());
                parcel2.writeNoException();
                break;
            }
            case 14: {
                this.zza(zzky.zzc(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 15: {
                final Bundle zzba = this.zzba();
                parcel2.writeNoException();
                zzel.zzb(parcel2, (Parcelable)zzba);
                break;
            }
            case 16: {
                final IBinder strongBinder2 = parcel.readStrongBinder();
                zzagx zzagx2;
                if (strongBinder2 == null) {
                    zzagx2 = zzagx;
                }
                else {
                    final IInterface queryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedAdSkuListener");
                    if (queryLocalInterface2 instanceof zzagx) {
                        zzagx2 = (zzagx)queryLocalInterface2;
                    }
                    else {
                        zzagx2 = new zzagy(strongBinder2);
                    }
                }
                this.zza(zzagx2);
                parcel2.writeNoException();
                break;
            }
            case 34: {
                this.setImmersiveMode(zzel.zza(parcel));
                parcel2.writeNoException();
                break;
            }
        }
        return true;
    }
}
