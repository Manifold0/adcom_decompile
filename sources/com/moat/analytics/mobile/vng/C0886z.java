package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.graphics.Rect;
import android.location.Location;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.facebook.places.model.PlaceFields;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.ironsource.sdk.constants.LocationConst;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.vng.z */
class C0886z {
    /* renamed from: a */
    String f1504a = "{}";
    /* renamed from: b */
    private C0885a f1505b = new C0885a();
    /* renamed from: c */
    private JSONObject f1506c;
    /* renamed from: d */
    private Rect f1507d;
    /* renamed from: e */
    private Rect f1508e;
    /* renamed from: f */
    private JSONObject f1509f;
    /* renamed from: g */
    private JSONObject f1510g;
    /* renamed from: h */
    private Location f1511h;
    /* renamed from: i */
    private Map<String, Object> f1512i = new HashMap();

    /* renamed from: com.moat.analytics.mobile.vng.z$1 */
    static class C08841 implements Comparator<Rect> {
        C08841() {
        }

        /* renamed from: a */
        public int m1639a(Rect rect, Rect rect2) {
            return Integer.valueOf(rect.top).compareTo(Integer.valueOf(rect2.top));
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m1639a((Rect) obj, (Rect) obj2);
        }
    }

    /* renamed from: com.moat.analytics.mobile.vng.z$a */
    private static class C0885a {
        /* renamed from: a */
        Rect f1501a = new Rect(0, 0, 0, 0);
        /* renamed from: b */
        double f1502b = 0.0d;
        /* renamed from: c */
        double f1503c = 0.0d;

        C0885a() {
        }
    }

    C0886z() {
    }

    @VisibleForTesting
    /* renamed from: a */
    static int m1640a(Rect rect, Set<Rect> set) {
        int i = 0;
        if (set.isEmpty()) {
            return 0;
        }
        List<Rect> arrayList = new ArrayList();
        arrayList.addAll(set);
        Collections.sort(arrayList, new C08841());
        List arrayList2 = new ArrayList();
        for (Rect rect2 : arrayList) {
            arrayList2.add(Integer.valueOf(rect2.left));
            arrayList2.add(Integer.valueOf(rect2.right));
        }
        Collections.sort(arrayList2);
        int i2 = 0;
        while (i < arrayList2.size() - 1) {
            if (!((Integer) arrayList2.get(i)).equals(arrayList2.get(i + 1))) {
                Rect rect3 = new Rect(((Integer) arrayList2.get(i)).intValue(), rect.top, ((Integer) arrayList2.get(i + 1)).intValue(), rect.bottom);
                int i3 = rect.top;
                int i4 = i2;
                i2 = i3;
                for (Rect rect22 : arrayList) {
                    if (Rect.intersects(rect22, rect3)) {
                        if (rect22.bottom > i2) {
                            i4 += (rect22.bottom - Math.max(i2, rect22.top)) * rect3.width();
                            i2 = rect22.bottom;
                        }
                        if (rect22.bottom == rect3.bottom) {
                            i2 = i4;
                            break;
                        }
                    }
                    i4 = i4;
                    i2 = i2;
                }
                i2 = i4;
            }
            i++;
        }
        return i2;
    }

    /* renamed from: a */
    private static Rect m1641a(DisplayMetrics displayMetrics) {
        return new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    /* renamed from: a */
    static Rect m1642a(View view) {
        return view != null ? C0886z.m1657h(view) : new Rect(0, 0, 0, 0);
    }

    /* renamed from: a */
    private static C0885a m1643a(View view, Rect rect, boolean z, boolean z2, boolean z3) {
        C0885a c0885a = new C0885a();
        int b = C0886z.m1648b(rect);
        if (view != null && z && z2 && !z3 && b > 0) {
            Rect rect2 = new Rect(0, 0, 0, 0);
            if (view.getGlobalVisibleRect(rect2)) {
                int b2 = C0886z.m1648b(rect2);
                if (b2 < b) {
                    C0858p.m1580b(2, "VisibilityInfo", null, "Ad is clipped");
                }
                Set hashSet = new HashSet();
                if (view.getRootView() instanceof ViewGroup) {
                    c0885a.f1501a = rect2;
                    boolean a = C0886z.m1647a(rect2, view, hashSet);
                    if (a) {
                        c0885a.f1503c = 1.0d;
                    }
                    if (!a) {
                        int a2 = C0886z.m1640a(rect2, hashSet);
                        if (a2 > 0) {
                            c0885a.f1503c = ((double) a2) / (((double) b2) * 1.0d);
                        }
                        c0885a.f1502b = ((double) (b2 - a2)) / (((double) b) * 1.0d);
                    }
                }
            }
        }
        return c0885a;
    }

    /* renamed from: a */
    private static Map<String, String> m1644a(Rect rect) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("x", String.valueOf(rect.left));
        hashMap.put("y", String.valueOf(rect.top));
        hashMap.put("w", String.valueOf(rect.right - rect.left));
        hashMap.put("h", String.valueOf(rect.bottom - rect.top));
        return hashMap;
    }

    /* renamed from: a */
    private static Map<String, String> m1645a(Rect rect, DisplayMetrics displayMetrics) {
        return C0886z.m1644a(C0886z.m1649b(rect, displayMetrics));
    }

    /* renamed from: a */
    private static JSONObject m1646a(Location location) {
        Map b = C0886z.m1650b(location);
        return b == null ? null : new JSONObject(b);
    }

    /* renamed from: a */
    private static boolean m1647a(Rect rect, View view, Set<Rect> set) {
        View rootView = view.getRootView();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(rootView);
        C0858p.m1580b(2, "VisibilityInfo", view, "starting covering rect search");
        boolean z = false;
        int i = 0;
        while (!arrayDeque.isEmpty() && i < 250) {
            int i2 = i + 1;
            rootView = (View) arrayDeque.pollLast();
            if (rootView.equals(view)) {
                C0858p.m1580b(2, "VisibilityInfo", rect, "found target");
                z = true;
                i = i2;
            } else if (C0886z.m1656g(rootView)) {
                boolean z2;
                if ((rootView instanceof ViewGroup) && !(rootView instanceof ListView)) {
                    ViewGroup viewGroup = (ViewGroup) rootView;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        arrayDeque.add(viewGroup.getChildAt(childCount));
                    }
                }
                if (VERSION.SDK_INT < 21) {
                    if (z) {
                        z2 = true;
                    }
                    z2 = false;
                } else if (rootView.getElevation() > view.getElevation()) {
                    z2 = true;
                } else {
                    if (z && rootView.getElevation() == view.getElevation()) {
                        z2 = true;
                    }
                    z2 = false;
                }
                if (z2) {
                    Rect h = C0886z.m1657h(rootView);
                    if (h.setIntersect(rect, h)) {
                        C0858p.m1580b(2, "VisibilityInfo", rootView, "Covered by " + rootView.getClass().getSimpleName() + "-" + h.toString());
                        set.add(h);
                        if (h.contains(rect)) {
                            return true;
                        }
                    }
                }
                i = i2;
            } else {
                i = i2;
            }
        }
        return false;
    }

    /* renamed from: b */
    private static int m1648b(Rect rect) {
        return rect.width() * rect.height();
    }

    /* renamed from: b */
    private static Rect m1649b(Rect rect, DisplayMetrics displayMetrics) {
        float f = displayMetrics.density;
        if (f == 0.0f) {
            return rect;
        }
        return new Rect(Math.round(((float) rect.left) / f), Math.round(((float) rect.top) / f), Math.round(((float) rect.right) / f), Math.round(((float) rect.bottom) / f));
    }

    /* renamed from: b */
    private static Map<String, String> m1650b(Location location) {
        if (location == null) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        hashMap.put(LocationConst.LATITUDE, Double.toString(location.getLatitude()));
        hashMap.put(LocationConst.LONGITUDE, Double.toString(location.getLongitude()));
        hashMap.put("timestamp", Long.toString(location.getTime()));
        hashMap.put("horizontalAccuracy", Float.toString(location.getAccuracy()));
        return hashMap;
    }

    /* renamed from: b */
    private static boolean m1651b(View view) {
        return VERSION.SDK_INT >= 19 ? view != null && view.isAttachedToWindow() : (view == null || view.getWindowToken() == null) ? false : true;
    }

    /* renamed from: c */
    private static boolean m1652c(View view) {
        return view != null && view.hasWindowFocus();
    }

    /* renamed from: d */
    private static boolean m1653d(View view) {
        return view == null || !view.isShown();
    }

    /* renamed from: e */
    private static float m1654e(View view) {
        return view == null ? 0.0f : C0886z.m1655f(view);
    }

    /* renamed from: f */
    private static float m1655f(View view) {
        float alpha = view.getAlpha();
        while (view != null && view.getParent() != null && ((double) alpha) != 0.0d && (view.getParent() instanceof View)) {
            alpha *= ((View) view.getParent()).getAlpha();
            view = (View) view.getParent();
        }
        return alpha;
    }

    /* renamed from: g */
    private static boolean m1656g(View view) {
        return view.isShown() && ((double) view.getAlpha()) > 0.0d;
    }

    /* renamed from: h */
    private static Rect m1657h(View view) {
        int[] iArr = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }

    /* renamed from: i */
    private static DisplayMetrics m1658i(View view) {
        if (VERSION.SDK_INT >= 17 && C0821a.f1339a != null) {
            Activity activity = (Activity) C0821a.f1339a.get();
            if (activity != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
                return displayMetrics;
            }
        }
        return view.getContext().getResources().getDisplayMetrics();
    }

    /* renamed from: a */
    void m1659a(String str, View view) {
        Map hashMap = new HashMap();
        String str2 = "{}";
        Object obj = null;
        if (view != null) {
            try {
                DisplayMetrics i = C0886z.m1658i(view);
                boolean b = C0886z.m1651b(view);
                boolean c = C0886z.m1652c(view);
                boolean d = C0886z.m1653d(view);
                float e = C0886z.m1654e(view);
                hashMap.put("dr", Float.valueOf(i.density));
                hashMap.put("dv", Double.valueOf(C0862s.m1586a()));
                hashMap.put("adKey", str);
                hashMap.put("isAttached", Integer.valueOf(b ? 1 : 0));
                hashMap.put("inFocus", Integer.valueOf(c ? 1 : 0));
                hashMap.put("isHidden", Integer.valueOf(d ? 1 : 0));
                hashMap.put("opacity", Float.valueOf(e));
                Rect a = C0886z.m1641a(i);
                Rect a2 = C0886z.m1642a(view);
                C0885a a3 = C0886z.m1643a(view, a2, b, c, d);
                if (!(this.f1506c != null && a3.f1502b == this.f1505b.f1502b && a3.f1501a.equals(this.f1505b.f1501a) && a3.f1503c == this.f1505b.f1503c)) {
                    this.f1505b = a3;
                    this.f1506c = new JSONObject(C0886z.m1645a(this.f1505b.f1501a, i));
                    obj = 1;
                }
                hashMap.put("coveredPercent", Double.valueOf(a3.f1503c));
                if (this.f1510g == null || !a.equals(this.f1508e)) {
                    this.f1508e = a;
                    this.f1510g = new JSONObject(C0886z.m1645a(a, i));
                    obj = 1;
                }
                if (this.f1509f == null || !a2.equals(this.f1507d)) {
                    this.f1507d = a2;
                    this.f1509f = new JSONObject(C0886z.m1645a(a2, i));
                    obj = 1;
                }
                if (this.f1512i == null || !hashMap.equals(this.f1512i)) {
                    this.f1512i = hashMap;
                    obj = 1;
                }
                Location b2 = C0857o.m1554a().m1573b();
                if (!C0857o.m1559a(b2, this.f1511h)) {
                    obj = 1;
                    this.f1511h = b2;
                }
                if (obj != null) {
                    JSONObject jSONObject = new JSONObject(this.f1512i);
                    jSONObject.accumulate("screen", this.f1510g);
                    jSONObject.accumulate(ParametersKeys.VIEW, this.f1509f);
                    jSONObject.accumulate(String.VISIBLE, this.f1506c);
                    jSONObject.accumulate("maybe", this.f1506c);
                    jSONObject.accumulate("visiblePercent", Double.valueOf(this.f1505b.f1502b));
                    if (b2 != null) {
                        jSONObject.accumulate(PlaceFields.LOCATION, C0886z.m1646a(b2));
                    }
                    this.f1504a = jSONObject.toString();
                    return;
                }
                String str3 = this.f1504a;
            } catch (Exception e2) {
                C0849m.m1543a(e2);
                this.f1504a = str2;
            }
        }
    }
}
