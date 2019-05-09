package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.widget.ImageView;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.component.f */
public class C2299f extends ImageView {
    /* renamed from: a */
    private static final int f5444a = ((int) (8.0f * C2600x.f6420b));
    /* renamed from: b */
    private static final float[] f5445b = new float[]{(float) f5444a, (float) f5444a, (float) f5444a, (float) f5444a, (float) f5444a, (float) f5444a, (float) f5444a, (float) f5444a};
    /* renamed from: c */
    private final Path f5446c = new Path();
    /* renamed from: d */
    private final RectF f5447d = new RectF();
    /* renamed from: e */
    private float[] f5448e = f5445b;
    /* renamed from: f */
    private boolean f5449f = false;

    public C2299f(Context context) {
        super(context);
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    private float[] getRadiiForCircularImage() {
        int min = Math.min(getWidth(), getHeight()) / 2;
        return new float[]{(float) min, (float) min, (float) min, (float) min, (float) min, (float) min, (float) min, (float) min};
    }

    protected void onDraw(Canvas canvas) {
        this.f5447d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.f5446c.reset();
        this.f5446c.addRoundRect(this.f5447d, this.f5449f ? getRadiiForCircularImage() : this.f5448e, Direction.CW);
        canvas.clipPath(this.f5446c);
        super.onDraw(canvas);
    }

    public void setFullCircleCorners(boolean z) {
        this.f5449f = z;
    }

    public void setRadius(int i) {
        int i2 = (int) (((float) i) * C2600x.f6420b);
        this.f5448e = new float[]{(float) i2, (float) i2, (float) i2, (float) i2, (float) i2, (float) i2, (float) i2, (float) i2};
    }

    public void setRadius(float[] fArr) {
        this.f5448e = fArr;
    }
}
