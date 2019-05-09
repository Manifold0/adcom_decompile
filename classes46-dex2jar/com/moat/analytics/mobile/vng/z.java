// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.os.Build$VERSION;
import android.widget.ListView;
import java.util.ArrayDeque;
import android.view.ViewGroup;
import java.util.HashSet;
import android.view.View;
import android.util.DisplayMetrics;
import android.support.annotation.VisibleForTesting;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import android.location.Location;
import android.graphics.Rect;
import org.json.JSONObject;

class z
{
    String a;
    private a b;
    private JSONObject c;
    private Rect d;
    private Rect e;
    private JSONObject f;
    private JSONObject g;
    private Location h;
    private Map<String, Object> i;
    
    z() {
        this.i = new HashMap<String, Object>();
        this.a = "{}";
        this.b = new a();
    }
    
    @VisibleForTesting
    static int a(final Rect rect, final Set<Rect> set) {
        int n = 0;
        int n3;
        if (!set.isEmpty()) {
            final ArrayList<Rect> list = new ArrayList<Rect>();
            list.addAll((Collection<?>)set);
            Collections.sort((List<Object>)list, (Comparator<? super Object>)new Comparator<Rect>() {
                public int a(final Rect rect, final Rect rect2) {
                    return Integer.valueOf(rect.top).compareTo(rect2.top);
                }
            });
            final ArrayList<Integer> list2 = (ArrayList<Integer>)new ArrayList<Comparable>();
            for (final Rect rect2 : list) {
                list2.add(rect2.left);
                list2.add(rect2.right);
            }
            Collections.sort((List<Comparable>)list2);
            int n2 = 0;
            while (true) {
                n3 = n2;
                if (n >= list2.size() - 1) {
                    break;
                }
                if (!((Integer)list2.get(n)).equals(list2.get(n + 1))) {
                    final Rect rect3 = new Rect((int)list2.get(n), rect.top, (int)list2.get(n + 1), rect.bottom);
                    int top = rect.top;
                    for (final Rect rect4 : list) {
                        int bottom = top;
                        int n4 = n2;
                        if (Rect.intersects(rect4, rect3)) {
                            bottom = top;
                            int n5 = n2;
                            if (rect4.bottom > top) {
                                n5 = n2 + (rect4.bottom - Math.max(top, rect4.top)) * rect3.width();
                                bottom = rect4.bottom;
                            }
                            n4 = n5;
                            if (rect4.bottom == rect3.bottom) {
                                n2 = n5;
                                break;
                            }
                        }
                        n2 = n4;
                        top = bottom;
                    }
                }
                ++n;
            }
        }
        else {
            n3 = 0;
        }
        return n3;
    }
    
    private static Rect a(final DisplayMetrics displayMetrics) {
        return new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }
    
    static Rect a(final View view) {
        if (view != null) {
            return h(view);
        }
        return new Rect(0, 0, 0, 0);
    }
    
    private static a a(final View view, Rect a, final boolean b, final boolean b2, final boolean b3) {
        final a a2 = new a();
        final int b4 = b(a);
        if (view != null && b && b2 && !b3 && b4 > 0) {
            a = new Rect(0, 0, 0, 0);
            if (view.getGlobalVisibleRect(a)) {
                final int b5 = b(a);
                if (b5 < b4) {
                    p.b(2, "VisibilityInfo", null, "Ad is clipped");
                }
                final HashSet<Rect> set = new HashSet<Rect>();
                if (view.getRootView() instanceof ViewGroup) {
                    a2.a = a;
                    final boolean a3 = a(a, view, set);
                    if (a3) {
                        a2.c = 1.0;
                    }
                    if (!a3) {
                        final int a4 = a(a, set);
                        if (a4 > 0) {
                            a2.c = a4 / (b5 * 1.0);
                        }
                        a2.b = (b5 - a4) / (b4 * 1.0);
                    }
                }
            }
        }
        return a2;
    }
    
    private static Map<String, String> a(final Rect rect) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("x", String.valueOf(rect.left));
        hashMap.put("y", String.valueOf(rect.top));
        hashMap.put("w", String.valueOf(rect.right - rect.left));
        hashMap.put("h", String.valueOf(rect.bottom - rect.top));
        return hashMap;
    }
    
    private static Map<String, String> a(final Rect rect, final DisplayMetrics displayMetrics) {
        return a(b(rect, displayMetrics));
    }
    
    private static JSONObject a(final Location location) {
        final Map<String, String> b = b(location);
        if (b == null) {
            return null;
        }
        return new JSONObject((Map)b);
    }
    
    private static boolean a(final Rect rect, final View view, final Set<Rect> set) {
        final boolean b = true;
        final View rootView = view.getRootView();
        final ArrayDeque<View> arrayDeque = new ArrayDeque<View>();
        arrayDeque.add(rootView);
        p.b(2, "VisibilityInfo", view, "starting covering rect search");
        boolean b2 = false;
        int n = 0;
    Label_0194_Outer:
        while (!arrayDeque.isEmpty() && n < 250) {
            final int n2 = n + 1;
            final View view2 = arrayDeque.pollLast();
            if (view2.equals(view)) {
                p.b(2, "VisibilityInfo", rect, "found target");
                b2 = true;
                n = n2;
            }
            else {
                if (g(view2)) {
                    if (view2 instanceof ViewGroup && !(view2 instanceof ListView)) {
                        final ViewGroup viewGroup = (ViewGroup)view2;
                        for (int i = viewGroup.getChildCount() - 1; i >= 0; --i) {
                            arrayDeque.add(viewGroup.getChildAt(i));
                        }
                    }
                    while (true) {
                        Label_0326: {
                            int n3;
                            if (Build$VERSION.SDK_INT >= 21) {
                                if (view2.getElevation() > view.getElevation()) {
                                    n3 = 1;
                                }
                                else {
                                    if (!b2 || view2.getElevation() != view.getElevation()) {
                                        break Label_0326;
                                    }
                                    n3 = 1;
                                }
                            }
                            else {
                                if (!b2) {
                                    break Label_0326;
                                }
                                n3 = 1;
                            }
                            if (n3 != 0) {
                                final Rect h = h(view2);
                                if (h.setIntersect(rect, h)) {
                                    p.b(2, "VisibilityInfo", view2, "Covered by " + view2.getClass().getSimpleName() + "-" + h.toString());
                                    set.add(h);
                                    final boolean b3 = b;
                                    if (h.contains(rect)) {
                                        return b3;
                                    }
                                }
                            }
                            n = n2;
                            continue Label_0194_Outer;
                        }
                        int n3 = 0;
                        continue;
                    }
                }
                n = n2;
            }
        }
        return false;
    }
    
    private static int b(final Rect rect) {
        return rect.width() * rect.height();
    }
    
    private static Rect b(final Rect rect, final DisplayMetrics displayMetrics) {
        final float density = displayMetrics.density;
        if (density == 0.0f) {
            return rect;
        }
        return new Rect(Math.round(rect.left / density), Math.round(rect.top / density), Math.round(rect.right / density), Math.round(rect.bottom / density));
    }
    
    private static Map<String, String> b(final Location location) {
        if (location == null) {
            return null;
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("latitude", Double.toString(location.getLatitude()));
        hashMap.put("longitude", Double.toString(location.getLongitude()));
        hashMap.put("timestamp", Long.toString(location.getTime()));
        hashMap.put("horizontalAccuracy", Float.toString(location.getAccuracy()));
        return hashMap;
    }
    
    private static boolean b(final View view) {
        if (Build$VERSION.SDK_INT >= 19) {
            if (view == null || !view.isAttachedToWindow()) {
                return false;
            }
        }
        else if (view == null || view.getWindowToken() == null) {
            return false;
        }
        return true;
    }
    
    private static boolean c(final View view) {
        return view != null && view.hasWindowFocus();
    }
    
    private static boolean d(final View view) {
        return view == null || !view.isShown();
    }
    
    private static float e(final View view) {
        if (view == null) {
            return 0.0f;
        }
        return f(view);
    }
    
    private static float f(View view) {
        float alpha;
        for (alpha = view.getAlpha(); view != null && view.getParent() != null && alpha != 0.0 && view.getParent() instanceof View; alpha *= ((View)view.getParent()).getAlpha(), view = (View)view.getParent()) {}
        return alpha;
    }
    
    private static boolean g(final View view) {
        return view.isShown() && view.getAlpha() > 0.0;
    }
    
    private static Rect h(final View view) {
        final int[] array2;
        final int[] array = array2 = new int[2];
        array2[1] = (array2[0] = Integer.MIN_VALUE);
        view.getLocationInWindow(array);
        final int n = array[0];
        final int n2 = array[1];
        return new Rect(n, n2, view.getWidth() + n, view.getHeight() + n2);
    }
    
    private static DisplayMetrics i(final View view) {
        if (Build$VERSION.SDK_INT >= 17 && com.moat.analytics.mobile.vng.a.a != null) {
            final Activity activity = com.moat.analytics.mobile.vng.a.a.get();
            if (activity != null) {
                final DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
                return displayMetrics;
            }
        }
        return view.getContext().getResources().getDisplayMetrics();
    }
    
    void a(String a, final View view) {
        final HashMap<String, Integer> i = new HashMap<String, Integer>();
        final String s = "{}";
        final int n = 0;
        if (view != null) {
            String string;
            DisplayMetrics j;
            boolean b;
            boolean c;
            boolean d;
            float e;
            int n2;
            int n3;
            int n4;
            Rect a2;
            Rect a3;
            a a4;
            int n5 = 0;
            Location b2;
            JSONObject jsonObject;
            Label_0165_Outer:Label_0193_Outer:
            while (true) {
                string = s;
                while (true) {
                Label_0784:
                    while (true) {
                    Label_0778:
                        while (true) {
                            Label_0772: {
                                try {
                                    j = i(view);
                                    string = s;
                                    b = b(view);
                                    string = s;
                                    c = c(view);
                                    string = s;
                                    d = d(view);
                                    string = s;
                                    e = e(view);
                                    string = s;
                                    i.put("dr", (Integer)(Object)Float.valueOf(j.density));
                                    string = s;
                                    i.put("dv", (Integer)(Object)Double.valueOf(com.moat.analytics.mobile.vng.s.a()));
                                    string = s;
                                    i.put("adKey", (Integer)a);
                                    if (!b) {
                                        break Label_0772;
                                    }
                                    n2 = 1;
                                    string = s;
                                    i.put("isAttached", n2);
                                    if (!c) {
                                        break Label_0778;
                                    }
                                    n3 = 1;
                                    string = s;
                                    i.put("inFocus", n3);
                                    if (!d) {
                                        break Label_0784;
                                    }
                                    n4 = 1;
                                    string = s;
                                    i.put("isHidden", n4);
                                    string = s;
                                    i.put("opacity", Float.valueOf(e));
                                    string = s;
                                    a2 = a(j);
                                    string = s;
                                    a3 = a(view);
                                    string = s;
                                    a4 = a(view, a3, b, c, d);
                                    string = s;
                                    Label_0382: {
                                        if (this.c != null) {
                                            string = s;
                                            if (a4.b == this.b.b) {
                                                string = s;
                                                if (a4.a.equals((Object)this.b.a)) {
                                                    n5 = n;
                                                    string = s;
                                                    if (a4.c == this.b.c) {
                                                        break Label_0382;
                                                    }
                                                }
                                            }
                                        }
                                        string = s;
                                        this.b = a4;
                                        string = s;
                                        this.c = new JSONObject((Map)a(this.b.a, j));
                                        n5 = 1;
                                    }
                                    string = s;
                                    i.put("coveredPercent", Double.valueOf(a4.c));
                                    string = s;
                                    Label_0463: {
                                        if (this.g != null) {
                                            string = s;
                                            if (a2.equals((Object)this.e)) {
                                                break Label_0463;
                                            }
                                        }
                                        string = s;
                                        this.e = a2;
                                        string = s;
                                        this.g = new JSONObject((Map)a(a2, j));
                                        n5 = 1;
                                    }
                                    string = s;
                                    Label_0525: {
                                        if (this.f != null) {
                                            string = s;
                                            if (a3.equals((Object)this.d)) {
                                                break Label_0525;
                                            }
                                        }
                                        string = s;
                                        this.d = a3;
                                        string = s;
                                        this.f = new JSONObject((Map)a(a3, j));
                                        n5 = 1;
                                    }
                                    string = s;
                                    Label_0567: {
                                        if (this.i != null) {
                                            string = s;
                                            if (i.equals(this.i)) {
                                                break Label_0567;
                                            }
                                        }
                                        string = s;
                                        this.i = (Map<String, Object>)i;
                                        n5 = 1;
                                    }
                                    string = s;
                                    b2 = o.a().b();
                                    string = s;
                                    if (!o.a(b2, this.h)) {
                                        n5 = 1;
                                        string = s;
                                        this.h = b2;
                                    }
                                    if (n5 != 0) {
                                        string = s;
                                        jsonObject = new JSONObject((Map)this.i);
                                        string = s;
                                        jsonObject.accumulate("screen", (Object)this.g);
                                        string = s;
                                        jsonObject.accumulate("view", (Object)this.f);
                                        string = s;
                                        jsonObject.accumulate("visible", (Object)this.c);
                                        string = s;
                                        jsonObject.accumulate("maybe", (Object)this.c);
                                        string = s;
                                        jsonObject.accumulate("visiblePercent", (Object)this.b.b);
                                        if (b2 != null) {
                                            string = s;
                                            jsonObject.accumulate("location", (Object)a(b2));
                                        }
                                        string = s;
                                        a = (string = jsonObject.toString());
                                        this.a = a;
                                        return;
                                    }
                                    string = s;
                                    a = this.a;
                                    return;
                                }
                                catch (Exception ex) {
                                    m.a(ex);
                                    this.a = string;
                                }
                                break;
                            }
                            n2 = 0;
                            continue Label_0165_Outer;
                        }
                        n3 = 0;
                        continue Label_0193_Outer;
                    }
                    n4 = 0;
                    continue;
                }
            }
        }
    }
    
    private static class a
    {
        Rect a;
        double b;
        double c;
        
        a() {
            this.a = new Rect(0, 0, 0, 0);
            this.b = 0.0;
            this.c = 0.0;
        }
    }
}
