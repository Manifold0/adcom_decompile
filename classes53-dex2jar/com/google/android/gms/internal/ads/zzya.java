// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import java.util.List;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.IInterface;
import android.os.Parcel;

public abstract class zzya extends zzek implements zzxz
{
    public zzya() {
        super("com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 2: {
                final String headline = this.getHeadline();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            }
            case 3: {
                final List images = this.getImages();
                parcel2.writeNoException();
                parcel2.writeList(images);
                break;
            }
            case 4: {
                final String body = this.getBody();
                parcel2.writeNoException();
                parcel2.writeString(body);
                break;
            }
            case 5: {
                final zzpw zzjz = this.zzjz();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzjz);
                break;
            }
            case 6: {
                final String callToAction = this.getCallToAction();
                parcel2.writeNoException();
                parcel2.writeString(callToAction);
                break;
            }
            case 7: {
                final double starRating = this.getStarRating();
                parcel2.writeNoException();
                parcel2.writeDouble(starRating);
                break;
            }
            case 8: {
                final String store = this.getStore();
                parcel2.writeNoException();
                parcel2.writeString(store);
                break;
            }
            case 9: {
                final String price = this.getPrice();
                parcel2.writeNoException();
                parcel2.writeString(price);
                break;
            }
            case 10: {
                this.recordImpression();
                parcel2.writeNoException();
                break;
            }
            case 11: {
                this.zzj(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 12: {
                this.zzk(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 13: {
                final boolean overrideImpressionRecording = this.getOverrideImpressionRecording();
                parcel2.writeNoException();
                zzel.zza(parcel2, overrideImpressionRecording);
                break;
            }
            case 14: {
                final boolean overrideClickHandling = this.getOverrideClickHandling();
                parcel2.writeNoException();
                zzel.zza(parcel2, overrideClickHandling);
                break;
            }
            case 15: {
                final Bundle extras = this.getExtras();
                parcel2.writeNoException();
                zzel.zzb(parcel2, (Parcelable)extras);
                break;
            }
            case 16: {
                this.zzl(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 17: {
                final zzlo videoController = this.getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)videoController);
                break;
            }
            case 18: {
                final IObjectWrapper zzmv = this.zzmv();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzmv);
                break;
            }
            case 19: {
                final zzps zzkf = this.zzkf();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzkf);
                break;
            }
            case 20: {
                final IObjectWrapper zzmw = this.zzmw();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzmw);
                break;
            }
            case 21: {
                final IObjectWrapper zzke = this.zzke();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzke);
                break;
            }
            case 22: {
                this.zzb(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
        }
        return true;
    }
}
