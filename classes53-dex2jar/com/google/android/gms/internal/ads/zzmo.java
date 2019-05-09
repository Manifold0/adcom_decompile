// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import android.os.RemoteException;

public final class zzmo extends zzaha
{
    private zzahe zzatl;
    
    public final void destroy() throws RemoteException {
    }
    
    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }
    
    public final boolean isLoaded() throws RemoteException {
        return false;
    }
    
    public final void pause() throws RemoteException {
    }
    
    public final void resume() throws RemoteException {
    }
    
    public final void setImmersiveMode(final boolean b) throws RemoteException {
    }
    
    public final void setUserId(final String s) throws RemoteException {
    }
    
    public final void show() throws RemoteException {
    }
    
    public final void zza(final zzagx zzagx) throws RemoteException {
    }
    
    public final void zza(final zzahe zzatl) throws RemoteException {
        this.zzatl = zzatl;
    }
    
    public final void zza(final zzahk zzahk) throws RemoteException {
        zzane.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzamu.zzsy.post((Runnable)new zzmp(this));
    }
    
    public final void zza(final zzkx zzkx) throws RemoteException {
    }
    
    public final Bundle zzba() throws RemoteException {
        return new Bundle();
    }
    
    public final void zzd(final IObjectWrapper objectWrapper) throws RemoteException {
    }
    
    public final void zze(final IObjectWrapper objectWrapper) throws RemoteException {
    }
    
    public final void zzf(final IObjectWrapper objectWrapper) throws RemoteException {
    }
}
