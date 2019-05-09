// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.webkit.WebView;
import android.support.v4.view.ViewCompat;
import java.io.File;
import android.net.Uri;
import com.google.android.gms.ads.internal.gmsg.zzy;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.gmsg.zzad;
import com.google.android.gms.ads.internal.gmsg.zzac;
import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.gmsg.zza;
import com.google.android.gms.ads.internal.overlay.zzc;
import java.util.Iterator;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.util.Map;
import java.io.IOException;
import java.net.URL;
import android.webkit.WebResourceResponse;
import android.content.Context;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import android.view.View;
import android.support.annotation.Nullable;
import android.view.View$OnAttachStateChangeListener;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
@VisibleForTesting
public final class zzasj extends zzus<zzaqw> implements zzasc, zzasx, zzasz, zzata, zzatb
{
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
    private ViewTreeObserver$OnGlobalLayoutListener zzdbw;
    private ViewTreeObserver$OnScrollChangedListener zzdbx;
    private boolean zzdby;
    private final zzaak zzdbz;
    private zzasg zzdca;
    private boolean zzdcb;
    private boolean zzdcc;
    private int zzdcd;
    private View$OnAttachStateChangeListener zzdce;
    @Nullable
    private zzait zzxd;
    
    public zzasj(final zzaqw zzaqw, final boolean b) {
        this(zzaqw, b, new zzaak(zzaqw, zzaqw.zzua(), new zzmw(zzaqw.getContext())), null);
    }
    
    @VisibleForTesting
    private zzasj(final zzaqw zzbnd, final boolean zzaek, final zzaak zzdbz, final zzaab zzaab) {
        this.mLock = new Object();
        this.zzdbu = false;
        this.zzbnd = zzbnd;
        this.zzaek = zzaek;
        this.zzdbz = zzdbz;
        this.zzbmx = null;
    }
    
    private final void zza(final View view, final zzait zzait, final int n) {
        if (zzait.zzph() && n > 0) {
            zzait.zzr(view);
            if (zzait.zzph()) {
                zzakk.zzcrm.postDelayed((Runnable)new zzasl(this, view, zzait, n), 100L);
            }
        }
    }
    
    private final void zza(final AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean b = false;
        final boolean b2 = this.zzbmx != null && this.zzbmx.zznf();
        zzbv.zzei();
        final Context context = this.zzbnd.getContext();
        if (!b2) {
            b = true;
        }
        zzl.zza(context, adOverlayInfoParcel, b);
        if (this.zzxd != null) {
            final String url = adOverlayInfoParcel.url;
            String url2;
            if ((url2 = url) == null) {
                url2 = url;
                if (adOverlayInfoParcel.zzbyl != null) {
                    url2 = adOverlayInfoParcel.zzbyl.url;
                }
            }
            this.zzxd.zzcf(url2);
        }
    }
    
    private final WebResourceResponse zze(final zzasu zzasu) throws IOException {
        URL url = new URL(zzasu.url);
        int n = 0;
        while (true) {
            ++n;
            if (n > 20) {
                throw new IOException(new StringBuilder(32).append("Too many redirects (20)").toString());
            }
            final URLConnection openConnection = url.openConnection();
            openConnection.setConnectTimeout(10000);
            openConnection.setReadTimeout(10000);
            for (final Map.Entry<String, String> entry : zzasu.zzab.entrySet()) {
                openConnection.addRequestProperty(entry.getKey(), entry.getValue());
            }
            if (!(openConnection instanceof HttpURLConnection)) {
                throw new IOException("Invalid protocol.");
            }
            final HttpURLConnection httpURLConnection = (HttpURLConnection)openConnection;
            zzbv.zzek().zza(this.zzbnd.getContext(), this.zzbnd.zztq().zzcw, false, httpURLConnection);
            final zzamy zzamy = new zzamy();
            zzamy.zza(httpURLConnection, (byte[])null);
            final int responseCode = httpURLConnection.getResponseCode();
            zzamy.zza(httpURLConnection, responseCode);
            if (responseCode < 300 || responseCode >= 400) {
                zzbv.zzek();
                return zzakk.zzb(httpURLConnection);
            }
            final String headerField = httpURLConnection.getHeaderField("Location");
            if (headerField == null) {
                throw new IOException("Missing Location header in redirect");
            }
            final URL url2 = new URL(url, headerField);
            final String protocol = url2.getProtocol();
            if (protocol == null) {
                zzakb.zzdk("Protocol is null");
                return null;
            }
            if (!protocol.equals("http") && !protocol.equals("https")) {
                final String value = String.valueOf(protocol);
                String concat;
                if (value.length() != 0) {
                    concat = "Unsupported scheme: ".concat(value);
                }
                else {
                    concat = new String("Unsupported scheme: ");
                }
                zzakb.zzdk(concat);
                return null;
            }
            final String value2 = String.valueOf(headerField);
            String concat2;
            if (value2.length() != 0) {
                concat2 = "Redirecting to ".concat(value2);
            }
            else {
                concat2 = new String("Redirecting to ");
            }
            zzakb.zzck(concat2);
            httpURLConnection.disconnect();
            url = url2;
        }
    }
    
    private final void zzuy() {
        if (this.zzdce == null) {
            return;
        }
        this.zzbnd.getView().removeOnAttachStateChangeListener(this.zzdce);
    }
    
    private final void zzvd() {
        if (this.zzdbr != null && ((this.zzdcb && this.zzdcd <= 0) || this.zzdcc)) {
            this.zzdbr.zze(!this.zzdcc);
            this.zzdbr = null;
        }
        this.zzbnd.zzup();
    }
    
    @Override
    public final void reset() {
        if (this.zzxd != null) {
            this.zzxd.zzpj();
            this.zzxd = null;
        }
        this.zzuy();
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
    
    @Override
    public final void zza(final int n, final int n2, final boolean b) {
        this.zzdbz.zzc(n, n2);
        if (this.zzbmx != null) {
            this.zzbmx.zza(n, n2, b);
        }
    }
    
    @Override
    public final void zza(final ViewTreeObserver$OnGlobalLayoutListener zzdbw, final ViewTreeObserver$OnScrollChangedListener zzdbx) {
        synchronized (this.mLock) {
            this.zzdbv = true;
            this.zzbnd.zzuo();
            this.zzdbw = zzdbw;
            this.zzdbx = zzdbx;
        }
    }
    
    public final void zza(final zzc zzc) {
        zzn zzbnc = null;
        final boolean zzuj = this.zzbnd.zzuj();
        zzjd zzapt;
        if (zzuj && !this.zzbnd.zzud().zzvs()) {
            zzapt = null;
        }
        else {
            zzapt = this.zzapt;
        }
        if (!zzuj) {
            zzbnc = this.zzbnc;
        }
        this.zza(new AdOverlayInfoParcel(zzc, zzapt, zzbnc, this.zzbnb, this.zzbnd.zztq()));
    }
    
    @Override
    public final void zza(final zzasd zzdbr) {
        this.zzdbr = zzdbr;
    }
    
    @Override
    public final void zza(final zzase zzdbs) {
        this.zzdbs = zzdbs;
    }
    
    @Override
    public final void zza(final zzasf zzdbt) {
        this.zzdbt = zzdbt;
    }
    
    @Override
    public final void zza(final zzasg zzdca) {
        this.zzdca = zzdca;
    }
    
    @Override
    public final void zza(final zzjd zzapt, final zzb zzbll, final zzn zzbnc, final zzd zzblm, final zzt zzbnb, final boolean zzdbu, @Nullable final zzz zzbmu, zzx zzbmw, final zzaam zzbmy, @Nullable final zzait zzxd) {
        if (zzbmw == null) {
            zzbmw = new zzx(this.zzbnd.getContext(), zzxd, null);
        }
        this.zzbmx = new zzaab(this.zzbnd, zzbmy);
        this.zzxd = zzxd;
        if (zzkb.zzik().zzd(zznk.zzayf)) {
            this.zza("/adMetadata", new zza(zzbll));
        }
        this.zza("/appEvent", new com.google.android.gms.ads.internal.gmsg.zzc(zzblm));
        this.zza("/backButton", zzf.zzblx);
        this.zza("/refresh", zzf.zzbly);
        this.zza("/canOpenURLs", zzf.zzblo);
        this.zza("/canOpenIntents", zzf.zzblp);
        this.zza("/click", zzf.zzblq);
        this.zza("/close", zzf.zzblr);
        this.zza("/customClose", zzf.zzbls);
        this.zza("/instrument", zzf.zzbmb);
        this.zza("/delayPageLoaded", zzf.zzbmd);
        this.zza("/delayPageClosed", zzf.zzbme);
        this.zza("/getLocationInfo", zzf.zzbmf);
        this.zza("/httpTrack", zzf.zzblt);
        this.zza("/log", zzf.zzblu);
        this.zza("/mraid", new zzac(zzbmw, this.zzbmx, zzbmy));
        this.zza("/mraidLoaded", this.zzdbz);
        this.zza("/open", new zzad<Object>(this.zzbnd.getContext(), this.zzbnd.zztq(), this.zzbnd.zzui(), zzbnb, zzapt, zzbll, zzblm, zzbnc, zzbmw, this.zzbmx));
        this.zza("/precache", new zzaql());
        this.zza("/touch", zzf.zzblw);
        this.zza("/video", zzf.zzblz);
        this.zza("/videoMeta", zzf.zzbma);
        if (zzbv.zzfh().zzs(this.zzbnd.getContext())) {
            this.zza("/logScionEvent", new zzab(this.zzbnd.getContext()));
        }
        if (zzbmu != null) {
            this.zza("/setInterstitialProperties", new zzy(zzbmu));
        }
        this.zzapt = zzapt;
        this.zzbnc = zzbnc;
        this.zzbll = zzbll;
        this.zzblm = zzblm;
        this.zzbnb = zzbnb;
        this.zzbmw = zzbmw;
        this.zzbmy = zzbmy;
        this.zzbmu = zzbmu;
        this.zzdbu = zzdbu;
    }
    
    public final void zza(final boolean b, final int n) {
        zzjd zzapt;
        if (this.zzbnd.zzuj() && !this.zzbnd.zzud().zzvs()) {
            zzapt = null;
        }
        else {
            zzapt = this.zzapt;
        }
        this.zza(new AdOverlayInfoParcel(zzapt, this.zzbnc, this.zzbnb, this.zzbnd, b, n, this.zzbnd.zztq()));
    }
    
    public final void zza(final boolean b, final int n, final String s) {
        zzn zzn = null;
        final boolean zzuj = this.zzbnd.zzuj();
        zzjd zzapt;
        if (zzuj && !this.zzbnd.zzud().zzvs()) {
            zzapt = null;
        }
        else {
            zzapt = this.zzapt;
        }
        if (!zzuj) {
            zzn = new zzasn(this.zzbnd, this.zzbnc);
        }
        this.zza(new AdOverlayInfoParcel(zzapt, zzn, this.zzbll, this.zzblm, this.zzbnb, this.zzbnd, b, n, s, this.zzbnd.zztq()));
    }
    
    public final void zza(final boolean b, final int n, final String s, final String s2) {
        final boolean zzuj = this.zzbnd.zzuj();
        zzjd zzapt;
        if (zzuj && !this.zzbnd.zzud().zzvs()) {
            zzapt = null;
        }
        else {
            zzapt = this.zzapt;
        }
        zzn zzn;
        if (zzuj) {
            zzn = null;
        }
        else {
            zzn = new zzasn(this.zzbnd, this.zzbnc);
        }
        this.zza(new AdOverlayInfoParcel(zzapt, zzn, this.zzbll, this.zzblm, this.zzbnb, this.zzbnd, b, n, s, s2, this.zzbnd.zztq()));
    }
    
    @Override
    public final boolean zza(zzasu concat) {
        final String value = String.valueOf(concat.url);
        String concat2;
        if (value.length() != 0) {
            concat2 = "AdWebView shouldOverrideUrlLoading: ".concat(value);
        }
        else {
            concat2 = new String("AdWebView shouldOverrideUrlLoading: ");
        }
        zzakb.v(concat2);
        final Uri uri = concat.uri;
        if (this.zzf(uri)) {
            return true;
        }
        if (this.zzdbu) {
            final String scheme = uri.getScheme();
            int n;
            if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0) {
                if (this.zzapt != null && (boolean)zzkb.zzik().zzd(zznk.zzaxf)) {
                    this.zzapt.onAdClicked();
                    if (this.zzxd != null) {
                        this.zzxd.zzcf(concat.url);
                    }
                    this.zzapt = null;
                }
                return false;
            }
        }
        if (this.zzbnd.getWebView().willNotDraw()) {
            final String value2 = String.valueOf(concat.url);
            if (value2.length() != 0) {
                concat = (zzasu)"AdWebView unable to handle URL: ".concat(value2);
            }
            else {
                concat = (zzasu)new String("AdWebView unable to handle URL: ");
            }
            zzakb.zzdk((String)concat);
            return true;
        }
        while (true) {
            while (true) {
                try {
                    final zzci zzui = this.zzbnd.zzui();
                    Uri zza = uri;
                    if (zzui != null) {
                        zza = uri;
                        if (zzui.zzb(uri)) {
                            zza = zzui.zza(uri, this.zzbnd.getContext(), this.zzbnd.getView(), this.zzbnd.zzto());
                        }
                    }
                    if (this.zzbmw == null || this.zzbmw.zzcy()) {
                        this.zza(new zzc("android.intent.action.VIEW", zza.toString(), null, null, null, null, null));
                        return true;
                    }
                }
                catch (zzcj zzcj) {
                    final String value3 = String.valueOf(concat.url);
                    String concat3;
                    if (value3.length() != 0) {
                        concat3 = "Unable to append parameter to URL: ".concat(value3);
                    }
                    else {
                        concat3 = new String("Unable to append parameter to URL: ");
                    }
                    zzakb.zzdk(concat3);
                    final Uri zza = uri;
                    continue;
                }
                break;
            }
            this.zzbmw.zzs(concat.url);
            return true;
        }
    }
    
    public final void zzah(final boolean zzdbu) {
        this.zzdbu = zzdbu;
    }
    
    @Override
    public final void zzb(final int n, final int n2) {
        if (this.zzbmx != null) {
            this.zzbmx.zzb(n, n2);
        }
    }
    
    @Override
    public final void zzb(final zzasu zzasu) {
        this.zzf(zzasu.uri);
    }
    
    @Override
    public final void zzc(final zzasu zzasu) {
        this.zzdcb = true;
        if (this.zzdbs != null) {
            this.zzdbs.zzly();
            this.zzdbs = null;
        }
        this.zzvd();
    }
    
    @Nullable
    @Override
    public final WebResourceResponse zzd(zzasu noClassDefFoundError) {
        if (this.zzxd != null) {
            this.zzxd.zza(((zzasu)noClassDefFoundError).url, ((zzasu)noClassDefFoundError).zzab, 1);
        }
        WebResourceResponse zzf;
        if (!"mraid.js".equalsIgnoreCase(new File(((zzasu)noClassDefFoundError).url).getName())) {
            zzf = null;
        }
        else {
            this.zznk();
            String s;
            if (this.zzbnd.zzud().zzvs()) {
                s = (String)zzkb.zzik().zzd(zznk.zzawe);
            }
            else if (this.zzbnd.zzuj()) {
                s = (String)zzkb.zzik().zzd(zznk.zzawd);
            }
            else {
                s = (String)zzkb.zzik().zzd(zznk.zzawc);
            }
            zzbv.zzek();
            zzf = zzakk.zzf(this.zzbnd.getContext(), this.zzbnd.zztq().zzcw, s);
        }
        if (zzf != null) {
            return zzf;
        }
        try {
            if (!zzajb.zzb(((zzasu)noClassDefFoundError).url, this.zzbnd.getContext()).equals(((zzasu)noClassDefFoundError).url)) {
                return this.zze((zzasu)noClassDefFoundError);
            }
            final zzhl zzaa = zzhl.zzaa(((zzasu)noClassDefFoundError).url);
            if (zzaa == null) {
                goto Label_0267;
            }
            final zzhi zza = zzbv.zzeq().zza(zzaa);
            if (zza != null && zza.zzhi()) {
                noClassDefFoundError = (NoClassDefFoundError)new WebResourceResponse("", "", zza.zzhj());
                return (WebResourceResponse)noClassDefFoundError;
            }
            goto Label_0267;
        }
        catch (Exception ex) {}
        catch (NoClassDefFoundError noClassDefFoundError) {
            goto Label_0255;
        }
    }
    
    @Override
    public final boolean zzfz() {
        synchronized (this.mLock) {
            return this.zzaek;
        }
    }
    
    @Override
    public final void zznk() {
        synchronized (this.mLock) {
            this.zzdbu = false;
            this.zzaek = true;
            zzaoe.zzcvy.execute(new zzask(this));
        }
    }
    
    @Override
    public final zzx zzut() {
        return this.zzbmw;
    }
    
    public final boolean zzuu() {
        synchronized (this.mLock) {
            return this.zzdbv;
        }
    }
    
    public final ViewTreeObserver$OnGlobalLayoutListener zzuv() {
        synchronized (this.mLock) {
            return this.zzdbw;
        }
    }
    
    public final ViewTreeObserver$OnScrollChangedListener zzuw() {
        synchronized (this.mLock) {
            return this.zzdbx;
        }
    }
    
    @Override
    public final boolean zzux() {
        synchronized (this.mLock) {
            return this.zzdby;
        }
    }
    
    @Override
    public final void zzuz() {
        final zzait zzxd = this.zzxd;
        if (zzxd != null) {
            final WebView webView = this.zzbnd.getWebView();
            if (!ViewCompat.isAttachedToWindow((View)webView)) {
                this.zzuy();
                this.zzdce = (View$OnAttachStateChangeListener)new zzasm(this, zzxd);
                this.zzbnd.getView().addOnAttachStateChangeListener(this.zzdce);
                return;
            }
            this.zza((View)webView, zzxd, 10);
        }
    }
    
    @Override
    public final void zzva() {
        synchronized (this.mLock) {
            this.zzdby = true;
            // monitorexit(this.mLock)
            ++this.zzdcd;
            this.zzvd();
        }
    }
    
    @Override
    public final void zzvb() {
        --this.zzdcd;
        this.zzvd();
    }
    
    @Override
    public final void zzvc() {
        this.zzdcc = true;
        this.zzvd();
    }
    
    public final zzasg zzve() {
        return this.zzdca;
    }
    
    @Override
    public final zzait zzvf() {
        return this.zzxd;
    }
}
