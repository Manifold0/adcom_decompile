// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.IBinder;

public final class zzqq extends zzej implements zzqo
{
    zzqq(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.formats.client.INativeContentAd");
    }
    
    public final void destroy() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(10, this.obtainAndWriteInterfaceToken());
    }
    
    public final String getAdvertiser() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(8, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final String getBody() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(5, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final String getCallToAction() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(7, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final Bundle getExtras() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(9, this.obtainAndWriteInterfaceToken());
        final Bundle bundle = (Bundle)zzel.zza(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }
    
    public final String getHeadline() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final List getImages() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(4, this.obtainAndWriteInterfaceToken());
        final ArrayList zzb = zzel.zzb(transactAndReadException);
        transactAndReadException.recycle();
        return zzb;
    }
    
    public final String getMediationAdapterClassName() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(17, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final zzlo getVideoController() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(11, this.obtainAndWriteInterfaceToken());
        final zzlo zze = zzlp.zze(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zze;
    }
    
    public final void performClick(final Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        this.transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken);
    }
    
    public final boolean recordImpression(final Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        final Parcel transactAndReadException = this.transactAndReadException(13, obtainAndWriteInterfaceToken);
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final void reportTouchEvent(final Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        this.transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken);
    }
    
    public final IObjectWrapper zzka() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return interface1;
    }
    
    public final IObjectWrapper zzke() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(16, this.obtainAndWriteInterfaceToken());
        final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return interface1;
    }
    
    public final zzps zzkf() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(15, this.obtainAndWriteInterfaceToken());
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzps zzps;
        if (strongBinder == null) {
            zzps = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
            if (queryLocalInterface instanceof zzps) {
                zzps = (zzps)queryLocalInterface;
            }
            else {
                zzps = new zzpu(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzps;
    }
    
    public final zzpw zzkg() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(6, this.obtainAndWriteInterfaceToken());
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzpw zzpw;
        if (strongBinder == null) {
            zzpw = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
            if (queryLocalInterface instanceof zzpw) {
                zzpw = (zzpw)queryLocalInterface;
            }
            else {
                zzpw = new zzpy(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzpw;
    }
}
