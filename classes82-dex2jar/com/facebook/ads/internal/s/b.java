// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.s;

import android.support.annotation.WorkerThread;
import com.facebook.ads.internal.v.a.n;
import android.net.NetworkInfo;
import org.json.JSONArray;
import java.util.Locale;
import android.text.TextUtils;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.v.a.p;
import org.json.JSONObject;
import android.support.annotation.UiThread;
import android.os.Looper;
import com.facebook.ads.internal.w.e.d;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import android.content.Context;
import android.os.Handler;
import com.facebook.ads.internal.v.a.a;
import android.net.ConnectivityManager;
import java.util.concurrent.ThreadPoolExecutor;

public class b
{
    private static final String a;
    private final a b;
    private final ThreadPoolExecutor c;
    private final ConnectivityManager d;
    private final com.facebook.ads.internal.v.a.a e;
    private final Handler f;
    private final long g;
    private final long h;
    private final Context i;
    private final Runnable j;
    private final Runnable k;
    private volatile boolean l;
    private int m;
    private long n;
    
    static {
        a = b.class.getSimpleName();
    }
    
    @UiThread
    b(final Context i, final a b) {
        this.j = new Runnable() {
            @Override
            public void run() {
                ++com.facebook.ads.internal.s.b.this.m;
                while (true) {
                    if (com.facebook.ads.internal.s.b.this.n <= 0L) {
                        break Label_0030;
                    }
                    try {
                        Thread.sleep(com.facebook.ads.internal.s.b.this.n);
                        com.facebook.ads.internal.s.b.this.c();
                    }
                    catch (InterruptedException ex) {
                        continue;
                    }
                    break;
                }
            }
        };
        this.k = new Runnable() {
            @Override
            public void run() {
                com.facebook.ads.internal.s.b.this.l = false;
                if (com.facebook.ads.internal.s.b.this.c.getQueue().isEmpty()) {
                    com.facebook.ads.internal.s.b.this.c.execute(com.facebook.ads.internal.s.b.this.j);
                }
            }
        };
        this.b = b;
        this.i = i;
        this.c = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        this.d = (ConnectivityManager)i.getSystemService("connectivity");
        this.e = com.facebook.ads.internal.w.e.d.b(i);
        this.f = new Handler(Looper.getMainLooper());
        this.g = com.facebook.ads.internal.r.a.v(i);
        this.h = com.facebook.ads.internal.r.a.w(i);
    }
    
    private void a(final long n) {
        this.f.postDelayed(this.k, n);
    }
    
    private void d() {
        if (this.m >= 5) {
            this.e();
            this.b();
            return;
        }
        if (this.m == 1) {
            this.n = 2000L;
        }
        else {
            this.n *= 2L;
        }
        this.a();
    }
    
    private void e() {
        this.m = 0;
        this.n = 0L;
        if (this.c.getQueue().size() == 0) {
            this.b.b();
        }
    }
    
    void a() {
        this.l = true;
        this.f.removeCallbacks(this.k);
        this.a(this.g);
    }
    
    void b() {
        if (this.l) {
            return;
        }
        this.l = true;
        this.f.removeCallbacks(this.k);
        this.a(this.h);
    }
    
    @WorkerThread
    void c() {
        JSONObject a;
        try {
            final NetworkInfo activeNetworkInfo = this.d.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                this.a(this.h);
                return;
            }
            a = this.b.a();
            if (a == null) {
                this.e();
                return;
            }
        }
        catch (Exception ex) {
            this.d();
            return;
        }
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("attempt", (Object)String.valueOf(this.m));
        a.put("data", (Object)jsonObject);
        final p p = new p();
        p.a("payload", a.toString());
        final com.facebook.ads.internal.v.a.a e = this.e;
        final Context i = this.i;
        final String urlPrefix = AdInternalSettings.getUrlPrefix();
        String s;
        if (TextUtils.isEmpty((CharSequence)urlPrefix)) {
            s = "https://www.facebook.com/adnw_logging/";
        }
        else {
            s = String.format(Locale.US, "https://www.%s.facebook.com/adnw_logging/", urlPrefix);
        }
        final String m = com.facebook.ads.internal.r.a.M(i);
        if (!TextUtils.isEmpty((CharSequence)m)) {
            s = s.replace("www", m);
        }
        final n b = e.b(s, p);
        String e2;
        if (b != null) {
            e2 = b.e();
        }
        else {
            e2 = null;
        }
        if (TextUtils.isEmpty((CharSequence)e2)) {
            if (a.has("events")) {
                this.b.b(a.getJSONArray("events"));
            }
            this.d();
            return;
        }
        if (b.a() != 200) {
            if (b.a() == 413 && com.facebook.ads.internal.r.a.J(this.i)) {
                this.b.c();
                this.e();
                return;
            }
            if (a.has("events")) {
                this.b.b(a.getJSONArray("events"));
            }
            this.d();
        }
        else {
            if (!this.b.a(new JSONArray(e2))) {
                this.d();
                return;
            }
            if (this.b.d()) {
                this.d();
                return;
            }
            this.e();
        }
    }
    
    public interface a
    {
        JSONObject a();
        
        boolean a(final JSONArray p0);
        
        void b();
        
        void b(final JSONArray p0);
        
        void c();
        
        boolean d();
    }
}
