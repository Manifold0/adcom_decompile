// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.IInterface;
import java.util.ArrayList;
import android.os.Parcel;
import java.util.List;
import android.os.RemoteException;
import android.os.IBinder;

public final class zzqu extends zzej implements zzqs
{
    zzqu(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }
    
    public final void destroy() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(8, this.obtainAndWriteInterfaceToken());
    }
    
    public final List<String> getAvailableAssetNames() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        final ArrayList stringArrayList = transactAndReadException.createStringArrayList();
        transactAndReadException.recycle();
        return (List<String>)stringArrayList;
    }
    
    public final String getCustomTemplateId() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(4, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final zzlo getVideoController() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(7, this.obtainAndWriteInterfaceToken());
        final zzlo zze = zzlp.zze(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zze;
    }
    
    public final void performClick(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
    
    public final void recordImpression() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(6, this.obtainAndWriteInterfaceToken());
    }
    
    public final String zzao(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        final Parcel transactAndReadException = this.transactAndReadException(1, obtainAndWriteInterfaceToken);
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final zzpw zzap(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        final Parcel transactAndReadException = this.transactAndReadException(2, obtainAndWriteInterfaceToken);
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
    
    public final boolean zzh(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        final Parcel transactAndReadException = this.transactAndReadException(10, obtainAndWriteInterfaceToken);
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final IObjectWrapper zzka() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(11, this.obtainAndWriteInterfaceToken());
        final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return interface1;
    }
    
    public final IObjectWrapper zzkh() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(9, this.obtainAndWriteInterfaceToken());
        final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return interface1;
    }
}
