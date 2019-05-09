package com.facebook.ads.internal.view.p022i.p067c;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.p021o.C1809f;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.p023b.C1812l;
import com.facebook.ads.internal.view.p022i.p023b.C1814j;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C2373p;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2410i;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.facebook.ads.internal.view.p022i.p023b.C2413o;
import com.facebook.ads.internal.view.p022i.p065a.C2390b;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.facebook.ads.internal.view.i.c.o */
public class C2491o extends RelativeLayout implements C2390b {
    /* renamed from: a */
    private static final int f6012a = ((int) (6.0f * C2600x.f6420b));
    /* renamed from: b */
    private ObjectAnimator f6013b;
    /* renamed from: c */
    private AtomicInteger f6014c;
    /* renamed from: d */
    private ProgressBar f6015d;
    @Nullable
    /* renamed from: e */
    private C2394a f6016e;
    /* renamed from: f */
    private C1809f f6017f;
    /* renamed from: g */
    private C1809f f6018g;
    /* renamed from: h */
    private C1809f f6019h;
    /* renamed from: i */
    private C1809f f6020i;

    /* renamed from: com.facebook.ads.internal.view.i.c.o$1 */
    class C24871 extends C2373p {
        /* renamed from: a */
        final /* synthetic */ C2491o f6008a;

        C24871(C2491o c2491o) {
            this.f6008a = c2491o;
        }

        /* renamed from: a */
        public void m6393a(C2413o c2413o) {
            if (this.f6008a.f6016e != null) {
                C2491o.m6401a(this.f6008a, this.f6008a.f6016e.getDuration(), this.f6008a.f6016e.getCurrentPositionInMillis());
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.o$2 */
    class C24882 extends C1814j {
        /* renamed from: a */
        final /* synthetic */ C2491o f6009a;

        C24882(C2491o c2491o) {
            this.f6009a = c2491o;
        }

        /* renamed from: a */
        public void m6395a(C2410i c2410i) {
            this.f6009a.m6402b();
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.o$3 */
    class C24893 extends C1812l {
        /* renamed from: a */
        final /* synthetic */ C2491o f6010a;

        C24893(C2491o c2491o) {
            this.f6010a = c2491o;
        }

        /* renamed from: a */
        public void m6397a(C2411k c2411k) {
            if (this.f6010a.f6016e != null) {
                C2491o.m6401a(this.f6010a, this.f6010a.f6016e.getDuration(), this.f6010a.f6016e.getCurrentPositionInMillis());
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.o$4 */
    class C24904 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ C2491o f6011a;

        C24904(C2491o c2491o) {
            this.f6011a = c2491o;
        }

        /* renamed from: a */
        public void m6399a(C2406c c2406c) {
            if (this.f6011a.f6016e != null) {
                C2491o.m6404c(this.f6011a);
            }
        }
    }

    public C2491o(Context context) {
        this(context, f6012a, -12549889);
    }

    public C2491o(Context context, int i, int i2) {
        super(context);
        this.f6017f = new C24871(this);
        this.f6018g = new C24882(this);
        this.f6019h = new C24893(this);
        this.f6020i = new C24904(this);
        this.f6014c = new AtomicInteger(-1);
        this.f6015d = new ProgressBar(context, null, 16842872);
        this.f6015d.setLayoutParams(new LayoutParams(-1, i));
        setProgressBarColor(i2);
        this.f6015d.setMax(10000);
        addView(this.f6015d);
    }

    /* renamed from: a */
    static /* synthetic */ void m6401a(C2491o c2491o, int i, int i2) {
        c2491o.m6402b();
        if (c2491o.f6014c.get() < i2 && i > i2) {
            int i3 = (i2 * 10000) / i;
            int min = (Math.min(i2 + 250, i) * 10000) / i;
            c2491o.f6013b = ObjectAnimator.ofInt(c2491o.f6015d, NotificationCompat.CATEGORY_PROGRESS, new int[]{i3, min});
            c2491o.f6013b.setDuration((long) Math.min(250, i - i2));
            c2491o.f6013b.setInterpolator(new LinearInterpolator());
            c2491o.f6013b.start();
            c2491o.f6014c.set(i2);
        }
    }

    /* renamed from: b */
    private void m6402b() {
        if (this.f6013b != null) {
            this.f6013b.cancel();
            this.f6013b.setTarget(null);
            this.f6013b = null;
            this.f6015d.clearAnimation();
        }
    }

    /* renamed from: c */
    static /* synthetic */ void m6404c(C2491o c2491o) {
        c2491o.m6402b();
        c2491o.f6013b = ObjectAnimator.ofInt(c2491o.f6015d, NotificationCompat.CATEGORY_PROGRESS, new int[]{0, 0});
        c2491o.f6013b.setDuration(0);
        c2491o.f6013b.setInterpolator(new LinearInterpolator());
        c2491o.f6013b.start();
        c2491o.f6014c.set(0);
    }

    /* renamed from: a */
    public void m6405a() {
        m6402b();
        this.f6015d = null;
        this.f6016e = null;
    }

    /* renamed from: a */
    public void mo5597a(C2394a c2394a) {
        this.f6016e = c2394a;
        c2394a.getEventBus().m5029a(this.f6018g, this.f6019h, this.f6017f, this.f6020i);
    }

    /* renamed from: b */
    public void mo5598b(C2394a c2394a) {
        c2394a.getEventBus().m5031b(this.f6017f, this.f6019h, this.f6018g, this.f6020i);
        this.f6016e = null;
    }

    public void setProgressBarColor(int i) {
        ColorDrawable colorDrawable = new ColorDrawable(0);
        ColorDrawable colorDrawable2 = new ColorDrawable(0);
        ScaleDrawable scaleDrawable = new ScaleDrawable(new ColorDrawable(i), GravityCompat.START, 1.0f, -1.0f);
        Drawable layerDrawable = new LayerDrawable(new Drawable[]{colorDrawable, colorDrawable2, scaleDrawable});
        layerDrawable.setId(0, 16908288);
        layerDrawable.setId(1, 16908303);
        layerDrawable.setId(2, 16908301);
        this.f6015d.setProgressDrawable(layerDrawable);
    }
}
