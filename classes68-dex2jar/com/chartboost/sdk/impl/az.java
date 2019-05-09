// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.graphics.PorterDuff$Mode;
import android.view.View$OnClickListener;
import com.chartboost.sdk.Libraries.h;
import android.widget.ImageView$ScaleType;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.Button;
import android.graphics.Rect;
import android.widget.RelativeLayout;

public abstract class az extends RelativeLayout
{
    private final Rect a;
    final a c;
    protected Button d;
    boolean e;
    
    public az(final Context context) {
        this(context, null);
    }
    
    @SuppressLint({ "ClickableViewAccessibility" })
    public az(final Context context, final AttributeSet set) {
        super(context, set);
        this.a = new Rect();
        this.d = null;
        this.e = true;
        (this.c = new a(this.getContext())).setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                final boolean a = az.this.a(view, motionEvent);
                switch (motionEvent.getActionMasked()) {
                    case 0: {
                        az.this.c.a(a);
                        return a;
                    }
                    case 2: {
                        az.this.c.a(a);
                        break;
                    }
                    case 1: {
                        if (az.this.getVisibility() == 0 && az.this.isEnabled() && a) {
                            az.this.a(motionEvent);
                        }
                        az.this.c.a(false);
                        break;
                    }
                    case 3:
                    case 4: {
                        az.this.c.a(false);
                        break;
                    }
                }
                return true;
            }
        });
        this.addView((View)this.c, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
    }
    
    public TextView a() {
        if (this.d == null) {
            (this.d = new Button(this.getContext())).setGravity(17);
        }
        this.d.postInvalidate();
        return (TextView)this.d;
    }
    
    protected abstract void a(final MotionEvent p0);
    
    public void a(final ImageView$ScaleType scaleType) {
        this.c.setScaleType(scaleType);
    }
    
    public void a(final h h) {
        if (h != null && h.d()) {
            this.c.a(h);
            this.a((String)null);
        }
    }
    
    public void a(final String text) {
        if (text != null) {
            this.a().setText((CharSequence)text);
            this.addView((View)this.a(), (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
            this.c.setVisibility(8);
            this.a(false);
            this.d.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    az.this.a((MotionEvent)null);
                }
            });
        }
        else if (this.d != null) {
            this.removeView((View)this.a());
            this.d = null;
            this.c.setVisibility(0);
            this.a(true);
        }
    }
    
    public void a(final boolean e) {
        this.e = e;
    }
    
    boolean a(final View view, final MotionEvent motionEvent) {
        view.getLocalVisibleRect(this.a);
        final Rect a = this.a;
        a.left += view.getPaddingLeft();
        final Rect a2 = this.a;
        a2.top += view.getPaddingTop();
        final Rect a3 = this.a;
        a3.right -= view.getPaddingRight();
        final Rect a4 = this.a;
        a4.bottom -= view.getPaddingBottom();
        return this.a.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()));
    }
    
    private class a extends ay
    {
        private boolean c;
        
        public a(final Context context) {
            super(context);
            this.c = false;
            this.c = false;
        }
        
        protected void a(final boolean b) {
            if (az.this.e && b) {
                if (!this.c) {
                    if (this.getDrawable() != null) {
                        this.getDrawable().setColorFilter(1996488704, PorterDuff$Mode.SRC_ATOP);
                    }
                    else if (this.getBackground() != null) {
                        this.getBackground().setColorFilter(1996488704, PorterDuff$Mode.SRC_ATOP);
                    }
                    this.invalidate();
                    this.c = true;
                }
            }
            else if (this.c) {
                if (this.getDrawable() != null) {
                    this.getDrawable().clearColorFilter();
                }
                else if (this.getBackground() != null) {
                    this.getBackground().clearColorFilter();
                }
                this.invalidate();
                this.c = false;
            }
        }
        
        public boolean performClick() {
            super.performClick();
            return true;
        }
    }
}
