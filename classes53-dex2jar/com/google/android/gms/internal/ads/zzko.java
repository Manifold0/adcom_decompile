// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.IBinder;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import android.os.Parcelable$Creator;
import android.os.IInterface;
import android.os.Parcel;

public abstract class zzko extends zzek implements zzkn
{
    public zzko() {
        super("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        final zzlg zzlg = null;
        final zzkh zzkh = null;
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                final zzkk zzdh = this.zzdh();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzdh);
                break;
            }
            case 2: {
                final IBinder strongBinder = parcel.readStrongBinder();
                zzkh zzkh2;
                if (strongBinder == null) {
                    zzkh2 = zzkh;
                }
                else {
                    final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    if (queryLocalInterface instanceof zzkh) {
                        zzkh2 = (zzkh)queryLocalInterface;
                    }
                    else {
                        zzkh2 = new zzkj(strongBinder);
                    }
                }
                this.zzb(zzkh2);
                parcel2.writeNoException();
                break;
            }
            case 3: {
                this.zza(zzqx.zzl(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 4: {
                this.zza(zzra.zzm(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 5: {
                this.zza(parcel.readString(), zzrg.zzo(parcel.readStrongBinder()), zzrd.zzn(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 6: {
                this.zza((zzpl)zzel.zza(parcel, (Parcelable$Creator)zzpl.CREATOR));
                parcel2.writeNoException();
                break;
            }
            case 7: {
                final IBinder strongBinder2 = parcel.readStrongBinder();
                zzlg zzlg2;
                if (strongBinder2 == null) {
                    zzlg2 = zzlg;
                }
                else {
                    final IInterface queryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    if (queryLocalInterface2 instanceof zzlg) {
                        zzlg2 = (zzlg)queryLocalInterface2;
                    }
                    else {
                        zzlg2 = new zzli(strongBinder2);
                    }
                }
                this.zzb(zzlg2);
                parcel2.writeNoException();
                break;
            }
            case 8: {
                this.zza(zzrj.zzp(parcel.readStrongBinder()), (zzjn)zzel.zza(parcel, (Parcelable$Creator)zzjn.CREATOR));
                parcel2.writeNoException();
                break;
            }
            case 9: {
                this.zza((PublisherAdViewOptions)zzel.zza(parcel, (Parcelable$Creator)PublisherAdViewOptions.CREATOR));
                parcel2.writeNoException();
                break;
            }
            case 10: {
                this.zza(zzrm.zzq(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
        }
        return true;
    }
}
