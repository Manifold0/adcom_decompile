// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.webkit.WebView;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import com.moat.analytics.mobile.vng.a.a.a;
import java.util.Map;
import android.view.View;

class n extends MoatFactory
{
    n() {
        if (!this.a()) {
            p.a(3, "Factory", this, "Failed to initialize MoatFactory. Please check that you've initialized the Moat SDK correctly.");
            p.a("[ERROR] ", "Failed to initialize MoatFactory, SDK was not started");
            throw new m();
        }
    }
    
    private NativeDisplayTracker a(final View view, final Map<String, String> map) {
        a.a(view);
        a.a(map);
        return x.a((x.a<NativeDisplayTracker>)new x.a<NativeDisplayTracker>() {
            final /* synthetic */ WeakReference a = new WeakReference((T)view);
            
            @Override
            public com.moat.analytics.mobile.vng.a.b.a<NativeDisplayTracker> a() {
                final View view = (View)this.a.get();
                if (view == null) {
                    p.a(3, "Factory", this, "Target view is null. Not creating NativeDisplayTracker.");
                    p.a("[ERROR] ", "NativeDisplayTracker creation failed, subject view is null");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                if (map == null || map.isEmpty()) {
                    p.a(3, "Factory", this, "adIds is null or empty. NativeDisplayTracker initialization failed.");
                    p.a("[ERROR] ", "NativeDisplayTracker creation failed, adIds is null or empty");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                p.a(3, "Factory", this, "Creating NativeDisplayTracker for " + view.getClass().getSimpleName() + "@" + view.hashCode());
                p.a("[INFO] ", "Attempting to create NativeDisplayTracker for " + view.getClass().getSimpleName() + "@" + view.hashCode());
                return (com.moat.analytics.mobile.vng.a.b.a<NativeDisplayTracker>)com.moat.analytics.mobile.vng.a.b.a.a(new t(view, map));
            }
        }, NativeDisplayTracker.class);
    }
    
    private NativeVideoTracker a(final String s) {
        return x.a((x.a<NativeVideoTracker>)new x.a<NativeVideoTracker>() {
            @Override
            public com.moat.analytics.mobile.vng.a.b.a<NativeVideoTracker> a() {
                if (s == null || s.isEmpty()) {
                    p.a(3, "Factory", this, "partnerCode is null or empty. NativeVideoTracker initialization failed.");
                    p.a("[ERROR] ", "NativeDisplayTracker creation failed, partnerCode is null or empty");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                p.a(3, "Factory", this, "Creating NativeVideo tracker.");
                p.a("[INFO] ", "Attempting to create NativeVideoTracker");
                return (com.moat.analytics.mobile.vng.a.b.a<NativeVideoTracker>)com.moat.analytics.mobile.vng.a.b.a.a(new u(s));
            }
        }, NativeVideoTracker.class);
    }
    
    private WebAdTracker a(final ViewGroup viewGroup) {
        a.a(viewGroup);
        return x.a((x.a<WebAdTracker>)new x.a<WebAdTracker>() {
            final /* synthetic */ WeakReference a = new WeakReference((T)viewGroup);
            
            @Override
            public com.moat.analytics.mobile.vng.a.b.a<WebAdTracker> a() {
                final ViewGroup viewGroup = (ViewGroup)this.a.get();
                if (viewGroup == null) {
                    p.a(3, "Factory", this, "Target ViewGroup is null. Not creating WebAdTracker.");
                    p.a("[ERROR] ", "WebAdTracker not created, adContainer ViewGroup is null");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                p.a(3, "Factory", this, "Creating WebAdTracker for " + viewGroup.getClass().getSimpleName() + "@" + viewGroup.hashCode());
                p.a("[INFO] ", "Attempting to create WebAdTracker for " + viewGroup.getClass().getSimpleName() + "@" + viewGroup.hashCode());
                final com.moat.analytics.mobile.vng.a.b.a<WebView> a = ab.a(viewGroup);
                final boolean c = a.c();
                final StringBuilder append = new StringBuilder().append("WebView ");
                String s;
                if (c) {
                    s = "";
                }
                else {
                    s = "not ";
                }
                p.a(3, "Factory", this, append.append(s).append("found inside of ad container.").toString());
                if (!c) {
                    p.a("[ERROR] ", "WebAdTracker not created, no WebView to track inside of ad container");
                }
                return (com.moat.analytics.mobile.vng.a.b.a<WebAdTracker>)com.moat.analytics.mobile.vng.a.b.a.a(new aa(a.c(null)));
            }
        }, WebAdTracker.class);
    }
    
    private WebAdTracker a(final WebView webView) {
        a.a(webView);
        return x.a((x.a<WebAdTracker>)new x.a<WebAdTracker>() {
            final /* synthetic */ WeakReference a = new WeakReference((T)webView);
            
            @Override
            public com.moat.analytics.mobile.vng.a.b.a<WebAdTracker> a() {
                final WebView webView = (WebView)this.a.get();
                if (webView == null) {
                    p.a(3, "Factory", this, "Target ViewGroup is null. Not creating WebAdTracker.");
                    p.a("[ERROR] ", "WebAdTracker not created, webView is null");
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                p.a(3, "Factory", this, "Creating WebAdTracker for " + webView.getClass().getSimpleName() + "@" + webView.hashCode());
                p.a("[INFO] ", "Attempting to create WebAdTracker for " + webView.getClass().getSimpleName() + "@" + webView.hashCode());
                return (com.moat.analytics.mobile.vng.a.b.a<WebAdTracker>)com.moat.analytics.mobile.vng.a.b.a.a(new aa(webView));
            }
        }, WebAdTracker.class);
    }
    
    private <T> T a(final MoatPlugin<T> moatPlugin) {
        return moatPlugin.a();
    }
    
    private boolean a() {
        return ((k)MoatAnalytics.getInstance()).a();
    }
    
    @Override
    public <T> T createCustomTracker(final MoatPlugin<T> moatPlugin) {
        try {
            return (T)this.a((MoatPlugin<Object>)moatPlugin);
        }
        catch (Exception ex) {
            m.a(ex);
            return moatPlugin.b();
        }
    }
    
    @Override
    public NativeDisplayTracker createNativeDisplayTracker(final View view, final Map<String, String> map) {
        try {
            return this.a(view, map);
        }
        catch (Exception ex) {
            m.a(ex);
            return new v.c();
        }
    }
    
    @Override
    public NativeVideoTracker createNativeVideoTracker(final String s) {
        try {
            return this.a(s);
        }
        catch (Exception ex) {
            m.a(ex);
            return new v.d();
        }
    }
    
    @Override
    public WebAdTracker createWebAdTracker(final ViewGroup viewGroup) {
        try {
            return this.a(viewGroup);
        }
        catch (Exception ex) {
            m.a(ex);
            return new v.e();
        }
    }
    
    @Override
    public WebAdTracker createWebAdTracker(final WebView webView) {
        try {
            return this.a(webView);
        }
        catch (Exception ex) {
            m.a(ex);
            return new v.e();
        }
    }
}
