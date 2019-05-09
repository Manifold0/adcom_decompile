package com.unity3d.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

/* renamed from: com.unity3d.player.l */
public final class C0253l extends View {
    /* renamed from: a */
    final int f206a;
    /* renamed from: b */
    final int f207b = getResources().getIdentifier("unity_static_splash", "drawable", getContext().getPackageName());
    /* renamed from: c */
    Bitmap f208c;
    /* renamed from: d */
    Bitmap f209d;

    /* renamed from: com.unity3d.player.l$1 */
    static /* synthetic */ class C02511 {
        /* renamed from: a */
        static final /* synthetic */ int[] f201a = new int[C0252a.m164a().length];

        static {
            try {
                f201a[C0252a.f202a - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f201a[C0252a.f203b - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f201a[C0252a.f204c - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.unity3d.player.l$a */
    enum C0252a {
        ;

        static {
            f202a = 1;
            f203b = 2;
            f204c = 3;
            f205d = new int[]{f202a, f203b, f204c};
        }

        /* renamed from: a */
        public static int[] m164a() {
            return (int[]) f205d.clone();
        }
    }

    public C0253l(Context context, int i) {
        super(context);
        this.f206a = i;
        if (this.f207b != 0) {
            forceLayout();
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f208c != null) {
            this.f208c.recycle();
            this.f208c = null;
        }
        if (this.f209d != null) {
            this.f209d.recycle();
            this.f209d = null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
        /*
        r10 = this;
        r1 = 1;
        r2 = 0;
        r0 = r10.f207b;
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = r10.f208c;
        if (r0 != 0) goto L_0x001e;
    L_0x000b:
        r0 = new android.graphics.BitmapFactory$Options;
        r0.<init>();
        r0.inScaled = r2;
        r3 = r10.getResources();
        r4 = r10.f207b;
        r0 = android.graphics.BitmapFactory.decodeResource(r3, r4, r0);
        r10.f208c = r0;
    L_0x001e:
        r0 = r10.f208c;
        r6 = r0.getWidth();
        r0 = r10.f208c;
        r4 = r0.getHeight();
        r5 = r10.getWidth();
        r3 = r10.getHeight();
        if (r5 == 0) goto L_0x0006;
    L_0x0034:
        if (r3 == 0) goto L_0x0006;
    L_0x0036:
        r0 = (float) r6;
        r7 = (float) r4;
        r7 = r0 / r7;
        r0 = (float) r5;
        r8 = (float) r3;
        r0 = r0 / r8;
        r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1));
        if (r0 > 0) goto L_0x00b0;
    L_0x0041:
        r0 = r1;
    L_0x0042:
        r8 = com.unity3d.player.C0253l.C02511.f201a;
        r9 = r10.f206a;
        r9 = r9 + -1;
        r8 = r8[r9];
        switch(r8) {
            case 1: goto L_0x00b2;
            case 2: goto L_0x00bf;
            case 3: goto L_0x00bf;
            default: goto L_0x004d;
        };
    L_0x004d:
        r0 = r4;
        r3 = r6;
    L_0x004f:
        r4 = r10.f209d;
        if (r4 == 0) goto L_0x0071;
    L_0x0053:
        r4 = r10.f209d;
        r4 = r4.getWidth();
        if (r4 != r3) goto L_0x0063;
    L_0x005b:
        r4 = r10.f209d;
        r4 = r4.getHeight();
        if (r4 == r0) goto L_0x0006;
    L_0x0063:
        r4 = r10.f209d;
        r5 = r10.f208c;
        if (r4 == r5) goto L_0x0071;
    L_0x0069:
        r4 = r10.f209d;
        r4.recycle();
        r4 = 0;
        r10.f209d = r4;
    L_0x0071:
        r4 = r10.f208c;
        r0 = android.graphics.Bitmap.createScaledBitmap(r4, r3, r0, r1);
        r10.f209d = r0;
        r0 = r10.f209d;
        r3 = r10.getResources();
        r3 = r3.getDisplayMetrics();
        r3 = r3.densityDpi;
        r0.setDensity(r3);
        r0 = new android.graphics.drawable.ColorDrawable;
        r3 = -16777216; // 0xffffffffff000000 float:-1.7014118E38 double:NaN;
        r0.<init>(r3);
        r3 = new android.graphics.drawable.BitmapDrawable;
        r4 = r10.getResources();
        r5 = r10.f209d;
        r3.<init>(r4, r5);
        r4 = 17;
        r3.setGravity(r4);
        r4 = new android.graphics.drawable.LayerDrawable;
        r5 = 2;
        r5 = new android.graphics.drawable.Drawable[r5];
        r5[r2] = r0;
        r5[r1] = r3;
        r4.<init>(r5);
        r10.setBackground(r4);
        goto L_0x0006;
    L_0x00b0:
        r0 = r2;
        goto L_0x0042;
    L_0x00b2:
        if (r5 >= r6) goto L_0x00d5;
    L_0x00b4:
        r0 = (float) r5;
        r0 = r0 / r7;
        r0 = (int) r0;
        r4 = r5;
    L_0x00b8:
        if (r3 >= r0) goto L_0x00d2;
    L_0x00ba:
        r0 = r3;
    L_0x00bb:
        r3 = (float) r0;
        r3 = r3 * r7;
        r3 = (int) r3;
        goto L_0x004f;
    L_0x00bf:
        r4 = r10.f206a;
        r6 = com.unity3d.player.C0253l.C0252a.f204c;
        if (r4 != r6) goto L_0x00ce;
    L_0x00c5:
        r4 = r1;
    L_0x00c6:
        r0 = r0 ^ r4;
        if (r0 == 0) goto L_0x00d0;
    L_0x00c9:
        r0 = (float) r5;
        r0 = r0 / r7;
        r0 = (int) r0;
        r3 = r5;
        goto L_0x004f;
    L_0x00ce:
        r4 = r2;
        goto L_0x00c6;
    L_0x00d0:
        r0 = r3;
        goto L_0x00bb;
    L_0x00d2:
        r3 = r4;
        goto L_0x004f;
    L_0x00d5:
        r0 = r4;
        r4 = r6;
        goto L_0x00b8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.l.onLayout(boolean, int, int, int, int):void");
    }
}
