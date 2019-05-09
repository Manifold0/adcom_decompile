package com.facebook.ads.internal.p034c;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import com.facebook.ads.AdError;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAdExtendedListener;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1786a;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p033b.C1943a;
import com.facebook.ads.internal.p033b.C1957f;
import com.facebook.ads.internal.p034c.p035a.C1966b;
import com.facebook.ads.internal.p034c.p035a.C1967c;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.protocol.C2066b;
import com.facebook.ads.internal.protocol.C2069d;
import com.facebook.ads.internal.protocol.C2071f;
import java.util.EnumSet;

/* renamed from: com.facebook.ads.internal.c.d */
public class C1977d implements C1972c {
    /* renamed from: a */
    private static final String f4329a = C1977d.class.getSimpleName();
    /* renamed from: b */
    private C1957f f4330b;
    /* renamed from: c */
    private boolean f4331c;
    /* renamed from: d */
    private boolean f4332d;
    /* renamed from: e */
    private final C1982g f4333e;
    /* renamed from: f */
    private final InterstitialAdExtendedListener f4334f;

    /* renamed from: com.facebook.ads.internal.c.d$1 */
    class C19731 extends C1786a {
        /* renamed from: a */
        final /* synthetic */ C1977d f4325a;

        C19731(C1977d c1977d) {
            this.f4325a = c1977d;
        }
    }

    /* renamed from: com.facebook.ads.internal.c.d$2 */
    class C19752 extends C1786a {
        /* renamed from: a */
        final /* synthetic */ C1977d f4327a;

        /* renamed from: com.facebook.ads.internal.c.d$2$1 */
        class C19741 extends C1786a {
            /* renamed from: a */
            final /* synthetic */ C19752 f4326a;

            C19741(C19752 c19752) {
                this.f4326a = c19752;
            }
        }

        C19752(C1977d c1977d) {
            this.f4327a = c1977d;
        }

        /* renamed from: a */
        public void mo5323a() {
            this.f4327a.f4334f.onAdClicked(this.f4327a.f4333e.m4727a());
        }

        /* renamed from: a */
        public void mo5324a(View view) {
        }

        /* renamed from: a */
        public void mo5325a(AdAdapter adAdapter) {
            this.f4327a.f4331c = true;
            this.f4327a.f4334f.onAdLoaded(this.f4327a.f4333e.m4727a());
        }

        /* renamed from: a */
        public void mo5326a(C2065a c2065a) {
            this.f4327a.f4334f.onError(this.f4327a.f4333e.m4727a(), AdError.getAdErrorFromWrapper(c2065a));
        }

        /* renamed from: b */
        public void mo5327b() {
            this.f4327a.f4334f.onLoggingImpression(this.f4327a.f4333e.m4727a());
        }

        /* renamed from: d */
        public void mo5446d() {
            this.f4327a.f4332d = false;
            if (this.f4327a.f4330b != null) {
                this.f4327a.f4330b.m4609a(new C19741(this));
                this.f4327a.f4330b.m4619f();
                this.f4327a.f4330b = null;
            }
            this.f4327a.f4334f.onInterstitialDismissed(this.f4327a.f4333e.m4727a());
        }

        /* renamed from: e */
        public void mo5447e() {
            this.f4327a.f4334f.onInterstitialDisplayed(this.f4327a.f4333e.m4727a());
        }

        /* renamed from: f */
        public void mo5448f() {
            this.f4327a.f4332d = false;
            this.f4327a.f4334f.onInterstitialActivityDestroyed();
        }
    }

    /* renamed from: com.facebook.ads.internal.c.d$3 */
    class C19763 extends C1786a {
        /* renamed from: a */
        final /* synthetic */ C1977d f4328a;

        C19763(C1977d c1977d) {
            this.f4328a = c1977d;
        }
    }

    public C1977d(C1982g c1982g, C1967c c1967c, String str) {
        this.f4333e = c1982g;
        this.f4334f = new C1966b(str, c1967c, this);
    }

    /* renamed from: a */
    public void mo5449a() {
        if (this.f4330b != null) {
            this.f4330b.m4609a(new C19763(this));
            this.f4330b.m4613a(true);
            this.f4330b = null;
            this.f4331c = false;
            this.f4332d = false;
        }
    }

    /* renamed from: a */
    public void m4692a(EnumSet<CacheFlag> enumSet, @Nullable String str) {
        if (!(this.f4331c || this.f4330b == null)) {
            Log.w(f4329a, "An ad load is already in progress. You should wait for adLoaded() to be called");
        }
        this.f4331c = false;
        if (this.f4332d) {
            C2625a.m6741b(this.f4333e.f4344a, "api", C2626b.f6541f, new C2066b(AdErrorType.NO_ADAPTER_ON_LOAD, "Interstitial load called while showing interstitial."));
            this.f4334f.onError(this.f4333e.m4727a(), new AdError(AdErrorType.LOAD_CALLED_WHILE_SHOWING_AD.getErrorCode(), AdErrorType.LOAD_CALLED_WHILE_SHOWING_AD.getDefaultErrorMessage()));
            return;
        }
        if (this.f4330b != null) {
            this.f4330b.m4609a(new C19731(this));
            this.f4330b.m4619f();
            this.f4330b = null;
        }
        C1943a c1943a = new C1943a(this.f4333e.f4345b, C2071f.m5048a(this.f4333e.f4344a.getResources().getDisplayMetrics()), AdPlacementType.INTERSTITIAL, C2069d.INTERSTITIAL, 1, enumSet);
        c1943a.m4602a(this.f4333e.f4347d);
        this.f4330b = new C1957f(this.f4333e.f4344a, c1943a);
        this.f4330b.m4609a(new C19752(this));
        this.f4330b.m4615b(str);
    }

    /* renamed from: b */
    public long m4693b() {
        return this.f4330b != null ? this.f4330b.m4621h() : -1;
    }

    /* renamed from: c */
    public boolean m4694c() {
        return this.f4330b == null || this.f4330b.m4620g();
    }

    /* renamed from: d */
    public boolean m4695d() {
        return this.f4331c;
    }

    /* renamed from: e */
    public boolean m4696e() {
        if (!this.f4331c) {
            this.f4334f.onError(this.f4333e.m4727a(), AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        } else if (this.f4330b == null) {
            C2625a.m6741b(this.f4333e.f4344a, "api", C2626b.f6542g, new C2066b(AdErrorType.INTERSTITIAL_CONTROLLER_IS_NULL, AdErrorType.INTERSTITIAL_CONTROLLER_IS_NULL.getDefaultErrorMessage()));
            this.f4334f.onError(this.f4333e.m4727a(), AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        } else {
            this.f4330b.m4618e();
            this.f4332d = true;
            this.f4331c = false;
            return true;
        }
    }
}
