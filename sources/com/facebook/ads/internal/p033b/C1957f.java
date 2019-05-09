package com.facebook.ads.internal.p033b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1918g;
import com.facebook.ads.internal.adapters.InterstitialAdapterListener;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p043m.C2044a;
import com.facebook.ads.internal.p043m.C2046c;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.protocol.C2066b;
import com.google.android.gms.drive.DriveFile;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.b.f */
public class C1957f extends C1946c {
    public C1957f(Context context, C1943a c1943a) {
        super(context, c1943a);
    }

    /* renamed from: a */
    protected void mo5419a() {
        ((C1918g) this.f).m4465a();
    }

    /* renamed from: a */
    protected void mo5420a(AdAdapter adAdapter, C2046c c2046c, C2044a c2044a, Map<String, Object> map) {
        final C1918g c1918g = (C1918g) adAdapter;
        final Runnable c19551 = new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C1957f f4285b;

            public void run() {
                this.f4285b.m4607a(c1918g);
                if (C2078a.ac(this.f4285b.b)) {
                    this.f4285b.e = null;
                    this.f4285b.c.mo5326a(new C2065a(AdErrorType.INTERSTITIAL_AD_TIMEOUT, ""));
                    return;
                }
                this.f4285b.m4622i();
            }
        };
        m4623j().postDelayed(c19551, (long) c2046c.m4970a().m4989j());
        c1918g.m4464a(this.b, new InterstitialAdapterListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1957f f4287b;

            public void onInterstitialActivityDestroyed() {
                this.f4287b.c.mo5448f();
            }

            public void onInterstitialAdClicked(C1918g c1918g, String str, boolean z) {
                this.f4287b.c.mo5323a();
                Object obj = !TextUtils.isEmpty(str) ? 1 : null;
                if (z && obj != null) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    if (!(this.f4287b.b instanceof Activity)) {
                        intent.addFlags(DriveFile.MODE_READ_ONLY);
                    }
                    intent.setData(Uri.parse(str));
                    this.f4287b.b.startActivity(intent);
                }
            }

            public void onInterstitialAdDismissed(C1918g c1918g) {
                this.f4287b.c.mo5446d();
            }

            public void onInterstitialAdDisplayed(C1918g c1918g) {
                this.f4287b.c.mo5447e();
            }

            public void onInterstitialAdLoaded(C1918g c1918g) {
                if (c1918g == this.f4287b.e) {
                    if (c1918g == null) {
                        C2625a.m6741b(this.f4287b.b, "api", C2626b.f6537b, new C2066b(AdErrorType.NO_ADAPTER_ON_LOAD, "Adapter is null on loadInterstitialAd"));
                        onInterstitialError(c1918g, AdError.internalError(AdError.INTERNAL_ERROR_2004));
                        return;
                    }
                    this.f4287b.m4623j().removeCallbacks(c19551);
                    this.f4287b.f = c1918g;
                    this.f4287b.c.mo5325a((AdAdapter) c1918g);
                }
            }

            public void onInterstitialError(C1918g c1918g, AdError adError) {
                if (c1918g == this.f4287b.e) {
                    this.f4287b.m4623j().removeCallbacks(c19551);
                    this.f4287b.m4607a((AdAdapter) c1918g);
                    if (!C2078a.ac(this.f4287b.b)) {
                        this.f4287b.m4622i();
                    }
                    this.f4287b.c.mo5326a(new C2065a(adError.getErrorCode(), adError.getErrorMessage()));
                }
            }

            public void onInterstitialLoggingImpression(C1918g c1918g) {
                this.f4287b.c.mo5327b();
            }
        }, map, this.g, this.h.f4250d, this.h.f4251e);
    }
}
