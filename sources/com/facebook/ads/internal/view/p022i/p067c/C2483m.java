package com.facebook.ads.internal.view.p022i.p067c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.Button;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.i.c.m */
public class C2483m extends Button {
    /* renamed from: a */
    private final Path f5995a;
    /* renamed from: b */
    private final Path f5996b;
    /* renamed from: c */
    private final Paint f5997c;
    /* renamed from: d */
    private final Path f5998d;
    /* renamed from: e */
    private boolean f5999e;

    public C2483m(Context context) {
        this(context, false);
    }

    public C2483m(Context context, final boolean z) {
        super(context);
        this.f5999e = false;
        this.f5995a = new Path();
        this.f5996b = new Path();
        this.f5998d = new Path();
        this.f5997c = new Paint(this) {
            /* renamed from: b */
            final /* synthetic */ C2483m f5994b;
        };
        setClickable(true);
        C2600x.m6680a((View) this, 0);
    }

    protected void onDraw(Canvas canvas) {
        if (canvas.isHardwareAccelerated() && VERSION.SDK_INT < 17) {
            setLayerType(1, null);
        }
        float max = ((float) Math.max(canvas.getWidth(), canvas.getHeight())) / 100.0f;
        if (this.f5999e) {
            this.f5998d.rewind();
            this.f5998d.moveTo(26.5f * max, 15.5f * max);
            this.f5998d.lineTo(26.5f * max, 84.5f * max);
            this.f5998d.lineTo(90.0f * max, 50.0f * max);
            this.f5998d.lineTo(26.5f * max, max * 15.5f);
            this.f5998d.close();
            canvas.drawPath(this.f5998d, this.f5997c);
        } else {
            this.f5995a.rewind();
            this.f5995a.moveTo(29.0f * max, 21.0f * max);
            this.f5995a.lineTo(29.0f * max, 79.0f * max);
            this.f5995a.lineTo(45.0f * max, 79.0f * max);
            this.f5995a.lineTo(45.0f * max, 21.0f * max);
            this.f5995a.lineTo(29.0f * max, 21.0f * max);
            this.f5995a.close();
            this.f5996b.rewind();
            this.f5996b.moveTo(55.0f * max, 21.0f * max);
            this.f5996b.lineTo(55.0f * max, 79.0f * max);
            this.f5996b.lineTo(71.0f * max, 79.0f * max);
            this.f5996b.lineTo(71.0f * max, 21.0f * max);
            this.f5996b.lineTo(55.0f * max, max * 21.0f);
            this.f5996b.close();
            canvas.drawPath(this.f5995a, this.f5997c);
            canvas.drawPath(this.f5996b, this.f5997c);
        }
        super.onDraw(canvas);
    }

    public void setChecked(boolean z) {
        this.f5999e = z;
        refreshDrawableState();
        invalidate();
    }
}
