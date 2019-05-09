package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzal;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzub extends zzkt {
    private final zzss zzbom;
    @Nullable
    private zzal zzbor;
    private final zztt zzbpd;
    private final String zzye;
    private boolean zzyu;

    public zzub(Context context, String str, zzxn zzxn, zzang zzang, zzw zzw) {
        this(str, new zzss(context, zzxn, zzang, zzw));
    }

    @VisibleForTesting
    private zzub(String str, zzss zzss) {
        this.zzye = str;
        this.zzbom = zzss;
        this.zzbpd = new zztt();
        zzbv.zzex().zza(zzss);
    }

    @VisibleForTesting
    private final void abort() {
        if (this.zzbor == null) {
            this.zzbor = this.zzbom.zzav(this.zzye);
            this.zzbpd.zzd(this.zzbor);
        }
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
        return this.zzbor != null ? this.zzbor.getMediationAdapterClassName() : null;
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

    public final void setImmersiveMode(boolean z) {
        this.zzyu = z;
    }

    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
        abort();
        if (this.zzbor != null) {
            this.zzbor.setManualImpressionsEnabled(z);
        }
    }

    public final void setUserId(String str) {
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

    public final void zza(zzaaw zzaaw) throws RemoteException {
        zzakb.zzdk("setInAppPurchaseListener is deprecated and should not be called.");
    }

    public final void zza(zzabc zzabc, String str) throws RemoteException {
        zzakb.zzdk("setPlayStorePurchaseParams is deprecated and should not be called.");
    }

    public final void zza(zzahe zzahe) {
        this.zzbpd.zzboh = zzahe;
        if (this.zzbor != null) {
            this.zzbpd.zzd(this.zzbor);
        }
    }

    public final void zza(zzjn zzjn) throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.zza(zzjn);
        }
    }

    public final void zza(zzke zzke) throws RemoteException {
        this.zzbpd.zzbog = zzke;
        if (this.zzbor != null) {
            this.zzbpd.zzd(this.zzbor);
        }
    }

    public final void zza(zzkh zzkh) throws RemoteException {
        this.zzbpd.zzxs = zzkh;
        if (this.zzbor != null) {
            this.zzbpd.zzd(this.zzbor);
        }
    }

    public final void zza(zzkx zzkx) throws RemoteException {
        this.zzbpd.zzbod = zzkx;
        if (this.zzbor != null) {
            this.zzbpd.zzd(this.zzbor);
        }
    }

    public final void zza(zzla zzla) throws RemoteException {
        this.zzbpd.zzboe = zzla;
        if (this.zzbor != null) {
            this.zzbpd.zzd(this.zzbor);
        }
    }

    public final void zza(zzlg zzlg) throws RemoteException {
        abort();
        if (this.zzbor != null) {
            this.zzbor.zza(zzlg);
        }
    }

    public final void zza(zzlu zzlu) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzmu zzmu) {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    public final void zza(zzod zzod) throws RemoteException {
        this.zzbpd.zzbof = zzod;
        if (this.zzbor != null) {
            this.zzbpd.zzd(this.zzbor);
        }
    }

    public final boolean zzb(zzjj zzjj) throws RemoteException {
        if (!zztw.zzh(zzjj).contains("gw")) {
            abort();
        }
        if (zztw.zzh(zzjj).contains("_skipMediation")) {
            abort();
        }
        if (zzjj.zzaqd != null) {
            abort();
        }
        if (this.zzbor != null) {
            return this.zzbor.zzb(zzjj);
        }
        zztw zzex = zzbv.zzex();
        if (zztw.zzh(zzjj).contains("_ad")) {
            zzex.zzb(zzjj, this.zzye);
        }
        zztz zza = zzex.zza(zzjj, this.zzye);
        if (zza != null) {
            if (zza.zzwa) {
                zzua.zzlk().zzln();
            } else {
                zza.load();
                zzua.zzlk().zzlo();
            }
            this.zzbor = zza.zzbor;
            zza.zzbot.zza(this.zzbpd);
            this.zzbpd.zzd(this.zzbor);
            return zza.zzbov;
        }
        abort();
        zzua.zzlk().zzlo();
        return this.zzbor.zzb(zzjj);
    }

    public final Bundle zzba() throws RemoteException {
        return this.zzbor != null ? this.zzbor.zzba() : new Bundle();
    }

    @Nullable
    public final IObjectWrapper zzbj() throws RemoteException {
        return this.zzbor != null ? this.zzbor.zzbj() : null;
    }

    @Nullable
    public final zzjn zzbk() throws RemoteException {
        return this.zzbor != null ? this.zzbor.zzbk() : null;
    }

    public final void zzbm() throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.zzbm();
        } else {
            zzakb.zzdk("Interstitial ad must be loaded before pingManualTrackingUrl().");
        }
    }

    public final zzla zzbw() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }

    public final zzkh zzbx() {
        throw new IllegalStateException("getIAdListener not implemented");
    }

    @Nullable
    public final String zzck() throws RemoteException {
        return this.zzbor != null ? this.zzbor.zzck() : null;
    }
}
