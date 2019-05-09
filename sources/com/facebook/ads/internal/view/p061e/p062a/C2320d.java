package com.facebook.ads.internal.view.p061e.p062a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.p021o.C1809f;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C2555y;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.C2423b;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import com.facebook.ads.internal.view.p022i.p067c.C2440b;
import com.facebook.ads.internal.view.p022i.p067c.C2451d;
import com.facebook.ads.internal.view.p022i.p067c.C2451d.C2450a;
import com.facebook.ads.internal.view.p022i.p067c.C2456f;
import com.facebook.ads.internal.view.p022i.p067c.C2459g;
import com.facebook.ads.internal.view.p022i.p067c.C2481l;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.e.a.d */
public class C2320d extends FrameLayout {
    /* renamed from: a */
    private static final int f5533a = ((int) (16.0f * C2600x.f6420b));
    /* renamed from: b */
    private final C2085c f5534b;
    /* renamed from: c */
    private C2555y f5535c;
    /* renamed from: d */
    private C2456f f5536d;
    /* renamed from: e */
    private C2481l f5537e;
    /* renamed from: f */
    private C2459g f5538f;
    @Nullable
    /* renamed from: g */
    private C2423b f5539g;

    /* renamed from: com.facebook.ads.internal.view.e.a.d$1 */
    class C23191 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2320d f5532a;

        C23191(C2320d c2320d) {
            this.f5532a = c2320d;
        }

        public void onClick(View view) {
            this.f5532a.f5537e.performClick();
        }
    }

    public C2320d(Context context, C2085c c2085c) {
        super(context);
        this.f5534b = c2085c;
        setUpView(context);
    }

    private void setUpPlugins(Context context) {
        this.f5535c.m6162d();
        this.f5538f = new C2459g(context);
        this.f5535c.m6158a(this.f5538f);
        this.f5536d = new C2456f(context);
        this.f5535c.m6158a(new C2440b(context));
        this.f5535c.m6158a(this.f5536d);
        this.f5537e = new C2481l(context, true);
        this.f5535c.m6158a(this.f5537e);
        this.f5535c.m6158a(new C2451d(this.f5537e, C2450a.FADE_OUT_ON_PLAY, true, true));
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        layoutParams.setMargins(f5533a, f5533a, f5533a, f5533a);
        this.f5536d.setLayoutParams(layoutParams);
        this.f5535c.addView(this.f5536d);
    }

    private void setUpVideo(Context context) {
        this.f5535c = new C2555y(context);
        this.f5535c.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        C2600x.m6679a(this.f5535c);
        addView(this.f5535c);
        setOnClickListener(new C23191(this));
    }

    private void setUpView(Context context) {
        setUpVideo(context);
        setUpPlugins(context);
    }

    /* renamed from: a */
    public void m5978a() {
        this.f5535c.m6160a(true);
    }

    /* renamed from: a */
    public void m5979a(C1809f c1809f) {
        this.f5535c.getEventBus().m5030a(c1809f);
    }

    /* renamed from: a */
    public void m5980a(C2085c c2085c, String str, Map<String, String> map) {
        m5983c();
        this.f5539g = new C2423b(getContext(), c2085c, this.f5535c, str, (Map) map);
    }

    /* renamed from: a */
    public void m5981a(C2389a c2389a) {
        this.f5535c.m6157a(c2389a);
    }

    /* renamed from: b */
    public boolean m5982b() {
        return this.f5535c.m6168j();
    }

    /* renamed from: c */
    public void m5983c() {
        if (this.f5539g != null) {
            this.f5539g.m6244a();
            this.f5539g = null;
        }
    }

    @VisibleForTesting
    public C2394a getSimpleVideoView() {
        return this.f5535c;
    }

    public float getVolume() {
        return this.f5535c.getVolume();
    }

    public void setPlaceholderUrl(String str) {
        this.f5538f.setImage(str);
    }

    public void setVideoURI(String str) {
        this.f5535c.setVideoURI(str);
    }

    public void setVolume(float f) {
        this.f5535c.setVolume(f);
        this.f5536d.m6315a();
    }
}
