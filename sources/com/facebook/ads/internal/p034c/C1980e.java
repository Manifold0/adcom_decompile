package com.facebook.ads.internal.p034c;

import android.util.Log;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardData;
import com.facebook.ads.S2SRewardedVideoAdExtendedListener;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1786a;
import com.facebook.ads.internal.adapters.C1930s;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p033b.C1943a;
import com.facebook.ads.internal.p033b.C1962h;
import com.facebook.ads.internal.p034c.p035a.C1967c;
import com.facebook.ads.internal.p034c.p035a.C1968d;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.protocol.C2069d;
import com.facebook.ads.internal.protocol.C2070e;

/* renamed from: com.facebook.ads.internal.c.e */
public class C1980e implements C1972c {
    /* renamed from: a */
    private static final String f4337a = C1980e.class.getSimpleName();
    /* renamed from: b */
    private final C1986j f4338b;
    /* renamed from: c */
    private C1962h f4339c;
    /* renamed from: d */
    private boolean f4340d = false;
    /* renamed from: e */
    private final S2SRewardedVideoAdExtendedListener f4341e;

    /* renamed from: com.facebook.ads.internal.c.e$1 */
    class C19781 extends C1786a {
        /* renamed from: a */
        final /* synthetic */ C1980e f4335a;

        C19781(C1980e c1980e) {
            this.f4335a = c1980e;
        }

        /* renamed from: a */
        public void mo5323a() {
            this.f4335a.f4341e.onAdClicked(this.f4335a.f4338b.m4745a());
        }

        /* renamed from: a */
        public void mo5325a(AdAdapter adAdapter) {
            C1930s c1930s = (C1930s) adAdapter;
            if (this.f4335a.f4338b.f4367e != null) {
                c1930s.m4536a(this.f4335a.f4338b.f4367e);
            }
            this.f4335a.f4338b.f4370h = c1930s.mo5412a();
            this.f4335a.f4340d = true;
            this.f4335a.f4341e.onAdLoaded(this.f4335a.f4338b.m4745a());
        }

        /* renamed from: a */
        public void mo5326a(C2065a c2065a) {
            this.f4335a.m4708a(true);
            this.f4335a.f4341e.onError(this.f4335a.f4338b.m4745a(), AdError.getAdErrorFromWrapper(c2065a));
        }

        /* renamed from: b */
        public void mo5327b() {
            this.f4335a.f4341e.onLoggingImpression(this.f4335a.f4338b.m4745a());
        }

        /* renamed from: g */
        public void mo5450g() {
            this.f4335a.f4341e.onRewardedVideoCompleted();
        }

        /* renamed from: h */
        public void mo5451h() {
            this.f4335a.f4341e.onRewardedVideoClosed();
        }

        /* renamed from: i */
        public void mo5452i() {
            this.f4335a.f4341e.onRewardServerFailed();
        }

        /* renamed from: j */
        public void mo5453j() {
            this.f4335a.f4341e.onRewardServerSuccess();
        }

        /* renamed from: k */
        public void mo5454k() {
            this.f4335a.f4341e.onRewardedVideoActivityDestroyed();
        }
    }

    /* renamed from: com.facebook.ads.internal.c.e$2 */
    class C19792 extends C1786a {
        /* renamed from: a */
        final /* synthetic */ C1980e f4336a;

        C19792(C1980e c1980e) {
            this.f4336a = c1980e;
        }
    }

    public C1980e(C1986j c1986j, C1967c c1967c, String str) {
        this.f4338b = c1986j;
        this.f4341e = new C1968d(str, c1967c, this, c1986j);
    }

    /* renamed from: a */
    private void m4708a(boolean z) {
        if (this.f4339c != null) {
            this.f4339c.m4609a(new C19792(this));
            this.f4339c.m4613a(z);
            this.f4339c = null;
        }
    }

    /* renamed from: a */
    public void mo5449a() {
        m4708a(true);
    }

    /* renamed from: a */
    public void m4712a(RewardData rewardData) {
        this.f4338b.f4367e = rewardData;
        if (this.f4340d) {
            this.f4339c.m4660a(rewardData);
        }
    }

    /* renamed from: a */
    public void m4713a(String str, boolean z) {
        try {
            if (!(this.f4340d || this.f4339c == null)) {
                Log.w(f4337a, "An ad load is already in progress. You should wait for adLoaded() to be called");
            }
            m4708a(false);
            this.f4340d = false;
            C1943a c1943a = new C1943a(this.f4338b.f4364b, C2070e.REWARDED_VIDEO, AdPlacementType.REWARDED_VIDEO, C2069d.INTERSTITIAL, 1);
            c1943a.m4603a(z);
            c1943a.m4602a(this.f4338b.f4366d);
            this.f4339c = new C1962h(this.f4338b.f4363a, c1943a);
            this.f4339c.m4609a(new C19781(this));
            this.f4339c.m4615b(str);
        } catch (Throwable e) {
            Log.e(f4337a, "Error loading rewarded video ad", e);
            C2625a.m6741b(this.f4338b.f4363a, "api", C2626b.f6544i, e);
            this.f4341e.onError(this.f4338b.m4745a(), AdError.internalError(AdError.INTERNAL_ERROR_2004));
        }
    }

    /* renamed from: a */
    public boolean m4714a(int i) {
        if (!this.f4340d) {
            this.f4341e.onError(this.f4338b.m4745a(), AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        } else if (this.f4339c != null) {
            this.f4339c.h.m4600a(i);
            this.f4339c.m4618e();
            this.f4340d = false;
            return true;
        } else {
            this.f4340d = false;
            return false;
        }
    }

    /* renamed from: b */
    public long m4715b() {
        return this.f4339c != null ? this.f4339c.m4621h() : -1;
    }

    /* renamed from: c */
    public boolean m4716c() {
        return this.f4339c == null || this.f4339c.m4620g();
    }

    /* renamed from: d */
    public boolean m4717d() {
        return this.f4340d;
    }
}
