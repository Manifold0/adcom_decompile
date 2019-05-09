// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.zzal;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.ActivityTransitionRequest;
import android.os.IInterface;
import com.google.android.gms.common.api.internal.IStatusCallback;
import android.os.Parcelable;
import android.app.PendingIntent;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.location.Location;
import android.os.IBinder;

public final class zzap extends zza implements zzao
{
    zzap(final IBinder binder) {
        super(binder, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
    }
    
    @Override
    public final Location zza(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        final Parcel transactAndReadException = this.transactAndReadException(21, obtainAndWriteInterfaceToken);
        final Location location = zzc.zza(transactAndReadException, (android.os.Parcelable$Creator<Location>)Location.CREATOR);
        transactAndReadException.recycle();
        return location;
    }
    
    @Override
    public final void zza(final long n, final boolean b, final PendingIntent pendingIntent) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeLong(n);
        zzc.zza(obtainAndWriteInterfaceToken, true);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)pendingIntent);
        this.transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final PendingIntent pendingIntent, final IStatusCallback statusCallback) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)pendingIntent);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)statusCallback);
        this.transactAndReadExceptionReturnVoid(73, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final Location location) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)location);
        this.transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzaj zzaj) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzaj);
        this.transactAndReadExceptionReturnVoid(67, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzbf zzbf) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzbf);
        this.transactAndReadExceptionReturnVoid(59, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzo zzo) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzo);
        this.transactAndReadExceptionReturnVoid(75, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final ActivityTransitionRequest activityTransitionRequest, final PendingIntent pendingIntent, final IStatusCallback statusCallback) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)activityTransitionRequest);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)pendingIntent);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)statusCallback);
        this.transactAndReadExceptionReturnVoid(72, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final GeofencingRequest geofencingRequest, final PendingIntent pendingIntent, final zzam zzam) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)geofencingRequest);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)pendingIntent);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzam);
        this.transactAndReadExceptionReturnVoid(57, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final LocationSettingsRequest locationSettingsRequest, final zzaq zzaq, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)locationSettingsRequest);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzaq);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(63, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzal zzal, final zzam zzam) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzal);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzam);
        this.transactAndReadExceptionReturnVoid(74, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final LocationAvailability zzb(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        final Parcel transactAndReadException = this.transactAndReadException(34, obtainAndWriteInterfaceToken);
        final LocationAvailability locationAvailability = zzc.zza(transactAndReadException, LocationAvailability.CREATOR);
        transactAndReadException.recycle();
        return locationAvailability;
    }
    
    @Override
    public final void zzb(final PendingIntent pendingIntent) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)pendingIntent);
        this.transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }
}
