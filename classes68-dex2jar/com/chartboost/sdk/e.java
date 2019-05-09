// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk;

import android.util.DisplayMetrics;
import com.chartboost.sdk.Tracking.a;
import android.widget.RelativeLayout;
import java.util.Iterator;
import android.app.Activity;
import com.chartboost.sdk.Model.CBError;
import android.content.Context;
import com.chartboost.sdk.Libraries.CBLogging;
import android.graphics.Color;
import com.chartboost.sdk.Libraries.CBUtility;
import java.util.IdentityHashMap;
import android.view.View;
import java.util.Map;
import org.json.JSONObject;
import android.os.Handler;

public abstract class e
{
    public final Handler a;
    public final c b;
    public boolean c;
    protected JSONObject d;
    public final com.chartboost.sdk.Model.c e;
    protected int f;
    public final Map<View, Runnable> g;
    protected boolean h;
    protected boolean i;
    private boolean j;
    private a k;
    
    public e(final com.chartboost.sdk.Model.c e, final Handler a, final c b) {
        this.c = false;
        this.g = new IdentityHashMap<View, Runnable>();
        this.h = true;
        this.i = true;
        this.a = a;
        this.b = b;
        this.e = e;
        this.k = null;
        this.f = CBUtility.a();
        this.j = false;
    }
    
    public static int a(String string) {
        int color = 0;
        if (string == null) {
            return color;
        }
        String string2 = string;
        Label_0045: {
            if (string.startsWith("#")) {
                break Label_0045;
            }
            try {
                color = Color.parseColor(string);
                return color;
            }
            catch (IllegalArgumentException ex2) {
                string2 = "#" + string;
            }
        }
        Label_0134: {
            if (string2.length() != 4) {
                string = string2;
                if (string2.length() != 5) {
                    break Label_0134;
                }
            }
            final StringBuilder sb = new StringBuilder(string2.length() * 2 + 1);
            sb.append("#");
            for (int i = 0; i < string2.length() - 1; ++i) {
                sb.append(string2.charAt(i + 1));
                sb.append(string2.charAt(i + 1));
            }
            string = sb.toString();
            try {
                return Color.parseColor(string);
            }
            catch (IllegalArgumentException ex) {
                CBLogging.c("CBViewProtocol", "error parsing color " + string, ex);
                return 0;
            }
        }
    }
    
    public static boolean a(final Context context) {
        return (context.getResources().getConfiguration().screenLayout & 0xF) >= 4;
    }
    
    public int a() {
        return this.f;
    }
    
    public void a(final View view, final Runnable runnable, final long n) {
        synchronized (this.g) {
            final Runnable runnable2 = this.g.get(view);
            if (runnable2 != null) {
                this.a.removeCallbacks(runnable2);
            }
            this.g.put(view, runnable);
            // monitorexit(this.g)
            this.a.postDelayed(runnable, n);
        }
    }
    
    public void a(final CBError.CBImpressionError cbImpressionError) {
        this.e.a(cbImpressionError);
    }
    
    public void a(final boolean b, final View view) {
        this.a(b, view, true);
    }
    
    public void a(final boolean clickable, final View view, final boolean b) {
        int visibility = 8;
        while (true) {
            if ((clickable && view.getVisibility() == 0) || (!clickable && view.getVisibility() == 8)) {
                synchronized (this.g) {
                    if (!this.g.containsKey(view)) {
                        return;
                    }
                    // monitorexit(this.g)
                    if (!b) {
                        if (clickable) {
                            visibility = 0;
                        }
                        view.setVisibility(visibility);
                        view.setClickable(clickable);
                        return;
                    }
                }
                final View view2;
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (!clickable) {
                            view2.setVisibility(8);
                            view2.setClickable(false);
                        }
                        synchronized (com.chartboost.sdk.e.this.g) {
                            com.chartboost.sdk.e.this.g.remove(view2);
                        }
                    }
                };
                if (this.e.p.b == 1) {}
                this.e.i.a.a(clickable, view2, 500L);
                this.a(view2, runnable, 500L);
                return;
            }
            continue;
        }
    }
    
    public boolean a(final JSONObject jsonObject) {
        this.d = com.chartboost.sdk.Libraries.e.a(jsonObject, "assets");
        if (this.d == null) {
            this.d = new JSONObject();
            CBLogging.b("CBViewProtocol", "Media got from the response is null or empty");
            this.a(CBError.CBImpressionError.INVALID_RESPONSE);
            return false;
        }
        return true;
    }
    
    protected abstract a b(final Context p0);
    
    public void b() {
        this.i();
    }
    
    public boolean b(final JSONObject jsonObject) {
        return this.e.a(jsonObject);
    }
    
    public CBError.CBImpressionError c() {
        final CBError.CBImpressionError cbImpressionError = null;
        final Activity b = this.b.b();
        Enum<CBError.CBImpressionError> no_HOST_ACTIVITY;
        if (b == null) {
            this.k = null;
            no_HOST_ACTIVITY = CBError.CBImpressionError.NO_HOST_ACTIVITY;
        }
        else {
            if (!this.i && !this.h) {
                return CBError.CBImpressionError.WRONG_ORIENTATION;
            }
            if (this.k == null) {
                this.k = this.b((Context)b);
            }
            no_HOST_ACTIVITY = cbImpressionError;
            if (this.e.p.b == 0) {
                no_HOST_ACTIVITY = cbImpressionError;
                if (!this.k.a(b)) {
                    this.k = null;
                    return CBError.CBImpressionError.ERROR_CREATING_VIEW;
                }
            }
        }
        return (CBError.CBImpressionError)no_HOST_ACTIVITY;
    }
    
    public void d() {
        this.f();
        synchronized (this.g) {
            final Iterator<Runnable> iterator = this.g.values().iterator();
            while (iterator.hasNext()) {
                this.a.removeCallbacks((Runnable)iterator.next());
            }
        }
        this.g.clear();
    }
    // monitorexit(map)
    
    public a e() {
        return this.k;
    }
    
    public void f() {
        if (this.k != null) {
            this.k.b();
        }
        this.k = null;
    }
    
    public JSONObject g() {
        return this.d;
    }
    
    public void h() {
        if (this.j) {
            return;
        }
        this.j = true;
        this.e.c();
    }
    
    protected void i() {
        this.e.d();
    }
    
    public float j() {
        return 0.0f;
    }
    
    public float k() {
        return 0.0f;
    }
    
    public boolean l() {
        return false;
    }
    
    public void m() {
        if (this.c) {
            this.c = false;
        }
        final a e = this.e();
        if (e != null && (e.a == null || CBUtility.a() != e.a)) {
            e.a(false);
        }
    }
    
    public void n() {
        this.c = true;
    }
    
    public abstract class a extends RelativeLayout
    {
        Integer a;
        private boolean c;
        private int d;
        private int e;
        private int f;
        private int g;
        
        public a(final Context context) {
            super(context);
            this.c = false;
            this.d = -1;
            this.e = -1;
            this.f = -1;
            this.g = -1;
            this.a = null;
            this.setFocusableInTouchMode(true);
            this.requestFocus();
        }
        
        private boolean b(final int d, final int e) {
            boolean b = true;
            if (e.this.e == null || e.this.e.p.b != 1) {
                if (this.c) {
                    return false;
                }
                final int a = CBUtility.a();
                if (this.d != d || this.e != e || this.a == null || this.a != a) {
                    this.c = true;
                    while (true) {
                        try {
                            if (e.this.h && CBUtility.a(a)) {
                                e.this.f = a;
                            }
                            else if (e.this.i && CBUtility.b(a)) {
                                e.this.f = a;
                            }
                            this.a(d, e);
                            this.post((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    com.chartboost.sdk.e.a.this.requestLayout();
                                }
                            });
                            this.d = d;
                            this.e = e;
                            this.a = a;
                            this.c = false;
                            return b;
                        }
                        catch (Exception ex) {
                            CBLogging.a("CBViewProtocol", "Exception raised while layouting Subviews", ex);
                            com.chartboost.sdk.Tracking.a.a(this.getClass(), "tryLayout", ex);
                            b = false;
                            continue;
                        }
                        break;
                    }
                }
            }
            return true;
        }
        
        public final void a() {
            this.a(false);
        }
        
        protected abstract void a(final int p0, final int p1);
        
        public final void a(final View view) {
            int id = 200;
            if (200 == this.getId()) {
                id = 201;
            }
            for (View view2 = this.findViewById(id); view2 != null; view2 = this.findViewById(id)) {
                ++id;
            }
            view.setId(id);
            view.setSaveEnabled(false);
        }
        
        public final void a(final boolean b) {
            if (b) {
                this.a = null;
            }
            this.a((Activity)this.getContext());
        }
        
        public boolean a(final Activity activity) {
            Label_0138: {
                if (this.f != -1) {
                    if (this.g != -1) {
                        break Label_0138;
                    }
                }
                while (true) {
                    try {
                        int f = this.getWidth();
                        final int height = this.getHeight();
                        int height2;
                        if (f == 0 || (height2 = height) == 0) {
                            View view;
                            if ((view = activity.getWindow().findViewById(16908290)) == null) {
                                view = activity.getWindow().getDecorView();
                            }
                            f = view.getWidth();
                            height2 = view.getHeight();
                        }
                        int heightPixels;
                        if (f == 0 || (heightPixels = height2) == 0) {
                            final DisplayMetrics displayMetrics = new DisplayMetrics();
                            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                            f = displayMetrics.widthPixels;
                            heightPixels = displayMetrics.heightPixels;
                        }
                        this.f = f;
                        this.g = heightPixels;
                        return this.b(this.f, this.g);
                    }
                    catch (Exception ex) {
                        final int height2 = 0;
                        final int f = 0;
                        continue;
                    }
                    break;
                }
            }
        }
        
        public void b() {
        }
        
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            synchronized (com.chartboost.sdk.e.this.g) {
                final Iterator<Runnable> iterator = com.chartboost.sdk.e.this.g.values().iterator();
                while (iterator.hasNext()) {
                    com.chartboost.sdk.e.this.a.removeCallbacks((Runnable)iterator.next());
                }
            }
            com.chartboost.sdk.e.this.g.clear();
        }
        // monitorexit(map)
        
        protected void onSizeChanged(final int f, final int g, final int n, final int n2) {
            super.onSizeChanged(f, g, n, n2);
            this.f = f;
            this.g = g;
            if (this.d != -1 && this.e != -1 && com.chartboost.sdk.e.this.e != null && com.chartboost.sdk.e.this.e.p.b == 0) {
                this.a();
            }
        }
    }
}
