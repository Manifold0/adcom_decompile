// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzal;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzub extends zzkt
{
    private final zzss zzbom;
    @Nullable
    private zzal zzbor;
    private final zztt zzbpd;
    private final String zzye;
    private boolean zzyu;
    
    public zzub(final Context context, final String s, final zzxn zzxn, final zzang zzang, final zzw zzw) {
        this(s, new zzss(context, zzxn, zzang, zzw));
    }
    
    @VisibleForTesting
    private zzub(final String zzye, final zzss zzbom) {
        this.zzye = zzye;
        this.zzbom = zzbom;
        this.zzbpd = new zztt();
        zzbv.zzex().zza(zzbom);
    }
    
    @VisibleForTesting
    private final void abort() {
        if (this.zzbor != null) {
            return;
        }
        this.zzbor = this.zzbom.zzav(this.zzye);
        this.zzbpd.zzd(this.zzbor);
    }
    
    public final void destroy() throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.destroy();
        }
    }
    
    public final String getAdUnitId() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }
    
    @Nullable
    public final String getMediationAdapterClassName() throws RemoteException {
        if (this.zzbor != null) {
            return this.zzbor.getMediationAdapterClassName();
        }
        return null;
    }
    
    public final zzlo getVideoController() {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }
    
    public final boolean isLoading() throws RemoteException {
        return this.zzbor != null && this.zzbor.isLoading();
    }
    
    public final boolean isReady() throws RemoteException {
        return this.zzbor != null && this.zzbor.isReady();
    }
    
    public final void pause() throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.pause();
        }
    }
    
    public final void resume() throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.resume();
        }
    }
    
    public final void setImmersiveMode(final boolean zzyu) {
        this.zzyu = zzyu;
    }
    
    public final void setManualImpressionsEnabled(final boolean manualImpressionsEnabled) throws RemoteException {
        this.abort();
        if (this.zzbor != null) {
            this.zzbor.setManualImpressionsEnabled(manualImpressionsEnabled);
        }
    }
    
    public final void setUserId(final String s) {
    }
    
    public final void showInterstitial() throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.setImmersiveMode(this.zzyu);
            this.zzbor.showInterstitial();
            return;
        }
        zzakb.zzdk("Interstitial ad must be loaded before showInterstitial().");
    }
    
    public final void stopLoading() throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.stopLoading();
        }
    }
    
    public final void zza(final zzaaw zzaaw) throws RemoteException {
        zzakb.zzdk("setInAppPurchaseListener is deprecated and should not be called.");
    }
    
    public final void zza(final zzabc zzabc, final String s) throws RemoteException {
        zzakb.zzdk("setPlayStorePurchaseParams is deprecated and should not be called.");
    }
    
    public final void zza(final zzahe zzboh) {
        this.zzbpd.zzboh = zzboh;
        if (this.zzbor != null) {
            this.zzbpd.zzd(this.zzbor);
        }
    }
    
    public final void zza(final zzjn zzjn) throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.zza(zzjn);
        }
    }
    
    public final void zza(final zzke zzbog) throws RemoteException {
        this.zzbpd.zzbog = zzbog;
        if (this.zzbor != null) {
            this.zzbpd.zzd(this.zzbor);
        }
    }
    
    public final void zza(final zzkh zzxs) throws RemoteException {
        this.zzbpd.zzxs = zzxs;
        if (this.zzbor != null) {
            this.zzbpd.zzd(this.zzbor);
        }
    }
    
    public final void zza(final zzkx zzbod) throws RemoteException {
        this.zzbpd.zzbod = zzbod;
        if (this.zzbor != null) {
            this.zzbpd.zzd(this.zzbor);
        }
    }
    
    public final void zza(final zzla zzboe) throws RemoteException {
        this.zzbpd.zzboe = zzboe;
        if (this.zzbor != null) {
            this.zzbpd.zzd(this.zzbor);
        }
    }
    
    public final void zza(final zzlg zzlg) throws RemoteException {
        this.abort();
        if (this.zzbor != null) {
            this.zzbor.zza(zzlg);
        }
    }
    
    public final void zza(final zzlu zzlu) {
        throw new IllegalStateException("Unused method");
    }
    
    public final void zza(final zzmu zzmu) {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }
    
    public final void zza(final zzod zzbof) throws RemoteException {
        this.zzbpd.zzbof = zzbof;
        if (this.zzbor != null) {
            this.zzbpd.zzd(this.zzbor);
        }
    }
    
    public final boolean zzb(final zzjj zzjj) throws RemoteException {
        if (!zztw.zzh(zzjj).contains("gw")) {
            this.abort();
        }
        if (zztw.zzh(zzjj).contains("_skipMediation")) {
            this.abort();
        }
        if (zzjj.zzaqd != null) {
            this.abort();
        }
        if (this.zzbor != null) {
            return this.zzbor.zzb(zzjj);
        }
        final zztw zzex = zzbv.zzex();
        if (zztw.zzh(zzjj).contains("_ad")) {
            zzex.zzb(zzjj, this.zzye);
        }
        final zztz zza = zzex.zza(zzjj, this.zzye);
        if (zza != null) {
            if (!zza.zzwa) {
                zza.load();
                zzua.zzlk().zzlo();
            }
            else {
                zzua.zzlk().zzln();
            }
            this.zzbor = zza.zzbor;
            zza.zzbot.zza(this.zzbpd);
            this.zzbpd.zzd(this.zzbor);
            return zza.zzbov;
        }
        this.abort();
        zzua.zzlk().zzlo();
        return this.zzbor.zzb(zzjj);
    }
    
    public final Bundle zzba() throws RemoteException {
        if (this.zzbor != null) {
            return this.zzbor.zzba();
        }
        return new Bundle();
    }
    
    @Nullable
    public final IObjectWrapper zzbj() throws RemoteException {
        if (this.zzbor != null) {
            return this.zzbor.zzbj();
        }
        return null;
    }
    
    @Nullable
    public final zzjn zzbk() throws RemoteException {
        if (this.zzbor != null) {
            return this.zzbor.zzbk();
        }
        return null;
    }
    
    public final void zzbm() throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.zzbm();
            return;
        }
        zzakb.zzdk("Interstitial ad must be loaded before pingManualTrackingUrl().");
    }
    
    public final zzla zzbw() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }
    
    public final zzkh zzbx() {
        throw new IllegalStateException("getIAdListener not implemented");
    }
    
    @Nullable
    public final String zzck() throws RemoteException {
        if (this.zzbor != null) {
            return this.zzbor.zzck();
        }
        return null;
    }
}
