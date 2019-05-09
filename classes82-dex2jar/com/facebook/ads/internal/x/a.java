// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.x;

import com.facebook.ads.internal.w.b.y;
import com.facebook.ads.internal.w.b.v;
import org.json.JSONObject;
import java.util.Collection;
import android.app.Activity;
import android.view.Display;
import android.content.Context;
import com.facebook.ads.internal.w.b.z;
import java.util.Locale;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.os.Build$VERSION;
import java.util.Arrays;
import java.lang.reflect.Array;
import android.graphics.Rect;
import java.util.Vector;
import android.support.annotation.VisibleForTesting;
import android.view.ViewGroup;
import com.facebook.ads.internal.w.b.x;
import java.util.HashMap;
import java.util.Map;
import android.os.Handler;
import java.lang.ref.WeakReference;
import android.view.View;

public class a
{
    private static final String a;
    private final View b;
    private final int c;
    private final int d;
    private final WeakReference<a> e;
    private final Handler f;
    private final boolean g;
    private Runnable h;
    private int i;
    private int j;
    private boolean k;
    private com.facebook.ads.internal.x.b l;
    private Map<String, Integer> m;
    private long n;
    private int o;
    
    static {
        a = a.class.getSimpleName();
    }
    
    public a(final View b, int n, final int n2, final boolean g, final a a) {
        this.f = new Handler();
        this.i = 0;
        this.j = 1000;
        this.k = true;
        this.l = new com.facebook.ads.internal.x.b(com.facebook.ads.internal.x.c.a);
        this.m = new HashMap<String, Integer>();
        this.n = 0L;
        this.o = 0;
        this.b = b;
        if (this.b.getId() == -1) {
            x.a(this.b);
        }
        this.c = n;
        this.e = new WeakReference<a>(a);
        this.g = g;
        if ((n = n2) < 0) {
            n = 0;
        }
        this.d = n;
    }
    
    public a(final View view, final int n, final a a) {
        this(view, n, 0, false, a);
    }
    
    public a(final View view, final int n, final boolean b, final a a) {
        this(view, n, 0, b, a);
    }
    
    @VisibleForTesting
    static float a(View view) {
        float alpha = view.getAlpha();
        while (view.getParent() instanceof ViewGroup) {
            view = (View)view.getParent();
            float alpha2;
            if ((alpha2 = view.getAlpha()) < 0.0f) {
                alpha2 = 0.0f;
            }
            float n = alpha2;
            if (alpha2 > 1.0f) {
                n = 1.0f;
            }
            alpha *= n;
        }
        return alpha;
    }
    
    private static int a(final Vector<Rect> vector) {
        final int size = vector.size();
        final int[] array = new int[size * 2];
        final int[] array2 = new int[size * 2];
        final boolean[][] array3 = (boolean[][])Array.newInstance(Boolean.TYPE, size * 2, size * 2);
        int i = 0;
        int n = 0;
        int n2 = 0;
        while (i < size) {
            final Rect rect = vector.elementAt(i);
            final int n3 = n2 + 1;
            array[n2] = rect.left;
            final int n4 = n + 1;
            array2[n] = rect.bottom;
            n2 = n3 + 1;
            array[n3] = rect.right;
            n = n4 + 1;
            array2[n4] = rect.top;
            ++i;
        }
        Arrays.sort(array);
        Arrays.sort(array2);
        for (int j = 0; j < size; ++j) {
            final Rect rect2 = vector.elementAt(j);
            final int a = a(array, rect2.left);
            final int a2 = a(array, rect2.right);
            final int a3 = a(array2, rect2.top);
            final int a4 = a(array2, rect2.bottom);
            for (int k = a + 1; k <= a2; ++k) {
                for (int l = a3 + 1; l <= a4; ++l) {
                    array3[k][l] = true;
                }
            }
        }
        int n5 = 0;
        int n6 = 0;
        while (n5 < size * 2) {
            for (int n7 = 0; n7 < size * 2; ++n7) {
                int n8;
                if (array3[n5][n7]) {
                    n8 = (array[n5] - array[n5 - 1]) * (array2[n7] - array2[n7 - 1]);
                }
                else {
                    n8 = 0;
                }
                n6 += n8;
            }
            ++n5;
        }
        return n6;
    }
    
    private static int a(final int[] array, final int n) {
        int i = 0;
        int length = array.length;
        while (i < length) {
            final int n2 = (length - i) / 2 + i;
            if (array[n2] == n) {
                return n2;
            }
            if (array[n2] > n) {
                length = n2;
            }
            else {
                i = n2 + 1;
            }
        }
        return -1;
    }
    
    public static com.facebook.ads.internal.x.b a(final View view, int n) {
        if (view == null) {
            return new com.facebook.ads.internal.x.b(c.c);
        }
        if (view.getParent() == null) {
            return new com.facebook.ads.internal.x.b(c.d);
        }
        if (!view.isShown()) {
            return new com.facebook.ads.internal.x.b(c.d);
        }
        if (view.getWindowVisibility() != 0) {
            return new com.facebook.ads.internal.x.b(c.e);
        }
        if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
            new StringBuilder().append("mAdView has invisible dimensions (w=").append(view.getMeasuredWidth()).append(", h=").append(view.getMeasuredHeight()).toString();
            return new com.facebook.ads.internal.x.b(c.g);
        }
        if (a(view) < 0.9f) {
            return new com.facebook.ads.internal.x.b(c.h);
        }
        final int width = view.getWidth();
        final int height = view.getHeight();
        final int[] array = new int[2];
        Rect rect;
        try {
            view.getLocationOnScreen(array);
            rect = new Rect();
            if (!view.getGlobalVisibleRect(rect)) {
                return new com.facebook.ads.internal.x.b(c.f);
            }
        }
        catch (NullPointerException ex) {
            return new com.facebook.ads.internal.x.b(c.g);
        }
        final Context context = view.getContext();
        DisplayMetrics displayMetrics;
        if (Build$VERSION.SDK_INT >= 17) {
            final Display defaultDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
            displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
        }
        else {
            displayMetrics = context.getResources().getDisplayMetrics();
        }
        final Vector<Rect> b = b(view);
        final int a = a(b);
        b.add(rect);
        final float n2 = (a(b) - a) * 1.0f / (view.getMeasuredHeight() * view.getMeasuredWidth());
        final boolean l = com.facebook.ads.internal.r.a.L(context);
        final int n3 = view.getWidth() * view.getHeight();
        float n4;
        if (n3 > 0) {
            n4 = 100.0f / n3;
        }
        else {
            n4 = 100.0f;
        }
        n = (int)Math.max(n, Math.ceil(n4));
        final float n5 = n / 100.0f;
        if (l) {
            if (n2 < n5) {
                String.format(Locale.US, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", n2, n5);
                return new com.facebook.ads.internal.x.b(c.n, n2);
            }
        }
        else {
            if (array[0] < 0 || displayMetrics.widthPixels - array[0] < width) {
                return new com.facebook.ads.internal.x.b(c.j, n2);
            }
            n = (int)((100.0 - n) * height / 100.0);
            if (rect.top - array[1] > n) {
                return new com.facebook.ads.internal.x.b(c.k, n2);
            }
            if (array[1] + height - rect.bottom > n) {
                return new com.facebook.ads.internal.x.b(c.l, n2);
            }
        }
        if (!com.facebook.ads.internal.w.i.a.b(context)) {
            return new com.facebook.ads.internal.x.b(c.m, n2);
        }
        final Map<String, String> a2 = com.facebook.ads.internal.w.i.b.a(context);
        if (z.b(a2)) {
            return new com.facebook.ads.internal.x.b(c.p, n2);
        }
        if (com.facebook.ads.internal.r.a.j(context) && z.a(a2)) {
            return new com.facebook.ads.internal.x.b(c.q, n2, a2);
        }
        Float n6 = null;
        if (com.facebook.ads.internal.r.a.H(context)) {
            final Activity a3 = com.facebook.ads.internal.w.a.b.a();
            if (a3 == null) {
                n6 = null;
            }
            else {
                View view2;
                if ((view2 = a3.findViewById(16908290)) == null) {
                    view2 = a3.getWindow().getDecorView().findViewById(16908290);
                }
                if (view2 == null) {
                    n6 = null;
                }
                else if (view == null || view.getId() == -1) {
                    n6 = null;
                }
                else if (view2.findViewById(view.getId()) == null) {
                    n6 = -1.0f;
                }
                else {
                    n6 = d.a(view2, view);
                }
            }
        }
        if (n6 != null) {
            if (n6 == -1.0f) {
                return new com.facebook.ads.internal.x.b(c.r);
            }
            if (n6 == 0.0f) {
                return new com.facebook.ads.internal.x.b(c.f);
            }
        }
        if (com.facebook.ads.internal.r.a.I(context) && n6 != null && n6 < n5) {
            String.format(Locale.US, "mAdView visible area is too small [%.2f%% visible, current threshold %.2f%%]", n6, n5);
            return new com.facebook.ads.internal.x.b(c.n, n2, a2);
        }
        return new com.facebook.ads.internal.x.b(c.b, n2, a2);
    }
    
    private static Vector<Rect> b(final View view) {
        final Vector<Rect> vector = new Vector<Rect>();
        if (!(view.getParent() instanceof ViewGroup)) {
            return vector;
        }
        final ViewGroup viewGroup = (ViewGroup)view.getParent();
        for (int i = viewGroup.indexOfChild(view) + 1; i < viewGroup.getChildCount(); ++i) {
            vector.addAll(c(viewGroup.getChildAt(i)));
        }
        vector.addAll(b((View)viewGroup));
        return vector;
    }
    
    private static Vector<Rect> c(final View view) {
        final int n = 0;
        final Vector<Rect> vector = new Vector<Rect>();
        if (!view.isShown() || (Build$VERSION.SDK_INT >= 11 && view.getAlpha() <= 0.0f)) {
            return vector;
        }
        if (view instanceof ViewGroup) {
            int n2;
            if (view.getBackground() == null || (Build$VERSION.SDK_INT >= 19 && view.getBackground().getAlpha() <= 0)) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            if (n2 != 0) {
                final ViewGroup viewGroup = (ViewGroup)view;
                for (int i = n; i < viewGroup.getChildCount(); ++i) {
                    vector.addAll(c(viewGroup.getChildAt(i)));
                }
                return vector;
            }
        }
        final Rect rect = new Rect();
        if (view.getGlobalVisibleRect(rect)) {
            vector.add(rect);
        }
        return vector;
    }
    
    public void a() {
        synchronized (this) {
            if (this.h != null) {
                this.c();
            }
            this.h = new b(this);
            this.f.postDelayed(this.h, (long)this.i);
            this.k = false;
            this.o = 0;
            this.l = new com.facebook.ads.internal.x.b(com.facebook.ads.internal.x.c.a);
            this.m = new HashMap<String, Integer>();
        }
    }
    
    public void a(final int i) {
        this.i = i;
    }
    
    public void a(final Map<String, String> map) {
        synchronized (this) {
            map.put("vrc", String.valueOf(this.l.b()));
            map.put("vp", String.valueOf(this.l.c()));
            map.put("vh", new JSONObject((Map)this.m).toString());
            map.put("vt", v.b(this.n));
            map.putAll(this.l.d());
        }
    }
    
    public void b(final int j) {
        this.j = j;
    }
    
    public boolean b() {
        synchronized (this) {
            return this.k;
        }
    }
    
    public void c() {
        synchronized (this) {
            this.f.removeCallbacks(this.h);
            this.h = null;
            this.k = true;
            this.o = 0;
        }
    }
    
    public String d() {
        synchronized (this) {
            return com.facebook.ads.internal.x.c.values()[this.l.b()].toString() + String.format(Locale.US, " (%.1f%%)", this.l.c() * 100.0f);
        }
    }
    
    public abstract static class a
    {
        public abstract void a();
        
        public void b() {
        }
    }
    
    private static final class b extends y<a>
    {
        b(final a a) {
            super(a);
        }
        
        @Override
        public void run() {
            int intValue = 0;
            final a a = this.a();
            if (a != null) {
                final View a2 = a.b;
                final a a3 = (a)a.e.get();
                if (a2 != null && a3 != null) {
                    Object o = com.facebook.ads.internal.x.a.a(a2, a.c);
                    int n = 0;
                    int n2;
                    Label_0275_Outer:Label_0280_Outer:
                    while (true) {
                        Label_0109: {
                            while (true) {
                                Label_0088: {
                                    while (true) {
                                        Label_0073: {
                                            if (((com.facebook.ads.internal.x.b)o).a()) {
                                                a.o++;
                                                break Label_0073;
                                            }
                                            Label_0265: {
                                                break Label_0265;
                                                while (true) {
                                                    o = String.valueOf(((com.facebook.ads.internal.x.b)o).b());
                                                    while (true) {
                                                        Label_0293: {
                                                            synchronized (a) {
                                                                if (a.m.containsKey(o)) {
                                                                    intValue = (int)a.m.get(o);
                                                                }
                                                                a.m.put(o, intValue + 1);
                                                                // monitorexit(a)
                                                                if (n == 0 || n2 != 0) {
                                                                    break Label_0293;
                                                                }
                                                                a.n = System.currentTimeMillis();
                                                                a3.a();
                                                                if (!a.g) {
                                                                    return;
                                                                }
                                                                if (!a.k && a.h != null) {
                                                                    a.f.postDelayed(a.h, (long)a.j);
                                                                    return;
                                                                }
                                                                return;
                                                                a.o = 0;
                                                                break;
                                                                n = 0;
                                                                break Label_0088;
                                                                n2 = 0;
                                                                break Label_0109;
                                                            }
                                                        }
                                                        if (n == 0 && n2 != 0) {
                                                            a3.b();
                                                            continue Label_0275_Outer;
                                                        }
                                                        continue Label_0275_Outer;
                                                    }
                                                }
                                            }
                                        }
                                        if (a.o <= a.d) {
                                            continue Label_0280_Outer;
                                        }
                                        break;
                                    }
                                    n = 1;
                                }
                                if (a.l == null || !a.l.a()) {
                                    continue;
                                }
                                break;
                            }
                            n2 = 1;
                        }
                        if (n != 0 || !((com.facebook.ads.internal.x.b)o).a()) {
                            a.l = (com.facebook.ads.internal.x.b)o;
                        }
                        continue Label_0275_Outer;
                    }
                }
            }
        }
    }
}
