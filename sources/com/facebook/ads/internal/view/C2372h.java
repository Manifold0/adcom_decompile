package com.facebook.ads.internal.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.C1895c;
import com.facebook.ads.internal.adapters.C1932l;
import com.facebook.ads.internal.adapters.C1934m;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p057e.C2610b;
import com.facebook.ads.internal.p027a.C1842b;
import com.facebook.ads.internal.p027a.C1843c;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.p019c.C2248a;
import com.facebook.ads.internal.view.p019c.C2248a.C1898b;
import com.facebook.ads.internal.view.p019c.C2248a.C1899c;
import com.tapjoy.TJAdUnitConstants.String;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.h */
public class C2372h implements C1921a {
    /* renamed from: a */
    private static final String f5740a = C2372h.class.getSimpleName();
    /* renamed from: b */
    private final C1789a f5741b;
    /* renamed from: c */
    private final C2248a f5742c;
    /* renamed from: d */
    private final C1898b f5743d;
    /* renamed from: e */
    private final C1934m f5744e;
    /* renamed from: f */
    private final C2085c f5745f;
    /* renamed from: g */
    private C1932l f5746g;

    /* renamed from: com.facebook.ads.internal.view.h$2 */
    class C23692 extends C1895c {
        /* renamed from: a */
        final /* synthetic */ C2372h f5736a;

        C23692(C2372h c2372h) {
            this.f5736a = c2372h;
        }

        /* renamed from: a */
        public void mo5392a() {
            this.f5736a.f5741b.mo5335a("com.facebook.ads.interstitial.impression.logged");
        }
    }

    public C2372h(final AudienceNetworkActivity audienceNetworkActivity, final C2085c c2085c, C1789a c1789a) {
        this.f5741b = c1789a;
        this.f5745f = c2085c;
        this.f5743d = new C1899c(this) {
            /* renamed from: c */
            final /* synthetic */ C2372h f5734c;
            /* renamed from: d */
            private long f5735d = 0;

            /* renamed from: a */
            public void mo5387a() {
                this.f5734c.f5744e.m4565b();
            }

            /* renamed from: a */
            public void mo5390a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if ("fbad".equals(parse.getScheme()) && String.CLOSE.equals(parse.getAuthority())) {
                    audienceNetworkActivity.finish();
                    return;
                }
                long j = this.f5735d;
                this.f5735d = System.currentTimeMillis();
                if (this.f5735d - j >= 1000) {
                    if ("fbad".equals(parse.getScheme()) && C1843c.m4144a(parse.getAuthority())) {
                        this.f5734c.f5741b.mo5335a("com.facebook.ads.interstitial.clicked");
                    }
                    C1842b a = C1843c.m4142a(audienceNetworkActivity, c2085c, this.f5734c.f5746g.getClientToken(), parse, map);
                    if (a != null) {
                        try {
                            a.mo5376a();
                        } catch (Throwable e) {
                            Log.e(C2372h.f5740a, "Error executing action", e);
                        }
                    }
                }
            }

            /* renamed from: b */
            public void mo5391b() {
                this.f5734c.f5744e.m4369a();
            }
        };
        this.f5742c = new C2248a(audienceNetworkActivity, new WeakReference(this.f5743d), 1);
        this.f5742c.setLayoutParams(new LayoutParams(-1, -1));
        C1895c c23692 = new C23692(this);
        this.f5744e = new C1934m(audienceNetworkActivity, c2085c, this.f5742c, this.f5742c.getViewabilityChecker(), c23692);
        c1789a.mo5333a(this.f5742c);
    }

    /* renamed from: a */
    public void mo5403a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (bundle == null || !bundle.containsKey("dataModel")) {
            this.f5746g = C1932l.m4550b(intent);
            if (this.f5746g != null) {
                this.f5744e.m4563a(this.f5746g);
                this.f5742c.loadDataWithBaseURL(C2610b.m6707a(), this.f5746g.m4554c(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
                this.f5742c.m5754a(this.f5746g.m4557f(), this.f5746g.m4558g());
                return;
            }
            return;
        }
        this.f5746g = C1932l.m4548a(bundle.getBundle("dataModel"));
        if (this.f5746g != null) {
            this.f5742c.loadDataWithBaseURL(C2610b.m6707a(), this.f5746g.m4554c(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
            this.f5742c.m5754a(this.f5746g.m4557f(), this.f5746g.m4558g());
        }
    }

    /* renamed from: a */
    public void mo5404a(Bundle bundle) {
        if (this.f5746g != null) {
            bundle.putBundle("dataModel", this.f5746g.m4559h());
        }
    }

    public void a_(boolean z) {
        this.f5742c.onPause();
    }

    /* renamed from: b */
    public void mo5406b(boolean z) {
        this.f5742c.onResume();
    }

    public void onDestroy() {
        if (!(this.f5746g == null || TextUtils.isEmpty(this.f5746g.getClientToken()))) {
            Map hashMap = new HashMap();
            this.f5742c.getViewabilityChecker().m6771a(hashMap);
            hashMap.put("touch", C2581k.m6645a(this.f5742c.getTouchData()));
            this.f5745f.mo5483l(this.f5746g.getClientToken(), hashMap);
        }
        C2610b.m6708a(this.f5742c);
        this.f5742c.destroy();
    }

    public void setListener(C1789a c1789a) {
    }
}
