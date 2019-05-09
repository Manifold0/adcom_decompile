package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Region.Op;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

public class an extends ao {
    /* renamed from: a */
    private int f7200a = 0;
    /* renamed from: b */
    private final Matrix f7201b = new Matrix();
    /* renamed from: c */
    private final float[] f7202c = new float[2];

    public an(Context context) {
        super(context);
    }

    public int getRotationCount() {
        return this.f7200a;
    }

    public void setRotationCount(int rotationCount) {
        this.f7200a = rotationCount & 3;
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.f7200a % 2 == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void dispatchDraw(Canvas canvas) {
        if (this.f7200a == 0) {
            super.dispatchDraw(canvas);
            return;
        }
        canvas.save();
        int width = getWidth();
        int height = getHeight();
        canvas.clipRect(0.0f, 0.0f, (float) width, (float) height, Op.REPLACE);
        try {
            View view = (ViewGroup) getParent();
            try {
                View view2 = (ViewGroup) view.getParent();
                if ((view2 instanceof ScrollView) || (view2 instanceof HorizontalScrollView)) {
                    view = view2;
                }
            } catch (Exception e) {
            }
            int left = getLeft() - view.getScrollX();
            int top = getTop() - view.getScrollY();
            canvas.clipRect((float) (0 - left), (float) (0 - top), (float) (view.getWidth() - left), (float) (view.getHeight() - top), Op.INTERSECT);
        } catch (Exception e2) {
        }
        canvas.rotate((float) (this.f7200a * 90));
        switch (this.f7200a) {
            case 1:
                canvas.translate(0.0f, (float) (-width));
                break;
            case 2:
                canvas.translate((float) (-width), (float) (-height));
                break;
            case 3:
                canvas.translate((float) (-height), 0.0f);
                break;
            default:
                throw new IllegalStateException();
        }
        this.f7201b.setRotate((float) (this.f7200a * -90));
        switch (this.f7200a) {
            case 1:
                this.f7201b.postTranslate(0.0f, (float) (width - 1));
                break;
            case 2:
                this.f7201b.postTranslate((float) (width - 1), (float) (height - 1));
                break;
            case 3:
                this.f7201b.postTranslate((float) (height - 1), 0.0f);
                break;
            default:
                throw new IllegalStateException();
        }
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (this.f7200a == 0) {
            return super.dispatchTouchEvent(event);
        }
        float[] fArr = this.f7202c;
        fArr[0] = event.getX();
        fArr[1] = event.getY();
        this.f7201b.mapPoints(fArr);
        event.setLocation(fArr[0], fArr[1]);
        return super.dispatchTouchEvent(event);
    }
}
