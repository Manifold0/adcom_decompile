package com.facebook.ads.internal.view.p056b;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

@TargetApi(19)
/* renamed from: com.facebook.ads.internal.view.b.b */
public class C2230b extends ProgressBar {
    /* renamed from: a */
    private static final int f5171a = Color.argb(26, 0, 0, 0);
    /* renamed from: b */
    private static final int f5172b = Color.rgb(88, 144, 255);
    /* renamed from: c */
    private Rect f5173c = new Rect();
    /* renamed from: d */
    private Paint f5174d = new Paint();

    public C2230b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setIndeterminate(false);
        setMax(100);
        ColorDrawable colorDrawable = new ColorDrawable(0);
        ClipDrawable clipDrawable = new ClipDrawable(new ColorDrawable(f5172b), 3, 1);
        setProgressDrawable(new LayerDrawable(new Drawable[]{colorDrawable, clipDrawable}));
        this.f5174d.setStyle(Style.FILL);
        this.f5174d.setColor(f5171a);
    }

    protected synchronized void onDraw(Canvas canvas) {
        canvas.drawRect(this.f5173c, this.f5174d);
        super.onDraw(canvas);
    }

    protected synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f5173c.set(0, 0, getMeasuredWidth(), 2);
    }

    public synchronized void setProgress(int i) {
        super.setProgress(i == 100 ? 0 : Math.max(i, 5));
    }
}
