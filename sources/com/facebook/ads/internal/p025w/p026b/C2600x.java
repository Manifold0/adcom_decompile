package com.facebook.ads.internal.p025w.p026b;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.facebook.ads.internal.w.b.x */
public class C2600x {
    /* renamed from: a */
    public static final DisplayMetrics f6419a = Resources.getSystem().getDisplayMetrics();
    /* renamed from: b */
    public static final float f6420b = f6419a.density;
    /* renamed from: c */
    private static final AtomicInteger f6421c = new AtomicInteger(1);

    /* renamed from: a */
    public static int m6677a() {
        int i;
        int i2;
        do {
            i = f6421c.get();
            i2 = i + 1;
            if (i2 > ViewCompat.MEASURED_SIZE_MASK) {
                i2 = 1;
            }
        } while (!f6421c.compareAndSet(i, i2));
        return i;
    }

    /* renamed from: a */
    public static int m6678a(int i) {
        return (int) TypedValue.applyDimension(2, (float) i, f6419a);
    }

    /* renamed from: a */
    public static void m6679a(View view) {
        if (VERSION.SDK_INT >= 17) {
            view.setId(View.generateViewId());
        } else {
            view.setId(C2600x.m6677a());
        }
    }

    /* renamed from: a */
    public static void m6680a(View view, int i) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(new ColorDrawable(i));
        } else {
            view.setBackgroundDrawable(new ColorDrawable(i));
        }
    }

    /* renamed from: a */
    public static void m6681a(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    /* renamed from: a */
    public static void m6682a(final View view, final View view2, final int i, final int i2) {
        if (view2 != null && view != null) {
            view.post(new Runnable() {
                public void run() {
                    Rect rect = new Rect();
                    view2.getHitRect(rect);
                    View view = (View) view2.getParent();
                    Rect rect2 = new Rect();
                    view.getGlobalVisibleRect(rect2);
                    Rect rect3 = new Rect();
                    view.getGlobalVisibleRect(rect3);
                    int i = rect2.left - rect3.left;
                    int i2 = rect2.top - rect3.top;
                    rect.left += i - i;
                    rect.top += i2 - i2;
                    rect.right += i + i;
                    rect.bottom = (i2 + i2) + rect.bottom;
                    view.setTouchDelegate(new TouchDelegate(rect, view2));
                }
            });
        }
    }

    /* renamed from: a */
    public static void m6683a(ViewGroup viewGroup) {
        if (VERSION.SDK_INT > 19) {
            C2600x.m6684a(viewGroup, 200);
        }
    }

    /* renamed from: a */
    public static void m6684a(ViewGroup viewGroup, int i) {
        if (VERSION.SDK_INT > 19) {
            C2600x.m6686a(viewGroup, new AutoTransition(), i);
        }
    }

    /* renamed from: a */
    public static void m6685a(ViewGroup viewGroup, Transition transition) {
        if (VERSION.SDK_INT > 19) {
            C2600x.m6686a(viewGroup, transition, 200);
        }
    }

    @TargetApi(19)
    /* renamed from: a */
    private static void m6686a(ViewGroup viewGroup, Transition transition, int i) {
        transition.setDuration((long) i);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());
        TransitionManager.beginDelayedTransition(viewGroup, transition);
    }

    /* renamed from: a */
    public static void m6687a(TextView textView, boolean z, int i) {
        Typeface create = z ? VERSION.SDK_INT >= 21 ? Typeface.create("sans-serif-medium", 0) : Typeface.create(Typeface.SANS_SERIF, 1) : Typeface.create(Typeface.SANS_SERIF, 0);
        textView.setTypeface(create);
        textView.setTextSize(2, (float) i);
    }

    /* renamed from: a */
    public static void m6688a(View... viewArr) {
        for (View b : viewArr) {
            C2600x.m6689b(b);
        }
    }

    /* renamed from: b */
    public static void m6689b(View view) {
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }

    /* renamed from: c */
    public static void m6690c(View view) {
        ViewParent parent = view.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            C2600x.m6683a((ViewGroup) parent);
        }
    }
}
