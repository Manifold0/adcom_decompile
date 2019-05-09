package com.facebook.ads.internal.view.p061e.p062a;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.view.View;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.view.C2310d;
import com.facebook.ads.internal.view.component.p058a.p059a.C2260b;
import com.facebook.ads.internal.view.component.p058a.p059a.C2260b.C2269c;
import com.facebook.ads.internal.view.component.p058a.p059a.C2260b.C2270d;
import com.facebook.ads.internal.view.component.p058a.p059a.C2260b.C2271e;
import com.facebook.ads.internal.view.p061e.p062a.C2318c.C2317a;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.facebook.ads.internal.view.e.a.a */
public class C2315a extends OnScrollListener {
    /* renamed from: a */
    private final LinearLayoutManager f5499a;
    /* renamed from: b */
    private final int f5500b;
    /* renamed from: c */
    private final SmoothScroller f5501c;
    /* renamed from: d */
    private final Set<Integer> f5502d = new HashSet();
    /* renamed from: e */
    private List<C2316b> f5503e;
    /* renamed from: f */
    private final C2630a f5504f;
    /* renamed from: g */
    private boolean f5505g = true;
    @Nullable
    /* renamed from: h */
    private C2317a f5506h;
    /* renamed from: i */
    private boolean f5507i = true;
    /* renamed from: j */
    private boolean f5508j = true;
    /* renamed from: k */
    private boolean f5509k;
    /* renamed from: l */
    private int f5510l = -1;
    /* renamed from: m */
    private float f5511m = 0.0f;
    /* renamed from: n */
    private final C2271e f5512n = new C23121(this);
    /* renamed from: o */
    private final C2269c f5513o = new C23132(this);
    /* renamed from: p */
    private final C2270d f5514p = new C23143(this);

    /* renamed from: com.facebook.ads.internal.view.e.a.a$1 */
    class C23121 implements C2271e {
        /* renamed from: a */
        final /* synthetic */ C2315a f5496a;

        C23121(C2315a c2315a) {
            this.f5496a = c2315a;
        }

        /* renamed from: a */
        public float mo5574a() {
            return this.f5496a.f5511m;
        }

        /* renamed from: a */
        public void mo5575a(float f) {
            this.f5496a.f5511m = f;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.e.a.a$2 */
    class C23132 implements C2269c {
        /* renamed from: a */
        final /* synthetic */ C2315a f5497a;

        C23132(C2315a c2315a) {
            this.f5497a = c2315a;
        }

        /* renamed from: a */
        public void mo5576a(int i) {
            this.f5497a.m5951a(i, true);
            if (this.f5497a.m5963g()) {
                C2315a.m5959c(this.f5497a);
            } else {
                C2315a.m5953a(this.f5497a, i);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.e.a.a$3 */
    class C23143 implements C2270d {
        /* renamed from: a */
        final /* synthetic */ C2315a f5498a;

        C23143(C2315a c2315a) {
            this.f5498a = c2315a;
        }

        /* renamed from: a */
        public void mo5577a(View view) {
            C2260b c2260b = (C2260b) view;
            c2260b.m5805j();
            if (this.f5498a.f5509k) {
                this.f5498a.f5508j = true;
            }
            if (this.f5498a.f5504f.m6773b() && ((Integer) c2260b.getTag(-1593835536)).intValue() == 0) {
                this.f5498a.f5504f.m6769a();
            }
        }

        /* renamed from: b */
        public void mo5578b(View view) {
            if (this.f5498a.f5509k) {
                this.f5498a.f5508j = false;
            }
        }
    }

    C2315a(C2310d c2310d, int i, List<C2316b> list, C2630a c2630a, @Nullable Bundle bundle) {
        this.f5499a = c2310d.getLayoutManager();
        this.f5500b = i;
        this.f5503e = list;
        this.f5504f = c2630a;
        this.f5501c = new LinearSmoothScroller(c2310d.getContext());
        c2310d.addOnScrollListener(this);
        if (bundle != null) {
            this.f5511m = bundle.getFloat("VOLUME_LEVEL_PARAM", 0.0f);
            this.f5508j = bundle.getBoolean("AUTO_PLAY_ENABLED_PARAM", true);
            this.f5505g = bundle.getBoolean("IS_FIRST_VIDEO_PARAM", true);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    /* renamed from: a */
    private com.facebook.ads.internal.view.component.p058a.p059a.C2260b m5949a(int r9, int r10, boolean r11) {
        /*
        r8 = this;
        r2 = 0;
        r4 = 0;
        r1 = r2;
    L_0x0003:
        if (r9 > r10) goto L_0x0014;
    L_0x0005:
        r0 = r8.f5499a;
        r0 = r0.findViewByPosition(r9);
        r0 = (com.facebook.ads.internal.view.component.p058a.p059a.C2260b) r0;
        r3 = r0.m5802g();
        if (r3 == 0) goto L_0x0015;
    L_0x0013:
        r1 = r2;
    L_0x0014:
        return r1;
    L_0x0015:
        r5 = com.facebook.ads.internal.view.p061e.p062a.C2315a.m5955a(r0);
        if (r1 != 0) goto L_0x004c;
    L_0x001b:
        r3 = r0.m5801f();
        if (r3 == 0) goto L_0x004c;
    L_0x0021:
        if (r5 == 0) goto L_0x004c;
    L_0x0023:
        r3 = r8.f5502d;
        r6 = java.lang.Integer.valueOf(r9);
        r3 = r3.contains(r6);
        if (r3 != 0) goto L_0x004c;
    L_0x002f:
        if (r11 == 0) goto L_0x004b;
    L_0x0031:
        r3 = r0.getWidth();
        r3 = (float) r3;
        r6 = 1067869798; // 0x3fa66666 float:1.3 double:5.275977814E-315;
        r3 = r3 * r6;
        r3 = (int) r3;
        r6 = r0.getX();
        r7 = r0.getWidth();
        r7 = (float) r7;
        r6 = r6 + r7;
        r6 = (int) r6;
        if (r6 > r3) goto L_0x005a;
    L_0x0048:
        r3 = 1;
    L_0x0049:
        if (r3 == 0) goto L_0x004c;
    L_0x004b:
        r1 = r0;
    L_0x004c:
        r0 = r0.m5801f();
        if (r0 == 0) goto L_0x0057;
    L_0x0052:
        if (r5 != 0) goto L_0x0057;
    L_0x0054:
        r8.m5951a(r9, r4);
    L_0x0057:
        r9 = r9 + 1;
        goto L_0x0003;
    L_0x005a:
        r3 = r4;
        goto L_0x0049;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.view.e.a.a.a(int, int, boolean):com.facebook.ads.internal.view.component.a.a.b");
    }

    /* renamed from: a */
    private void m5950a(int i) {
        this.f5501c.setTargetPosition(i);
        this.f5499a.startSmoothScroll(this.f5501c);
    }

    /* renamed from: a */
    private void m5951a(int i, boolean z) {
        if (z) {
            this.f5502d.add(Integer.valueOf(i));
        } else {
            this.f5502d.remove(Integer.valueOf(i));
        }
    }

    /* renamed from: a */
    private void m5952a(C2260b c2260b, boolean z) {
        if (m5963g()) {
            c2260b.setAlpha(z ? 1.0f : 0.5f);
        }
        if (!z && c2260b.m5802g()) {
            c2260b.m5804i();
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m5953a(C2315a c2315a, int i) {
        C2260b a = c2315a.m5949a(i + 1, c2315a.f5499a.findLastVisibleItemPosition(), false);
        if (a != null) {
            a.m5803h();
            c2315a.m5950a(((Integer) a.getTag(-1593835536)).intValue());
        }
    }

    /* renamed from: a */
    private static boolean m5955a(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return ((float) rect.width()) / ((float) view.getWidth()) >= 0.15f;
    }

    /* renamed from: b */
    private void m5957b(int i) {
        C2260b c2260b = (C2260b) this.f5499a.findViewByPosition(i);
        if (!C2315a.m5955a((View) c2260b)) {
            m5952a(c2260b, false);
        }
    }

    /* renamed from: c */
    static /* synthetic */ void m5959c(C2315a c2315a) {
        int findFirstCompletelyVisibleItemPosition = c2315a.f5499a.findFirstCompletelyVisibleItemPosition();
        if (findFirstCompletelyVisibleItemPosition != -1 && findFirstCompletelyVisibleItemPosition < c2315a.f5503e.size() - 1) {
            c2315a.m5950a(findFirstCompletelyVisibleItemPosition + 1);
        }
    }

    /* renamed from: f */
    private void m5962f() {
        if (this.f5508j) {
            C2260b a = m5949a(this.f5499a.findFirstVisibleItemPosition(), this.f5499a.findLastVisibleItemPosition(), true);
            if (a != null) {
                a.m5803h();
            }
        }
    }

    /* renamed from: g */
    private boolean m5963g() {
        return this.f5500b == 1;
    }

    /* renamed from: a */
    public void m5964a() {
        this.f5510l = -1;
        int findFirstVisibleItemPosition = this.f5499a.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = this.f5499a.findLastVisibleItemPosition();
        int i = findFirstVisibleItemPosition;
        while (i <= findLastVisibleItemPosition && i >= 0) {
            C2260b c2260b = (C2260b) this.f5499a.findViewByPosition(i);
            if (c2260b == null || !c2260b.m5802g()) {
                i++;
            } else {
                this.f5510l = i;
                c2260b.m5804i();
                return;
            }
        }
    }

    /* renamed from: a */
    void m5965a(Bundle bundle) {
        bundle.putFloat("VOLUME_LEVEL_PARAM", this.f5511m);
        bundle.putBoolean("AUTO_PLAY_ENABLED_PARAM", this.f5508j);
        bundle.putBoolean("IS_FIRST_VIDEO_PARAM", this.f5505g);
    }

    /* renamed from: a */
    void m5966a(C2317a c2317a) {
        this.f5506h = c2317a;
    }

    /* renamed from: b */
    public void m5967b() {
        C2260b c2260b = (C2260b) this.f5499a.findViewByPosition(this.f5510l);
        if (this.f5510l >= 0) {
            c2260b.m5803h();
        }
    }

    /* renamed from: c */
    public C2271e m5968c() {
        return this.f5512n;
    }

    /* renamed from: d */
    public C2269c m5969d() {
        return this.f5513o;
    }

    /* renamed from: e */
    public C2270d m5970e() {
        return this.f5514p;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        if (i == 0) {
            this.f5509k = true;
            m5962f();
        }
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        this.f5509k = false;
        if (this.f5507i) {
            this.f5509k = true;
            m5962f();
            this.f5507i = false;
        }
        int findFirstVisibleItemPosition = this.f5499a.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = this.f5499a.findLastVisibleItemPosition();
        m5957b(findFirstVisibleItemPosition);
        m5957b(findLastVisibleItemPosition);
        for (int i3 = findFirstVisibleItemPosition; i3 <= findLastVisibleItemPosition; i3++) {
            boolean z;
            C2260b c2260b = (C2260b) this.f5499a.findViewByPosition(i3);
            if (C2315a.m5955a((View) c2260b)) {
                m5952a(c2260b, true);
            }
            if (this.f5505g && c2260b.m5801f()) {
                this.f5505g = false;
                z = true;
            } else {
                z = false;
            }
            if (z) {
                this.f5512n.mo5575a(((C2316b) this.f5503e.get(((Integer) c2260b.getTag(-1593835536)).intValue())).m5973c().m4317c().m4244f() ? 0.0f : 1.0f);
            }
        }
        if (m5963g() && this.f5506h != null) {
            int findFirstCompletelyVisibleItemPosition = this.f5499a.findFirstCompletelyVisibleItemPosition();
            if (findFirstCompletelyVisibleItemPosition == -1) {
                findFirstCompletelyVisibleItemPosition = i < 0 ? findFirstVisibleItemPosition : findLastVisibleItemPosition;
            }
            this.f5506h.mo5579a(findFirstCompletelyVisibleItemPosition);
        }
    }
}
