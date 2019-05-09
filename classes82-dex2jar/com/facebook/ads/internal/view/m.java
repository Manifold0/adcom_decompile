// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import com.facebook.ads.internal.adapters.b.l;
import java.lang.ref.WeakReference;
import com.facebook.ads.internal.w.b.w;
import java.util.Map;
import java.util.HashMap;
import android.text.TextUtils;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import com.facebook.ads.internal.s.c;
import android.content.Context;
import com.facebook.ads.internal.view.f.b;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.b.k;

public class m extends o
{
    private final k d;
    private final AudienceNetworkActivity.BackButtonInterceptor e;
    private b f;
    private boolean g;
    
    public m(final Context context, final c c, final k d, final com.facebook.ads.internal.view.a.a a) {
        super(context, c, a);
        this.e = new AudienceNetworkActivity.BackButtonInterceptor() {
            @Override
            public boolean interceptBackButton() {
                return !m.this.g;
            }
        };
        this.d = d;
    }
    
    public void a(final Intent intent, final Bundle bundle, final AudienceNetworkActivity audienceNetworkActivity) {
        super.a(audienceNetworkActivity, this.d);
        audienceNetworkActivity.addBackButtonInterceptor(this.e);
        final com.facebook.ads.internal.adapters.b.o a = com.facebook.ads.internal.adapters.b.o.a(this.d);
        this.a((View)(this.f = new b((Context)audienceNetworkActivity, a, this.getAdEventManager(), this.getAudienceNetworkListener(), (b.c)new a(audienceNetworkActivity, this, this.d, this.getAdEventManager(), this.getAudienceNetworkListener()), a.f().c() > 0, true)), true, 1);
        this.b.setVisibility(8);
        this.f.c();
    }
    
    public void a(final Bundle bundle) {
    }
    
    public void a_(final boolean b) {
        this.f.e();
    }
    
    public void b(final boolean b) {
        this.f.d();
    }
    
    @Override
    public void onDestroy() {
        w touchDataRecorder = null;
        super.onDestroy();
        if (!TextUtils.isEmpty((CharSequence)this.d.c())) {
            final com.facebook.ads.internal.view.c.a adWebView = this.f.getAdWebView();
            com.facebook.ads.internal.x.a viewabilityChecker;
            if (adWebView != null) {
                viewabilityChecker = adWebView.getViewabilityChecker();
            }
            else {
                viewabilityChecker = null;
            }
            if (adWebView != null) {
                touchDataRecorder = adWebView.getTouchDataRecorder();
            }
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            if (viewabilityChecker != null) {
                viewabilityChecker.a(hashMap);
                hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(touchDataRecorder.e()));
            }
            this.a.l(this.d.c(), hashMap);
        }
        this.f.f();
    }
    
    private static class a implements c
    {
        private final WeakReference<Activity> a;
        private final WeakReference<m> b;
        private final k c;
        private final com.facebook.ads.internal.s.c d;
        private final WeakReference<com.facebook.ads.internal.view.a.a> e;
        
        a(final Activity activity, final m m, final k c, final com.facebook.ads.internal.s.c d, final com.facebook.ads.internal.view.a.a a) {
            this.a = new WeakReference<Activity>(activity);
            this.b = new WeakReference<m>(m);
            this.c = c;
            this.d = d;
            this.e = new WeakReference<com.facebook.ads.internal.view.a.a>(a);
        }
        
        private void e() {
            if (this.a.get() != null) {
                this.a.get().finish();
            }
        }
        
        @Override
        public void a() {
        }
        
        @Override
        public void a(final com.facebook.ads.internal.x.a a, final w w) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            if (!TextUtils.isEmpty((CharSequence)this.c.c())) {
                a.a(hashMap);
                hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(w.e()));
                this.d.a(this.c.c(), hashMap);
                if (this.e.get() != null) {
                    ((com.facebook.ads.internal.view.a.a)this.e.get()).a("com.facebook.ads.interstitial.impression.logged");
                }
            }
        }
        
        @Override
        public void a(final boolean b) {
            if (this.b.get() == null || this.b.get().f.getAdWebView() == null || this.e.get() == null) {
                return;
            }
            final com.facebook.ads.internal.view.c.a adWebView = this.b.get().f.getAdWebView();
            final com.facebook.ads.internal.view.component.a a = new com.facebook.ads.internal.view.component.a(this.b.get().getContext(), true, false, "com.facebook.ads.interstitial.clicked", this.c.b().a(), this.d, this.e.get(), adWebView.getViewabilityChecker(), adWebView.getTouchDataRecorder());
            a.a(this.c.d().get(0).b(), this.c.c(), new HashMap<String, String>(), b);
            a.performClick();
        }
        
        @Override
        public void b() {
            if (this.b.get() != null) {
                this.b.get().g = true;
            }
        }
        
        @Override
        public void c() {
            this.e();
        }
        
        @Override
        public void d() {
            if (this.e.get() != null) {
                ((com.facebook.ads.internal.view.a.a)this.e.get()).a("com.facebook.ads.interstitial.error");
            }
            this.e();
        }
    }
}
