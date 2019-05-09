package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C1388c;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1391a;
import com.tonyodev.fetch.FetchConst;
import java.util.IdentityHashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.e */
public abstract class C1403e {
    /* renamed from: a */
    public final Handler f2871a;
    /* renamed from: b */
    public final C1397c f2872b;
    /* renamed from: c */
    public boolean f2873c = false;
    /* renamed from: d */
    protected JSONObject f2874d;
    /* renamed from: e */
    public final C1388c f2875e;
    /* renamed from: f */
    protected int f2876f;
    /* renamed from: g */
    public final Map<View, Runnable> f2877g = new IdentityHashMap();
    /* renamed from: h */
    protected boolean f2878h = true;
    /* renamed from: i */
    protected boolean f2879i = true;
    /* renamed from: j */
    private boolean f2880j;
    /* renamed from: k */
    private C1402a f2881k;

    /* renamed from: com.chartboost.sdk.e$a */
    public abstract class C1402a extends RelativeLayout {
        /* renamed from: a */
        Integer f2864a = null;
        /* renamed from: b */
        final /* synthetic */ C1403e f2865b;
        /* renamed from: c */
        private boolean f2866c = false;
        /* renamed from: d */
        private int f2867d = -1;
        /* renamed from: e */
        private int f2868e = -1;
        /* renamed from: f */
        private int f2869f = -1;
        /* renamed from: g */
        private int f2870g = -1;

        /* renamed from: com.chartboost.sdk.e$a$1 */
        class C14011 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C1402a f2863a;

            C14011(C1402a c1402a) {
                this.f2863a = c1402a;
            }

            public void run() {
                this.f2863a.requestLayout();
            }
        }

        /* renamed from: a */
        protected abstract void mo4296a(int i, int i2);

        public C1402a(C1403e c1403e, Context context) {
            this.f2865b = c1403e;
            super(context);
            setFocusableInTouchMode(true);
            requestFocus();
        }

        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            this.f2869f = w;
            this.f2870g = h;
            if (this.f2867d != -1 && this.f2868e != -1 && this.f2865b.f2875e != null && this.f2865b.f2875e.f2770p.f2730b == 0) {
                m3288a();
            }
        }

        /* renamed from: b */
        private boolean m3287b(int i, int i2) {
            boolean z = true;
            if (this.f2865b.f2875e != null && this.f2865b.f2875e.f2770p.f2730b == 1) {
                return true;
            }
            if (this.f2866c) {
                return false;
            }
            int a = CBUtility.m3107a();
            if (this.f2867d == i && this.f2868e == i2 && this.f2864a != null && this.f2864a.intValue() == a) {
                return true;
            }
            this.f2866c = true;
            try {
                if (this.f2865b.f2878h && CBUtility.m3111a(a)) {
                    this.f2865b.f2876f = a;
                } else if (this.f2865b.f2879i && CBUtility.m3116b(a)) {
                    this.f2865b.f2876f = a;
                }
                mo4296a(i, i2);
                post(new C14011(this));
                this.f2867d = i;
                this.f2868e = i2;
                this.f2864a = Integer.valueOf(a);
            } catch (Exception e) {
                CBLogging.m3098a("CBViewProtocol", "Exception raised while layouting Subviews", e);
                C1391a.m3206a(getClass(), "tryLayout", e);
                z = false;
            }
            this.f2866c = false;
            return z;
        }

        /* renamed from: a */
        public final void m3288a() {
            m3291a(false);
        }

        /* renamed from: a */
        public final void m3291a(boolean z) {
            if (z) {
                this.f2864a = null;
            }
            m3292a((Activity) getContext());
        }

        /* renamed from: b */
        public void mo4318b() {
        }

        /* renamed from: a */
        public boolean m3292a(Activity activity) {
            if (this.f2869f == -1 || this.f2870g == -1) {
                int width;
                int height;
                try {
                    width = getWidth();
                    height = getHeight();
                    if (width == 0 || height == 0) {
                        View findViewById = activity.getWindow().findViewById(16908290);
                        if (findViewById == null) {
                            findViewById = activity.getWindow().getDecorView();
                        }
                        width = findViewById.getWidth();
                        height = findViewById.getHeight();
                    }
                } catch (Exception e) {
                    height = 0;
                    width = 0;
                }
                if (width == 0 || r0 == 0) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    width = displayMetrics.widthPixels;
                    height = displayMetrics.heightPixels;
                }
                this.f2869f = width;
                this.f2870g = height;
            }
            return m3287b(this.f2869f, this.f2870g);
        }

        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            synchronized (this.f2865b.f2877g) {
                for (Runnable removeCallbacks : this.f2865b.f2877g.values()) {
                    this.f2865b.f2871a.removeCallbacks(removeCallbacks);
                }
                this.f2865b.f2877g.clear();
            }
        }

        /* renamed from: a */
        public final void m3290a(View view) {
            int i = 200;
            if (200 == getId()) {
                i = FetchConst.NETWORK_WIFI;
            }
            int i2 = i;
            View findViewById = findViewById(i);
            while (findViewById != null) {
                i2++;
                findViewById = findViewById(i2);
            }
            view.setId(i2);
            view.setSaveEnabled(false);
        }
    }

    /* renamed from: b */
    protected abstract C1402a mo4298b(Context context);

    /* renamed from: a */
    public static boolean m3295a(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 4;
    }

    public C1403e(C1388c c1388c, Handler handler, C1397c c1397c) {
        this.f2871a = handler;
        this.f2872b = c1397c;
        this.f2875e = c1388c;
        this.f2881k = null;
        this.f2876f = CBUtility.m3107a();
        this.f2880j = false;
    }

    /* renamed from: a */
    public int m3296a() {
        return this.f2876f;
    }

    /* renamed from: a */
    public boolean mo4297a(JSONObject jSONObject) {
        this.f2874d = C1377e.m3129a(jSONObject, "assets");
        if (this.f2874d != null) {
            return true;
        }
        this.f2874d = new JSONObject();
        CBLogging.m3099b("CBViewProtocol", "Media got from the response is null or empty");
        m3298a(CBImpressionError.INVALID_RESPONSE);
        return false;
    }

    /* renamed from: b */
    public void m3303b() {
        mo4322i();
    }

    /* renamed from: c */
    public CBImpressionError m3305c() {
        Activity b = this.f2872b.m3255b();
        if (b == null) {
            this.f2881k = null;
            return CBImpressionError.NO_HOST_ACTIVITY;
        } else if (!this.f2879i && !this.f2878h) {
            return CBImpressionError.WRONG_ORIENTATION;
        } else {
            if (this.f2881k == null) {
                this.f2881k = mo4298b((Context) b);
            }
            if (this.f2875e.f2770p.f2730b != 0 || this.f2881k.m3292a(b)) {
                return null;
            }
            this.f2881k = null;
            return CBImpressionError.ERROR_CREATING_VIEW;
        }
    }

    /* renamed from: d */
    public void mo4299d() {
        m3308f();
        synchronized (this.f2877g) {
            for (Runnable removeCallbacks : this.f2877g.values()) {
                this.f2871a.removeCallbacks(removeCallbacks);
            }
            this.f2877g.clear();
        }
    }

    /* renamed from: e */
    public C1402a mo4300e() {
        return this.f2881k;
    }

    /* renamed from: f */
    public void m3308f() {
        if (this.f2881k != null) {
            this.f2881k.mo4318b();
        }
        this.f2881k = null;
    }

    /* renamed from: g */
    public JSONObject m3309g() {
        return this.f2874d;
    }

    /* renamed from: a */
    public void m3298a(CBImpressionError cBImpressionError) {
        this.f2875e.m3171a(cBImpressionError);
    }

    /* renamed from: h */
    public void mo4301h() {
        if (!this.f2880j) {
            this.f2880j = true;
            this.f2875e.m3177c();
        }
    }

    /* renamed from: i */
    protected void mo4322i() {
        this.f2875e.m3178d();
    }

    /* renamed from: b */
    public boolean m3304b(JSONObject jSONObject) {
        return this.f2875e.m3175a(jSONObject);
    }

    /* renamed from: a */
    public void m3299a(boolean z, View view) {
        m3300a(z, view, true);
    }

    /* renamed from: a */
    public void m3300a(final boolean z, final View view, boolean z2) {
        int i = 8;
        if ((z && view.getVisibility() == 0) || (!z && view.getVisibility() == 8)) {
            synchronized (this.f2877g) {
                if (!this.f2877g.containsKey(view)) {
                    return;
                }
            }
        }
        if (z2) {
            Runnable c14001 = new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ C1403e f2862c;

                public void run() {
                    if (!z) {
                        view.setVisibility(8);
                        view.setClickable(false);
                    }
                    synchronized (this.f2862c.f2877g) {
                        this.f2862c.f2877g.remove(view);
                    }
                }
            };
            if (this.f2875e.f2770p.f2730b == 1) {
                this.f2875e.f2763i.f2854a.m3467a(z, view, 500);
                m3297a(view, c14001, 500);
            } else {
                this.f2875e.f2763i.f2854a.m3467a(z, view, 500);
                m3297a(view, c14001, 500);
            }
            return;
        }
        if (z) {
            i = 0;
        }
        view.setVisibility(i);
        view.setClickable(z);
    }

    /* renamed from: a */
    public void m3297a(View view, Runnable runnable, long j) {
        synchronized (this.f2877g) {
            Runnable runnable2 = (Runnable) this.f2877g.get(view);
            if (runnable2 != null) {
                this.f2871a.removeCallbacks(runnable2);
            }
            this.f2877g.put(view, runnable);
        }
        this.f2871a.postDelayed(runnable, j);
    }

    /* renamed from: a */
    public static int m3294a(String str) {
        int i = 0;
        if (str != null) {
            if (!str.startsWith("#")) {
                try {
                    i = Color.parseColor(str);
                } catch (IllegalArgumentException e) {
                    str = "#" + str;
                }
            }
            if (str.length() == 4 || str.length() == 5) {
                StringBuilder stringBuilder = new StringBuilder((str.length() * 2) + 1);
                stringBuilder.append("#");
                for (int i2 = i; i2 < str.length() - 1; i2++) {
                    stringBuilder.append(str.charAt(i2 + 1));
                    stringBuilder.append(str.charAt(i2 + 1));
                }
                str = stringBuilder.toString();
            }
            try {
                i = Color.parseColor(str);
            } catch (Throwable e2) {
                CBLogging.m3102c("CBViewProtocol", "error parsing color " + str, e2);
            }
        }
        return i;
    }

    /* renamed from: j */
    public float mo4302j() {
        return 0.0f;
    }

    /* renamed from: k */
    public float mo4303k() {
        return 0.0f;
    }

    /* renamed from: l */
    public boolean mo4304l() {
        return false;
    }

    /* renamed from: m */
    public void mo4305m() {
        if (this.f2873c) {
            this.f2873c = false;
        }
        C1402a e = mo4300e();
        if (e == null) {
            return;
        }
        if (e.f2864a == null || CBUtility.m3107a() != e.f2864a.intValue()) {
            e.m3291a(false);
        }
    }

    /* renamed from: n */
    public void mo4306n() {
        this.f2873c = true;
    }
}
