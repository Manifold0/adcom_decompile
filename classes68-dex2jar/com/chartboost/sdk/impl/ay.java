// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Canvas;
import android.content.Context;
import com.chartboost.sdk.Libraries.h;
import android.widget.TextView;
import android.widget.ImageView;

public class ay extends ImageView
{
    protected TextView a;
    private h b;
    
    public ay(final Context context) {
        super(context);
        this.b = null;
        this.a = null;
    }
    
    protected void a(final Canvas canvas) {
        if (this.a != null) {
            this.a.layout(0, 0, canvas.getWidth(), canvas.getHeight());
            this.a.setEnabled(this.isEnabled());
            this.a.setSelected(this.isSelected());
            if (this.isFocused()) {
                this.a.requestFocus();
            }
            else {
                this.a.clearFocus();
            }
            this.a.setPressed(this.isPressed());
            this.a.draw(canvas);
        }
    }
    
    public void a(final h b) {
        if (b == null || !b.d() || this.b == b) {
            return;
        }
        this.b = b;
        this.setImageDrawable((Drawable)new BitmapDrawable(b.e()));
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        this.a(canvas);
    }
    
    public void setImageBitmap(final Bitmap bitmap) {
        this.b = null;
        this.setImageDrawable((Drawable)new BitmapDrawable(bitmap));
    }
}
