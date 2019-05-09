// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.os.Bundle;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzzk extends zzek implements zzzj
{
    public zzzk() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }
    
    public static zzzj zzt(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
        if (queryLocalInterface instanceof zzzj) {
            return (zzzj)queryLocalInterface;
        }
        return new zzzl(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        final zzzh zzzh = null;
        zzzf zzzf = null;
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(parcel.readStrongBinder());
                final String string = parcel.readString();
                final Bundle bundle = (Bundle)zzel.zza(parcel, Bundle.CREATOR);
                final IBinder strongBinder = parcel.readStrongBinder();
                zzzm zzzm;
                if (strongBinder == null) {
                    zzzm = null;
                }
                else {
                    final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback");
                    if (queryLocalInterface instanceof zzzm) {
                        zzzm = (zzzm)queryLocalInterface;
                    }
                    else {
                        zzzm = new zzzn(strongBinder);
                    }
                }
                this.zza(interface1, string, bundle, zzzm);
                parcel2.writeNoException();
                break;
            }
            case 2: {
                final zzzt zznc = this.zznc();
                parcel2.writeNoException();
                zzel.zzb(parcel2, (Parcelable)zznc);
                break;
            }
            case 3: {
                final zzzt zznd = this.zznd();
                parcel2.writeNoException();
                zzel.zzb(parcel2, (Parcelable)zznd);
                break;
            }
            case 4: {
                final byte[] byteArray = parcel.createByteArray();
                final String string2 = parcel.readString();
                final Bundle bundle2 = (Bundle)zzel.zza(parcel, Bundle.CREATOR);
                final IObjectWrapper interface2 = IObjectWrapper$Stub.asInterface(parcel.readStrongBinder());
                final IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    final IInterface queryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
                    if (queryLocalInterface2 instanceof zzzf) {
                        zzzf = (zzzf)queryLocalInterface2;
                    }
                    else {
                        zzzf = new zzzg(strongBinder2);
                    }
                }
                this.zza(byteArray, string2, bundle2, interface2, zzzf, zzxu.zzs(parcel.readStrongBinder()), (zzjn)zzel.zza(parcel, (Parcelable$Creator)zzjn.CREATOR));
                parcel2.writeNoException();
                break;
            }
            case 5: {
                final zzlo videoController = this.getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)videoController);
                break;
            }
            case 6: {
                final byte[] byteArray2 = parcel.createByteArray();
                final String string3 = parcel.readString();
                final Bundle bundle3 = (Bundle)zzel.zza(parcel, Bundle.CREATOR);
                final IObjectWrapper interface3 = IObjectWrapper$Stub.asInterface(parcel.readStrongBinder());
                final IBinder strongBinder3 = parcel.readStrongBinder();
                zzzh zzzh2;
                if (strongBinder3 == null) {
                    zzzh2 = zzzh;
                }
                else {
                    final IInterface queryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback");
                    if (queryLocalInterface3 instanceof zzzh) {
                        zzzh2 = (zzzh)queryLocalInterface3;
                    }
                    else {
                        zzzh2 = new zzzi(strongBinder3);
                    }
                }
                this.zza(byteArray2, string3, bundle3, interface3, zzzh2, zzxu.zzs(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 7: {
                this.showInterstitial();
                parcel2.writeNoException();
                break;
            }
        }
        return true;
    }
}
