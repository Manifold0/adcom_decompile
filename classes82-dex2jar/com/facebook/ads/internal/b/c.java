// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.b;

import android.text.TextUtils;
import android.security.NetworkSecurityPolicy;
import java.util.EnumSet;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.protocol.g;
import com.facebook.ads.internal.u.f;
import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;
import com.facebook.ads.internal.protocol.AdErrorType;
import android.util.Log;
import android.webkit.CookieSyncManager;
import android.os.Build$VERSION;
import android.webkit.CookieManager;
import android.os.Looper;
import com.facebook.ads.internal.u.b;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.adapters.AdAdapter;
import android.view.View;
import com.facebook.ads.internal.adapters.a;
import android.content.Context;
import com.facebook.ads.internal.adapters.d;
import android.annotation.SuppressLint;
import android.os.Handler;

public abstract class c implements b
{
    private static final String i;
    private static final Handler j;
    @SuppressLint({ "StaticFieldLeak" })
    private static com.facebook.ads.internal.u.c k;
    private static d l;
    volatile boolean a;
    protected final Context b;
    protected com.facebook.ads.internal.adapters.a c;
    View d;
    @Nullable
    AdAdapter e;
    public AdAdapter f;
    public final com.facebook.ads.internal.s.c g;
    public final com.facebook.ads.internal.b.a h;
    private final com.facebook.ads.internal.u.c m;
    private final d n;
    private com.facebook.ads.internal.m.c o;
    private com.facebook.ads.internal.u.b p;
    
    static {
        com.facebook.ads.internal.w.b.c.a();
        i = c.class.getSimpleName();
        j = new Handler(Looper.getMainLooper());
    }
    
    public c(final Context context, final com.facebook.ads.internal.b.a h) {
        this.b = context.getApplicationContext();
        this.h = h;
        Label_0090: {
            if (com.facebook.ads.internal.b.c.k == null) {
                break Label_0090;
            }
            this.m = com.facebook.ads.internal.b.c.k;
            Label_0051_Outer:Label_0071_Outer:
            while (true) {
                this.m.a((b)this);
                Label_0108: {
                    if (com.facebook.ads.internal.b.c.l == null) {
                        break Label_0108;
                    }
                    this.n = com.facebook.ads.internal.b.c.l;
                    while (true) {
                        try {
                            while (true) {
                                CookieManager.getInstance();
                                if (Build$VERSION.SDK_INT < 21) {
                                    CookieSyncManager.createInstance(this.b);
                                }
                                com.facebook.ads.internal.n.a.b(this.b);
                                this.g = com.facebook.ads.internal.s.d.a(this.b);
                                return;
                                this.m = new com.facebook.ads.internal.u.c(this.b);
                                continue Label_0051_Outer;
                                this.n = new d();
                                continue Label_0071_Outer;
                            }
                        }
                        catch (Exception ex) {
                            Log.w(com.facebook.ads.internal.b.c.i, "Failed to initialize CookieManager.", (Throwable)ex);
                            continue;
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
    
    static /* synthetic */ void a(final c c) {
        c.e = null;
        final com.facebook.ads.internal.m.c o = c.o;
        final com.facebook.ads.internal.m.a e = o.e();
        if (e == null) {
            c.c.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NO_FILL, ""));
            return;
        }
        final String a = e.a();
        final AdAdapter a2 = c.n.a(o.a().b());
        if (a2 == null) {
            Log.e(c.i, "Adapter does not exist: " + a);
            c.i();
            return;
        }
        if (c.h.a() != a2.getPlacementType()) {
            c.c.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.INTERNAL_ERROR, ""));
            return;
        }
        c.e = a2;
        final com.facebook.ads.internal.m.d a3 = o.a();
        final HashMap<String, JSONObject> hashMap = new HashMap<String, JSONObject>();
        hashMap.put("data", e.c());
        hashMap.put("definition", (JSONObject)a3);
        hashMap.put("placementId", (JSONObject)c.h.a);
        hashMap.put("requestTime", (JSONObject)a3.a());
        hashMap.put("data_model_type", (JSONObject)e.b());
        if (c.p == null) {
            c.c.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.UNKNOWN_ERROR, "environment is empty"));
            return;
        }
        c.a(a2, o, e, (Map<String, Object>)hashMap);
    }
    
    protected abstract void a();
    
    void a(final AdAdapter adAdapter) {
        if (adAdapter != null) {
            adAdapter.onDestroy();
        }
    }
    
    protected abstract void a(final AdAdapter p0, final com.facebook.ads.internal.m.c p1, final com.facebook.ads.internal.m.a p2, final Map<String, Object> p3);
    
    public void a(final com.facebook.ads.internal.adapters.a c) {
        this.c = c;
    }
    
    @Override
    public void a(final com.facebook.ads.internal.protocol.a a) {
        synchronized (this) {
            this.j().post((Runnable)new Runnable() {
                @Override
                public void run() {
                    com.facebook.ads.internal.b.c.this.c.a(a);
                }
            });
        }
    }
    
    @Override
    public void a(final f f) {
        synchronized (this) {
            Label_0040: {
                if (!com.facebook.ads.internal.r.a.U(this.b)) {
                    break Label_0040;
                }
                final com.facebook.ads.internal.protocol.a c = this.c();
                if (c == null) {
                    break Label_0040;
                }
                Log.e("FBAudienceNetwork", c.b());
                this.a(c);
                return;
            }
            this.j().post((Runnable)new Runnable() {
                @Override
                public void run() {
                    final com.facebook.ads.internal.m.c a = f.a();
                    if (a == null || a.a() == null) {
                        throw new IllegalStateException("invalid placement in response");
                    }
                    com.facebook.ads.internal.b.c.this.o = a;
                    com.facebook.ads.internal.b.c.this.i();
                }
            });
        }
    }
    
    protected void a(final String s) {
        try {
            this.p = this.h.a(this.b, new g(this.b, s, this.h.a, this.h.b));
            this.m.a(this.p);
        }
        catch (com.facebook.ads.internal.protocol.b b) {
            this.a(com.facebook.ads.internal.protocol.a.a(b));
        }
    }
    
    public void a(final boolean b) {
        if (!b && !this.a) {
            return;
        }
        this.a(this.f);
        this.m.a();
        this.d = null;
        this.a = false;
    }
    
    public com.facebook.ads.internal.m.d b() {
        if (this.o == null) {
            return null;
        }
        return this.o.a();
    }
    
    public void b(final String s) {
        this.a(s);
    }
    
    @Nullable
    com.facebook.ads.internal.protocol.a c() {
        final EnumSet<CacheFlag> d = this.h.d;
        if (d == null || d.contains(CacheFlag.NONE) || this.d()) {
            return null;
        }
        return new com.facebook.ads.internal.protocol.a(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
    }
    
    boolean d() {
        final boolean b = Build$VERSION.SDK_INT < 24 || NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted() || NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted("127.0.0.1");
        if (!b) {
            com.facebook.ads.internal.w.h.a.b(this.b, "cache", com.facebook.ads.internal.w.h.b.W, new Exception("Cleartext http is not allowed."));
        }
        return b;
    }
    
    public void e() {
        if (this.f == null) {
            com.facebook.ads.internal.w.h.a.b(this.b, "api", com.facebook.ads.internal.w.h.b.e, new com.facebook.ads.internal.protocol.b(AdErrorType.NO_ADAPTER_ON_START, "Adapter is null on startAd"));
            this.c.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.INTERNAL_ERROR, AdErrorType.INTERNAL_ERROR.getDefaultErrorMessage()));
            return;
        }
        if (this.a) {
            com.facebook.ads.internal.w.h.a.b(this.b, "api", com.facebook.ads.internal.w.h.b.c, new com.facebook.ads.internal.protocol.b(AdErrorType.AD_ALREADY_STARTED, "ad already started"));
            this.c.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.AD_ALREADY_STARTED, AdErrorType.AD_ALREADY_STARTED.getDefaultErrorMessage()));
            return;
        }
        if (!TextUtils.isEmpty((CharSequence)this.f.getClientToken())) {
            this.g.b(this.f.getClientToken());
        }
        this.a = true;
        this.a();
    }
    
    public void f() {
        this.a(false);
    }
    
    public boolean g() {
        return this.o == null || this.o.g();
    }
    
    public long h() {
        if (this.o != null) {
            return this.o.h();
        }
        return -1L;
    }
    
    void i() {
        synchronized (this) {
            com.facebook.ads.internal.b.c.j.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        com.facebook.ads.internal.b.c.a(com.facebook.ads.internal.b.c.this);
                    }
                    catch (Exception ex) {
                        com.facebook.ads.internal.w.h.a.b(com.facebook.ads.internal.b.c.this.b, "api", com.facebook.ads.internal.w.h.b.q, ex);
                    }
                }
            });
        }
    }
    
    Handler j() {
        return com.facebook.ads.internal.b.c.j;
    }
}
