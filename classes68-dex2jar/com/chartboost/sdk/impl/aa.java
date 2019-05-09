// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.content.Context;
import android.graphics.Paint;
import android.widget.LinearLayout;

public class aa extends LinearLayout
{
    private final Paint a;
    private final float b;
    private int c;
    
    public aa(final Context context) {
        super(context);
        this.b = 1.0f;
        this.c = 0;
        final int round = Math.round(context.getResources().getDisplayMetrics().density * 5.0f);
        this.setPadding(round, round, round, round);
        this.setBaselineAligned(false);
        (this.a = new Paint()).setStyle(Paint$Style.FILL);
    }
    
    public void a(final int color) {
        this.a.setColor(color);
        this.invalidate();
    }
    
    public void b(final int c) {
        this.c = c;
    }
    
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final float density = this.getContext().getResources().getDisplayMetrics().density;
        if ((this.c & 0x1) > 0) {
            canvas.drawRect(0.0f, 0.0f, (float)canvas.getWidth(), 1.0f * density, this.a);
        }
        if ((this.c & 0x2) > 0) {
            canvas.drawRect(canvas.getWidth() - 1.0f * density, 0.0f, (float)canvas.getWidth(), (float)canvas.getHeight(), this.a);
        }
        if ((this.c & 0x4) > 0) {
            canvas.drawRect(0.0f, canvas.getHeight() - 1.0f * density, (float)canvas.getWidth(), (float)canvas.getHeight(), this.a);
        }
        if ((this.c & 0x8) > 0) {
            canvas.drawRect(0.0f, 0.0f, 1.0f * density, (float)canvas.getHeight(), this.a);
        }
    }
}
