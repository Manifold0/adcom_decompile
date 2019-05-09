// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.content.Intent;
import android.os.IBinder;

public final class zzaar extends zzej implements zzaap
{
    zzaar(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    }
    
    public final void onActivityResult(final int n, final int n2, final Intent intent) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(n);
        obtainAndWriteInterfaceToken.writeInt(n2);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)intent);
        this.transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken);
    }
    
    public final void onBackPressed() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(10, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onCreate(final Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
    
    public final void onDestroy() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(8, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onPause() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(5, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onRestart() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(2, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onResume() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(4, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onSaveInstanceState(final Bundle bundle) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        final Parcel transactAndReadException = this.transactAndReadException(6, obtainAndWriteInterfaceToken);
        if (transactAndReadException.readInt() != 0) {
            bundle.readFromParcel(transactAndReadException);
        }
        transactAndReadException.recycle();
    }
    
    public final void onStart() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(3, this.obtainAndWriteInterfaceToken());
    }
    
    public final void onStop() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(7, this.obtainAndWriteInterfaceToken());
    }
    
    public final void zzax() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(9, this.obtainAndWriteInterfaceToken());
    }
    
    public final boolean zznj() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(11, this.obtainAndWriteInterfaceToken());
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final void zzo(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }
}
