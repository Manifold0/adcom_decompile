// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.graphics.Paint$Style;
import com.facebook.ads.internal.w.b.x;
import android.graphics.RectF;
import android.graphics.Paint;
import android.widget.TextView;
import android.util.Log;
import android.view.View$OnClickListener;
import com.facebook.ads.internal.view.i.a;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.o.d;
import android.content.Context;
import com.facebook.ads.internal.view.i.b.o;
import com.facebook.ads.internal.o.f;
import java.util.concurrent.atomic.AtomicBoolean;
import com.facebook.ads.internal.view.i.a.c;

public class i extends c
{
    private final a a;
    private final int b;
    private final String c;
    private final String d;
    private final AtomicBoolean e;
    private final f<o> f;
    
    public i(final Context context, final int b, final String c, final String d) {
        super(context);
        this.f = new f<o>() {
            @Override
            public Class<o> a() {
                return o.class;
            }
            
            @Override
            public void a(final o o) {
                if (i.this.e.get() || com.facebook.ads.internal.view.i.a.c.this.getVideoView() == null) {
                    return;
                }
                final int n = i.this.b - com.facebook.ads.internal.view.i.a.c.this.getVideoView().getCurrentPositionInMillis() / 1000;
                if (n > 0) {
                    i.this.a.setText((CharSequence)(i.this.c + ' ' + n));
                    return;
                }
                i.this.a.setText((CharSequence)i.this.d);
                i.this.e.set(true);
            }
        };
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = new AtomicBoolean(false);
        (this.a = new a(context)).setText((CharSequence)(this.c + ' ' + b));
        this.addView((View)this.a, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-2, -2));
    }
    
    public void a() {
        super.a();
        if (this.getVideoView() != null) {
            this.getVideoView().getEventBus().a(this.f);
        }
        this.a.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (!i.this.e.get()) {
                    Log.i("SkipPlugin", "User clicked skip before the ads is allowed to skip.");
                }
                else if (com.facebook.ads.internal.view.i.a.c.this.getVideoView() != null) {
                    com.facebook.ads.internal.view.i.a.c.this.getVideoView().f();
                }
            }
        });
    }
    
    public void b() {
        if (this.getVideoView() != null) {
            this.a.setOnClickListener((View$OnClickListener)null);
            this.getVideoView().getEventBus().b(this.f);
        }
        super.b();
    }
    
    private static class a extends TextView
    {
        private final Paint a;
        private final Paint b;
        private final RectF c;
        
        public a(final Context context) {
            super(context);
            final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            x.a((View)this, 0);
            this.setTextColor(-3355444);
            this.setPadding((int)(displayMetrics.density * 9.0f), (int)(displayMetrics.density * 5.0f), (int)(displayMetrics.density * 9.0f), (int)(displayMetrics.density * 5.0f));
            this.setTextSize(18.0f);
            (this.a = new Paint()).setStyle(Paint$Style.STROKE);
            this.a.setColor(-10066330);
            this.a.setStrokeWidth(1.0f);
            this.a.setAntiAlias(true);
            (this.b = new Paint()).setStyle(Paint$Style.FILL);
            this.b.setColor(-1895825408);
            this.c = new RectF();
        }
        
        protected void onDraw(final Canvas canvas) {
            if (this.getText().length() == 0) {
                return;
            }
            final int width = this.getWidth();
            final int height = this.getHeight();
            this.c.set((float)0, (float)0, (float)width, (float)height);
            canvas.drawRoundRect(this.c, 6.0f, 6.0f, this.b);
            this.c.set((float)2, (float)2, (float)(width - 2), (float)(height - 2));
            canvas.drawRoundRect(this.c, 6.0f, 6.0f, this.a);
            super.onDraw(canvas);
        }
    }
}
