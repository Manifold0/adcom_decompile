// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.zzal;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.common.api.internal.IStatusCallback;
import android.app.PendingIntent;
import android.os.RemoteException;
import android.location.Location;
import android.os.IInterface;

public interface zzao extends IInterface
{
    Location zza(final String p0) throws RemoteException;
    
    void zza(final long p0, final boolean p1, final PendingIntent p2) throws RemoteException;
    
    void zza(final PendingIntent p0, final IStatusCallback p1) throws RemoteException;
    
    void zza(final Location p0) throws RemoteException;
    
    void zza(final zzaj p0) throws RemoteException;
    
    void zza(final zzbf p0) throws RemoteException;
    
    void zza(final zzo p0) throws RemoteException;
    
    void zza(final ActivityTransitionRequest p0, final PendingIntent p1, final IStatusCallback p2) throws RemoteException;
    
    void zza(final GeofencingRequest p0, final PendingIntent p1, final zzam p2) throws RemoteException;
    
    void zza(final LocationSettingsRequest p0, final zzaq p1, final String p2) throws RemoteException;
    
    void zza(final zzal p0, final zzam p1) throws RemoteException;
    
    void zza(final boolean p0) throws RemoteException;
    
    LocationAvailability zzb(final String p0) throws RemoteException;
    
    void zzb(final PendingIntent p0) throws RemoteException;
}
