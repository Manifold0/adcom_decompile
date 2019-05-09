package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.C1381h;

public class ay extends ImageView {
    /* renamed from: a */
    protected TextView f3109a = null;
    /* renamed from: b */
    private C1381h f3110b = null;

    public ay(Context context) {
        super(context);
    }

    /* renamed from: a */
    public void m3471a(C1381h c1381h) {
        if (c1381h != null && c1381h.m3155d() && this.f3110b != c1381h) {
            this.f3110b = c1381h;
            setImageDrawable(new BitmapDrawable(c1381h.m3156e()));
        }
    }

    public void setImageBitmap(Bitmap adImage) {
        this.f3110b = null;
        setImageDrawable(new BitmapDrawable(adImage));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m3470a(canvas);
    }

    /* renamed from: a */
    protected void m3470a(Canvas canvas) {
        if (this.f3109a != null) {
            this.f3109a.layout(0, 0, canvas.getWidth(), canvas.getHeight());
            this.f3109a.setEnabled(isEnabled());
            this.f3109a.setSelected(isSelected());
            if (isFocused()) {
                this.f3109a.requestFocus();
            } else {
                this.f3109a.clearFocus();
            }
            this.f3109a.setPressed(isPressed());
            this.f3109a.draw(canvas);
        }
    }
}
