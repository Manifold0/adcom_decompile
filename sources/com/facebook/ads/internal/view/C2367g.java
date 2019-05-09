package com.facebook.ads.internal.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.p025w.p026b.C2600x;

/* renamed from: com.facebook.ads.internal.view.g */
public class C2367g extends View {
    /* renamed from: a */
    private Paint f5727a;
    /* renamed from: b */
    private Paint f5728b;
    /* renamed from: c */
    private Paint f5729c;
    /* renamed from: d */
    private int f5730d;
    /* renamed from: e */
    private boolean f5731e;

    public C2367g(Context context) {
        this(context, 60, true);
    }

    public C2367g(Context context, int i, boolean z) {
        super(context);
        this.f5730d = i;
        this.f5731e = z;
        if (z) {
            this.f5727a = new Paint();
            this.f5727a.setColor(-3355444);
            this.f5727a.setStyle(Style.STROKE);
            this.f5727a.setStrokeWidth(3.0f);
            this.f5727a.setAntiAlias(true);
            this.f5728b = new Paint();
            this.f5728b.setColor(-1287371708);
            this.f5728b.setStyle(Style.FILL);
            this.f5728b.setAntiAlias(true);
            this.f5729c = new Paint();
            this.f5729c.setColor(-1);
            this.f5729c.setStyle(Style.STROKE);
            this.f5729c.setStrokeWidth(6.0f);
            this.f5729c.setAntiAlias(true);
        }
        float f = C2600x.f6420b;
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (((float) this.f5730d) * f), (int) (f * ((float) this.f5730d)));
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        setLayoutParams(layoutParams);
    }

    protected void onDraw(Canvas canvas) {
        if (this.f5731e) {
            if (canvas.isHardwareAccelerated() && VERSION.SDK_INT < 17) {
                setLayerType(1, null);
            }
            int min = Math.min(canvas.getWidth(), canvas.getHeight());
            int i = min / 2;
            int i2 = min / 2;
            int i3 = (i * 2) / 3;
            canvas.drawCircle((float) i, (float) i2, (float) i3, this.f5727a);
            canvas.drawCircle((float) i, (float) i2, (float) (i3 - 2), this.f5728b);
            int i4 = min / 3;
            int i5 = min / 3;
            canvas.drawLine((float) i4, (float) i5, (float) (i4 * 2), (float) (i5 * 2), this.f5729c);
            canvas.drawLine((float) (i4 * 2), (float) i5, (float) i4, (float) (i5 * 2), this.f5729c);
        }
        super.onDraw(canvas);
    }
}
