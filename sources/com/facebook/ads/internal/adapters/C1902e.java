package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.p025w.p057e.C2610b;
import com.facebook.ads.internal.p027a.C1842b;
import com.facebook.ads.internal.p027a.C1843c;
import com.facebook.ads.internal.p027a.C1846e;
import com.facebook.ads.internal.p043m.C2047d;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2069d;
import com.facebook.ads.internal.view.p019c.C2248a;
import com.facebook.ads.internal.view.p019c.C2248a.C1898b;
import com.facebook.ads.internal.view.p019c.C2248a.C1899c;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.e */
public class C1902e implements AdAdapter {
    /* renamed from: a */
    private static final String f4043a = C1902e.class.getSimpleName();
    @Nullable
    /* renamed from: b */
    private C1898b f4044b;
    @Nullable
    /* renamed from: c */
    private C2248a f4045c;
    /* renamed from: d */
    private C1934m f4046d;
    /* renamed from: e */
    private BannerAdapterListener f4047e;
    /* renamed from: f */
    private Map<String, Object> f4048f;
    @Nullable
    /* renamed from: g */
    private C2085c f4049g;
    /* renamed from: h */
    private String f4050h;
    /* renamed from: i */
    private Context f4051i;

    /* renamed from: com.facebook.ads.internal.adapters.e$2 */
    class C19012 extends C1895c {
        /* renamed from: a */
        final /* synthetic */ C1902e f4042a;

        C19012(C1902e c1902e) {
            this.f4042a = c1902e;
        }

        /* renamed from: a */
        public void mo5392a() {
            if (this.f4042a.f4047e != null) {
                this.f4042a.f4047e.onBannerLoggingImpression(this.f4042a);
            }
        }
    }

    /* renamed from: a */
    public void m4402a(Context context, C2085c c2085c, C2069d c2069d, BannerAdapterListener bannerAdapterListener, Map<String, Object> map) {
        this.f4051i = context;
        this.f4049g = c2085c;
        this.f4047e = bannerAdapterListener;
        this.f4048f = map;
        C2047d c2047d = (C2047d) this.f4048f.get("definition");
        final C1932l a = C1932l.m4549a((JSONObject) this.f4048f.get("data"));
        this.f4050h = a.getClientToken();
        if (C1846e.m4149a(this.f4051i, a, this.f4049g)) {
            this.f4047e.onBannerError(this, AdError.internalError(AdError.INTERNAL_ERROR_2006));
            return;
        }
        this.f4044b = new C1899c(this) {
            /* renamed from: b */
            final /* synthetic */ C1902e f4041b;

            /* renamed from: a */
            public void mo5387a() {
                this.f4041b.f4046d.m4565b();
            }

            /* renamed from: a */
            public void mo5390a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if ("fbad".equals(parse.getScheme()) && C1843c.m4144a(parse.getAuthority()) && this.f4041b.f4047e != null) {
                    this.f4041b.f4047e.onBannerAdClicked(this.f4041b);
                }
                C1842b a = C1843c.m4142a(this.f4041b.f4051i, this.f4041b.f4049g, a.getClientToken(), parse, map);
                if (a != null) {
                    try {
                        a.mo5376a();
                    } catch (Throwable e) {
                        Log.e(C1902e.f4043a, "Error executing action", e);
                    }
                }
            }

            /* renamed from: b */
            public void mo5391b() {
                if (this.f4041b.f4046d != null) {
                    this.f4041b.f4046d.m4369a();
                }
            }
        };
        this.f4045c = new C2248a(this.f4051i, new WeakReference(this.f4044b), c2047d.m4985f());
        this.f4045c.m5754a(c2047d.m4987h(), c2047d.m4988i());
        this.f4046d = new C1934m(this.f4051i, this.f4049g, this.f4045c, this.f4045c.getViewabilityChecker(), new C19012(this));
        this.f4046d.m4563a(a);
        this.f4045c.loadDataWithBaseURL(C2610b.m6707a(), a.m4554c(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
        if (this.f4047e != null) {
            this.f4047e.onBannerAdLoaded(this, this.f4045c);
        }
    }

    public String getClientToken() {
        return this.f4050h;
    }

    public final AdPlacementType getPlacementType() {
        return AdPlacementType.BANNER;
    }

    public void onDestroy() {
        if (this.f4045c != null) {
            this.f4045c.destroy();
            this.f4045c = null;
            this.f4044b = null;
        }
    }
}
