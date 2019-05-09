// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.util.List;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.IInterface;
import android.os.Parcel;

public abstract class zzqp extends zzek implements zzqo
{
    public zzqp() {
        super("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 2: {
                final IObjectWrapper zzka = this.zzka();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzka);
                break;
            }
            case 3: {
                final String headline = this.getHeadline();
                parcel2.writeNoException();
                parcel2.writeString(headline);
                break;
            }
            case 4: {
                final List images = this.getImages();
                parcel2.writeNoException();
                parcel2.writeList(images);
                break;
            }
            case 5: {
                final String body = this.getBody();
                parcel2.writeNoException();
                parcel2.writeString(body);
                break;
            }
            case 6: {
                final zzpw zzkg = this.zzkg();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzkg);
                break;
            }
            case 7: {
                final String callToAction = this.getCallToAction();
                parcel2.writeNoException();
                parcel2.writeString(callToAction);
                break;
            }
            case 8: {
                final String advertiser = this.getAdvertiser();
                parcel2.writeNoException();
                parcel2.writeString(advertiser);
                break;
            }
            case 9: {
                final Bundle extras = this.getExtras();
                parcel2.writeNoException();
                zzel.zzb(parcel2, (Parcelable)extras);
                break;
            }
            case 10: {
                this.destroy();
                parcel2.writeNoException();
                break;
            }
            case 11: {
                final zzlo videoController = this.getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)videoController);
                break;
            }
            case 12: {
                this.performClick((Bundle)zzel.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            }
            case 13: {
                final boolean recordImpression = this.recordImpression((Bundle)zzel.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                zzel.zza(parcel2, recordImpression);
                break;
            }
            case 14: {
                this.reportTouchEvent((Bundle)zzel.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            }
            case 15: {
                final zzps zzkf = this.zzkf();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzkf);
                break;
            }
            case 16: {
                final IObjectWrapper zzke = this.zzke();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzke);
                break;
            }
            case 17: {
                final String mediationAdapterClassName = this.getMediationAdapterClassName();
                parcel2.writeNoException();
                parcel2.writeString(mediationAdapterClassName);
                break;
            }
        }
        return true;
    }
}
