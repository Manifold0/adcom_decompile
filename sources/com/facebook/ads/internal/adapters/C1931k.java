package com.facebook.ads.internal.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.p030b.C1864a;
import com.facebook.ads.internal.adapters.p030b.C1869d;
import com.facebook.ads.internal.adapters.p030b.C1874f;
import com.facebook.ads.internal.adapters.p030b.C1884n;
import com.facebook.ads.internal.adapters.p030b.C1885o;
import com.facebook.ads.internal.adapters.p030b.C1887q;
import com.facebook.ads.internal.adapters.p031c.C1894a;
import com.facebook.ads.internal.adapters.p031c.C1894a.C1891b;
import com.facebook.ads.internal.p024h.C1832a;
import com.facebook.ads.internal.p024h.C2011b;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p043m.C2047d;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.settings.C2094a.C2093a;
import com.facebook.ads.internal.view.p061e.C2330c;
import com.google.android.gms.drive.DriveFile;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.k */
public class C1931k extends C1930s {
    /* renamed from: c */
    private final String f4183c = UUID.randomUUID().toString();
    /* renamed from: d */
    private final AtomicBoolean f4184d = new AtomicBoolean();
    /* renamed from: e */
    private Context f4185e;
    /* renamed from: f */
    private C1940t f4186f;
    /* renamed from: g */
    private String f4187g;
    /* renamed from: h */
    private String f4188h;
    /* renamed from: i */
    private long f4189i;
    /* renamed from: j */
    private C1864a f4190j;
    /* renamed from: k */
    private C1941u f4191k;
    /* renamed from: l */
    private C2093a f4192l;
    /* renamed from: m */
    private String f4193m;
    /* renamed from: n */
    private C1929a f4194n;

    /* renamed from: com.facebook.ads.internal.adapters.k$b */
    private static abstract class C1926b implements C1832a {
        /* renamed from: d */
        final WeakReference<C1931k> f4168d;
        /* renamed from: e */
        final WeakReference<C1940t> f4169e;
        /* renamed from: f */
        final C2011b f4170f;
        /* renamed from: g */
        final AtomicBoolean f4171g;
        /* renamed from: h */
        final boolean f4172h;

        private C1926b(C1931k c1931k, C1940t c1940t, C2011b c2011b, AtomicBoolean atomicBoolean, boolean z) {
            this.f4168d = new WeakReference(c1931k);
            this.f4169e = new WeakReference(c1940t);
            this.f4170f = c2011b;
            this.f4171g = atomicBoolean;
            this.f4172h = z;
        }

        /* renamed from: a */
        public void mo5368a() {
            mo5411a(true, (C1931k) this.f4168d.get(), (C1940t) this.f4169e.get());
        }

        /* renamed from: a */
        abstract void mo5411a(boolean z, @Nullable C1931k c1931k, @Nullable C1940t c1940t);

        /* renamed from: b */
        public void mo5369b() {
            if (this.f4169e.get() != null && this.f4168d.get() != null) {
                if (this.f4172h) {
                    ((C1940t) this.f4169e.get()).mo5432a((C1930s) this.f4168d.get(), AdError.CACHE_ERROR);
                } else {
                    mo5411a(false, (C1931k) this.f4168d.get(), (C1940t) this.f4169e.get());
                }
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.adapters.k$a */
    private static class C1929a implements C1891b {
        /* renamed from: a */
        final WeakReference<C1931k> f4178a;
        /* renamed from: b */
        final WeakReference<C1940t> f4179b;
        /* renamed from: c */
        final AtomicBoolean f4180c;

        C1929a(C1931k c1931k, C1940t c1940t, AtomicBoolean atomicBoolean) {
            this.f4178a = new WeakReference(c1931k);
            this.f4179b = new WeakReference(c1940t);
            this.f4180c = atomicBoolean;
        }

        /* renamed from: a */
        public void mo5399a() {
            this.f4180c.set(true);
            if (this.f4179b.get() != null && this.f4178a.get() != null) {
                ((C1940t) this.f4179b.get()).mo5431a((C1930s) this.f4178a.get());
            }
        }

        /* renamed from: a */
        public void mo5400a(AdError adError) {
            if (this.f4179b.get() != null && this.f4178a.get() != null) {
                ((C1940t) this.f4179b.get()).mo5432a((C1930s) this.f4178a.get(), adError);
            }
        }
    }

    /* renamed from: a */
    private void m4539a(Context context, boolean z, C1887q c1887q) {
        this.f4194n = new C1929a(this, this.f4186f, this.f4184d);
        C1894a.m4379a(context, C1885o.m4346a(c1887q), z, this.f4194n);
    }

    /* renamed from: a */
    private void m4541a(C2011b c2011b, C1887q c1887q) {
        c2011b.m4844a(c1887q.m4362f().m4329b(), C2330c.f5589a, C2330c.f5589a);
        c2011b.m4843a(c1887q.m4366j().m4238a());
        String g = c1887q.m4366j().m4245g();
        Context context = this.f4185e;
        C1869d j = c1887q.m4366j();
        int min = C2078a.m5080S(context) ? Math.min(C2600x.f6419a.heightPixels, j.m4247i()) : j.m4247i();
        Context context2 = this.f4185e;
        C1869d j2 = c1887q.m4366j();
        c2011b.m4844a(g, min, C2078a.m5080S(context2) ? Math.min(C2600x.f6419a.widthPixels, j2.m4246h()) : j2.m4246h());
        for (String a : c1887q.m4367k().m4301d()) {
            c2011b.m4844a(a, -1, -1);
        }
    }

    /* renamed from: b */
    private static boolean m4544b(C1887q c1887q, boolean z) {
        C1884n j = c1887q.m4366j().m4248j();
        return (j == null || (z && j.m4342i())) ? false : true;
    }

    /* renamed from: a */
    public int mo5412a() {
        if (this.f4190j == null) {
            return -1;
        }
        if (this.f4192l != C2093a.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD) {
            return ((C1887q) this.f4190j).m4366j().m4242d();
        }
        int i = 0;
        for (C1887q j : ((C1874f) this.f4190j).m4281j()) {
            int d = j.m4366j().m4242d();
            if (i >= d) {
                d = i;
            }
            i = d;
        }
        return i;
    }

    /* renamed from: a */
    public void m4546a(Context context, C1940t c1940t, Map<String, Object> map, boolean z, String str) {
        this.f4185e = context;
        this.f4186f = c1940t;
        this.f4184d.set(false);
        this.f4188h = (String) map.get("placementId");
        this.f4189i = ((Long) map.get(AudienceNetworkActivity.REQUEST_TIME)).longValue();
        int k = ((C2047d) map.get("definition")).m4990k();
        this.f4193m = str;
        this.f4187g = this.f4188h != null ? this.f4188h.split("_")[0] : "";
        boolean equals = "choose_your_own_ad_rewarded_video".equals(map.get("data_model_type"));
        this.f4190j = C1864a.m4204a(equals, (JSONObject) map.get("data"));
        C2093a c2093a = equals ? C2093a.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD : C1931k.m4544b((C1887q) this.f4190j, true) ? C2093a.REWARDED_PLAYABLE : C2093a.REWARDED_VIDEO;
        this.f4192l = c2093a;
        this.f4190j.mo5385b(this.f4193m);
        this.f4190j.m4206a(k);
        if (this.f4192l == C2093a.REWARDED_VIDEO && TextUtils.isEmpty(((C1887q) this.f4190j).m4366j().m4238a())) {
            this.f4186f.mo5432a(this, AdError.internalError(AdError.INTERNAL_ERROR_2003));
            return;
        }
        this.f4191k = new C1941u(this.f4183c, this, c1940t);
        LocalBroadcastManager.getInstance(this.f4185e).registerReceiver(this.f4191k, this.f4191k.m4595a());
        C2011b c2011b;
        if (this.f4192l == C2093a.REWARDED_VIDEO) {
            c2011b = new C2011b(context);
            final C1887q c1887q = (C1887q) this.f4190j;
            m4541a(c2011b, c1887q);
            final boolean z2 = z;
            c2011b.m4842a(new C1926b(this, this, this.f4186f, c2011b, this.f4184d, z) {
                /* renamed from: c */
                final /* synthetic */ C1931k f4175c;

                /* renamed from: a */
                void mo5411a(boolean z, @Nullable C1931k c1931k, @Nullable C1940t c1940t) {
                    if (c1931k != null && c1940t != null) {
                        this.g.set(true);
                        c1887q.m4360c(z ? this.f.m4846c(c1887q.m4366j().m4238a()) : c1887q.m4366j().m4238a());
                        if (C1931k.m4544b((C1887q) this.f4175c.f4190j, false)) {
                            this.f4175c.m4539a(this.f4175c.f4185e, z2, c1887q);
                        } else {
                            c1940t.mo5431a(c1931k);
                        }
                    }
                }
            });
        } else if (this.f4192l == C2093a.REWARDED_PLAYABLE) {
            m4539a(context, z, (C1887q) this.f4190j);
        } else {
            c2011b = new C2011b(context);
            final C1874f c1874f = (C1874f) this.f4190j;
            for (C1887q a : c1874f.m4281j()) {
                m4541a(c2011b, a);
            }
            c2011b.m4842a(new C1926b(this, this, this.f4186f, c2011b, this.f4184d, z) {
                /* renamed from: b */
                final /* synthetic */ C1931k f4177b;

                /* renamed from: a */
                void mo5411a(boolean z, @Nullable C1931k c1931k, @Nullable C1940t c1940t) {
                    if (c1931k != null && c1940t != null) {
                        this.g.set(true);
                        for (C1887q c1887q : c1874f.m4281j()) {
                            c1887q.m4360c(z ? this.f.m4846c(c1887q.m4366j().m4238a()) : c1887q.m4366j().m4238a());
                            if (C1931k.m4544b(c1887q, false)) {
                            }
                        }
                        c1940t.mo5431a(c1931k);
                    }
                }
            });
        }
    }

    /* renamed from: b */
    public boolean mo5413b() {
        if (!this.f4184d.get()) {
            return false;
        }
        String urlPrefix;
        if (this.a != null) {
            urlPrefix = AdSettings.getUrlPrefix();
            if (urlPrefix == null || urlPrefix.isEmpty()) {
                urlPrefix = "https://www.facebook.com/audience_network/server_side_reward";
            } else {
                urlPrefix = String.format(Locale.US, "https://www.%s.facebook.com/audience_network/server_side_reward", new Object[]{urlPrefix});
            }
            Uri parse = Uri.parse(urlPrefix);
            Builder builder = new Builder();
            builder.scheme(parse.getScheme());
            builder.authority(parse.getAuthority());
            builder.path(parse.getPath());
            builder.query(parse.getQuery());
            builder.fragment(parse.getFragment());
            builder.appendQueryParameter("puid", this.a.getUserID());
            builder.appendQueryParameter("pc", this.a.getCurrency());
            builder.appendQueryParameter("ptid", this.f4183c);
            builder.appendQueryParameter("appid", this.f4187g);
            urlPrefix = builder.build().toString();
        } else {
            urlPrefix = null;
        }
        this.f4190j.mo5386a(urlPrefix);
        Intent intent = new Intent(this.f4185e, AudienceNetworkActivity.getAdActivity());
        intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, this.f4192l);
        intent.putExtra("rewardedVideoAdDataBundle", this.f4190j);
        intent.putExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.f4183c);
        intent.putExtra(AudienceNetworkActivity.REWARD_SERVER_URL, urlPrefix);
        intent.putExtra("placementId", this.f4188h);
        intent.putExtra(AudienceNetworkActivity.REQUEST_TIME, this.f4189i);
        if (this.b != -1 && System.getInt(this.f4185e.getContentResolver(), "accelerometer_rotation", 0) != 1) {
            intent.putExtra(AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY, this.b);
        } else if (!C2078a.m5115y(this.f4185e)) {
            intent.putExtra(AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY, 6);
        }
        if (!(this.f4185e instanceof Activity)) {
            intent.setFlags(intent.getFlags() | DriveFile.MODE_READ_ONLY);
        }
        this.f4185e.startActivity(intent);
        return true;
    }

    public String getClientToken() {
        return this.f4190j.mo5384a();
    }

    public void onDestroy() {
        if (this.f4191k != null) {
            try {
                LocalBroadcastManager.getInstance(this.f4185e).unregisterReceiver(this.f4191k);
            } catch (Exception e) {
            }
        }
    }
}
