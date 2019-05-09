// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.e;

import android.view.MotionEvent;
import android.graphics.Canvas;
import android.content.ActivityNotFoundException;
import android.util.Log;
import java.util.Map;
import com.facebook.ads.internal.w.b.k;
import java.util.HashMap;
import com.facebook.ads.internal.view.i.b.b;
import android.net.Uri;
import android.view.View$OnClickListener;
import com.facebook.ads.internal.w.b.x;
import android.graphics.Paint$Style;
import android.view.View;
import android.graphics.Typeface;
import android.widget.TextView;
import com.facebook.ads.internal.s.c;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.Paint;
import com.facebook.ads.internal.w.b.w;
import android.widget.RelativeLayout;

public class a extends RelativeLayout
{
    private final w a;
    private final String b;
    private com.facebook.ads.internal.view.i.a c;
    private final Paint d;
    private final RectF e;
    
    public a(final Context context, final w a, final String b, final String text, final int color, final com.facebook.ads.internal.view.i.a c, final c c2, final String s) {
        super(context);
        this.a = a;
        this.b = b;
        this.c = c;
        final TextView textView = new TextView(context);
        textView.setTextColor(-1);
        textView.setTextSize(16.0f);
        textView.setText((CharSequence)text);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        this.setGravity(17);
        this.addView((View)textView);
        (this.d = new Paint()).setStyle(Paint$Style.FILL);
        this.d.setColor(color);
        this.e = new RectF();
        x.a((View)this, 0);
        this.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                try {
                    final Uri parse = Uri.parse(com.facebook.ads.internal.view.e.a.this.b);
                    com.facebook.ads.internal.view.e.a.this.c.getEventBus().a(new b(parse));
                    final HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("touch", k.a(com.facebook.ads.internal.view.e.a.this.a.e()));
                    final com.facebook.ads.internal.a.b a = com.facebook.ads.internal.a.c.a(com.facebook.ads.internal.view.e.a.this.getContext(), c2, s, parse, hashMap);
                    if (a != null) {
                        a.a();
                    }
                }
                catch (ActivityNotFoundException ex) {
                    Log.e(String.valueOf(a.class), "Error while opening " + com.facebook.ads.internal.view.e.a.this.b, (Throwable)ex);
                }
                catch (Exception ex2) {
                    Log.e(String.valueOf(a.class), "Error executing action", (Throwable)ex2);
                }
            }
        });
    }
    
    protected void onDraw(final Canvas canvas) {
        final float density = this.getContext().getResources().getDisplayMetrics().density;
        this.e.set(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight());
        canvas.drawRoundRect(this.e, 10.0f * density, density * 10.0f, this.d);
        super.onDraw(canvas);
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        this.a.a(motionEvent, this.getRootView(), (View)this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
