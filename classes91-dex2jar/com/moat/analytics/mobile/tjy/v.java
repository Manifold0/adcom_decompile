// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.webkit.WebView;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import android.view.View;
import android.app.Activity;
import java.util.concurrent.atomic.AtomicReference;

class v extends MoatFactory
{
    private static final AtomicReference c;
    private final bl a;
    private final a b;
    
    static {
        c = new AtomicReference();
    }
    
    v(final Activity activity) {
        this.a = new bm();
        Label_0054: {
            if (v.c.get() != null) {
                break Label_0054;
            }
            ap ap = new an();
            while (true) {
                try {
                    ap = new as(ab.a);
                    v.c.compareAndSet(null, ap);
                    (this.b = new c(activity, v.c.get())).b();
                }
                catch (Exception ex) {
                    com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
                    continue;
                }
                break;
            }
        }
    }
    
    private NativeDisplayTracker a(final View view, final String s) {
        com.moat.analytics.mobile.tjy.base.asserts.a.a(view);
        final ap ap;
        return (NativeDisplayTracker)ay.a(ap, new y(this, new WeakReference((T)view), ap = v.c.get(), s), new ag());
    }
    
    private NativeVideoTracker a(final String s) {
        final ap ap = v.c.get();
        return (NativeVideoTracker)ay.a(ap, new z(this, ap, s), new ai());
    }
    
    private WebAdTracker a(final ViewGroup viewGroup) {
        com.moat.analytics.mobile.tjy.base.asserts.a.a(viewGroup);
        final ap ap;
        return (WebAdTracker)ay.a(ap, new x(this, new WeakReference((T)viewGroup), ap = v.c.get()), new bk());
    }
    
    private WebAdTracker a(final WebView webView) {
        com.moat.analytics.mobile.tjy.base.asserts.a.a(webView);
        final WeakReference weakReference = new WeakReference((T)webView);
        final ap ap = v.c.get();
        return (WebAdTracker)ay.a(ap, new w(this, weakReference, ap), new bk());
    }
    
    public Object a(final ac ac) {
        return ac.a(this.b, v.c.get());
    }
    
    @Override
    public Object createCustomTracker(final ac ac) {
        try {
            return this.a(ac);
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
            return ac.a();
        }
    }
    
    @Override
    public NativeDisplayTracker createNativeDisplayTracker(final View view, final String s) {
        try {
            return this.a(view, s);
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
            return new al();
        }
    }
    
    @Override
    public NativeVideoTracker createNativeVideoTracker(final String s) {
        try {
            return this.a(s);
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
            return new am();
        }
    }
    
    @Override
    public WebAdTracker createWebAdTracker(final ViewGroup viewGroup) {
        try {
            return this.a(viewGroup);
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
            return new ao();
        }
    }
    
    @Override
    public WebAdTracker createWebAdTracker(final WebView webView) {
        try {
            return this.a(webView);
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
            return new ao();
        }
    }
    
    @Override
    public WebAdTracker createWebDisplayTracker(final ViewGroup viewGroup) {
        return this.createWebAdTracker(viewGroup);
    }
    
    @Override
    public WebAdTracker createWebDisplayTracker(final WebView webView) {
        return this.createWebAdTracker(webView);
    }
}
