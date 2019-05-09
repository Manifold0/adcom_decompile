// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import android.graphics.Canvas;
import java.util.Map;
import java.util.HashMap;
import com.facebook.ads.internal.view.i.b.b;
import android.net.Uri;
import android.view.View$OnClickListener;
import com.facebook.ads.internal.view.i.a;
import android.util.DisplayMetrics;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View;
import com.facebook.ads.internal.w.b.x;
import android.graphics.Paint$Style;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.Paint;
import android.widget.TextView;
import com.facebook.ads.internal.view.i.a.c;

public class e extends c
{
    private final String a;
    private final TextView b;
    private final com.facebook.ads.internal.s.c c;
    private final String d;
    private final Paint e;
    private final RectF f;
    
    public e(final Context context, final String a, final com.facebook.ads.internal.s.c c, final String d, final String text) {
        super(context);
        this.a = a;
        this.c = c;
        this.d = d;
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        (this.b = new TextView(this.getContext())).setTextColor(-3355444);
        this.b.setTextSize(16.0f);
        this.b.setPadding((int)(displayMetrics.density * 6.0f), (int)(displayMetrics.density * 4.0f), (int)(displayMetrics.density * 6.0f), (int)(displayMetrics.density * 4.0f));
        (this.e = new Paint()).setStyle(Paint$Style.FILL);
        this.e.setColor(-16777216);
        this.e.setAlpha(178);
        this.f = new RectF();
        x.a((View)this, 0);
        this.b.setText((CharSequence)text);
        this.addView((View)this.b, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-2, -2));
    }
    
    @Override
    protected void a() {
        super.a();
        this.b.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (com.facebook.ads.internal.view.i.a.c.this.getVideoView() != null) {
                    final Uri parse = Uri.parse(com.facebook.ads.internal.view.i.c.e.this.a);
                    com.facebook.ads.internal.view.i.a.c.this.getVideoView().getEventBus().a(new com.facebook.ads.internal.view.i.b.b(parse));
                    final com.facebook.ads.internal.a.b a = com.facebook.ads.internal.a.c.a(com.facebook.ads.internal.view.i.c.e.this.getContext(), com.facebook.ads.internal.view.i.c.e.this.c, com.facebook.ads.internal.view.i.c.e.this.d, parse, new HashMap<String, String>());
                    if (a != null) {
                        a.a();
                    }
                }
            }
        });
    }
    
    @Override
    protected void b() {
        this.b.setOnClickListener((View$OnClickListener)null);
        super.b();
    }
    
    protected void onDraw(final Canvas canvas) {
        this.f.set(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight());
        canvas.drawRoundRect(this.f, 0.0f, 0.0f, this.e);
        super.onDraw(canvas);
    }
}
