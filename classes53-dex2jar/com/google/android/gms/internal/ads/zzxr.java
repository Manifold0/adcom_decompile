// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Parcelable;
import java.util.List;
import android.os.IInterface;
import android.os.Parcelable$Creator;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.Parcel;

public abstract class zzxr extends zzek implements zzxq
{
    public zzxr() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        final zzxt zzxt = null;
        final zzxt zzxt2 = null;
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(parcel.readStrongBinder());
                final zzjn zzjn = (zzjn)zzel.zza(parcel, (Parcelable$Creator)com.google.android.gms.internal.ads.zzjn.CREATOR);
                final zzjj zzjj = (zzjj)zzel.zza(parcel, (Parcelable$Creator)com.google.android.gms.internal.ads.zzjj.CREATOR);
                final String string = parcel.readString();
                final IBinder strongBinder = parcel.readStrongBinder();
                zzxt zzxt3;
                if (strongBinder == null) {
                    zzxt3 = null;
                }
                else {
                    final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface instanceof zzxt) {
                        zzxt3 = (zzxt)queryLocalInterface;
                    }
                    else {
                        zzxt3 = new zzxv(strongBinder);
                    }
                }
                this.zza(interface1, zzjn, zzjj, string, zzxt3);
                parcel2.writeNoException();
                break;
            }
            case 2: {
                final IObjectWrapper view = this.getView();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)view);
                break;
            }
            case 3: {
                final IObjectWrapper interface2 = IObjectWrapper$Stub.asInterface(parcel.readStrongBinder());
                final zzjj zzjj2 = (zzjj)zzel.zza(parcel, (Parcelable$Creator)zzjj.CREATOR);
                final String string2 = parcel.readString();
                final IBinder strongBinder2 = parcel.readStrongBinder();
                zzxt zzxt4;
                if (strongBinder2 == null) {
                    zzxt4 = zzxt2;
                }
                else {
                    final IInterface queryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface2 instanceof zzxt) {
                        zzxt4 = (zzxt)queryLocalInterface2;
                    }
                    else {
                        zzxt4 = new zzxv(strongBinder2);
                    }
                }
                this.zza(interface2, zzjj2, string2, zzxt4);
                parcel2.writeNoException();
                break;
            }
            case 4: {
                this.showInterstitial();
                parcel2.writeNoException();
                break;
            }
            case 5: {
                this.destroy();
                parcel2.writeNoException();
                break;
            }
            case 6: {
                final IObjectWrapper interface3 = IObjectWrapper$Stub.asInterface(parcel.readStrongBinder());
                final zzjn zzjn2 = (zzjn)zzel.zza(parcel, (Parcelable$Creator)zzjn.CREATOR);
                final zzjj zzjj3 = (zzjj)zzel.zza(parcel, (Parcelable$Creator)zzjj.CREATOR);
                final String string3 = parcel.readString();
                final String string4 = parcel.readString();
                final IBinder strongBinder3 = parcel.readStrongBinder();
                zzxt zzxt5;
                if (strongBinder3 == null) {
                    zzxt5 = zzxt;
                }
                else {
                    final IInterface queryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface3 instanceof zzxt) {
                        zzxt5 = (zzxt)queryLocalInterface3;
                    }
                    else {
                        zzxt5 = new zzxv(strongBinder3);
                    }
                }
                this.zza(interface3, zzjn2, zzjj3, string3, string4, zzxt5);
                parcel2.writeNoException();
                break;
            }
            case 7: {
                final IObjectWrapper interface4 = IObjectWrapper$Stub.asInterface(parcel.readStrongBinder());
                final zzjj zzjj4 = (zzjj)zzel.zza(parcel, (Parcelable$Creator)zzjj.CREATOR);
                final String string5 = parcel.readString();
                final String string6 = parcel.readString();
                final IBinder strongBinder4 = parcel.readStrongBinder();
                zzxt zzxt6;
                if (strongBinder4 == null) {
                    zzxt6 = null;
                }
                else {
                    final IInterface queryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface4 instanceof zzxt) {
                        zzxt6 = (zzxt)queryLocalInterface4;
                    }
                    else {
                        zzxt6 = new zzxv(strongBinder4);
                    }
                }
                this.zza(interface4, zzjj4, string5, string6, zzxt6);
                parcel2.writeNoException();
                break;
            }
            case 8: {
                this.pause();
                parcel2.writeNoException();
                break;
            }
            case 9: {
                this.resume();
                parcel2.writeNoException();
                break;
            }
            case 10: {
                this.zza(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), (zzjj)zzel.zza(parcel, (Parcelable$Creator)zzjj.CREATOR), parcel.readString(), zzaid.zzaa(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                break;
            }
            case 11: {
                this.zzc((zzjj)zzel.zza(parcel, (Parcelable$Creator)zzjj.CREATOR), parcel.readString());
                parcel2.writeNoException();
                break;
            }
            case 12: {
                this.showVideo();
                parcel2.writeNoException();
                break;
            }
            case 13: {
                final boolean initialized = this.isInitialized();
                parcel2.writeNoException();
                zzel.zza(parcel2, initialized);
                break;
            }
            case 14: {
                final IObjectWrapper interface5 = IObjectWrapper$Stub.asInterface(parcel.readStrongBinder());
                final zzjj zzjj5 = (zzjj)zzel.zza(parcel, (Parcelable$Creator)zzjj.CREATOR);
                final String string7 = parcel.readString();
                final String string8 = parcel.readString();
                final IBinder strongBinder5 = parcel.readStrongBinder();
                zzxt zzxt7;
                if (strongBinder5 == null) {
                    zzxt7 = null;
                }
                else {
                    final IInterface queryLocalInterface5 = strongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener");
                    if (queryLocalInterface5 instanceof zzxt) {
                        zzxt7 = (zzxt)queryLocalInterface5;
                    }
                    else {
                        zzxt7 = new zzxv(strongBinder5);
                    }
                }
                this.zza(interface5, zzjj5, string7, string8, zzxt7, (zzpl)zzel.zza(parcel, (Parcelable$Creator)zzpl.CREATOR), parcel.createStringArrayList());
                parcel2.writeNoException();
                break;
            }
            case 15: {
                final zzxz zzmo = this.zzmo();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzmo);
                break;
            }
            case 16: {
                final zzyc zzmp = this.zzmp();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzmp);
                break;
            }
            case 17: {
                final Bundle zzmq = this.zzmq();
                parcel2.writeNoException();
                zzel.zzb(parcel2, (Parcelable)zzmq);
                break;
            }
            case 18: {
                final Bundle interstitialAdapterInfo = this.getInterstitialAdapterInfo();
                parcel2.writeNoException();
                zzel.zzb(parcel2, (Parcelable)interstitialAdapterInfo);
                break;
            }
            case 19: {
                final Bundle zzmr = this.zzmr();
                parcel2.writeNoException();
                zzel.zzb(parcel2, (Parcelable)zzmr);
                break;
            }
            case 20: {
                this.zza((zzjj)zzel.zza(parcel, (Parcelable$Creator)zzjj.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            }
            case 21: {
                this.zzi(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 22: {
                final boolean zzms = this.zzms();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzms);
                break;
            }
            case 23: {
                this.zza(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), zzaid.zzaa(parcel.readStrongBinder()), parcel.createStringArrayList());
                parcel2.writeNoException();
                break;
            }
            case 24: {
                final zzqs zzmt = this.zzmt();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzmt);
                break;
            }
            case 25: {
                this.setImmersiveMode(zzel.zza(parcel));
                parcel2.writeNoException();
                break;
            }
            case 26: {
                final zzlo videoController = this.getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)videoController);
                break;
            }
            case 27: {
                final zzyf zzmu = this.zzmu();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzmu);
                break;
            }
        }
        return true;
    }
}
