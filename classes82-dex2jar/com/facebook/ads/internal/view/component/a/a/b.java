// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a.a;

import java.lang.ref.WeakReference;
import com.facebook.ads.internal.o.f;
import android.widget.ImageView;
import android.graphics.Path$Direction;
import android.graphics.Canvas;
import java.util.Map;
import android.content.Context;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.os.Build$VERSION;
import android.graphics.Paint$Style;
import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.view.i.b.i;
import android.view.View;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.w;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.view.component.a.e;
import com.facebook.ads.internal.view.i.b.n;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.l;
import android.graphics.RectF;
import android.graphics.Path;
import com.facebook.ads.internal.view.e.a.a;
import android.graphics.Paint;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.view.e.a.d;
import com.facebook.ads.internal.view.x;
import com.facebook.ads.internal.view.component.a.c;

public abstract class b extends com.facebook.ads.internal.view.component.a.c
{
    private static final int c;
    private static final int d;
    private static final int e;
    private x f;
    private com.facebook.ads.internal.view.e.a.d g;
    private RelativeLayout h;
    private final String i;
    private final Paint j;
    private boolean k;
    private com.facebook.ads.internal.view.e.a.a l;
    private final Path m;
    private final RectF n;
    private boolean o;
    private boolean p;
    private a q;
    private final com.facebook.ads.internal.view.i.b.x r;
    private final com.facebook.ads.internal.view.i.b.d s;
    private final l t;
    private final j u;
    private final n v;
    
    static {
        c = (int)(1.0f * com.facebook.ads.internal.w.b.x.b);
        d = (int)(4.0f * com.facebook.ads.internal.w.b.x.b);
        e = (int)(6.0f * com.facebook.ads.internal.w.b.x.b);
    }
    
    b(final com.facebook.ads.internal.view.component.a.e e, final h h, final boolean b, final String i, final com.facebook.ads.internal.view.e.a.a l) {
        super(e, h, b);
        this.m = new Path();
        this.n = new RectF();
        this.r = new com.facebook.ads.internal.view.i.b.x() {
            @Override
            public void a(final w w) {
                b.this.l.c().a(b.this.getVideoView().getVolume());
            }
        };
        this.s = new com.facebook.ads.internal.view.i.b.d() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                b.this.l.d().a((int)b.this.getTag(-1593835536));
            }
        };
        this.t = new l() {
            @Override
            public void a(final k k) {
                b.this.l.e().a((View)b.this);
            }
        };
        this.u = new j() {
            @Override
            public void a(final i i) {
                b.this.l.e().b((View)b.this);
            }
        };
        this.v = new n() {
            @Override
            public void a(final m m) {
                b.this.p = true;
                b.b(b.this);
            }
        };
        this.l = l;
        this.i = i;
        this.setGravity(17);
        this.setPadding(b.c, 0, b.c, b.c);
        com.facebook.ads.internal.w.b.x.a((View)this, 0);
        this.setUpView(this.getContext());
        (this.j = new Paint()).setColor(-16777216);
        this.j.setStyle(Paint$Style.FILL);
        this.j.setAlpha(16);
        this.j.setAntiAlias(true);
        if (Build$VERSION.SDK_INT < 18) {
            this.setLayerType(1, (Paint)null);
        }
    }
    
    private void a(final View view) {
        view.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -2));
        com.facebook.ads.internal.w.b.x.a(view);
    }
    
    static /* synthetic */ void b(final b b) {
        if (b.q != null && ((b.f() && b.p) || (!b.f() && b.o))) {
            b.q.a();
        }
    }
    
    private void setUpView(final Context upMediaContainer) {
        this.setUpImageView(upMediaContainer);
        this.setUpVideoView(upMediaContainer);
        this.setUpMediaContainer(upMediaContainer);
        this.h.addView((View)this.f);
        this.h.addView((View)this.g);
        this.a(upMediaContainer);
    }
    
    protected abstract void a(final Context p0);
    
    public void a(final com.facebook.ads.internal.adapters.b.i i, final Map<String, String> map) {
        this.getCtaButton().a(i, this.i, map);
    }
    
    public void a(final String s, final String s2) {
        this.getTitleDescContainer().a(s, s2, null, true, false);
    }
    
    public void a(final Map<String, String> map) {
        this.g.c();
        if (this.f()) {
            this.g.a(this.getAdEventManager(), this.i, map);
        }
    }
    
    @Override
    public boolean a() {
        return false;
    }
    
    @Override
    protected boolean e() {
        return false;
    }
    
    public boolean f() {
        return this.k;
    }
    
    public boolean g() {
        return this.f() && this.g.b();
    }
    
    protected final RelativeLayout getMediaContainer() {
        return this.h;
    }
    
    public final com.facebook.ads.internal.view.e.a.d getVideoView() {
        return this.g;
    }
    
    public void h() {
        if (this.f()) {
            this.j();
            this.g.a(com.facebook.ads.internal.view.i.a.a.c);
        }
    }
    
    public void i() {
        if (this.f()) {
            this.g.a();
        }
    }
    
    public void j() {
        final float a = this.l.c().a();
        if (this.f() && a != this.g.getVolume()) {
            this.g.setVolume(a);
        }
    }
    
    protected void onDraw(final Canvas canvas) {
        this.m.reset();
        this.n.set(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight());
        this.m.addRoundRect(this.n, (float)b.e, (float)b.e, Path$Direction.CW);
        canvas.drawPath(this.m, this.j);
        this.n.set((float)b.c, 0.0f, (float)(this.getWidth() - b.c), (float)(this.getHeight() - b.c));
        this.m.addRoundRect(this.n, (float)b.d, (float)b.d, Path$Direction.CW);
        canvas.clipPath(this.m);
        super.onDraw(canvas);
    }
    
    public void setImageUrl(final String s) {
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        new com.facebook.ads.internal.view.c.d(this.f).a().a(new b(this)).a(s);
    }
    
    public void setIsVideo(final boolean k) {
        this.k = k;
    }
    
    public void setOnAssetsLoadedListener(final a q) {
        this.q = q;
    }
    
    protected void setUpImageView(final Context context) {
        this.a((View)(this.f = new x(context)));
    }
    
    protected void setUpMediaContainer(final Context context) {
        this.a((View)(this.h = new RelativeLayout(context)));
    }
    
    protected void setUpVideoView(final Context context) {
        this.a((View)(this.g = new com.facebook.ads.internal.view.e.a.d(context, this.getAdEventManager())));
    }
    
    public void setVideoPlaceholderUrl(final String placeholderUrl) {
        this.g.setPlaceholderUrl(placeholderUrl);
    }
    
    public void setVideoUrl(final String videoURI) {
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        this.g.setVideoURI(videoURI);
        this.g.a(this.r);
        this.g.a(this.s);
        this.g.a(this.t);
        this.g.a(this.u);
        this.g.a(this.v);
    }
    
    public interface a
    {
        void a();
    }
    
    private static class b implements com.facebook.ads.internal.view.c.e
    {
        final WeakReference<com.facebook.ads.internal.view.component.a.a.b> a;
        
        private b(final com.facebook.ads.internal.view.component.a.a.b b) {
            this.a = new WeakReference<com.facebook.ads.internal.view.component.a.a.b>(b);
        }
        
        @Override
        public void a(final boolean b) {
            final com.facebook.ads.internal.view.component.a.a.b b2 = this.a.get();
            if (b2 != null) {
                b2.o = b;
                b.b(b2);
            }
        }
    }
    
    public interface c
    {
        void a(final int p0);
    }
    
    public interface d
    {
        void a(final View p0);
        
        void b(final View p0);
    }
    
    public interface e
    {
        float a();
        
        void a(final float p0);
    }
}
