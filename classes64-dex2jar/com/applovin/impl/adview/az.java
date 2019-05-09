// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.ViewParent;
import android.view.ViewGroup;
import android.os.Bundle;
import com.applovin.impl.sdk.ar;
import android.util.Log;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import com.applovin.impl.sdk.ab;
import android.util.AttributeSet;
import android.widget.ImageView$ScaleType;
import com.applovin.impl.sdk.ap;
import com.applovin.sdk.AppLovinSdk;
import android.widget.RelativeLayout$LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.AlphaAnimation;
import android.view.View$OnTouchListener;
import android.view.View$OnClickListener;
import com.applovin.adview.AppLovinTouchToClickListener;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.view.Display;
import android.content.Context;
import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.impl.sdk.ef;
import android.net.Uri;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.impl.a.o;
import java.util.concurrent.TimeUnit;
import com.applovin.impl.a.a;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.m;
import android.widget.FrameLayout;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicBoolean;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.impl.sdk.g;
import com.applovin.impl.sdk.an;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.sdk.ga;
import android.widget.ProgressBar;
import com.applovin.impl.sdk.bm;
import android.media.MediaPlayer;
import java.lang.ref.WeakReference;
import android.widget.ImageView;
import android.view.View;
import android.app.Activity;

public abstract class az extends Activity implements aq
{
    public static final String KEY_WRAPPER_ID = "com.applovin.interstitial.wrapper_id";
    public static volatile cb lastKnownWrapper;
    private ak A;
    private View B;
    private ai C;
    private ImageView D;
    private WeakReference<MediaPlayer> E;
    private bm F;
    private cn G;
    private ProgressBar H;
    private cp I;
    private ga J;
    private AppLovinAdView a;
    private cb b;
    private volatile boolean c;
    protected int computedLengthSeconds;
    protected am countdownManager;
    public volatile an currentAd;
    public String currentPlacement;
    private g d;
    private volatile boolean e;
    private volatile boolean f;
    private volatile boolean g;
    private volatile boolean h;
    private volatile boolean i;
    private volatile boolean j;
    private volatile boolean k;
    private volatile boolean l;
    public AppLovinLogger logger;
    private boolean m;
    private volatile boolean n;
    private boolean o;
    private boolean p;
    protected volatile boolean poststitialWasDisplayed;
    private boolean q;
    private long r;
    private long s;
    public AppLovinSdkImpl sdk;
    public ee settingsProxy;
    private int t;
    private AtomicBoolean u;
    private Handler v;
    protected volatile boolean videoMuted;
    public AppLovinVideoView videoView;
    private Handler w;
    private FrameLayout x;
    private ak y;
    private View z;
    
    static {
        az.lastKnownWrapper = null;
    }
    
    public az() {
        this.c = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.poststitialWasDisplayed = false;
        this.m = false;
        this.videoMuted = false;
        this.n = false;
        this.o = true;
        this.p = false;
        this.computedLengthSeconds = 0;
        this.q = false;
        this.r = 0L;
        this.s = 0L;
        this.t = 0;
        this.u = new AtomicBoolean(false);
        this.E = new WeakReference<MediaPlayer>(null);
    }
    
    private boolean A() {
        return this.E() && !this.C() && this.settingsProxy.H() && this.F != null;
    }
    
    private int B() {
        if (this.currentAd instanceof m) {
            float n = ((m)this.currentAd).h();
            if (n <= 0.0f) {
                n = this.currentAd.x();
            }
            return (int)Math.min(gd.a(System.currentTimeMillis() - this.r) / n * 100.0, 100.0);
        }
        return 0;
    }
    
    private boolean C() {
        return this.B() >= this.currentAd.S();
    }
    
    private boolean D() {
        return AppLovinAdType.INCENTIVIZED.equals(this.currentAd.getType());
    }
    
    private boolean E() {
        return !this.currentAd.f() && this.D();
    }
    
    private void F() {
        long n = 0L;
        if (this.currentAd != null && (this.currentAd.ab() >= 0L || this.currentAd.ac() >= 0) && this.J == null) {
            long ab;
            if (this.currentAd.ab() >= 0L) {
                ab = this.currentAd.ab();
            }
            else {
                long n2;
                if (this.isVastAd()) {
                    final a a = (a)this.currentAd;
                    final o a2 = a.a();
                    if (a2 != null && a2.b() > 0) {
                        n = 0L + TimeUnit.SECONDS.toMillis(a2.b());
                    }
                    else {
                        final int duration = this.videoView.getDuration();
                        if (duration > 0) {
                            n = 0L + duration;
                        }
                    }
                    n2 = n;
                    if (a.ad()) {
                        final int n3 = (int)a.x();
                        n2 = n;
                        if (n3 > 0) {
                            n2 = n + TimeUnit.SECONDS.toMillis(n3);
                        }
                    }
                }
                else {
                    n2 = n;
                    if (this.currentAd instanceof m) {
                        final m m = (m)this.currentAd;
                        final int duration2 = this.videoView.getDuration();
                        if (duration2 > 0) {
                            n = 0L + duration2;
                        }
                        n2 = n;
                        if (m.ad()) {
                            final int n4 = (int)m.h();
                            if (n4 > 0) {
                                n2 = n + TimeUnit.SECONDS.toMillis(n4);
                            }
                            else {
                                final int n5 = (int)m.x();
                                n2 = n;
                                if (n5 > 0) {
                                    n2 = n + TimeUnit.SECONDS.toMillis(n5);
                                }
                            }
                        }
                    }
                }
                ab = (long)(n2 * (this.currentAd.ac() / 100.0));
            }
            this.logger.d("InterActivity", "Scheduling report reward in " + TimeUnit.MILLISECONDS.toSeconds(ab) + " seconds...");
            this.J = ga.a(ab, this.sdk, new com.applovin.impl.adview.bm(this));
        }
    }
    
    private void G() {
        if (!this.c || this.q) {
            if (this.a == null) {
                this.exitWithError("AdView was null");
                return;
            }
            this.a.setAdDisplayListener(new bn(this));
            this.a.setAdClickListener(new bo(this));
            this.a(this.currentAd = (an)this.b.b());
            this.j();
            if (this.currentAd.isVideoAd()) {
                this.n = this.currentAd.b();
                if (this.n) {
                    this.logger.d("InterActivity", "Preparing stream for " + this.currentAd.d());
                }
                else {
                    this.logger.d("InterActivity", "Preparing cached video playback for " + this.currentAd.d());
                }
                if (this.d != null) {
                    final g d = this.d;
                    long n;
                    if (this.n) {
                        n = 1L;
                    }
                    else {
                        n = 0L;
                    }
                    d.a(n);
                }
            }
            final Uri d2 = this.currentAd.d();
            this.a(d2);
            if (d2 == null) {
                this.F();
            }
            this.y.bringToFront();
            if (this.n() && this.z != null) {
                this.z.bringToFront();
            }
            if (this.A != null) {
                this.A.bringToFront();
            }
            if (!this.currentAd.af()) {
                this.a.renderAd(this.currentAd, this.currentPlacement);
            }
            this.b.a(true);
            if (!this.currentAd.f()) {
                if (this.E() && this.settingsProxy.M()) {
                    this.d(this.currentAd);
                }
                this.showPoststitial();
            }
        }
    }
    
    private void H() {
        if (this.videoView != null) {
            this.t = this.getVideoPercentViewed();
            this.videoView.stopPlayback();
        }
    }
    
    private boolean I() {
        return this.videoMuted;
    }
    
    private void J() {
        this.sdk.put(ef.k, this.videoView.getCurrentPosition());
        this.sdk.put(ef.l, true);
        while (true) {
            try {
                this.countdownManager.c();
                this.videoView.pause();
            }
            catch (Throwable t) {
                this.logger.e("InterActivity", "Unable to pause countdown timers", t);
                continue;
            }
            break;
        }
    }
    
    private void K() {
        final long max = Math.max(0L, new ee(this.sdk).ad());
        if (max > 0L) {
            this.sdk.getLogger().d("InterActivity", "Resuming video with delay of " + max);
            this.w.postDelayed((Runnable)new bq(this), max);
            return;
        }
        this.sdk.getLogger().d("InterActivity", "Resuming video immediately");
        this.L();
    }
    
    private void L() {
        if (!this.poststitialWasDisplayed && this.videoView != null && !this.videoView.isPlaying()) {
            this.videoView.seekTo((int)this.sdk.get(ef.k, this.videoView.getDuration()));
            this.videoView.start();
            this.countdownManager.a();
        }
    }
    
    private void M() {
        if (!this.j) {
            while (true) {
                while (true) {
                    try {
                        if (!this.currentAd.f()) {
                            break;
                        }
                        final double n = this.getVideoPercentViewed();
                        final String a = this.currentAd.a((int)n, this.currentPlacement, this.n);
                        if (AppLovinSdkUtils.isValidString(a)) {
                            this.sdk.getPostbackService().dispatchPostbackAsync(a, null);
                            this.a(this.currentAd, n, this.isFullyWatched());
                            if (this.d != null) {
                                this.d.b((long)n);
                            }
                            return;
                        }
                    }
                    catch (Throwable t) {
                        if (this.logger != null) {
                            this.logger.e("InterActivity", "Failed to notify end listener.", t);
                        }
                        return;
                    }
                    this.logger.e("InterActivity", "Received invalid placement aware parameterized video completion url. No postback dispatched.");
                    continue;
                }
            }
            if (this.currentAd instanceof m && this.E() && this.settingsProxy.M()) {
                final int b = this.B();
                this.logger.d("InterActivity", "Rewarded playable engaged at " + b + " percent");
                this.a(this.currentAd, b, b >= this.currentAd.S());
            }
        }
    }
    
    private int a(final int n) {
        return AppLovinSdkUtils.dpToPx((Context)this, n);
    }
    
    private int a(final int n, final boolean b) {
        if (b) {
            if (n != 0) {
                if (n == 1) {
                    return 9;
                }
                if (n == 2) {
                    return 8;
                }
                if (n == 3) {
                    return 1;
                }
                return -1;
            }
        }
        else {
            if (n == 0) {
                return 1;
            }
            if (n != 1) {
                if (n == 2) {
                    return 9;
                }
                if (n == 3) {
                    return 8;
                }
                return -1;
            }
        }
        return 0;
    }
    
    private static int a(final Display display) {
        if (display.getWidth() == display.getHeight()) {
            return 3;
        }
        if (display.getWidth() < display.getHeight()) {
            return 1;
        }
        return 2;
    }
    
    private void a(final long n, final ak ak) {
        this.w.postDelayed((Runnable)new bf(this, ak), n);
    }
    
    private void a(final Uri videoURI) {
        this.videoView = new AppLovinVideoView((Context)this);
        if (videoURI != null) {
            this.videoView.setOnPreparedListener((MediaPlayer$OnPreparedListener)new br(this));
            this.videoView.setOnCompletionListener((MediaPlayer$OnCompletionListener)new bu(this));
            this.videoView.setOnErrorListener((MediaPlayer$OnErrorListener)new bv(this));
            this.videoView.setVideoURI(videoURI);
        }
        this.videoView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1, 17));
        this.videoView.setOnTouchListener((View$OnTouchListener)new AppLovinTouchToClickListener((Context)this, (View$OnClickListener)new bx(this)));
        this.x.addView((View)this.videoView);
        this.setContentView((View)this.x);
        this.p();
    }
    
    private void a(final View view, final boolean b, final long duration) {
        float n = 1.0f;
        float n2;
        if (b) {
            n2 = 0.0f;
        }
        else {
            n2 = 1.0f;
        }
        if (!b) {
            n = 0.0f;
        }
        final AlphaAnimation alphaAnimation = new AlphaAnimation(n2, n);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setAnimationListener((Animation$AnimationListener)new bl(this, view, b));
        view.startAnimation((Animation)alphaAnimation);
    }
    
    private void a(final an an) {
        (this.x = new FrameLayout((Context)this)).setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        this.x.setBackgroundColor(an.L());
        this.w = new Handler();
        this.v = new Handler();
        this.countdownManager = new am(this.v, this.sdk);
    }
    
    private void a(final AppLovinAd appLovinAd) {
        com.applovin.impl.sdk.bv.a(this.b.d(), appLovinAd, this.sdk);
        this.g = true;
    }
    
    private void a(final AppLovinAd appLovinAd, final double n, final boolean b) {
        this.j = true;
        com.applovin.impl.sdk.bv.a(this.b.c(), appLovinAd, n, b, this.sdk);
    }
    
    private void a(final boolean b) {
        Uri uri;
        if (b) {
            uri = this.currentAd.al();
        }
        else {
            uri = this.currentAd.am();
        }
        AppLovinSdkUtils.safePopulateImageView(this.D, uri, this.a(this.settingsProxy.s()));
    }
    
    private boolean a() {
        final int identifier = this.getResources().getIdentifier(this.settingsProxy.O(), "bool", "android");
        return identifier > 0 && this.getResources().getBoolean(identifier);
    }
    
    private void b() {
        this.getWindow().getDecorView().setSystemUiVisibility(5894);
    }
    
    private void b(int requestedOrientation, final boolean b) {
        final int n = 0;
        final int n2 = 1;
        final boolean l = this.settingsProxy.L();
        if (this.b.f() == ap.b) {
            if (b) {
                if (requestedOrientation != 1 && requestedOrientation != 3) {
                    this.c = true;
                    this.setRequestedOrientation(1);
                }
                else if (l) {
                    if (requestedOrientation == 1) {
                        this.setRequestedOrientation(9);
                        return;
                    }
                    this.setRequestedOrientation(1);
                }
            }
            else {
                if (requestedOrientation != 0 && requestedOrientation != 2) {
                    this.c = true;
                    this.setRequestedOrientation(1);
                    return;
                }
                if (l) {
                    if (requestedOrientation == 0) {
                        requestedOrientation = n2;
                    }
                    else {
                        requestedOrientation = 9;
                    }
                    this.setRequestedOrientation(requestedOrientation);
                }
            }
        }
        else if (this.b.f() == ap.c) {
            if (b) {
                if (requestedOrientation != 0 && requestedOrientation != 2) {
                    this.c = true;
                    this.setRequestedOrientation(0);
                    return;
                }
                if (l) {
                    if (requestedOrientation == 2) {
                        requestedOrientation = 8;
                    }
                    else {
                        requestedOrientation = 0;
                    }
                    this.setRequestedOrientation(requestedOrientation);
                }
            }
            else {
                if (requestedOrientation != 1 && requestedOrientation != 3) {
                    this.c = true;
                    this.setRequestedOrientation(0);
                    return;
                }
                if (l) {
                    if (requestedOrientation == 1) {
                        requestedOrientation = n;
                    }
                    else {
                        requestedOrientation = 8;
                    }
                    this.setRequestedOrientation(requestedOrientation);
                }
            }
        }
    }
    
    private void b(final AppLovinAd appLovinAd) {
        this.dismiss();
        this.c(appLovinAd);
    }
    
    private void b(final boolean videoMuted) {
        this.videoMuted = videoMuted;
        final MediaPlayer mediaPlayer = this.E.get();
        if (mediaPlayer != null) {
            boolean b;
            if (videoMuted) {
                b = false;
            }
            else {
                b = true;
            }
            mediaPlayer.setVolume((float)(b ? 1 : 0), (float)(b ? 1 : 0));
        }
    }
    
    private void c(final AppLovinAd appLovinAd) {
        if (!this.h) {
            this.h = true;
            if (this.b != null) {
                com.applovin.impl.sdk.bv.b(this.b.d(), appLovinAd, this.sdk);
            }
        }
    }
    
    private boolean c() {
        return this.b == null || this.settingsProxy == null || this.settingsProxy.a() || (this.settingsProxy.c() && this.k) || (this.settingsProxy.b() && this.poststitialWasDisplayed);
    }
    
    private void d() {
        if (this.currentAd.E() && this.currentAd.g() != null) {
            this.sdk.getLogger().d("InterActivity", "Clicking through video...");
            this.clickThroughFromVideo();
            return;
        }
        this.e();
        this.f();
    }
    
    private void d(final AppLovinAd appLovinAd) {
        if (!this.i) {
            this.i = true;
            com.applovin.impl.sdk.bv.a(this.b.c(), appLovinAd, this.sdk);
        }
    }
    
    private void e() {
        if (this.settingsProxy.p() && this.C != null && this.C.getVisibility() != 8) {
            this.a(this.C, this.C.getVisibility() == 4, 750L);
        }
    }
    
    private void f() {
        final cm d = this.currentAd.D();
        if (d != null && d.e() && !this.poststitialWasDisplayed && this.G != null) {
            this.a((View)this.G, this.G.getVisibility() == 4, d.f());
        }
    }
    
    private void g() {
        if (this.sdk != null) {
            this.sdk.put(ef.l, false);
            this.sdk.put(ef.k, 0);
        }
    }
    
    private void h() {
        this.f = true;
        this.showPoststitial();
    }
    
    private boolean i() {
        if (this.sdk.get(ef.k, 0) > 0) {
            return this.videoMuted;
        }
        if (this.settingsProxy.y()) {
            return this.sdk.getSettings().isMuted();
        }
        return this.settingsProxy.w();
    }
    
    private void j() {
        final int n = 3;
        (this.y = ak.a(this.sdk, (Context)this, this.currentAd.y())).setVisibility(8);
        this.y.setOnClickListener((View$OnClickListener)new by(this));
        final int a = this.a(this.currentAd.U());
        int n2;
        if (this.currentAd.X()) {
            n2 = 3;
        }
        else {
            n2 = 5;
        }
        final int n3 = n2 | 0x30;
        int n4;
        if (this.currentAd.Y()) {
            n4 = n;
        }
        else {
            n4 = 5;
        }
        final int n5 = n4 | 0x30;
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(a, a, n3 | 0x30);
        this.y.a(a);
        final int a2 = this.a(this.currentAd.V());
        final int a3 = this.a(this.currentAd.W());
        frameLayout$LayoutParams.setMargins(a3, a2, a3, a2);
        this.x.addView((View)this.y, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
        (this.A = ak.a(this.sdk, (Context)this, this.currentAd.z())).setVisibility(8);
        this.A.setOnClickListener((View$OnClickListener)new bz(this));
        final FrameLayout$LayoutParams frameLayout$LayoutParams2 = new FrameLayout$LayoutParams(a, a, n5);
        frameLayout$LayoutParams2.setMargins(a3, a2, a3, a2);
        this.A.a(a);
        this.x.addView((View)this.A, (ViewGroup$LayoutParams)frameLayout$LayoutParams2);
        this.A.bringToFront();
        if (this.n()) {
            final int a4 = this.a(new ee(this.sdk).k());
            (this.z = new View((Context)this)).setBackgroundColor(0);
            this.z.setVisibility(8);
            (this.B = new View((Context)this)).setBackgroundColor(0);
            this.B.setVisibility(8);
            final int n6 = a + a4;
            final int n7 = a2 - this.a(5);
            final FrameLayout$LayoutParams frameLayout$LayoutParams3 = new FrameLayout$LayoutParams(n6, n6, n3);
            frameLayout$LayoutParams3.setMargins(n7, n7, n7, n7);
            final FrameLayout$LayoutParams frameLayout$LayoutParams4 = new FrameLayout$LayoutParams(n6, n6, n5);
            frameLayout$LayoutParams4.setMargins(n7, n7, n7, n7);
            this.z.setOnClickListener((View$OnClickListener)new ca(this));
            this.B.setOnClickListener((View$OnClickListener)new bb(this));
            this.x.addView(this.z, (ViewGroup$LayoutParams)frameLayout$LayoutParams3);
            this.z.bringToFront();
            this.x.addView(this.B, (ViewGroup$LayoutParams)frameLayout$LayoutParams4);
            this.B.bringToFront();
        }
    }
    
    private void k() {
        if (this.D == null) {
            try {
                this.videoMuted = this.i();
                this.D = new ImageView((Context)this);
                if (!this.l()) {
                    final int a = this.a(this.settingsProxy.s());
                    final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(a, a, this.settingsProxy.t());
                    this.D.setScaleType(ImageView$ScaleType.FIT_CENTER);
                    final int a2 = this.a(this.settingsProxy.u());
                    frameLayout$LayoutParams.setMargins(a2, a2, a2, a2);
                    Uri uri;
                    if (this.videoMuted) {
                        uri = this.currentAd.al();
                    }
                    else {
                        uri = this.currentAd.am();
                    }
                    if (uri != null) {
                        this.sdk.getLogger().d("InterActivity", "Added mute button with params: " + frameLayout$LayoutParams);
                        this.a(this.videoMuted);
                        this.D.setClickable(true);
                        this.D.setOnClickListener((View$OnClickListener)new bc(this));
                        this.x.addView((View)this.D, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
                        this.D.bringToFront();
                        return;
                    }
                    this.sdk.getLogger().e("InterActivity", "Attempting to add mute button but could not find uri = " + uri);
                    return;
                }
            }
            catch (Exception ex) {
                this.sdk.getLogger().w("InterActivity", "Failed to attach mute button", ex);
                return;
            }
            this.sdk.getLogger().d("InterActivity", "Mute button should be hidden");
        }
    }
    
    private boolean l() {
        if (this.settingsProxy.q()) {
            if (!this.settingsProxy.r()) {
                return false;
            }
            if (this.i()) {
                return false;
            }
            if (this.settingsProxy.x()) {
                return false;
            }
        }
        return true;
    }
    
    private void m() {
        this.runOnUiThread((Runnable)new bd(this));
    }
    
    private boolean n() {
        return this.settingsProxy.k() > 0;
    }
    
    private void o() {
        this.runOnUiThread((Runnable)new be(this));
    }
    
    private void p() {
        if (this.currentAd.w() >= 0.0f) {
            ak ak;
            if (this.m && this.A != null) {
                ak = this.A;
            }
            else {
                ak = this.y;
            }
            this.a(gd.c(this.currentAd.w()), ak);
        }
    }
    
    private void q() {
        boolean b;
        if (this.settingsProxy.h() && this.t() > 0) {
            b = true;
        }
        else {
            b = false;
        }
        if (this.C == null && b) {
            this.C = new ai((Context)this);
            final int k = this.currentAd.K();
            this.C.c(k);
            this.C.b((float)this.settingsProxy.g());
            this.C.d(k);
            this.C.a((float)this.settingsProxy.f());
            this.C.b(this.t());
            this.C.a(this.t());
            final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(this.a(this.settingsProxy.e()), this.a(this.settingsProxy.e()), this.settingsProxy.o());
            final int a = this.a(this.settingsProxy.n());
            frameLayout$LayoutParams.setMargins(a, a, a, a);
            this.x.addView((View)this.C, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
            this.C.bringToFront();
            this.C.setVisibility(0);
            this.countdownManager.a("COUNTDOWN_CLOCK", 1000L, new bg(this, this.s()));
        }
    }
    
    private boolean r() {
        return !this.p && !this.poststitialWasDisplayed && this.videoView.isPlaying();
    }
    
    private long s() {
        return TimeUnit.SECONDS.toMillis(this.t());
    }
    
    private int t() {
        final int j = this.currentAd.J();
        if (j <= 0 && this.settingsProxy.m()) {
            return this.computedLengthSeconds + 1;
        }
        return j;
    }
    
    @SuppressLint({ "NewApi" })
    private void u() {
        if (this.H != null || !this.currentAd.Q()) {
            return;
        }
        this.logger.i("InterActivity", "Attaching video progress bar...");
        (this.H = new ProgressBar((Context)this, (AttributeSet)null, 16842872)).setMax(this.settingsProxy.af());
        this.H.setPadding(0, 0, 0, 0);
        while (true) {
            if (!ab.g()) {
                break Label_0097;
            }
            try {
                this.H.setProgressTintList(ColorStateList.valueOf(this.currentAd.R()));
                final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(this.videoView.getWidth(), 20, 80);
                frameLayout$LayoutParams.setMargins(0, 0, 0, this.settingsProxy.ag());
                this.x.addView((View)this.H, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
                this.H.bringToFront();
                this.countdownManager.a("PROGRESS_BAR", this.settingsProxy.ae(), new bh(this));
            }
            catch (Throwable t) {
                this.logger.e("InterActivity", "Unable to update progress bar color.", t);
                continue;
            }
            break;
        }
    }
    
    private void v() {
        final cm d = this.currentAd.D();
        if (AppLovinSdkUtils.isValidString(this.currentAd.C()) && d != null && this.G == null) {
            this.logger.i("InterActivity", "Attaching video button...");
            this.G = this.w();
            final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams((int)(d.a() / 100.0 * this.videoView.getWidth()), (int)(this.videoView.getHeight() * (d.b() / 100.0)), d.d());
            final int a = this.a(d.c());
            frameLayout$LayoutParams.setMargins(a, a, a, a);
            this.x.addView((View)this.G, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
            this.G.bringToFront();
            if (d.i() > 0.0f) {
                this.G.setVisibility(4);
                this.w.postDelayed((Runnable)new bi(this, d), gd.c(d.i()));
            }
            if (d.j() > 0.0f) {
                this.w.postDelayed((Runnable)new bj(this, d), gd.c(d.j()));
            }
        }
    }
    
    private cn w() {
        this.logger.d("InterActivity", "Create video button with HTML = " + this.currentAd.C());
        final co co = new co(this.sdk);
        this.I = new bk(this);
        co.a(new WeakReference<cp>(this.I));
        final cn cn = new cn(co, this.getApplicationContext());
        cn.a(this.currentAd.C());
        return cn;
    }
    
    private void x() {
        if (this.z()) {
            this.J();
            this.pauseReportRewardTask();
            this.logger.d("InterActivity", "Prompting incentivized ad close warning");
            this.F.b();
            return;
        }
        this.skipVideo();
    }
    
    private void y() {
        if (this.A()) {
            this.logger.d("InterActivity", "Prompting incentivized non-video ad close warning");
            this.F.c();
            return;
        }
        this.dismiss();
    }
    
    private boolean z() {
        return this.D() && !this.isFullyWatched() && this.settingsProxy.G() && this.F != null;
    }
    
    public void clickThroughFromVideo() {
        try {
            this.sdk.getAdService().trackAndLaunchVideoClick(this.currentAd, this.currentPlacement, this.a, this.currentAd.g());
            com.applovin.impl.sdk.bv.a(this.b.e(), this.currentAd, this.sdk);
            if (this.d != null) {
                this.d.b();
            }
        }
        catch (Throwable t) {
            this.sdk.getLogger().e("InterActivity", "Encountered error while clicking through video.", t);
        }
    }
    
    public void continueVideo() {
        this.L();
    }
    
    public void dismiss() {
        this.logger.i("InterActivity", "Dismissing ad after " + (System.currentTimeMillis() - this.r) + " milliseconds elapsed");
        ((AdViewControllerImpl)this.a.getAdViewController()).setIsForegroundClickInvalidated(true);
        this.g();
        this.M();
        if (this.b != null) {
            if (this.currentAd != null) {
                this.c(this.currentAd);
                if (this.d != null) {
                    this.d.c();
                    this.d = null;
                }
            }
            this.b.a(false);
            this.b.h();
        }
        this.finish();
    }
    
    public void exitWithError(final String s) {
        while (true) {
            try {
                Log.e("InterActivity", "Failed to properly render an Interstitial Activity, due to error: " + s, new Throwable("Initialized = " + cb.d + "; CleanedUp = " + cb.e));
                this.c(new ar());
                this.finish();
            }
            catch (Exception ex) {
                Log.e("InterActivity", "Failed to show a video ad due to error:", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public boolean getPoststitialWasDisplayed() {
        return this.poststitialWasDisplayed;
    }
    
    public int getVideoPercentViewed() {
        if (this.f) {
            return 100;
        }
        if (this.videoView == null) {
            this.logger.e("InterActivity", "No video view detected on video end");
            return 0;
        }
        final int duration = this.videoView.getDuration();
        if (duration > 0) {
            return (int)(this.videoView.getCurrentPosition() / (double)duration * 100.0);
        }
        return this.t;
    }
    
    public void handleMediaError() {
        if (this.u.compareAndSet(false, true)) {
            if (this.settingsProxy.j()) {
                this.logger.e("InterActivity", "Handling media player error - Finishing activity...");
                this.finish();
            }
            else {
                this.logger.e("InterActivity", "Handling media player error - Showing poststitial...");
                this.showPoststitial();
            }
            this.logger.e("InterActivity", "Finished handling media player error.");
            return;
        }
        this.logger.e("InterActivity", "Already handled media player error. Doing nothing...");
    }
    
    protected boolean isFullyWatched() {
        return this.getVideoPercentViewed() >= this.currentAd.S();
    }
    
    protected boolean isVastAd() {
        return this.currentAd instanceof a;
    }
    
    public void onBackPressed() {
        if (this.c()) {
            this.logger.d("InterActivity", "Back button was pressed; forwarding to Android for handling...");
            super.onBackPressed();
            return;
        }
        try {
            if (this.m && this.A != null && this.A.getVisibility() == 0 && this.A.getAlpha() > 0.0f && !this.k) {
                this.logger.d("InterActivity", "Back button was pressed; forwarding as a click to skip button.");
                this.A.performClick();
                return;
            }
        }
        catch (Exception ex) {
            super.onBackPressed();
            return;
        }
        if (this.y != null && this.y.getVisibility() == 0 && this.y.getAlpha() > 0.0f) {
            this.logger.d("InterActivity", "Back button was pressed; forwarding as a click to close button.");
            this.y.performClick();
            return;
        }
        this.logger.d("InterActivity", "Back button was pressed, but was not eligible for dismissal.");
    }
    
    protected void onCreate(final Bundle p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_1        
        //     2: invokespecial   android/app/Activity.onCreate:(Landroid/os/Bundle;)V
        //     5: aload_0        
        //     6: iconst_1       
        //     7: invokevirtual   com/applovin/impl/adview/az.requestWindowFeature:(I)Z
        //    10: pop            
        //    11: aload_0        
        //    12: invokevirtual   com/applovin/impl/adview/az.getIntent:()Landroid/content/Intent;
        //    15: ldc             "com.applovin.interstitial.wrapper_id"
        //    17: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    20: astore_1       
        //    21: aload_1        
        //    22: ifnull          583
        //    25: aload_1        
        //    26: invokevirtual   java/lang/String.isEmpty:()Z
        //    29: ifne            583
        //    32: aload_0        
        //    33: aload_1        
        //    34: invokestatic    com/applovin/impl/adview/cb.a:(Ljava/lang/String;)Lcom/applovin/impl/adview/cb;
        //    37: putfield        com/applovin/impl/adview/az.b:Lcom/applovin/impl/adview/cb;
        //    40: aload_0        
        //    41: getfield        com/applovin/impl/adview/az.b:Lcom/applovin/impl/adview/cb;
        //    44: ifnonnull       60
        //    47: getstatic       com/applovin/impl/adview/az.lastKnownWrapper:Lcom/applovin/impl/adview/cb;
        //    50: ifnull          60
        //    53: aload_0        
        //    54: getstatic       com/applovin/impl/adview/az.lastKnownWrapper:Lcom/applovin/impl/adview/cb;
        //    57: putfield        com/applovin/impl/adview/az.b:Lcom/applovin/impl/adview/cb;
        //    60: aload_0        
        //    61: getfield        com/applovin/impl/adview/az.b:Lcom/applovin/impl/adview/cb;
        //    64: ifnull          551
        //    67: aload_0        
        //    68: getfield        com/applovin/impl/adview/az.b:Lcom/applovin/impl/adview/cb;
        //    71: invokevirtual   com/applovin/impl/adview/cb.b:()Lcom/applovin/sdk/AppLovinAd;
        //    74: astore_1       
        //    75: aload_0        
        //    76: aload_0        
        //    77: getfield        com/applovin/impl/adview/az.b:Lcom/applovin/impl/adview/cb;
        //    80: invokevirtual   com/applovin/impl/adview/cb.a:()Lcom/applovin/sdk/AppLovinSdk;
        //    83: checkcast       Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    86: putfield        com/applovin/impl/adview/az.sdk:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    89: aload_0        
        //    90: aload_0        
        //    91: getfield        com/applovin/impl/adview/az.b:Lcom/applovin/impl/adview/cb;
        //    94: invokevirtual   com/applovin/impl/adview/cb.a:()Lcom/applovin/sdk/AppLovinSdk;
        //    97: invokevirtual   com/applovin/sdk/AppLovinSdk.getLogger:()Lcom/applovin/sdk/AppLovinLogger;
        //   100: putfield        com/applovin/impl/adview/az.logger:Lcom/applovin/sdk/AppLovinLogger;
        //   103: aload_0        
        //   104: new             Lcom/applovin/impl/sdk/ee;
        //   107: dup            
        //   108: aload_0        
        //   109: getfield        com/applovin/impl/adview/az.b:Lcom/applovin/impl/adview/cb;
        //   112: invokevirtual   com/applovin/impl/adview/cb.a:()Lcom/applovin/sdk/AppLovinSdk;
        //   115: invokespecial   com/applovin/impl/sdk/ee.<init>:(Lcom/applovin/sdk/AppLovinSdk;)V
        //   118: putfield        com/applovin/impl/adview/az.settingsProxy:Lcom/applovin/impl/sdk/ee;
        //   121: aload_0        
        //   122: aload_0        
        //   123: getfield        com/applovin/impl/adview/az.b:Lcom/applovin/impl/adview/cb;
        //   126: invokevirtual   com/applovin/impl/adview/cb.g:()Ljava/lang/String;
        //   129: putfield        com/applovin/impl/adview/az.currentPlacement:Ljava/lang/String;
        //   132: aload_0        
        //   133: new             Lcom/applovin/impl/sdk/g;
        //   136: dup            
        //   137: aload_1        
        //   138: aload_0        
        //   139: getfield        com/applovin/impl/adview/az.sdk:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //   142: invokespecial   com/applovin/impl/sdk/g.<init>:(Lcom/applovin/sdk/AppLovinAd;Lcom/applovin/sdk/AppLovinSdk;)V
        //   145: putfield        com/applovin/impl/adview/az.d:Lcom/applovin/impl/sdk/g;
        //   148: aload_1        
        //   149: ifnull          541
        //   152: aload_1        
        //   153: checkcast       Lcom/applovin/impl/sdk/an;
        //   156: astore_1       
        //   157: aload_0        
        //   158: ldc_w           16908290
        //   161: invokevirtual   com/applovin/impl/adview/az.findViewById:(I)Landroid/view/View;
        //   164: astore          5
        //   166: aload           5
        //   168: ifnull          187
        //   171: aload_1        
        //   172: invokevirtual   com/applovin/impl/sdk/an.f:()Z
        //   175: ifeq            442
        //   178: aload           5
        //   180: aload_1        
        //   181: invokevirtual   com/applovin/impl/sdk/an.L:()I
        //   184: invokevirtual   android/view/View.setBackgroundColor:(I)V
        //   187: aload_0        
        //   188: invokestatic    java/lang/System.currentTimeMillis:()J
        //   191: putfield        com/applovin/impl/adview/az.r:J
        //   194: aload_1        
        //   195: invokevirtual   com/applovin/impl/sdk/an.F:()Z
        //   198: ifeq            214
        //   201: aload_0        
        //   202: invokevirtual   com/applovin/impl/adview/az.getWindow:()Landroid/view/Window;
        //   205: ldc_w           16777216
        //   208: ldc_w           16777216
        //   211: invokevirtual   android/view/Window.setFlags:(II)V
        //   214: aload_0        
        //   215: ldc_w           "window"
        //   218: invokevirtual   com/applovin/impl/adview/az.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   221: checkcast       Landroid/view/WindowManager;
        //   224: invokeinterface android/view/WindowManager.getDefaultDisplay:()Landroid/view/Display;
        //   229: astore          5
        //   231: aload           5
        //   233: invokestatic    com/applovin/impl/adview/az.a:(Landroid/view/Display;)I
        //   236: istore_3       
        //   237: aload           5
        //   239: invokevirtual   android/view/Display.getRotation:()I
        //   242: istore_2       
        //   243: iload_3        
        //   244: iconst_2       
        //   245: if_icmpne       593
        //   248: iload_2        
        //   249: ifeq            623
        //   252: goto            593
        //   255: aload_1        
        //   256: invokevirtual   com/applovin/impl/sdk/an.I:()Z
        //   259: ifeq            517
        //   262: aload_0        
        //   263: iload_2        
        //   264: iload           4
        //   266: invokespecial   com/applovin/impl/adview/az.a:(IZ)I
        //   269: istore_3       
        //   270: iload_3        
        //   271: iconst_m1      
        //   272: if_icmpeq       493
        //   275: aload_0        
        //   276: getfield        com/applovin/impl/adview/az.logger:Lcom/applovin/sdk/AppLovinLogger;
        //   279: ldc             "InterActivity"
        //   281: new             Ljava/lang/StringBuilder;
        //   284: dup            
        //   285: invokespecial   java/lang/StringBuilder.<init>:()V
        //   288: ldc_w           "Locking activity orientation to current orientation: "
        //   291: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   294: iload_3        
        //   295: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   298: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   301: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   306: aload_0        
        //   307: iload_3        
        //   308: invokevirtual   com/applovin/impl/adview/az.setRequestedOrientation:(I)V
        //   311: aload_0        
        //   312: new             Lcom/applovin/adview/AppLovinAdView;
        //   315: dup            
        //   316: aload_0        
        //   317: getfield        com/applovin/impl/adview/az.sdk:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //   320: getstatic       com/applovin/sdk/AppLovinAdSize.INTERSTITIAL:Lcom/applovin/sdk/AppLovinAdSize;
        //   323: aload_0        
        //   324: invokespecial   com/applovin/adview/AppLovinAdView.<init>:(Lcom/applovin/sdk/AppLovinSdk;Lcom/applovin/sdk/AppLovinAdSize;Landroid/content/Context;)V
        //   327: putfield        com/applovin/impl/adview/az.a:Lcom/applovin/adview/AppLovinAdView;
        //   330: aload_0        
        //   331: getfield        com/applovin/impl/adview/az.a:Lcom/applovin/adview/AppLovinAdView;
        //   334: iconst_0       
        //   335: invokevirtual   com/applovin/adview/AppLovinAdView.setAutoDestroy:(Z)V
        //   338: aload_0        
        //   339: getfield        com/applovin/impl/adview/az.a:Lcom/applovin/adview/AppLovinAdView;
        //   342: invokevirtual   com/applovin/adview/AppLovinAdView.getAdViewController:()Lcom/applovin/adview/AdViewController;
        //   345: checkcast       Lcom/applovin/impl/adview/AdViewControllerImpl;
        //   348: aload_0        
        //   349: getfield        com/applovin/impl/adview/az.d:Lcom/applovin/impl/sdk/g;
        //   352: invokevirtual   com/applovin/impl/adview/AdViewControllerImpl.setStatsManagerHelper:(Lcom/applovin/impl/sdk/g;)V
        //   355: aload_0        
        //   356: getfield        com/applovin/impl/adview/az.b:Lcom/applovin/impl/adview/cb;
        //   359: aload_0        
        //   360: invokevirtual   com/applovin/impl/adview/cb.a:(Lcom/applovin/impl/adview/aq;)V
        //   363: aload_0        
        //   364: aload_0        
        //   365: getfield        com/applovin/impl/adview/az.settingsProxy:Lcom/applovin/impl/sdk/ee;
        //   368: invokevirtual   com/applovin/impl/sdk/ee.l:()Z
        //   371: putfield        com/applovin/impl/adview/az.m:Z
        //   374: aload_0        
        //   375: invokevirtual   com/applovin/impl/adview/az.getApplicationContext:()Landroid/content/Context;
        //   378: invokestatic    com/applovin/impl/sdk/ab.a:(Landroid/content/Context;)Z
        //   381: ifne            629
        //   384: aload_0        
        //   385: invokevirtual   com/applovin/impl/adview/az.getApplicationContext:()Landroid/content/Context;
        //   388: invokestatic    com/applovin/impl/sdk/ab.a:(Landroid/content/Context;)Z
        //   391: ifeq            635
        //   394: goto            629
        //   397: aload_0        
        //   398: iload           4
        //   400: putfield        com/applovin/impl/adview/az.q:Z
        //   403: aload_0        
        //   404: new             Lcom/applovin/impl/sdk/bm;
        //   407: dup            
        //   408: aload_0        
        //   409: aload_0        
        //   410: getfield        com/applovin/impl/adview/az.sdk:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //   413: invokespecial   com/applovin/impl/sdk/bm.<init>:(Lcom/applovin/impl/adview/az;Lcom/applovin/impl/sdk/AppLovinSdkImpl;)V
        //   416: putfield        com/applovin/impl/adview/az.F:Lcom/applovin/impl/sdk/bm;
        //   419: aload_0        
        //   420: invokespecial   com/applovin/impl/adview/az.g:()V
        //   423: aload_0        
        //   424: getfield        com/applovin/impl/adview/az.d:Lcom/applovin/impl/sdk/g;
        //   427: ifnull          437
        //   430: aload_0        
        //   431: getfield        com/applovin/impl/adview/az.d:Lcom/applovin/impl/sdk/g;
        //   434: invokevirtual   com/applovin/impl/sdk/g.a:()V
        //   437: aload_0        
        //   438: invokespecial   com/applovin/impl/adview/az.G:()V
        //   441: return         
        //   442: aload           5
        //   444: aload_1        
        //   445: invokevirtual   com/applovin/impl/sdk/an.M:()I
        //   448: invokevirtual   android/view/View.setBackgroundColor:(I)V
        //   451: goto            187
        //   454: astore_1       
        //   455: aload_0        
        //   456: getfield        com/applovin/impl/adview/az.logger:Lcom/applovin/sdk/AppLovinLogger;
        //   459: ifnull          477
        //   462: aload_0        
        //   463: getfield        com/applovin/impl/adview/az.logger:Lcom/applovin/sdk/AppLovinLogger;
        //   466: ldc             "InterActivity"
        //   468: ldc_w           "Encountered error during onCreate."
        //   471: aload_1        
        //   472: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   477: aload_0        
        //   478: ldc_w           "An error was encountered during interstitial ad creation."
        //   481: invokevirtual   com/applovin/impl/adview/az.exitWithError:(Ljava/lang/String;)V
        //   484: goto            419
        //   487: iconst_0       
        //   488: istore          4
        //   490: goto            255
        //   493: aload_0        
        //   494: getfield        com/applovin/impl/adview/az.logger:Lcom/applovin/sdk/AppLovinLogger;
        //   497: ldc             "InterActivity"
        //   499: ldc_w           "Unable to detect current orientation. Locking to targeted orientation..."
        //   502: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   507: aload_0        
        //   508: iload_2        
        //   509: iload           4
        //   511: invokespecial   com/applovin/impl/adview/az.b:(IZ)V
        //   514: goto            311
        //   517: aload_0        
        //   518: getfield        com/applovin/impl/adview/az.logger:Lcom/applovin/sdk/AppLovinLogger;
        //   521: ldc             "InterActivity"
        //   523: ldc_w           "Locking activity orientation to targeted orientation..."
        //   526: invokeinterface com/applovin/sdk/AppLovinLogger.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   531: aload_0        
        //   532: iload_2        
        //   533: iload           4
        //   535: invokespecial   com/applovin/impl/adview/az.b:(IZ)V
        //   538: goto            311
        //   541: aload_0        
        //   542: ldc_w           "No current ad found."
        //   545: invokevirtual   com/applovin/impl/adview/az.exitWithError:(Ljava/lang/String;)V
        //   548: goto            419
        //   551: aload_0        
        //   552: new             Ljava/lang/StringBuilder;
        //   555: dup            
        //   556: invokespecial   java/lang/StringBuilder.<init>:()V
        //   559: ldc_w           "Wrapper is null; initialized state: "
        //   562: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   565: getstatic       com/applovin/impl/adview/cb.d:Z
        //   568: invokestatic    java/lang/Boolean.toString:(Z)Ljava/lang/String;
        //   571: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   574: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   577: invokevirtual   com/applovin/impl/adview/az.exitWithError:(Ljava/lang/String;)V
        //   580: goto            419
        //   583: aload_0        
        //   584: ldc_w           "Wrapper ID is null"
        //   587: invokevirtual   com/applovin/impl/adview/az.exitWithError:(Ljava/lang/String;)V
        //   590: goto            419
        //   593: iload_3        
        //   594: iconst_2       
        //   595: if_icmpne       603
        //   598: iload_2        
        //   599: iconst_2       
        //   600: if_icmpeq       623
        //   603: iload_3        
        //   604: iconst_1       
        //   605: if_icmpne       613
        //   608: iload_2        
        //   609: iconst_1       
        //   610: if_icmpeq       623
        //   613: iload_3        
        //   614: iconst_1       
        //   615: if_icmpne       487
        //   618: iload_2        
        //   619: iconst_3       
        //   620: if_icmpne       487
        //   623: iconst_1       
        //   624: istore          4
        //   626: goto            255
        //   629: iconst_1       
        //   630: istore          4
        //   632: goto            397
        //   635: iconst_0       
        //   636: istore          4
        //   638: goto            397
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  11     21     454    487    Ljava/lang/Throwable;
        //  25     60     454    487    Ljava/lang/Throwable;
        //  60     148    454    487    Ljava/lang/Throwable;
        //  152    166    454    487    Ljava/lang/Throwable;
        //  171    187    454    487    Ljava/lang/Throwable;
        //  187    214    454    487    Ljava/lang/Throwable;
        //  214    243    454    487    Ljava/lang/Throwable;
        //  255    270    454    487    Ljava/lang/Throwable;
        //  275    311    454    487    Ljava/lang/Throwable;
        //  311    394    454    487    Ljava/lang/Throwable;
        //  397    419    454    487    Ljava/lang/Throwable;
        //  442    451    454    487    Ljava/lang/Throwable;
        //  493    514    454    487    Ljava/lang/Throwable;
        //  517    538    454    487    Ljava/lang/Throwable;
        //  541    548    454    487    Ljava/lang/Throwable;
        //  551    580    454    487    Ljava/lang/Throwable;
        //  583    590    454    487    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: logicaland:boolean(logicaland:boolean(logicalor:boolean(cmpne(var_3_EC:int, ldc:int(2)), cmpne:boolean(var_2_F2:int, ldc:int(2))), logicalor:boolean(cmpne(var_3_EC:int, ldc:int(1)), cmpne:boolean(var_2_F2:int, ldc:int(1)))), logicalor:boolean(cmpne:boolean(var_3_EC:int, ldc:int(1)), cmpne:boolean(var_2_F2:int, ldc:int(3))))
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.GotoRemoval.traverseGraph(GotoRemoval.java:88)
        //     at com.strobel.decompiler.ast.GotoRemoval.removeGotos(GotoRemoval.java:52)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:276)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void onDestroy() {
        while (true) {
            try {
                if (this.a != null) {
                    final ViewParent parent = this.a.getParent();
                    if (parent != null && parent instanceof ViewGroup) {
                        ((ViewGroup)parent).removeView((View)this.a);
                    }
                    this.a.destroy();
                    this.a = null;
                }
                if (this.videoView != null) {
                    this.videoView.pause();
                    this.videoView.stopPlayback();
                }
                if (this.countdownManager != null) {
                    this.countdownManager.b();
                }
                if (this.w != null) {
                    this.w.removeCallbacksAndMessages((Object)null);
                }
                if (this.v != null) {
                    this.v.removeCallbacksAndMessages((Object)null);
                }
                if (this.currentAd != null) {
                    this.M();
                    this.c(this.currentAd);
                }
                super.onDestroy();
            }
            catch (Throwable t) {
                this.logger.w("InterActivity", "Unable to destroy video view", t);
                if (this.currentAd != null) {
                    this.M();
                    this.c(this.currentAd);
                }
                continue;
            }
            finally {
                if (this.currentAd != null) {
                    this.M();
                    this.c(this.currentAd);
                }
            }
            break;
        }
    }
    
    protected void onPause() {
        this.logger.d("InterActivity", "App paused...");
        this.s = System.currentTimeMillis();
        if (!this.e) {
            if (this.q) {
                this.J();
            }
            else if (!this.c) {
                this.J();
            }
        }
        this.b.a(false);
        this.F.a();
        this.pauseReportRewardTask();
        super.onPause();
    }
    
    protected void onResume() {
        super.onResume();
        this.logger.d("InterActivity", "App resumed...");
        this.b.a(true);
        if (!this.o) {
            if (this.d != null) {
                this.d.c(System.currentTimeMillis() - this.s);
            }
            if (this.sdk.get(ef.l, false) && !this.F.d() && !this.poststitialWasDisplayed) {
                this.K();
                if (this.currentAd != null && this.settingsProxy.i() && !this.currentAd.H() && !this.poststitialWasDisplayed && this.m && this.A != null) {
                    this.a(0L, this.A);
                }
            }
            else {
                final boolean b = this.currentAd instanceof m && ((m)this.currentAd).i();
                if (this.currentAd != null && this.settingsProxy.i() && !this.currentAd.G() && this.poststitialWasDisplayed && this.y != null && !b) {
                    this.a(0L, this.y);
                }
            }
            this.resumeReportRewardTask();
        }
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        while (true) {
            Label_0151: {
                if (!b) {
                    break Label_0151;
                }
                this.logger.d("InterActivity", "Window gained focus");
                try {
                    if (ab.f() && this.settingsProxy.F() && this.a()) {
                        this.b();
                        if (this.settingsProxy.P() > 0L) {
                            this.w.postDelayed((Runnable)new ba(this), this.settingsProxy.P());
                        }
                    }
                    else {
                        this.getWindow().setFlags(1024, 1024);
                    }
                    if (this.settingsProxy.N() && !this.poststitialWasDisplayed) {
                        this.K();
                        this.resumeReportRewardTask();
                    }
                    this.o = false;
                    return;
                }
                catch (Throwable t) {
                    this.logger.e("InterActivity", "Setting window flags failed.", t);
                    continue;
                }
            }
            this.logger.d("InterActivity", "Window lost focus");
            if (this.settingsProxy.N() && !this.poststitialWasDisplayed) {
                this.J();
                this.pauseReportRewardTask();
                continue;
            }
            continue;
        }
    }
    
    public void pauseReportRewardTask() {
        if (this.J != null) {
            this.J.a();
        }
    }
    
    protected void playVideo() {
        this.d(this.currentAd);
        this.videoView.start();
        this.countdownManager.a();
    }
    
    public void resumeReportRewardTask() {
        if (this.J != null) {
            this.J.b();
        }
    }
    
    protected boolean shouldContinueFullLengthVideoCountdown() {
        return !this.f && !this.poststitialWasDisplayed;
    }
    
    public void showPoststitial() {
        while (true) {
            while (true) {
                Label_0418: {
                    while (true) {
                        Label_0401: {
                            Label_0389: {
                                try {
                                    if (!this.currentAd.Z()) {
                                        this.H();
                                    }
                                    if (this.a != null) {
                                        final ViewParent parent = this.a.getParent();
                                        if (parent instanceof ViewGroup) {
                                            ((ViewGroup)parent).removeView((View)this.a);
                                        }
                                        final FrameLayout contentView = new FrameLayout((Context)this);
                                        contentView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
                                        contentView.setBackgroundColor(this.currentAd.M());
                                        contentView.addView((View)this.a);
                                        if (this.currentAd.af()) {
                                            this.a.renderAd(this.currentAd, this.currentPlacement);
                                        }
                                        if (this.currentAd.Z()) {
                                            this.H();
                                        }
                                        if (this.x != null) {
                                            this.x.removeAllViewsInLayout();
                                        }
                                        if (this.n() && this.z != null) {
                                            contentView.addView(this.z);
                                            this.z.bringToFront();
                                        }
                                        if (this.y != null) {
                                            final ViewParent parent2 = this.y.getParent();
                                            if (parent2 instanceof ViewGroup) {
                                                ((ViewGroup)parent2).removeView((View)this.y);
                                            }
                                            contentView.addView((View)this.y);
                                            this.y.bringToFront();
                                        }
                                        this.setContentView((View)contentView);
                                        if (this.settingsProxy.J()) {
                                            this.a.setVisibility(4);
                                            this.a.setVisibility(0);
                                        }
                                        final int t = this.currentAd.T();
                                        if (t >= 0) {
                                            this.w.postDelayed((Runnable)new bp(this), (long)t);
                                        }
                                    }
                                    if (!(this.currentAd instanceof m)) {
                                        break Label_0418;
                                    }
                                    final int i = ((m)this.currentAd).i() ? 1 : 0;
                                    if (i == 0) {
                                        if (this.currentAd.x() >= 0.0f) {
                                            this.a(gd.c(this.currentAd.x()), this.y);
                                        }
                                        else {
                                            if (this.currentAd.x() != -2.0f) {
                                                break Label_0389;
                                            }
                                            this.y.setVisibility(0);
                                        }
                                        this.poststitialWasDisplayed = true;
                                        return;
                                    }
                                    break Label_0401;
                                }
                                catch (Throwable t2) {
                                    this.logger.e("InterActivity", "Encountered error while showing poststitial. Dismissing...", t2);
                                    this.dismiss();
                                    return;
                                }
                            }
                            this.a(0L, this.y);
                            continue;
                        }
                        this.logger.d("InterActivity", "Skip showing of close button");
                        continue;
                    }
                }
                final int i = 0;
                continue;
            }
        }
    }
    
    public void skipVideo() {
        if (this.d != null) {
            this.d.f();
        }
        if (this.currentAd.B()) {
            this.dismiss();
            return;
        }
        this.showPoststitial();
    }
    
    public void toggleMute() {
        Label_0020: {
            if (this.I()) {
                break Label_0020;
            }
            boolean b = true;
            try {
                while (true) {
                    this.b(b);
                    this.a(b);
                    return;
                    b = false;
                    continue;
                }
            }
            catch (Throwable t) {
                this.logger.e("InterActivity", "Unable to set volume to " + b, t);
            }
        }
    }
}
