package com.facebook.ads.internal.view.p022i.p067c;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.p023b.C1810n;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C2373p;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2412m;
import com.facebook.ads.internal.view.p022i.p023b.C2413o;
import com.facebook.ads.internal.view.p022i.p065a.C2390b;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.facebook.ads.internal.view.i.c.j */
public class C2473j extends View implements C2390b {
    /* renamed from: a */
    private final Paint f5968a;
    /* renamed from: b */
    private final Paint f5969b;
    /* renamed from: c */
    private final Paint f5970c;
    /* renamed from: d */
    private C2472a f5971d = C2472a.CLOSE_BUTTON_MODE;
    /* renamed from: e */
    private final Paint f5972e;
    /* renamed from: f */
    private final RectF f5973f;
    @Nullable
    /* renamed from: g */
    private C2394a f5974g;
    /* renamed from: h */
    private int f5975h;
    /* renamed from: i */
    private final AtomicInteger f5976i = new AtomicInteger(0);
    /* renamed from: j */
    private final AtomicBoolean f5977j = new AtomicBoolean(false);
    /* renamed from: k */
    private final C1810n f5978k = new C24691(this);
    /* renamed from: l */
    private final C2373p f5979l = new C24702(this);
    /* renamed from: m */
    private final C1818d f5980m = new C24713(this);

    /* renamed from: com.facebook.ads.internal.view.i.c.j$1 */
    class C24691 extends C1810n {
        /* renamed from: a */
        final /* synthetic */ C2473j f5962a;

        C24691(C2473j c2473j) {
            this.f5962a = c2473j;
        }

        /* renamed from: a */
        public void m6353a(C2412m c2412m) {
            this.f5962a.f5977j.set(true);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.j$2 */
    class C24702 extends C2373p {
        /* renamed from: a */
        final /* synthetic */ C2473j f5963a;

        C24702(C2473j c2473j) {
            this.f5963a = c2473j;
        }

        /* renamed from: a */
        public void m6355a(C2413o c2413o) {
            if (this.f5963a.f5974g != null) {
                int c = this.f5963a.f5975h;
                int duration = this.f5963a.f5974g.getDuration();
                if (c <= 0) {
                    this.f5963a.f5976i.set(0);
                } else {
                    c = Math.min(duration, c * 1000);
                    if (c != 0) {
                        this.f5963a.f5976i.set(((c - this.f5963a.f5974g.getCurrentPositionInMillis()) * 100) / c);
                    } else {
                        return;
                    }
                }
                this.f5963a.postInvalidate();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.j$3 */
    class C24713 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ C2473j f5964a;

        C24713(C2473j c2473j) {
            this.f5964a = c2473j;
        }

        /* renamed from: a */
        public void m6357a(C2406c c2406c) {
            this.f5964a.f5975h = 0;
            this.f5964a.f5976i.set(0);
            this.f5964a.postInvalidate();
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.j$a */
    public enum C2472a {
        CLOSE_BUTTON_MODE,
        SKIP_BUTTON_MODE
    }

    public C2473j(Context context, int i, int i2) {
        super(context);
        float f = getResources().getDisplayMetrics().density;
        this.f5975h = i;
        this.f5969b = new Paint();
        this.f5969b.setStyle(Style.FILL);
        this.f5969b.setColor(i2);
        this.f5970c = new Paint();
        this.f5970c.setColor(-1);
        this.f5970c.setAlpha(230);
        this.f5970c.setStyle(Style.FILL);
        this.f5970c.setStrokeWidth(1.0f * f);
        this.f5970c.setAntiAlias(true);
        this.f5968a = new Paint();
        this.f5968a.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f5968a.setStyle(Style.STROKE);
        this.f5968a.setAlpha(102);
        this.f5968a.setStrokeWidth(1.5f * f);
        this.f5968a.setAntiAlias(true);
        setLayerType(1, null);
        this.f5968a.setMaskFilter(new BlurMaskFilter(6.0f, Blur.NORMAL));
        this.f5972e = new Paint();
        this.f5972e.setColor(-10066330);
        this.f5972e.setStyle(Style.STROKE);
        this.f5972e.setStrokeWidth(f * 2.0f);
        this.f5972e.setAntiAlias(true);
        this.f5973f = new RectF();
    }

    /* renamed from: a */
    public void mo5597a(C2394a c2394a) {
        this.f5974g = c2394a;
        this.f5974g.getEventBus().m5029a(this.f5978k, this.f5979l, this.f5980m);
    }

    /* renamed from: a */
    public boolean m6364a() {
        return this.f5974g != null && (this.f5975h <= 0 || this.f5976i.get() < 0);
    }

    /* renamed from: b */
    public void mo5598b(C2394a c2394a) {
        this.f5974g.getEventBus().m5031b(this.f5980m, this.f5979l, this.f5978k);
        this.f5974g = null;
    }

    public int getSkipSeconds() {
        return this.f5975h;
    }

    protected void onDraw(Canvas canvas) {
        if (this.f5977j.get()) {
            int min = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
            int i = min / 2;
            int i2 = min / 2;
            canvas.drawCircle((float) (getPaddingLeft() + i), (float) (getPaddingTop() + i2), (float) i, this.f5968a);
            canvas.drawCircle((float) (getPaddingLeft() + i), (float) (i2 + getPaddingTop()), (float) i, this.f5970c);
            if (this.f5976i.get() > 0) {
                this.f5973f.set((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom()));
                canvas.drawArc(this.f5973f, -90.0f, ((float) (-(this.f5976i.get() * 360))) / 100.0f, true, this.f5969b);
            } else if (this.f5971d == C2472a.SKIP_BUTTON_MODE) {
                i2 = min / 4;
                min /= 3;
                Path path = new Path();
                path.moveTo((float) (getPaddingLeft() + i2), (float) (getPaddingTop() + min));
                path.lineTo((float) (getPaddingLeft() + i), (float) (getPaddingTop() + i));
                path.lineTo((float) (getPaddingLeft() + i2), (float) ((min * 2) + getPaddingTop()));
                canvas.drawPath(path, this.f5972e);
                path = new Path();
                path.moveTo((float) (getPaddingLeft() + i), (float) (getPaddingTop() + min));
                path.lineTo((float) ((i2 * 3) + getPaddingLeft()), (float) (getPaddingTop() + i));
                path.lineTo((float) (i + getPaddingLeft()), (float) ((min * 2) + getPaddingTop()));
                canvas.drawPath(path, this.f5972e);
            } else {
                int i3 = min / 3;
                int i4 = min / 3;
                canvas.drawLine((float) (getPaddingLeft() + i3), (float) (getPaddingTop() + i4), (float) ((i3 * 2) + getPaddingLeft()), (float) ((i4 * 2) + getPaddingTop()), this.f5972e);
                canvas.drawLine((float) ((i3 * 2) + getPaddingLeft()), (float) (getPaddingTop() + i4), (float) (getPaddingLeft() + i3), (float) ((i4 * 2) + getPaddingTop()), this.f5972e);
            }
            super.onDraw(canvas);
            return;
        }
        super.onDraw(canvas);
    }

    public void setButtonMode(C2472a c2472a) {
        this.f5971d = c2472a;
    }
}
