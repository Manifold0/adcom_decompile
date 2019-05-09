// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import android.graphics.Canvas;
import android.view.View$OnClickListener;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.a;
import android.util.DisplayMetrics;
import android.view.View;
import com.facebook.ads.internal.w.b.x;
import android.graphics.Paint$Style;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.i;
import android.content.Context;
import android.graphics.Paint;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.a.c;

public class l extends c
{
    private final j a;
    private final com.facebook.ads.internal.view.i.b.l b;
    private final d c;
    private final m d;
    private final Paint e;
    
    public l(final Context context) {
        this(context, false);
    }
    
    public l(final Context context, final boolean b) {
        super(context);
        this.a = new j() {
            @Override
            public void a(final i i) {
                l.this.d.setChecked(true);
            }
        };
        this.b = new com.facebook.ads.internal.view.i.b.l() {
            @Override
            public void a(final k k) {
                l.this.d.setChecked(false);
            }
        };
        this.c = new d() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                l.this.d.setChecked(true);
            }
        };
        this.d = new m(context, b);
        final DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams((int)(displayMetrics.density * 23.76), (int)(displayMetrics.density * 23.76));
        layoutParams.addRule(13);
        this.d.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.d.setChecked(true);
        this.d.setClickable(false);
        (this.e = new Paint()).setStyle(Paint$Style.FILL);
        if (b) {
            this.e.setColor(-1728053248);
        }
        else {
            this.e.setColor(-1);
            this.e.setAlpha(204);
        }
        x.a((View)this, 0);
        this.addView((View)this.d);
        this.setGravity(17);
        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams((int)(displayMetrics.density * 72.0), (int)(displayMetrics.density * 72.0));
        layoutParams2.addRule(13);
        this.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
    }
    
    @Override
    protected void a() {
        super.a();
        if (this.getVideoView() != null) {
            this.getVideoView().getEventBus().a(this.a, this.b, this.c);
        }
        this.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (com.facebook.ads.internal.view.i.a.c.this.getVideoView() == null) {
                    return;
                }
                switch (l$5.a[com.facebook.ads.internal.view.i.a.c.this.getVideoView().getState().ordinal()]) {
                    default: {}
                    case 1:
                    case 2:
                    case 3:
                    case 4: {
                        com.facebook.ads.internal.view.i.a.c.this.getVideoView().a(com.facebook.ads.internal.view.i.a.a.b);
                    }
                    case 5: {
                        com.facebook.ads.internal.view.i.a.c.this.getVideoView().a(true);
                    }
                }
            }
        });
    }
    
    @Override
    protected void b() {
        this.setOnClickListener((View$OnClickListener)null);
        if (this.getVideoView() != null) {
            this.getVideoView().getEventBus().b(this.c, this.b, this.a);
        }
        super.b();
    }
    
    protected void onDraw(final Canvas canvas) {
        final int min = Math.min(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getHeight() - this.getPaddingTop() - this.getPaddingBottom());
        final int n = min / 2;
        canvas.drawCircle((float)(this.getPaddingLeft() + n), (float)(min / 2 + this.getPaddingTop()), (float)n, this.e);
        super.onDraw(canvas);
    }
}
