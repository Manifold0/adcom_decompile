// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import java.util.HashMap;
import android.util.Log;
import org.json.JSONException;
import com.facebook.ads.internal.view.i.c.i;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.view.i.c.l;
import com.facebook.ads.internal.view.i.c.k;
import android.text.TextUtils;
import com.facebook.ads.internal.w.b.u;
import android.view.View;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import com.facebook.ads.CacheFlag;
import java.util.EnumSet;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.o.d;
import com.facebook.ads.internal.view.i.b.b;
import com.facebook.ads.internal.view.i.b.e;
import com.facebook.ads.internal.view.i.b.m;
import android.content.Context;
import org.json.JSONObject;
import com.facebook.ads.internal.view.i.a;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.s.c;
import android.os.Bundle;
import com.facebook.ads.internal.w.b.r;

public class f extends n implements r<Bundle>
{
    static final /* synthetic */ boolean e;
    @Nullable
    protected c a;
    @Nullable
    protected a b;
    @Nullable
    protected JSONObject c;
    @Nullable
    protected Context d;
    private final com.facebook.ads.internal.o.f<com.facebook.ads.internal.view.i.b.c> f;
    private final com.facebook.ads.internal.o.f<m> g;
    private final com.facebook.ads.internal.o.f<e> h;
    private final com.facebook.ads.internal.o.f<b> i;
    @Nullable
    private com.facebook.ads.a.a j;
    @Nullable
    private String k;
    private boolean l;
    @Nullable
    private com.facebook.ads.internal.view.i.c m;
    @Nullable
    private String n;
    private boolean o;
    private com.facebook.ads.internal.h.b p;
    
    static {
        e = !f.class.desiredAssertionStatus();
    }
    
    public f() {
        this.f = new com.facebook.ads.internal.o.f<com.facebook.ads.internal.view.i.b.c>() {
            @Override
            public Class<com.facebook.ads.internal.view.i.b.c> a() {
                return com.facebook.ads.internal.view.i.b.c.class;
            }
            
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                if (f.this.j == null) {
                    return;
                }
                f.this.j.d(f.this);
            }
        };
        this.g = new com.facebook.ads.internal.o.f<m>() {
            @Override
            public Class<m> a() {
                return m.class;
            }
            
            @Override
            public void a(final m m) {
                f.this.l = true;
                if (f.this.j != null) {
                    f.this.j.a(f.this);
                }
            }
        };
        this.h = new com.facebook.ads.internal.o.f<e>() {
            @Override
            public Class<e> a() {
                return e.class;
            }
            
            @Override
            public void a(final e e) {
                if (f.this.j == null) {
                    return;
                }
                f.this.j.a(f.this, AdError.internalError(2003));
            }
        };
        this.i = new com.facebook.ads.internal.o.f<b>() {
            @Override
            public Class<b> a() {
                return b.class;
            }
            
            @Override
            public void a(final b b) {
                if (f.this.j != null) {
                    f.this.j.b(f.this);
                }
            }
        };
        this.l = false;
        this.o = false;
    }
    
    private void a(final Context d, final com.facebook.ads.a.a j, final JSONObject c, final c a, @Nullable final Bundle bundle, final EnumSet<CacheFlag> set, final int videoProgressReportIntervalMs) {
        this.d = d;
        this.j = j;
        this.a = a;
        this.c = c;
        this.l = false;
        final JSONObject jsonObject = c.getJSONObject("video");
        this.n = c.optString("ct");
        (this.b = new a(d)).setVideoProgressReportIntervalMs(videoProgressReportIntervalMs);
        this.a();
        this.b.getEventBus().a(this.f, this.g, this.h, this.i);
        final ArrayList<com.facebook.ads.internal.d.b> list = new ArrayList<com.facebook.ads.internal.d.b>();
        list.add(new com.facebook.ads.internal.d.b(1.0E-7, -1.0, 0.001, false) {
            @Override
            protected void a(final boolean b, final boolean b2, final com.facebook.ads.internal.d.c c) {
                com.facebook.ads.internal.adapters.f.this.f();
            }
        });
        if (bundle != null) {
            this.m = new com.facebook.ads.internal.view.i.b(d, a, this.b, list, this.n, bundle.getBundle("logger"), null);
        }
        else {
            this.m = new com.facebook.ads.internal.view.i.b(d, a, this.b, list, this.n);
        }
        this.j.a(this, (View)this.b);
        if (u.a(d) == u.a.c && jsonObject.has("videoHDURL") && !jsonObject.isNull("videoHDURL")) {
            this.k = jsonObject.getString("videoHDURL");
        }
        else {
            this.k = jsonObject.getString("videoURL");
        }
        if (set.contains(CacheFlag.VIDEO)) {
            (this.p = new com.facebook.ads.internal.h.b(d)).a(this.k);
            this.p.a(new com.facebook.ads.internal.h.a() {
                @Override
                public void a() {
                    com.facebook.ads.internal.adapters.f.this.b.setVideoURI(com.facebook.ads.internal.adapters.f.this.h());
                }
                
                @Override
                public void b() {
                    com.facebook.ads.internal.adapters.f.this.b.setVideoURI(com.facebook.ads.internal.adapters.f.this.h());
                }
            });
            return;
        }
        this.b.setVideoURI(this.h());
    }
    
    private String h() {
        String c;
        final String s = c = "";
        if (this.p != null) {
            c = s;
            if (this.k != null) {
                c = this.p.c(this.k);
            }
        }
        String k = c;
        if (TextUtils.isEmpty((CharSequence)c)) {
            k = this.k;
        }
        return k;
    }
    
    protected void a() {
        if (!com.facebook.ads.internal.adapters.f.e && this.d == null) {
            throw new AssertionError();
        }
        if (!com.facebook.ads.internal.adapters.f.e && this.c == null) {
            throw new AssertionError();
        }
        JSONObject optJSONObject = this.c.optJSONObject("text");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        this.b.a(new k(this.d));
        final l l = new l(this.d);
        this.b.a(l);
        this.b.a(new com.facebook.ads.internal.view.i.c.d((View)l, com.facebook.ads.internal.view.i.c.d.a.b));
        this.b.a(new com.facebook.ads.internal.view.i.c.b(this.d));
        final String b = this.b();
        if (b != null) {
            final com.facebook.ads.internal.view.i.c.c c = new com.facebook.ads.internal.view.i.c.c(this.d, b);
            final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-2, -2);
            layoutParams.addRule(12);
            layoutParams.addRule(9);
            c.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            c.setCountdownTextColor(-1);
            this.b.a(c);
        }
        if (this.c.has("cta") && !this.c.isNull("cta")) {
            final JSONObject jsonObject = this.c.getJSONObject("cta");
            final com.facebook.ads.internal.view.i.c.e e = new com.facebook.ads.internal.view.i.c.e(this.d, jsonObject.getString("url"), this.a, this.n, jsonObject.getString("text"));
            final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
            layoutParams2.addRule(10);
            layoutParams2.addRule(11);
            e.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            this.b.a(e);
        }
        final String d = this.d();
        if (!TextUtils.isEmpty((CharSequence)d)) {
            this.b.a(new com.facebook.ads.internal.view.i.c.a(this.d, d, this.n, new float[] { 0.0f, 0.0f, 8.0f, 0.0f }));
        }
        final int c2 = this.c();
        if (c2 > 0) {
            final i i = new i(this.d, c2, optJSONObject.optString("skipAdIn", "Skip Ad in"), optJSONObject.optString("skipAd", "Skip Ad"));
            final RelativeLayout$LayoutParams layoutParams3 = new RelativeLayout$LayoutParams(-2, -2);
            layoutParams3.addRule(12);
            layoutParams3.addRule(11);
            i.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
            i.setPadding(0, 0, 0, 30);
            this.b.a(i);
        }
    }
    
    public void a(final Context context, final com.facebook.ads.a.a a, final c c, final Bundle bundle, final EnumSet<CacheFlag> set) {
        try {
            final JSONObject jsonObject = new JSONObject(bundle.getString("ad_response"));
            this.a(context, a, jsonObject, c, bundle, set, jsonObject.optInt("video_time_polling_interval", 200));
        }
        catch (JSONException ex) {
            a.a(this, AdError.INTERNAL_ERROR);
        }
    }
    
    public void a(final Context context, final com.facebook.ads.a.a a, final Map<String, Object> map, final c c, final EnumSet<CacheFlag> set) {
        try {
            final JSONObject jsonObject = map.get("data");
            final com.facebook.ads.internal.m.d d = (com.facebook.ads.internal.m.d)map.get("definition");
            int k;
            if (d == null) {
                k = 200;
            }
            else {
                k = d.k();
            }
            this.a(context, a, jsonObject, c, null, set, k);
        }
        catch (JSONException ex) {
            a.a(this, AdError.INTERNAL_ERROR);
        }
    }
    
    protected String b() {
        if (!com.facebook.ads.internal.adapters.f.e && this.c == null) {
            throw new AssertionError();
        }
        try {
            final JSONObject jsonObject = this.c.getJSONObject("capabilities");
            if (jsonObject.has("countdown")) {
                if (jsonObject.isNull("countdown")) {
                    return null;
                }
                final JSONObject jsonObject2 = jsonObject.getJSONObject("countdown");
                if (jsonObject2.has("format")) {
                    return jsonObject2.optString("format");
                }
            }
        }
        catch (Exception ex) {
            Log.w(String.valueOf(f.class), "Invalid JSON", (Throwable)ex);
        }
        return null;
    }
    
    protected int c() {
        if (!com.facebook.ads.internal.adapters.f.e && this.c == null) {
            throw new AssertionError();
        }
        try {
            final JSONObject jsonObject = this.c.getJSONObject("capabilities");
            if (jsonObject.has("skipButton")) {
                if (jsonObject.isNull("skipButton")) {
                    return -1;
                }
                final JSONObject jsonObject2 = jsonObject.getJSONObject("skipButton");
                if (jsonObject2.has("skippableSeconds")) {
                    return jsonObject2.getInt("skippableSeconds");
                }
            }
        }
        catch (Exception ex) {
            Log.w(String.valueOf(f.class), "Invalid JSON", (Throwable)ex);
        }
        return -1;
    }
    
    @Nullable
    protected String d() {
        if (!com.facebook.ads.internal.adapters.f.e && this.c == null) {
            throw new AssertionError();
        }
        try {
            final JSONObject jsonObject = this.c.getJSONObject("capabilities");
            if (jsonObject.has("adChoices")) {
                if (jsonObject.isNull("adChoices")) {
                    return null;
                }
                final JSONObject jsonObject2 = jsonObject.getJSONObject("adChoices");
                if (jsonObject2.has("url")) {
                    return jsonObject2.getString("url");
                }
            }
        }
        catch (Exception ex) {
            Log.w(String.valueOf(f.class), "Invalid JSON", (Throwable)ex);
        }
        return null;
    }
    
    @Override
    public boolean e() {
        if (!this.l || this.b == null) {
            return false;
        }
        if (this.m.j() > 0) {
            this.b.a(this.m.j());
        }
        this.b.a(com.facebook.ads.internal.view.i.a.a.c);
        return true;
    }
    
    protected void f() {
        if (this.a != null && !this.o) {
            this.o = true;
            this.a.a(this.n, new HashMap<String, String>());
            if (this.j != null) {
                this.j.c(this);
            }
        }
    }
    
    @Override
    public Bundle g() {
        if (this.m == null || this.c == null || this.b == null || this.b.getState() == com.facebook.ads.internal.view.i.d.d.a) {
            return null;
        }
        final Bundle bundle = new Bundle();
        bundle.putBundle("logger", this.m.g());
        bundle.putString("ad_response", this.c.toString());
        return bundle;
    }
    
    @Override
    public String getClientToken() {
        return this.n;
    }
    
    @Override
    public void onDestroy() {
        if (this.b != null) {
            this.b.g();
            this.b.l();
        }
        this.j = null;
        this.a = null;
        this.k = null;
        this.l = false;
        this.n = null;
        this.b = null;
        this.m = null;
        this.c = null;
        this.d = null;
        this.o = false;
    }
}
