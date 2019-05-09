// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import java.util.Iterator;
import android.text.TextUtils;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import com.facebook.ads.internal.view.q;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.t.g;
import com.facebook.ads.internal.w.h.a;
import com.facebook.ads.internal.view.c.e;
import com.facebook.ads.internal.view.c.d;
import android.support.v7.widget.RecyclerView$Adapter;
import android.graphics.Bitmap;
import android.support.annotation.VisibleForTesting;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.w.b.x;
import android.annotation.TargetApi;
import android.util.AttributeSet;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import com.facebook.ads.internal.view.c.b;
import android.widget.ImageView;
import com.facebook.ads.internal.view.j;
import com.facebook.ads.internal.t.f;

public class MediaView extends f
{
    private static final String a;
    private j b;
    private ImageView c;
    private b d;
    private RecyclerView e;
    private MediaViewVideoRenderer f;
    private View g;
    @Nullable
    private MediaViewListener h;
    private boolean i;
    private boolean j;
    private boolean k;
    
    static {
        a = MediaView.class.getSimpleName();
    }
    
    public MediaView(final Context context) {
        super(context);
        this.setIconView(new ImageView(context));
        this.setImageRenderer(new b(context));
        this.b = new j(context);
        this.b();
        this.setVideoRenderer(new DefaultMediaViewVideoRenderer(context));
        this.a();
    }
    
    public MediaView(final Context context, final AttributeSet set) {
        super(context, set);
        this.setIconView(new ImageView(context, set));
        this.setImageRenderer(new b(context, set));
        this.b = new j(context, set);
        this.b();
        this.setVideoRenderer(new DefaultMediaViewVideoRenderer(context, set));
        this.a();
    }
    
    public MediaView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.setIconView(new ImageView(context, set, n));
        this.setImageRenderer(new b(context, set, n));
        this.b = new j(context, set, n);
        this.b();
        this.setVideoRenderer(new DefaultMediaViewVideoRenderer(context, set, n));
        this.a();
    }
    
    @TargetApi(21)
    public MediaView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.setIconView(new ImageView(context, set, n, n2));
        this.setImageRenderer(new b(context, set, n, n2));
        this.b = new j(context, set, n);
        this.b();
        this.setVideoRenderer(new DefaultMediaViewVideoRenderer(context, set, n, n2));
        this.a();
    }
    
    private void a() {
        com.facebook.ads.internal.w.b.j.a((View)this, com.facebook.ads.internal.w.b.j.n);
        com.facebook.ads.internal.w.b.j.a((View)this.d, com.facebook.ads.internal.w.b.j.n);
        com.facebook.ads.internal.w.b.j.a((View)this.f, com.facebook.ads.internal.w.b.j.n);
        com.facebook.ads.internal.w.b.j.a((View)this.e, com.facebook.ads.internal.w.b.j.n);
        this.j = true;
    }
    
    private void b() {
        if (this.i) {
            throw new IllegalStateException("Carousel renderer must be set before nativeAd.");
        }
        if (this.e != null) {
            x.b((View)this.b);
        }
        final float b = x.b;
        final int round = Math.round(4.0f * b);
        final int round2 = Math.round(b * 12.0f);
        this.b.setChildSpacing(round);
        this.b.setPadding(0, round2, 0, round2);
        this.b.setVisibility(8);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -1);
        relativeLayout$LayoutParams.addRule(13);
        this.addView((View)this.b, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
    }
    
    private void setIconView(final ImageView c) {
        if (this.i) {
            throw new IllegalStateException("Image renderer must be set before nativeBannerAd.");
        }
        if (this.c != null) {
            x.b((View)this.c);
        }
        c.setVisibility(8);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -1);
        relativeLayout$LayoutParams.addRule(13);
        this.addView((View)c, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.c = c;
    }
    
    private void setImageRenderer(final b d) {
        if (this.i) {
            throw new IllegalStateException("Image renderer must be set before nativeAd.");
        }
        if (this.d != null) {
            this.removeView((View)this.d);
        }
        d.setVisibility(8);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -1);
        relativeLayout$LayoutParams.addRule(13);
        this.addView((View)d, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.d = d;
    }
    
    @VisibleForTesting
    void a(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        this.j = false;
        this.addView(view, viewGroup$LayoutParams);
        this.j = true;
    }
    
    void a(final NativeAdBase nativeAdBase, final boolean b) {
        this.i = true;
        nativeAdBase.b(this);
        this.d.setVisibility(8);
        this.d.a(null, null);
        this.f.setVisibility(8);
        this.f.a();
        if (this.e != null) {
            this.e.setVisibility(8);
            this.e.setAdapter((RecyclerView$Adapter)null);
        }
        this.c.setVisibility(0);
        this.bringChildToFront((View)this.c);
        this.g = (View)this.c;
        final d a = new d(this.c).a();
        if (b) {
            a.a(new e() {
                @Override
                public void a(final boolean b) {
                    nativeAdBase.f().a(b, true);
                }
            });
        }
        final g i = nativeAdBase.f().i();
        if (i != null) {
            a.a(i.a());
            return;
        }
        if (b) {
            nativeAdBase.f().a(false, true);
        }
        com.facebook.ads.internal.w.h.a.b(this.getContext(), "api", com.facebook.ads.internal.w.h.b.h, new Exception("Native Ad Icon is null. Loaded: " + nativeAdBase.f().f()));
    }
    
    public void addView(final View view) {
        if (!this.j) {
            super.addView(view);
        }
    }
    
    public void addView(final View view, final int n) {
        if (!this.j) {
            super.addView(view, n);
        }
    }
    
    public void addView(final View view, final int n, final int n2) {
        if (!this.j) {
            super.addView(view, n, n2);
        }
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (!this.j) {
            super.addView(view, n, viewGroup$LayoutParams);
        }
    }
    
    public void addView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (!this.j) {
            super.addView(view, viewGroup$LayoutParams);
        }
    }
    
    public void bringChildToFront(final View view) {
        if (view == this.e || view == this.f || view == this.d || view == this.c) {
            super.bringChildToFront(view);
        }
    }
    
    public void destroy() {
        this.f.pause(false);
        this.f.destroy();
    }
    
    @Override
    protected View getAdContentsView() {
        return this.g;
    }
    
    protected c getAdEventManager() {
        return com.facebook.ads.internal.s.d.a(this.getContext());
    }
    
    public void setListener(final MediaViewListener h) {
        this.h = h;
        if (h == null) {
            this.f.setListener(null);
            return;
        }
        this.f.setListener(new q() {
            @Override
            public void a() {
                h.onVolumeChange(MediaView.this, MediaView.this.f.getVolume());
            }
            
            @Override
            public void b() {
                h.onPause(MediaView.this);
            }
            
            @Override
            public void c() {
                h.onPlay(MediaView.this);
            }
            
            @Override
            public void d() {
                h.onFullscreenBackground(MediaView.this);
            }
            
            @Override
            public void e() {
                h.onFullscreenForeground(MediaView.this);
            }
            
            @Override
            public void f() {
                h.onExitFullscreen(MediaView.this);
            }
            
            @Override
            public void g() {
                h.onEnterFullscreen(MediaView.this);
            }
            
            @Override
            public void h() {
                h.onComplete(MediaView.this);
            }
        });
    }
    
    void setNativeAd(final NativeAd nativeAd) {
        this.i = true;
        nativeAd.a(this);
        this.c.setVisibility(8);
        this.c.setImageDrawable((Drawable)null);
        int n = 0;
        Label_0036: {
            if (nativeAd.d() == null) {
                n = 0;
            }
            else {
                final Iterator<NativeAd> iterator = nativeAd.d().iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().getAdCoverImage() == null) {
                        n = 0;
                        break Label_0036;
                    }
                }
                n = 1;
            }
        }
        if (n != 0) {
            final boolean q = com.facebook.ads.internal.r.a.q(this.getContext());
            this.e = this.b;
            ((j)this.e).setCurrentPosition(0);
            com.facebook.ads.internal.adapters.a.a adapter;
            if (q) {
                adapter = new com.facebook.ads.internal.adapters.a.b((j)this.e, nativeAd.f().u());
            }
            else {
                adapter = new com.facebook.ads.internal.adapters.a.c((j)this.e, nativeAd.f().u());
            }
            adapter.a((com.facebook.ads.internal.adapters.a.a.a)new com.facebook.ads.internal.adapters.a.a.a() {
                @Override
                public void a() {
                    nativeAd.f().a(true, true);
                }
            });
            this.e.setAdapter((RecyclerView$Adapter)adapter);
            this.g = (View)this.e;
            this.d.setVisibility(8);
            this.d.a(null, null);
            this.f.setVisibility(8);
            this.f.a();
            this.bringChildToFront((View)this.e);
            this.e.setVisibility(0);
        }
        else {
            int n2;
            if (Build$VERSION.SDK_INT >= 14 && !TextUtils.isEmpty((CharSequence)nativeAd.a())) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            if (n2 != 0) {
                nativeAd.f().b(this.k);
                this.g = this.f.getVideoView();
                this.d.setVisibility(8);
                this.d.a(null, null);
                if (this.e != null) {
                    this.e.setVisibility(8);
                    this.e.setAdapter((RecyclerView$Adapter)null);
                }
                this.bringChildToFront((View)this.f);
                this.f.setNativeAd(nativeAd);
                this.f.setVisibility(0);
                return;
            }
            if (nativeAd.getAdCoverImage() != null) {
                this.g = (View)this.d.getBodyImageView();
                this.f.setVisibility(8);
                this.f.a();
                if (this.e != null) {
                    this.e.setVisibility(8);
                    this.e.setAdapter((RecyclerView$Adapter)null);
                }
                this.bringChildToFront((View)this.d);
                this.d.setVisibility(0);
                new d(this.d).a(this.getHeight(), this.getWidth()).a(com.facebook.ads.internal.r.a.m(this.getContext())).a(new e() {
                    @Override
                    public void a(final boolean b) {
                        nativeAd.f().a(b, true);
                    }
                }).a(nativeAd.f().j().a());
            }
        }
    }
    
    public void setVideoRenderer(final MediaViewVideoRenderer f) {
        if (this.i) {
            throw new IllegalStateException("Video renderer must be set before nativeAd.");
        }
        if (this.f != null) {
            this.removeView((View)this.f);
            this.f.destroy();
        }
        f.setAdEventManager(this.getAdEventManager());
        f.setVisibility(8);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -1);
        relativeLayout$LayoutParams.addRule(13);
        this.a((View)f, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.f = f;
        this.k = !(this.f instanceof DefaultMediaViewVideoRenderer);
    }
}
