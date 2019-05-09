package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaaw;
import com.google.android.gms.internal.ads.zzabc;
import com.google.android.gms.internal.ads.zzabm;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzadj;
import com.google.android.gms.internal.ads.zzagp;
import com.google.android.gms.internal.ads.zzagu;
import com.google.android.gms.internal.ads.zzagx;
import com.google.android.gms.internal.ads.zzahe;
import com.google.android.gms.internal.ads.zzaig;
import com.google.android.gms.internal.ads.zzajb;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzajj;
import com.google.android.gms.internal.ads.zzajs;
import com.google.android.gms.internal.ads.zzaju;
import com.google.android.gms.internal.ads.zzajz;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzes;
import com.google.android.gms.internal.ads.zzjd;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjk;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzke;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkt;
import com.google.android.gms.internal.ads.zzkx;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zzms;
import com.google.android.gms.internal.ads.zzmu;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zznv;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzod;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public abstract class zza extends zzkt implements zzb, zzd, zzt, zzabm, zzadj, zzajs, zzjd {
    protected zznx zzvr;
    protected zznv zzvs;
    private zznv zzvt;
    protected boolean zzvu = false;
    protected final zzbl zzvv;
    protected final zzbw zzvw;
    @Nullable
    protected transient zzjj zzvx;
    protected final zzes zzvy;
    private final Bundle zzvz = new Bundle();
    private boolean zzwa = false;
    @Nullable
    protected IObjectWrapper zzwb;
    protected final zzw zzwc;

    @VisibleForTesting
    zza(zzbw zzbw, @Nullable zzbl zzbl, zzw zzw) {
        this.zzvw = zzbw;
        this.zzvv = new zzbl(this);
        this.zzwc = zzw;
        zzbv.zzek().zzak(this.zzvw.zzrt);
        zzbv.zzek().zzal(this.zzvw.zzrt);
        zzajz.zzai(this.zzvw.zzrt);
        zzbv.zzfi().initialize(this.zzvw.zzrt);
        zzbv.zzeo().zzd(this.zzvw.zzrt, this.zzvw.zzacr);
        zzbv.zzeq().initialize(this.zzvw.zzrt);
        this.zzvy = zzbv.zzeo().zzqd();
        zzbv.zzen().initialize(this.zzvw.zzrt);
        zzbv.zzfk().initialize(this.zzvw.zzrt);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbci)).booleanValue()) {
            Timer timer = new Timer();
            timer.schedule(new zzb(this, new CountDownLatch(((Integer) zzkb.zzik().zzd(zznk.zzbck)).intValue()), timer), 0, ((Long) zzkb.zzik().zzd(zznk.zzbcj)).longValue());
        }
    }

    protected static boolean zza(zzjj zzjj) {
        Bundle bundle = zzjj.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        return bundle == null || !bundle.containsKey("gw");
    }

    @VisibleForTesting
    private static long zzq(String str) {
        Throwable e;
        int indexOf = str.indexOf("ufe");
        int indexOf2 = str.indexOf(44, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = str.length();
        }
        try {
            return Long.parseLong(str.substring(indexOf + 4, indexOf2));
        } catch (IndexOutOfBoundsException e2) {
            e = e2;
            zzane.zzb("", e);
            return -1;
        } catch (NumberFormatException e3) {
            e = e3;
            zzane.zzb("", e);
            return -1;
        }
    }

    public void destroy() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: destroy");
        this.zzvv.cancel();
        this.zzvy.zzi(this.zzvw.zzacw);
        zzbw zzbw = this.zzvw;
        if (zzbw.zzacs != null) {
            zzbw.zzacs.zzfs();
        }
        zzbw.zzada = null;
        zzbw.zzadc = null;
        zzbw.zzadb = null;
        zzbw.zzado = null;
        zzbw.zzadd = null;
        zzbw.zzg(false);
        if (zzbw.zzacs != null) {
            zzbw.zzacs.removeAllViews();
        }
        zzbw.zzfm();
        zzbw.zzfn();
        zzbw.zzacw = null;
    }

    public String getAdUnitId() {
        return this.zzvw.zzacp;
    }

    public zzlo getVideoController() {
        return null;
    }

    public final boolean isLoading() {
        return this.zzvu;
    }

    public final boolean isReady() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: isLoaded");
        return this.zzvw.zzact == null && this.zzvw.zzacu == null && this.zzvw.zzacw != null;
    }

    public void onAdClicked() {
        if (this.zzvw.zzacw == null) {
            zzakb.zzdk("Ad state was null when trying to ping click URLs.");
            return;
        }
        zzakb.zzck("Pinging click URLs.");
        if (this.zzvw.zzacy != null) {
            this.zzvw.zzacy.zzpn();
        }
        if (this.zzvw.zzacw.zzbsn != null) {
            zzbv.zzek();
            zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzc(this.zzvw.zzacw.zzbsn));
        }
        if (this.zzvw.zzacz != null) {
            try {
                this.zzvw.zzacz.onAdClicked();
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void onAppEvent(String str, @Nullable String str2) {
        if (this.zzvw.zzadb != null) {
            try {
                this.zzvw.zzadb.onAppEvent(str, str2);
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public void pause() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: pause");
    }

    public void resume() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: resume");
    }

    public void setImmersiveMode(boolean z) {
        throw new IllegalStateException("#005 Unexpected call to an abstract (unimplemented) method.");
    }

    public void setManualImpressionsEnabled(boolean z) {
        zzakb.zzdk("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
    }

    public final void setUserId(String str) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setUserId");
        this.zzvw.zzadr = str;
    }

    public final void stopLoading() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: stopLoading");
        this.zzvu = false;
        this.zzvw.zzg(true);
    }

    public void zza(zzaaw zzaaw) {
        zzakb.zzdk("#006 Unexpected call to a deprecated method.");
    }

    public final void zza(zzabc zzabc, String str) {
        zzakb.zzdk("#006 Unexpected call to a deprecated method.");
    }

    public final void zza(zzagx zzagx) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedAdSkuListener");
        this.zzvw.zzadq = zzagx;
    }

    public final void zza(zzahe zzahe) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedVideoAdListener");
        this.zzvw.zzadp = zzahe;
    }

    protected final void zza(@Nullable zzaig zzaig) {
        if (this.zzvw.zzadp != null) {
            try {
                String str = "";
                int i = 1;
                if (zzaig != null) {
                    str = zzaig.type;
                    i = zzaig.zzcmk;
                }
                zzagu zzagp = new zzagp(str, i);
                this.zzvw.zzadp.zza(zzagp);
                if (this.zzvw.zzadq != null) {
                    this.zzvw.zzadq.zza(zzagp, this.zzvw.zzacx.zzcgs.zzcdi);
                }
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zza(zzaji zzaji) {
        if (!(zzaji.zzcos.zzceu == -1 || TextUtils.isEmpty(zzaji.zzcos.zzcfd))) {
            long zzq = zzq(zzaji.zzcos.zzcfd);
            if (zzq != -1) {
                zznv zzd = this.zzvr.zzd(zzq + zzaji.zzcos.zzceu);
                this.zzvr.zza(zzd, "stc");
            }
        }
        this.zzvr.zzan(zzaji.zzcos.zzcfd);
        this.zzvr.zza(this.zzvs, "arf");
        this.zzvt = this.zzvr.zzjj();
        this.zzvr.zze("gqi", zzaji.zzcos.zzamj);
        this.zzvw.zzact = null;
        this.zzvw.zzacx = zzaji;
        zzaji.zzcoq.zza(new zzc(this, zzaji));
        zzaji.zzcoq.zza(com.google.android.gms.internal.ads.zzhu.zza.zzb.AD_LOADED);
        zza(zzaji, this.zzvr);
    }

    protected abstract void zza(zzaji zzaji, zznx zznx);

    public final void zza(zzjn zzjn) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdSize");
        this.zzvw.zzacv = zzjn;
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null || this.zzvw.zzadv != 0)) {
            this.zzvw.zzacw.zzbyo.zza(zzasi.zzb(zzjn));
        }
        if (this.zzvw.zzacs != null) {
            if (this.zzvw.zzacs.getChildCount() > 1) {
                this.zzvw.zzacs.removeView(this.zzvw.zzacs.getNextView());
            }
            this.zzvw.zzacs.setMinimumWidth(zzjn.widthPixels);
            this.zzvw.zzacs.setMinimumHeight(zzjn.heightPixels);
            this.zzvw.zzacs.requestLayout();
        }
    }

    public final void zza(zzke zzke) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdClickListener");
        this.zzvw.zzacz = zzke;
    }

    public final void zza(zzkh zzkh) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdListener");
        this.zzvw.zzada = zzkh;
    }

    public final void zza(zzkx zzkx) {
        this.zzvw.zzadc = zzkx;
    }

    public final void zza(zzla zzla) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAppEventListener");
        this.zzvw.zzadb = zzla;
    }

    public final void zza(zzlg zzlg) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setCorrelationIdProvider");
        this.zzvw.zzadd = zzlg;
    }

    public final void zza(@Nullable zzlu zzlu) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setIconAdOptions");
        this.zzvw.zzadl = zzlu;
    }

    public final void zza(@Nullable zzmu zzmu) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setVideoOptions");
        this.zzvw.zzadk = zzmu;
    }

    public final void zza(zznv zznv) {
        this.zzvr = new zznx(((Boolean) zzkb.zzik().zzd(zznk.zzawh)).booleanValue(), "load_ad", this.zzvw.zzacv.zzarb);
        this.zzvt = new zznv(-1, null, null);
        if (zznv == null) {
            this.zzvs = new zznv(-1, null, null);
        } else {
            this.zzvs = new zznv(zznv.getTime(), zznv.zzjg(), zznv.zzjh());
        }
    }

    public void zza(zzod zzod) {
        throw new IllegalStateException("#005 Unexpected call to an abstract (unimplemented) method.");
    }

    public final void zza(String str, Bundle bundle) {
        this.zzvz.putAll(bundle);
        if (this.zzwa && this.zzvw.zzadc != null) {
            try {
                this.zzvw.zzadc.zzt();
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zza(HashSet<zzajj> hashSet) {
        this.zzvw.zza(hashSet);
    }

    boolean zza(zzajh zzajh) {
        return false;
    }

    protected abstract boolean zza(@Nullable zzajh zzajh, zzajh zzajh2);

    protected abstract boolean zza(zzjj zzjj, zznx zznx);

    protected final List<String> zzb(List<String> list) {
        List arrayList = new ArrayList(list.size());
        for (String zzc : list) {
            arrayList.add(zzajb.zzc(zzc, this.zzvw.zzrt));
        }
        return arrayList;
    }

    public void zzb(zzajh zzajh) {
        this.zzvr.zza(this.zzvt, "awr");
        this.zzvw.zzacu = null;
        if (!(zzajh.errorCode == -2 || zzajh.errorCode == 3 || this.zzvw.zzfl() == null)) {
            zzbv.zzep().zzb(this.zzvw.zzfl());
        }
        if (zzajh.errorCode == -1) {
            this.zzvu = false;
            return;
        }
        if (zza(zzajh)) {
            zzakb.zzck("Ad refresh scheduled.");
        }
        if (zzajh.errorCode != -2) {
            if (zzajh.errorCode == 3) {
                zzajh.zzcoq.zza(com.google.android.gms.internal.ads.zzhu.zza.zzb.AD_FAILED_TO_LOAD_NO_FILL);
            } else {
                zzajh.zzcoq.zza(com.google.android.gms.internal.ads.zzhu.zza.zzb.AD_FAILED_TO_LOAD);
            }
            zzi(zzajh.errorCode);
            return;
        }
        if (this.zzvw.zzadt == null) {
            this.zzvw.zzadt = new zzaju(this.zzvw.zzacp);
        }
        if (this.zzvw.zzacs != null) {
            this.zzvw.zzacs.zzfr().zzdc(zzajh.zzcfl);
        }
        this.zzvy.zzh(this.zzvw.zzacw);
        if (zza(this.zzvw.zzacw, zzajh)) {
            this.zzvw.zzacw = zzajh;
            zzbw zzbw = this.zzvw;
            if (zzbw.zzacy != null) {
                if (zzbw.zzacw != null) {
                    zzbw.zzacy.zzh(zzbw.zzacw.zzcoh);
                    zzbw.zzacy.zzi(zzbw.zzacw.zzcoi);
                    zzbw.zzacy.zzz(zzbw.zzacw.zzceq);
                }
                zzbw.zzacy.zzy(zzbw.zzacv.zzarc);
            }
            this.zzvr.zze("is_mraid", this.zzvw.zzacw.zzfz() ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            this.zzvr.zze("is_mediation", this.zzvw.zzacw.zzceq ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            if (!(this.zzvw.zzacw.zzbyo == null || this.zzvw.zzacw.zzbyo.zzuf() == null)) {
                this.zzvr.zze("is_delay_pl", this.zzvw.zzacw.zzbyo.zzuf().zzux() ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            this.zzvr.zza(this.zzvs, "ttc");
            if (zzbv.zzeo().zzpy() != null) {
                zzbv.zzeo().zzpy().zza(this.zzvr);
            }
            zzbv();
            if (this.zzvw.zzfo()) {
                zzbq();
            }
        }
        if (zzajh.zzbsr != null) {
            zzbv.zzek().zza(this.zzvw.zzrt, zzajh.zzbsr);
        }
    }

    protected void zzb(boolean z) {
        zzakb.m3915v("Ad finished loading.");
        this.zzvu = z;
        this.zzwa = true;
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdLoaded();
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdLoaded();
            } catch (Throwable e2) {
                zzakb.zzd("#007 Could not call remote method.", e2);
            }
        }
        if (this.zzvw.zzadc != null) {
            try {
                this.zzvw.zzadc.zzt();
            } catch (Throwable e22) {
                zzakb.zzd("#007 Could not call remote method.", e22);
            }
        }
    }

    public boolean zzb(zzjj zzjj) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: loadAd");
        zzbv.zzeq().zzhh();
        this.zzvz.clear();
        this.zzwa = false;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayo)).booleanValue()) {
            zzjj = zzjj.zzhv();
            if (((Boolean) zzkb.zzik().zzd(zznk.zzayp)).booleanValue()) {
                zzjj.extras.putBoolean(AdMobAdapter.NEW_BUNDLE, true);
            }
        }
        if (DeviceProperties.isSidewinder(this.zzvw.zzrt) && zzjj.zzaqe != null) {
            zzjj = new zzjk(zzjj).zza(null).zzhw();
        }
        if (this.zzvw.zzact == null && this.zzvw.zzacu == null) {
            zzakb.zzdj("Starting ad request.");
            zza(null);
            this.zzvs = this.zzvr.zzjj();
            if (zzjj.zzapz) {
                zzakb.zzdj("This request is sent from a test device.");
            } else {
                zzkb.zzif();
                String zzbc = zzamu.zzbc(this.zzvw.zzrt);
                zzakb.zzdj(new StringBuilder(String.valueOf(zzbc).length() + 71).append("Use AdRequest.Builder.addTestDevice(\"").append(zzbc).append("\") to get test ads on this device.").toString());
            }
            this.zzvv.zzf(zzjj);
            this.zzvu = zza(zzjj, this.zzvr);
            return this.zzvu;
        }
        if (this.zzvx != null) {
            zzakb.zzdk("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
        } else {
            zzakb.zzdk("Loading already in progress, saving this object for future refreshes.");
        }
        this.zzvx = zzjj;
        return false;
    }

    public final Bundle zzba() {
        return this.zzwa ? this.zzvz : new Bundle();
    }

    public final zzw zzbi() {
        return this.zzwc;
    }

    public final IObjectWrapper zzbj() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: getAdFrame");
        return ObjectWrapper.wrap(this.zzvw.zzacs);
    }

    @Nullable
    public final zzjn zzbk() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: getAdSize");
        return this.zzvw.zzacv == null ? null : new zzms(this.zzvw.zzacv);
    }

    public final void zzbl() {
        zzbo();
    }

    public final void zzbm() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: recordManualImpression");
        if (this.zzvw.zzacw == null) {
            zzakb.zzdk("Ad state was null when trying to ping manual tracking URLs.");
            return;
        }
        zzakb.zzck("Pinging manual tracking URLs.");
        if (!this.zzvw.zzacw.zzcoo) {
            List arrayList = new ArrayList();
            if (this.zzvw.zzacw.zzces != null) {
                arrayList.addAll(this.zzvw.zzacw.zzces);
            }
            if (!(this.zzvw.zzacw.zzbtw == null || this.zzvw.zzacw.zzbtw.zzbrz == null)) {
                arrayList.addAll(this.zzvw.zzacw.zzbtw.zzbrz);
            }
            if (!arrayList.isEmpty()) {
                zzbv.zzek();
                zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, arrayList);
                this.zzvw.zzacw.zzcoo = true;
            }
        }
    }

    protected void zzbn() {
        zzakb.m3915v("Ad closing.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdClosed();
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdClosed();
            } catch (Throwable e2) {
                zzakb.zzd("#007 Could not call remote method.", e2);
            }
        }
    }

    protected final void zzbo() {
        zzakb.m3915v("Ad leaving application.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdLeftApplication();
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdLeftApplication();
            } catch (Throwable e2) {
                zzakb.zzd("#007 Could not call remote method.", e2);
            }
        }
    }

    protected final void zzbp() {
        zzakb.m3915v("Ad opening.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdOpened();
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdOpened();
            } catch (Throwable e2) {
                zzakb.zzd("#007 Could not call remote method.", e2);
            }
        }
    }

    protected void zzbq() {
        zzb(false);
    }

    public final void zzbr() {
        zzakb.zzdj("Ad impression.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdImpression();
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zzbs() {
        zzakb.zzdj("Ad clicked.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdClicked();
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    protected final void zzbt() {
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoStarted();
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    protected final void zzbu() {
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoCompleted();
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zzbv() {
        zzajh zzajh = this.zzvw.zzacw;
        if (zzajh != null && !TextUtils.isEmpty(zzajh.zzcfl) && !zzajh.zzcop && zzbv.zzeu().zzrx()) {
            zzakb.zzck("Sending troubleshooting signals to the server.");
            zzbv.zzeu().zzb(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzajh.zzcfl, this.zzvw.zzacp);
            zzajh.zzcop = true;
        }
    }

    public final zzla zzbw() {
        return this.zzvw.zzadb;
    }

    public final zzkh zzbx() {
        return this.zzvw.zzada;
    }

    protected final void zzby() {
        if (this.zzwb != null) {
            zzbv.zzfa().zzn(this.zzwb);
            this.zzwb = null;
        }
    }

    @Nullable
    protected final String zzbz() {
        zzaji zzaji = this.zzvw.zzacx;
        if (zzaji == null) {
            return "javascript";
        }
        if (zzaji.zzcos == null) {
            return "javascript";
        }
        Object obj = zzaji.zzcos.zzcfq;
        if (TextUtils.isEmpty(obj)) {
            return "javascript";
        }
        try {
            return new JSONObject(obj).optInt(MessengerShareContentUtility.MEDIA_TYPE, -1) == 0 ? null : "javascript";
        } catch (Throwable e) {
            zzane.zzc("", e);
            return "javascript";
        }
    }

    protected final List<String> zzc(List<String> list) {
        List arrayList = new ArrayList(list.size());
        for (String zzb : list) {
            arrayList.add(zzajb.zzb(zzb, this.zzvw.zzrt));
        }
        return arrayList;
    }

    protected void zzc(int i, boolean z) {
        zzakb.zzdk("Failed to load ad: " + i);
        this.zzvu = z;
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdFailedToLoad(i);
            } catch (Throwable e) {
                zzakb.zzd("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdFailedToLoad(i);
            } catch (Throwable e2) {
                zzakb.zzd("#007 Could not call remote method.", e2);
            }
        }
    }

    protected boolean zzc(zzjj zzjj) {
        if (this.zzvw.zzacs == null) {
            return false;
        }
        ViewParent parent = this.zzvw.zzacs.getParent();
        if (!(parent instanceof View)) {
            return false;
        }
        View view = (View) parent;
        return zzbv.zzek().zza(view, view.getContext());
    }

    protected final void zzg(View view) {
        zzbx zzbx = this.zzvw.zzacs;
        if (zzbx != null) {
            zzbx.addView(view, zzbv.zzem().zzro());
        }
    }

    protected void zzi(int i) {
        zzc(i, false);
    }
}
