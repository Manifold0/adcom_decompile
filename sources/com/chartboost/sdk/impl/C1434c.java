package com.chartboost.sdk.impl;

import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.facebook.internal.AnalyticsEvents;

/* renamed from: com.chartboost.sdk.impl.c */
public class C1434c {
    /* renamed from: a */
    public final int f3209a;
    /* renamed from: b */
    public final String f3210b;
    /* renamed from: c */
    public final String f3211c;
    /* renamed from: d */
    public final String f3212d;
    /* renamed from: e */
    public final String f3213e;
    /* renamed from: f */
    public final String f3214f;
    /* renamed from: g */
    public final boolean f3215g;
    /* renamed from: h */
    public final boolean f3216h;

    /* renamed from: com.chartboost.sdk.impl.c$a */
    public class C1433a implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1434c f3205a;
        /* renamed from: b */
        private final int f3206b;
        /* renamed from: c */
        private final String f3207c;
        /* renamed from: d */
        private final CBImpressionError f3208d;

        public C1433a(C1434c c1434c, int i, String str, CBImpressionError cBImpressionError) {
            this.f3205a = c1434c;
            this.f3206b = i;
            this.f3207c = str;
            this.f3208d = cBImpressionError;
        }

        public void run() {
            switch (this.f3206b) {
                case 0:
                    this.f3205a.m3543d(this.f3207c);
                    return;
                case 1:
                    this.f3205a.m3539a(this.f3207c);
                    return;
                case 2:
                    this.f3205a.m3541b(this.f3207c);
                    return;
                case 3:
                    this.f3205a.m3542c(this.f3207c);
                    return;
                case 4:
                    this.f3205a.m3540a(this.f3207c, this.f3208d);
                    return;
                case 5:
                    this.f3205a.m3544e(this.f3207c);
                    return;
                default:
                    return;
            }
        }
    }

    private C1434c(int i, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2) {
        this.f3209a = i;
        this.f3210b = str;
        this.f3211c = str2;
        this.f3212d = str3;
        this.f3213e = str4;
        this.f3214f = str5;
        this.f3215g = z;
        this.f3216h = z2;
    }

    /* renamed from: a */
    public static C1434c m3535a() {
        return new C1434c(0, "interstitial", "interstitial", "/interstitial/get", "webview/%s/interstitial/get", "/interstitial/show", false, false);
    }

    /* renamed from: b */
    public static C1434c m3536b() {
        return new C1434c(1, "rewarded", "rewarded-video", "/reward/get", "webview/%s/reward/get", "/reward/show", true, false);
    }

    /* renamed from: c */
    public static C1434c m3537c() {
        return new C1434c(2, "inplay", null, "/inplay/get", "no webview endpoint", "/inplay/show", false, true);
    }

    /* renamed from: a */
    public String m3538a(int i) {
        String str = "%s-%s";
        Object[] objArr = new Object[2];
        objArr[0] = this.f3211c;
        objArr[1] = i == 1 ? AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB : "native";
        return String.format(str, objArr);
    }

    /* renamed from: a */
    public void m3539a(String str) {
        if (C1410i.f2926c != null) {
            switch (this.f3209a) {
                case 0:
                    C1410i.f2926c.didClickInterstitial(str);
                    return;
                case 1:
                    C1410i.f2926c.didClickRewardedVideo(str);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: b */
    public void m3541b(String str) {
        if (C1410i.f2926c != null) {
            switch (this.f3209a) {
                case 0:
                    C1410i.f2926c.didCloseInterstitial(str);
                    return;
                case 1:
                    C1410i.f2926c.didCloseRewardedVideo(str);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: c */
    public void m3542c(String str) {
        if (C1410i.f2926c != null) {
            switch (this.f3209a) {
                case 0:
                    C1410i.f2926c.didDismissInterstitial(str);
                    return;
                case 1:
                    C1410i.f2926c.didDismissRewardedVideo(str);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: d */
    public void m3543d(String str) {
        if (C1410i.f2926c != null) {
            switch (this.f3209a) {
                case 0:
                    C1410i.f2926c.didCacheInterstitial(str);
                    return;
                case 1:
                    C1410i.f2926c.didCacheRewardedVideo(str);
                    return;
                case 2:
                    C1410i.f2926c.didCacheInPlay(str);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public void m3540a(String str, CBImpressionError cBImpressionError) {
        if (C1410i.f2926c != null) {
            switch (this.f3209a) {
                case 0:
                    C1410i.f2926c.didFailToLoadInterstitial(str, cBImpressionError);
                    return;
                case 1:
                    C1410i.f2926c.didFailToLoadRewardedVideo(str, cBImpressionError);
                    return;
                case 2:
                    C1410i.f2926c.didFailToLoadInPlay(str, cBImpressionError);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: e */
    public void m3544e(String str) {
        if (C1410i.f2926c != null) {
            switch (this.f3209a) {
                case 0:
                    C1410i.f2926c.didDisplayInterstitial(str);
                    return;
                case 1:
                    C1410i.f2926c.didDisplayRewardedVideo(str);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: f */
    public boolean m3545f(String str) {
        if (C1410i.f2926c == null) {
            return true;
        }
        switch (this.f3209a) {
            case 0:
                return C1410i.f2926c.shouldDisplayInterstitial(str);
            case 1:
                return C1410i.f2926c.shouldDisplayRewardedVideo(str);
            default:
                return true;
        }
    }

    /* renamed from: g */
    public boolean m3546g(String str) {
        if (C1410i.f2926c == null) {
            return true;
        }
        switch (this.f3209a) {
            case 0:
                return C1410i.f2926c.shouldRequestInterstitial(str);
            default:
                return true;
        }
    }
}
