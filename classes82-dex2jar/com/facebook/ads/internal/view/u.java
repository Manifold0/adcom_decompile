// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.view.MotionEvent;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.support.v4.widget.ViewDragHelper$Callback;
import android.view.ViewGroup;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import com.facebook.ads.internal.view.component.a.l;
import android.widget.RelativeLayout;

public class u extends RelativeLayout
{
    private l a;
    private ViewDragHelper b;
    @Nullable
    private a c;
    private boolean d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    
    public u(final Context context, final l a, final int g, final int i) {
        super(context);
        this.d = true;
        this.e = 0;
        this.f = 0;
        this.b = ViewDragHelper.create((ViewGroup)this, 1.0f, (ViewDragHelper$Callback)new b());
        this.a = a;
        this.i = i;
        this.a.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        this.g = g;
        this.h = this.g;
        this.a.offsetTopAndBottom(this.g);
        this.f = this.g;
        this.addView((View)this.a);
        this.setBackgroundColor(0);
    }
    
    private void d() {
        this.d = true;
        if (this.c != null) {
            this.c.a();
        }
    }
    
    static /* synthetic */ void d(final u u) {
        u.d = false;
        if (u.c != null) {
            u.c.b();
        }
    }
    
    public void a() {
        this.a.offsetTopAndBottom(this.g);
        this.f = this.g;
        this.d();
    }
    
    public void b() {
        this.a.offsetTopAndBottom(this.i);
        this.f = this.i;
    }
    
    public boolean c() {
        return this.d;
    }
    
    public void computeScroll() {
        if (this.b.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation((View)this);
            return;
        }
        this.f = this.a.getTop();
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        final int n = (int)motionEvent.getX();
        final int n2 = (int)motionEvent.getY();
        return this.d && this.b.isViewUnder((View)this.a, n, n2) && this.a.getScrollY() == 0;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        this.a.offsetTopAndBottom(this.f);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int n = (int)motionEvent.getX();
        final int n2 = (int)motionEvent.getY();
        this.a.a(motionEvent);
        if (this.b.isViewUnder((View)this.a, n, n2)) {
            this.b.processTouchEvent(motionEvent);
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void setDragListener(final a c) {
        this.c = c;
    }
    
    public void setDragRange(final int g) {
        this.g = g;
        this.b.smoothSlideViewTo((View)this.a, 0, this.g);
    }
    
    public interface a
    {
        void a();
        
        void b();
    }
    
    private class b extends ViewDragHelper$Callback
    {
        public int clampViewPositionVertical(final View view, final int n, int paddingTop) {
            paddingTop = u.this.getPaddingTop();
            return Math.min(Math.max(n, paddingTop), u.this.g);
        }
        
        public int getViewVerticalDragRange(final View view) {
            return u.this.g;
        }
        
        public void onViewDragStateChanged(final int n) {
            if (n == u.this.e) {
                return;
            }
            if (n == 0 && (u.this.e == 1 || u.this.e == 2)) {
                if (u.this.h == u.this.i) {
                    u.d(u.this);
                }
                else if (u.this.h == u.this.g) {
                    u.this.d();
                }
            }
            u.this.e = n;
        }
        
        public void onViewPositionChanged(final View view, final int n, final int n2, final int n3, final int n4) {
            u.this.h = n2;
        }
        
        public void onViewReleased(final View view, final float n, final float n2) {
            int n3 = 1;
            if (u.this.h == u.this.i) {
                u.this.d = false;
            }
            else {
                if (u.this.h == u.this.g) {
                    u.this.d = true;
                    return;
                }
                if (n2 <= 800.0) {
                    if (n2 < -800.0) {
                        n3 = 0;
                    }
                    else if (u.this.h <= u.this.g / 2) {
                        if (u.this.h < u.this.g / 2) {
                            n3 = 0;
                        }
                        else {
                            n3 = 0;
                        }
                    }
                }
                int n4;
                if (n3 != 0) {
                    n4 = u.this.g;
                }
                else {
                    n4 = u.this.i;
                }
                if (u.this.b.settleCapturedViewAt(0, n4)) {
                    ViewCompat.postInvalidateOnAnimation((View)u.this);
                }
            }
        }
        
        public boolean tryCaptureView(final View view, final int n) {
            return view == u.this.a;
        }
    }
}
