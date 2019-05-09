// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component;

import java.util.Locale;
import android.graphics.Paint$Style;
import android.graphics.Canvas;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.graphics.ColorUtils;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.b.h;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.graphics.RectF;
import android.graphics.Paint;
import android.widget.Button;

public class c extends Button
{
    public static final int a;
    private static final int b;
    private final Paint c;
    private final RectF d;
    private final boolean e;
    
    static {
        a = (int)(16.0f * x.b);
        b = (int)(4.0f * x.b);
    }
    
    public c(final Context context, final boolean e, final boolean b, final h h) {
        super(context);
        this.e = e;
        x.a((TextView)this, false, 16);
        this.setGravity(17);
        this.setPadding(com.facebook.ads.internal.view.component.c.a, com.facebook.ads.internal.view.component.c.a, com.facebook.ads.internal.view.component.c.a, com.facebook.ads.internal.view.component.c.a);
        int e2;
        int blendARGB;
        if (h != null) {
            this.setTextColor(h.f(b));
            e2 = h.e(b);
            blendARGB = ColorUtils.blendARGB(e2, -16777216, 0.1f);
        }
        else {
            this.setBackgroundColor(0);
            this.setTextColor(0);
            blendARGB = 0;
            e2 = 0;
        }
        this.c = new Paint();
        this.setButtonColor(e2);
        this.d = new RectF();
        if (!e) {
            final StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[] { 16842919 }, (Drawable)new ColorDrawable(blendARGB));
            stateListDrawable.addState(new int[0], (Drawable)new ColorDrawable(e2));
            x.a((View)this, (Drawable)stateListDrawable);
        }
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.e) {
            this.d.set(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight());
            canvas.drawRoundRect(this.d, (float)com.facebook.ads.internal.view.component.c.b, (float)com.facebook.ads.internal.view.component.c.b, this.c);
        }
        super.onDraw(canvas);
    }
    
    public void setButtonColor(final int color) {
        this.c.setStyle(Paint$Style.FILL);
        this.c.setColor(color);
    }
    
    public void setText(final String s) {
        super.setText((CharSequence)s.toUpperCase(Locale.US));
    }
}
