package com.facebook.ads.internal.view.component.p058a.p059a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1877i;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.C2554x;
import com.facebook.ads.internal.view.component.p058a.C2259c;
import com.facebook.ads.internal.view.component.p058a.C2279e;
import com.facebook.ads.internal.view.p019c.C1802e;
import com.facebook.ads.internal.view.p019c.C2252d;
import com.facebook.ads.internal.view.p022i.p023b.C1810n;
import com.facebook.ads.internal.view.p022i.p023b.C1812l;
import com.facebook.ads.internal.view.p022i.p023b.C1814j;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C1820x;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2410i;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.facebook.ads.internal.view.p022i.p023b.C2412m;
import com.facebook.ads.internal.view.p022i.p023b.C2419w;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import com.facebook.ads.internal.view.p061e.p062a.C2315a;
import com.facebook.ads.internal.view.p061e.p062a.C2320d;
import java.lang.ref.WeakReference;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.component.a.a.b */
public abstract class C2260b extends C2259c {
    /* renamed from: c */
    private static final int f5327c = ((int) (1.0f * C2600x.f6420b));
    /* renamed from: d */
    private static final int f5328d = ((int) (4.0f * C2600x.f6420b));
    /* renamed from: e */
    private static final int f5329e = ((int) (6.0f * C2600x.f6420b));
    /* renamed from: f */
    private C2554x f5330f;
    /* renamed from: g */
    private C2320d f5331g;
    /* renamed from: h */
    private RelativeLayout f5332h;
    /* renamed from: i */
    private final String f5333i;
    /* renamed from: j */
    private final Paint f5334j;
    /* renamed from: k */
    private boolean f5335k;
    /* renamed from: l */
    private C2315a f5336l;
    /* renamed from: m */
    private final Path f5337m = new Path();
    /* renamed from: n */
    private final RectF f5338n = new RectF();
    /* renamed from: o */
    private boolean f5339o;
    /* renamed from: p */
    private boolean f5340p;
    /* renamed from: q */
    private C2267a f5341q;
    /* renamed from: r */
    private final C1820x f5342r = new C22621(this);
    /* renamed from: s */
    private final C1818d f5343s = new C22632(this);
    /* renamed from: t */
    private final C1812l f5344t = new C22643(this);
    /* renamed from: u */
    private final C1814j f5345u = new C22654(this);
    /* renamed from: v */
    private final C1810n f5346v = new C22665(this);

    /* renamed from: com.facebook.ads.internal.view.component.a.a.b$1 */
    class C22621 extends C1820x {
        /* renamed from: a */
        final /* synthetic */ C2260b f5348a;

        C22621(C2260b c2260b) {
            this.f5348a = c2260b;
        }

        /* renamed from: a */
        public void m5810a(C2419w c2419w) {
            this.f5348a.f5336l.m5968c().mo5575a(this.f5348a.getVideoView().getVolume());
        }
    }

    /* renamed from: com.facebook.ads.internal.view.component.a.a.b$2 */
    class C22632 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ C2260b f5349a;

        C22632(C2260b c2260b) {
            this.f5349a = c2260b;
        }

        /* renamed from: a */
        public void m5812a(C2406c c2406c) {
            this.f5349a.f5336l.m5969d().mo5576a(((Integer) this.f5349a.getTag(-1593835536)).intValue());
        }
    }

    /* renamed from: com.facebook.ads.internal.view.component.a.a.b$3 */
    class C22643 extends C1812l {
        /* renamed from: a */
        final /* synthetic */ C2260b f5350a;

        C22643(C2260b c2260b) {
            this.f5350a = c2260b;
        }

        /* renamed from: a */
        public void m5814a(C2411k c2411k) {
            this.f5350a.f5336l.m5970e().mo5577a(this.f5350a);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.component.a.a.b$4 */
    class C22654 extends C1814j {
        /* renamed from: a */
        final /* synthetic */ C2260b f5351a;

        C22654(C2260b c2260b) {
            this.f5351a = c2260b;
        }

        /* renamed from: a */
        public void m5816a(C2410i c2410i) {
            this.f5351a.f5336l.m5970e().mo5578b(this.f5351a);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.component.a.a.b$5 */
    class C22665 extends C1810n {
        /* renamed from: a */
        final /* synthetic */ C2260b f5352a;

        C22665(C2260b c2260b) {
            this.f5352a = c2260b;
        }

        /* renamed from: a */
        public void m5818a(C2412m c2412m) {
            this.f5352a.f5340p = true;
            C2260b.m5793b(this.f5352a);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.component.a.a.b$a */
    public interface C2267a {
        /* renamed from: a */
        void mo5582a();
    }

    /* renamed from: com.facebook.ads.internal.view.component.a.a.b$b */
    private static class C2268b implements C1802e {
        /* renamed from: a */
        final WeakReference<C2260b> f5353a;

        private C2268b(C2260b c2260b) {
            this.f5353a = new WeakReference(c2260b);
        }

        /* renamed from: a */
        public void mo5349a(boolean z) {
            C2260b c2260b = (C2260b) this.f5353a.get();
            if (c2260b != null) {
                c2260b.f5339o = z;
                C2260b.m5793b(c2260b);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.component.a.a.b$c */
    public interface C2269c {
        /* renamed from: a */
        void mo5576a(int i);
    }

    /* renamed from: com.facebook.ads.internal.view.component.a.a.b$d */
    public interface C2270d {
        /* renamed from: a */
        void mo5577a(View view);

        /* renamed from: b */
        void mo5578b(View view);
    }

    /* renamed from: com.facebook.ads.internal.view.component.a.a.b$e */
    public interface C2271e {
        /* renamed from: a */
        float mo5574a();

        /* renamed from: a */
        void mo5575a(float f);
    }

    C2260b(C2279e c2279e, C1876h c1876h, boolean z, String str, C2315a c2315a) {
        super(c2279e, c1876h, z);
        this.f5336l = c2315a;
        this.f5333i = str;
        setGravity(17);
        setPadding(f5327c, 0, f5327c, f5327c);
        C2600x.m6680a((View) this, 0);
        setUpView(getContext());
        this.f5334j = new Paint();
        this.f5334j.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f5334j.setStyle(Style.FILL);
        this.f5334j.setAlpha(16);
        this.f5334j.setAntiAlias(true);
        if (VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    /* renamed from: a */
    private void m5791a(View view) {
        view.setLayoutParams(new LayoutParams(-1, -2));
        C2600x.m6679a(view);
    }

    /* renamed from: b */
    static /* synthetic */ void m5793b(C2260b c2260b) {
        if (c2260b.f5341q != null) {
            if ((c2260b.m5801f() && c2260b.f5340p) || (!c2260b.m5801f() && c2260b.f5339o)) {
                c2260b.f5341q.mo5582a();
            }
        }
    }

    private void setUpView(Context context) {
        setUpImageView(context);
        setUpVideoView(context);
        setUpMediaContainer(context);
        this.f5332h.addView(this.f5330f);
        this.f5332h.addView(this.f5331g);
        mo5557a(context);
    }

    /* renamed from: a */
    protected abstract void mo5557a(Context context);

    /* renamed from: a */
    public void m5796a(C1877i c1877i, Map<String, String> map) {
        getCtaButton().m5917a(c1877i, this.f5333i, map);
    }

    /* renamed from: a */
    public void m5797a(String str, String str2) {
        getTitleDescContainer().m5924a(str, str2, null, true, false);
    }

    /* renamed from: a */
    public void m5798a(Map<String, String> map) {
        this.f5331g.m5983c();
        if (m5801f()) {
            this.f5331g.m5980a(getAdEventManager(), this.f5333i, map);
        }
    }

    /* renamed from: a */
    public boolean mo5555a() {
        return false;
    }

    /* renamed from: e */
    protected boolean mo5556e() {
        return false;
    }

    /* renamed from: f */
    public boolean m5801f() {
        return this.f5335k;
    }

    /* renamed from: g */
    public boolean m5802g() {
        return m5801f() && this.f5331g.m5982b();
    }

    protected final RelativeLayout getMediaContainer() {
        return this.f5332h;
    }

    public final C2320d getVideoView() {
        return this.f5331g;
    }

    /* renamed from: h */
    public void m5803h() {
        if (m5801f()) {
            m5805j();
            this.f5331g.m5981a(C2389a.AUTO_STARTED);
        }
    }

    /* renamed from: i */
    public void m5804i() {
        if (m5801f()) {
            this.f5331g.m5978a();
        }
    }

    /* renamed from: j */
    public void m5805j() {
        float a = this.f5336l.m5968c().mo5574a();
        if (m5801f() && a != this.f5331g.getVolume()) {
            this.f5331g.setVolume(a);
        }
    }

    protected void onDraw(Canvas canvas) {
        this.f5337m.reset();
        this.f5338n.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.f5337m.addRoundRect(this.f5338n, (float) f5329e, (float) f5329e, Direction.CW);
        canvas.drawPath(this.f5337m, this.f5334j);
        this.f5338n.set((float) f5327c, 0.0f, (float) (getWidth() - f5327c), (float) (getHeight() - f5327c));
        this.f5337m.addRoundRect(this.f5338n, (float) f5328d, (float) f5328d, Direction.CW);
        canvas.clipPath(this.f5337m);
        super.onDraw(canvas);
    }

    public void setImageUrl(String str) {
        this.f5330f.setVisibility(0);
        this.f5331g.setVisibility(8);
        new C2252d(this.f5330f).m5771a().m5773a(new C2268b()).m5775a(str);
    }

    public void setIsVideo(boolean z) {
        this.f5335k = z;
    }

    public void setOnAssetsLoadedListener(C2267a c2267a) {
        this.f5341q = c2267a;
    }

    protected void setUpImageView(Context context) {
        this.f5330f = new C2554x(context);
        m5791a(this.f5330f);
    }

    protected void setUpMediaContainer(Context context) {
        this.f5332h = new RelativeLayout(context);
        m5791a(this.f5332h);
    }

    protected void setUpVideoView(Context context) {
        this.f5331g = new C2320d(context, getAdEventManager());
        m5791a(this.f5331g);
    }

    public void setVideoPlaceholderUrl(String str) {
        this.f5331g.setPlaceholderUrl(str);
    }

    public void setVideoUrl(String str) {
        this.f5330f.setVisibility(8);
        this.f5331g.setVisibility(0);
        this.f5331g.setVideoURI(str);
        this.f5331g.m5979a(this.f5342r);
        this.f5331g.m5979a(this.f5343s);
        this.f5331g.m5979a(this.f5344t);
        this.f5331g.m5979a(this.f5345u);
        this.f5331g.m5979a(this.f5346v);
    }
}
