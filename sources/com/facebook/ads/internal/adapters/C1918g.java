package com.facebook.ads.internal.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.internal.adapters.p030b.C1869d;
import com.facebook.ads.internal.adapters.p030b.C1879k;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.adapters.p030b.C1884n;
import com.facebook.ads.internal.adapters.p030b.C1885o;
import com.facebook.ads.internal.adapters.p031c.C1894a;
import com.facebook.ads.internal.adapters.p031c.C1894a.C1891b;
import com.facebook.ads.internal.p024h.C1832a;
import com.facebook.ads.internal.p024h.C2011b;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p027a.C1846e;
import com.facebook.ads.internal.p043m.C2047d;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.settings.C2094a.C2093a;
import com.facebook.ads.internal.view.C1921a;
import com.facebook.ads.internal.view.p019c.C2253f;
import com.facebook.ads.internal.view.p061e.C2330c;
import com.facebook.ads.p018a.C1800a;
import com.google.android.gms.drive.DriveFile;
import com.tapjoy.TapjoyConstants;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.g */
public class C1918g implements AdAdapter {
    /* renamed from: a */
    private static final ConcurrentMap<String, C1921a> f4087a = new ConcurrentHashMap();
    /* renamed from: b */
    private final String f4088b = UUID.randomUUID().toString();
    /* renamed from: c */
    private String f4089c;
    /* renamed from: d */
    private long f4090d;
    /* renamed from: e */
    private Context f4091e;
    /* renamed from: f */
    private C1935o f4092f;
    /* renamed from: g */
    private InterstitialAdapterListener f4093g;
    /* renamed from: h */
    private C1891b f4094h;
    /* renamed from: i */
    private boolean f4095i;
    /* renamed from: j */
    private C1932l f4096j;
    /* renamed from: k */
    private C2253f f4097k = C2253f.UNSPECIFIED;
    /* renamed from: l */
    private C1879k f4098l;
    /* renamed from: m */
    private C2093a f4099m;
    /* renamed from: n */
    private boolean f4100n;
    /* renamed from: o */
    private String f4101o;
    /* renamed from: p */
    private String f4102p;

    /* renamed from: com.facebook.ads.internal.adapters.g$5 */
    class C19175 implements C1832a {
        /* renamed from: a */
        final /* synthetic */ C1918g f4086a;

        C19175(C1918g c1918g) {
            this.f4086a = c1918g;
        }

        /* renamed from: a */
        private void m4450a(boolean z) {
            if (z || !C2078a.m5081T(this.f4086a.f4091e)) {
                this.f4086a.f4095i = true;
                this.f4086a.f4093g.onInterstitialAdLoaded(this.f4086a);
                return;
            }
            this.f4086a.f4093g.onInterstitialError(this.f4086a, AdError.CACHE_ERROR);
        }

        /* renamed from: a */
        public void mo5368a() {
            m4450a(true);
        }

        /* renamed from: b */
        public void mo5369b() {
            if (C2078a.m5079R(this.f4086a.f4091e)) {
                C2625a.m6741b(this.f4086a.f4091e, "cache", C2626b.f6530U, new Exception("Interstitial image cache failed"));
            }
            m4450a(false);
        }
    }

    /* renamed from: a */
    private static int m4453a(Context context, C1869d c1869d) {
        return C2078a.m5080S(context) ? Math.min(C2600x.f6419a.widthPixels, c1869d.m4246h()) : c1869d.m4246h();
    }

    /* renamed from: a */
    public static C1921a m4455a(String str) {
        return (C1921a) f4087a.get(str);
    }

    /* renamed from: a */
    public static void m4457a(C1921a c1921a) {
        for (Entry entry : f4087a.entrySet()) {
            if (entry.getValue() == c1921a) {
                f4087a.remove(entry.getKey());
            }
        }
    }

    /* renamed from: b */
    private static int m4460b(Context context, C1869d c1869d) {
        return C2078a.m5080S(context) ? Math.min(C2600x.f6419a.heightPixels, c1869d.m4247i()) : c1869d.m4247i();
    }

    /* renamed from: a */
    public void m4464a(Context context, InterstitialAdapterListener interstitialAdapterListener, Map<String, Object> map, C2085c c2085c, final EnumSet<CacheFlag> enumSet, String str) {
        this.f4091e = context;
        this.f4093g = interstitialAdapterListener;
        this.f4089c = (String) map.get("placementId");
        this.f4090d = ((Long) map.get(AudienceNetworkActivity.REQUEST_TIME)).longValue();
        this.f4101o = str;
        JSONObject jSONObject = (JSONObject) map.get("data");
        this.f4102p = jSONObject.optString("ct");
        C2047d c2047d = (C2047d) map.get("definition");
        if (jSONObject.has("markup")) {
            this.f4099m = C2093a.INTERSTITIAL_WEB_VIEW;
            this.f4096j = C1932l.m4549a(jSONObject);
            if (C1846e.m4149a(this.f4091e, this.f4096j, c2085c)) {
                interstitialAdapterListener.onInterstitialError(this, AdError.internalError(AdError.INTERNAL_ERROR_2006));
                return;
            }
            this.f4092f = new C1935o(this.f4091e, this.f4088b, this, this.f4093g);
            this.f4092f.m4566a();
            Map e = this.f4096j.m4556e();
            if (e.containsKey("orientation")) {
                this.f4097k = C2253f.m5778a(Integer.parseInt((String) e.get("orientation")));
            }
            this.f4095i = true;
            if (this.f4093g != null) {
                this.f4093g.onInterstitialAdLoaded(this);
            }
        } else if (jSONObject.has("video")) {
            this.f4099m = C2093a.INTERSTITIAL_OLD_NATIVE_VIDEO;
            this.f4092f = new C1935o(this.f4091e, this.f4088b, this, this.f4093g);
            this.f4092f.m4566a();
            final C1922h c1922h = new C1922h();
            c1922h.m4428a(this.f4091e, new C1800a(this) {
                /* renamed from: b */
                final /* synthetic */ C1918g f4079b;

                /* renamed from: a */
                public void mo5343a(C1911n c1911n) {
                    this.f4079b.f4095i = true;
                    if (this.f4079b.f4093g != null) {
                        this.f4079b.f4093g.onInterstitialAdLoaded(this.f4079b);
                    }
                }

                /* renamed from: a */
                public void mo5344a(C1911n c1911n, View view) {
                    this.f4079b.f4097k = c1922h.m4483i();
                    C1918g.f4087a.put(this.f4079b.f4088b, c1922h);
                }

                /* renamed from: a */
                public void mo5345a(C1911n c1911n, AdError adError) {
                    c1922h.m4484j();
                    this.f4079b.f4093g.onInterstitialError(this.f4079b, adError);
                }

                /* renamed from: b */
                public void mo5346b(C1911n c1911n) {
                    this.f4079b.f4093g.onInterstitialAdClicked(this.f4079b, "", true);
                }

                /* renamed from: c */
                public void mo5347c(C1911n c1911n) {
                    this.f4079b.f4093g.onInterstitialLoggingImpression(this.f4079b);
                }

                /* renamed from: d */
                public void mo5348d(C1911n c1911n) {
                }
            }, (Map) map, c2085c, (EnumSet) enumSet);
        } else {
            this.f4098l = C1879k.m4302a(jSONObject, this.f4091e);
            this.f4098l.m4305a(this.f4101o);
            if (c2047d != null) {
                this.f4098l.m4304a(c2047d.m4990k());
            }
            if (this.f4098l.m4308d().size() == 0) {
                this.f4093g.onInterstitialError(this, AdError.internalError(AdError.INTERNAL_ERROR_2006));
                C2625a.m6741b(this.f4091e, "api", C2626b.f6545j, new Exception("Internal Error 2006 without a valid AdInfo."));
                return;
            }
            this.f4092f = new C1935o(this.f4091e, this.f4088b, this, this.f4093g);
            this.f4092f.m4566a();
            C2011b c2011b;
            if (jSONObject.has("carousel")) {
                this.f4099m = C2093a.INTERSTITIAL_NATIVE_CAROUSEL;
                c2011b = new C2011b(this.f4091e);
                c2011b.m4844a(this.f4098l.m4303a().m4329b(), C2330c.f5589a, C2330c.f5589a);
                List<C1880l> d = this.f4098l.m4308d();
                boolean contains = enumSet.contains(CacheFlag.VIDEO);
                for (C1880l c1880l : d) {
                    c2011b.m4844a(c1880l.m4317c().m4245g(), C1918g.m4460b(this.f4091e, c1880l.m4317c()), C1918g.m4453a(this.f4091e, c1880l.m4317c()));
                    if (contains && !TextUtils.isEmpty(c1880l.m4317c().m4238a())) {
                        c2011b.m4843a(c1880l.m4317c().m4245g());
                    }
                }
                c2011b.m4842a(new C1832a(this) {
                    /* renamed from: b */
                    final /* synthetic */ C1918g f4081b;

                    /* renamed from: a */
                    private void m4441a(boolean z) {
                        boolean z2 = false;
                        boolean z3 = !enumSet.contains(CacheFlag.NONE);
                        if (z || !C2078a.m5081T(this.f4081b.f4091e)) {
                            C1918g c1918g = this.f4081b;
                            if (z && z3) {
                                z2 = true;
                            }
                            c1918g.f4100n = z2;
                            this.f4081b.f4095i = true;
                            this.f4081b.f4093g.onInterstitialAdLoaded(this.f4081b);
                            return;
                        }
                        this.f4081b.f4093g.onInterstitialError(this.f4081b, AdError.CACHE_ERROR);
                    }

                    /* renamed from: a */
                    public void mo5368a() {
                        m4441a(true);
                    }

                    /* renamed from: b */
                    public void mo5369b() {
                        if (C2078a.m5079R(this.f4081b.f4091e)) {
                            C2625a.m6741b(this.f4081b.f4091e, "cache", C2626b.f6529T, new Exception("Interstitial carousel cache failed"));
                        }
                        m4441a(false);
                    }
                });
            } else if (jSONObject.has("playable_data")) {
                this.f4099m = C2093a.INTERSTITIAL_NATIVE_PLAYABLE;
                C1885o a = C1885o.m4345a(this.f4098l);
                C1884n j = a.m4352f().m4248j();
                this.f4097k = j != null ? j.m4339f() : C2253f.UNSPECIFIED;
                this.f4094h = new C1891b(this) {
                    /* renamed from: b */
                    final /* synthetic */ C1918g f4083b;

                    /* renamed from: b */
                    private void m4444b() {
                        this.f4083b.f4095i = true;
                        this.f4083b.f4093g.onInterstitialAdLoaded(this.f4083b);
                    }

                    /* renamed from: a */
                    public void mo5399a() {
                        this.f4083b.f4100n = !enumSet.contains(CacheFlag.NONE);
                        m4444b();
                    }

                    /* renamed from: a */
                    public void mo5400a(AdError adError) {
                        if (C2078a.m5081T(this.f4083b.f4091e)) {
                            this.f4083b.f4093g.onInterstitialError(this.f4083b, AdError.CACHE_ERROR);
                        } else {
                            m4444b();
                        }
                    }
                };
                C1894a.m4379a(this.f4091e, a, C2078a.m5081T(this.f4091e), this.f4094h);
            } else if (jSONObject.has(TapjoyConstants.TJC_VIDEO_URL)) {
                this.f4099m = C2093a.INTERSTITIAL_NATIVE_VIDEO;
                c2011b = new C2011b(this.f4091e);
                r0 = ((C1880l) this.f4098l.m4308d().get(0)).m4317c();
                c2011b.m4844a(r0.m4245g(), C1918g.m4460b(this.f4091e, r0), C1918g.m4453a(this.f4091e, r0));
                c2011b.m4844a(this.f4098l.m4303a().m4329b(), C2330c.f5589a, C2330c.f5589a);
                if (enumSet.contains(CacheFlag.VIDEO)) {
                    c2011b.m4843a(r0.m4238a());
                }
                c2011b.m4842a(new C1832a(this) {
                    /* renamed from: b */
                    final /* synthetic */ C1918g f4085b;

                    /* renamed from: a */
                    private void m4447a(boolean z) {
                        if (z || !C2078a.m5081T(this.f4085b.f4091e)) {
                            C1918g c1918g = this.f4085b;
                            boolean z2 = z && enumSet.contains(CacheFlag.VIDEO);
                            c1918g.f4100n = z2;
                            this.f4085b.f4095i = true;
                            this.f4085b.f4093g.onInterstitialAdLoaded(this.f4085b);
                            return;
                        }
                        this.f4085b.f4093g.onInterstitialError(this.f4085b, AdError.CACHE_ERROR);
                    }

                    /* renamed from: a */
                    public void mo5368a() {
                        m4447a(true);
                    }

                    /* renamed from: b */
                    public void mo5369b() {
                        if (C2078a.m5079R(this.f4085b.f4091e)) {
                            C2625a.m6741b(this.f4085b.f4091e, "cache", C2626b.f6531V, new Exception("Interstitial video cache failed"));
                        }
                        m4447a(false);
                    }
                });
            } else {
                this.f4099m = C2093a.INTERSTITIAL_NATIVE_IMAGE;
                c2011b = new C2011b(this.f4091e);
                r0 = ((C1880l) this.f4098l.m4308d().get(0)).m4317c();
                c2011b.m4844a(r0.m4245g(), C1918g.m4460b(this.f4091e, r0), C1918g.m4453a(this.f4091e, r0));
                c2011b.m4844a(this.f4098l.m4303a().m4329b(), C2330c.f5589a, C2330c.f5589a);
                c2011b.m4842a(new C19175(this));
            }
        }
    }

    /* renamed from: a */
    public boolean m4465a() {
        if (this.f4095i) {
            Intent intent = new Intent(this.f4091e, AudienceNetworkActivity.getAdActivity());
            String str = AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY;
            int rotation = ((WindowManager) this.f4091e.getSystemService("window")).getDefaultDisplay().getRotation();
            if (this.f4097k != C2253f.UNSPECIFIED) {
                if (this.f4097k != C2253f.LANDSCAPE) {
                    switch (rotation) {
                        case 2:
                            rotation = 9;
                            break;
                        default:
                            rotation = 1;
                            break;
                    }
                }
                switch (rotation) {
                    case 2:
                    case 3:
                        rotation = 8;
                        break;
                    default:
                        rotation = 0;
                        break;
                }
            }
            rotation = -1;
            intent.putExtra(str, rotation);
            intent.putExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.f4088b);
            intent.putExtra("placementId", this.f4089c);
            intent.putExtra(AudienceNetworkActivity.REQUEST_TIME, this.f4090d);
            intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, this.f4099m);
            intent.putExtra(AudienceNetworkActivity.USE_CACHE, this.f4100n);
            if (this.f4098l != null) {
                intent.putExtra("ad_data_bundle", this.f4098l);
            } else if (this.f4096j != null) {
                this.f4096j.m4552a(intent);
            }
            intent.addFlags(DriveFile.MODE_READ_ONLY);
            try {
                this.f4091e.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                intent.setClass(this.f4091e, InterstitialAdActivity.class);
                this.f4091e.startActivity(intent);
            }
            return true;
        } else if (this.f4093g == null) {
            return false;
        } else {
            this.f4093g.onInterstitialError(this, AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        }
    }

    @Nullable
    public String getClientToken() {
        return this.f4102p;
    }

    public final AdPlacementType getPlacementType() {
        return AdPlacementType.INTERSTITIAL;
    }

    public void onDestroy() {
        if (this.f4092f != null) {
            this.f4092f.m4567b();
        }
    }
}
