// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import com.facebook.ads.internal.view.c.e;
import android.support.annotation.Nullable;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View;
import com.facebook.ads.internal.w.b.x;
import android.widget.ImageView$ScaleType;
import com.facebook.ads.internal.o.d;
import android.content.Context;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.o.f;
import android.widget.ImageView;
import com.facebook.ads.internal.view.i.a.c;

public class g extends c
{
    private final ImageView a;
    private final f<k> b;
    private final f<com.facebook.ads.internal.view.i.b.c> c;
    
    public g(final Context context) {
        super(context);
        this.b = new f<k>() {
            @Override
            public Class<k> a() {
                return k.class;
            }
            
            @Override
            public void a(final k k) {
                g.this.setVisibility(8);
            }
        };
        this.c = new f<com.facebook.ads.internal.view.i.b.c>() {
            @Override
            public Class<com.facebook.ads.internal.view.i.b.c> a() {
                return com.facebook.ads.internal.view.i.b.c.class;
            }
            
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                g.this.setVisibility(0);
            }
        };
        (this.a = new ImageView(context)).setScaleType(ImageView$ScaleType.FIT_CENTER);
        x.a((View)this.a, -16777216);
        this.a.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        this.addView((View)this.a);
    }
    
    @Override
    protected void a() {
        super.a();
        if (this.getVideoView() != null) {
            this.getVideoView().getEventBus().a(this.b, this.c);
        }
    }
    
    public void a(@Nullable final String s, @Nullable final e e) {
        if (s == null) {
            this.setVisibility(8);
            return;
        }
        this.setVisibility(0);
        final com.facebook.ads.internal.view.c.d a = new com.facebook.ads.internal.view.c.d(this.a).a();
        if (e != null) {
            a.a(e);
        }
        a.a(s);
    }
    
    @Override
    protected void b() {
        if (this.getVideoView() != null) {
            this.getVideoView().getEventBus().b(this.c, this.b);
        }
        super.b();
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        this.a.layout(0, 0, n3 - n, n4 - n2);
    }
    
    public void setImage(@Nullable final String s) {
        this.a(s, null);
    }
}
