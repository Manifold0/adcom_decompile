// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Bundle;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Parcelable;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzkt extends zzek implements zzks
{
    public zzkt() {
        super("com.google.android.gms.ads.internal.client.IAdManager");
    }
    
    public static zzks zzb(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        if (queryLocalInterface instanceof zzks) {
            return (zzks)queryLocalInterface;
        }
        return new zzku(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        final zzla zzla = null;
        final zzke zzke = null;
        final zzlg zzlg = null;
        final zzkx zzkx = null;
        final zzkh zzkh = null;
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                final IObjectWrapper zzbj = this.zzbj();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzbj);
                break;
            }
            case 2: {
                this.destroy();
                parcel2.writeNoException();
                break;
            }
            case 3: {
                final boolean ready = this.isReady();
                parcel2.writeNoException();
                zzel.zza(parcel2, ready);
                break;
            }
            case 4: {
                final boolean zzb = this.zzb((zzjj)zzel.zza(parcel, (Parcelable$Creator)zzjj.CREATOR));
                parcel2.writeNoException();
                zzel.zza(parcel2, zzb);
                break;
            }
            case 5: {
                this.pause();
                parcel2.writeNoException();
                break;
            }
            case 6: {
                this.resume();
                parcel2.writeNoException();
                break;
            }
            case 7: {
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
                this.zza(zzkh2);
                parcel2.writeNoException();
                break;
            }
            case 8: {
                final IBinder strongBinder2 = parcel.readStrongBinder();
                zzla zzla2;
                if (strongBinder2 == null) {
                    zzla2 = zzla;
                }
                else {
                    final IInterface queryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
                    if (queryLocalInterface2 instanceof zzla) {
                        zzla2 = (zzla)queryLocalInterface2;
                    }
                    else {
                        zzla2 = new zzlc(strongBinder2);
                    }
                }
                this.zza(zzla2);
                parcel2.writeNoException();
                break;
            }
            case 9: {
                this.showInterstitial();
                parcel2.writeNoException();
                break;
            }
            case 10: {
                this.stopLoading();
                parcel2.writeNoException();
                break;
            }
            case 11: {
                this.zzbm();
                parcel2.writeNoException();
                break;
            }
            case 12: {
                final zzjn zzbk = this.zzbk();
                parcel2.writeNoException();
                zzel.zzb(parcel2, (Parcelable)zzbk);
                break;
            }
            case 13: {
                this.zza((zzjn)zzel.zza(parcel, (Parcelable$Creator)zzjn.CREATOR));
                parcel2.writeNoException();
                break;
            }
            case 14: {
                this.zza(zzaax.zzv(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 15: {
                this.zza(zzabd.zzx(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                break;
            }
            case 18: {
                final String mediationAdapterClassName = this.getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            }
            case 19: {
                this.zza(zzoe.zzf(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 20: {
                final IBinder strongBinder3 = parcel.readStrongBinder();
                zzke zzke2;
                if (strongBinder3 == null) {
                    zzke2 = zzke;
                }
                else {
                    final IInterface queryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdClickListener");
                    if (queryLocalInterface3 instanceof zzke) {
                        zzke2 = (zzke)queryLocalInterface3;
                    }
                    else {
                        zzke2 = new zzkg(strongBinder3);
                    }
                }
                this.zza(zzke2);
                parcel2.writeNoException();
                break;
            }
            case 21: {
                final IBinder strongBinder4 = parcel.readStrongBinder();
                zzlg zzlg2;
                if (strongBinder4 == null) {
                    zzlg2 = zzlg;
                }
                else {
                    final IInterface queryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    if (queryLocalInterface4 instanceof zzlg) {
                        zzlg2 = (zzlg)queryLocalInterface4;
                    }
                    else {
                        zzlg2 = new zzli(strongBinder4);
                    }
                }
                this.zza(zzlg2);
                parcel2.writeNoException();
                break;
            }
            case 22: {
                this.setManualImpressionsEnabled(zzel.zza(parcel));
                parcel2.writeNoException();
                break;
            }
            case 23: {
                final boolean loading = this.isLoading();
                parcel2.writeNoException();
                zzel.zza(parcel2, loading);
                break;
            }
            case 24: {
                this.zza(zzahf.zzz(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 25: {
                this.setUserId(parcel.readString());
                parcel2.writeNoException();
                break;
            }
            case 26: {
                final zzlo videoController = this.getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)videoController);
                break;
            }
            case 29: {
                this.zza((zzmu)zzel.zza(parcel, (Parcelable$Creator)zzmu.CREATOR));
                parcel2.writeNoException();
                break;
            }
            case 30: {
                this.zza((zzlu)zzel.zza(parcel, (Parcelable$Creator)zzlu.CREATOR));
                parcel2.writeNoException();
                break;
            }
            case 31: {
                final String adUnitId = this.getAdUnitId();
                parcel2.writeNoException();
                parcel2.writeString(adUnitId);
                break;
            }
            case 32: {
                final zzla zzbw = this.zzbw();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzbw);
                break;
            }
            case 33: {
                final zzkh zzbx = this.zzbx();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzbx);
                break;
            }
            case 34: {
                this.setImmersiveMode(zzel.zza(parcel));
                parcel2.writeNoException();
                break;
            }
            case 35: {
                final String zzck = this.zzck();
                parcel2.writeNoException();
                parcel2.writeString(zzck);
                break;
            }
            case 36: {
                final IBinder strongBinder5 = parcel.readStrongBinder();
                zzkx zzkx2;
                if (strongBinder5 == null) {
                    zzkx2 = zzkx;
                }
                else {
                    final IInterface queryLocalInterface5 = strongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdMetadataListener");
                    if (queryLocalInterface5 instanceof zzkx) {
                        zzkx2 = (zzkx)queryLocalInterface5;
                    }
                    else {
                        zzkx2 = new zzkz(strongBinder5);
                    }
                }
                this.zza(zzkx2);
                parcel2.writeNoException();
                break;
            }
            case 37: {
                final Bundle zzba = this.zzba();
                parcel2.writeNoException();
                zzel.zzb(parcel2, (Parcelable)zzba);
                break;
            }
        }
        return true;
    }
}
