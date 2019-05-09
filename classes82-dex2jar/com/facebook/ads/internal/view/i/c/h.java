// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import com.facebook.ads.internal.view.i.a.a;
import android.view.MotionEvent;
import com.facebook.ads.internal.o.f;
import android.util.DisplayMetrics;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.i;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.n;
import android.view.View$OnTouchListener;
import com.facebook.ads.internal.view.i.a.c;

public class h extends c implements View$OnTouchListener
{
    private final n a;
    private final j b;
    private final l c;
    private final d d;
    private final m e;
    
    public h(final Context context) {
        this(context, null);
    }
    
    public h(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public h(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.a = new n() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.m m) {
                h.this.setVisibility(0);
            }
        };
        this.b = new j() {
            @Override
            public void a(final i i) {
                h.this.e.setChecked(true);
            }
        };
        this.c = new l() {
            @Override
            public void a(final k k) {
                h.this.e.setChecked(false);
            }
        };
        this.d = new d() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                h.this.e.setChecked(true);
            }
        };
        final DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        (this.e = new m(context)).setChecked(true);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams((int)(displayMetrics.density * 25.0f), (int)(displayMetrics.density * 25.0f));
        this.setVisibility(8);
        this.addView((View)this.e, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.setClickable(true);
        this.setFocusable(true);
    }
    
    @Override
    protected void a() {
        super.a();
        this.e.setOnTouchListener((View$OnTouchListener)this);
        this.setOnTouchListener((View$OnTouchListener)this);
        if (this.getVideoView() != null) {
            this.getVideoView().getEventBus().a(this.a, this.d, this.b, this.c);
        }
    }
    
    @Override
    protected void b() {
        if (this.getVideoView() != null) {
            this.getVideoView().getEventBus().b(this.c, this.b, this.d, this.a);
        }
        this.setOnTouchListener((View$OnTouchListener)null);
        this.e.setOnTouchListener((View$OnTouchListener)null);
        super.b();
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            final com.facebook.ads.internal.view.i.a videoView = this.getVideoView();
            if (videoView != null) {
                if (videoView.getState() == com.facebook.ads.internal.view.i.d.d.c || videoView.getState() == com.facebook.ads.internal.view.i.d.d.e || videoView.getState() == com.facebook.ads.internal.view.i.d.d.g) {
                    videoView.a(com.facebook.ads.internal.view.i.a.a.b);
                    return true;
                }
                if (videoView.getState() == com.facebook.ads.internal.view.i.d.d.d) {
                    videoView.a(true);
                    return false;
                }
            }
        }
        return false;
    }
}
