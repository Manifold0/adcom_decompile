// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import java.util.Iterator;
import com.facebook.ads.internal.m.d;
import android.text.TextUtils;
import com.facebook.ads.internal.h.b;
import com.facebook.ads.internal.adapters.i;
import com.facebook.ads.internal.protocol.e;
import android.util.Log;
import android.webkit.CookieSyncManager;
import android.os.Build$VERSION;
import android.webkit.CookieManager;
import java.util.ArrayList;
import com.facebook.ads.internal.a;
import java.util.List;
import android.content.Context;

public class NativeAdsManager
{
    private static final String a;
    private final Context b;
    private final String c;
    private final int d;
    private final List<NativeAd> e;
    private int f;
    private Listener g;
    private String h;
    private a i;
    private boolean j;
    private boolean k;
    
    static {
        a = NativeAdsManager.class.getSimpleName();
    }
    
    public NativeAdsManager(final Context b, final String c, final int n) {
        if (b == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        this.b = b;
        this.c = c;
        this.d = Math.max(n, 0);
        this.e = new ArrayList<NativeAd>(n);
        this.f = -1;
        this.k = false;
        this.j = false;
        try {
            CookieManager.getInstance();
            if (Build$VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(b);
            }
        }
        catch (Exception ex) {
            Log.w(NativeAdsManager.a, "Failed to initialize CookieManager.", (Throwable)ex);
        }
    }
    
    public void disableAutoRefresh() {
        this.j = true;
        if (this.i != null) {
            this.i.c();
        }
    }
    
    public int getUniqueNativeAdCount() {
        return this.e.size();
    }
    
    public boolean isLoaded() {
        return this.k;
    }
    
    public void loadAds() {
        this.loadAds(NativeAdBase.MediaCacheFlag.ALL);
    }
    
    public void loadAds(final NativeAdBase.MediaCacheFlag mediaCacheFlag) {
        final e j = com.facebook.ads.internal.protocol.e.j;
        final int d = this.d;
        if (this.i != null) {
            this.i.b();
        }
        this.i = new a(this.b, this.c, j, null, d);
        if (this.j) {
            this.i.c();
        }
        this.i.a(this.h);
        this.i.a((a.a)new a.a() {
            @Override
            public void a(final com.facebook.ads.internal.protocol.a a) {
                if (NativeAdsManager.this.g != null) {
                    NativeAdsManager.this.g.onAdError(AdError.getAdErrorFromWrapper(a));
                }
            }
            
            @Override
            public void a(final List<i> list) {
                final com.facebook.ads.internal.h.b b = new com.facebook.ads.internal.h.b(NativeAdsManager.this.b);
                for (final i i : list) {
                    if (mediaCacheFlag.equals(NativeAdBase.MediaCacheFlag.ALL)) {
                        if (i.l() != null) {
                            b.a(i.l().a(), i.l().c(), i.l().b());
                        }
                        if (i.m() != null) {
                            b.a(i.m().a(), i.m().c(), i.m().b());
                        }
                        if (TextUtils.isEmpty((CharSequence)i.t())) {
                            continue;
                        }
                        b.a(i.t());
                    }
                }
                b.a(new com.facebook.ads.internal.h.a() {
                    private void c() {
                        NativeAdsManager.this.k = true;
                        NativeAdsManager.this.e.clear();
                        NativeAdsManager.this.f = 0;
                        final Iterator<i> iterator = list.iterator();
                        while (iterator.hasNext()) {
                            NativeAdsManager.this.e.add(new NativeAd(NativeAdsManager.this.b, iterator.next(), null));
                        }
                        if (NativeAdsManager.this.g != null) {
                            NativeAdsManager.this.g.onAdsLoaded();
                        }
                    }
                    
                    @Override
                    public void a() {
                        this.c();
                    }
                    
                    @Override
                    public void b() {
                        this.c();
                    }
                });
            }
        });
        this.i.a();
    }
    
    public NativeAd nextNativeAd() {
        NativeAdBase nativeAdBase;
        if (this.e.size() == 0) {
            nativeAdBase = null;
        }
        else {
            final int n = this.f++;
            nativeAdBase = this.e.get(n % this.e.size());
            if (n >= this.e.size()) {
                return new NativeAd(nativeAdBase);
            }
        }
        return (NativeAd)nativeAdBase;
    }
    
    public void setExtraHints(final String h) {
        this.h = h;
    }
    
    public void setListener(final Listener g) {
        this.g = g;
    }
    
    public interface Listener
    {
        void onAdError(final AdError p0);
        
        void onAdsLoaded();
    }
}
