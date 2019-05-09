// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.c;

import android.webkit.WebResourceRequest;
import android.os.Handler;
import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import com.facebook.ads.internal.adapters.b.p;
import java.lang.ref.WeakReference;
import com.facebook.ads.internal.adapters.b.n;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.h.b;
import com.facebook.ads.internal.adapters.b.o;
import android.content.Context;

public final class a
{
    public static void a(final Context context, final o o, final boolean b, final b b2) {
        if (a.n(context)) {
            b2.a();
            return;
        }
        final n j = o.f().j();
        final com.facebook.ads.internal.h.b b3 = new com.facebook.ads.internal.h.b(context);
        if (j == null) {
            b2.a(AdError.CACHE_ERROR);
            return;
        }
        switch (a$1.a[j.k().ordinal()]) {
            case 1: {
                b3.a(j.a());
                break;
            }
            case 2: {
                b3.b(j.a());
                break;
            }
        }
        b3.a(o.b().b(), -1, -1);
        b3.a(j.b(), -1, -1);
        b3.a(new a(context, b2, b3, j, b));
    }
    
    private static class a implements com.facebook.ads.internal.h.a
    {
        final Context a;
        final WeakReference<b> b;
        final com.facebook.ads.internal.h.b c;
        final n d;
        final boolean e;
        
        private a(final Context a, final b b, final com.facebook.ads.internal.h.b c, final n d, final boolean e) {
            this.a = a;
            this.b = new WeakReference<b>(b);
            this.c = c;
            this.d = d;
            this.e = e;
        }
        
        private void a(final boolean b) {
            if (this.b.get() == null) {
                return;
            }
            if (this.d.k() == p.a) {
                final WebView webView = new WebView(this.a);
                webView.getSettings().setCacheMode(1);
                webView.setWebViewClient((WebViewClient)new c(this.d, this.b, this.e));
                webView.loadUrl(this.d.a());
                return;
            }
            String s = this.d.a();
            if (b) {
                if (this.d.k() == p.c) {
                    s = this.c.d(this.d.a());
                }
                else {
                    s = this.c.c(this.d.a());
                }
            }
            this.d.a(s);
            ((b)this.b.get()).a();
        }
        
        @Override
        public void a() {
            this.a(true);
        }
        
        @Override
        public void b() {
            if (this.b.get() == null) {
                return;
            }
            if (this.e) {
                ((b)this.b.get()).a(AdError.CACHE_ERROR);
                return;
            }
            this.a(false);
        }
    }
    
    public interface b
    {
        void a();
        
        void a(final AdError p0);
    }
    
    private static class c extends WebViewClient
    {
        boolean a;
        final n b;
        final WeakReference<b> c;
        final boolean d;
        
        c(final n b, final WeakReference<b> c, final boolean d) {
            this.a = false;
            this.b = b;
            this.c = c;
            this.d = d;
        }
        
        private void a() {
            if (this.c.get() != null) {
                ((b)this.c.get()).a();
            }
        }
        
        private void a(final WebResourceError webResourceError) {
            if (this.c.get() == null) {
                return;
            }
            if (this.d) {
                ((b)this.c.get()).a(AdError.CACHE_ERROR);
                return;
            }
            this.a();
        }
        
        public void onPageFinished(final WebView webView, final String s) {
            this.a = true;
            this.a();
        }
        
        public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            new Handler().postDelayed((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (!com.facebook.ads.internal.adapters.c.a.c.this.a) {
                        com.facebook.ads.internal.adapters.c.a.c.this.a(null);
                    }
                }
            }, (long)this.b.g());
        }
        
        public void onReceivedError(final WebView webView, final WebResourceRequest webResourceRequest, final WebResourceError webResourceError) {
            this.a = true;
            this.a(webResourceError);
        }
    }
}
