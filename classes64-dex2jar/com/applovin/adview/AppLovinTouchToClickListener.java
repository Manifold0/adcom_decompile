// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnClickListener;
import android.content.Context;
import android.view.View$OnTouchListener;

public class AppLovinTouchToClickListener implements View$OnTouchListener
{
    private long a;
    private float b;
    private float c;
    private Context d;
    private View$OnClickListener e;
    
    public AppLovinTouchToClickListener(final Context d, final View$OnClickListener e) {
        this.d = d;
        this.e = e;
    }
    
    private float a(final float n) {
        return n / this.d.getResources().getDisplayMetrics().density;
    }
    
    private float a(float n, float n2, final float n3, final float n4) {
        n -= n3;
        n2 -= n4;
        return this.a((float)Math.sqrt(n * n + n2 * n2));
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0: {
                this.a = System.currentTimeMillis();
                this.b = motionEvent.getX();
                this.c = motionEvent.getY();
                break;
            }
            case 1: {
                if (System.currentTimeMillis() - this.a < 1000L && this.a(this.b, this.c, motionEvent.getX(), motionEvent.getY()) < 10.0f) {
                    this.e.onClick(view);
                    break;
                }
                break;
            }
        }
        return true;
    }
}
