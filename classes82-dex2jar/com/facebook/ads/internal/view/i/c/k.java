// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.RelativeLayout$LayoutParams;
import android.graphics.PorterDuff$Mode;
import android.widget.ProgressBar;
import android.util.TypedValue;
import com.facebook.ads.internal.o.d;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.a.c;

public class k extends c
{
    private final f<m> a;
    
    public k(final Context context) {
        this(context, null);
    }
    
    public k(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public k(final Context context, final AttributeSet set, int n) {
        super(context, set, n);
        this.a = new f<m>() {
            @Override
            public Class<m> a() {
                return m.class;
            }
            
            @Override
            public void a(final m m) {
                k.this.setVisibility(8);
            }
        };
        n = (int)TypedValue.applyDimension(1, 40.0f, this.getResources().getDisplayMetrics());
        final ProgressBar progressBar = new ProgressBar(this.getContext());
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable().setColorFilter(-1, PorterDuff$Mode.SRC_IN);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(n, n);
        relativeLayout$LayoutParams.addRule(13);
        this.addView((View)progressBar, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
    }
    
    @Override
    protected void a() {
        super.a();
        this.setVisibility(0);
        if (this.getVideoView() != null) {
            this.getVideoView().getEventBus().a(this.a);
        }
    }
    
    @Override
    protected void b() {
        if (this.getVideoView() != null) {
            this.getVideoView().getEventBus().b(this.a);
        }
        this.setVisibility(8);
        super.b();
    }
}
