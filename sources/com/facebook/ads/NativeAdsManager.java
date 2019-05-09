package com.facebook.ads;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.NativeAdBase.MediaCacheFlag;
import com.facebook.ads.internal.C1857a;
import com.facebook.ads.internal.C1857a.C1834a;
import com.facebook.ads.internal.adapters.C1924i;
import com.facebook.ads.internal.p024h.C1832a;
import com.facebook.ads.internal.p024h.C2011b;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.protocol.C2070e;
import java.util.ArrayList;
import java.util.List;

public class NativeAdsManager {
    /* renamed from: a */
    private static final String f3806a = NativeAdsManager.class.getSimpleName();
    /* renamed from: b */
    private final Context f3807b;
    /* renamed from: c */
    private final String f3808c;
    /* renamed from: d */
    private final int f3809d;
    /* renamed from: e */
    private final List<NativeAd> f3810e;
    /* renamed from: f */
    private int f3811f;
    /* renamed from: g */
    private Listener f3812g;
    /* renamed from: h */
    private String f3813h;
    /* renamed from: i */
    private C1857a f3814i;
    /* renamed from: j */
    private boolean f3815j;
    /* renamed from: k */
    private boolean f3816k;

    public interface Listener {
        void onAdError(AdError adError);

        void onAdsLoaded();
    }

    public NativeAdsManager(Context context, String str, int i) {
        if (context == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        this.f3807b = context;
        this.f3808c = str;
        this.f3809d = Math.max(i, 0);
        this.f3810e = new ArrayList(i);
        this.f3811f = -1;
        this.f3816k = false;
        this.f3815j = false;
        try {
            CookieManager.getInstance();
            if (VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(context);
            }
        } catch (Throwable e) {
            Log.w(f3806a, "Failed to initialize CookieManager.", e);
        }
    }

    public void disableAutoRefresh() {
        this.f3815j = true;
        if (this.f3814i != null) {
            this.f3814i.m4190c();
        }
    }

    public int getUniqueNativeAdCount() {
        return this.f3810e.size();
    }

    public boolean isLoaded() {
        return this.f3816k;
    }

    public void loadAds() {
        loadAds(MediaCacheFlag.ALL);
    }

    public void loadAds(final MediaCacheFlag mediaCacheFlag) {
        C2070e c2070e = C2070e.NATIVE_UNKNOWN;
        int i = this.f3809d;
        if (this.f3814i != null) {
            this.f3814i.m4189b();
        }
        this.f3814i = new C1857a(this.f3807b, this.f3808c, c2070e, null, i);
        if (this.f3815j) {
            this.f3814i.m4190c();
        }
        this.f3814i.m4188a(this.f3813h);
        this.f3814i.m4185a(new C1834a(this) {
            /* renamed from: b */
            final /* synthetic */ NativeAdsManager f3805b;

            /* renamed from: a */
            public void mo5370a(C2065a c2065a) {
                if (this.f3805b.f3812g != null) {
                    this.f3805b.f3812g.onAdError(AdError.getAdErrorFromWrapper(c2065a));
                }
            }

            /* renamed from: a */
            public void mo5371a(final List<C1924i> list) {
                C2011b c2011b = new C2011b(this.f3805b.f3807b);
                for (C1924i c1924i : list) {
                    if (mediaCacheFlag.equals(MediaCacheFlag.ALL)) {
                        if (c1924i.m4512l() != null) {
                            c2011b.m4844a(c1924i.m4512l().m5352a(), c1924i.m4512l().m5354c(), c1924i.m4512l().m5353b());
                        }
                        if (c1924i.m4513m() != null) {
                            c2011b.m4844a(c1924i.m4513m().m5352a(), c1924i.m4513m().m5354c(), c1924i.m4513m().m5353b());
                        }
                        if (!TextUtils.isEmpty(c1924i.m4520t())) {
                            c2011b.m4843a(c1924i.m4520t());
                        }
                    }
                }
                c2011b.m4842a(new C1832a(this) {
                    /* renamed from: b */
                    final /* synthetic */ C18351 f3803b;

                    /* renamed from: c */
                    private void m4112c() {
                        this.f3803b.f3805b.f3816k = true;
                        this.f3803b.f3805b.f3810e.clear();
                        this.f3803b.f3805b.f3811f = 0;
                        for (C1924i nativeAd : list) {
                            this.f3803b.f3805b.f3810e.add(new NativeAd(this.f3803b.f3805b.f3807b, nativeAd, null));
                        }
                        if (this.f3803b.f3805b.f3812g != null) {
                            this.f3803b.f3805b.f3812g.onAdsLoaded();
                        }
                    }

                    /* renamed from: a */
                    public void mo5368a() {
                        m4112c();
                    }

                    /* renamed from: b */
                    public void mo5369b() {
                        m4112c();
                    }
                });
            }
        });
        this.f3814i.m4184a();
    }

    public NativeAd nextNativeAd() {
        if (this.f3810e.size() == 0) {
            return null;
        }
        int i = this.f3811f;
        this.f3811f = i + 1;
        NativeAdBase nativeAdBase = (NativeAd) this.f3810e.get(i % this.f3810e.size());
        return i >= this.f3810e.size() ? new NativeAd(nativeAdBase) : nativeAdBase;
    }

    public void setExtraHints(String str) {
        this.f3813h = str;
    }

    public void setListener(Listener listener) {
        this.f3812g = listener;
    }
}
