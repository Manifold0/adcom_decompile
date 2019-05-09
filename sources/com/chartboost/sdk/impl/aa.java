package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.widget.LinearLayout;

public class aa extends LinearLayout {
    /* renamed from: a */
    private final Paint f2952a;
    /* renamed from: b */
    private final float f2953b = 1.0f;
    /* renamed from: c */
    private int f2954c = 0;

    public aa(Context context) {
        super(context);
        int round = Math.round(context.getResources().getDisplayMetrics().density * 5.0f);
        setPadding(round, round, round, round);
        setBaselineAligned(false);
        this.f2952a = new Paint();
        this.f2952a.setStyle(Style.FILL);
    }

    /* renamed from: a */
    public void m3337a(int i) {
        this.f2952a.setColor(i);
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = getContext().getResources().getDisplayMetrics().density;
        if ((this.f2954c & 1) > 0) {
            canvas.drawRect(0.0f, 0.0f, (float) canvas.getWidth(), 1.0f * f, this.f2952a);
        }
        if ((this.f2954c & 2) > 0) {
            canvas.drawRect(((float) canvas.getWidth()) - (1.0f * f), 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), this.f2952a);
        }
        if ((this.f2954c & 4) > 0) {
            canvas.drawRect(0.0f, ((float) canvas.getHeight()) - (1.0f * f), (float) canvas.getWidth(), (float) canvas.getHeight(), this.f2952a);
        }
        if ((this.f2954c & 8) > 0) {
            canvas.drawRect(0.0f, 0.0f, 1.0f * f, (float) canvas.getHeight(), this.f2952a);
        }
    }

    /* renamed from: b */
    public void m3338b(int i) {
        this.f2954c = i;
    }
}
