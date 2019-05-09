// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.view.ViewParent;
import android.graphics.Typeface;
import android.widget.TextView;
import android.annotation.TargetApi;
import android.transition.TransitionManager;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.transition.Transition;
import android.transition.AutoTransition;
import android.view.ViewGroup;
import android.view.TouchDelegate;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build$VERSION;
import android.view.View;
import android.util.TypedValue;
import android.content.res.Resources;
import java.util.concurrent.atomic.AtomicInteger;
import android.util.DisplayMetrics;

public class x
{
    public static final DisplayMetrics a;
    public static final float b;
    private static final AtomicInteger c;
    
    static {
        a = Resources.getSystem().getDisplayMetrics();
        b = x.a.density;
        c = new AtomicInteger(1);
    }
    
    public static int a() {
        int value;
        int n;
        do {
            value = x.c.get();
            if ((n = value + 1) > 16777215) {
                n = 1;
            }
        } while (!x.c.compareAndSet(value, n));
        return value;
    }
    
    public static int a(final int n) {
        return (int)TypedValue.applyDimension(2, (float)n, x.a);
    }
    
    public static void a(final View view) {
        if (Build$VERSION.SDK_INT >= 17) {
            view.setId(View.generateViewId());
            return;
        }
        view.setId(a());
    }
    
    public static void a(final View view, final int n) {
        if (Build$VERSION.SDK_INT >= 16) {
            view.setBackground((Drawable)new ColorDrawable(n));
            return;
        }
        view.setBackgroundDrawable((Drawable)new ColorDrawable(n));
    }
    
    public static void a(final View view, final Drawable drawable) {
        if (Build$VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
            return;
        }
        view.setBackgroundDrawable(drawable);
    }
    
    public static void a(final View view, final View view2, final int n, final int n2) {
        if (view2 == null || view == null) {
            return;
        }
        view.post((Runnable)new Runnable() {
            @Override
            public void run() {
                final Rect rect = new Rect();
                view2.getHitRect(rect);
                final View view = (View)view2.getParent();
                final Rect rect2 = new Rect();
                view.getGlobalVisibleRect(rect2);
                final Rect rect3 = new Rect();
                view.getGlobalVisibleRect(rect3);
                final int n = rect2.left - rect3.left;
                final int n2 = rect2.top - rect3.top;
                rect.left += n - n;
                rect.top += n2 - n2;
                rect.right += n + n;
                rect.bottom += n2 + n2;
                view.setTouchDelegate(new TouchDelegate(rect, view2));
            }
        });
    }
    
    public static void a(final ViewGroup viewGroup) {
        if (Build$VERSION.SDK_INT > 19) {
            a(viewGroup, 200);
        }
    }
    
    public static void a(final ViewGroup viewGroup, final int n) {
        if (Build$VERSION.SDK_INT > 19) {
            a(viewGroup, (Transition)new AutoTransition(), n);
        }
    }
    
    public static void a(final ViewGroup viewGroup, final Transition transition) {
        if (Build$VERSION.SDK_INT > 19) {
            a(viewGroup, transition, 200);
        }
    }
    
    @TargetApi(19)
    private static void a(final ViewGroup viewGroup, final Transition transition, final int n) {
        transition.setDuration((long)n);
        transition.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
        TransitionManager.beginDelayedTransition(viewGroup, transition);
    }
    
    public static void a(final TextView textView, final boolean b, final int n) {
        Typeface typeface;
        if (b) {
            if (Build$VERSION.SDK_INT >= 21) {
                typeface = Typeface.create("sans-serif-medium", 0);
            }
            else {
                typeface = Typeface.create(Typeface.SANS_SERIF, 1);
            }
        }
        else {
            typeface = Typeface.create(Typeface.SANS_SERIF, 0);
        }
        textView.setTypeface(typeface);
        textView.setTextSize(2, (float)n);
    }
    
    public static void a(final View... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            b(array[i]);
        }
    }
    
    public static void b(final View view) {
        if (view != null) {
            final ViewGroup viewGroup = (ViewGroup)view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }
    
    public static void c(final View view) {
        final ViewParent parent = view.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            a((ViewGroup)parent);
        }
    }
}
