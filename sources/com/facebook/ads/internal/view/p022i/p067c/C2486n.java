package com.facebook.ads.internal.view.p022i.p067c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C2373p;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2413o;
import com.facebook.ads.internal.view.p022i.p065a.C2390b;

/* renamed from: com.facebook.ads.internal.view.i.c.n */
public class C2486n extends View implements C2390b {
    /* renamed from: a */
    private final Paint f6002a = new Paint();
    /* renamed from: b */
    private final Rect f6003b;
    /* renamed from: c */
    private float f6004c;
    /* renamed from: d */
    private final C2373p f6005d = new C24841(this);
    /* renamed from: e */
    private final C1818d f6006e = new C24852(this);
    @Nullable
    /* renamed from: f */
    private C2394a f6007f;

    /* renamed from: com.facebook.ads.internal.view.i.c.n$1 */
    class C24841 extends C2373p {
        /* renamed from: a */
        final /* synthetic */ C2486n f6000a;

        C24841(C2486n c2486n) {
            this.f6000a = c2486n;
        }

        /* renamed from: a */
        public void m6385a(C2413o c2413o) {
            if (this.f6000a.f6007f != null) {
                int duration = this.f6000a.f6007f.getDuration();
                if (duration > 0) {
                    this.f6000a.f6004c = ((float) this.f6000a.f6007f.getCurrentPositionInMillis()) / ((float) duration);
                } else {
                    this.f6000a.f6004c = 0.0f;
                }
                this.f6000a.postInvalidate();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.n$2 */
    class C24852 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ C2486n f6001a;

        C24852(C2486n c2486n) {
            this.f6001a = c2486n;
        }

        /* renamed from: a */
        public void m6387a(C2406c c2406c) {
            if (this.f6001a.f6007f != null) {
                this.f6001a.f6004c = 0.0f;
                this.f6001a.postInvalidate();
            }
        }
    }

    public C2486n(Context context) {
        super(context);
        this.f6002a.setStyle(Style.FILL);
        this.f6002a.setColor(-9528840);
        this.f6003b = new Rect();
    }

    /* renamed from: a */
    public void mo5597a(C2394a c2394a) {
        this.f6007f = c2394a;
        c2394a.getEventBus().m5029a(this.f6005d, this.f6006e);
    }

    /* renamed from: b */
    public void mo5598b(C2394a c2394a) {
        c2394a.getEventBus().m5031b(this.f6006e, this.f6005d);
        this.f6007f = null;
    }

    public void draw(Canvas canvas) {
        this.f6003b.set(0, 0, (int) (((float) getWidth()) * this.f6004c), getHeight());
        canvas.drawRect(this.f6003b, this.f6002a);
        super.draw(canvas);
    }
}
