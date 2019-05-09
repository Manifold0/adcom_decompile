// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.webkit.WebViewClient;
import org.json.JSONObject;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.ads.internal.overlay.zzc;
import android.annotation.SuppressLint;
import com.google.android.gms.ads.internal.gmsg.zzv;
import android.view.View$MeasureSpec;
import android.view.MotionEvent;
import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.content.Intent;
import android.webkit.WebView;
import android.os.Build$VERSION;
import android.app.Activity;
import android.util.DisplayMetrics;
import java.util.HashMap;
import com.google.android.gms.common.util.PlatformVersion;
import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.View;
import com.google.android.gms.ads.internal.zzw;
import java.util.Map;
import android.view.View$OnClickListener;
import java.lang.ref.WeakReference;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzbo;
import javax.annotation.concurrent.GuardedBy;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;
import android.webkit.DownloadListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

@zzadh
@ParametersAreNonnullByDefault
@VisibleForTesting
final class zzasq extends zzasv implements ViewTreeObserver$OnGlobalLayoutListener, DownloadListener, zzaqw, zzuo
{
    private zzamt zzaee;
    private final WindowManager zzaeu;
    @Nullable
    private final zzci zzbjc;
    private int zzbwy;
    private int zzbwz;
    private int zzbxb;
    private int zzbxc;
    @GuardedBy("this")
    private String zzchp;
    private zznv zzdad;
    private final zzbo zzddb;
    @GuardedBy("this")
    private zzd zzddg;
    @GuardedBy("this")
    private zzasi zzddh;
    @GuardedBy("this")
    private boolean zzddi;
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
    private Map<String, zzaqh> zzdeb;
    private zzasj zzdet;
    private float zzdeu;
    @GuardedBy("this")
    private String zzus;
    private final zzw zzwc;
    private final zzang zzyf;
    
    @VisibleForTesting
    private zzasq(final zzash zzash, final zzasi zzddh, final String zzus, final boolean zzddk, final boolean b, @Nullable final zzci zzbjc, final zzang zzyf, final zznx zznx, final zzbo zzddb, final zzw zzwc, final zzhs zzhs) {
        super(zzash);
        this.zzddn = true;
        this.zzddo = false;
        this.zzchp = "";
        this.zzbwz = -1;
        this.zzbwy = -1;
        this.zzbxb = -1;
        this.zzbxc = -1;
        this.zzddh = zzddh;
        this.zzus = zzus;
        this.zzddk = zzddk;
        this.zzddm = -1;
        this.zzbjc = zzbjc;
        this.zzyf = zzyf;
        this.zzddb = zzddb;
        this.zzwc = zzwc;
        this.zzaeu = (WindowManager)this.getContext().getSystemService("window");
        this.zzaee = new zzamt(this.zzvv().zzto(), (View)this, (ViewTreeObserver$OnGlobalLayoutListener)this, null);
        zzbv.zzek().zza((Context)zzash, zzyf.zzcw, this.getSettings());
        this.setDownloadListener((DownloadListener)this);
        this.zzdeu = this.zzvv().getResources().getDisplayMetrics().density;
        this.zzvk();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            this.addJavascriptInterface(zzaro.zzk(this), "googleAdsJsInterface");
        }
        this.zzvo();
        this.zzddx = new zznw(new zznx(true, "make_wv", this.zzus));
        this.zzddx.zzji().zzc(zznx);
        this.zzdad = zznq.zzb(this.zzddx.zzji());
        this.zzddx.zza("native:view_create", this.zzdad);
        this.zzddw = null;
        this.zzddv = null;
        zzbv.zzem().zzaw((Context)zzash);
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
        zzup.zza(this, "onAdVisibilityChanged", hashMap);
    }
    
    static zzasq zzc(final Context context, final zzasi zzasi, final String s, final boolean b, final boolean b2, @Nullable final zzci zzci, final zzang zzang, final zznx zznx, final zzbo zzbo, final zzw zzw, final zzhs zzhs) {
        return new zzasq(new zzash(context), zzasi, s, b, b2, zzci, zzang, zznx, zzbo, zzw, zzhs);
    }
    
    private final boolean zzvh() {
        if (this.zzdet.zzfz() || this.zzdet.zzuu()) {
            zzbv.zzek();
            final DisplayMetrics zza = zzakk.zza(this.zzaeu);
            zzkb.zzif();
            final int zzb = zzamu.zzb(zza, zza.widthPixels);
            zzkb.zzif();
            final int zzb2 = zzamu.zzb(zza, zza.heightPixels);
            final Activity zzto = this.zzvv().zzto();
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
                zzb4 = zzamu.zzb(zza, zzf[0]);
                zzkb.zzif();
                zzb3 = zzamu.zzb(zza, zzf[1]);
            }
            if (this.zzbwy != zzb || this.zzbwz != zzb2 || this.zzbxb != zzb4 || this.zzbxc != zzb3) {
                final boolean b = this.zzbwy != zzb || this.zzbwz != zzb2;
                this.zzbwy = zzb;
                this.zzbwz = zzb2;
                this.zzbxb = zzb4;
                this.zzbxc = zzb3;
                new zzaal(this).zza(zzb, zzb2, zzb4, zzb3, zza.density, this.zzaeu.getDefaultDisplay().getRotation());
                return b;
            }
        }
        return false;
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
                            if (this.zzdet == null || !this.zzdet.zzuu()) {
                                break Label_0140;
                            }
                            if (this.zzddr) {
                                break Label_0125;
                            }
                            if (this.zzdet.zzuv() == null) {
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
                    final ViewTreeObserver$OnScrollChangedListener zzuw = this.zzdet.zzuw();
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
            if (this.zzddr && this.zzdet != null && this.zzdet.zzuu() && this.getViewTreeObserver() != null && this.getViewTreeObserver().isAlive()) {
                final ViewTreeObserver$OnGlobalLayoutListener zzuv = this.zzdet.zzuv();
                if (zzuv != null) {
                    zzbv.zzem().zza(this.getViewTreeObserver(), zzuv);
                }
                final ViewTreeObserver$OnScrollChangedListener zzuw = this.zzdet.zzuw();
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
    @Override
    protected final void onDraw(final Canvas canvas) {
        if (Build$VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || this.isAttachedToWindow()) {
            super.onDraw(canvas);
            if (this.zzdet != null && this.zzdet.zzve() != null) {
                this.zzdet.zzve().zzda();
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
                this.zza("/contentHeight", new zzasr(this));
                this.zzbe("(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();");
                final int size3 = View$MeasureSpec.getSize(n);
                switch (this.zzddu) {
                    case -1: {
                        n = View$MeasureSpec.getSize(n2);
                        break;
                    }
                    default: {
                        n = (int)(this.zzddu * this.zzdeu);
                        break;
                    }
                }
                this.setMeasuredDimension(size3, n);
            }
            else {
                if (this.zzddh.zzvs()) {
                    final DisplayMetrics displayMetrics = new DisplayMetrics();
                    this.zzaeu.getDefaultDisplay().getMetrics(displayMetrics);
                    this.setMeasuredDimension(displayMetrics.widthPixels, displayMetrics.heightPixels);
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
                    if (this.zzddh.widthPixels / this.zzdeu <= n2 / this.zzdeu && this.zzddh.heightPixels / this.zzdeu <= n7 / this.zzdeu) {
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
                if (n2 != 0) {
                    n = (int)(this.zzddh.widthPixels / this.zzdeu);
                    n2 = (int)(this.zzddh.heightPixels / this.zzdeu);
                    zzakb.zzdk(new StringBuilder(103).append("Not enough space to show ad. Needs ").append(n).append("x").append(n2).append(" dp, but only has ").append((int)(size4 / this.zzdeu)).append("x").append((int)(size5 / this.zzdeu)).append(" dp.").toString());
                    if (this.getVisibility() != 8) {
                        this.setVisibility(4);
                    }
                    this.setMeasuredDimension(0, 0);
                    return;
                }
                if (this.getVisibility() != 8) {
                    this.setVisibility(0);
                }
                this.setMeasuredDimension(this.zzddh.widthPixels, this.zzddh.heightPixels);
            }
        }
    }
    
    @Override
    public final void onPause() {
        try {
            if (PlatformVersion.isAtLeastHoneycomb()) {
                super.onPause();
            }
        }
        catch (Exception ex) {
            zzakb.zzb("Could not pause webview.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onResume() {
        try {
            if (PlatformVersion.isAtLeastHoneycomb()) {
                super.onResume();
            }
        }
        catch (Exception ex) {
            zzakb.zzb("Could not resume webview.", (Throwable)ex);
        }
    }
    
    @Override
    public final boolean onTouchEvent(final MotionEvent motionEvent) {
        while (true) {
            Label_0045: {
                if (!this.zzdet.zzuu()) {
                    break Label_0045;
                }
                synchronized (this) {
                    if (this.zzdds != null) {
                        this.zzdds.zzc(motionEvent);
                    }
                    // monitorexit(this)
                    return super.onTouchEvent(motionEvent);
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
    
    @Override
    public final void stopLoading() {
        try {
            super.stopLoading();
        }
        catch (Exception ex) {
            zzakb.zzb("Could not stop loading webview.", (Throwable)ex);
        }
    }
    
    public final void zza(final zzc zzc) {
        this.zzdet.zza(zzc);
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
    
    public final void zza(final zzasj zzdet) {
        this.zzdet = zzdet;
    }
    
    public final void zza(final zzfs zzfs) {
        synchronized (this) {
            this.zzddq = zzfs.zztg;
            // monitorexit(this)
            this.zzal(zzfs.zztg);
        }
    }
    
    public final void zza(final String s, final zzv<? super zzaqw> zzv) {
        if (this.zzdet != null) {
            this.zzdet.zza(s, zzv);
        }
    }
    
    public final void zza(final String s, final Predicate<zzv<? super zzaqw>> predicate) {
        if (this.zzdet != null) {
            this.zzdet.zza(s, predicate);
        }
    }
    
    public final void zza(final String s, final Map map) {
        zzup.zza(this, s, map);
    }
    
    public final void zza(final String s, final JSONObject jsonObject) {
        zzup.zzb(this, s, jsonObject);
    }
    
    public final void zza(final boolean b, final int n) {
        this.zzdet.zza(b, n);
    }
    
    public final void zza(final boolean b, final int n, final String s) {
        this.zzdet.zza(b, n, s);
    }
    
    public final void zza(final boolean b, final int n, final String s, final String s2) {
        this.zzdet.zza(b, n, s, s2);
    }
    
    public final void zzah(final boolean b) {
        this.zzdet.zzah(b);
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
        zzup.zza(this, "onhide", hashMap);
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
    
    @Override
    protected final void zzam(final boolean b) {
        // monitorenter(this)
        Label_0043: {
            if (b) {
                break Label_0043;
            }
            try {
                this.zzvo();
                this.zzaee.zzsd();
                if (this.zzddg != null) {
                    this.zzddg.close();
                    this.zzddg.onDestroy();
                    this.zzddg = null;
                }
                this.zzdet.reset();
                zzbv.zzff();
                zzaqg.zzb(this);
                this.zzvn();
            }
            finally {
            }
            // monitorexit(this)
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
        if (this.zzdet != null) {
            this.zzdet.zzb(s, zzv);
        }
    }
    
    public final void zzb(final String s, final JSONObject jsonObject) {
        zzup.zza(this, s, jsonObject);
    }
    
    public final void zzbe(final String s) {
        synchronized (this) {
            if (!this.isDestroyed()) {
                super.zzbe(s);
            }
            else {
                zzakb.zzdk("The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    public final zzw zzbi() {
        return this.zzwc;
    }
    
    public final void zzbm(final Context baseContext) {
        this.zzvv().setBaseContext(baseContext);
        this.zzaee.zzi(this.zzvv().zzto());
    }
    
    public final void zzc(final String s, String zzb, @Nullable final String s2) {
        synchronized (this) {
            if (zzkb.zzik().zzd(zznk.zzaya)) {
                zzb = zzarx.zzb(zzb, zzarx.zzvp());
            }
            super.loadDataWithBaseURL(s, zzb, "text/html", "UTF-8", s2);
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
    
    public final void zzf(final String s, final String s2) {
        zzup.zza(this, s, s2);
    }
    
    public final void zzno() {
        if (this.zzddv == null) {
            zznq.zza(this.zzddx.zzji(), this.zzdad, "aes2");
            this.zzddv = zznq.zzb(this.zzddx.zzji());
            this.zzddx.zza("native:view_show", this.zzddv);
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>(1);
        hashMap.put("version", this.zzyf.zzcw);
        zzup.zza(this, "onshow", hashMap);
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
        return this.zzvv().zzto();
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
        zzup.zza(this, "onhide", hashMap);
    }
    
    public final void zztz() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(3);
        hashMap.put("app_muted", String.valueOf(zzbv.zzfj().zzdp()));
        hashMap.put("app_volume", String.valueOf(zzbv.zzfj().zzdo()));
        hashMap.put("device_volume", String.valueOf(zzalb.zzay(this.getContext())));
        zzup.zza(this, "volume", hashMap);
    }
    
    public final void zzu(final boolean zzddi) {
        synchronized (this) {
            if (this.zzddg != null) {
                this.zzddg.zza(this.zzdet.zzfz(), zzddi);
            }
            else {
                this.zzddi = zzddi;
            }
        }
    }
    
    public final Context zzua() {
        return this.zzvv().zzua();
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
        return super.zzdfb;
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
