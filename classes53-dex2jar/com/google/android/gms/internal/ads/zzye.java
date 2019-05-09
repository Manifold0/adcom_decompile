// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzye extends zzej implements zzyc
{
    zzye(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
    }
    
    public final String getAdvertiser() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(7, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final String getBody() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(4, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final String getCallToAction() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(6, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final Bundle getExtras() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(13, this.obtainAndWriteInterfaceToken());
        final Bundle bundle = (Bundle)zzel.zza(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }
    
    public final String getHeadline() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final List getImages() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        final ArrayList zzb = zzel.zzb(transactAndReadException);
        transactAndReadException.recycle();
        return zzb;
    }
    
    public final boolean getOverrideClickHandling() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(12, this.obtainAndWriteInterfaceToken());
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final boolean getOverrideImpressionRecording() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(11, this.obtainAndWriteInterfaceToken());
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final zzlo getVideoController() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(16, this.obtainAndWriteInterfaceToken());
        final zzlo zze = zzlp.zze(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zze;
    }
    
    public final void recordImpression() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(8, this.obtainAndWriteInterfaceToken());
    }
    
    public final void zzb(final IObjectWrapper objectWrapper, final IObjectWrapper objectWrapper2, final IObjectWrapper objectWrapper3) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper2);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper3);
        this.transactAndReadExceptionReturnVoid(22, obtainAndWriteInterfaceToken);
    }
    
    public final void zzj(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }
    
    public final void zzk(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(10, obtainAndWriteInterfaceToken);
    }
    
    public final IObjectWrapper zzke() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(21, this.obtainAndWriteInterfaceToken());
        final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return interface1;
    }
    
    public final zzps zzkf() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(19, this.obtainAndWriteInterfaceToken());
        final zzps zzg = zzpt.zzg(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzg;
    }
    
    public final zzpw zzkg() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(5, this.obtainAndWriteInterfaceToken());
        final zzpw zzh = zzpx.zzh(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzh;
    }
    
    public final void zzl(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken);
    }
    
    public final IObjectWrapper zzmv() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(15, this.obtainAndWriteInterfaceToken());
        final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return interface1;
    }
    
    public final IObjectWrapper zzmw() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(20, this.obtainAndWriteInterfaceToken());
        final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return interface1;
    }
}
