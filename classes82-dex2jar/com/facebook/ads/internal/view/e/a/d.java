// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.e.a;

import android.support.annotation.VisibleForTesting;
import com.facebook.ads.internal.view.i.a;
import java.util.Map;
import android.view.View$OnClickListener;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.i.b;
import com.facebook.ads.internal.view.i.c.g;
import com.facebook.ads.internal.view.i.c.l;
import com.facebook.ads.internal.view.i.c.f;
import com.facebook.ads.internal.view.y;
import com.facebook.ads.internal.s.c;
import android.widget.FrameLayout;

public class d extends FrameLayout
{
    private static final int a;
    private final c b;
    private y c;
    private f d;
    private l e;
    private g f;
    @Nullable
    private b g;
    
    static {
        a = (int)(16.0f * x.b);
    }
    
    public d(final Context upView, final c b) {
        super(upView);
        this.b = b;
        this.setUpView(upView);
    }
    
    private void setUpPlugins(final Context context) {
        this.c.d();
        this.f = new g(context);
        this.c.a(this.f);
        this.d = new f(context);
        this.c.a(new com.facebook.ads.internal.view.i.c.b(context));
        this.c.a(this.d);
        this.e = new l(context, true);
        this.c.a(this.e);
        this.c.a(new com.facebook.ads.internal.view.i.c.d((View)this.e, com.facebook.ads.internal.view.i.c.d.a.c, true, true));
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        layoutParams.setMargins(com.facebook.ads.internal.view.e.a.d.a, com.facebook.ads.internal.view.e.a.d.a, com.facebook.ads.internal.view.e.a.d.a, com.facebook.ads.internal.view.e.a.d.a);
        this.d.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.c.addView((View)this.d);
    }
    
    private void setUpVideo(final Context context) {
        (this.c = new y(context)).setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -2));
        x.a((View)this.c);
        this.addView((View)this.c);
        this.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                com.facebook.ads.internal.view.e.a.d.this.e.performClick();
            }
        });
    }
    
    private void setUpView(final Context context) {
        this.setUpVideo(context);
        this.setUpPlugins(context);
    }
    
    public void a() {
        this.c.a(true);
    }
    
    public void a(final com.facebook.ads.internal.o.f f) {
        this.c.getEventBus().a(f);
    }
    
    public void a(final c c, final String s, final Map<String, String> map) {
        this.c();
        this.g = new b(this.getContext(), c, this.c, s, map);
    }
    
    public void a(final com.facebook.ads.internal.view.i.a.a a) {
        this.c.a(a);
    }
    
    public boolean b() {
        return this.c.j();
    }
    
    public void c() {
        if (this.g != null) {
            this.g.a();
            this.g = null;
        }
    }
    
    @VisibleForTesting
    public a getSimpleVideoView() {
        return this.c;
    }
    
    public float getVolume() {
        return this.c.getVolume();
    }
    
    public void setPlaceholderUrl(final String image) {
        this.f.setImage(image);
    }
    
    public void setVideoURI(final String videoURI) {
        this.c.setVideoURI(videoURI);
    }
    
    public void setVolume(final float volume) {
        this.c.setVolume(volume);
        this.d.a();
    }
}
