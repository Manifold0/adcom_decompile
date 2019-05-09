package com.facebook.ads.internal.view.p022i;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.p021o.C1809f;
import com.facebook.ads.internal.p021o.C2061d;
import com.facebook.ads.internal.p021o.C2062e;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.p022i.C2422c.C2392a;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2407e;
import com.facebook.ads.internal.view.p022i.p023b.C2410i;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.facebook.ads.internal.view.p022i.p023b.C2412m;
import com.facebook.ads.internal.view.p022i.p023b.C2413o;
import com.facebook.ads.internal.view.p022i.p023b.C2414q;
import com.facebook.ads.internal.view.p022i.p023b.C2415s;
import com.facebook.ads.internal.view.p022i.p023b.C2416t;
import com.facebook.ads.internal.view.p022i.p023b.C2417u;
import com.facebook.ads.internal.view.p022i.p023b.C2419w;
import com.facebook.ads.internal.view.p022i.p023b.C2420y;
import com.facebook.ads.internal.view.p022i.p023b.C2421z;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import com.facebook.ads.internal.view.p022i.p065a.C2390b;
import com.facebook.ads.internal.view.p022i.p065a.C2391c;
import com.facebook.ads.internal.view.p022i.p066d.C2393e;
import com.facebook.ads.internal.view.p022i.p066d.C2495c;
import com.facebook.ads.internal.view.p022i.p066d.C2496a;
import com.facebook.ads.internal.view.p022i.p066d.C2500b;
import com.facebook.ads.internal.view.p022i.p066d.C2501d;
import com.facebook.ads.internal.view.p022i.p067c.C2459g;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.facebook.ads.internal.view.i.a */
public class C2394a extends RelativeLayout implements C2392a, C2393e {
    /* renamed from: b */
    private static final C2412m f5780b = new C2412m();
    /* renamed from: c */
    private static final C2407e f5781c = new C2407e();
    /* renamed from: d */
    private static final C2415s f5782d = new C2415s();
    /* renamed from: e */
    private static final C2416t f5783e = new C2416t();
    /* renamed from: f */
    private static final C2411k f5784f = new C2411k();
    /* renamed from: g */
    private static final C2419w f5785g = new C2419w();
    /* renamed from: h */
    private static final C2421z f5786h = new C2421z();
    /* renamed from: i */
    private static final C2420y f5787i = new C2420y();
    /* renamed from: a */
    protected final C2495c f5788a;
    /* renamed from: j */
    private C2502d f5789j;
    /* renamed from: k */
    private final List<C2390b> f5790k = new ArrayList();
    /* renamed from: l */
    private final Handler f5791l = new Handler();
    /* renamed from: m */
    private final Handler f5792m = new Handler();
    /* renamed from: n */
    private final C2062e<C1809f, C2061d> f5793n = new C2062e();
    /* renamed from: o */
    private boolean f5794o;
    /* renamed from: p */
    private boolean f5795p;
    /* renamed from: q */
    private boolean f5796q = false;
    /* renamed from: r */
    private int f5797r = 200;
    /* renamed from: s */
    private final OnTouchListener f5798s = new C23874(this);

    /* renamed from: com.facebook.ads.internal.view.i.a$1 */
    class C23841 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2394a f5765a;

        C23841(C2394a c2394a) {
            this.f5765a = c2394a;
        }

        public void run() {
            if (!this.f5765a.f5794o) {
                this.f5765a.f5793n.m5028a(new C2413o(this.f5765a.getCurrentPositionInMillis()));
                this.f5765a.f5791l.postDelayed(this, (long) this.f5765a.f5797r);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.a$4 */
    class C23874 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2394a f5773a;

        C23874(C2394a c2394a) {
            this.f5773a = c2394a;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.f5773a.f5793n.m5028a(new C2417u(view, motionEvent));
            return false;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.a$5 */
    class C23885 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2394a f5774a;

        C23885(C2394a c2394a) {
            this.f5774a = c2394a;
        }

        public void run() {
            this.f5774a.getEventBus().m5028a(C2394a.f5782d);
        }
    }

    public C2394a(Context context) {
        super(context);
        if (C2078a.m5089a(context)) {
            this.f5788a = new C2496a(context);
        } else {
            this.f5788a = new C2500b(context);
        }
        mo5638a();
    }

    public C2394a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (C2078a.m5089a(context)) {
            this.f5788a = new C2496a(context, attributeSet);
        } else {
            this.f5788a = new C2500b(context, attributeSet);
        }
        mo5638a();
    }

    public C2394a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (C2078a.m5089a(context)) {
            this.f5788a = new C2496a(context, attributeSet, i);
        } else {
            this.f5788a = new C2500b(context, attributeSet, i);
        }
        mo5638a();
    }

    @TargetApi(21)
    public C2394a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        if (C2078a.m5089a(context)) {
            this.f5788a = new C2496a(context, attributeSet, i, i2);
        } else {
            this.f5788a = new C2500b(context, attributeSet, i, i2);
        }
        mo5638a();
    }

    /* renamed from: a */
    private void mo5638a() {
        if (mo5606h() && (this.f5788a instanceof C2496a)) {
            ((C2496a) this.f5788a).setTestMode(AdInternalSettings.isTestMode(getContext()));
        }
        this.f5788a.setRequestedVolume(1.0f);
        this.f5788a.setVideoStateChangeListener(this);
        this.f5789j = new C2502d(getContext(), this.f5788a);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(this.f5789j, layoutParams);
        setOnTouchListener(this.f5798s);
    }

    /* renamed from: b */
    private void mo5639b() {
        this.f5791l.postDelayed(new C23841(this), (long) this.f5797r);
    }

    /* renamed from: a */
    public void m6155a(int i) {
        this.f5791l.removeCallbacksAndMessages(null);
        this.f5788a.mo5611a(i);
    }

    /* renamed from: a */
    public void mo5599a(final int i, final int i2) {
        this.f5792m.post(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C2394a f5772c;

            public void run() {
                this.f5772c.f5793n.m5028a(new C2414q(i, i2));
            }
        });
        mo5639b();
    }

    /* renamed from: a */
    public void m6157a(C2389a c2389a) {
        if (this.f5794o && this.f5788a.getState() == C2501d.PLAYBACK_COMPLETED) {
            this.f5794o = false;
        }
        this.f5788a.mo5612a(c2389a);
    }

    /* renamed from: a */
    public void m6158a(C2390b c2390b) {
        this.f5790k.add(c2390b);
    }

    /* renamed from: a */
    public void mo5600a(final C2501d c2501d) {
        final int currentPositionInMillis = getCurrentPositionInMillis();
        final int duration = getDuration();
        this.f5792m.post(new Runnable(this) {
            /* renamed from: d */
            final /* synthetic */ C2394a f5769d;

            public void run() {
                if (c2501d == C2501d.PREPARED) {
                    this.f5769d.f5793n.m5028a(C2394a.f5780b);
                } else if (c2501d == C2501d.ERROR) {
                    this.f5769d.f5794o = true;
                    this.f5769d.f5793n.m5028a(C2394a.f5781c);
                } else if (c2501d == C2501d.PLAYBACK_COMPLETED) {
                    this.f5769d.f5794o = true;
                    this.f5769d.f5791l.removeCallbacksAndMessages(null);
                    this.f5769d.f5793n.m5028a(new C2406c(currentPositionInMillis, duration));
                } else if (c2501d == C2501d.STARTED) {
                    this.f5769d.f5793n.m5028a(C2394a.f5784f);
                    this.f5769d.f5791l.removeCallbacksAndMessages(null);
                    this.f5769d.mo5639b();
                } else if (c2501d == C2501d.PAUSED) {
                    this.f5769d.f5793n.m5028a(new C2410i(currentPositionInMillis));
                    this.f5769d.f5791l.removeCallbacksAndMessages(null);
                } else if (c2501d == C2501d.IDLE) {
                    this.f5769d.f5793n.m5028a(C2394a.f5783e);
                    this.f5769d.f5791l.removeCallbacksAndMessages(null);
                }
            }
        });
    }

    /* renamed from: a */
    public void m6160a(boolean z) {
        if (!m6171m()) {
            this.f5788a.mo5613a(z);
            this.f5796q = z;
        }
    }

    /* renamed from: c */
    public void m6161c() {
        for (C2390b c2390b : this.f5790k) {
            if (c2390b instanceof C2391c) {
                C2391c c2391c = (C2391c) c2390b;
                if (c2391c.getParent() == null) {
                    if (c2391c instanceof C2459g) {
                        this.f5789j.m6441a(c2391c);
                    } else {
                        addView(c2391c);
                    }
                }
            }
            c2390b.mo5597a(this);
        }
    }

    /* renamed from: d */
    public void m6162d() {
        for (C2390b c2390b : this.f5790k) {
            if (c2390b instanceof C2391c) {
                C2391c c2391c = (C2391c) c2390b;
                if (c2391c instanceof C2459g) {
                    this.f5789j.m6442b(c2391c);
                } else {
                    C2600x.m6689b(c2391c);
                }
            }
            c2390b.mo5598b(this);
        }
    }

    /* renamed from: e */
    public void m6163e() {
        if (!m6171m()) {
            this.f5788a.mo5610a();
        }
    }

    /* renamed from: f */
    public void m6164f() {
        this.f5792m.post(new C23885(this));
        this.f5788a.mo5614b();
    }

    /* renamed from: g */
    public void m6165g() {
        this.f5788a.mo5615c();
    }

    public int getCurrentPositionInMillis() {
        return this.f5788a.getCurrentPosition();
    }

    public int getDuration() {
        return this.f5788a.getDuration();
    }

    @NonNull
    public C2062e<C1809f, C2061d> getEventBus() {
        return this.f5793n;
    }

    public long getInitialBufferTime() {
        return this.f5788a.getInitialBufferTime();
    }

    public C2501d getState() {
        return this.f5788a.getState();
    }

    protected Handler getStateHandler() {
        return this.f5792m;
    }

    public TextureView getTextureView() {
        return (TextureView) this.f5788a;
    }

    public int getVideoHeight() {
        return this.f5788a.getVideoHeight();
    }

    public int getVideoProgressReportIntervalMs() {
        return this.f5797r;
    }

    public C2389a getVideoStartReason() {
        return this.f5788a.getStartReason();
    }

    public View getVideoView() {
        return this.f5789j;
    }

    public int getVideoWidth() {
        return this.f5788a.getVideoWidth();
    }

    public View getView() {
        return this;
    }

    public float getVolume() {
        return this.f5788a.getVolume();
    }

    /* renamed from: h */
    public boolean mo5606h() {
        return C2078a.m5089a(getContext());
    }

    /* renamed from: i */
    public boolean mo5607i() {
        return this.f5795p;
    }

    /* renamed from: j */
    public boolean m6168j() {
        return getState() == C2501d.STARTED;
    }

    /* renamed from: k */
    public boolean m6169k() {
        return this.f5788a.mo5616d();
    }

    /* renamed from: l */
    public void m6170l() {
        this.f5788a.setVideoStateChangeListener(null);
        this.f5788a.mo5617e();
    }

    /* renamed from: m */
    public boolean m6171m() {
        return getState() == C2501d.PAUSED;
    }

    /* renamed from: n */
    public boolean m6172n() {
        return m6171m() && this.f5796q;
    }

    protected void onAttachedToWindow() {
        this.f5793n.m5028a(f5787i);
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        this.f5793n.m5028a(f5786h);
        super.onDetachedFromWindow();
    }

    public void setControlsAnchorView(View view) {
        if (this.f5788a != null) {
            this.f5788a.setControlsAnchorView(view);
        }
    }

    public void setIsFullScreen(boolean z) {
        this.f5795p = z;
        this.f5788a.setFullScreen(z);
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
    }

    public void setVideoMPD(@Nullable String str) {
        this.f5788a.setVideoMPD(str);
    }

    public void setVideoProgressReportIntervalMs(int i) {
        this.f5797r = i;
    }

    public void setVideoURI(@Nullable Uri uri) {
        if (uri == null) {
            m6162d();
        } else {
            m6161c();
            this.f5788a.setup(uri);
        }
        this.f5794o = false;
    }

    public void setVideoURI(@Nullable String str) {
        setVideoURI(str != null ? Uri.parse(str) : null);
    }

    public void setVolume(float f) {
        this.f5788a.setRequestedVolume(f);
        getEventBus().m5028a(f5785g);
    }
}
