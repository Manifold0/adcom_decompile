// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.view.MenuItem;
import android.widget.PopupMenu$OnMenuItemClickListener;
import android.net.Uri;
import com.facebook.ads.internal.w.e.g;
import android.text.TextUtils;
import com.facebook.ads.internal.adapters.b.m;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import android.support.v4.graphics.ColorUtils;
import com.facebook.ads.internal.adapters.b.h;
import android.view.ViewGroup$LayoutParams;
import android.animation.LayoutTransition;
import android.widget.LinearLayout$LayoutParams;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.ImageView$ScaleType;
import android.os.Build$VERSION;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.b.o;
import android.content.Context;
import android.content.res.Resources;
import com.facebook.ads.internal.w.b.x;
import android.widget.PopupMenu$OnDismissListener;
import android.support.annotation.Nullable;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.view.e.c;
import com.facebook.ads.internal.view.component.CircularProgressView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.p;
import com.facebook.ads.internal.view.i.a.b;
import android.widget.LinearLayout;

public class i extends LinearLayout implements com.facebook.ads.internal.view.i.a.b
{
    public static final int a;
    private static final float d;
    private static final int e;
    private static final int f;
    private static final int g;
    private static final int h;
    private static final int i;
    private static final int j;
    private final p b;
    private final d c;
    private final com.facebook.ads.internal.view.a.a k;
    private final ImageView l;
    private final FrameLayout m;
    private final ImageView n;
    private final CircularProgressView o;
    private final c p;
    private final RelativeLayout q;
    private final PopupMenu r;
    @Nullable
    private ImageView s;
    @Nullable
    private b t;
    @Nullable
    private com.facebook.ads.internal.view.i.a u;
    private int v;
    private boolean w;
    private boolean x;
    private PopupMenu$OnDismissListener y;
    
    static {
        a = (int)(56.0f * x.b);
        d = Resources.getSystem().getDisplayMetrics().density;
        e = (int)(40.0f * com.facebook.ads.internal.view.i.d);
        f = (int)(44.0f * com.facebook.ads.internal.view.i.d);
        g = (int)(10.0f * com.facebook.ads.internal.view.i.d);
        h = (int)(16.0f * com.facebook.ads.internal.view.i.d);
        i = com.facebook.ads.internal.view.i.h - com.facebook.ads.internal.view.i.g;
        j = com.facebook.ads.internal.view.i.h * 2 - com.facebook.ads.internal.view.i.g;
    }
    
    public i(final Context context, final com.facebook.ads.internal.view.a.a k, final a closeButtonStyle) {
        super(context);
        this.b = new p() {
            @Override
            public void a(final o o) {
                if (com.facebook.ads.internal.view.i.this.u != null && com.facebook.ads.internal.view.i.this.v != 0 && com.facebook.ads.internal.view.i.this.o.isShown()) {
                    final float n = com.facebook.ads.internal.view.i.this.u.getCurrentPositionInMillis() / Math.min(com.facebook.ads.internal.view.i.this.v * 1000.0f, (float)com.facebook.ads.internal.view.i.this.u.getDuration());
                    com.facebook.ads.internal.view.i.this.setProgress(100.0f * n);
                    if (n >= 1.0f) {
                        com.facebook.ads.internal.view.i.this.a(true);
                        com.facebook.ads.internal.view.i.this.u.getEventBus().b(com.facebook.ads.internal.view.i.this.b, com.facebook.ads.internal.view.i.this.c);
                    }
                }
            }
        };
        this.c = new d() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                if (com.facebook.ads.internal.view.i.this.u != null && com.facebook.ads.internal.view.i.this.v != 0 && com.facebook.ads.internal.view.i.this.o.isShown() && !com.facebook.ads.internal.view.i.this.x) {
                    com.facebook.ads.internal.view.i.this.a(true);
                    com.facebook.ads.internal.view.i.this.u.getEventBus().b(com.facebook.ads.internal.view.i.this.b, com.facebook.ads.internal.view.i.this.c);
                }
            }
        };
        this.v = 0;
        this.w = false;
        this.x = false;
        this.k = k;
        this.setGravity(16);
        if (Build$VERSION.SDK_INT >= 14) {
            this.y = (PopupMenu$OnDismissListener)new PopupMenu$OnDismissListener() {
                public void onDismiss(final PopupMenu popupMenu) {
                    com.facebook.ads.internal.view.i.this.w = false;
                }
            };
        }
        (this.n = new ImageView(context)).setPadding(com.facebook.ads.internal.view.i.g, com.facebook.ads.internal.view.i.g, com.facebook.ads.internal.view.i.g, com.facebook.ads.internal.view.i.g);
        this.n.setScaleType(ImageView$ScaleType.FIT_CENTER);
        this.n.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (com.facebook.ads.internal.view.i.this.t != null && com.facebook.ads.internal.view.i.this.x) {
                    com.facebook.ads.internal.view.i.this.t.a();
                }
            }
        });
        this.setCloseButtonStyle(closeButtonStyle);
        (this.o = new CircularProgressView(context)).setPadding(com.facebook.ads.internal.view.i.g, com.facebook.ads.internal.view.i.g, com.facebook.ads.internal.view.i.g, com.facebook.ads.internal.view.i.g);
        this.o.setProgress(0.0f);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-2, -2);
        linearLayout$LayoutParams.setMargins(com.facebook.ads.internal.view.i.i, com.facebook.ads.internal.view.i.i, com.facebook.ads.internal.view.i.j, com.facebook.ads.internal.view.i.i);
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(com.facebook.ads.internal.view.i.f, com.facebook.ads.internal.view.i.f);
        (this.m = new FrameLayout(context)).setLayoutTransition(new LayoutTransition());
        this.m.addView((View)this.n, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        this.m.addView((View)this.o, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        this.addView((View)this.m, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        this.q = new RelativeLayout(context);
        final LinearLayout$LayoutParams linearLayout$LayoutParams3 = new LinearLayout$LayoutParams(0, -2);
        linearLayout$LayoutParams3.weight = 1.0f;
        this.p = new c(context);
        final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.p.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.q.addView((View)this.p);
        this.addView((View)this.q, (ViewGroup$LayoutParams)linearLayout$LayoutParams3);
        (this.l = new ImageView(context)).setPadding(com.facebook.ads.internal.view.i.g, com.facebook.ads.internal.view.i.g, com.facebook.ads.internal.view.i.g, com.facebook.ads.internal.view.i.g);
        this.l.setScaleType(ImageView$ScaleType.FIT_CENTER);
        this.l.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.f));
        this.l.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                com.facebook.ads.internal.view.i.this.r.show();
                com.facebook.ads.internal.view.i.this.w = true;
            }
        });
        this.r = new PopupMenu(context, (View)this.l);
        this.r.getMenu().add((CharSequence)"Ad Choices");
        final LinearLayout$LayoutParams linearLayout$LayoutParams4 = new LinearLayout$LayoutParams(com.facebook.ads.internal.view.i.e, com.facebook.ads.internal.view.i.e);
        linearLayout$LayoutParams4.setMargins(0, com.facebook.ads.internal.view.i.h / 2, com.facebook.ads.internal.view.i.h / 2, com.facebook.ads.internal.view.i.h / 2);
        this.addView((View)this.l, (ViewGroup$LayoutParams)linearLayout$LayoutParams4);
    }
    
    public void a(final h h, final boolean b) {
        final int a = h.a(b);
        this.p.a(h.g(b), a);
        this.l.setColorFilter(a);
        if (this.s != null) {
            this.s.setColorFilter(a);
        }
        this.n.setColorFilter(a);
        this.o.a(ColorUtils.setAlphaComponent(a, 77), a);
        if (b) {
            final GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable$Orientation.TOP_BOTTOM, new int[] { -1778384896, 0 });
            gradientDrawable.setCornerRadius(0.0f);
            com.facebook.ads.internal.w.b.x.a((View)this, (Drawable)gradientDrawable);
            return;
        }
        com.facebook.ads.internal.w.b.x.a((View)this, 0);
    }
    
    public void a(final m m, final String s) {
        (this.s = new ImageView(this.getContext())).setPadding(com.facebook.ads.internal.view.i.g, com.facebook.ads.internal.view.i.g, com.facebook.ads.internal.view.i.g, com.facebook.ads.internal.view.i.g);
        this.s.setScaleType(ImageView$ScaleType.FIT_CENTER);
        this.s.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.i));
        this.s.setColorFilter(-1);
        this.addView((View)this.s, this.getChildCount() - 1, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(com.facebook.ads.internal.view.i.e, com.facebook.ads.internal.view.i.e));
        this.s.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                com.facebook.ads.internal.view.i.this.k.a(s, true, null);
            }
        });
        this.l.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                String s;
                if (!TextUtils.isEmpty((CharSequence)com.facebook.ads.internal.f.a.m(com.facebook.ads.internal.view.i.this.getContext()))) {
                    s = com.facebook.ads.internal.f.a.m(com.facebook.ads.internal.view.i.this.getContext());
                }
                else {
                    s = m.c();
                }
                if (!TextUtils.isEmpty((CharSequence)s)) {
                    com.facebook.ads.internal.w.e.g.a(new g(), com.facebook.ads.internal.view.i.this.getContext(), Uri.parse(s), s);
                }
            }
        });
    }
    
    public void a(final m pageDetails, final String s, final int v) {
        this.v = v;
        this.p.setPageDetails(pageDetails);
        this.r.setOnMenuItemClickListener((PopupMenu$OnMenuItemClickListener)new PopupMenu$OnMenuItemClickListener() {
            public boolean onMenuItemClick(final MenuItem menuItem) {
                com.facebook.ads.internal.view.i.this.w = false;
                if (!TextUtils.isEmpty((CharSequence)pageDetails.c())) {
                    com.facebook.ads.internal.w.e.g.a(new g(), com.facebook.ads.internal.view.i.this.getContext(), Uri.parse(pageDetails.c()), s);
                }
                return true;
            }
        });
        if (Build$VERSION.SDK_INT >= 14) {
            this.r.setOnDismissListener(this.y);
        }
        this.a(v <= 0);
    }
    
    public void a(final com.facebook.ads.internal.view.i.a u) {
        this.u = u;
        this.u.getEventBus().a(this.b, this.c);
    }
    
    public void a(final boolean x) {
        final int n = 8;
        this.x = x;
        this.m.setVisibility(0);
        final CircularProgressView o = this.o;
        int visibility;
        if (x) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        o.setVisibility(visibility);
        final ImageView n2 = this.n;
        int visibility2 = n;
        if (x) {
            visibility2 = 0;
        }
        n2.setVisibility(visibility2);
        ((LinearLayout$LayoutParams)this.q.getLayoutParams()).leftMargin = 0;
    }
    
    public boolean a() {
        return this.x;
    }
    
    public void b() {
        this.x = false;
        this.m.setVisibility(8);
        this.o.setVisibility(8);
        this.n.setVisibility(8);
        ((LinearLayout$LayoutParams)this.q.getLayoutParams()).leftMargin = com.facebook.ads.internal.view.i.g;
    }
    
    public void b(final com.facebook.ads.internal.view.i.a a) {
        if (this.u != null) {
            this.u.getEventBus().b(this.b, this.c);
            this.u = null;
        }
    }
    
    public void b(final boolean b) {
        final ImageView l = this.l;
        int visibility;
        if (b) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        l.setVisibility(visibility);
    }
    
    public void c() {
        com.facebook.ads.internal.w.b.x.b((View)this.p);
    }
    
    public void d() {
        if (Build$VERSION.SDK_INT >= 14) {
            this.r.setOnDismissListener((PopupMenu$OnDismissListener)null);
        }
        this.r.dismiss();
        if (Build$VERSION.SDK_INT >= 14) {
            this.r.setOnDismissListener(this.y);
        }
    }
    
    public void e() {
        if (this.w && Build$VERSION.SDK_INT >= 14) {
            this.r.show();
        }
    }
    
    public void setCloseButtonStyle(final a a) {
        if (this.n == null) {
            return;
        }
        com.facebook.ads.internal.w.c.b b = null;
        switch (i$9.a[a.ordinal()]) {
            default: {
                b = com.facebook.ads.internal.w.c.b.q;
                break;
            }
            case 1: {
                b = com.facebook.ads.internal.w.c.b.o;
                break;
            }
            case 2: {
                b = com.facebook.ads.internal.w.c.b.u;
                break;
            }
        }
        this.n.setImageBitmap(com.facebook.ads.internal.w.c.c.a(b));
    }
    
    public void setPageDetailsVisibility(final int visibility) {
        this.q.setVisibility(visibility);
    }
    
    public void setProgress(final float progressWithAnimation) {
        this.o.setProgressWithAnimation(progressWithAnimation);
    }
    
    public void setShowPageDetails(final boolean b) {
        this.q.removeAllViews();
        if (b) {
            this.q.addView((View)this.p);
        }
    }
    
    public void setToolbarListener(final b t) {
        this.t = t;
    }
    
    public enum a
    {
        a, 
        b, 
        c;
    }
    
    public interface b
    {
        void a();
    }
}
