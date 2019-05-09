// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zzmh extends zzkl
{
    final /* synthetic */ zzmf zzati;
    
    private zzmh(final zzmf zzati) {
        this.zzati = zzati;
    }
    
    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }
    
    public final boolean isLoading() throws RemoteException {
        return false;
    }
    
    public final void zza(final zzjj zzjj, final int n) throws RemoteException {
        zzane.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzamu.zzsy.post((Runnable)new zzmi(this));
    }
    
    public final String zzck() throws RemoteException {
        return null;
    }
    
    public final void zzd(final zzjj zzjj) throws RemoteException {
        this.zza(zzjj, 1);
    }
}
