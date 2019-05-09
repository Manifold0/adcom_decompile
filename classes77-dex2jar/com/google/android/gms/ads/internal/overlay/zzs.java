// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.dynamic.IObjectWrapper;
import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import android.os.Bundle;
import android.os.RemoteException;
import android.content.Intent;
import android.app.Activity;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzaaq;

@zzadh
public final class zzs extends zzaaq
{
    private AdOverlayInfoParcel zzbza;
    private boolean zzbzb;
    private boolean zzbzc;
    private Activity zztk;
    
    public zzs(final Activity zztk, final AdOverlayInfoParcel zzbza) {
        this.zzbzb = false;
        this.zzbzc = false;
        this.zzbza = zzbza;
        this.zztk = zztk;
    }
    
    private final void zznr() {
        synchronized (this) {
            if (!this.zzbzc) {
                if (this.zzbza.zzbyn != null) {
                    this.zzbza.zzbyn.zzcb();
                }
                this.zzbzc = true;
            }
        }
    }
    
    public final void onActivityResult(final int n, final int n2, final Intent intent) throws RemoteException {
    }
    
    public final void onBackPressed() throws RemoteException {
    }
    
    public final void onCreate(final Bundle bundle) {
        boolean boolean1 = false;
        if (bundle != null) {
            boolean1 = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        if (this.zzbza == null) {
            this.zztk.finish();
        }
        else {
            if (boolean1) {
                this.zztk.finish();
                return;
            }
            if (bundle == null) {
                if (this.zzbza.zzbym != null) {
                    this.zzbza.zzbym.onAdClicked();
                }
                if (this.zztk.getIntent() != null && this.zztk.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true) && this.zzbza.zzbyn != null) {
                    this.zzbza.zzbyn.zzcc();
                }
            }
            zzbv.zzeh();
            if (!zza.zza((Context)this.zztk, this.zzbza.zzbyl, this.zzbza.zzbyt)) {
                this.zztk.finish();
            }
        }
    }
    
    public final void onDestroy() throws RemoteException {
        if (this.zztk.isFinishing()) {
            this.zznr();
        }
    }
    
    public final void onPause() throws RemoteException {
        if (this.zzbza.zzbyn != null) {
            this.zzbza.zzbyn.onPause();
        }
        if (this.zztk.isFinishing()) {
            this.zznr();
        }
    }
    
    public final void onRestart() throws RemoteException {
    }
    
    public final void onResume() throws RemoteException {
        if (this.zzbzb) {
            this.zztk.finish();
        }
        else {
            this.zzbzb = true;
            if (this.zzbza.zzbyn != null) {
                this.zzbza.zzbyn.onResume();
            }
        }
    }
    
    public final void onSaveInstanceState(final Bundle bundle) throws RemoteException {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzbzb);
    }
    
    public final void onStart() throws RemoteException {
    }
    
    public final void onStop() throws RemoteException {
        if (this.zztk.isFinishing()) {
            this.zznr();
        }
    }
    
    public final void zzax() throws RemoteException {
    }
    
    public final boolean zznj() throws RemoteException {
        return false;
    }
    
    public final void zzo(final IObjectWrapper objectWrapper) throws RemoteException {
    }
}
