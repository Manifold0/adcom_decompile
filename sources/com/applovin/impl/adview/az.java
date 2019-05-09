package com.applovin.impl.adview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinTouchToClickListener;
import com.applovin.impl.p016a.C1228a;
import com.applovin.impl.p016a.C1242o;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.C1280g;
import com.applovin.impl.sdk.C1286m;
import com.applovin.impl.sdk.ab;
import com.applovin.impl.sdk.an;
import com.applovin.impl.sdk.ap;
import com.applovin.impl.sdk.ar;
import com.applovin.impl.sdk.bm;
import com.applovin.impl.sdk.bv;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.ef;
import com.applovin.impl.sdk.ga;
import com.applovin.impl.sdk.gd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import com.tapjoy.TapjoyConstants;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class az extends Activity implements aq {
    public static final String KEY_WRAPPER_ID = "com.applovin.interstitial.wrapper_id";
    public static volatile cb lastKnownWrapper = null;
    /* renamed from: A */
    private ak f1521A;
    /* renamed from: B */
    private View f1522B;
    /* renamed from: C */
    private ai f1523C;
    /* renamed from: D */
    private ImageView f1524D;
    /* renamed from: E */
    private WeakReference<MediaPlayer> f1525E = new WeakReference(null);
    /* renamed from: F */
    private bm f1526F;
    /* renamed from: G */
    private cn f1527G;
    /* renamed from: H */
    private ProgressBar f1528H;
    /* renamed from: I */
    private cp f1529I;
    /* renamed from: J */
    private ga f1530J;
    /* renamed from: a */
    private AppLovinAdView f1531a;
    /* renamed from: b */
    private cb f1532b;
    /* renamed from: c */
    private volatile boolean f1533c = false;
    protected int computedLengthSeconds = 0;
    protected am countdownManager;
    public volatile an currentAd;
    public String currentPlacement;
    /* renamed from: d */
    private C1280g f1534d;
    /* renamed from: e */
    private volatile boolean f1535e = false;
    /* renamed from: f */
    private volatile boolean f1536f = false;
    /* renamed from: g */
    private volatile boolean f1537g = false;
    /* renamed from: h */
    private volatile boolean f1538h = false;
    /* renamed from: i */
    private volatile boolean f1539i = false;
    /* renamed from: j */
    private volatile boolean f1540j = false;
    /* renamed from: k */
    private volatile boolean f1541k = false;
    /* renamed from: l */
    private volatile boolean f1542l = false;
    public AppLovinLogger logger;
    /* renamed from: m */
    private boolean f1543m = false;
    /* renamed from: n */
    private volatile boolean f1544n = false;
    /* renamed from: o */
    private boolean f1545o = true;
    /* renamed from: p */
    private boolean f1546p = false;
    protected volatile boolean poststitialWasDisplayed = false;
    /* renamed from: q */
    private boolean f1547q = false;
    /* renamed from: r */
    private long f1548r = 0;
    /* renamed from: s */
    private long f1549s = 0;
    public AppLovinSdkImpl sdk;
    public ee settingsProxy;
    /* renamed from: t */
    private int f1550t = 0;
    /* renamed from: u */
    private AtomicBoolean f1551u = new AtomicBoolean(false);
    /* renamed from: v */
    private Handler f1552v;
    protected volatile boolean videoMuted = false;
    public AppLovinVideoView videoView;
    /* renamed from: w */
    private Handler f1553w;
    /* renamed from: x */
    private FrameLayout f1554x;
    /* renamed from: y */
    private ak f1555y;
    /* renamed from: z */
    private View f1556z;

    /* renamed from: A */
    private boolean m1671A() {
        return m1679E() && !m1676C() && this.settingsProxy.m2681H() && this.f1526F != null;
    }

    /* renamed from: B */
    private int m1673B() {
        if (!(this.currentAd instanceof C1286m)) {
            return 0;
        }
        float h = ((C1286m) this.currentAd).m3030h();
        if (h <= 0.0f) {
            h = this.currentAd.m1829x();
        }
        return (int) Math.min((gd.m2928a(System.currentTimeMillis() - this.f1548r) / ((double) h)) * 100.0d, 100.0d);
    }

    /* renamed from: C */
    private boolean m1676C() {
        return m1673B() >= this.currentAd.m1806S();
    }

    /* renamed from: D */
    private boolean m1678D() {
        return AppLovinAdType.INCENTIVIZED.equals(this.currentAd.getType());
    }

    /* renamed from: E */
    private boolean m1679E() {
        return !this.currentAd.mo3992f() && m1678D();
    }

    /* renamed from: F */
    private void m1680F() {
        long j = 0;
        if (this.currentAd == null) {
            return;
        }
        if ((this.currentAd.ab() >= 0 || this.currentAd.ac() >= 0) && this.f1530J == null) {
            long ab;
            if (this.currentAd.ab() >= 0) {
                ab = this.currentAd.ab();
            } else {
                int duration;
                int x;
                if (isVastAd()) {
                    C1228a c1228a = (C1228a) this.currentAd;
                    C1242o a = c1228a.mo4000a();
                    if (a == null || a.m1926b() <= 0) {
                        duration = this.videoView.getDuration();
                        if (duration > 0) {
                            j = 0 + ((long) duration);
                        }
                    } else {
                        j = 0 + TimeUnit.SECONDS.toMillis((long) a.m1926b());
                    }
                    if (c1228a.ad()) {
                        x = (int) c1228a.m1829x();
                        if (x > 0) {
                            j += TimeUnit.SECONDS.toMillis((long) x);
                        }
                    }
                } else if (this.currentAd instanceof C1286m) {
                    C1286m c1286m = (C1286m) this.currentAd;
                    duration = this.videoView.getDuration();
                    if (duration > 0) {
                        j = 0 + ((long) duration);
                    }
                    if (c1286m.ad()) {
                        duration = (int) c1286m.m3030h();
                        if (duration > 0) {
                            j += TimeUnit.SECONDS.toMillis((long) duration);
                        } else {
                            x = (int) c1286m.m1829x();
                            if (x > 0) {
                                j += TimeUnit.SECONDS.toMillis((long) x);
                            }
                        }
                    }
                }
                ab = (long) (((double) j) * (((double) this.currentAd.ac()) / 100.0d));
            }
            this.logger.mo4172d("InterActivity", "Scheduling report reward in " + TimeUnit.MILLISECONDS.toSeconds(ab) + " seconds...");
            this.f1530J = ga.m2918a(ab, this.sdk, new bm(this));
        }
    }

    /* renamed from: G */
    private void m1681G() {
        if (this.f1533c && !this.f1547q) {
            return;
        }
        if (this.f1531a != null) {
            this.f1531a.setAdDisplayListener(new bn(this));
            this.f1531a.setAdClickListener(new bo(this));
            this.currentAd = (an) this.f1532b.m2065b();
            m1698a(this.currentAd);
            m1728j();
            if (this.currentAd.isVideoAd()) {
                this.f1544n = this.currentAd.mo4001b();
                if (this.f1544n) {
                    this.logger.mo4172d("InterActivity", "Preparing stream for " + this.currentAd.mo4002d());
                } else {
                    this.logger.mo4172d("InterActivity", "Preparing cached video playback for " + this.currentAd.mo4002d());
                }
                if (this.f1534d != null) {
                    this.f1534d.m2910a(this.f1544n ? 1 : 0);
                }
            }
            Uri d = this.currentAd.mo4002d();
            m1693a(d);
            if (d == null) {
                m1680F();
            }
            this.f1555y.bringToFront();
            if (m1737n() && this.f1556z != null) {
                this.f1556z.bringToFront();
            }
            if (this.f1521A != null) {
                this.f1521A.bringToFront();
            }
            if (!this.currentAd.af()) {
                this.f1531a.renderAd(this.currentAd, this.currentPlacement);
            }
            this.f1532b.m2064a(true);
            if (!this.currentAd.mo3992f()) {
                if (m1679E() && this.settingsProxy.m2686M()) {
                    m1717d(this.currentAd);
                }
                showPoststitial();
                return;
            }
            return;
        }
        exitWithError("AdView was null");
    }

    /* renamed from: H */
    private void m1682H() {
        if (this.videoView != null) {
            this.f1550t = getVideoPercentViewed();
            this.videoView.stopPlayback();
        }
    }

    /* renamed from: I */
    private boolean m1683I() {
        return this.videoMuted;
    }

    /* renamed from: J */
    private void m1684J() {
        this.sdk.put(ef.f2450k, Integer.valueOf(this.videoView.getCurrentPosition()));
        this.sdk.put(ef.f2451l, Boolean.valueOf(true));
        try {
            this.countdownManager.m2016c();
        } catch (Throwable th) {
            this.logger.mo4174e("InterActivity", "Unable to pause countdown timers", th);
        }
        this.videoView.pause();
    }

    /* renamed from: K */
    private void m1685K() {
        long max = Math.max(0, new ee(this.sdk).ad());
        if (max > 0) {
            this.sdk.getLogger().mo4172d("InterActivity", "Resuming video with delay of " + max);
            this.f1553w.postDelayed(new bq(this), max);
            return;
        }
        this.sdk.getLogger().mo4172d("InterActivity", "Resuming video immediately");
        m1686L();
    }

    /* renamed from: L */
    private void m1686L() {
        if (!this.poststitialWasDisplayed && this.videoView != null && !this.videoView.isPlaying()) {
            this.videoView.seekTo(((Integer) this.sdk.get(ef.f2450k, Integer.valueOf(this.videoView.getDuration()))).intValue());
            this.videoView.start();
            this.countdownManager.m2013a();
        }
    }

    /* renamed from: M */
    private void m1687M() {
        if (!this.f1540j) {
            try {
                if (this.currentAd.mo3992f()) {
                    double videoPercentViewed = (double) getVideoPercentViewed();
                    String a = this.currentAd.m1815a((int) videoPercentViewed, this.currentPlacement, this.f1544n);
                    if (AppLovinSdkUtils.isValidString(a)) {
                        this.sdk.getPostbackService().dispatchPostbackAsync(a, null);
                    } else {
                        this.logger.mo4173e("InterActivity", "Received invalid placement aware parameterized video completion url. No postback dispatched.");
                    }
                    m1700a(this.currentAd, videoPercentViewed, isFullyWatched());
                    if (this.f1534d != null) {
                        this.f1534d.m2912b((long) videoPercentViewed);
                    }
                } else if ((this.currentAd instanceof C1286m) && m1679E() && this.settingsProxy.m2686M()) {
                    int B = m1673B();
                    this.logger.mo4172d("InterActivity", "Rewarded playable engaged at " + B + " percent");
                    m1700a(this.currentAd, (double) B, B >= this.currentAd.m1806S());
                }
            } catch (Throwable th) {
                if (this.logger != null) {
                    this.logger.mo4174e("InterActivity", "Failed to notify end listener.", th);
                }
            }
        }
    }

    /* renamed from: a */
    private int m1688a(int i) {
        return AppLovinSdkUtils.dpToPx(this, i);
    }

    /* renamed from: a */
    private int m1689a(int i, boolean z) {
        if (z) {
            if (i == 0) {
                return 0;
            }
            if (i == 1) {
                return 9;
            }
            if (i == 2) {
                return 8;
            }
            if (i == 3) {
                return 1;
            }
        } else if (i == 0) {
            return 1;
        } else {
            if (i == 1) {
                return 0;
            }
            if (i == 2) {
                return 9;
            }
            if (i == 3) {
                return 8;
            }
        }
        return -1;
    }

    /* renamed from: a */
    private static int m1690a(Display display) {
        return display.getWidth() == display.getHeight() ? 3 : display.getWidth() < display.getHeight() ? 1 : 2;
    }

    /* renamed from: a */
    private void m1692a(long j, ak akVar) {
        this.f1553w.postDelayed(new bf(this, akVar), j);
    }

    /* renamed from: a */
    private void m1693a(Uri uri) {
        this.videoView = new AppLovinVideoView(this);
        if (uri != null) {
            this.videoView.setOnPreparedListener(new br(this));
            this.videoView.setOnCompletionListener(new bu(this));
            this.videoView.setOnErrorListener(new bv(this));
            this.videoView.setVideoURI(uri);
        }
        this.videoView.setLayoutParams(new LayoutParams(-1, -1, 17));
        this.videoView.setOnTouchListener(new AppLovinTouchToClickListener(this, new bx(this)));
        this.f1554x.addView(this.videoView);
        setContentView(this.f1554x);
        m1740p();
    }

    /* renamed from: a */
    private void m1694a(View view, boolean z, long j) {
        float f = 1.0f;
        float f2 = z ? 0.0f : 1.0f;
        if (!z) {
            f = 0.0f;
        }
        Animation alphaAnimation = new AlphaAnimation(f2, f);
        alphaAnimation.setDuration(j);
        alphaAnimation.setAnimationListener(new bl(this, view, z));
        view.startAnimation(alphaAnimation);
    }

    /* renamed from: a */
    private void m1698a(an anVar) {
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.f1554x = new FrameLayout(this);
        this.f1554x.setLayoutParams(layoutParams);
        this.f1554x.setBackgroundColor(anVar.m1799L());
        this.f1553w = new Handler();
        this.f1552v = new Handler();
        this.countdownManager = new am(this.f1552v, this.sdk);
    }

    /* renamed from: a */
    private void m1699a(AppLovinAd appLovinAd) {
        bv.m2401a(this.f1532b.m2067d(), appLovinAd, this.sdk);
        this.f1537g = true;
    }

    /* renamed from: a */
    private void m1700a(AppLovinAd appLovinAd, double d, boolean z) {
        this.f1540j = true;
        bv.m2405a(this.f1532b.m2066c(), appLovinAd, d, z, this.sdk);
    }

    /* renamed from: a */
    private void m1701a(boolean z) {
        AppLovinSdkUtils.safePopulateImageView(this.f1524D, z ? this.currentAd.al() : this.currentAd.am(), m1688a(this.settingsProxy.m2718s()));
    }

    /* renamed from: a */
    private boolean m1702a() {
        int identifier = getResources().getIdentifier(this.settingsProxy.m2688O(), "bool", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        return identifier > 0 && getResources().getBoolean(identifier);
    }

    /* renamed from: b */
    private void m1704b() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    /* renamed from: b */
    private void m1705b(int i, boolean z) {
        int i2 = 0;
        int i3 = 1;
        boolean L = this.settingsProxy.m2685L();
        if (this.f1532b.m2069f() == ap.ACTIVITY_PORTRAIT) {
            if (z) {
                if (i != 1 && i != 3) {
                    this.f1533c = true;
                    setRequestedOrientation(1);
                } else if (!L) {
                } else {
                    if (i == 1) {
                        setRequestedOrientation(9);
                    } else {
                        setRequestedOrientation(1);
                    }
                }
            } else if (i != 0 && i != 2) {
                this.f1533c = true;
                setRequestedOrientation(1);
            } else if (L) {
                if (i != 0) {
                    i3 = 9;
                }
                setRequestedOrientation(i3);
            }
        } else if (this.f1532b.m2069f() != ap.ACTIVITY_LANDSCAPE) {
        } else {
            if (z) {
                if (i != 0 && i != 2) {
                    this.f1533c = true;
                    setRequestedOrientation(0);
                } else if (L) {
                    setRequestedOrientation(i == 2 ? 8 : 0);
                }
            } else if (i != 1 && i != 3) {
                this.f1533c = true;
                setRequestedOrientation(0);
            } else if (L) {
                if (i != 1) {
                    i2 = 8;
                }
                setRequestedOrientation(i2);
            }
        }
    }

    /* renamed from: b */
    private void m1707b(AppLovinAd appLovinAd) {
        dismiss();
        m1712c(appLovinAd);
    }

    /* renamed from: b */
    private void m1708b(boolean z) {
        this.videoMuted = z;
        MediaPlayer mediaPlayer = (MediaPlayer) this.f1525E.get();
        if (mediaPlayer != null) {
            int i = z ? 0 : 1;
            mediaPlayer.setVolume((float) i, (float) i);
        }
    }

    /* renamed from: c */
    private void m1712c(AppLovinAd appLovinAd) {
        if (!this.f1538h) {
            this.f1538h = true;
            if (this.f1532b != null) {
                bv.m2409b(this.f1532b.m2067d(), appLovinAd, this.sdk);
            }
        }
    }

    /* renamed from: c */
    private boolean m1713c() {
        return (this.f1532b == null || this.settingsProxy == null || this.settingsProxy.m2700a()) ? true : (this.settingsProxy.m2702c() && this.f1541k) ? true : this.settingsProxy.m2701b() && this.poststitialWasDisplayed;
    }

    /* renamed from: d */
    private void m1716d() {
        if (!this.currentAd.mo3999E() || this.currentAd.mo4003g() == null) {
            m1718e();
            m1720f();
            return;
        }
        this.sdk.getLogger().mo4172d("InterActivity", "Clicking through video...");
        clickThroughFromVideo();
    }

    /* renamed from: d */
    private void m1717d(AppLovinAd appLovinAd) {
        if (!this.f1539i) {
            this.f1539i = true;
            bv.m2406a(this.f1532b.m2066c(), appLovinAd, this.sdk);
        }
    }

    /* renamed from: e */
    private void m1718e() {
        if (this.settingsProxy.m2715p() && this.f1523C != null && this.f1523C.getVisibility() != 8) {
            m1694a(this.f1523C, this.f1523C.getVisibility() == 4, 750);
        }
    }

    /* renamed from: f */
    private void m1720f() {
        cm D = this.currentAd.m1791D();
        if (D != null && D.m2087e() && !this.poststitialWasDisplayed && this.f1527G != null) {
            m1694a(this.f1527G, this.f1527G.getVisibility() == 4, D.m2088f());
        }
    }

    /* renamed from: g */
    private void m1722g() {
        if (this.sdk != null) {
            this.sdk.put(ef.f2451l, Boolean.valueOf(false));
            this.sdk.put(ef.f2450k, Integer.valueOf(0));
        }
    }

    /* renamed from: h */
    private void m1724h() {
        this.f1536f = true;
        showPoststitial();
    }

    /* renamed from: i */
    private boolean m1727i() {
        return ((Integer) this.sdk.get(ef.f2450k, Integer.valueOf(0))).intValue() > 0 ? this.videoMuted : this.settingsProxy.m2724y() ? this.sdk.getSettings().isMuted() : this.settingsProxy.m2722w();
    }

    /* renamed from: j */
    private void m1728j() {
        int i = 3;
        this.f1555y = ak.m2007a(this.sdk, this, this.currentAd.m1830y());
        this.f1555y.setVisibility(8);
        this.f1555y.setOnClickListener(new by(this));
        int a = m1688a(this.currentAd.m1808U());
        int i2 = (this.currentAd.m1811X() ? 3 : 5) | 48;
        if (!this.currentAd.m1812Y()) {
            i = 5;
        }
        i |= 48;
        ViewGroup.LayoutParams layoutParams = new LayoutParams(a, a, i2 | 48);
        this.f1555y.mo4047a(a);
        int a2 = m1688a(this.currentAd.m1809V());
        int a3 = m1688a(this.currentAd.m1810W());
        layoutParams.setMargins(a3, a2, a3, a2);
        this.f1554x.addView(this.f1555y, layoutParams);
        this.f1521A = ak.m2007a(this.sdk, this, this.currentAd.m1831z());
        this.f1521A.setVisibility(8);
        this.f1521A.setOnClickListener(new bz(this));
        layoutParams = new LayoutParams(a, a, i);
        layoutParams.setMargins(a3, a2, a3, a2);
        this.f1521A.mo4047a(a);
        this.f1554x.addView(this.f1521A, layoutParams);
        this.f1521A.bringToFront();
        if (m1737n()) {
            int a4 = m1688a(new ee(this.sdk).m2710k());
            this.f1556z = new View(this);
            this.f1556z.setBackgroundColor(0);
            this.f1556z.setVisibility(8);
            this.f1522B = new View(this);
            this.f1522B.setBackgroundColor(0);
            this.f1522B.setVisibility(8);
            a += a4;
            int a5 = a2 - m1688a(5);
            layoutParams = new LayoutParams(a, a, i2);
            layoutParams.setMargins(a5, a5, a5, a5);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(a, a, i);
            layoutParams2.setMargins(a5, a5, a5, a5);
            this.f1556z.setOnClickListener(new ca(this));
            this.f1522B.setOnClickListener(new bb(this));
            this.f1554x.addView(this.f1556z, layoutParams);
            this.f1556z.bringToFront();
            this.f1554x.addView(this.f1522B, layoutParams2);
            this.f1522B.bringToFront();
        }
    }

    /* renamed from: k */
    private void m1730k() {
        if (this.f1524D == null) {
            try {
                this.videoMuted = m1727i();
                this.f1524D = new ImageView(this);
                if (m1733l()) {
                    this.sdk.getLogger().mo4172d("InterActivity", "Mute button should be hidden");
                    return;
                }
                int a = m1688a(this.settingsProxy.m2718s());
                ViewGroup.LayoutParams layoutParams = new LayoutParams(a, a, this.settingsProxy.m2719t());
                this.f1524D.setScaleType(ScaleType.FIT_CENTER);
                a = m1688a(this.settingsProxy.m2720u());
                layoutParams.setMargins(a, a, a, a);
                Object al = this.videoMuted ? this.currentAd.al() : this.currentAd.am();
                if (al != null) {
                    this.sdk.getLogger().mo4172d("InterActivity", "Added mute button with params: " + layoutParams);
                    m1701a(this.videoMuted);
                    this.f1524D.setClickable(true);
                    this.f1524D.setOnClickListener(new bc(this));
                    this.f1554x.addView(this.f1524D, layoutParams);
                    this.f1524D.bringToFront();
                    return;
                }
                this.sdk.getLogger().mo4173e("InterActivity", "Attempting to add mute button but could not find uri = " + al);
            } catch (Throwable e) {
                this.sdk.getLogger().mo4179w("InterActivity", "Failed to attach mute button", e);
            }
        }
    }

    /* renamed from: l */
    private boolean m1733l() {
        return !this.settingsProxy.m2716q() ? true : this.settingsProxy.m2717r() ? m1727i() ? false : !this.settingsProxy.m2723x() : false;
    }

    /* renamed from: m */
    private void m1734m() {
        runOnUiThread(new bd(this));
    }

    /* renamed from: n */
    private boolean m1737n() {
        return this.settingsProxy.m2710k() > 0;
    }

    /* renamed from: o */
    private void m1739o() {
        runOnUiThread(new be(this));
    }

    /* renamed from: p */
    private void m1740p() {
        if (this.currentAd.m1828w() >= 0.0f) {
            ak akVar = (!this.f1543m || this.f1521A == null) ? this.f1555y : this.f1521A;
            m1692a(gd.m2952c(this.currentAd.m1828w()), akVar);
        }
    }

    /* renamed from: q */
    private void m1742q() {
        int i = (!this.settingsProxy.m2707h() || m1748t() <= 0) ? 0 : 1;
        if (this.f1523C == null && i != 0) {
            this.f1523C = new ai(this);
            i = this.currentAd.m1798K();
            this.f1523C.m1992c(i);
            this.f1523C.m1989b((float) this.settingsProxy.m2706g());
            this.f1523C.m1994d(i);
            this.f1523C.m1986a((float) this.settingsProxy.m2705f());
            this.f1523C.m1990b(m1748t());
            this.f1523C.m1987a(m1748t());
            ViewGroup.LayoutParams layoutParams = new LayoutParams(m1688a(this.settingsProxy.m2704e()), m1688a(this.settingsProxy.m2704e()), this.settingsProxy.m2714o());
            int a = m1688a(this.settingsProxy.m2713n());
            layoutParams.setMargins(a, a, a, a);
            this.f1554x.addView(this.f1523C, layoutParams);
            this.f1523C.bringToFront();
            this.f1523C.setVisibility(0);
            this.countdownManager.m2014a("COUNTDOWN_CLOCK", 1000, new bg(this, m1746s()));
        }
    }

    /* renamed from: r */
    private boolean m1745r() {
        return (this.f1546p || this.poststitialWasDisplayed || !this.videoView.isPlaying()) ? false : true;
    }

    /* renamed from: s */
    private long m1746s() {
        return TimeUnit.SECONDS.toMillis((long) m1748t());
    }

    /* renamed from: t */
    private int m1748t() {
        int J = this.currentAd.m1797J();
        return (J <= 0 && this.settingsProxy.m2712m()) ? this.computedLengthSeconds + 1 : J;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: u */
    private void m1750u() {
        if (this.f1528H == null && this.currentAd.m1804Q()) {
            this.logger.mo4175i("InterActivity", "Attaching video progress bar...");
            this.f1528H = new ProgressBar(this, null, 16842872);
            this.f1528H.setMax(this.settingsProxy.af());
            this.f1528H.setPadding(0, 0, 0, 0);
            if (ab.m2207g()) {
                try {
                    this.f1528H.setProgressTintList(ColorStateList.valueOf(this.currentAd.m1805R()));
                } catch (Throwable th) {
                    this.logger.mo4174e("InterActivity", "Unable to update progress bar color.", th);
                }
            }
            ViewGroup.LayoutParams layoutParams = new LayoutParams(this.videoView.getWidth(), 20, 80);
            layoutParams.setMargins(0, 0, 0, this.settingsProxy.ag());
            this.f1554x.addView(this.f1528H, layoutParams);
            this.f1528H.bringToFront();
            this.countdownManager.m2014a("PROGRESS_BAR", this.settingsProxy.ae(), new bh(this));
        }
    }

    /* renamed from: v */
    private void m1752v() {
        cm D = this.currentAd.m1791D();
        if (AppLovinSdkUtils.isValidString(this.currentAd.m1790C()) && D != null && this.f1527G == null) {
            this.logger.mo4175i("InterActivity", "Attaching video button...");
            this.f1527G = m1755w();
            double b = ((double) D.m2084b()) / 100.0d;
            ViewGroup.LayoutParams layoutParams = new LayoutParams((int) ((((double) D.m2083a()) / 100.0d) * ((double) this.videoView.getWidth())), (int) (((double) this.videoView.getHeight()) * b), D.m2086d());
            int a = m1688a(D.m2085c());
            layoutParams.setMargins(a, a, a, a);
            this.f1554x.addView(this.f1527G, layoutParams);
            this.f1527G.bringToFront();
            if (D.m2091i() > 0.0f) {
                this.f1527G.setVisibility(4);
                this.f1553w.postDelayed(new bi(this, D), gd.m2952c(D.m2091i()));
            }
            if (D.m2092j() > 0.0f) {
                this.f1553w.postDelayed(new bj(this, D), gd.m2952c(D.m2092j()));
            }
        }
    }

    /* renamed from: w */
    private cn m1755w() {
        this.logger.mo4172d("InterActivity", "Create video button with HTML = " + this.currentAd.m1790C());
        co coVar = new co(this.sdk);
        this.f1529I = new bk(this);
        coVar.m2095a(new WeakReference(this.f1529I));
        cn cnVar = new cn(coVar, getApplicationContext());
        cnVar.m2093a(this.currentAd.m1790C());
        return cnVar;
    }

    /* renamed from: x */
    private void m1756x() {
        if (m1761z()) {
            m1684J();
            pauseReportRewardTask();
            this.logger.mo4172d("InterActivity", "Prompting incentivized ad close warning");
            this.f1526F.m2379b();
            return;
        }
        skipVideo();
    }

    /* renamed from: y */
    private void m1759y() {
        if (m1671A()) {
            this.logger.mo4172d("InterActivity", "Prompting incentivized non-video ad close warning");
            this.f1526F.m2380c();
            return;
        }
        dismiss();
    }

    /* renamed from: z */
    private boolean m1761z() {
        return m1678D() && !isFullyWatched() && this.settingsProxy.m2680G() && this.f1526F != null;
    }

    public void clickThroughFromVideo() {
        try {
            this.sdk.getAdService().trackAndLaunchVideoClick(this.currentAd, this.currentPlacement, this.f1531a, this.currentAd.mo4003g());
            bv.m2400a(this.f1532b.m2068e(), this.currentAd, this.sdk);
            if (this.f1534d != null) {
                this.f1534d.m2911b();
            }
        } catch (Throwable th) {
            this.sdk.getLogger().mo4174e("InterActivity", "Encountered error while clicking through video.", th);
        }
    }

    public void continueVideo() {
        m1686L();
    }

    public void dismiss() {
        this.logger.mo4175i("InterActivity", "Dismissing ad after " + (System.currentTimeMillis() - this.f1548r) + " milliseconds elapsed");
        ((AdViewControllerImpl) this.f1531a.getAdViewController()).setIsForegroundClickInvalidated(true);
        m1722g();
        m1687M();
        if (this.f1532b != null) {
            if (this.currentAd != null) {
                m1712c(this.currentAd);
                if (this.f1534d != null) {
                    this.f1534d.m2913c();
                    this.f1534d = null;
                }
            }
            this.f1532b.m2064a(false);
            this.f1532b.m2071h();
        }
        finish();
    }

    public void exitWithError(String str) {
        try {
            Log.e("InterActivity", "Failed to properly render an Interstitial Activity, due to error: " + str, new Throwable("Initialized = " + cb.f1831d + "; CleanedUp = " + cb.f1832e));
            m1712c(new ar());
        } catch (Throwable e) {
            Log.e("InterActivity", "Failed to show a video ad due to error:", e);
        }
        finish();
    }

    public boolean getPoststitialWasDisplayed() {
        return this.poststitialWasDisplayed;
    }

    public int getVideoPercentViewed() {
        if (this.f1536f) {
            return 100;
        }
        if (this.videoView != null) {
            int duration = this.videoView.getDuration();
            return duration > 0 ? (int) ((((double) this.videoView.getCurrentPosition()) / ((double) duration)) * 100.0d) : this.f1550t;
        } else {
            this.logger.mo4173e("InterActivity", "No video view detected on video end");
            return 0;
        }
    }

    public void handleMediaError() {
        if (this.f1551u.compareAndSet(false, true)) {
            if (this.settingsProxy.m2709j()) {
                this.logger.mo4173e("InterActivity", "Handling media player error - Finishing activity...");
                finish();
            } else {
                this.logger.mo4173e("InterActivity", "Handling media player error - Showing poststitial...");
                showPoststitial();
            }
            this.logger.mo4173e("InterActivity", "Finished handling media player error.");
            return;
        }
        this.logger.mo4173e("InterActivity", "Already handled media player error. Doing nothing...");
    }

    protected boolean isFullyWatched() {
        return getVideoPercentViewed() >= this.currentAd.m1806S();
    }

    protected boolean isVastAd() {
        return this.currentAd instanceof C1228a;
    }

    public void onBackPressed() {
        if (m1713c()) {
            this.logger.mo4172d("InterActivity", "Back button was pressed; forwarding to Android for handling...");
            super.onBackPressed();
            return;
        }
        try {
            if (this.f1543m && this.f1521A != null && this.f1521A.getVisibility() == 0 && this.f1521A.getAlpha() > 0.0f && !this.f1541k) {
                this.logger.mo4172d("InterActivity", "Back button was pressed; forwarding as a click to skip button.");
                this.f1521A.performClick();
            } else if (this.f1555y == null || this.f1555y.getVisibility() != 0 || this.f1555y.getAlpha() <= 0.0f) {
                this.logger.mo4172d("InterActivity", "Back button was pressed, but was not eligible for dismissal.");
            } else {
                this.logger.mo4172d("InterActivity", "Back button was pressed; forwarding as a click to close button.");
                this.f1555y.performClick();
            }
        } catch (Exception e) {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        try {
            String stringExtra = getIntent().getStringExtra(KEY_WRAPPER_ID);
            if (stringExtra == null || stringExtra.isEmpty()) {
                exitWithError("Wrapper ID is null");
                m1722g();
                if (this.f1534d != null) {
                    this.f1534d.m2909a();
                }
                m1681G();
            }
            this.f1532b = cb.m2048a(stringExtra);
            if (this.f1532b == null && lastKnownWrapper != null) {
                this.f1532b = lastKnownWrapper;
            }
            if (this.f1532b != null) {
                AppLovinAd b = this.f1532b.m2065b();
                this.sdk = (AppLovinSdkImpl) this.f1532b.m2061a();
                this.logger = this.f1532b.m2061a().getLogger();
                this.settingsProxy = new ee(this.f1532b.m2061a());
                this.currentPlacement = this.f1532b.m2070g();
                this.f1534d = new C1280g(b, this.sdk);
                if (b != null) {
                    an anVar = (an) b;
                    View findViewById = findViewById(16908290);
                    if (findViewById != null) {
                        if (anVar.mo3992f()) {
                            findViewById.setBackgroundColor(anVar.m1799L());
                        } else {
                            findViewById.setBackgroundColor(anVar.m1800M());
                        }
                    }
                    this.f1548r = System.currentTimeMillis();
                    if (anVar.m1793F()) {
                        getWindow().setFlags(16777216, 16777216);
                    }
                    Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
                    int a = m1690a(defaultDisplay);
                    int rotation = defaultDisplay.getRotation();
                    boolean z = (a == 2 && rotation == 0) || ((a == 2 && rotation == 2) || ((a == 1 && rotation == 1) || (a == 1 && rotation == 3)));
                    if (anVar.m1796I()) {
                        int a2 = m1689a(rotation, z);
                        if (a2 != -1) {
                            this.logger.mo4172d("InterActivity", "Locking activity orientation to current orientation: " + a2);
                            setRequestedOrientation(a2);
                        } else {
                            this.logger.mo4173e("InterActivity", "Unable to detect current orientation. Locking to targeted orientation...");
                            m1705b(rotation, z);
                        }
                    } else {
                        this.logger.mo4172d("InterActivity", "Locking activity orientation to targeted orientation...");
                        m1705b(rotation, z);
                    }
                    this.f1531a = new AppLovinAdView(this.sdk, AppLovinAdSize.INTERSTITIAL, (Context) this);
                    this.f1531a.setAutoDestroy(false);
                    ((AdViewControllerImpl) this.f1531a.getAdViewController()).setStatsManagerHelper(this.f1534d);
                    this.f1532b.m2062a((aq) this);
                    this.f1543m = this.settingsProxy.m2711l();
                    boolean z2 = ab.m2197a(getApplicationContext()) || ab.m2197a(getApplicationContext());
                    this.f1547q = z2;
                    this.f1526F = new bm(this, this.sdk);
                } else {
                    exitWithError("No current ad found.");
                }
            } else {
                exitWithError("Wrapper is null; initialized state: " + Boolean.toString(cb.f1831d));
            }
            m1722g();
            if (this.f1534d != null) {
                this.f1534d.m2909a();
            }
            m1681G();
        } catch (Throwable th) {
            if (this.logger != null) {
                this.logger.mo4174e("InterActivity", "Encountered error during onCreate.", th);
            }
            exitWithError("An error was encountered during interstitial ad creation.");
        }
    }

    protected void onDestroy() {
        try {
            if (this.f1531a != null) {
                ViewParent parent = this.f1531a.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(this.f1531a);
                }
                this.f1531a.destroy();
                this.f1531a = null;
            }
            if (this.videoView != null) {
                this.videoView.pause();
                this.videoView.stopPlayback();
            }
            if (this.countdownManager != null) {
                this.countdownManager.m2015b();
            }
            if (this.f1553w != null) {
                this.f1553w.removeCallbacksAndMessages(null);
            }
            if (this.f1552v != null) {
                this.f1552v.removeCallbacksAndMessages(null);
            }
            if (this.currentAd != null) {
                m1687M();
                m1712c(this.currentAd);
            }
        } catch (Throwable th) {
            if (this.currentAd != null) {
                m1687M();
                m1712c(this.currentAd);
            }
        }
        super.onDestroy();
    }

    protected void onPause() {
        this.logger.mo4172d("InterActivity", "App paused...");
        this.f1549s = System.currentTimeMillis();
        if (!this.f1535e) {
            if (this.f1547q) {
                m1684J();
            } else if (!this.f1533c) {
                m1684J();
            }
        }
        this.f1532b.m2064a(false);
        this.f1526F.m2378a();
        pauseReportRewardTask();
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        this.logger.mo4172d("InterActivity", "App resumed...");
        this.f1532b.m2064a(true);
        if (!this.f1545o) {
            if (this.f1534d != null) {
                this.f1534d.m2914c(System.currentTimeMillis() - this.f1549s);
            }
            if (!((Boolean) this.sdk.get(ef.f2451l, Boolean.valueOf(false))).booleanValue() || this.f1526F.m2381d() || this.poststitialWasDisplayed) {
                boolean i = this.currentAd instanceof C1286m ? ((C1286m) this.currentAd).m3031i() : false;
                if (!(this.currentAd == null || !this.settingsProxy.m2708i() || this.currentAd.m1794G() || !this.poststitialWasDisplayed || this.f1555y == null || i)) {
                    m1692a(0, this.f1555y);
                }
            } else {
                m1685K();
                if (!(this.currentAd == null || !this.settingsProxy.m2708i() || this.currentAd.m1795H() || this.poststitialWasDisplayed || !this.f1543m || this.f1521A == null)) {
                    m1692a(0, this.f1521A);
                }
            }
            resumeReportRewardTask();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.logger.mo4172d("InterActivity", "Window gained focus");
            try {
                if (ab.m2206f() && this.settingsProxy.m2679F() && m1702a()) {
                    m1704b();
                    if (this.settingsProxy.m2689P() > 0) {
                        this.f1553w.postDelayed(new ba(this), this.settingsProxy.m2689P());
                    }
                } else {
                    getWindow().setFlags(1024, 1024);
                }
                if (this.settingsProxy.m2687N() && !this.poststitialWasDisplayed) {
                    m1685K();
                    resumeReportRewardTask();
                }
            } catch (Throwable th) {
                this.logger.mo4174e("InterActivity", "Setting window flags failed.", th);
            }
        } else {
            this.logger.mo4172d("InterActivity", "Window lost focus");
            if (this.settingsProxy.m2687N() && !this.poststitialWasDisplayed) {
                m1684J();
                pauseReportRewardTask();
            }
        }
        this.f1545o = false;
    }

    public void pauseReportRewardTask() {
        if (this.f1530J != null) {
            this.f1530J.m2924a();
        }
    }

    protected void playVideo() {
        m1717d(this.currentAd);
        this.videoView.start();
        this.countdownManager.m2013a();
    }

    public void resumeReportRewardTask() {
        if (this.f1530J != null) {
            this.f1530J.m2925b();
        }
    }

    protected boolean shouldContinueFullLengthVideoCountdown() {
        return (this.f1536f || this.poststitialWasDisplayed) ? false : true;
    }

    public void showPoststitial() {
        try {
            if (!this.currentAd.m1813Z()) {
                m1682H();
            }
            if (this.f1531a != null) {
                ViewParent parent = this.f1531a.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(this.f1531a);
                }
                View frameLayout = new FrameLayout(this);
                frameLayout.setLayoutParams(new LayoutParams(-1, -1));
                frameLayout.setBackgroundColor(this.currentAd.m1800M());
                frameLayout.addView(this.f1531a);
                if (this.currentAd.af()) {
                    this.f1531a.renderAd(this.currentAd, this.currentPlacement);
                }
                if (this.currentAd.m1813Z()) {
                    m1682H();
                }
                if (this.f1554x != null) {
                    this.f1554x.removeAllViewsInLayout();
                }
                if (m1737n() && this.f1556z != null) {
                    frameLayout.addView(this.f1556z);
                    this.f1556z.bringToFront();
                }
                if (this.f1555y != null) {
                    parent = this.f1555y.getParent();
                    if (parent instanceof ViewGroup) {
                        ((ViewGroup) parent).removeView(this.f1555y);
                    }
                    frameLayout.addView(this.f1555y);
                    this.f1555y.bringToFront();
                }
                setContentView(frameLayout);
                if (this.settingsProxy.m2683J()) {
                    this.f1531a.setVisibility(4);
                    this.f1531a.setVisibility(0);
                }
                int T = this.currentAd.m1807T();
                if (T >= 0) {
                    this.f1553w.postDelayed(new bp(this), (long) T);
                }
            }
            if (this.currentAd instanceof C1286m ? ((C1286m) this.currentAd).m3031i() : false) {
                this.logger.mo4172d("InterActivity", "Skip showing of close button");
            } else if (this.currentAd.m1829x() >= 0.0f) {
                m1692a(gd.m2952c(this.currentAd.m1829x()), this.f1555y);
            } else if (this.currentAd.m1829x() == -2.0f) {
                this.f1555y.setVisibility(0);
            } else {
                m1692a(0, this.f1555y);
            }
            this.poststitialWasDisplayed = true;
        } catch (Throwable th) {
            this.logger.mo4174e("InterActivity", "Encountered error while showing poststitial. Dismissing...", th);
            dismiss();
        }
    }

    public void skipVideo() {
        if (this.f1534d != null) {
            this.f1534d.m2917f();
        }
        if (this.currentAd.m1789B()) {
            dismiss();
        } else {
            showPoststitial();
        }
    }

    public void toggleMute() {
        boolean z = !m1683I();
        try {
            m1708b(z);
            m1701a(z);
        } catch (Throwable th) {
            this.logger.mo4174e("InterActivity", "Unable to set volume to " + z, th);
        }
    }
}
