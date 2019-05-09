// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.protocol.AdPlacementType;
import android.support.annotation.Nullable;
import java.io.Serializable;
import android.content.ActivityNotFoundException;
import com.facebook.ads.InterstitialAdActivity;
import android.view.WindowManager;
import android.content.Intent;
import com.facebook.ads.AudienceNetworkActivity;
import java.util.List;
import android.text.TextUtils;
import com.facebook.ads.internal.w.h.b;
import android.view.View;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.a.e;
import org.json.JSONObject;
import com.facebook.ads.CacheFlag;
import java.util.EnumSet;
import com.facebook.ads.internal.s.c;
import java.util.Iterator;
import java.util.Map;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.adapters.b.d;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import com.facebook.ads.internal.adapters.b.k;
import com.facebook.ads.internal.view.c.f;
import android.content.Context;
import com.facebook.ads.internal.view.a;
import java.util.concurrent.ConcurrentMap;

public class g implements AdAdapter
{
    private static final ConcurrentMap<String, a> a;
    private final String b;
    private String c;
    private long d;
    private Context e;
    private o f;
    private InterstitialAdapterListener g;
    private com.facebook.ads.internal.adapters.c.a.b h;
    private boolean i;
    private l j;
    private f k;
    private k l;
    private com.facebook.ads.internal.settings.a.a m;
    private boolean n;
    private String o;
    private String p;
    
    static {
        a = new ConcurrentHashMap<String, a>();
    }
    
    public g() {
        this.b = UUID.randomUUID().toString();
        this.k = com.facebook.ads.internal.view.c.f.a;
    }
    
    private static int a(final Context context, final d d) {
        if (com.facebook.ads.internal.r.a.S(context)) {
            return Math.min(x.a.widthPixels, d.h());
        }
        return d.h();
    }
    
    public static a a(final String s) {
        return g.a.get(s);
    }
    
    public static void a(final a a) {
        for (final Map.Entry<Object, Object> entry : g.a.entrySet()) {
            if (entry.getValue() == a) {
                g.a.remove(entry.getKey());
            }
        }
    }
    
    static /* synthetic */ void a(final String s, final a a) {
        g.a.put(s, a);
    }
    
    private static int b(final Context context, final d d) {
        if (com.facebook.ads.internal.r.a.S(context)) {
            return Math.min(x.a.heightPixels, d.i());
        }
        return d.i();
    }
    
    public void a(final Context e, final InterstitialAdapterListener g, final Map<String, Object> map, final c c, final EnumSet<CacheFlag> set, final String o) {
        this.e = e;
        this.g = g;
        this.c = (String)map.get("placementId");
        this.d = (long)map.get("requestTime");
        this.o = o;
        final JSONObject jsonObject = map.get("data");
        this.p = jsonObject.optString("ct");
        final com.facebook.ads.internal.m.d d = map.get("definition");
        if (jsonObject.has("markup")) {
            this.m = com.facebook.ads.internal.settings.a.a.a;
            this.j = com.facebook.ads.internal.adapters.l.a(jsonObject);
            if (e.a(this.e, (e.a)this.j, c)) {
                g.onInterstitialError(this, AdError.internalError(2006));
            }
            else {
                (this.f = new o(this.e, this.b, this, this.g)).a();
                final Map<String, String> e2 = this.j.e();
                if (e2.containsKey("orientation")) {
                    this.k = com.facebook.ads.internal.view.c.f.a(Integer.parseInt(e2.get("orientation")));
                }
                this.i = true;
                if (this.g != null) {
                    this.g.onInterstitialAdLoaded(this);
                }
            }
            return;
        }
        if (jsonObject.has("video")) {
            this.m = com.facebook.ads.internal.settings.a.a.b;
            (this.f = new o(this.e, this.b, this, this.g)).a();
            final h h = new h();
            h.a(this.e, new com.facebook.ads.a.a() {
                @Override
                public void a(final n n) {
                    com.facebook.ads.internal.adapters.g.this.i = true;
                    if (com.facebook.ads.internal.adapters.g.this.g == null) {
                        return;
                    }
                    com.facebook.ads.internal.adapters.g.this.g.onInterstitialAdLoaded(com.facebook.ads.internal.adapters.g.this);
                }
                
                @Override
                public void a(final n n, final View view) {
                    com.facebook.ads.internal.adapters.g.this.k = h.i();
                    com.facebook.ads.internal.adapters.g.a(com.facebook.ads.internal.adapters.g.this.b, h);
                }
                
                @Override
                public void a(final n n, final AdError adError) {
                    h.j();
                    com.facebook.ads.internal.adapters.g.this.g.onInterstitialError(com.facebook.ads.internal.adapters.g.this, adError);
                }
                
                @Override
                public void b(final n n) {
                    com.facebook.ads.internal.adapters.g.this.g.onInterstitialAdClicked(com.facebook.ads.internal.adapters.g.this, "", true);
                }
                
                @Override
                public void c(final n n) {
                    com.facebook.ads.internal.adapters.g.this.g.onInterstitialLoggingImpression(com.facebook.ads.internal.adapters.g.this);
                }
                
                @Override
                public void d(final n n) {
                }
            }, map, c, set);
            return;
        }
        (this.l = com.facebook.ads.internal.adapters.b.k.a(jsonObject, this.e)).a(this.o);
        if (d != null) {
            this.l.a(d.k());
        }
        if (this.l.d().size() == 0) {
            this.g.onInterstitialError(this, AdError.internalError(2006));
            com.facebook.ads.internal.w.h.a.b(this.e, "api", com.facebook.ads.internal.w.h.b.j, new Exception("Internal Error 2006 without a valid AdInfo."));
            return;
        }
        (this.f = new o(this.e, this.b, this, this.g)).a();
        if (jsonObject.has("carousel")) {
            this.m = com.facebook.ads.internal.settings.a.a.e;
            final com.facebook.ads.internal.h.b b = new com.facebook.ads.internal.h.b(this.e);
            b.a(this.l.a().b(), com.facebook.ads.internal.view.e.c.a, com.facebook.ads.internal.view.e.c.a);
            final List<com.facebook.ads.internal.adapters.b.l> d2 = this.l.d();
            final boolean contains = set.contains(CacheFlag.VIDEO);
            for (final com.facebook.ads.internal.adapters.b.l l : d2) {
                b.a(l.c().g(), b(this.e, l.c()), a(this.e, l.c()));
                if (contains && !TextUtils.isEmpty((CharSequence)l.c().a())) {
                    b.a(l.c().g());
                }
            }
            b.a(new com.facebook.ads.internal.h.a() {
                private void a(final boolean b) {
                    final boolean b2 = false;
                    int n;
                    if (!set.contains(CacheFlag.NONE)) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    if (b || !com.facebook.ads.internal.r.a.T(com.facebook.ads.internal.adapters.g.this.e)) {
                        final g b3 = com.facebook.ads.internal.adapters.g.this;
                        boolean b4 = b2;
                        if (b) {
                            b4 = b2;
                            if (n != 0) {
                                b4 = true;
                            }
                        }
                        b3.n = b4;
                        com.facebook.ads.internal.adapters.g.this.i = true;
                        com.facebook.ads.internal.adapters.g.this.g.onInterstitialAdLoaded(com.facebook.ads.internal.adapters.g.this);
                        return;
                    }
                    com.facebook.ads.internal.adapters.g.this.g.onInterstitialError(com.facebook.ads.internal.adapters.g.this, AdError.CACHE_ERROR);
                }
                
                @Override
                public void a() {
                    this.a(true);
                }
                
                @Override
                public void b() {
                    if (com.facebook.ads.internal.r.a.R(com.facebook.ads.internal.adapters.g.this.e)) {
                        com.facebook.ads.internal.w.h.a.b(com.facebook.ads.internal.adapters.g.this.e, "cache", com.facebook.ads.internal.w.h.b.T, new Exception("Interstitial carousel cache failed"));
                    }
                    this.a(false);
                }
            });
            return;
        }
        if (jsonObject.has("playable_data")) {
            this.m = com.facebook.ads.internal.settings.a.a.f;
            final com.facebook.ads.internal.adapters.b.o a = com.facebook.ads.internal.adapters.b.o.a(this.l);
            final com.facebook.ads.internal.adapters.b.n j = a.f().j();
            f k;
            if (j != null) {
                k = j.f();
            }
            else {
                k = com.facebook.ads.internal.view.c.f.a;
            }
            this.k = k;
            this.h = new com.facebook.ads.internal.adapters.c.a.b() {
                private void b() {
                    com.facebook.ads.internal.adapters.g.this.i = true;
                    com.facebook.ads.internal.adapters.g.this.g.onInterstitialAdLoaded(com.facebook.ads.internal.adapters.g.this);
                }
                
                @Override
                public void a() {
                    com.facebook.ads.internal.adapters.g.this.n = !set.contains(CacheFlag.NONE);
                    this.b();
                }
                
                @Override
                public void a(final AdError adError) {
                    if (!com.facebook.ads.internal.r.a.T(com.facebook.ads.internal.adapters.g.this.e)) {
                        this.b();
                        return;
                    }
                    com.facebook.ads.internal.adapters.g.this.g.onInterstitialError(com.facebook.ads.internal.adapters.g.this, AdError.CACHE_ERROR);
                }
            };
            com.facebook.ads.internal.adapters.c.a.a(this.e, a, com.facebook.ads.internal.r.a.T(this.e), this.h);
            return;
        }
        if (jsonObject.has("video_url")) {
            this.m = com.facebook.ads.internal.settings.a.a.c;
            final com.facebook.ads.internal.h.b b2 = new com.facebook.ads.internal.h.b(this.e);
            final d c2 = this.l.d().get(0).c();
            b2.a(c2.g(), b(this.e, c2), a(this.e, c2));
            b2.a(this.l.a().b(), com.facebook.ads.internal.view.e.c.a, com.facebook.ads.internal.view.e.c.a);
            if (set.contains(CacheFlag.VIDEO)) {
                b2.a(c2.a());
            }
            b2.a(new com.facebook.ads.internal.h.a() {
                private void a(final boolean b) {
                    if (b || !com.facebook.ads.internal.r.a.T(com.facebook.ads.internal.adapters.g.this.e)) {
                        com.facebook.ads.internal.adapters.g.this.n = (b && set.contains(CacheFlag.VIDEO));
                        com.facebook.ads.internal.adapters.g.this.i = true;
                        com.facebook.ads.internal.adapters.g.this.g.onInterstitialAdLoaded(com.facebook.ads.internal.adapters.g.this);
                        return;
                    }
                    com.facebook.ads.internal.adapters.g.this.g.onInterstitialError(com.facebook.ads.internal.adapters.g.this, AdError.CACHE_ERROR);
                }
                
                @Override
                public void a() {
                    this.a(true);
                }
                
                @Override
                public void b() {
                    if (com.facebook.ads.internal.r.a.R(com.facebook.ads.internal.adapters.g.this.e)) {
                        com.facebook.ads.internal.w.h.a.b(com.facebook.ads.internal.adapters.g.this.e, "cache", com.facebook.ads.internal.w.h.b.V, new Exception("Interstitial video cache failed"));
                    }
                    this.a(false);
                }
            });
            return;
        }
        this.m = com.facebook.ads.internal.settings.a.a.d;
        final com.facebook.ads.internal.h.b b3 = new com.facebook.ads.internal.h.b(this.e);
        final d c3 = this.l.d().get(0).c();
        b3.a(c3.g(), b(this.e, c3), a(this.e, c3));
        b3.a(this.l.a().b(), com.facebook.ads.internal.view.e.c.a, com.facebook.ads.internal.view.e.c.a);
        b3.a(new com.facebook.ads.internal.h.a() {
            private void a(final boolean b) {
                if (b || !com.facebook.ads.internal.r.a.T(com.facebook.ads.internal.adapters.g.this.e)) {
                    com.facebook.ads.internal.adapters.g.this.i = true;
                    com.facebook.ads.internal.adapters.g.this.g.onInterstitialAdLoaded(com.facebook.ads.internal.adapters.g.this);
                    return;
                }
                com.facebook.ads.internal.adapters.g.this.g.onInterstitialError(com.facebook.ads.internal.adapters.g.this, AdError.CACHE_ERROR);
            }
            
            @Override
            public void a() {
                this.a(true);
            }
            
            @Override
            public void b() {
                if (com.facebook.ads.internal.r.a.R(com.facebook.ads.internal.adapters.g.this.e)) {
                    com.facebook.ads.internal.w.h.a.b(com.facebook.ads.internal.adapters.g.this.e, "cache", com.facebook.ads.internal.w.h.b.U, new Exception("Interstitial image cache failed"));
                }
                this.a(false);
            }
        });
    }
    
    public boolean a() {
        if (!this.i) {
            if (this.g != null) {
                this.g.onInterstitialError(this, AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            }
            return false;
        }
        final Intent intent = new Intent(this.e, AudienceNetworkActivity.getAdActivity());
        final int rotation = ((WindowManager)this.e.getSystemService("window")).getDefaultDisplay().getRotation();
        while (true) {
            while (true) {
                int n = 0;
                Label_0078: {
                    if (this.k == com.facebook.ads.internal.view.c.f.a) {
                        n = -1;
                        break Label_0078;
                    }
                    Label_0182: {
                        break Label_0182;
                    Block_8_Outer:
                        while (true) {
                            intent.addFlags(268435456);
                            try {
                                this.e.startActivity(intent);
                                return true;
                                // iftrue(Label_0227:, this.k != f.c)
                                // switch([Lcom.strobel.decompiler.ast.Label;@27e74960, rotation)
                                // iftrue(Label_0164:, this.j == null)
                                Block_7: {
                                    break Block_7;
                                    Label_0216: {
                                        n = 0;
                                    }
                                    break;
                                    Label_0227:
                                    Label_0221:
                                    n = 8;
                                    break;
                                    while (true) {
                                        this.j.a(intent);
                                        continue Block_8_Outer;
                                        continue;
                                    }
                                    Label_0253:
                                    n = 9;
                                    break;
                                }
                                // switch([Lcom.strobel.decompiler.ast.Label;@6668cff9, rotation)
                                Label_0248: {
                                    n = 1;
                                }
                                break;
                            }
                            catch (ActivityNotFoundException ex) {
                                intent.setClass(this.e, (Class)InterstitialAdActivity.class);
                                this.e.startActivity(intent);
                                return true;
                            }
                        }
                    }
                }
                intent.putExtra("predefinedOrientationKey", n);
                intent.putExtra("uniqueId", this.b);
                intent.putExtra("placementId", this.c);
                intent.putExtra("requestTime", this.d);
                intent.putExtra("viewType", (Serializable)this.m);
                intent.putExtra("useCache", this.n);
                if (this.l != null) {
                    intent.putExtra("ad_data_bundle", (Serializable)this.l);
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    @Nullable
    @Override
    public String getClientToken() {
        return this.p;
    }
    
    @Override
    public final AdPlacementType getPlacementType() {
        return AdPlacementType.INTERSTITIAL;
    }
    
    @Override
    public void onDestroy() {
        if (this.f != null) {
            this.f.b();
        }
    }
}
