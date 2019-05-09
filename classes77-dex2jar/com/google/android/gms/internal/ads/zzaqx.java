// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.v4.view.ViewCompat;
import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.common.util.Predicate;
import java.util.concurrent.CopyOnWriteArrayList;
import com.google.android.gms.ads.internal.gmsg.zzy;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.gmsg.zzad;
import com.google.android.gms.ads.internal.gmsg.zzac;
import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.gmsg.zza;
import com.google.android.gms.ads.internal.overlay.zzc;
import android.view.KeyEvent;
import android.annotation.TargetApi;
import java.util.Collections;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import java.util.Iterator;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.URL;
import android.webkit.WebResourceResponse;
import java.util.Map;
import android.net.Uri;
import android.text.TextUtils;
import android.os.Bundle;
import android.content.Context;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import android.view.View;
import android.support.annotation.Nullable;
import android.view.View$OnAttachStateChangeListener;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.List;
import java.util.HashMap;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.gmsg.zzb;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;
import android.webkit.WebViewClient;

@zzadh
@ParametersAreNonnullByDefault
@VisibleForTesting
public class zzaqx extends WebViewClient implements zzasc
{
    private static final String[] zzdbo;
    private static final String[] zzdbp;
    private final Object mLock;
    @GuardedBy("mLock")
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
    private final HashMap<String, List<zzv<? super zzaqw>>> zzdbq;
    private zzasd zzdbr;
    private zzase zzdbs;
    private zzasf zzdbt;
    private boolean zzdbu;
    @GuardedBy("mLock")
    private boolean zzdbv;
    @GuardedBy("mLock")
    private ViewTreeObserver$OnGlobalLayoutListener zzdbw;
    @GuardedBy("mLock")
    private ViewTreeObserver$OnScrollChangedListener zzdbx;
    @GuardedBy("mLock")
    private boolean zzdby;
    private final zzaak zzdbz;
    private zzasg zzdca;
    private boolean zzdcb;
    private boolean zzdcc;
    private int zzdcd;
    private View$OnAttachStateChangeListener zzdce;
    @Nullable
    protected zzait zzxd;
    
    static {
        zzdbo = new String[] { "UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS" };
        zzdbp = new String[] { "NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID" };
    }
    
    public zzaqx(final zzaqw zzaqw, final boolean b) {
        this(zzaqw, b, new zzaak(zzaqw, zzaqw.zzua(), new zzmw(zzaqw.getContext())), null);
    }
    
    @VisibleForTesting
    private zzaqx(final zzaqw zzbnd, final boolean zzaek, final zzaak zzdbz, final zzaab zzaab) {
        this.zzdbq = new HashMap<String, List<zzv<? super zzaqw>>>();
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
                zzakk.zzcrm.postDelayed((Runnable)new zzaqz(this, view, zzait, n), 100L);
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
    
    private final void zzd(final Context context, String host, final String s, final String s2) {
        if (!(boolean)zzkb.zzik().zzd(zznk.zzazy)) {
            return;
        }
        final Bundle bundle = new Bundle();
        bundle.putString("err", host);
        bundle.putString("code", s);
        while (true) {
            Label_0111: {
                if (TextUtils.isEmpty((CharSequence)s2)) {
                    break Label_0111;
                }
                final Uri parse = Uri.parse(s2);
                if (parse.getHost() == null) {
                    break Label_0111;
                }
                host = parse.getHost();
                bundle.putString("host", host);
                zzbv.zzek().zza(context, this.zzbnd.zztq().zzcw, "gmob-apps", bundle, true);
                return;
            }
            host = "";
            continue;
        }
    }
    
    private final WebResourceResponse zze(String s, final Map<String, String> map) throws IOException {
        URL url = new URL(s);
        int n = 0;
        while (true) {
            ++n;
            if (n > 20) {
                throw new IOException(new StringBuilder(32).append("Too many redirects (20)").toString());
            }
            final URLConnection openConnection = url.openConnection();
            openConnection.setConnectTimeout(10000);
            openConnection.setReadTimeout(10000);
            for (final Map.Entry<String, String> entry : map.entrySet()) {
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
            s = url2.getProtocol();
            if (s == null) {
                zzakb.zzdk("Protocol is null");
                return null;
            }
            if (!s.equals("http") && !s.equals("https")) {
                s = String.valueOf(s);
                if (s.length() != 0) {
                    s = "Unsupported scheme: ".concat(s);
                }
                else {
                    s = new String("Unsupported scheme: ");
                }
                zzakb.zzdk(s);
                return null;
            }
            s = String.valueOf(headerField);
            if (s.length() != 0) {
                s = "Redirecting to ".concat(s);
            }
            else {
                s = new String("Redirecting to ");
            }
            zzakb.zzck(s);
            httpURLConnection.disconnect();
            url = url2;
        }
    }
    
    private final void zzi(final Uri uri) {
        final String path = uri.getPath();
        final List<zzv<? super zzaqw>> list = this.zzdbq.get(path);
        if (list != null) {
            zzbv.zzek();
            final Map<String, String> zzg = zzakk.zzg(uri);
            if (zzakb.isLoggable(2)) {
                final String value = String.valueOf(path);
                String concat;
                if (value.length() != 0) {
                    concat = "Received GMSG: ".concat(value);
                }
                else {
                    concat = new String("Received GMSG: ");
                }
                zzakb.v(concat);
                for (final String s : zzg.keySet()) {
                    final String s2 = zzg.get(s);
                    zzakb.v(new StringBuilder(String.valueOf(s).length() + 4 + String.valueOf(s2).length()).append("  ").append(s).append(": ").append(s2).toString());
                }
            }
            final Iterator<zzv<zzaqw>> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().zza(this.zzbnd, zzg);
            }
        }
        else {
            final String value2 = String.valueOf(uri);
            zzakb.v(new StringBuilder(String.valueOf(value2).length() + 32).append("No GMSG handler found for GMSG: ").append(value2).toString());
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
    
    public final void onLoadResource(final WebView webView, final String s) {
        final String value = String.valueOf(s);
        String concat;
        if (value.length() != 0) {
            concat = "Loading resource: ".concat(value);
        }
        else {
            concat = new String("Loading resource: ");
        }
        zzakb.v(concat);
        final Uri parse = Uri.parse(s);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            this.zzi(parse);
        }
    }
    
    public final void onPageFinished(final WebView webView, final String s) {
        synchronized (this.mLock) {
            if (this.zzbnd.isDestroyed()) {
                zzakb.v("Blank page loaded, 1...");
                this.zzbnd.zzuk();
                return;
            }
            // monitorexit(this.mLock)
            this.zzdcb = true;
            if (this.zzdbs != null) {
                this.zzdbs.zzly();
                this.zzdbs = null;
            }
            this.zzvd();
        }
    }
    
    public final void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
        String value;
        if (n < 0 && -n - 1 < zzaqx.zzdbo.length) {
            value = zzaqx.zzdbo[-n - 1];
        }
        else {
            value = String.valueOf(n);
        }
        this.zzd(this.zzbnd.getContext(), "http_err", value, s2);
        super.onReceivedError(webView, n, s, s2);
    }
    
    public final void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
        if (sslError != null) {
            final int primaryError = sslError.getPrimaryError();
            String value;
            if (primaryError >= 0 && primaryError < zzaqx.zzdbp.length) {
                value = zzaqx.zzdbp[primaryError];
            }
            else {
                value = String.valueOf(primaryError);
            }
            this.zzd(this.zzbnd.getContext(), "ssl_err", value, zzbv.zzem().zza(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }
    
    public final void reset() {
        if (this.zzxd != null) {
            this.zzxd.zzpj();
            this.zzxd = null;
        }
        this.zzuy();
        synchronized (this.mLock) {
            this.zzdbq.clear();
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
    
    @TargetApi(11)
    @Nullable
    public WebResourceResponse shouldInterceptRequest(final WebView webView, final String s) {
        return this.zzd(s, Collections.emptyMap());
    }
    
    public boolean shouldOverrideKeyEvent(final WebView webView, final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            default: {
                return false;
            }
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 222: {
                return true;
            }
        }
    }
    
    public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        final String value = String.valueOf(s);
        String concat;
        if (value.length() != 0) {
            concat = "AdWebView shouldOverrideUrlLoading: ".concat(value);
        }
        else {
            concat = new String("AdWebView shouldOverrideUrlLoading: ");
        }
        zzakb.v(concat);
        final Uri parse = Uri.parse(s);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            this.zzi(parse);
        }
        else {
            if (this.zzdbu && webView == this.zzbnd.getWebView()) {
                final String scheme = parse.getScheme();
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
                            this.zzxd.zzcf(s);
                        }
                        this.zzapt = null;
                    }
                    return super.shouldOverrideUrlLoading(webView, s);
                }
            }
            if (!this.zzbnd.getWebView().willNotDraw()) {
                while (true) {
                    try {
                        final zzci zzui = this.zzbnd.zzui();
                        Uri zza = parse;
                        if (zzui != null) {
                            zza = parse;
                            if (zzui.zzb(parse)) {
                                zza = zzui.zza(parse, this.zzbnd.getContext(), this.zzbnd.getView(), this.zzbnd.zzto());
                            }
                        }
                        if (this.zzbmw == null || this.zzbmw.zzcy()) {
                            this.zza(new zzc("android.intent.action.VIEW", zza.toString(), null, null, null, null, null));
                            return true;
                        }
                    }
                    catch (zzcj zzcj) {
                        final String value2 = String.valueOf(s);
                        String concat2;
                        if (value2.length() != 0) {
                            concat2 = "Unable to append parameter to URL: ".concat(value2);
                        }
                        else {
                            concat2 = new String("Unable to append parameter to URL: ");
                        }
                        zzakb.zzdk(concat2);
                        final Uri zza = parse;
                        continue;
                    }
                    break;
                }
                this.zzbmw.zzs(s);
            }
            else {
                final String value3 = String.valueOf(s);
                String concat3;
                if (value3.length() != 0) {
                    concat3 = "AdWebView unable to handle URL: ".concat(value3);
                }
                else {
                    concat3 = new String("AdWebView unable to handle URL: ");
                }
                zzakb.zzdk(concat3);
            }
        }
        return true;
    }
    
    public final void zza(final int n, final int n2, final boolean b) {
        this.zzdbz.zzc(n, n2);
        if (this.zzbmx != null) {
            this.zzbmx.zza(n, n2, b);
        }
    }
    
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
    
    public final void zza(final zzasd zzdbr) {
        this.zzdbr = zzdbr;
    }
    
    public final void zza(final zzase zzdbs) {
        this.zzdbs = zzdbs;
    }
    
    public final void zza(final zzasf zzdbt) {
        this.zzdbt = zzdbt;
    }
    
    public final void zza(final zzasg zzdca) {
        this.zzdca = zzdca;
    }
    
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
    
    public final void zza(final String s, final zzv<? super zzaqw> zzv) {
        synchronized (this.mLock) {
            List<zzv<? super zzaqw>> list;
            if ((list = this.zzdbq.get(s)) == null) {
                list = new CopyOnWriteArrayList<zzv<? super zzaqw>>();
                this.zzdbq.put(s, list);
            }
            list.add(zzv);
        }
    }
    
    public final void zza(final String s, final Predicate<zzv<? super zzaqw>> predicate) {
        final ArrayList<zzv<? super zzaqw>> list2;
        synchronized (this.mLock) {
            final List<zzv<? super zzaqw>> list = this.zzdbq.get(s);
            if (list == null) {
                return;
            }
            list2 = new ArrayList<zzv<? super zzaqw>>();
            for (final zzv<? super zzaqw> zzv : list) {
                if (predicate.apply((Object)zzv)) {
                    list2.add(zzv);
                }
            }
        }
        final List list3;
        list3.removeAll(list2);
    }
    // monitorexit(o)
    
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
            zzn = new zzarb(this.zzbnd, this.zzbnc);
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
            zzn = new zzarb(this.zzbnd, this.zzbnc);
        }
        this.zza(new AdOverlayInfoParcel(zzapt, zzn, this.zzbll, this.zzblm, this.zzbnb, this.zzbnd, b, n, s, s2, this.zzbnd.zztq()));
    }
    
    public final void zzah(final boolean zzdbu) {
        this.zzdbu = zzdbu;
    }
    
    public final void zzb(final int n, final int n2) {
        if (this.zzbmx != null) {
            this.zzbmx.zzb(n, n2);
        }
    }
    
    public final void zzb(final String s, final zzv<? super zzaqw> zzv) {
        synchronized (this.mLock) {
            final List<zzv<? super zzaqw>> list = this.zzdbq.get(s);
            if (list == null) {
                return;
            }
            list.remove(zzv);
        }
    }
    
    @Nullable
    protected final WebResourceResponse zzd(String noClassDefFoundError, final Map<String, String> map) {
        try {
            final String zzb = zzajb.zzb((String)noClassDefFoundError, this.zzbnd.getContext());
            if (!zzb.equals(noClassDefFoundError)) {
                return this.zze(zzb, map);
            }
            final zzhl zzaa = zzhl.zzaa((String)noClassDefFoundError);
            if (zzaa == null) {
                goto Label_0090;
            }
            final zzhi zza = zzbv.zzeq().zza(zzaa);
            if (zza != null && zza.zzhi()) {
                noClassDefFoundError = (NoClassDefFoundError)new WebResourceResponse("", "", zza.zzhj());
                return (WebResourceResponse)noClassDefFoundError;
            }
            goto Label_0090;
        }
        catch (Exception ex) {}
        catch (NoClassDefFoundError noClassDefFoundError) {
            goto Label_0078;
        }
    }
    
    public final boolean zzfz() {
        synchronized (this.mLock) {
            return this.zzaek;
        }
    }
    
    public final void zznk() {
        synchronized (this.mLock) {
            this.zzdbu = false;
            this.zzaek = true;
            zzaoe.zzcvy.execute(new zzaqy(this));
        }
    }
    
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
    
    public final boolean zzux() {
        synchronized (this.mLock) {
            return this.zzdby;
        }
    }
    
    public final void zzuz() {
        final zzait zzxd = this.zzxd;
        if (zzxd != null) {
            final WebView webView = this.zzbnd.getWebView();
            if (!ViewCompat.isAttachedToWindow((View)webView)) {
                this.zzuy();
                this.zzdce = (View$OnAttachStateChangeListener)new zzara(this, zzxd);
                this.zzbnd.getView().addOnAttachStateChangeListener(this.zzdce);
                return;
            }
            this.zza((View)webView, zzxd, 10);
        }
    }
    
    public final void zzva() {
        synchronized (this.mLock) {
            this.zzdby = true;
            // monitorexit(this.mLock)
            ++this.zzdcd;
            this.zzvd();
        }
    }
    
    public final void zzvb() {
        --this.zzdcd;
        this.zzvd();
    }
    
    public final void zzvc() {
        this.zzdcc = true;
        this.zzvd();
    }
    
    public final zzasg zzve() {
        return this.zzdca;
    }
    
    public final zzait zzvf() {
        return this.zzxd;
    }
}
