package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.component.g */
public class C2300g extends RelativeLayout {
    /* renamed from: a */
    protected int f5450a = ((int) (4.0f * C2600x.f6420b));
    /* renamed from: b */
    private final Path f5451b = new Path();
    /* renamed from: c */
    private final RectF f5452c = new RectF();

    public C2300g(Context context) {
        super(context);
        C2600x.m6680a((View) this, 0);
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    protected void onDraw(Canvas canvas) {
        this.f5452c.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.f5451b.reset();
        this.f5451b.addRoundRect(this.f5452c, (float) this.f5450a, (float) this.f5450a, Direction.CW);
        canvas.clipPath(this.f5451b);
        super.onDraw(canvas);
    }

    public void setCornerRadius(int i) {
        this.f5450a = (int) (((float) i) * C2600x.f6420b);
    }
}
