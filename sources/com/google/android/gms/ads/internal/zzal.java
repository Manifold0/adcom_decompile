package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.gmsg.zzah;
import com.google.android.gms.ads.internal.gmsg.zzai;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzaej;
import com.google.android.gms.internal.ads.zzafs;
import com.google.android.gms.internal.ads.zzago;
import com.google.android.gms.internal.ads.zzaig;
import com.google.android.gms.internal.ads.zzait;
import com.google.android.gms.internal.ads.zzaix;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarc;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzasc;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzfp;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzwx;
import com.google.android.gms.internal.ads.zzwy;
import com.google.android.gms.internal.ads.zzxn;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzal extends zzi implements zzai, zzz {
    private transient boolean zzyq = false;
    private int zzyr = -1;
    private boolean zzys;
    private float zzyt;
    private boolean zzyu;
    private zzaix zzyv;
    private String zzyw;
    private final String zzyx;
    private final zzago zzyy;

    public zzal(Context context, zzjn zzjn, String str, zzxn zzxn, zzang zzang, zzw zzw) {
        super(context, zzjn, str, zzxn, zzang, zzw);
        boolean z = zzjn != null && "reward_mb".equals(zzjn.zzarb);
        this.zzyx = z ? "/Rewarded" : "/Interstitial";
        this.zzyy = z ? new zzago(this.zzvw, this.zzwh, new zzan(this), this, this) : null;
    }

    @VisibleForTesting
    private static zzaji zzb(zzaji zzaji) {
        try {
            String jSONObject = zzafs.zzb(zzaji.zzcos).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, zzaji.zzcgs.zzacp);
            zzwx zzwx = new zzwx(jSONObject, null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null, -1);
            zzaej zzaej = zzaji.zzcos;
            zzwy zzwy = new zzwy(Collections.singletonList(zzwx), ((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), zzaej.zzbsr, zzaej.zzbss, "", -1, 0, 1, null, 0, -1, -1, false);
            return new zzaji(zzaji.zzcgs, new zzaej(zzaji.zzcgs, zzaej.zzbyq, zzaej.zzceo, Collections.emptyList(), Collections.emptyList(), zzaej.zzcep, true, zzaej.zzcer, Collections.emptyList(), zzaej.zzbsu, zzaej.orientation, zzaej.zzcet, zzaej.zzceu, zzaej.zzcev, zzaej.zzcew, zzaej.zzcex, null, zzaej.zzcez, zzaej.zzare, zzaej.zzcdd, zzaej.zzcfa, zzaej.zzcfb, zzaej.zzamj, zzaej.zzarf, zzaej.zzarg, null, Collections.emptyList(), Collections.emptyList(), zzaej.zzcfh, zzaej.zzcfi, zzaej.zzcdr, zzaej.zzcds, zzaej.zzbsr, zzaej.zzbss, zzaej.zzcfj, null, zzaej.zzcfl, zzaej.zzcfm, zzaej.zzced, zzaej.zzzl, 0, zzaej.zzcfp, Collections.emptyList(), zzaej.zzzm, zzaej.zzcfq), zzwy, zzaji.zzacv, zzaji.errorCode, zzaji.zzcoh, zzaji.zzcoi, null, zzaji.zzcoq, null);
        } catch (Throwable e) {
            zzakb.zzb("Unable to generate ad state for an interstitial ad with pooling.", e);
            return zzaji;
        }
    }

    private final void zzb(Bundle bundle) {
        zzbv.zzek().zzb(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, "gmob-apps", bundle, false);
    }

    private final boolean zzc(boolean z) {
        return this.zzyy != null && z;
    }

    public final void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzyu = z;
    }

    public final void showInterstitial() {
        Preconditions.checkMainThread("showInterstitial must be called on the main UI thread.");
        boolean z = this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq;
        if (zzc(z)) {
            this.zzyy.zzw(this.zzyu);
            return;
        }
        if (zzbv.zzfh().zzv(this.zzvw.zzrt)) {
            this.zzyw = zzbv.zzfh().zzy(this.zzvw.zzrt);
            String valueOf = String.valueOf(this.zzyw);
            String valueOf2 = String.valueOf(this.zzyx);
            this.zzyw = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        if (this.zzvw.zzacw == null) {
            zzakb.zzdk("The interstitial has not loaded.");
            return;
        }
        if (((Boolean) zzkb.zzik().zzd(zznk.zzazx)).booleanValue()) {
            Bundle bundle;
            valueOf2 = this.zzvw.zzrt.getApplicationContext() != null ? this.zzvw.zzrt.getApplicationContext().getPackageName() : this.zzvw.zzrt.getPackageName();
            if (!this.zzyq) {
                zzakb.zzdk("It is not recommended to show an interstitial before onAdLoaded completes.");
                bundle = new Bundle();
                bundle.putString("appid", valueOf2);
                bundle.putString("action", "show_interstitial_before_load_finish");
                zzb(bundle);
            }
            zzbv.zzek();
            if (!zzakk.zzaq(this.zzvw.zzrt)) {
                zzakb.zzdk("It is not recommended to show an interstitial when app is not in foreground.");
                bundle = new Bundle();
                bundle.putString("appid", valueOf2);
                bundle.putString("action", "show_interstitial_app_not_in_foreground");
                zzb(bundle);
            }
        }
        if (!this.zzvw.zzfp()) {
            if (this.zzvw.zzacw.zzceq && this.zzvw.zzacw.zzbtx != null) {
                try {
                    if (((Boolean) zzkb.zzik().zzd(zznk.zzayr)).booleanValue()) {
                        this.zzvw.zzacw.zzbtx.setImmersiveMode(this.zzyu);
                    }
                    this.zzvw.zzacw.zzbtx.showInterstitial();
                } catch (Throwable e) {
                    zzakb.zzc("Could not show interstitial.", e);
                    zzdj();
                }
            } else if (this.zzvw.zzacw.zzbyo == null) {
                zzakb.zzdk("The interstitial failed to load.");
            } else if (this.zzvw.zzacw.zzbyo.zzuj()) {
                zzakb.zzdk("The interstitial is already showing.");
            } else {
                Bitmap zzar;
                this.zzvw.zzacw.zzbyo.zzai(true);
                this.zzvw.zzj(this.zzvw.zzacw.zzbyo.getView());
                if (this.zzvw.zzacw.zzcob != null) {
                    this.zzvy.zza(this.zzvw.zzacv, this.zzvw.zzacw);
                }
                if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                    zzajh zzajh = this.zzvw.zzacw;
                    if (zzajh.zzfz()) {
                        new zzfp(this.zzvw.zzrt, zzajh.zzbyo.getView()).zza(zzajh.zzbyo);
                    } else {
                        zzajh.zzbyo.zzuf().zza(new zzam(this, zzajh));
                    }
                }
                if (this.zzvw.zzze) {
                    zzbv.zzek();
                    zzar = zzakk.zzar(this.zzvw.zzrt);
                } else {
                    zzar = null;
                }
                this.zzyr = zzbv.zzfe().zzb(zzar);
                if (!((Boolean) zzkb.zzik().zzd(zznk.zzbbg)).booleanValue() || zzar == null) {
                    zzaq zzaq = new zzaq(this.zzvw.zzze, zzdi(), false, 0.0f, -1, this.zzyu, this.zzvw.zzacw.zzzl, this.zzvw.zzacw.zzzm);
                    int requestedOrientation = this.zzvw.zzacw.zzbyo.getRequestedOrientation();
                    if (requestedOrientation == -1) {
                        requestedOrientation = this.zzvw.zzacw.orientation;
                    }
                    AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(this, this, this, this.zzvw.zzacw.zzbyo, requestedOrientation, this.zzvw.zzacr, this.zzvw.zzacw.zzcev, zzaq);
                    zzbv.zzei();
                    zzl.zza(this.zzvw.zzrt, adOverlayInfoParcel, true);
                    return;
                }
                new zzao(this, this.zzyr).zzqo();
            }
        }
    }

    protected final zzaqw zza(zzaji zzaji, @Nullable zzx zzx, @Nullable zzait zzait) throws zzarg {
        zzbv.zzel();
        zzaqw zza = zzarc.zza(this.zzvw.zzrt, zzasi.zzb(this.zzvw.zzacv), this.zzvw.zzacv.zzarb, false, false, this.zzvw.zzacq, this.zzvw.zzacr, this.zzvr, this, this.zzwc, zzaji.zzcoq);
        zza.zzuf().zza(this, this, null, this, this, ((Boolean) zzkb.zzik().zzd(zznk.zzaxe)).booleanValue(), this, zzx, this, zzait);
        zza(zza);
        zza.zzdr(zzaji.zzcgs.zzcdi);
        zza.zza("/reward", new zzah(this));
        return zza;
    }

    public final void zza(zzaji zzaji, zznx zznx) {
        Object obj = 1;
        if (zzaji.errorCode != -2) {
            super.zza(zzaji, zznx);
            return;
        }
        if (zzc(zzaji.zzcod != null)) {
            this.zzyy.zzou();
            return;
        }
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayy)).booleanValue()) {
            if (zzaji.zzcos.zzceq) {
                obj = null;
            }
            if (zza.zza(zzaji.zzcgs.zzccv) && r1 != null) {
                this.zzvw.zzacx = zzb(zzaji);
            }
            super.zza(this.zzvw.zzacx, zznx);
            return;
        }
        super.zza(zzaji, zznx);
    }

    public final void zza(boolean z, float f) {
        this.zzys = z;
        this.zzyt = f;
    }

    public final boolean zza(@Nullable zzajh zzajh, zzajh zzajh2) {
        if (zzc(zzajh2.zzceq)) {
            return zzago.zza(zzajh, zzajh2);
        }
        if (!super.zza(zzajh, zzajh2)) {
            return false;
        }
        if (!(this.zzvw.zzfo() || this.zzvw.zzadu == null || zzajh2.zzcob == null)) {
            this.zzvy.zza(this.zzvw.zzacv, zzajh2, this.zzvw.zzadu);
        }
        zzb(zzajh2, false);
        return true;
    }

    protected final boolean zza(zzjj zzjj, zzajh zzajh, boolean z) {
        if (this.zzvw.zzfo() && zzajh.zzbyo != null) {
            zzbv.zzem();
            zzakq.zzi(zzajh.zzbyo);
        }
        return this.zzvv.zzdz();
    }

    public final boolean zza(zzjj zzjj, zznx zznx) {
        if (this.zzvw.zzacw != null) {
            zzakb.zzdk("An interstitial is already loading. Aborting.");
            return false;
        }
        if (this.zzyv == null && zza.zza(zzjj) && zzbv.zzfh().zzv(this.zzvw.zzrt) && !TextUtils.isEmpty(this.zzvw.zzacp)) {
            this.zzyv = new zzaix(this.zzvw.zzrt, this.zzvw.zzacp);
        }
        return super.zza(zzjj, zznx);
    }

    public final void zzb(zzaig zzaig) {
        boolean z = this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq;
        if (zzc(z)) {
            zza(this.zzyy.zzd(zzaig));
            return;
        }
        if (this.zzvw.zzacw != null) {
            if (this.zzvw.zzacw.zzcfg != null) {
                zzbv.zzek();
                zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw.zzcfg);
            }
            if (this.zzvw.zzacw.zzcfe != null) {
                zzaig = this.zzvw.zzacw.zzcfe;
            }
        }
        zza(zzaig);
    }

    protected final void zzbn() {
        zzdj();
        super.zzbn();
    }

    protected final void zzbq() {
        zzaqw zzaqw = this.zzvw.zzacw != null ? this.zzvw.zzacw.zzbyo : null;
        zzaji zzaji = this.zzvw.zzacx;
        if (!(zzaji == null || zzaji.zzcos == null || !zzaji.zzcos.zzcfp || zzaqw == null || !zzbv.zzfa().zzi(this.zzvw.zzrt))) {
            this.zzwb = zzbv.zzfa().zza(this.zzvw.zzacr.zzcve + "." + this.zzvw.zzacr.zzcvf, zzaqw.getWebView(), "", "javascript", zzbz());
            if (!(this.zzwb == null || zzaqw.getView() == null)) {
                zzbv.zzfa().zza(this.zzwb, zzaqw.getView());
                zzbv.zzfa().zzm(this.zzwb);
            }
        }
        super.zzbq();
        this.zzyq = true;
    }

    public final void zzcb() {
        super.zzcb();
        this.zzvy.zzh(this.zzvw.zzacw);
        if (this.zzyv != null) {
            this.zzyv.zzx(false);
        }
        zzby();
    }

    public final void zzcc() {
        recordImpression();
        super.zzcc();
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null)) {
            zzasc zzuf = this.zzvw.zzacw.zzbyo.zzuf();
            if (zzuf != null) {
                zzuf.zzuz();
            }
        }
        if (!(!zzbv.zzfh().zzv(this.zzvw.zzrt) || this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null)) {
            zzbv.zzfh().zzd(this.zzvw.zzacw.zzbyo.getContext(), this.zzyw);
        }
        if (this.zzyv != null) {
            this.zzyv.zzx(true);
        }
        if (this.zzwb != null && this.zzvw.zzacw != null && this.zzvw.zzacw.zzbyo != null) {
            this.zzvw.zzacw.zzbyo.zza("onSdkImpression", new HashMap());
        }
    }

    public final void zzcz() {
        zzd zzub = this.zzvw.zzacw.zzbyo.zzub();
        if (zzub != null) {
            zzub.close();
        }
    }

    public final void zzd(boolean z) {
        this.zzvw.zzze = z;
    }

    protected final boolean zzdi() {
        if (!(this.zzvw.zzrt instanceof Activity)) {
            return false;
        }
        Window window = ((Activity) this.zzvw.zzrt).getWindow();
        if (window == null || window.getDecorView() == null) {
            return false;
        }
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        window.getDecorView().getGlobalVisibleRect(rect, null);
        window.getDecorView().getWindowVisibleDisplayFrame(rect2);
        return (rect.bottom == 0 || rect2.bottom == 0 || rect.top != rect2.top) ? false : true;
    }

    public final void zzdj() {
        zzbv.zzfe().zzb(Integer.valueOf(this.zzyr));
        if (this.zzvw.zzfo()) {
            this.zzvw.zzfm();
            this.zzvw.zzacw = null;
            this.zzvw.zzze = false;
            this.zzyq = false;
        }
    }

    public final void zzdk() {
        boolean z = this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq;
        if (zzc(z)) {
            this.zzyy.zzov();
            zzbt();
            return;
        }
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.zzcog == null)) {
            zzbv.zzek();
            zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw.zzcog);
        }
        zzbt();
    }

    public final void zzdl() {
        boolean z = this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq;
        if (zzc(z)) {
            this.zzyy.zzow();
        }
        zzbu();
    }
}
