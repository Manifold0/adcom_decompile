package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.WebResourceResponse;
import com.adjust.sdk.Constants;
import com.google.android.gms.ads.internal.gmsg.zza;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.gmsg.zzac;
import com.google.android.gms.ads.internal.gmsg.zzad;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.gmsg.zzy;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
@VisibleForTesting
public final class zzasj extends zzus<zzaqw> implements zzasc, zzasx, zzasz, zzata, zzatb {
    private final Object mLock;
    private boolean zzaek;
    private zzjd zzapt;
    private zzb zzbll;
    private zzd zzblm;
    private zzz zzbmu;
    private zzx zzbmw;
    private zzaab zzbmx;
    private zzaam zzbmy;
    private zzt zzbnb;
    private zzn zzbnc;
    private zzaqw zzbnd;
    private zzasd zzdbr;
    private zzase zzdbs;
    private zzasf zzdbt;
    private boolean zzdbu;
    private boolean zzdbv;
    private OnGlobalLayoutListener zzdbw;
    private OnScrollChangedListener zzdbx;
    private boolean zzdby;
    private final zzaak zzdbz;
    private zzasg zzdca;
    private boolean zzdcb;
    private boolean zzdcc;
    private int zzdcd;
    private OnAttachStateChangeListener zzdce;
    @Nullable
    private zzait zzxd;

    public zzasj(zzaqw zzaqw, boolean z) {
        this(zzaqw, z, new zzaak(zzaqw, zzaqw.zzua(), new zzmw(zzaqw.getContext())), null);
    }

    @VisibleForTesting
    private zzasj(zzaqw zzaqw, boolean z, zzaak zzaak, zzaab zzaab) {
        this.mLock = new Object();
        this.zzdbu = false;
        this.zzbnd = zzaqw;
        this.zzaek = z;
        this.zzdbz = zzaak;
        this.zzbmx = null;
    }

    private final void zza(View view, zzait zzait, int i) {
        if (zzait.zzph() && i > 0) {
            zzait.zzr(view);
            if (zzait.zzph()) {
                zzakk.zzcrm.postDelayed(new zzasl(this, view, zzait, i), 100);
            }
        }
    }

    private final void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean z = false;
        boolean zznf = this.zzbmx != null ? this.zzbmx.zznf() : false;
        zzbv.zzei();
        Context context = this.zzbnd.getContext();
        if (!zznf) {
            z = true;
        }
        zzl.zza(context, adOverlayInfoParcel, z);
        if (this.zzxd != null) {
            String str = adOverlayInfoParcel.url;
            if (str == null && adOverlayInfoParcel.zzbyl != null) {
                str = adOverlayInfoParcel.zzbyl.url;
            }
            this.zzxd.zzcf(str);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.webkit.WebResourceResponse zze(com.google.android.gms.internal.ads.zzasu r10) throws java.io.IOException {
        /*
        r9 = this;
        r8 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r4 = 0;
        r3 = 0;
        r0 = new java.net.URL;
        r1 = r10.url;
        r0.<init>(r1);
        r6 = r0;
        r0 = r4;
    L_0x000d:
        r5 = r0 + 1;
        r0 = 20;
        if (r5 > r0) goto L_0x00f3;
    L_0x0013:
        r2 = r6.openConnection();
        r2.setConnectTimeout(r8);
        r2.setReadTimeout(r8);
        r0 = r10.zzab;
        r0 = r0.entrySet();
        r7 = r0.iterator();
    L_0x0027:
        r0 = r7.hasNext();
        if (r0 == 0) goto L_0x0043;
    L_0x002d:
        r0 = r7.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (java.lang.String) r1;
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r2.addRequestProperty(r1, r0);
        goto L_0x0027;
    L_0x0043:
        r0 = r2 instanceof java.net.HttpURLConnection;
        if (r0 != 0) goto L_0x004f;
    L_0x0047:
        r0 = new java.io.IOException;
        r1 = "Invalid protocol.";
        r0.<init>(r1);
        throw r0;
    L_0x004f:
        r0 = r2;
        r0 = (java.net.HttpURLConnection) r0;
        r1 = com.google.android.gms.ads.internal.zzbv.zzek();
        r2 = r9.zzbnd;
        r2 = r2.getContext();
        r7 = r9.zzbnd;
        r7 = r7.zztq();
        r7 = r7.zzcw;
        r1.zza(r2, r7, r4, r0);
        r1 = new com.google.android.gms.internal.ads.zzamy;
        r1.<init>();
        r1.zza(r0, r3);
        r2 = r0.getResponseCode();
        r1.zza(r0, r2);
        r1 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r2 < r1) goto L_0x00eb;
    L_0x007a:
        r1 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r2 >= r1) goto L_0x00eb;
    L_0x007e:
        r1 = "Location";
        r1 = r0.getHeaderField(r1);
        if (r1 != 0) goto L_0x008e;
    L_0x0086:
        r0 = new java.io.IOException;
        r1 = "Missing Location header in redirect";
        r0.<init>(r1);
        throw r0;
    L_0x008e:
        r2 = new java.net.URL;
        r2.<init>(r6, r1);
        r6 = r2.getProtocol();
        if (r6 != 0) goto L_0x00a0;
    L_0x0099:
        r0 = "Protocol is null";
        com.google.android.gms.internal.ads.zzakb.zzdk(r0);
        r0 = r3;
    L_0x009f:
        return r0;
    L_0x00a0:
        r7 = "http";
        r7 = r6.equals(r7);
        if (r7 != 0) goto L_0x00cb;
    L_0x00a8:
        r7 = "https";
        r7 = r6.equals(r7);
        if (r7 != 0) goto L_0x00cb;
    L_0x00b0:
        r1 = "Unsupported scheme: ";
        r0 = java.lang.String.valueOf(r6);
        r2 = r0.length();
        if (r2 == 0) goto L_0x00c5;
    L_0x00bc:
        r0 = r1.concat(r0);
    L_0x00c0:
        com.google.android.gms.internal.ads.zzakb.zzdk(r0);
        r0 = r3;
        goto L_0x009f;
    L_0x00c5:
        r0 = new java.lang.String;
        r0.<init>(r1);
        goto L_0x00c0;
    L_0x00cb:
        r6 = "Redirecting to ";
        r1 = java.lang.String.valueOf(r1);
        r7 = r1.length();
        if (r7 == 0) goto L_0x00e5;
    L_0x00d7:
        r1 = r6.concat(r1);
    L_0x00db:
        com.google.android.gms.internal.ads.zzakb.zzck(r1);
        r0.disconnect();
        r0 = r5;
        r6 = r2;
        goto L_0x000d;
    L_0x00e5:
        r1 = new java.lang.String;
        r1.<init>(r6);
        goto L_0x00db;
    L_0x00eb:
        com.google.android.gms.ads.internal.zzbv.zzek();
        r0 = com.google.android.gms.internal.ads.zzakk.zzb(r0);
        goto L_0x009f;
    L_0x00f3:
        r0 = new java.io.IOException;
        r1 = 32;
        r2 = new java.lang.StringBuilder;
        r2.<init>(r1);
        r1 = "Too many redirects (20)";
        r1 = r2.append(r1);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzasj.zze(com.google.android.gms.internal.ads.zzasu):android.webkit.WebResourceResponse");
    }

    private final void zzuy() {
        if (this.zzdce != null) {
            this.zzbnd.getView().removeOnAttachStateChangeListener(this.zzdce);
        }
    }

    private final void zzvd() {
        if (this.zzdbr != null && ((this.zzdcb && this.zzdcd <= 0) || this.zzdcc)) {
            this.zzdbr.zze(!this.zzdcc);
            this.zzdbr = null;
        }
        this.zzbnd.zzup();
    }

    public final /* synthetic */ Object getReference() {
        return this.zzbnd;
    }

    public final void reset() {
        if (this.zzxd != null) {
            this.zzxd.zzpj();
            this.zzxd = null;
        }
        zzuy();
        super.reset();
        synchronized (this.mLock) {
            this.zzapt = null;
            this.zzbnc = null;
            this.zzdbr = null;
            this.zzdbs = null;
            this.zzbll = null;
            this.zzblm = null;
            this.zzdbu = false;
            this.zzaek = false;
            this.zzdbv = false;
            this.zzdby = false;
            this.zzbnb = null;
            this.zzdbt = null;
            if (this.zzbmx != null) {
                this.zzbmx.zzm(true);
                this.zzbmx = null;
            }
        }
    }

    public final void zza(int i, int i2, boolean z) {
        this.zzdbz.zzc(i, i2);
        if (this.zzbmx != null) {
            this.zzbmx.zza(i, i2, z);
        }
    }

    public final void zza(OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        synchronized (this.mLock) {
            this.zzdbv = true;
            this.zzbnd.zzuo();
            this.zzdbw = onGlobalLayoutListener;
            this.zzdbx = onScrollChangedListener;
        }
    }

    public final void zza(zzc zzc) {
        zzn zzn = null;
        boolean zzuj = this.zzbnd.zzuj();
        zzjd zzjd = (!zzuj || this.zzbnd.zzud().zzvs()) ? this.zzapt : null;
        if (!zzuj) {
            zzn = this.zzbnc;
        }
        zza(new AdOverlayInfoParcel(zzc, zzjd, zzn, this.zzbnb, this.zzbnd.zztq()));
    }

    public final void zza(zzasd zzasd) {
        this.zzdbr = zzasd;
    }

    public final void zza(zzase zzase) {
        this.zzdbs = zzase;
    }

    public final void zza(zzasf zzasf) {
        this.zzdbt = zzasf;
    }

    public final void zza(zzasg zzasg) {
        this.zzdca = zzasg;
    }

    public final void zza(zzjd zzjd, zzb zzb, zzn zzn, zzd zzd, zzt zzt, boolean z, @Nullable zzz zzz, zzx zzx, zzaam zzaam, @Nullable zzait zzait) {
        zzx zzx2 = zzx == null ? new zzx(this.zzbnd.getContext(), zzait, null) : zzx;
        this.zzbmx = new zzaab(this.zzbnd, zzaam);
        this.zzxd = zzait;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzayf)).booleanValue()) {
            zza("/adMetadata", new zza(zzb));
        }
        zza("/appEvent", new com.google.android.gms.ads.internal.gmsg.zzc(zzd));
        zza("/backButton", zzf.zzblx);
        zza("/refresh", zzf.zzbly);
        zza("/canOpenURLs", zzf.zzblo);
        zza("/canOpenIntents", zzf.zzblp);
        zza("/click", zzf.zzblq);
        zza("/close", zzf.zzblr);
        zza("/customClose", zzf.zzbls);
        zza("/instrument", zzf.zzbmb);
        zza("/delayPageLoaded", zzf.zzbmd);
        zza("/delayPageClosed", zzf.zzbme);
        zza("/getLocationInfo", zzf.zzbmf);
        zza("/httpTrack", zzf.zzblt);
        zza("/log", zzf.zzblu);
        zza("/mraid", new zzac(zzx2, this.zzbmx, zzaam));
        zza("/mraidLoaded", this.zzdbz);
        zza("/open", new zzad(this.zzbnd.getContext(), this.zzbnd.zztq(), this.zzbnd.zzui(), zzt, zzjd, zzb, zzd, zzn, zzx2, this.zzbmx));
        zza("/precache", new zzaql());
        zza("/touch", zzf.zzblw);
        zza("/video", zzf.zzblz);
        zza("/videoMeta", zzf.zzbma);
        if (zzbv.zzfh().zzs(this.zzbnd.getContext())) {
            zza("/logScionEvent", new zzab(this.zzbnd.getContext()));
        }
        if (zzz != null) {
            zza("/setInterstitialProperties", new zzy(zzz));
        }
        this.zzapt = zzjd;
        this.zzbnc = zzn;
        this.zzbll = zzb;
        this.zzblm = zzd;
        this.zzbnb = zzt;
        this.zzbmw = zzx2;
        this.zzbmy = zzaam;
        this.zzbmu = zzz;
        this.zzdbu = z;
    }

    public final void zza(boolean z, int i) {
        zzjd zzjd = (!this.zzbnd.zzuj() || this.zzbnd.zzud().zzvs()) ? this.zzapt : null;
        zza(new AdOverlayInfoParcel(zzjd, this.zzbnc, this.zzbnb, this.zzbnd, z, i, this.zzbnd.zztq()));
    }

    public final void zza(boolean z, int i, String str) {
        zzn zzn = null;
        boolean zzuj = this.zzbnd.zzuj();
        zzjd zzjd = (!zzuj || this.zzbnd.zzud().zzvs()) ? this.zzapt : null;
        if (!zzuj) {
            zzn = new zzasn(this.zzbnd, this.zzbnc);
        }
        zza(new AdOverlayInfoParcel(zzjd, zzn, this.zzbll, this.zzblm, this.zzbnb, this.zzbnd, z, i, str, this.zzbnd.zztq()));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        boolean zzuj = this.zzbnd.zzuj();
        zzjd zzjd = (!zzuj || this.zzbnd.zzud().zzvs()) ? this.zzapt : null;
        zza(new AdOverlayInfoParcel(zzjd, zzuj ? null : new zzasn(this.zzbnd, this.zzbnc), this.zzbll, this.zzblm, this.zzbnb, this.zzbnd, z, i, str, str2, this.zzbnd.zztq()));
    }

    public final boolean zza(zzasu zzasu) {
        String str;
        String str2 = "AdWebView shouldOverrideUrlLoading: ";
        String valueOf = String.valueOf(zzasu.url);
        zzakb.m3915v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        Uri uri = zzasu.uri;
        if (zzf(uri)) {
            return true;
        }
        if (this.zzdbu) {
            str2 = uri.getScheme();
            Object obj = ("http".equalsIgnoreCase(str2) || Constants.SCHEME.equalsIgnoreCase(str2)) ? 1 : null;
            if (obj != null) {
                if (this.zzapt != null) {
                    if (((Boolean) zzkb.zzik().zzd(zznk.zzaxf)).booleanValue()) {
                        this.zzapt.onAdClicked();
                        if (this.zzxd != null) {
                            this.zzxd.zzcf(zzasu.url);
                        }
                        this.zzapt = null;
                    }
                }
                return false;
            }
        }
        if (this.zzbnd.getWebView().willNotDraw()) {
            str = "AdWebView unable to handle URL: ";
            valueOf = String.valueOf(zzasu.url);
            zzakb.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        } else {
            Uri uri2;
            try {
                zzci zzui = this.zzbnd.zzui();
                if (zzui != null && zzui.zzb(uri)) {
                    uri = zzui.zza(uri, this.zzbnd.getContext(), this.zzbnd.getView(), this.zzbnd.zzto());
                }
                uri2 = uri;
            } catch (zzcj e) {
                str2 = "Unable to append parameter to URL: ";
                str = String.valueOf(zzasu.url);
                zzakb.zzdk(str.length() != 0 ? str2.concat(str) : new String(str2));
                uri2 = uri;
            }
            if (this.zzbmw == null || this.zzbmw.zzcy()) {
                zza(new zzc("android.intent.action.VIEW", uri2.toString(), null, null, null, null, null));
            } else {
                this.zzbmw.zzs(zzasu.url);
            }
        }
        return true;
    }

    public final void zzah(boolean z) {
        this.zzdbu = z;
    }

    public final void zzb(int i, int i2) {
        if (this.zzbmx != null) {
            this.zzbmx.zzb(i, i2);
        }
    }

    public final void zzb(zzasu zzasu) {
        zzf(zzasu.uri);
    }

    public final void zzc(zzasu zzasu) {
        this.zzdcb = true;
        if (this.zzdbs != null) {
            this.zzdbs.zzly();
            this.zzdbs = null;
        }
        zzvd();
    }

    @Nullable
    public final WebResourceResponse zzd(zzasu zzasu) {
        WebResourceResponse zzf;
        Throwable e;
        if (this.zzxd != null) {
            this.zzxd.zza(zzasu.url, zzasu.zzab, 1);
        }
        if ("mraid.js".equalsIgnoreCase(new File(zzasu.url).getName())) {
            String str;
            zznk();
            if (this.zzbnd.zzud().zzvs()) {
                str = (String) zzkb.zzik().zzd(zznk.zzawe);
            } else if (this.zzbnd.zzuj()) {
                str = (String) zzkb.zzik().zzd(zznk.zzawd);
            } else {
                str = (String) zzkb.zzik().zzd(zznk.zzawc);
            }
            zzbv.zzek();
            zzf = zzakk.zzf(this.zzbnd.getContext(), this.zzbnd.zztq().zzcw, str);
        } else {
            zzf = null;
        }
        if (zzf != null) {
            return zzf;
        }
        try {
            if (!zzajb.zzb(zzasu.url, this.zzbnd.getContext()).equals(zzasu.url)) {
                return zze(zzasu);
            }
            zzhl zzaa = zzhl.zzaa(zzasu.url);
            if (zzaa != null) {
                zzhi zza = zzbv.zzeq().zza(zzaa);
                if (zza != null && zza.zzhi()) {
                    return new WebResourceResponse("", "", zza.zzhj());
                }
            }
            if (zzamy.isEnabled()) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzazn)).booleanValue()) {
                    return zze(zzasu);
                }
            }
            return null;
        } catch (Exception e2) {
            e = e2;
            zzbv.zzeo().zza(e, "AdWebViewClient.interceptRequest");
            return null;
        } catch (NoClassDefFoundError e3) {
            e = e3;
            zzbv.zzeo().zza(e, "AdWebViewClient.interceptRequest");
            return null;
        }
    }

    public final boolean zzfz() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzaek;
        }
        return z;
    }

    public final void zznk() {
        synchronized (this.mLock) {
            this.zzdbu = false;
            this.zzaek = true;
            zzaoe.zzcvy.execute(new zzask(this));
        }
    }

    public final zzx zzut() {
        return this.zzbmw;
    }

    public final boolean zzuu() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdbv;
        }
        return z;
    }

    public final OnGlobalLayoutListener zzuv() {
        OnGlobalLayoutListener onGlobalLayoutListener;
        synchronized (this.mLock) {
            onGlobalLayoutListener = this.zzdbw;
        }
        return onGlobalLayoutListener;
    }

    public final OnScrollChangedListener zzuw() {
        OnScrollChangedListener onScrollChangedListener;
        synchronized (this.mLock) {
            onScrollChangedListener = this.zzdbx;
        }
        return onScrollChangedListener;
    }

    public final boolean zzux() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdby;
        }
        return z;
    }

    public final void zzuz() {
        zzait zzait = this.zzxd;
        if (zzait != null) {
            View webView = this.zzbnd.getWebView();
            if (ViewCompat.isAttachedToWindow(webView)) {
                zza(webView, zzait, 10);
                return;
            }
            zzuy();
            this.zzdce = new zzasm(this, zzait);
            this.zzbnd.getView().addOnAttachStateChangeListener(this.zzdce);
        }
    }

    public final void zzva() {
        synchronized (this.mLock) {
            this.zzdby = true;
        }
        this.zzdcd++;
        zzvd();
    }

    public final void zzvb() {
        this.zzdcd--;
        zzvd();
    }

    public final void zzvc() {
        this.zzdcc = true;
        zzvd();
    }

    public final zzasg zzve() {
        return this.zzdca;
    }

    public final zzait zzvf() {
        return this.zzxd;
    }

    final /* synthetic */ void zzvg() {
        this.zzbnd.zzuo();
        com.google.android.gms.ads.internal.overlay.zzd zzub = this.zzbnd.zzub();
        if (zzub != null) {
            zzub.zznk();
        }
        if (this.zzdbt != null) {
            this.zzdbt.zzdb();
            this.zzdbt = null;
        }
    }
}
