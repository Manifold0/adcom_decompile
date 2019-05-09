package com.facebook.ads.internal.p029x;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.facebook.ads.internal.p025w.p026b.C1839y;
import com.facebook.ads.internal.p025w.p026b.C2597v;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p026b.C2601z;
import com.facebook.ads.internal.p025w.p068a.C2564b;
import com.facebook.ads.internal.p025w.p073i.C2627a;
import com.facebook.ads.internal.p025w.p073i.C2628b;
import com.facebook.ads.internal.p050r.C2078a;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.x.a */
public class C2630a {
    /* renamed from: a */
    private static final String f6564a = C2630a.class.getSimpleName();
    /* renamed from: b */
    private final View f6565b;
    /* renamed from: c */
    private final int f6566c;
    /* renamed from: d */
    private final int f6567d;
    /* renamed from: e */
    private final WeakReference<C1858a> f6568e;
    /* renamed from: f */
    private final Handler f6569f;
    /* renamed from: g */
    private final boolean f6570g;
    /* renamed from: h */
    private Runnable f6571h;
    /* renamed from: i */
    private int f6572i;
    /* renamed from: j */
    private int f6573j;
    /* renamed from: k */
    private boolean f6574k;
    /* renamed from: l */
    private C2631b f6575l;
    /* renamed from: m */
    private Map<String, Integer> f6576m;
    /* renamed from: n */
    private long f6577n;
    /* renamed from: o */
    private int f6578o;

    /* renamed from: com.facebook.ads.internal.x.a$a */
    public static abstract class C1858a {
        /* renamed from: a */
        public abstract void mo5381a();

        /* renamed from: b */
        public void mo5500b() {
        }
    }

    /* renamed from: com.facebook.ads.internal.x.a$b */
    private static final class C2629b extends C1839y<C2630a> {
        C2629b(C2630a c2630a) {
            super(c2630a);
        }

        public void run() {
            int i = 0;
            C2630a c2630a = (C2630a) m4138a();
            if (c2630a != null) {
                View a = c2630a.f6565b;
                C1858a c1858a = (C1858a) c2630a.f6568e.get();
                if (a != null && c1858a != null) {
                    C2631b a2 = C2630a.m6753a(a, c2630a.f6566c);
                    if (a2.m6776a()) {
                        c2630a.f6578o = c2630a.f6578o + 1;
                    } else {
                        c2630a.f6578o = 0;
                    }
                    int i2 = c2630a.f6578o > c2630a.f6567d ? 1 : 0;
                    int i3 = (c2630a.f6575l == null || !c2630a.f6575l.m6776a()) ? 0 : 1;
                    if (!(i2 == 0 && a2.m6776a())) {
                        c2630a.f6575l = a2;
                    }
                    String valueOf = String.valueOf(a2.m6777b());
                    synchronized (c2630a) {
                        if (c2630a.f6576m.containsKey(valueOf)) {
                            i = ((Integer) c2630a.f6576m.get(valueOf)).intValue();
                        }
                        c2630a.f6576m.put(valueOf, Integer.valueOf(i + 1));
                    }
                    if (i2 != 0 && i3 == 0) {
                        c2630a.f6577n = System.currentTimeMillis();
                        c1858a.mo5381a();
                        if (!c2630a.f6570g) {
                            return;
                        }
                    } else if (i2 == 0 && i3 != 0) {
                        c1858a.mo5500b();
                    }
                    if (!c2630a.f6574k && c2630a.f6571h != null) {
                        c2630a.f6569f.postDelayed(c2630a.f6571h, (long) c2630a.f6573j);
                    }
                }
            }
        }
    }

    public C2630a(View view, int i, int i2, boolean z, C1858a c1858a) {
        this.f6569f = new Handler();
        this.f6572i = 0;
        this.f6573j = 1000;
        this.f6574k = true;
        this.f6575l = new C2631b(C2632c.UNKNOWN);
        this.f6576m = new HashMap();
        this.f6577n = 0;
        this.f6578o = 0;
        this.f6565b = view;
        if (this.f6565b.getId() == -1) {
            C2600x.m6679a(this.f6565b);
        }
        this.f6566c = i;
        this.f6568e = new WeakReference(c1858a);
        this.f6570g = z;
        if (i2 < 0) {
            i2 = 0;
        }
        this.f6567d = i2;
    }

    public C2630a(View view, int i, C1858a c1858a) {
        this(view, i, 0, false, c1858a);
    }

    public C2630a(View view, int i, boolean z, C1858a c1858a) {
        this(view, i, 0, z, c1858a);
    }

    @VisibleForTesting
    /* renamed from: a */
    static float m6747a(View view) {
        float alpha = view.getAlpha();
        while (view.getParent() instanceof ViewGroup) {
            View view2 = (View) view.getParent();
            float alpha2 = view2.getAlpha();
            if (alpha2 < 0.0f) {
                alpha2 = 0.0f;
            }
            if (alpha2 > 1.0f) {
                alpha2 = 1.0f;
            }
            alpha *= alpha2;
            view = view2;
        }
        return alpha;
    }

    /* renamed from: a */
    private static int m6749a(Vector<Rect> vector) {
        int i;
        int i2;
        int size = vector.size();
        int[] iArr = new int[(size * 2)];
        int[] iArr2 = new int[(size * 2)];
        boolean[][] zArr = (boolean[][]) Array.newInstance(Boolean.TYPE, new int[]{size * 2, size * 2});
        int i3 = 0;
        int i4 = 0;
        for (i = 0; i < size; i++) {
            Rect rect = (Rect) vector.elementAt(i);
            int i5 = i4 + 1;
            iArr[i4] = rect.left;
            int i6 = i3 + 1;
            iArr2[i3] = rect.bottom;
            i4 = i5 + 1;
            iArr[i5] = rect.right;
            i3 = i6 + 1;
            iArr2[i6] = rect.top;
        }
        Arrays.sort(iArr);
        Arrays.sort(iArr2);
        for (i3 = 0; i3 < size; i3++) {
            rect = (Rect) vector.elementAt(i3);
            i = C2630a.m6750a(iArr, rect.left);
            i4 = C2630a.m6750a(iArr, rect.right);
            i5 = C2630a.m6750a(iArr2, rect.top);
            i6 = C2630a.m6750a(iArr2, rect.bottom);
            for (i++; i <= i4; i++) {
                for (i2 = i5 + 1; i2 <= i6; i2++) {
                    zArr[i][i2] = true;
                }
            }
        }
        i4 = 0;
        i2 = 0;
        while (i4 < size * 2) {
            i3 = i2;
            for (i = 0; i < size * 2; i++) {
                i3 += zArr[i4][i] ? (iArr[i4] - iArr[i4 - 1]) * (iArr2[i] - iArr2[i - 1]) : 0;
            }
            i4++;
            i2 = i3;
        }
        return i2;
    }

    /* renamed from: a */
    private static int m6750a(int[] iArr, int i) {
        int i2 = 0;
        int length = iArr.length;
        while (i2 < length) {
            int i3 = ((length - i2) / 2) + i2;
            if (iArr[i3] == i) {
                return i3;
            }
            if (iArr[i3] > i) {
                length = i2;
            } else {
                int i4 = length;
                length = i3 + 1;
                i3 = i4;
            }
            i2 = length;
            length = i3;
        }
        return -1;
    }

    /* renamed from: a */
    public static C2631b m6753a(View view, int i) {
        String str;
        if (view == null) {
            str = "mAdView is null.";
            return new C2631b(C2632c.AD_IS_NULL);
        } else if (view.getParent() == null) {
            str = "mAdView has no parent.";
            return new C2631b(C2632c.INVALID_PARENT);
        } else if (!view.isShown()) {
            str = "mAdView parent is not set to VISIBLE.";
            return new C2631b(C2632c.INVALID_PARENT);
        } else if (view.getWindowVisibility() != 0) {
            str = "mAdView window is not set to VISIBLE.";
            return new C2631b(C2632c.INVALID_WINDOW);
        } else if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
            "mAdView has invisible dimensions (w=" + view.getMeasuredWidth() + ", h=" + view.getMeasuredHeight();
            return new C2631b(C2632c.INVALID_DIMENSIONS);
        } else if (C2630a.m6747a(view) < 0.9f) {
            str = "mAdView is too transparent.";
            return new C2631b(C2632c.AD_IS_TRANSPARENT);
        } else {
            int width = view.getWidth();
            int height = view.getHeight();
            int[] iArr = new int[2];
            try {
                view.getLocationOnScreen(iArr);
                Rect rect = new Rect();
                if (!view.getGlobalVisibleRect(rect)) {
                    return new C2631b(C2632c.AD_IS_NOT_VISIBLE);
                }
                Context context = view.getContext();
                DisplayMetrics displayMetrics;
                if (VERSION.SDK_INT >= 17) {
                    Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                    displayMetrics = new DisplayMetrics();
                    defaultDisplay.getRealMetrics(displayMetrics);
                } else {
                    displayMetrics = context.getResources().getDisplayMetrics();
                }
                Vector b = C2630a.m6756b(view);
                int a = C2630a.m6749a(b);
                b.add(rect);
                float a2 = (((float) (C2630a.m6749a(b) - a)) * 1.0f) / ((float) (view.getMeasuredHeight() * view.getMeasuredWidth()));
                int width2 = view.getWidth() * view.getHeight();
                width2 = (int) Math.max((double) i, Math.ceil((double) (width2 > 0 ? 100.0f / ((float) width2) : 100.0f)));
                float f = ((float) width2) / 100.0f;
                if (C2078a.m5073L(context)) {
                    if (a2 < f) {
                        String.format(Locale.US, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", new Object[]{Float.valueOf(a2), Float.valueOf(f)});
                        return new C2631b(C2632c.AD_INSUFFICIENT_VISIBLE_AREA, a2);
                    }
                } else if (iArr[0] < 0 || r0.widthPixels - iArr[0] < width) {
                    str = "mAdView is not fully on screen horizontally.";
                    return new C2631b(C2632c.AD_OFFSCREEN_HORIZONTALLY, a2);
                } else {
                    int i2 = (int) (((100.0d - ((double) width2)) * ((double) height)) / 100.0d);
                    if (rect.top - iArr[1] > i2) {
                        str = "mAdView is not visible from the top.";
                        return new C2631b(C2632c.AD_OFFSCREEN_TOP, a2);
                    } else if ((iArr[1] + height) - rect.bottom > i2) {
                        str = "mAdView is not visible from the bottom.";
                        return new C2631b(C2632c.AD_OFFSCREEN_BOTTOM, a2);
                    }
                }
                if (C2627a.m6744b(context)) {
                    Map a3 = C2628b.m6745a(context);
                    if (C2601z.m6693b(a3)) {
                        str = "Keyguard is obstructing view.";
                        return new C2631b(C2632c.AD_IS_OBSTRUCTED_BY_KEYGUARD, a2);
                    } else if (C2078a.m5100j(context) && C2601z.m6692a(a3)) {
                        str = "Ad is on top of the Lockscreen.";
                        return new C2631b(C2632c.AD_IN_LOCKSCREEN, a2, a3);
                    } else {
                        Float f2 = null;
                        if (C2078a.m5069H(context)) {
                            Activity a4 = C2564b.m6613a();
                            if (a4 == null) {
                                f2 = null;
                            } else {
                                View findViewById = a4.findViewById(16908290);
                                if (findViewById == null) {
                                    findViewById = a4.getWindow().getDecorView().findViewById(16908290);
                                }
                                f2 = findViewById == null ? null : (view == null || view.getId() == -1) ? null : findViewById.findViewById(view.getId()) == null ? Float.valueOf(-1.0f) : C2633d.m6781a(findViewById, view);
                            }
                        }
                        if (f2 != null) {
                            if (f2.floatValue() == -1.0f) {
                                str = "mAdView is not in the top activity";
                                return new C2631b(C2632c.AD_IS_NOT_IN_ACTIVITY);
                            } else if (f2.floatValue() == 0.0f) {
                                str = "mAdView is not visible";
                                return new C2631b(C2632c.AD_IS_NOT_VISIBLE);
                            }
                        }
                        if (!C2078a.m5070I(context) || f2 == null || f2.floatValue() >= f) {
                            str = "mAdView is visible.";
                            return new C2631b(C2632c.IS_VIEWABLE, a2, a3);
                        }
                        String.format(Locale.US, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", new Object[]{f2, Float.valueOf(f)});
                        return new C2631b(C2632c.AD_INSUFFICIENT_VISIBLE_AREA, a2, a3);
                    }
                }
                str = "Screen is not interactive.";
                return new C2631b(C2632c.SCREEN_NOT_INTERACTIVE, a2);
            } catch (NullPointerException e) {
                str = "Cannot get location on screen.";
                return new C2631b(C2632c.INVALID_DIMENSIONS);
            }
        }
    }

    /* renamed from: b */
    private static Vector<Rect> m6756b(View view) {
        Vector<Rect> vector = new Vector();
        if (!(view.getParent() instanceof ViewGroup)) {
            return vector;
        }
        View view2 = (ViewGroup) view.getParent();
        for (int indexOfChild = view2.indexOfChild(view) + 1; indexOfChild < view2.getChildCount(); indexOfChild++) {
            vector.addAll(C2630a.m6758c(view2.getChildAt(indexOfChild)));
        }
        vector.addAll(C2630a.m6756b(view2));
        return vector;
    }

    /* renamed from: c */
    private static Vector<Rect> m6758c(View view) {
        int i = 0;
        Vector<Rect> vector = new Vector();
        if (!view.isShown() || (VERSION.SDK_INT >= 11 && view.getAlpha() <= 0.0f)) {
            return vector;
        }
        if (view instanceof ViewGroup) {
            int i2 = (view.getBackground() == null || (VERSION.SDK_INT >= 19 && view.getBackground().getAlpha() <= 0)) ? 1 : 0;
            if (i2 != 0) {
                ViewGroup viewGroup = (ViewGroup) view;
                while (i < viewGroup.getChildCount()) {
                    vector.addAll(C2630a.m6758c(viewGroup.getChildAt(i)));
                    i++;
                }
                return vector;
            }
        }
        Rect rect = new Rect();
        if (view.getGlobalVisibleRect(rect)) {
            vector.add(rect);
        }
        return vector;
    }

    /* renamed from: a */
    public synchronized void m6769a() {
        if (this.f6571h != null) {
            m6774c();
        }
        this.f6571h = new C2629b(this);
        this.f6569f.postDelayed(this.f6571h, (long) this.f6572i);
        this.f6574k = false;
        this.f6578o = 0;
        this.f6575l = new C2631b(C2632c.UNKNOWN);
        this.f6576m = new HashMap();
    }

    /* renamed from: a */
    public void m6770a(int i) {
        this.f6572i = i;
    }

    /* renamed from: a */
    public synchronized void m6771a(Map<String, String> map) {
        map.put("vrc", String.valueOf(this.f6575l.m6777b()));
        map.put("vp", String.valueOf(this.f6575l.m6778c()));
        map.put("vh", new JSONObject(this.f6576m).toString());
        map.put("vt", C2597v.m6669b(this.f6577n));
        map.putAll(this.f6575l.m6779d());
    }

    /* renamed from: b */
    public void m6772b(int i) {
        this.f6573j = i;
    }

    /* renamed from: b */
    public synchronized boolean m6773b() {
        return this.f6574k;
    }

    /* renamed from: c */
    public synchronized void m6774c() {
        this.f6569f.removeCallbacks(this.f6571h);
        this.f6571h = null;
        this.f6574k = true;
        this.f6578o = 0;
    }

    /* renamed from: d */
    public synchronized String m6775d() {
        return C2632c.values()[this.f6575l.m6777b()].toString() + String.format(Locale.US, " (%.1f%%)", new Object[]{Float.valueOf(this.f6575l.m6778c() * 100.0f)});
    }
}
