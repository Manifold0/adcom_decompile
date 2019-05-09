// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.accounttransfer.zzt;
import com.google.android.gms.auth.api.accounttransfer.zzl;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import android.os.IInterface;

public interface zzx extends IInterface
{
    void onFailure(final Status p0) throws RemoteException;
    
    void zza(final DeviceMetaData p0) throws RemoteException;
    
    void zza(final Status p0, final zzl p1) throws RemoteException;
    
    void zza(final Status p0, final zzt p1) throws RemoteException;
    
    void zza(final byte[] p0) throws RemoteException;
    
    void zzb(final Status p0) throws RemoteException;
    
    void zzd() throws RemoteException;
}
