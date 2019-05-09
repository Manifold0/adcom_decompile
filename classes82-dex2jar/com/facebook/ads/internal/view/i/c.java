// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i;

import android.view.View;
import android.media.AudioManager;
import android.database.ContentObserver;
import android.provider.Settings$System;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.graphics.Rect;
import java.util.HashMap;
import android.os.Handler;
import com.facebook.ads.internal.d.b;
import java.util.List;
import java.util.Map;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.d.a;
import android.content.Context;
import android.os.Bundle;
import com.facebook.ads.internal.w.b.r;

public class c implements r<Bundle>
{
    private final String a;
    private boolean b;
    private final Context c;
    private final com.facebook.ads.internal.s.c d;
    private final a e;
    private final com.facebook.ads.internal.d.a f;
    private int g;
    private int h;
    @Nullable
    private String i;
    @Nullable
    private String j;
    private final e k;
    @Nullable
    private final Map<String, String> l;
    
    public c(final Context context, final com.facebook.ads.internal.s.c c, final a a, final List<com.facebook.ads.internal.d.b> list, final String s) {
        this(context, c, a, list, s, null);
    }
    
    public c(final Context context, final com.facebook.ads.internal.s.c c, final a a, final List<com.facebook.ads.internal.d.b> list, final String s, @Nullable final Bundle bundle) {
        this(context, c, a, list, s, bundle, null);
    }
    
    public c(final Context c, final com.facebook.ads.internal.s.c d, final a e, final List<com.facebook.ads.internal.d.b> list, final String a, @Nullable final Bundle bundle, @Nullable final Map<String, String> l) {
        this.b = true;
        this.g = 0;
        this.h = 0;
        this.i = null;
        this.j = null;
        this.c = c;
        this.d = d;
        this.e = e;
        this.a = a;
        this.l = l;
        list.add(new com.facebook.ads.internal.d.b(0.5, -1.0, 2.0, true) {
            @Override
            protected void a(final boolean b, final boolean b2, final com.facebook.ads.internal.d.c c) {
                if (b2) {
                    c.this.d.e(c.this.a, c.this.a(b.d));
                }
            }
        });
        list.add(new com.facebook.ads.internal.d.b(1.0E-7, -1.0, 0.001, false) {
            @Override
            protected void a(final boolean b, final boolean b2, final com.facebook.ads.internal.d.c c) {
                if (b2) {
                    c.this.d.e(c.this.a, c.this.a(b.i));
                }
            }
        });
        if (bundle != null) {
            this.f = new com.facebook.ads.internal.d.a(e.getView(), list, bundle.getBundle("adQualityManager"));
            this.g = bundle.getInt("lastProgressTimeMS");
            this.h = bundle.getInt("lastBoundaryTimeMS");
        }
        else {
            this.f = new com.facebook.ads.internal.d.a(e.getView(), list);
        }
        this.k = new e(new Handler(), this);
    }
    
    private Map<String, String> a(final b b) {
        return this.a(b, this.e.getCurrentPositionInMillis());
    }
    
    private Map<String, String> a(final b b, final int n) {
        int n2 = 1;
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        int n3;
        if (this.e.getVideoStartReason() == com.facebook.ads.internal.view.i.a.a.c) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        if (this.e.i()) {
            n2 = 0;
        }
        String s;
        if (n3 != 0) {
            s = "1";
        }
        else {
            s = "0";
        }
        hashMap.put("autoplay", s);
        String s2;
        if (n2 != 0) {
            s2 = "1";
        }
        else {
            s2 = "0";
        }
        hashMap.put("inline", s2);
        hashMap.put("exoplayer", String.valueOf(this.e.h()));
        hashMap.put("prep", Long.toString(this.e.getInitialBufferTime()));
        final com.facebook.ads.internal.d.c c = this.f.c();
        final com.facebook.ads.internal.d.c.a c2 = c.c();
        hashMap.put("vwa", String.valueOf(c2.d()));
        hashMap.put("vwm", String.valueOf(c2.c()));
        hashMap.put("vwmax", String.valueOf(c2.e()));
        hashMap.put("vtime_ms", String.valueOf(c2.g() * 1000.0));
        hashMap.put("mcvt_ms", String.valueOf(c2.h() * 1000.0));
        if (this.i != null) {
            hashMap.put("vw_d", this.i);
        }
        if (this.j != null) {
            hashMap.put("vw_rsn", this.j);
        }
        final com.facebook.ads.internal.d.c.a d = c.d();
        hashMap.put("vla", String.valueOf(d.d()));
        hashMap.put("vlm", String.valueOf(d.c()));
        hashMap.put("vlmax", String.valueOf(d.e()));
        hashMap.put("atime_ms", String.valueOf(d.g() * 1000.0));
        hashMap.put("mcat_ms", String.valueOf(d.h() * 1000.0));
        hashMap.put("ptime", String.valueOf(this.h / 1000.0f));
        hashMap.put("time", String.valueOf(n / 1000.0f));
        final Rect rect = new Rect();
        this.e.getGlobalVisibleRect(rect);
        hashMap.put("pt", String.valueOf(rect.top));
        hashMap.put("pl", String.valueOf(rect.left));
        hashMap.put("ph", String.valueOf(this.e.getMeasuredHeight()));
        hashMap.put("pw", String.valueOf(this.e.getMeasuredWidth()));
        final WindowManager windowManager = (WindowManager)this.c.getSystemService("window");
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        hashMap.put("vph", String.valueOf(displayMetrics.heightPixels));
        hashMap.put("vpw", String.valueOf(displayMetrics.widthPixels));
        if (this.l != null) {
            hashMap.putAll(this.l);
        }
        hashMap.put("action", String.valueOf(b.j));
        return hashMap;
    }
    
    void a(final int n) {
        this.a(n, false, false);
    }
    
    void a(final int n, final int n2) {
        this.a(n, true, false);
        this.h = n2;
        this.g = n2;
        this.f.a();
        this.f.b();
    }
    
    void a(final int g, final boolean b, final boolean b2) {
        if (g > 0.0 && g >= this.g) {
            if (g > this.g) {
                this.f.a((g - this.g) / 1000.0f, this.d());
                this.g = g;
                if (b2 || g - this.h >= 5000) {
                    this.d.e(this.a, this.a(com.facebook.ads.internal.view.i.c.b.c, g));
                    this.h = this.g;
                    this.f.a();
                    return;
                }
            }
            if (b) {
                this.d.e(this.a, this.a(com.facebook.ads.internal.view.i.c.b.c, g));
            }
        }
    }
    
    public void b() {
        this.c.getContentResolver().registerContentObserver(Settings$System.CONTENT_URI, true, (ContentObserver)this.k);
    }
    
    public void b(final int n) {
        this.a(n, true, false);
        this.h = 0;
        this.g = 0;
        this.f.a();
        this.f.b();
    }
    
    public void c() {
        this.c.getContentResolver().unregisterContentObserver((ContentObserver)this.k);
    }
    
    protected float d() {
        final AudioManager audioManager = (AudioManager)this.c.getSystemService("audio");
        Label_0058: {
            if (audioManager == null) {
                break Label_0058;
            }
            final int streamVolume = audioManager.getStreamVolume(3);
            final int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            if (streamMaxVolume <= 0) {
                break Label_0058;
            }
            final float n = streamVolume * 1.0f / streamMaxVolume;
            return n * this.e.getVolume();
        }
        final float n = 0.0f;
        return n * this.e.getVolume();
    }
    
    void e() {
        if (this.d() < 0.05) {
            if (this.b) {
                this.d.e(this.a, this.a(com.facebook.ads.internal.view.i.c.b.g));
                this.b = false;
            }
        }
        else if (!this.b) {
            this.d.e(this.a, this.a(com.facebook.ads.internal.view.i.c.b.h));
            this.b = true;
        }
    }
    
    void f() {
        this.d.e(this.a, this.a(com.facebook.ads.internal.view.i.c.b.b));
    }
    
    @Override
    public Bundle g() {
        this.a(this.j(), this.j());
        final Bundle bundle = new Bundle();
        bundle.putInt("lastProgressTimeMS", this.g);
        bundle.putInt("lastBoundaryTimeMS", this.h);
        bundle.putBundle("adQualityManager", this.f.g());
        return bundle;
    }
    
    void h() {
        this.d.e(this.a, this.a(com.facebook.ads.internal.view.i.c.b.e));
    }
    
    void i() {
        this.d.e(this.a, this.a(com.facebook.ads.internal.view.i.c.b.f));
    }
    
    public int j() {
        return this.g;
    }
    
    public interface a
    {
        int getCurrentPositionInMillis();
        
        boolean getGlobalVisibleRect(final Rect p0);
        
        long getInitialBufferTime();
        
        int getMeasuredHeight();
        
        int getMeasuredWidth();
        
        com.facebook.ads.internal.view.i.a.a getVideoStartReason();
        
        @Nullable
        View getView();
        
        float getVolume();
        
        boolean h();
        
        boolean i();
    }
    
    protected enum b
    {
        a(0), 
        b(1), 
        c(2), 
        d(3), 
        e(4), 
        f(5), 
        g(6), 
        h(7), 
        i(10);
        
        public final int j;
        
        private b(final int j) {
            this.j = j;
        }
    }
}
