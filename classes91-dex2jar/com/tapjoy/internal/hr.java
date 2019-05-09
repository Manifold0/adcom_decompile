// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.graphics.Bitmap;
import java.util.Iterator;
import android.view.View$OnClickListener;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.ViewGroup$LayoutParams;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.ImageView$ScaleType;
import java.lang.ref.WeakReference;
import android.content.Context;
import java.util.ArrayList;
import android.widget.RelativeLayout;

public final class hr extends RelativeLayout
{
    private gu a;
    private a b;
    private af c;
    private int d;
    private int e;
    private hd f;
    private ArrayList g;
    private ArrayList h;
    
    public hr(final Context context, final gu a, final a b) {
        super(context);
        this.c = af.a;
        this.d = 0;
        this.e = 0;
        this.f = null;
        this.g = null;
        this.h = null;
        this.a = a;
        this.b = b;
    }
    
    private void a() {
        final Iterator iterator = this.a.a.iterator();
        hd hd = null;
        hd f;
        while (true) {
            f = hd;
            if (!iterator.hasNext()) {
                break;
            }
            f = iterator.next();
            if (f.a == this.c) {
                break;
            }
            if (f.a != af.a) {
                continue;
            }
            hd = f;
        }
        this.removeAllViews();
        if (this.g != null) {
            final Iterator<WeakReference<hj>> iterator2 = (Iterator<WeakReference<hj>>)this.g.iterator();
            while (iterator2.hasNext()) {
                final hj hj = iterator2.next().get();
                if (hj != null) {
                    hj.c();
                }
            }
            this.g.clear();
        }
        if (this.h != null) {
            final Iterator<WeakReference<hj>> iterator3 = (Iterator<WeakReference<hj>>)this.h.iterator();
            while (iterator3.hasNext()) {
                final hj hj2 = iterator3.next().get();
                if (hj2 != null) {
                    hj2.c();
                }
            }
            this.h.clear();
        }
        if (f != null) {
            this.f = f;
            final Context context = this.getContext();
            for (final hc tag : f.c) {
                final RelativeLayout relativeLayout = new RelativeLayout(context);
                hj hj3;
                if (tag.l.c != null) {
                    hj3 = new hj(context);
                    hj3.setScaleType(ImageView$ScaleType.FIT_XY);
                    hj3.a(tag.l.d, tag.l.c);
                    if (this.g == null) {
                        this.g = new ArrayList();
                    }
                    this.g.add(new WeakReference<hj>(hj3));
                }
                else {
                    hj3 = null;
                }
                hj hj4;
                if (tag.m != null && tag.m.c != null) {
                    hj4 = new hj(context);
                    hj4.setScaleType(ImageView$ScaleType.FIT_XY);
                    hj4.a(tag.m.d, tag.m.c);
                    if (this.h == null) {
                        this.h = new ArrayList();
                    }
                    this.h.add(new WeakReference<hj>(hj4));
                }
                else {
                    hj4 = null;
                }
                final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(0, 0);
                final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-1, -1);
                final Bitmap b = tag.l.b;
                Bitmap b2;
                if (tag.m != null) {
                    b2 = tag.m.b;
                }
                else {
                    b2 = null;
                }
                Object o;
                if (b != null) {
                    o = new BitmapDrawable((Resources)null, b);
                }
                else {
                    o = null;
                }
                BitmapDrawable bitmapDrawable;
                if (b2 != null) {
                    bitmapDrawable = new BitmapDrawable((Resources)null, b2);
                }
                else {
                    bitmapDrawable = null;
                }
                if (o != null) {
                    ag.a((View)relativeLayout, (Drawable)o);
                }
                if (hj3 != null) {
                    relativeLayout.addView((View)hj3, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
                    hj3.a();
                }
                if (hj4 != null) {
                    relativeLayout.addView((View)hj4, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
                    hj4.setVisibility(4);
                }
                relativeLayout.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
                    final /* synthetic */ BitmapDrawable d = (BitmapDrawable)o;
                    
                    public final boolean onTouch(final View view, final MotionEvent motionEvent) {
                        final int n = 1;
                        if (motionEvent.getAction() == 0) {
                            if (hj4 != null || bitmapDrawable != null) {
                                if (hj3 != null) {
                                    hj3.b();
                                    hj3.setVisibility(4);
                                }
                                ag.a(view, null);
                            }
                            if (bitmapDrawable != null) {
                                ag.a(view, (Drawable)bitmapDrawable);
                            }
                            else if (hj4 != null) {
                                hj4.setVisibility(0);
                                hj4.a();
                                return false;
                            }
                        }
                        else if (motionEvent.getAction() == 1) {
                            final float x = motionEvent.getX();
                            final float y = motionEvent.getY();
                            int n2 = n;
                            if (x >= 0.0f) {
                                n2 = n;
                                if (x < view.getWidth()) {
                                    n2 = n;
                                    if (y >= 0.0f) {
                                        if (y >= view.getHeight()) {
                                            n2 = n;
                                        }
                                        else {
                                            n2 = 0;
                                        }
                                    }
                                }
                            }
                            if (n2 != 0) {
                                if (this.d != null) {
                                    ag.a(view, (Drawable)this.d);
                                }
                                else if (bitmapDrawable != null) {
                                    ag.a(view, null);
                                }
                            }
                            if (hj4 != null) {
                                hj4.b();
                                hj4.setVisibility(4);
                            }
                            if ((hj4 != null || bitmapDrawable != null) && hj3 != null && n2 != 0) {
                                hj3.setVisibility(0);
                                hj3.a();
                                return false;
                            }
                        }
                        return false;
                    }
                });
                relativeLayout.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public final void onClick(final View view) {
                        if (hj4 != null) {
                            hj4.b();
                            relativeLayout.removeView((View)hj4);
                        }
                        if (hj3 != null) {
                            hj3.b();
                            relativeLayout.removeView((View)hj3);
                        }
                        hr.this.b.a(tag);
                    }
                });
                relativeLayout.setTag((Object)tag);
                this.addView((View)relativeLayout, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
            }
        }
    }
    
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.b.a();
    }
    
    protected final void onMeasure(final int n, final int n2) {
        final int size = View$MeasureSpec.getSize(n);
        final int size2 = View$MeasureSpec.getSize(n2);
        if (size >= size2) {
            if (this.c != af.c) {
                this.c = af.c;
                this.a();
            }
        }
        else if (this.c != af.b) {
            this.c = af.b;
            this.a();
        }
        Label_0555: {
            if (this.d != size || this.e != size2) {
                this.d = size;
                this.e = size2;
                float n3 = (float)size;
                float n4 = (float)size2;
                while (true) {
                    Label_0562: {
                        if (this.f == null || this.f.b == null) {
                            break Label_0562;
                        }
                        final float n5 = this.f.b.y * n3 / this.f.b.x / n4;
                        float n7;
                        float n9;
                        if (n5 < 1.0f) {
                            final float n6 = this.f.b.y * n3 / this.f.b.x;
                            n7 = (n4 - n6) / 2.0f;
                            final float n8 = 0.0f;
                            n4 = n6;
                            n9 = n8;
                        }
                        else {
                            if (n5 <= 1.0f) {
                                break Label_0562;
                            }
                            final float n10 = this.f.b.x * n4 / this.f.b.y;
                            n9 = (n3 - n10) / 2.0f;
                            n7 = 0.0f;
                            n3 = n10;
                        }
                        for (final View view : ah.a((ViewGroup)this)) {
                            final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)view.getLayoutParams();
                            final hc hc = (hc)view.getTag();
                            final float a = hc.a.a(n3, n4);
                            final float a2 = hc.b.a(n3, n4);
                            final float a3 = hc.c.a(n3, n4);
                            final float a4 = hc.d.a(n3, n4);
                            final int e = hc.e;
                            final int f = hc.f;
                            int n11 = e;
                            float n12 = a;
                            if (e == 14) {
                                n11 = 9;
                                n12 = a + (n3 - a3) / 2.0f;
                            }
                            int n13 = f;
                            float n14 = a2;
                            if (f == 15) {
                                n13 = 10;
                                n14 = a2 + (n4 - a4) / 2.0f;
                            }
                            relativeLayout$LayoutParams.addRule(n11, -1);
                            relativeLayout$LayoutParams.addRule(n13, -1);
                            relativeLayout$LayoutParams.width = Math.round(a3);
                            relativeLayout$LayoutParams.height = Math.round(a4);
                            if (n11 == 9) {
                                relativeLayout$LayoutParams.leftMargin = Math.round(n9 + n12);
                            }
                            else if (n11 == 11) {
                                relativeLayout$LayoutParams.rightMargin = Math.round(n9 + n12);
                            }
                            if (n13 == 10) {
                                relativeLayout$LayoutParams.topMargin = Math.round(n7 + n14);
                            }
                            else {
                                if (n13 != 12) {
                                    continue;
                                }
                                relativeLayout$LayoutParams.bottomMargin = Math.round(n7 + n14);
                            }
                        }
                        break Label_0555;
                    }
                    float n9 = 0.0f;
                    float n7 = 0.0f;
                    continue;
                }
            }
        }
        super.onMeasure(n, n2);
    }
    
    protected final void onVisibilityChanged(final View view, final int n) {
        super.onVisibilityChanged(view, n);
        if (n == 0) {
            if (this.h != null) {
                final Iterator<WeakReference<hj>> iterator = this.h.iterator();
                while (iterator.hasNext()) {
                    final hj hj = iterator.next().get();
                    if (hj != null) {
                        hj.setVisibility(4);
                        hj.b();
                    }
                }
            }
            if (this.g != null) {
                final Iterator<WeakReference<hj>> iterator2 = this.g.iterator();
                while (iterator2.hasNext()) {
                    final hj hj2 = iterator2.next().get();
                    if (hj2 != null) {
                        hj2.setVisibility(0);
                        hj2.a();
                    }
                }
            }
        }
        else {
            if (this.g != null) {
                final Iterator<WeakReference<hj>> iterator3 = this.g.iterator();
                while (iterator3.hasNext()) {
                    final hj hj3 = iterator3.next().get();
                    if (hj3 != null) {
                        hj3.b();
                    }
                }
            }
            if (this.h != null) {
                final Iterator<WeakReference<hj>> iterator4 = this.h.iterator();
                while (iterator4.hasNext()) {
                    final hj hj4 = iterator4.next().get();
                    if (hj4 != null) {
                        hj4.b();
                    }
                }
            }
        }
    }
    
    public interface a
    {
        void a();
        
        void a(final hc p0);
    }
}
