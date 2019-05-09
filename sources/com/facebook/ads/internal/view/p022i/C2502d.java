package com.facebook.ads.internal.view.p022i;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.p022i.p065a.C2391c;
import com.facebook.ads.internal.view.p022i.p066d.C2495c;
import com.facebook.ads.internal.view.p022i.p067c.C2459g;
import java.lang.ref.WeakReference;

/* renamed from: com.facebook.ads.internal.view.i.d */
public class C2502d extends RelativeLayout {
    /* renamed from: a */
    private final C2495c f6085a;
    @Nullable
    /* renamed from: b */
    private C2459g f6086b;
    /* renamed from: c */
    private WeakReference<C2098a> f6087c;

    /* renamed from: com.facebook.ads.internal.view.i.d$a */
    public interface C2098a {
        /* renamed from: a */
        void mo5499a();
    }

    public C2502d(Context context, C2495c c2495c) {
        super(context);
        this.f6085a = c2495c;
        C2600x.m6689b((View) this.f6085a);
        addView(this.f6085a.getView(), new LayoutParams(-1, -1));
    }

    /* renamed from: a */
    public void m6441a(C2391c c2391c) {
        addView(c2391c, new LayoutParams(-1, -1));
        this.f6086b = (C2459g) c2391c;
    }

    /* renamed from: b */
    public void m6442b(C2391c c2391c) {
        C2600x.m6689b(c2391c);
        this.f6086b = null;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ((View) this.f6085a).layout(0, 0, getWidth(), getHeight());
        if (this.f6086b != null) {
            this.f6086b.layout(0, 0, getWidth(), getHeight());
        }
    }

    protected void onMeasure(int i, int i2) {
        Object obj = null;
        int videoWidth = this.f6085a.getVideoWidth();
        int videoHeight = this.f6085a.getVideoHeight();
        int defaultSize = C2502d.getDefaultSize(videoWidth, i);
        int defaultSize2 = C2502d.getDefaultSize(videoHeight, i2);
        if (videoWidth > 0 && videoHeight > 0) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            int mode2 = MeasureSpec.getMode(i2);
            defaultSize2 = MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                if (videoWidth * defaultSize2 < size * videoHeight) {
                    defaultSize = (defaultSize2 * videoWidth) / videoHeight;
                    obj = 1;
                } else if (videoWidth * defaultSize2 > size * videoHeight) {
                    defaultSize2 = (size * videoHeight) / videoWidth;
                    defaultSize = size;
                    size = 1;
                } else {
                    defaultSize = size;
                    size = 1;
                }
            } else if (mode == 1073741824) {
                defaultSize = (size * videoHeight) / videoWidth;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = size;
                    size = 1;
                } else {
                    defaultSize = size;
                    size = 1;
                }
            } else {
                if (mode2 == 1073741824) {
                    defaultSize = (defaultSize2 * videoWidth) / videoHeight;
                    if (mode == Integer.MIN_VALUE && defaultSize > size) {
                        defaultSize = size;
                        size = 1;
                    }
                } else {
                    if (mode2 != Integer.MIN_VALUE || videoHeight <= defaultSize2) {
                        defaultSize2 = videoHeight;
                        defaultSize = videoWidth;
                    } else {
                        defaultSize = (defaultSize2 * videoWidth) / videoHeight;
                    }
                    if (mode == Integer.MIN_VALUE && r1 > size) {
                        defaultSize2 = (size * videoHeight) / videoWidth;
                        defaultSize = size;
                        size = 1;
                    }
                }
                size = 1;
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
        if (obj != null && this.f6087c != null && this.f6087c.get() != null) {
            ((C2098a) this.f6087c.get()).mo5499a();
        }
    }

    public void setViewImplInflationListener(C2098a c2098a) {
        this.f6087c = new WeakReference(c2098a);
    }
}
