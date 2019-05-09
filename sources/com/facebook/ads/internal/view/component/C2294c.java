package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import java.util.Locale;

/* renamed from: com.facebook.ads.internal.view.component.c */
public class C2294c extends Button {
    /* renamed from: a */
    public static final int f5423a = ((int) (16.0f * C2600x.f6420b));
    /* renamed from: b */
    private static final int f5424b = ((int) (4.0f * C2600x.f6420b));
    /* renamed from: c */
    private final Paint f5425c;
    /* renamed from: d */
    private final RectF f5426d;
    /* renamed from: e */
    private final boolean f5427e;

    public C2294c(Context context, boolean z, boolean z2, C1876h c1876h) {
        int e;
        int blendARGB;
        super(context);
        this.f5427e = z;
        C2600x.m6687a((TextView) this, false, 16);
        setGravity(17);
        setPadding(f5423a, f5423a, f5423a, f5423a);
        if (c1876h != null) {
            setTextColor(c1876h.m4292f(z2));
            e = c1876h.m4291e(z2);
            blendARGB = ColorUtils.blendARGB(e, ViewCompat.MEASURED_STATE_MASK, 0.1f);
        } else {
            setBackgroundColor(0);
            setTextColor(0);
            blendARGB = 0;
            e = 0;
        }
        this.f5425c = new Paint();
        setButtonColor(e);
        this.f5426d = new RectF();
        if (!z) {
            Drawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(blendARGB));
            stateListDrawable.addState(new int[0], new ColorDrawable(e));
            C2600x.m6681a((View) this, stateListDrawable);
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.f5427e) {
            this.f5426d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            canvas.drawRoundRect(this.f5426d, (float) f5424b, (float) f5424b, this.f5425c);
        }
        super.onDraw(canvas);
    }

    public void setButtonColor(int i) {
        this.f5425c.setStyle(Style.FILL);
        this.f5425c.setColor(i);
    }

    public void setText(String str) {
        super.setText(str.toUpperCase(Locale.US));
    }
}
