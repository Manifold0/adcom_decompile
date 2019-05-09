// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.ArrayList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Point;
import android.graphics.Rect;
import java.util.Iterator;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.RelativeLayout;

public final class ht extends RelativeLayout implements View$OnClickListener
{
    private boolean a;
    private float b;
    private View c;
    private View d;
    private FrameLayout e;
    private ImageView f;
    private hq g;
    private gx h;
    private a i;
    
    public ht(Context context, final gx h, final a i) {
        final boolean b = true;
        super(context);
        this.b = 1.0f;
        this.h = h;
        this.i = i;
        context = this.getContext();
        (this.c = new View(context)).setId(1);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(0, 0);
        relativeLayout$LayoutParams.addRule(13);
        this.addView(this.c, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.d = new View(context);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(0, 0);
        relativeLayout$LayoutParams2.addRule(13);
        this.addView(this.d, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        this.e = new FrameLayout(context);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams3 = new RelativeLayout$LayoutParams(0, 0);
        relativeLayout$LayoutParams3.addRule(13);
        this.addView((View)this.e, (ViewGroup$LayoutParams)relativeLayout$LayoutParams3);
        (this.f = new ImageView(context)).setOnClickListener((View$OnClickListener)this);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams4 = new RelativeLayout$LayoutParams(0, 0);
        relativeLayout$LayoutParams4.addRule(7, this.c.getId());
        relativeLayout$LayoutParams4.addRule(6, this.c.getId());
        this.addView((View)this.f, (ViewGroup$LayoutParams)relativeLayout$LayoutParams4);
        Label_0328: {
            if (this.h.m != null) {
                final gy m = this.h.m;
                while (true) {
                    Label_0384: {
                        if (m.a == null) {
                            break Label_0384;
                        }
                        int n = b ? 1 : 0;
                        if (m.b == null) {
                            if (m.c == null) {
                                break Label_0384;
                            }
                            n = (b ? 1 : 0);
                        }
                        if (n != 0) {
                            (this.g = new hq(context)).setOnClickListener((View$OnClickListener)this);
                            final RelativeLayout$LayoutParams relativeLayout$LayoutParams5 = new RelativeLayout$LayoutParams(0, 0);
                            relativeLayout$LayoutParams5.addRule(5, this.d.getId());
                            relativeLayout$LayoutParams5.addRule(8, this.d.getId());
                            this.addView((View)this.g, (ViewGroup$LayoutParams)relativeLayout$LayoutParams5);
                        }
                        break Label_0328;
                    }
                    int n = 0;
                    continue;
                }
            }
        }
        this.f.setImageBitmap(h.c.b);
        if (this.g != null && h.m != null && h.m.a != null) {
            this.g.setImageBitmap(h.m.a.b);
        }
    }
    
    private int a(final int n) {
        return (int)(n * this.b);
    }
    
    public final void onClick(final View view) {
        if (view == this.f) {
            this.i.a();
        }
        else {
            if (view != null && view == this.g) {
                final hq g = this.g;
                g.a = !g.a;
                g.a();
                g.invalidate();
                this.i.b();
                return;
            }
            if (view.getTag() instanceof gv) {
                this.i.a((gv)view.getTag());
            }
        }
    }
    
    protected final void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
    }
    
    protected final void onMeasure(final int n, final int n2) {
        final int n3 = 15;
        int x = 0;
        final int size = View$MeasureSpec.getSize(n);
        final int size2 = View$MeasureSpec.getSize(n2);
        if (this.a) {
            this.b = Math.min(size / 480.0f, size2 / 320.0f);
        }
        else {
            this.b = Math.min(size / 320.0f, size2 / 480.0f);
        }
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)this.c.getLayoutParams();
        int n4;
        if (this.a) {
            n4 = 480;
        }
        else {
            n4 = 320;
        }
        relativeLayout$LayoutParams.width = this.a(n4);
        int n5;
        if (this.a) {
            n5 = 320;
        }
        else {
            n5 = 480;
        }
        relativeLayout$LayoutParams.height = this.a(n5);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = (RelativeLayout$LayoutParams)this.d.getLayoutParams();
        int n6;
        if (this.a) {
            n6 = 448;
        }
        else {
            n6 = 290;
        }
        relativeLayout$LayoutParams2.width = this.a(n6);
        int n7;
        if (this.a) {
            n7 = 290;
        }
        else {
            n7 = 448;
        }
        relativeLayout$LayoutParams2.height = this.a(n7);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams3 = (RelativeLayout$LayoutParams)this.e.getLayoutParams();
        relativeLayout$LayoutParams3.width = relativeLayout$LayoutParams2.width;
        relativeLayout$LayoutParams3.height = relativeLayout$LayoutParams2.height;
        for (final View view : ah.a((ViewGroup)this.e)) {
            final FrameLayout$LayoutParams frameLayout$LayoutParams = (FrameLayout$LayoutParams)view.getLayoutParams();
            final Rect a = ((gv)view.getTag()).a;
            frameLayout$LayoutParams.width = this.a(a.width());
            frameLayout$LayoutParams.height = this.a(a.height());
            frameLayout$LayoutParams.leftMargin = this.a(a.left);
            frameLayout$LayoutParams.topMargin = this.a(a.top);
        }
        final int a2 = this.a(0);
        this.f.setPadding(a2, a2, a2, a2);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams4 = (RelativeLayout$LayoutParams)this.f.getLayoutParams();
        relativeLayout$LayoutParams4.width = this.a(30);
        relativeLayout$LayoutParams4.height = relativeLayout$LayoutParams4.width;
        relativeLayout$LayoutParams4.rightMargin = -a2 + this.a(this.h.d.x);
        relativeLayout$LayoutParams4.topMargin = -a2 + this.a(this.h.d.y);
        Label_0626: {
            if (this.g != null) {
                int n8;
                if (this.a) {
                    n8 = 16;
                }
                else {
                    n8 = 15;
                }
                final int a3 = this.a(n8);
                int n9;
                if (this.a) {
                    n9 = n3;
                }
                else {
                    n9 = 16;
                }
                final int a4 = this.a(n9);
                this.g.setPadding(a2, a2, a2, a2);
                final RelativeLayout$LayoutParams relativeLayout$LayoutParams5 = (RelativeLayout$LayoutParams)this.g.getLayoutParams();
                relativeLayout$LayoutParams5.width = this.a(26);
                relativeLayout$LayoutParams5.height = relativeLayout$LayoutParams5.width;
                while (true) {
                    Label_0692: {
                        if (this.h.m == null) {
                            break Label_0692;
                        }
                        Point point;
                        if (this.a) {
                            final gy m = this.h.m;
                            if (m.b == null) {
                                point = m.c;
                            }
                            else {
                                point = m.b;
                            }
                        }
                        else {
                            final gy i = this.h.m;
                            if (i.c == null) {
                                point = i.b;
                            }
                            else {
                                point = i.c;
                            }
                        }
                        if (point == null) {
                            break Label_0692;
                        }
                        x = point.x;
                        final int y = point.y;
                        relativeLayout$LayoutParams5.leftMargin = this.a(x) + a3;
                        relativeLayout$LayoutParams5.topMargin = this.a(y) + a4;
                        break Label_0626;
                    }
                    final int y = 0;
                    continue;
                }
            }
        }
        super.onMeasure(n, n2);
    }
    
    public final void setLandscape(final boolean a) {
        this.a = a;
        Bitmap bitmap;
        Bitmap bitmap2;
        ArrayList list;
        if (a) {
            bitmap = this.h.b.b;
            bitmap2 = this.h.f.b;
            list = this.h.j;
        }
        else {
            bitmap = this.h.a.b;
            bitmap2 = this.h.e.b;
            list = this.h.i;
        }
        ag.a(this.c, (Drawable)new BitmapDrawable((Resources)null, bitmap));
        ag.a(this.d, (Drawable)new BitmapDrawable((Resources)null, bitmap2));
        if (this.e.getChildCount() > 0) {
            this.e.removeAllViews();
        }
        final Context context = this.getContext();
        for (final gv tag : list) {
            final View view = new View(context);
            view.setTag((Object)tag);
            view.setOnClickListener((View$OnClickListener)this);
            this.e.addView(view, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(0, 0, 51));
        }
    }
    
    public interface a
    {
        void a();
        
        void a(final gv p0);
        
        void b();
    }
}
