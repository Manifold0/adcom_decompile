package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.view.View;
import com.google.ads.AdRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaaw;
import com.google.android.gms.internal.ads.zzabl;
import com.google.android.gms.internal.ads.zzacm;
import com.google.android.gms.internal.ads.zzacq;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzanz;
import com.google.android.gms.internal.ads.zzaoj;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzev;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzlr;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzos;
import com.google.android.gms.internal.ads.zzov;
import com.google.android.gms.internal.ads.zzox;
import com.google.android.gms.internal.ads.zzoy;
import com.google.android.gms.internal.ads.zzoz;
import com.google.android.gms.internal.ads.zzpa;
import com.google.android.gms.internal.ads.zzpb;
import com.google.android.gms.internal.ads.zzpd;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzwy;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzxq;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import com.google.android.gms.internal.ads.zzyf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzbc extends zzd implements zzpa {
    private final Object mLock;
    private zzaqw zzaaa;
    @Nullable
    private zzaqw zzaab;
    private int zzaac;
    @GuardedBy("mLock")
    private zzacm zzaad;
    private final String zzaae;
    private boolean zzwl;
    @VisibleForTesting
    private boolean zzzy;
    private zzaoj<zzpb> zzzz;

    public zzbc(Context context, zzw zzw, zzjn zzjn, String str, zzxn zzxn, zzang zzang) {
        this(context, zzw, zzjn, str, zzxn, zzang, false);
    }

    public zzbc(Context context, zzw zzw, zzjn zzjn, String str, zzxn zzxn, zzang zzang, boolean z) {
        super(context, zzjn, str, zzxn, zzang, zzw);
        this.mLock = new Object();
        this.zzzz = new zzaoj();
        this.zzaac = 1;
        this.zzaae = UUID.randomUUID().toString();
        this.zzzy = z;
    }

    private static zzov zza(zzpb zzpb) {
        zzov zzov = null;
        Object obj = null;
        if (zzpb instanceof zzoq) {
            zzoq zzoq = (zzoq) zzpb;
            zzov = new zzov(zzoq.getHeadline(), zzoq.getImages(), zzoq.getBody(), zzoq.zzkg(), zzoq.getCallToAction(), zzoq.getAdvertiser(), -1.0d, null, null, zzoq.zzkc(), zzoq.getVideoController(), zzoq.zzkd(), zzoq.zzke(), zzoq.getMediationAdapterClassName(), zzoq.getExtras());
            obj = zzoq.zzka() != null ? ObjectWrapper.unwrap(zzoq.zzka()) : null;
        } else if (zzpb instanceof zzoo) {
            zzoo zzoo = (zzoo) zzpb;
            zzov = new zzov(zzoo.getHeadline(), zzoo.getImages(), zzoo.getBody(), zzoo.zzjz(), zzoo.getCallToAction(), null, zzoo.getStarRating(), zzoo.getStore(), zzoo.getPrice(), zzoo.zzkc(), zzoo.getVideoController(), zzoo.zzkd(), zzoo.zzke(), zzoo.getMediationAdapterClassName(), zzoo.getExtras());
            obj = zzoo.zzka() != null ? ObjectWrapper.unwrap(zzoo.zzka()) : null;
        }
        if (obj instanceof zzpd) {
            zzov.zzb((zzpd) obj);
        }
        return zzov;
    }

    private static void zza(zzbw zzbw, zzbw zzbw2) {
        if (zzbw2.zzade == null) {
            zzbw2.zzade = zzbw.zzade;
        }
        if (zzbw2.zzadf == null) {
            zzbw2.zzadf = zzbw.zzadf;
        }
        if (zzbw2.zzadh == null) {
            zzbw2.zzadh = zzbw.zzadh;
        }
        if (zzbw2.zzadi == null) {
            zzbw2.zzadi = zzbw.zzadi;
        }
        if (zzbw2.zzadk == null) {
            zzbw2.zzadk = zzbw.zzadk;
        }
        if (zzbw2.zzadj == null) {
            zzbw2.zzadj = zzbw.zzadj;
        }
        if (zzbw2.zzads == null) {
            zzbw2.zzads = zzbw.zzads;
        }
        if (zzbw2.zzacy == null) {
            zzbw2.zzacy = zzbw.zzacy;
        }
        if (zzbw2.zzadt == null) {
            zzbw2.zzadt = zzbw.zzadt;
        }
        if (zzbw2.zzacz == null) {
            zzbw2.zzacz = zzbw.zzacz;
        }
        if (zzbw2.zzada == null) {
            zzbw2.zzada = zzbw.zzada;
        }
        if (zzbw2.zzacv == null) {
            zzbw2.zzacv = zzbw.zzacv;
        }
        if (zzbw2.zzacw == null) {
            zzbw2.zzacw = zzbw.zzacw;
        }
        if (zzbw2.zzacx == null) {
            zzbw2.zzacx = zzbw.zzacx;
        }
    }

    private final void zza(zzoo zzoo) {
        zzakk.zzcrm.post(new zzbg(this, zzoo));
    }

    private final void zza(zzoq zzoq) {
        zzakk.zzcrm.post(new zzbi(this, zzoq));
    }

    private final void zza(zzov zzov) {
        zzakk.zzcrm.post(new zzbh(this, zzov));
    }

    private final boolean zzcp() {
        return this.zzvw.zzacw != null && this.zzvw.zzacw.zzcfp;
    }

    @Nullable
    private final zzwy zzcw() {
        return (this.zzvw.zzacw == null || !this.zzvw.zzacw.zzceq) ? null : this.zzvw.zzacw.zzcod;
    }

    private final void zzdx() {
        zzacm zzdr = zzdr();
        if (zzdr != null) {
            zzdr.zzmc();
        }
    }

    public final String getAdUnitId() {
        return this.zzvw.zzacp;
    }

    public final String getUuid() {
        return this.zzaae;
    }

    public final void pause() {
        throw new IllegalStateException("Native Ad DOES NOT support pause().");
    }

    public final void resume() {
        throw new IllegalStateException("Native Ad DOES NOT support resume().");
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
    }

    public final void zza(zzaaw zzaaw) {
        throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
    }

    public final void zza(zzaji zzaji, zznx zznx) {
        Throwable e;
        if (zzaji.zzacv != null) {
            this.zzvw.zzacv = zzaji.zzacv;
        }
        if (zzaji.errorCode != -2) {
            zzakk.zzcrm.post(new zzbd(this, zzaji));
            return;
        }
        int i = zzaji.zzcgs.zzceg;
        if (i == 1) {
            this.zzvw.zzadv = 0;
            zzbw zzbw = this.zzvw;
            zzbv.zzej();
            zzbw.zzacu = zzabl.zza(this.zzvw.zzrt, this, zzaji, this.zzvw.zzacq, null, this.zzwh, this, zznx);
            String str = "AdRenderer: ";
            String valueOf = String.valueOf(this.zzvw.zzacu.getClass().getName());
            zzakb.zzck(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            return;
        }
        JSONArray jSONArray = new JSONArray();
        try {
            int i2;
            JSONArray jSONArray2 = new JSONObject(zzaji.zzcos.zzceo).getJSONArray("slots");
            for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                JSONArray jSONArray3 = jSONArray2.getJSONObject(i3).getJSONArray("ads");
                for (int i4 = 0; i4 < jSONArray3.length(); i4++) {
                    jSONArray.put(jSONArray3.get(i4));
                }
            }
            zzdx();
            List arrayList = new ArrayList();
            for (i2 = 0; i2 < i; i2++) {
                arrayList.add(zzaki.zza(new zzbe(this, i2, jSONArray, i, zzaji)));
            }
            for (i2 = 0; i2 < arrayList.size(); i2++) {
                try {
                    zzakk.zzcrm.post(new zzbf(this, (zzpb) ((zzanz) arrayList.get(i2)).get(((Long) zzkb.zzik().zzd(zznk.zzbao)).longValue(), TimeUnit.MILLISECONDS), i2, arrayList));
                } catch (Throwable e2) {
                    zzane.zzc("", e2);
                    Thread.currentThread().interrupt();
                } catch (CancellationException e3) {
                    e2 = e3;
                    zzane.zzc("", e2);
                } catch (ExecutionException e4) {
                    e2 = e4;
                    zzane.zzc("", e2);
                } catch (TimeoutException e5) {
                    e2 = e5;
                    zzane.zzc("", e2);
                }
            }
        } catch (Throwable e22) {
            zzakb.zzc("Malformed native ad response", e22);
            zzi(0);
        }
    }

    public final void zza(zzod zzod) {
        throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
    }

    public final void zza(zzox zzox) {
        if (this.zzaaa != null) {
            this.zzaaa.zzb(zzox);
        }
    }

    public final void zza(zzoz zzoz) {
        if (this.zzvw.zzacw.zzcob != null) {
            zzbv.zzeo().zzqd().zza(this.zzvw.zzacv, this.zzvw.zzacw, new zzev(zzoz), null);
        }
    }

    protected final boolean zza(zzajh zzajh, zzajh zzajh2) {
        zzd(null);
        if (this.zzvw.zzfo()) {
            if (zzajh2.zzceq) {
                zzdx();
                try {
                    zzyf zzmu = zzajh2.zzbtx != null ? zzajh2.zzbtx.zzmu() : null;
                    zzxz zzmo = zzajh2.zzbtx != null ? zzajh2.zzbtx.zzmo() : null;
                    zzyc zzmp = zzajh2.zzbtx != null ? zzajh2.zzbtx.zzmp() : null;
                    zzqs zzmt = zzajh2.zzbtx != null ? zzajh2.zzbtx.zzmt() : null;
                    String zzc = zzd.zzc(zzajh2);
                    zzov zzov;
                    if (zzmu == null || this.zzvw.zzadg == null) {
                        if (zzmo != null) {
                            if (this.zzvw.zzadg != null) {
                                zzov = new zzov(zzmo.getHeadline(), zzmo.getImages(), zzmo.getBody(), zzmo.zzjz() != null ? zzmo.zzjz() : null, zzmo.getCallToAction(), null, zzmo.getStarRating(), zzmo.getStore(), zzmo.getPrice(), null, zzmo.getVideoController(), zzmo.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmo.zzmw()) : null, zzmo.zzke(), zzc, zzmo.getExtras());
                                zzov.zzb(new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmo, (zzpb) zzov));
                                zza(zzov);
                            }
                        }
                        if (zzmo != null) {
                            if (this.zzvw.zzade != null) {
                                zzoo zzoo = new zzoo(zzmo.getHeadline(), zzmo.getImages(), zzmo.getBody(), zzmo.zzjz() != null ? zzmo.zzjz() : null, zzmo.getCallToAction(), zzmo.getStarRating(), zzmo.getStore(), zzmo.getPrice(), null, zzmo.getExtras(), zzmo.getVideoController(), zzmo.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmo.zzmw()) : null, zzmo.zzke(), zzc);
                                zzoo.zzb(new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmo, (zzpb) zzoo));
                                zza(zzoo);
                            }
                        }
                        if (zzmp != null && this.zzvw.zzadg != null) {
                            zzov = new zzov(zzmp.getHeadline(), zzmp.getImages(), zzmp.getBody(), zzmp.zzkg() != null ? zzmp.zzkg() : null, zzmp.getCallToAction(), zzmp.getAdvertiser(), -1.0d, null, null, null, zzmp.getVideoController(), zzmp.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmp.zzmw()) : null, zzmp.zzke(), zzc, zzmp.getExtras());
                            zzov.zzb(new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmp, (zzpb) zzov));
                            zza(zzov);
                        } else if (zzmp != null && this.zzvw.zzadf != null) {
                            zzoq zzoq = new zzoq(zzmp.getHeadline(), zzmp.getImages(), zzmp.getBody(), zzmp.zzkg() != null ? zzmp.zzkg() : null, zzmp.getCallToAction(), zzmp.getAdvertiser(), null, zzmp.getExtras(), zzmp.getVideoController(), zzmp.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmp.zzmw()) : null, zzmp.zzke(), zzc);
                            zzoq.zzb(new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmp, (zzpb) zzoq));
                            zza(zzoq);
                        } else if (zzmt == null || this.zzvw.zzadi == null || this.zzvw.zzadi.get(zzmt.getCustomTemplateId()) == null) {
                            zzakb.zzdk("No matching mapper/listener for retrieved native ad template.");
                            zzi(0);
                            return false;
                        } else {
                            zzakk.zzcrm.post(new zzbk(this, zzmt));
                        }
                    } else {
                        zzov = new zzov(zzmu.getHeadline(), zzmu.getImages(), zzmu.getBody(), zzmu.zzjz() != null ? zzmu.zzjz() : null, zzmu.getCallToAction(), zzmu.getAdvertiser(), zzmu.getStarRating(), zzmu.getStore(), zzmu.getPrice(), null, zzmu.getVideoController(), zzmu.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmu.zzmw()) : null, zzmu.zzke(), zzc, zzmu.getExtras());
                        zzov.zzb(new zzoy(this.zzvw.zzrt, (zzpa) this, this.zzvw.zzacq, zzmu, (zzpb) zzov));
                        zza(zzov);
                    }
                } catch (Throwable e) {
                    zzakb.zzd("#007 Could not call remote method.", e);
                }
            } else {
                zzpb zzpb = zzajh2.zzcoj;
                if (this.zzzy) {
                    this.zzzz.set(zzpb);
                } else if ((zzpb instanceof zzoq) && this.zzvw.zzadg != null) {
                    zza(zza(zzajh2.zzcoj));
                } else if ((zzpb instanceof zzoq) && this.zzvw.zzadf != null) {
                    zza((zzoq) zzajh2.zzcoj);
                } else if ((zzpb instanceof zzoo) && this.zzvw.zzadg != null) {
                    zza(zza(zzajh2.zzcoj));
                } else if ((zzpb instanceof zzoo) && this.zzvw.zzade != null) {
                    zza((zzoo) zzajh2.zzcoj);
                } else if (!(zzpb instanceof zzos) || this.zzvw.zzadi == null || this.zzvw.zzadi.get(((zzos) zzpb).getCustomTemplateId()) == null) {
                    zzakb.zzdk("No matching listener for retrieved native ad template.");
                    zzi(0);
                    return false;
                } else {
                    zzakk.zzcrm.post(new zzbj(this, ((zzos) zzpb).getCustomTemplateId(), zzajh2));
                }
            }
            return super.zza(zzajh, zzajh2);
        }
        throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
    }

    protected final boolean zza(zzjj zzjj, zzajh zzajh, boolean z) {
        return this.zzvv.zzdz();
    }

    public final boolean zza(zzjj zzjj, zznx zznx) {
        try {
            zzdq();
            return super.zza(zzjj, zznx, this.zzaac);
        } catch (Throwable e) {
            String str = "Error initializing webview.";
            if (zzane.isLoggable(4)) {
                Log.i(AdRequest.LOGTAG, str, e);
            }
            return false;
        }
    }

    protected final void zzb(@Nullable IObjectWrapper iObjectWrapper) {
        Object unwrap = iObjectWrapper != null ? ObjectWrapper.unwrap(iObjectWrapper) : null;
        if (unwrap instanceof zzoz) {
            ((zzoz) unwrap).zzkl();
        }
        super.zzb(this.zzvw.zzacw, false);
    }

    protected final void zzb(boolean z) {
        String str = null;
        super.zzb(z);
        if (this.zzwl) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbcb)).booleanValue()) {
                zzdt();
            }
        }
        if (!zzcp()) {
            return;
        }
        if (this.zzaab != null || this.zzaaa != null) {
            zzaqw zzaqw;
            if (this.zzaab != null) {
                zzaqw = this.zzaab;
            } else if (this.zzaaa != null) {
                str = "javascript";
                zzaqw = this.zzaaa;
            } else {
                zzaqw = null;
            }
            if (zzaqw.getWebView() != null && zzbv.zzfa().zzi(this.zzvw.zzrt)) {
                int i = this.zzvw.zzacr.zzcve;
                this.zzwb = zzbv.zzfa().zza(i + "." + this.zzvw.zzacr.zzcvf, zzaqw.getWebView(), "", "javascript", str);
                if (this.zzwb != null) {
                    zzbv.zzfa().zzm(this.zzwb);
                }
            }
        }
    }

    protected final void zzbq() {
        zzb(false);
    }

    protected final void zzc(int i, boolean z) {
        zzdx();
        super.zzc(i, z);
    }

    public final void zzcd() {
        zzajh zzajh = this.zzvw.zzacw;
        if (zzajh.zzbtx == null) {
            super.zzcd();
            return;
        }
        try {
            zzxq zzxq = zzajh.zzbtx;
            zzlo zzlo = null;
            zzxz zzmo = zzxq.zzmo();
            if (zzmo != null) {
                zzlo = zzmo.getVideoController();
            } else {
                zzyc zzmp = zzxq.zzmp();
                if (zzmp != null) {
                    zzlo = zzmp.getVideoController();
                } else {
                    zzqs zzmt = zzxq.zzmt();
                    if (zzmt != null) {
                        zzlo = zzmt.getVideoController();
                    }
                }
            }
            if (zzlo != null) {
                zzlr zzio = zzlo.zzio();
                if (zzio != null) {
                    zzio.onVideoEnd();
                }
            }
        } catch (Throwable e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void zzce() {
        if (this.zzvw.zzacw == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.zzbty)) {
            super.zzce();
        } else {
            zzbs();
        }
    }

    public final void zzcj() {
        if (this.zzvw.zzacw == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.zzbty)) {
            super.zzcj();
        } else {
            zzbr();
        }
    }

    public final void zzcr() {
        if (zzcp() && this.zzwb != null) {
            zzaqw zzaqw = null;
            if (this.zzaab != null) {
                zzaqw = this.zzaab;
            } else if (this.zzaaa != null) {
                zzaqw = this.zzaaa;
            }
            if (zzaqw != null) {
                zzaqw.zza("onSdkImpression", new HashMap());
            }
        }
    }

    public final void zzcs() {
        super.zzby();
        if (this.zzaab != null) {
            this.zzaab.destroy();
            this.zzaab = null;
        }
    }

    public final void zzct() {
        if (this.zzaaa != null) {
            this.zzaaa.destroy();
            this.zzaaa = null;
        }
    }

    public final boolean zzcu() {
        return zzcw() != null ? zzcw().zzbta : false;
    }

    public final boolean zzcv() {
        return zzcw() != null ? zzcw().zzbtb : false;
    }

    public final void zzd(@Nullable List<String> list) {
        Preconditions.checkMainThread("setNativeTemplates must be called on the main UI thread.");
        this.zzvw.zzads = list;
    }

    final void zzdq() throws zzarg {
        synchronized (this.mLock) {
            zzakb.m3915v("Initializing webview native ads utills");
            this.zzaad = new zzacq(this.zzvw.zzrt, this, this.zzaae, this.zzvw.zzacq, this.zzvw.zzacr);
        }
    }

    @Nullable
    public final zzacm zzdr() {
        zzacm zzacm;
        synchronized (this.mLock) {
            zzacm = this.zzaad;
        }
        return zzacm;
    }

    protected final Future<zzpb> zzds() {
        return this.zzzz;
    }

    public final void zzdt() {
        if (this.zzvw.zzacw == null || this.zzaaa == null) {
            this.zzwl = true;
            zzakb.zzdk("Request to enable ActiveView before adState is available.");
            return;
        }
        zzbv.zzeo().zzqd().zza(this.zzvw.zzacv, this.zzvw.zzacw, this.zzaaa.getView(), this.zzaaa);
        this.zzwl = false;
    }

    public final void zzdu() {
        this.zzwl = false;
        if (this.zzvw.zzacw == null || this.zzaaa == null) {
            zzakb.zzdk("Request to enable ActiveView before adState is available.");
        } else {
            zzbv.zzeo().zzqd().zzh(this.zzvw.zzacw);
        }
    }

    public final SimpleArrayMap<String, zzrf> zzdv() {
        Preconditions.checkMainThread("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        return this.zzvw.zzadi;
    }

    public final void zzdw() {
        if (this.zzaaa != null && this.zzaaa.zztm() != null && this.zzvw.zzadj != null && this.zzvw.zzadj.zzbjr != null) {
            this.zzaaa.zztm().zzb(this.zzvw.zzadj.zzbjr);
        }
    }

    public final void zzf(zzaqw zzaqw) {
        this.zzaaa = zzaqw;
    }

    public final void zzg(@Nullable zzaqw zzaqw) {
        this.zzaab = zzaqw;
    }

    protected final void zzi(int i) {
        zzc(i, false);
    }

    public final void zzi(View view) {
        if (this.zzwb != null) {
            zzbv.zzfa().zza(this.zzwb, view);
        }
    }

    public final void zzj(int i) {
        Preconditions.checkMainThread("setMaxNumberOfAds must be called on the main UI thread.");
        this.zzaac = i;
    }

    @Nullable
    public final zzrc zzr(String str) {
        Preconditions.checkMainThread("getOnCustomClickListener must be called on the main UI thread.");
        return this.zzvw.zzadh == null ? null : (zzrc) this.zzvw.zzadh.get(str);
    }
}
