package com.applovin.adview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class AppLovinTouchToClickListener implements OnTouchListener {
    /* renamed from: a */
    private long f1560a;
    /* renamed from: b */
    private float f1561b;
    /* renamed from: c */
    private float f1562c;
    /* renamed from: d */
    private Context f1563d;
    /* renamed from: e */
    private OnClickListener f1564e;

    public AppLovinTouchToClickListener(Context context, OnClickListener onClickListener) {
        this.f1563d = context;
        this.f1564e = onClickListener;
    }

    /* renamed from: a */
    private float m1770a(float f) {
        return f / this.f1563d.getResources().getDisplayMetrics().density;
    }

    /* renamed from: a */
    private float m1771a(float f, float f2, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        return m1770a((float) Math.sqrt((double) ((f5 * f5) + (f6 * f6))));
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.f1560a = System.currentTimeMillis();
                this.f1561b = motionEvent.getX();
                this.f1562c = motionEvent.getY();
                break;
            case 1:
                if (System.currentTimeMillis() - this.f1560a < 1000 && m1771a(this.f1561b, this.f1562c, motionEvent.getX(), motionEvent.getY()) < 10.0f) {
                    this.f1564e.onClick(view);
                    break;
                }
        }
        return true;
    }
}
