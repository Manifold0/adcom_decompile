package com.facebook.ads.internal.view.p019c;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.ads.internal.p025w.p026b.C2580j;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.c.b */
public class C2249b extends FrameLayout {
    /* renamed from: a */
    private final ImageView f5258a;
    /* renamed from: b */
    private int f5259b;
    /* renamed from: c */
    private int f5260c;

    public C2249b(Context context) {
        super(context);
        this.f5258a = new ImageView(context);
        m5756a();
    }

    public C2249b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5258a = new ImageView(context, attributeSet);
        m5756a();
    }

    public C2249b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5258a = new ImageView(context, attributeSet, i);
        m5756a();
    }

    @TargetApi(21)
    public C2249b(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f5258a = new ImageView(context, attributeSet, i, i2);
        m5756a();
    }

    /* renamed from: a */
    private void m5756a() {
        this.f5258a.setScaleType(ScaleType.FIT_XY);
        addView(this.f5258a, new LayoutParams(-2, -2));
        C2580j.m6643a(this.f5258a, C2580j.INTERNAL_AD_MEDIA);
    }

    /* renamed from: a */
    public void m5757a(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap2 != null) {
            C2600x.m6681a((View) this, new BitmapDrawable(getContext().getResources(), bitmap2));
        } else {
            C2600x.m6680a((View) this, 0);
        }
        if (bitmap != null) {
            this.f5259b = bitmap.getWidth();
            this.f5260c = bitmap.getHeight();
            this.f5258a.setImageBitmap(Bitmap.createBitmap(bitmap));
            return;
        }
        this.f5258a.setImageDrawable(null);
    }

    public ImageView getBodyImageView() {
        return this.f5258a;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        if (this.f5259b <= 0 || this.f5260c <= 0) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        float min = Math.min(((float) i5) / ((float) this.f5259b), ((float) i6) / ((float) this.f5260c));
        int i7 = (int) (((float) this.f5259b) * min);
        int i8 = (int) (min * ((float) this.f5260c));
        i5 = (i5 / 2) + i;
        i6 = (i6 / 2) + i2;
        this.f5258a.layout(i5 - (i7 / 2), i6 - (i8 / 2), i5 + (i7 / 2), i6 + (i8 / 2));
    }
}
