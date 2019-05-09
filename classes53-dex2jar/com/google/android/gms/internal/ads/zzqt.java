// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import java.util.List;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzqt extends zzek implements zzqs
{
    public zzqt() {
        super("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }
    
    public static zzqs zzk(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        if (queryLocalInterface instanceof zzqs) {
            return (zzqs)queryLocalInterface;
        }
        return new zzqu(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                final String zzao = this.zzao(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(zzao);
                break;
            }
            case 2: {
                final zzpw zzap = this.zzap(parcel.readString());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzap);
                break;
            }
            case 3: {
                final List<String> availableAssetNames = this.getAvailableAssetNames();
                parcel2.writeNoException();
                parcel2.writeStringList((List)availableAssetNames);
                break;
            }
            case 4: {
                final String customTemplateId = this.getCustomTemplateId();
                parcel2.writeNoException();
                parcel2.writeString(customTemplateId);
                break;
            }
            case 5: {
                this.performClick(parcel.readString());
                parcel2.writeNoException();
                break;
            }
            case 6: {
                this.recordImpression();
                parcel2.writeNoException();
                break;
            }
            case 7: {
                final zzlo videoController = this.getVideoController();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)videoController);
                break;
            }
            case 8: {
                this.destroy();
                parcel2.writeNoException();
                break;
            }
            case 9: {
                final IObjectWrapper zzkh = this.zzkh();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzkh);
                break;
            }
            case 10: {
                final boolean zzh = this.zzh(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzel.zza(parcel2, zzh);
                break;
            }
            case 11: {
                final IObjectWrapper zzka = this.zzka();
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)zzka);
                break;
            }
        }
        return true;
    }
}
