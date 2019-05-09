package com.facebook.ads.internal.view.p022i.p067c;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.p021o.C1809f;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.p019c.C1802e;
import com.facebook.ads.internal.view.p019c.C2252d;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.facebook.ads.internal.view.p022i.p065a.C2391c;

/* renamed from: com.facebook.ads.internal.view.i.c.g */
public class C2459g extends C2391c {
    /* renamed from: a */
    private final ImageView f5939a;
    /* renamed from: b */
    private final C1809f<C2411k> f5940b = new C24571(this);
    /* renamed from: c */
    private final C1809f<C2406c> f5941c = new C24582(this);

    /* renamed from: com.facebook.ads.internal.view.i.c.g$1 */
    class C24571 extends C1809f<C2411k> {
        /* renamed from: a */
        final /* synthetic */ C2459g f5937a;

        C24571(C2459g c2459g) {
            this.f5937a = c2459g;
        }

        /* renamed from: a */
        public Class<C2411k> mo5359a() {
            return C2411k.class;
        }

        /* renamed from: a */
        public void m6320a(C2411k c2411k) {
            this.f5937a.setVisibility(8);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.g$2 */
    class C24582 extends C1809f<C2406c> {
        /* renamed from: a */
        final /* synthetic */ C2459g f5938a;

        C24582(C2459g c2459g) {
            this.f5938a = c2459g;
        }

        /* renamed from: a */
        public Class<C2406c> mo5359a() {
            return C2406c.class;
        }

        /* renamed from: a */
        public void m6323a(C2406c c2406c) {
            this.f5938a.setVisibility(0);
        }
    }

    public C2459g(Context context) {
        super(context);
        this.f5939a = new ImageView(context);
        this.f5939a.setScaleType(ScaleType.FIT_CENTER);
        C2600x.m6680a(this.f5939a, (int) ViewCompat.MEASURED_STATE_MASK);
        this.f5939a.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.f5939a);
    }

    /* renamed from: a */
    protected void mo5608a() {
        super.mo5608a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().m5029a(this.f5940b, this.f5941c);
        }
    }

    /* renamed from: a */
    public void m6325a(@Nullable String str, @Nullable C1802e c1802e) {
        if (str == null) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        C2252d a = new C2252d(this.f5939a).m5771a();
        if (c1802e != null) {
            a.m5773a(c1802e);
        }
        a.m5775a(str);
    }

    /* renamed from: b */
    protected void mo5609b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().m5031b(this.f5941c, this.f5940b);
        }
        super.mo5609b();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f5939a.layout(0, 0, i3 - i, i4 - i2);
    }

    public void setImage(@Nullable String str) {
        m6325a(str, null);
    }
}
