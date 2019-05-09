package com.tapjoy.internal;

import android.view.animation.Animation;

public class ai {
    /* renamed from: a */
    protected final Animation f7173a;

    /* renamed from: com.tapjoy.internal.ai$1 */
    public static /* synthetic */ class C28371 {
        /* renamed from: a */
        public static final /* synthetic */ int[] f7167a = new int[C2838a.m7160a().length];

        static {
            try {
                f7167a[C2838a.f7168a - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f7167a[C2838a.f7169b - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f7167a[C2838a.f7170c - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f7167a[C2838a.f7171d - 1] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* renamed from: com.tapjoy.internal.ai$a */
    public enum C2838a {
        ;

        /* renamed from: a */
        public static int[] m7160a() {
            return (int[]) f7172e.clone();
        }

        static {
            f7168a = 1;
            f7169b = 2;
            f7170c = 3;
            f7171d = 4;
            f7172e = new int[]{f7168a, f7169b, f7170c, f7171d};
        }
    }

    public ai(Animation animation) {
        this.f7173a = animation;
        animation.setDuration(400);
    }

    /* renamed from: a */
    public Animation mo6175a() {
        return this.f7173a;
    }

    /* renamed from: b */
    public final ai m7162b() {
        this.f7173a.setDuration(600);
        return this;
    }
}
