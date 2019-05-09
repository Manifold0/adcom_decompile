package com.facebook.ads.internal.view.p022i;

import android.content.Context;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.p025w.p026b.C1910r;
import com.facebook.ads.internal.p032d.C1907b;
import com.facebook.ads.internal.p032d.C1987a;
import com.facebook.ads.internal.p032d.C1989c;
import com.facebook.ads.internal.p032d.C1989c.C1988a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import com.facebook.appevents.AppEventsConstants;
import com.ironsource.sdk.constants.LocationConst;
import com.ironsource.sdk.precache.DownloadManager;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.i.c */
public class C2422c implements C1910r<Bundle> {
    /* renamed from: a */
    private final String f5831a;
    /* renamed from: b */
    private boolean f5832b;
    /* renamed from: c */
    private final Context f5833c;
    /* renamed from: d */
    private final C2085c f5834d;
    /* renamed from: e */
    private final C2392a f5835e;
    /* renamed from: f */
    private final C1987a f5836f;
    /* renamed from: g */
    private int f5837g;
    /* renamed from: h */
    private int f5838h;
    @Nullable
    /* renamed from: i */
    private String f5839i;
    @Nullable
    /* renamed from: j */
    private String f5840j;
    /* renamed from: k */
    private final C2503e f5841k;
    @Nullable
    /* renamed from: l */
    private final Map<String, String> f5842l;

    /* renamed from: com.facebook.ads.internal.view.i.c$a */
    public interface C2392a {
        int getCurrentPositionInMillis();

        boolean getGlobalVisibleRect(Rect rect);

        long getInitialBufferTime();

        int getMeasuredHeight();

        int getMeasuredWidth();

        C2389a getVideoStartReason();

        @Nullable
        View getView();

        float getVolume();

        /* renamed from: h */
        boolean mo5606h();

        /* renamed from: i */
        boolean mo5607i();
    }

    /* renamed from: com.facebook.ads.internal.view.i.c$b */
    protected enum C2426b {
        PLAY(0),
        SKIP(1),
        TIME(2),
        MRC(3),
        PAUSE(4),
        RESUME(5),
        MUTE(6),
        UNMUTE(7),
        VIEWABLE_IMPRESSION(10);
        
        /* renamed from: j */
        public final int f5869j;

        private C2426b(int i) {
            this.f5869j = i;
        }
    }

    public C2422c(Context context, C2085c c2085c, C2392a c2392a, List<C1907b> list, String str) {
        this(context, c2085c, c2392a, list, str, null);
    }

    public C2422c(Context context, C2085c c2085c, C2392a c2392a, List<C1907b> list, String str, @Nullable Bundle bundle) {
        this(context, c2085c, c2392a, list, str, bundle, null);
    }

    public C2422c(Context context, C2085c c2085c, C2392a c2392a, List<C1907b> list, String str, @Nullable Bundle bundle, @Nullable Map<String, String> map) {
        this.f5832b = true;
        this.f5837g = 0;
        this.f5838h = 0;
        this.f5839i = null;
        this.f5840j = null;
        this.f5833c = context;
        this.f5834d = c2085c;
        this.f5835e = c2392a;
        this.f5831a = str;
        this.f5842l = map;
        list.add(new C1907b(this, 0.5d, -1.0d, 2.0d, true) {
            /* renamed from: a */
            final /* synthetic */ C2422c f5857a;

            /* renamed from: a */
            protected void mo5396a(boolean z, boolean z2, C1989c c1989c) {
                if (z2) {
                    this.f5857a.f5834d.mo5476e(this.f5857a.f5831a, this.f5857a.m6213a(C2426b.MRC));
                }
            }
        });
        list.add(new C1907b(this, 1.0E-7d, -1.0d, 0.001d, false) {
            /* renamed from: a */
            final /* synthetic */ C2422c f5858a;

            /* renamed from: a */
            protected void mo5396a(boolean z, boolean z2, C1989c c1989c) {
                if (z2) {
                    this.f5858a.f5834d.mo5476e(this.f5858a.f5831a, this.f5858a.m6213a(C2426b.VIEWABLE_IMPRESSION));
                }
            }
        });
        if (bundle != null) {
            this.f5836f = new C1987a(c2392a.getView(), list, bundle.getBundle("adQualityManager"));
            this.f5837g = bundle.getInt("lastProgressTimeMS");
            this.f5838h = bundle.getInt("lastBoundaryTimeMS");
        } else {
            this.f5836f = new C1987a(c2392a.getView(), list);
        }
        this.f5841k = new C2503e(new Handler(), this);
    }

    /* renamed from: a */
    private Map<String, String> m6213a(C2426b c2426b) {
        return m6214a(c2426b, this.f5835e.getCurrentPositionInMillis());
    }

    /* renamed from: a */
    private Map<String, String> m6214a(C2426b c2426b, int i) {
        Object obj = 1;
        Map<String, String> hashMap = new HashMap();
        Object obj2 = this.f5835e.getVideoStartReason() == C2389a.AUTO_STARTED ? 1 : null;
        if (this.f5835e.mo5607i()) {
            obj = null;
        }
        hashMap.put(AudienceNetworkActivity.AUTOPLAY, obj2 != null ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        hashMap.put(String.INLINE, obj != null ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        hashMap.put("exoplayer", String.valueOf(this.f5835e.mo5606h()));
        hashMap.put("prep", Long.toString(this.f5835e.getInitialBufferTime()));
        C1989c c = this.f5836f.m4750c();
        C1988a c2 = c.m4765c();
        hashMap.put("vwa", String.valueOf(c2.m4756d()));
        hashMap.put("vwm", String.valueOf(c2.m4755c()));
        hashMap.put("vwmax", String.valueOf(c2.m4757e()));
        hashMap.put("vtime_ms", String.valueOf(c2.m4759g() * 1000.0d));
        hashMap.put("mcvt_ms", String.valueOf(c2.m4760h() * 1000.0d));
        if (this.f5839i != null) {
            hashMap.put("vw_d", this.f5839i);
        }
        if (this.f5840j != null) {
            hashMap.put("vw_rsn", this.f5840j);
        }
        C1988a d = c.m4766d();
        hashMap.put("vla", String.valueOf(d.m4756d()));
        hashMap.put("vlm", String.valueOf(d.m4755c()));
        hashMap.put("vlmax", String.valueOf(d.m4757e()));
        hashMap.put("atime_ms", String.valueOf(d.m4759g() * 1000.0d));
        hashMap.put("mcat_ms", String.valueOf(d.m4760h() * 1000.0d));
        hashMap.put("ptime", String.valueOf(((float) this.f5838h) / 1000.0f));
        hashMap.put(LocationConst.TIME, String.valueOf(((float) i) / 1000.0f));
        Rect rect = new Rect();
        this.f5835e.getGlobalVisibleRect(rect);
        hashMap.put("pt", String.valueOf(rect.top));
        hashMap.put("pl", String.valueOf(rect.left));
        hashMap.put("ph", String.valueOf(this.f5835e.getMeasuredHeight()));
        hashMap.put("pw", String.valueOf(this.f5835e.getMeasuredWidth()));
        WindowManager windowManager = (WindowManager) this.f5833c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        hashMap.put("vph", String.valueOf(displayMetrics.heightPixels));
        hashMap.put("vpw", String.valueOf(displayMetrics.widthPixels));
        if (this.f5842l != null) {
            hashMap.putAll(this.f5842l);
        }
        hashMap.put("action", String.valueOf(c2426b.f5869j));
        return hashMap;
    }

    /* renamed from: a */
    void m6217a(int i) {
        m6219a(i, false, false);
    }

    /* renamed from: a */
    void m6218a(int i, int i2) {
        m6219a(i, true, false);
        this.f5838h = i2;
        this.f5837g = i2;
        this.f5836f.m4747a();
        this.f5836f.m4749b();
    }

    /* renamed from: a */
    void m6219a(int i, boolean z, boolean z2) {
        if (((double) i) > 0.0d && i >= this.f5837g) {
            if (i > this.f5837g) {
                this.f5836f.m4748a((double) (((float) (i - this.f5837g)) / 1000.0f), (double) m6223d());
                this.f5837g = i;
                if (z2 || i - this.f5838h >= DownloadManager.OPERATION_TIMEOUT) {
                    this.f5834d.mo5476e(this.f5831a, m6214a(C2426b.TIME, i));
                    this.f5838h = this.f5837g;
                    this.f5836f.m4747a();
                    return;
                }
            }
            if (z) {
                this.f5834d.mo5476e(this.f5831a, m6214a(C2426b.TIME, i));
            }
        }
    }

    /* renamed from: b */
    public void m6220b() {
        this.f5833c.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.f5841k);
    }

    /* renamed from: b */
    public void m6221b(int i) {
        m6219a(i, true, false);
        this.f5838h = 0;
        this.f5837g = 0;
        this.f5836f.m4747a();
        this.f5836f.m4749b();
    }

    /* renamed from: c */
    public void m6222c() {
        this.f5833c.getContentResolver().unregisterContentObserver(this.f5841k);
    }

    /* renamed from: d */
    protected float m6223d() {
        float f;
        AudioManager audioManager = (AudioManager) this.f5833c.getSystemService("audio");
        if (audioManager != null) {
            int streamVolume = audioManager.getStreamVolume(3);
            int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            if (streamMaxVolume > 0) {
                f = (((float) streamVolume) * 1.0f) / ((float) streamMaxVolume);
                return f * this.f5835e.getVolume();
            }
        }
        f = 0.0f;
        return f * this.f5835e.getVolume();
    }

    /* renamed from: e */
    void m6224e() {
        if (((double) m6223d()) < 0.05d) {
            if (this.f5832b) {
                this.f5834d.mo5476e(this.f5831a, m6213a(C2426b.MUTE));
                this.f5832b = false;
            }
        } else if (!this.f5832b) {
            this.f5834d.mo5476e(this.f5831a, m6213a(C2426b.UNMUTE));
            this.f5832b = true;
        }
    }

    /* renamed from: f */
    void m6225f() {
        this.f5834d.mo5476e(this.f5831a, m6213a(C2426b.SKIP));
    }

    /* renamed from: g */
    public Bundle mo5398g() {
        m6218a(m6229j(), m6229j());
        Bundle bundle = new Bundle();
        bundle.putInt("lastProgressTimeMS", this.f5837g);
        bundle.putInt("lastBoundaryTimeMS", this.f5838h);
        bundle.putBundle("adQualityManager", this.f5836f.mo5398g());
        return bundle;
    }

    /* renamed from: h */
    void m6227h() {
        this.f5834d.mo5476e(this.f5831a, m6213a(C2426b.PAUSE));
    }

    /* renamed from: i */
    void m6228i() {
        this.f5834d.mo5476e(this.f5831a, m6213a(C2426b.RESUME));
    }

    /* renamed from: j */
    public int m6229j() {
        return this.f5837g;
    }
}
