// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONObject;
import org.json.JSONException;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.ads.internal.overlay.zzc;
import android.webkit.WebViewClient;
import android.annotation.SuppressLint;
import com.google.android.gms.ads.internal.gmsg.zzv;
import android.view.View$MeasureSpec;
import android.view.MotionEvent;
import android.graphics.Canvas;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.content.Intent;
import android.app.Activity;
import java.util.HashMap;
import android.annotation.TargetApi;
import android.webkit.ValueCallback;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.View;
import com.google.android.gms.common.util.PlatformVersion;
import android.webkit.WebSettings;
import android.os.Build$VERSION;
import com.google.android.gms.ads.internal.zzbv;
import android.content.Context;
import com.google.android.gms.ads.internal.zzw;
import java.util.Map;
import android.view.View$OnClickListener;
import java.lang.ref.WeakReference;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbo;
import javax.annotation.concurrent.GuardedBy;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;
import android.webkit.DownloadListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.webkit.WebView;

@zzadh
@ParametersAreNonnullByDefault
@VisibleForTesting
final class zzari extends WebView implements ViewTreeObserver$OnGlobalLayoutListener, DownloadListener, zzaqw
{
    private zzamt zzaee;
    private final WindowManager zzaeu;
    private final DisplayMetrics zzagj;
    @Nullable
    private final zzci zzbjc;
    private int zzbwy;
    private int zzbwz;
    private int zzbxb;
    private int zzbxc;
    private final zzhs zzcch;
    @GuardedBy("this")
    private String zzchp;
    @GuardedBy("this")
    private Boolean zzcpp;
    private zznv zzdad;
    private final zzash zzdda;
    private final zzbo zzddb;
    private final float zzddc;
    private boolean zzddd;
    private boolean zzdde;
    private zzaqx zzddf;
    @GuardedBy("this")
    private zzd zzddg;
    @GuardedBy("this")
    private zzasi zzddh;
    @GuardedBy("this")
    private boolean zzddi;
    @GuardedBy("this")
    private boolean zzddj;
    @GuardedBy("this")
    private boolean zzddk;
    @GuardedBy("this")
    private boolean zzddl;
    @GuardedBy("this")
    private int zzddm;
    @GuardedBy("this")
    private boolean zzddn;
    @GuardedBy("this")
    private boolean zzddo;
    @GuardedBy("this")
    private zzarl zzddp;
    @GuardedBy("this")
    private boolean zzddq;
    @GuardedBy("this")
    private boolean zzddr;
    @GuardedBy("this")
    private zzox zzdds;
    @GuardedBy("this")
    private int zzddt;
    @GuardedBy("this")
    private int zzddu;
    private zznv zzddv;
    private zznv zzddw;
    private zznw zzddx;
    private WeakReference<View$OnClickListener> zzddy;
    @GuardedBy("this")
    private zzd zzddz;
    @GuardedBy("this")
    private boolean zzdea;
    private Map<String, zzaqh> zzdeb;
    @GuardedBy("this")
    private String zzus;
    private final zzw zzwc;
    private final zzang zzyf;
    
    @VisibleForTesting
    private zzari(final zzash zzdda, zzasi settings, final String zzus, final boolean zzddk, final boolean b, @Nullable final zzci zzbjc, final zzang zzyf, final zznx zznx, final zzbo zzddb, final zzw zzwc, final zzhs zzcch) {
        super((Context)zzdda);
        this.zzddd = false;
        this.zzdde = false;
        this.zzddn = true;
        this.zzddo = false;
        this.zzchp = "";
        this.zzbwz = -1;
        this.zzbwy = -1;
        this.zzbxb = -1;
        this.zzbxc = -1;
        this.zzdda = zzdda;
        this.zzddh = settings;
        this.zzus = zzus;
        this.zzddk = zzddk;
        this.zzddm = -1;
        this.zzbjc = zzbjc;
        this.zzyf = zzyf;
        this.zzddb = zzddb;
        this.zzwc = zzwc;
        this.zzaeu = (WindowManager)this.getContext().getSystemService("window");
        zzbv.zzek();
        this.zzagj = zzakk.zza(this.zzaeu);
        this.zzddc = this.zzagj.density;
        this.zzcch = zzcch;
        this.setBackgroundColor(0);
        settings = (zzasi)this.getSettings();
        ((WebSettings)settings).setAllowFileAccess(false);
        while (true) {
            try {
                ((WebSettings)settings).setJavaScriptEnabled(true);
                ((WebSettings)settings).setSavePassword(false);
                ((WebSettings)settings).setSupportMultipleWindows(true);
                ((WebSettings)settings).setJavaScriptCanOpenWindowsAutomatically(true);
                if (Build$VERSION.SDK_INT >= 21) {
                    ((WebSettings)settings).setMixedContentMode(2);
                }
                zzbv.zzek().zza((Context)zzdda, zzyf.zzcw, (WebSettings)settings);
                zzbv.zzem().zza(this.getContext(), (WebSettings)settings);
                this.setDownloadListener((DownloadListener)this);
                this.zzvk();
                if (PlatformVersion.isAtLeastJellyBeanMR1()) {
                    this.addJavascriptInterface((Object)zzaro.zzk(this), "googleAdsJsInterface");
                }
                if (PlatformVersion.isAtLeastHoneycomb()) {
                    this.removeJavascriptInterface("accessibility");
                    this.removeJavascriptInterface("accessibilityTraversal");
                }
                this.zzaee = new zzamt(this.zzdda.zzto(), (View)this, (ViewTreeObserver$OnGlobalLayoutListener)this, null);
                this.zzvo();
                this.zzddx = new zznw(new zznx(true, "make_wv", this.zzus));
                this.zzddx.zzji().zzc(zznx);
                this.zzdad = zznq.zzb(this.zzddx.zzji());
                this.zzddx.zza("native:view_create", this.zzdad);
                this.zzddw = null;
                this.zzddv = null;
                zzbv.zzem().zzaw((Context)zzdda);
                zzbv.zzeo().zzqe();
            }
            catch (NullPointerException ex) {
                zzakb.zzb("Unable to enable Javascript.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    @VisibleForTesting
    private final void zza(final Boolean zzcpp) {
        synchronized (this) {
            this.zzcpp = zzcpp;
            // monitorexit(this)
            zzbv.zzeo().zza(zzcpp);
        }
    }
    
    @TargetApi(19)
    private final void zza(final String s, final ValueCallback<String> valueCallback) {
        synchronized (this) {
            if (!this.isDestroyed()) {
                this.evaluateJavascript(s, null);
            }
            else {
                zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    private final void zzal(final boolean b) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        String s;
        if (b) {
            s = "1";
        }
        else {
            s = "0";
        }
        hashMap.put("isVisible", s);
        this.zza("onAdVisibilityChanged", hashMap);
    }
    
    static zzari zzb(final Context context, final zzasi zzasi, final String s, final boolean b, final boolean b2, @Nullable final zzci zzci, final zzang zzang, final zznx zznx, final zzbo zzbo, final zzw zzw, final zzhs zzhs) {
        return new zzari(new zzash(context), zzasi, s, b, b2, zzci, zzang, zznx, zzbo, zzw, zzhs);
    }
    
    private final void zzds(final String s) {
        synchronized (this) {
            if (!this.isDestroyed()) {
                this.loadUrl(s);
            }
            else {
                zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    private final void zzdt(final String noClassDefFoundError) {
        // monitorenter(this)
        try {
            super.loadUrl((String)noClassDefFoundError);
        }
        catch (Exception ex) {}
        catch (NoClassDefFoundError noClassDefFoundError) {}
        catch (IncompatibleClassChangeError noClassDefFoundError) {}
        catch (UnsatisfiedLinkError noClassDefFoundError) {}
    }
    
    private final void zzdu(String s) {
        if (!PlatformVersion.isAtLeastKitKat()) {
            s = String.valueOf(s);
            if (s.length() != 0) {
                s = "javascript:".concat(s);
            }
            else {
                s = new String("javascript:");
            }
            this.zzds(s);
            return;
        }
        if (this.zzpz() == null) {
            this.zzvi();
        }
        if (this.zzpz()) {
            this.zza(s, (ValueCallback<String>)null);
            return;
        }
        s = String.valueOf(s);
        if (s.length() != 0) {
            s = "javascript:".concat(s);
        }
        else {
            s = new String("javascript:");
        }
        this.zzds(s);
    }
    
    @VisibleForTesting
    private final Boolean zzpz() {
        synchronized (this) {
            return this.zzcpp;
        }
    }
    
    private final void zzqf() {
        synchronized (this) {
            if (!this.zzdea) {
                this.zzdea = true;
                zzbv.zzeo().zzqf();
            }
        }
    }
    
    private final boolean zzvh() {
        if (this.zzddf.zzfz() || this.zzddf.zzuu()) {
            zzkb.zzif();
            final int zzb = zzamu.zzb(this.zzagj, this.zzagj.widthPixels);
            zzkb.zzif();
            final int zzb2 = zzamu.zzb(this.zzagj, this.zzagj.heightPixels);
            final Activity zzto = this.zzdda.zzto();
            int zzb3;
            int zzb4;
            if (zzto == null || zzto.getWindow() == null) {
                zzb3 = zzb2;
                zzb4 = zzb;
            }
            else {
                zzbv.zzek();
                final int[] zzf = zzakk.zzf(zzto);
                zzkb.zzif();
                zzb4 = zzamu.zzb(this.zzagj, zzf[0]);
                zzkb.zzif();
                zzb3 = zzamu.zzb(this.zzagj, zzf[1]);
            }
            if (this.zzbwy != zzb || this.zzbwz != zzb2 || this.zzbxb != zzb4 || this.zzbxc != zzb3) {
                final boolean b = this.zzbwy != zzb || this.zzbwz != zzb2;
                this.zzbwy = zzb;
                this.zzbwz = zzb2;
                this.zzbxb = zzb4;
                this.zzbxc = zzb3;
                new zzaal(this).zza(zzb, zzb2, zzb4, zzb3, this.zzagj.density, this.zzaeu.getDefaultDisplay().getRotation());
                return b;
            }
        }
        return false;
    }
    
    private final void zzvi() {
        synchronized (this) {
            this.zzcpp = zzbv.zzeo().zzpz();
            if (this.zzcpp != null) {
                return;
            }
            try {
                this.evaluateJavascript("(function(){})()", null);
                this.zza(true);
            }
            catch (IllegalStateException ex) {
                this.zza(false);
            }
        }
    }
    
    private final void zzvj() {
        zznq.zza(this.zzddx.zzji(), this.zzdad, "aeh2");
    }
    
    private final void zzvk() {
        while (true) {
            Label_0058: {
                synchronized (this) {
                    if (this.zzddk || this.zzddh.zzvs()) {
                        zzakb.zzck("Enabling hardware acceleration on an overlay.");
                        this.zzvm();
                    }
                    else {
                        if (Build$VERSION.SDK_INT >= 18) {
                            break Label_0058;
                        }
                        zzakb.zzck("Disabling hardware acceleration on an AdView.");
                        this.zzvl();
                    }
                    return;
                }
            }
            zzakb.zzck("Enabling hardware acceleration on an AdView.");
            this.zzvm();
        }
    }
    
    private final void zzvl() {
        synchronized (this) {
            if (!this.zzddl) {
                zzbv.zzem().zzz((View)this);
            }
            this.zzddl = true;
        }
    }
    
    private final void zzvm() {
        synchronized (this) {
            if (this.zzddl) {
                zzbv.zzem().zzy((View)this);
            }
            this.zzddl = false;
        }
    }
    
    private final void zzvn() {
        synchronized (this) {
            this.zzdeb = null;
        }
    }
    
    private final void zzvo() {
        if (this.zzddx != null) {
            final zznx zzji = this.zzddx.zzji();
            if (zzji != null && zzbv.zzeo().zzpy() != null) {
                zzbv.zzeo().zzpy().zza(zzji);
            }
        }
    }
    
    public final void destroy() {
        synchronized (this) {
            this.zzvo();
            this.zzaee.zzsd();
            if (this.zzddg != null) {
                this.zzddg.close();
                this.zzddg.onDestroy();
                this.zzddg = null;
            }
            this.zzddf.reset();
            if (!this.zzddj) {
                zzbv.zzff();
                zzaqg.zzb(this);
                this.zzvn();
                this.zzddj = true;
                zzakb.v("Initiating WebView self destruct sequence in 3...");
                zzakb.v("Loading blank page in WebView, 2...");
                this.zzdt("about:blank");
            }
        }
    }
    
    @TargetApi(19)
    public final void evaluateJavascript(final String s, final ValueCallback<String> valueCallback) {
        synchronized (this) {
            if (this.isDestroyed()) {
                zzakb.zzdm("#004 The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue((Object)null);
                }
            }
            else {
                super.evaluateJavascript(s, (ValueCallback)valueCallback);
            }
        }
    }
    
    protected final void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.zzddj) {
                    this.zzddf.reset();
                    zzbv.zzff();
                    zzaqg.zzb(this);
                    this.zzvn();
                    this.zzqf();
                }
            }
        }
        finally {
            super.finalize();
        }
    }
    
    public final View$OnClickListener getOnClickListener() {
        return this.zzddy.get();
    }
    
    public final int getRequestedOrientation() {
        synchronized (this) {
            return this.zzddm;
        }
    }
    
    public final View getView() {
        return (View)this;
    }
    
    public final WebView getWebView() {
        return this;
    }
    
    public final boolean isDestroyed() {
        synchronized (this) {
            return this.zzddj;
        }
    }
    
    public final void loadData(final String s, final String s2, final String s3) {
        synchronized (this) {
            if (!this.isDestroyed()) {
                super.loadData(s, s2, s3);
            }
            else {
                zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    public final void loadDataWithBaseURL(final String s, final String s2, final String s3, final String s4, final String s5) {
        synchronized (this) {
            if (!this.isDestroyed()) {
                super.loadDataWithBaseURL(s, s2, s3, s4, s5);
            }
            else {
                zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    public final void loadUrl(final String noClassDefFoundError) {
        // monitorenter(this)
        try {
            if (this.isDestroyed()) {
                goto Label_0045;
            }
            try {
                super.loadUrl((String)noClassDefFoundError);
            }
            catch (Exception ex) {}
            catch (NoClassDefFoundError noClassDefFoundError) {}
            catch (IncompatibleClassChangeError noClassDefFoundError) {}
        }
        finally {}
    }
    
    protected final void onAttachedToWindow() {
        // monitorexit(this)
        while (true) {
            Label_0140: {
                Label_0125: {
                    Label_0090: {
                        synchronized (this) {
                            super.onAttachedToWindow();
                            if (!this.isDestroyed()) {
                                this.zzaee.onAttachedToWindow();
                            }
                            final boolean zzddq = this.zzddq;
                            if (this.zzddf == null || !this.zzddf.zzuu()) {
                                break Label_0140;
                            }
                            if (this.zzddr) {
                                break Label_0125;
                            }
                            if (this.zzddf.zzuv() == null) {
                                break Label_0090;
                            }
                            zzbv.zzfg();
                            if (this == null) {
                                throw null;
                            }
                        }
                        final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener;
                        zzaor.zza((View)this, viewTreeObserver$OnGlobalLayoutListener);
                    }
                    final ViewTreeObserver$OnScrollChangedListener zzuw = this.zzddf.zzuw();
                    if (zzuw != null) {
                        zzbv.zzfg();
                        if (this == null) {
                            throw null;
                        }
                        zzaor.zza((View)this, zzuw);
                    }
                    this.zzddr = true;
                }
                this.zzvh();
                final boolean zzddq = true;
                this.zzal(zzddq);
                return;
            }
            continue;
        }
    }
    
    protected final void onDetachedFromWindow() {
        synchronized (this) {
            if (!this.isDestroyed()) {
                this.zzaee.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzddr && this.zzddf != null && this.zzddf.zzuu() && this.getViewTreeObserver() != null && this.getViewTreeObserver().isAlive()) {
                final ViewTreeObserver$OnGlobalLayoutListener zzuv = this.zzddf.zzuv();
                if (zzuv != null) {
                    zzbv.zzem().zza(this.getViewTreeObserver(), zzuv);
                }
                final ViewTreeObserver$OnScrollChangedListener zzuw = this.zzddf.zzuw();
                if (zzuw != null) {
                    this.getViewTreeObserver().removeOnScrollChangedListener(zzuw);
                }
                this.zzddr = false;
            }
            // monitorexit(this)
            this.zzal(false);
        }
    }
    
    public final void onDownloadStart(final String s, final String s2, final String s3, final String s4, final long n) {
        try {
            final Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(s), s4);
            zzbv.zzek();
            zzakk.zza(this.getContext(), intent);
        }
        catch (ActivityNotFoundException ex) {
            zzakb.zzck(new StringBuilder(String.valueOf(s).length() + 51 + String.valueOf(s4).length()).append("Couldn't find an Activity to view url/mimetype: ").append(s).append(" / ").append(s4).toString());
        }
    }
    
    @TargetApi(21)
    protected final void onDraw(final Canvas canvas) {
        if (!this.isDestroyed() && (Build$VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || this.isAttachedToWindow())) {
            super.onDraw(canvas);
            if (this.zzddf != null && this.zzddf.zzve() != null) {
                this.zzddf.zzve().zzda();
            }
        }
    }
    
    public final boolean onGenericMotionEvent(final MotionEvent motionEvent) {
        if (zzkb.zzik().zzd(zznk.zzaxx)) {
            final float axisValue = motionEvent.getAxisValue(9);
            final float axisValue2 = motionEvent.getAxisValue(10);
            if (motionEvent.getActionMasked() == 8 && ((axisValue > 0.0f && !this.canScrollVertically(-1)) || (axisValue < 0.0f && !this.canScrollVertically(1)) || (axisValue2 > 0.0f && !this.canScrollHorizontally(-1)) || (axisValue2 < 0.0f && !this.canScrollHorizontally(1)))) {
                return false;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }
    
    public final void onGlobalLayout() {
        final boolean zzvh = this.zzvh();
        final zzd zzub = this.zzub();
        if (zzub != null && zzvh) {
            zzub.zznn();
        }
    }
    
    @SuppressLint({ "DrawAllocation" })
    protected final void onMeasure(int n, int n2) {
        while (true) {
            Label_0058: {
                synchronized (this) {
                    if (this.isDestroyed()) {
                        this.setMeasuredDimension(0, 0);
                    }
                    else {
                        if (!this.isInEditMode() && !this.zzddk && !this.zzddh.zzvt()) {
                            break Label_0058;
                        }
                        super.onMeasure(n, n2);
                    }
                    return;
                }
            }
            if (this.zzddh.zzvu()) {
                final zzarl zztm = this.zztm();
                float aspectRatio;
                if (zztm != null) {
                    aspectRatio = zztm.getAspectRatio();
                }
                else {
                    aspectRatio = 0.0f;
                }
                if (aspectRatio == 0.0f) {
                    super.onMeasure(n, n2);
                    return;
                }
                final int size = View$MeasureSpec.getSize(n);
                final int size2 = View$MeasureSpec.getSize(n2);
                n = (int)(size2 * aspectRatio);
                n2 = (int)(size / aspectRatio);
                int n3;
                int n4;
                int n5;
                int n6;
                if (size2 == 0 && n2 != 0) {
                    n3 = (int)(n2 * aspectRatio);
                    n4 = n2;
                    n5 = size;
                    n6 = n2;
                }
                else {
                    n6 = n2;
                    n3 = n;
                    n4 = size2;
                    if ((n5 = size) == 0) {
                        n6 = n2;
                        n3 = n;
                        n4 = size2;
                        n5 = size;
                        if (n != 0) {
                            n6 = (int)(n / aspectRatio);
                            n5 = n;
                            n3 = n;
                            n4 = size2;
                        }
                    }
                }
                this.setMeasuredDimension(Math.min(n3, n5), Math.min(n6, n4));
            }
            else if (this.zzddh.isFluid()) {
                if ((boolean)zzkb.zzik().zzd(zznk.zzbch) || !PlatformVersion.isAtLeastJellyBeanMR1()) {
                    super.onMeasure(n, n2);
                    return;
                }
                this.zza("/contentHeight", new zzarj(this));
                this.zzdu("(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();");
                final float density = this.zzagj.density;
                final int size3 = View$MeasureSpec.getSize(n);
                switch (this.zzddu) {
                    case -1: {
                        n = View$MeasureSpec.getSize(n2);
                        break;
                    }
                    default: {
                        n = (int)(density * this.zzddu);
                        break;
                    }
                }
                this.setMeasuredDimension(size3, n);
            }
            else {
                if (this.zzddh.zzvs()) {
                    this.setMeasuredDimension(this.zzagj.widthPixels, this.zzagj.heightPixels);
                    return;
                }
                final int mode = View$MeasureSpec.getMode(n);
                final int size4 = View$MeasureSpec.getSize(n);
                n = View$MeasureSpec.getMode(n2);
                final int size5 = View$MeasureSpec.getSize(n2);
                if (mode != Integer.MIN_VALUE && mode != 1073741824) {
                    n2 = Integer.MAX_VALUE;
                }
                else {
                    n2 = size4;
                }
                int n7;
                if (n == Integer.MIN_VALUE || n == 1073741824) {
                    n7 = size5;
                }
                else {
                    n7 = Integer.MAX_VALUE;
                }
                if (this.zzddh.widthPixels <= n2 && this.zzddh.heightPixels <= n7) {
                    n = 0;
                }
                else {
                    n = 1;
                }
                if (zzkb.zzik().zzd(zznk.zzbfe)) {
                    if (this.zzddh.widthPixels / this.zzddc <= n2 / this.zzddc && this.zzddh.heightPixels / this.zzddc <= n7 / this.zzddc) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    if (n == 0) {
                        n2 = n;
                    }
                }
                else {
                    n2 = n;
                }
                if (n2 == 0) {
                    if (this.getVisibility() != 8) {
                        this.setVisibility(0);
                    }
                    if (!this.zzdde) {
                        this.zzcch.zza(zzhu.zza.zzb.zzalk);
                        this.zzdde = true;
                    }
                    this.setMeasuredDimension(this.zzddh.widthPixels, this.zzddh.heightPixels);
                    return;
                }
                n = (int)(this.zzddh.widthPixels / this.zzddc);
                n2 = (int)(this.zzddh.heightPixels / this.zzddc);
                zzakb.zzdk(new StringBuilder(103).append("Not enough space to show ad. Needs ").append(n).append("x").append(n2).append(" dp, but only has ").append((int)(size4 / this.zzddc)).append("x").append((int)(size5 / this.zzddc)).append(" dp.").toString());
                if (this.getVisibility() != 8) {
                    this.setVisibility(4);
                }
                this.setMeasuredDimension(0, 0);
                if (!this.zzddd) {
                    this.zzcch.zza(zzhu.zza.zzb.zzalj);
                    this.zzddd = true;
                }
            }
        }
    }
    
    public final void onPause() {
        if (!this.isDestroyed()) {
            try {
                if (PlatformVersion.isAtLeastHoneycomb()) {
                    super.onPause();
                }
            }
            catch (Exception ex) {
                zzakb.zzb("Could not pause webview.", (Throwable)ex);
            }
        }
    }
    
    public final void onResume() {
        if (!this.isDestroyed()) {
            try {
                if (PlatformVersion.isAtLeastHoneycomb()) {
                    super.onResume();
                }
            }
            catch (Exception ex) {
                zzakb.zzb("Could not resume webview.", (Throwable)ex);
            }
        }
    }
    
    public final boolean onTouchEvent(final MotionEvent motionEvent) {
        while (true) {
            Label_0048: {
                if (!this.zzddf.zzuu()) {
                    break Label_0048;
                }
                synchronized (this) {
                    if (this.zzdds != null) {
                        this.zzdds.zzc(motionEvent);
                    }
                    // monitorexit(this)
                    if (this.isDestroyed()) {
                        return false;
                    }
                    final MotionEvent motionEvent2;
                    return super.onTouchEvent(motionEvent2);
                }
            }
            if (this.zzbjc != null) {
                this.zzbjc.zza(motionEvent);
                continue;
            }
            continue;
        }
    }
    
    public final void setOnClickListener(final View$OnClickListener onClickListener) {
        this.zzddy = new WeakReference<View$OnClickListener>(onClickListener);
        super.setOnClickListener(onClickListener);
    }
    
    public final void setRequestedOrientation(final int zzddm) {
        synchronized (this) {
            this.zzddm = zzddm;
            if (this.zzddg != null) {
                this.zzddg.setRequestedOrientation(this.zzddm);
            }
        }
    }
    
    public final void setWebViewClient(final WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzaqx) {
            this.zzddf = (zzaqx)webViewClient;
        }
    }
    
    public final void stopLoading() {
        if (this.isDestroyed()) {
            return;
        }
        try {
            super.stopLoading();
        }
        catch (Exception ex) {
            zzakb.zzb("Could not stop loading webview.", (Throwable)ex);
        }
    }
    
    public final void zza(final zzc zzc) {
        this.zzddf.zza(zzc);
    }
    
    public final void zza(final zzd zzddg) {
        synchronized (this) {
            this.zzddg = zzddg;
        }
    }
    
    public final void zza(final zzarl zzddp) {
        synchronized (this) {
            if (this.zzddp != null) {
                zzakb.e("Attempt to create multiple AdWebViewVideoControllers.");
            }
            else {
                this.zzddp = zzddp;
            }
        }
    }
    
    public final void zza(final zzasi zzddh) {
        synchronized (this) {
            this.zzddh = zzddh;
            this.requestLayout();
        }
    }
    
    public final void zza(final zzfs zzfs) {
        synchronized (this) {
            this.zzddq = zzfs.zztg;
            // monitorexit(this)
            this.zzal(zzfs.zztg);
        }
    }
    
    public final void zza(final String s, final zzv<? super zzaqw> zzv) {
        if (this.zzddf != null) {
            this.zzddf.zza(s, zzv);
        }
    }
    
    public final void zza(final String s, final Predicate<zzv<? super zzaqw>> predicate) {
        if (this.zzddf != null) {
            this.zzddf.zza(s, predicate);
        }
    }
    
    public final void zza(final String s, final Map<String, ?> map) {
        try {
            this.zza(s, zzbv.zzek().zzn(map));
        }
        catch (JSONException ex) {
            zzakb.zzdk("Could not convert parameters to JSON.");
        }
    }
    
    public final void zza(String s, final JSONObject jsonObject) {
        JSONObject jsonObject2 = jsonObject;
        if (jsonObject == null) {
            jsonObject2 = new JSONObject();
        }
        final String string = jsonObject2.toString();
        final StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(s);
        sb.append("'");
        sb.append(",");
        sb.append(string);
        sb.append(");");
        s = String.valueOf(sb.toString());
        if (s.length() != 0) {
            s = "Dispatching AFMA event: ".concat(s);
        }
        else {
            s = new String("Dispatching AFMA event: ");
        }
        zzakb.zzck(s);
        this.zzdu(sb.toString());
    }
    
    public final void zza(final boolean b, final int n) {
        this.zzddf.zza(b, n);
    }
    
    public final void zza(final boolean b, final int n, final String s) {
        this.zzddf.zza(b, n, s);
    }
    
    public final void zza(final boolean b, final int n, final String s, final String s2) {
        this.zzddf.zza(b, n, s, s2);
    }
    
    public final void zzah(final boolean b) {
        this.zzddf.zzah(b);
    }
    
    public final void zzai(final int n) {
        if (n == 0) {
            zznq.zza(this.zzddx.zzji(), this.zzdad, "aebb2");
        }
        this.zzvj();
        if (this.zzddx.zzji() != null) {
            this.zzddx.zzji().zze("close_type", String.valueOf(n));
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>(2);
        hashMap.put("closetype", String.valueOf(n));
        hashMap.put("version", this.zzyf.zzcw);
        this.zza("onhide", hashMap);
    }
    
    public final void zzai(final boolean zzddk) {
        synchronized (this) {
            int n;
            if (zzddk != this.zzddk) {
                n = 1;
            }
            else {
                n = 0;
            }
            this.zzddk = zzddk;
            this.zzvk();
            if (n != 0) {
                final zzaal zzaal = new zzaal(this);
                String s;
                if (zzddk) {
                    s = "expanded";
                }
                else {
                    s = "default";
                }
                zzaal.zzby(s);
            }
        }
    }
    
    public final void zzaj(final boolean zzddn) {
        synchronized (this) {
            this.zzddn = zzddn;
        }
    }
    
    public final void zzak(final boolean b) {
        synchronized (this) {
            final int zzddt = this.zzddt;
            int n;
            if (b) {
                n = 1;
            }
            else {
                n = -1;
            }
            this.zzddt = n + zzddt;
            if (this.zzddt <= 0 && this.zzddg != null) {
                this.zzddg.zznq();
            }
        }
    }
    
    public final void zzb(final zzd zzddz) {
        synchronized (this) {
            this.zzddz = zzddz;
        }
    }
    
    public final void zzb(final zzox zzdds) {
        synchronized (this) {
            this.zzdds = zzdds;
        }
    }
    
    public final void zzb(final String s, final zzv<? super zzaqw> zzv) {
        if (this.zzddf != null) {
            this.zzddf.zzb(s, zzv);
        }
    }
    
    public final void zzb(final String s, final JSONObject jsonObject) {
        JSONObject jsonObject2 = jsonObject;
        if (jsonObject == null) {
            jsonObject2 = new JSONObject();
        }
        final String string = jsonObject2.toString();
        this.zzdu(new StringBuilder(String.valueOf(s).length() + 3 + String.valueOf(string).length()).append(s).append("(").append(string).append(");").toString());
    }
    
    public final void zzbe(final String s) {
        this.zzdu(s);
    }
    
    public final zzw zzbi() {
        return this.zzwc;
    }
    
    public final void zzbm(final Context baseContext) {
        this.zzdda.setBaseContext(baseContext);
        this.zzaee.zzi(this.zzdda.zzto());
    }
    
    public final void zzc(final String s, String zzb, @Nullable final String s2) {
        synchronized (this) {
            if (!this.isDestroyed()) {
                if (zzkb.zzik().zzd(zznk.zzaya)) {
                    zzb = zzarx.zzb(zzb, zzarx.zzvp());
                }
                super.loadDataWithBaseURL(s, zzb, "text/html", "UTF-8", s2);
            }
            else {
                zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    public final void zzcl() {
        synchronized (this) {
            this.zzddo = true;
            if (this.zzddb != null) {
                this.zzddb.zzcl();
            }
        }
    }
    
    public final void zzcm() {
        synchronized (this) {
            this.zzddo = false;
            if (this.zzddb != null) {
                this.zzddb.zzcm();
            }
        }
    }
    
    public final void zzdr(final String s) {
        // monitorenter(this)
        String zzchp = s;
        if (s == null) {
            zzchp = "";
        }
        try {
            this.zzchp = zzchp;
        }
        finally {
        }
        // monitorexit(this)
    }
    
    public final void zzno() {
        if (this.zzddv == null) {
            zznq.zza(this.zzddx.zzji(), this.zzdad, "aes2");
            this.zzddv = zznq.zzb(this.zzddx.zzji());
            this.zzddx.zza("native:view_show", this.zzddv);
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.zzyf.zzcw);
        this.zza("onshow", hashMap);
    }
    
    public final void zznp() {
        final zzd zzub = this.zzub();
        if (zzub != null) {
            zzub.zznp();
        }
    }
    
    public final String zzol() {
        synchronized (this) {
            return this.zzchp;
        }
    }
    
    public final zzapn zztl() {
        return null;
    }
    
    public final zzarl zztm() {
        synchronized (this) {
            return this.zzddp;
        }
    }
    
    public final zznv zztn() {
        return this.zzdad;
    }
    
    public final Activity zzto() {
        return this.zzdda.zzto();
    }
    
    public final zznw zztp() {
        return this.zzddx;
    }
    
    public final zzang zztq() {
        return this.zzyf;
    }
    
    public final int zztr() {
        return this.getMeasuredHeight();
    }
    
    public final int zzts() {
        return this.getMeasuredWidth();
    }
    
    public final void zzty() {
        this.zzvj();
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.zzyf.zzcw);
        this.zza("onhide", hashMap);
    }
    
    public final void zztz() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(3);
        hashMap.put("app_muted", String.valueOf(zzbv.zzfj().zzdp()));
        hashMap.put("app_volume", String.valueOf(zzbv.zzfj().zzdo()));
        hashMap.put("device_volume", String.valueOf(zzalb.zzay(this.getContext())));
        this.zza("volume", hashMap);
    }
    
    public final void zzu(final boolean zzddi) {
        synchronized (this) {
            if (this.zzddg != null) {
                this.zzddg.zza(this.zzddf.zzfz(), zzddi);
            }
            else {
                this.zzddi = zzddi;
            }
        }
    }
    
    public final Context zzua() {
        return this.zzdda.zzua();
    }
    
    public final zzd zzub() {
        synchronized (this) {
            return this.zzddg;
        }
    }
    
    public final zzd zzuc() {
        synchronized (this) {
            return this.zzddz;
        }
    }
    
    public final zzasi zzud() {
        synchronized (this) {
            return this.zzddh;
        }
    }
    
    public final String zzue() {
        synchronized (this) {
            return this.zzus;
        }
    }
    
    public final WebViewClient zzug() {
        return this.zzddf;
    }
    
    public final boolean zzuh() {
        synchronized (this) {
            return this.zzddi;
        }
    }
    
    public final zzci zzui() {
        return this.zzbjc;
    }
    
    public final boolean zzuj() {
        synchronized (this) {
            return this.zzddk;
        }
    }
    
    public final void zzuk() {
        synchronized (this) {
            zzakb.v("Destroying WebView!");
            this.zzqf();
            zzakk.zzcrm.post((Runnable)new zzark(this));
        }
    }
    
    public final boolean zzul() {
        synchronized (this) {
            return this.zzddn;
        }
    }
    
    public final boolean zzum() {
        synchronized (this) {
            return this.zzddo;
        }
    }
    
    public final boolean zzun() {
        synchronized (this) {
            return this.zzddt > 0;
        }
    }
    
    public final void zzuo() {
        this.zzaee.zzsc();
    }
    
    public final void zzup() {
        if (this.zzddw == null) {
            this.zzddw = zznq.zzb(this.zzddx.zzji());
            this.zzddx.zza("native:view_load", this.zzddw);
        }
    }
    
    public final zzox zzuq() {
        synchronized (this) {
            return this.zzdds;
        }
    }
    
    public final void zzur() {
        this.setBackgroundColor(0);
    }
    
    public final void zzus() {
        zzakb.v("Cannot add text view to inner AdWebView");
    }
}
