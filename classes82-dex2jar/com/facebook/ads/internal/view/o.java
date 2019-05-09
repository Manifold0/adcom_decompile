// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.support.annotation.RequiresApi;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.res.Configuration;
import java.util.Map;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.adapters.b.k;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.b.h;
import android.os.Build$VERSION;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.w.b.x;
import android.view.View;
import android.content.Context;
import com.facebook.ads.internal.w.b.t;
import com.facebook.ads.internal.adapters.b.b;
import com.facebook.ads.internal.s.c;
import android.widget.RelativeLayout;

public abstract class o extends RelativeLayout implements a
{
    protected final c a;
    protected final i b;
    protected b c;
    private final a d;
    private final t e;
    private String f;
    
    protected o(final Context context, final c a, final a d) {
        super(context.getApplicationContext());
        this.a = a;
        this.d = d;
        this.b = new i(this.getContext(), this.getAudienceNetworkListener(), i.a.a);
        this.e = new t((View)this);
    }
    
    private void a() {
        this.removeAllViews();
        x.b((View)this);
    }
    
    protected void a(final View view, final boolean b, final int n) {
        this.e.a(t.a.a);
        this.a();
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -1);
        int a;
        if (b) {
            a = 0;
        }
        else {
            a = i.a;
        }
        relativeLayout$LayoutParams.setMargins(0, a, 0, 0);
        this.addView(view, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        h h;
        if (n == 1) {
            h = this.c.a();
        }
        else {
            h = this.c.b();
        }
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-1, i.a);
        relativeLayout$LayoutParams2.addRule(10);
        this.b.a(h, b);
        this.addView((View)this.b, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        x.a((View)this, h.d(b));
        if (this.d != null) {
            this.d.a((View)this, 0);
            if (b && Build$VERSION.SDK_INT >= 19) {
                this.e.a(t.a.b);
            }
        }
    }
    
    public void a(final AudienceNetworkActivity audienceNetworkActivity, final k k) {
        this.e.a(audienceNetworkActivity.getWindow());
        this.c = k.b();
        this.f = k.i();
        this.b.a(k.a(), k.c(), k.d().get(0).c().c());
        this.b.setToolbarListener((i.b)new i.b() {
            @Override
            public void a() {
                audienceNetworkActivity.finish();
            }
        });
        if (com.facebook.ads.internal.f.a.a(this.getContext(), true)) {
            this.b.a(k.a(), k.c());
        }
    }
    
    protected void a(final com.facebook.ads.internal.view.component.a.c c, @Nullable final com.facebook.ads.internal.view.component.a.l l, @Nullable final u.a dragListener, final int n, final int n2, final boolean b, final int n3) {
        this.a((View)c, b, n3);
        if (l != null) {
            this.b.setPageDetailsVisibility(4);
            this.e.a(t.a.a);
            if (n3 != 1) {
                final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(n2, -1);
                final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(x.a.widthPixels - n2, i.a);
                layoutParams.addRule(10);
                this.b.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                relativeLayout$LayoutParams.addRule(11);
                c.addView((View)l, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
                return;
            }
            final u u = new u(this.getContext(), l, n - i.a, 0);
            this.addView((View)u);
            if (dragListener != null) {
                u.setDragListener(dragListener);
            }
        }
    }
    
    protected final void a(final Map<String, String> map) {
        if (this.f == null) {
            return;
        }
        map.put("extra_hints", this.f);
    }
    
    protected c getAdEventManager() {
        return this.a;
    }
    
    protected a getAudienceNetworkListener() {
        return this.d;
    }
    
    protected void onConfigurationChanged(final Configuration configuration) {
        this.b.d();
        super.onConfigurationChanged(configuration);
        final ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            @RequiresApi(api = 16)
            public void onGlobalLayout() {
                o.this.b.e();
                if (Build$VERSION.SDK_INT < 16) {
                    viewTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                    return;
                }
                viewTreeObserver.removeOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
            }
        });
    }
    
    public void onDestroy() {
        this.e.a();
        this.b.setToolbarListener(null);
        this.a();
    }
    
    public void setListener(final a a) {
    }
}
