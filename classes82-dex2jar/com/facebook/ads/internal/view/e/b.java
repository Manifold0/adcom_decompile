// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.e;

import android.graphics.Canvas;
import com.facebook.ads.internal.w.b.x;
import android.graphics.Paint$Style;
import android.view.View;
import android.graphics.Typeface;
import android.widget.TextView;
import android.content.Context;
import android.graphics.RectF;
import android.graphics.Paint;
import android.widget.RelativeLayout;

public class b extends RelativeLayout
{
    private final Paint a;
    private final RectF b;
    
    public b(final Context context, final String text) {
        super(context);
        final float density = context.getResources().getDisplayMetrics().density;
        final TextView textView = new TextView(context);
        textView.setTextColor(-16777216);
        textView.setTextSize(16.0f);
        textView.setText((CharSequence)text);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        this.setGravity(17);
        final int n = (int)(density * 6.0f);
        textView.setPadding(n, n, n, n);
        this.addView((View)textView);
        (this.a = new Paint()).setStyle(Paint$Style.FILL);
        this.a.setColor(-1);
        this.b = new RectF();
        x.a((View)this, 0);
    }
    
    protected void onDraw(final Canvas canvas) {
        final float density = this.getContext().getResources().getDisplayMetrics().density;
        this.b.set(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight());
        canvas.drawRoundRect(this.b, 10.0f * density, density * 10.0f, this.a);
        super.onDraw(canvas);
    }
}
