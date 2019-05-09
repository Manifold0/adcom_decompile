// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzks extends IInterface
{
    void destroy() throws RemoteException;
    
    String getAdUnitId() throws RemoteException;
    
    String getMediationAdapterClassName() throws RemoteException;
    
    zzlo getVideoController() throws RemoteException;
    
    boolean isLoading() throws RemoteException;
    
    boolean isReady() throws RemoteException;
    
    void pause() throws RemoteException;
    
    void resume() throws RemoteException;
    
    void setImmersiveMode(final boolean p0) throws RemoteException;
    
    void setManualImpressionsEnabled(final boolean p0) throws RemoteException;
    
    void setUserId(final String p0) throws RemoteException;
    
    void showInterstitial() throws RemoteException;
    
    void stopLoading() throws RemoteException;
    
    void zza(final zzaaw p0) throws RemoteException;
    
    void zza(final zzabc p0, final String p1) throws RemoteException;
    
    void zza(final zzahe p0) throws RemoteException;
    
    void zza(final zzjn p0) throws RemoteException;
    
    void zza(final zzke p0) throws RemoteException;
    
    void zza(final zzkh p0) throws RemoteException;
    
    void zza(final zzkx p0) throws RemoteException;
    
    void zza(final zzla p0) throws RemoteException;
    
    void zza(final zzlg p0) throws RemoteException;
    
    void zza(final zzlu p0) throws RemoteException;
    
    void zza(final zzmu p0) throws RemoteException;
    
    void zza(final zzod p0) throws RemoteException;
    
    boolean zzb(final zzjj p0) throws RemoteException;
    
    Bundle zzba() throws RemoteException;
    
    IObjectWrapper zzbj() throws RemoteException;
    
    zzjn zzbk() throws RemoteException;
    
    void zzbm() throws RemoteException;
    
    zzla zzbw() throws RemoteException;
    
    zzkh zzbx() throws RemoteException;
    
    String zzck() throws RemoteException;
}
