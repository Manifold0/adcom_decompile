package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBUtility;

/* renamed from: com.chartboost.sdk.impl.z */
public abstract class C1456z extends RelativeLayout {
    /* renamed from: a */
    protected C1469v f3324a;
    /* renamed from: b */
    private aa f3325b;
    /* renamed from: c */
    private int f3326c = 1;

    /* renamed from: a */
    protected abstract View mo4316a();

    /* renamed from: b */
    protected abstract int mo4317b();

    public C1456z(Context context, C1469v c1469v) {
        super(context);
        this.f3324a = c1469v;
        m3637a(context);
    }

    /* renamed from: a */
    public void m3640a(int i) {
        this.f3326c = i;
        LayoutParams layoutParams = null;
        setClickable(false);
        int b = mo4317b();
        switch (this.f3326c) {
            case 0:
                layoutParams = new RelativeLayout.LayoutParams(-1, CBUtility.m3108a(b, getContext()));
                layoutParams.addRule(10);
                this.f3325b.m3338b(1);
                break;
            case 1:
                layoutParams = new RelativeLayout.LayoutParams(-1, CBUtility.m3108a(b, getContext()));
                layoutParams.addRule(12);
                this.f3325b.m3338b(4);
                break;
            case 2:
                layoutParams = new RelativeLayout.LayoutParams(CBUtility.m3108a(b, getContext()), -1);
                layoutParams.addRule(9);
                this.f3325b.m3338b(8);
                break;
            case 3:
                layoutParams = new RelativeLayout.LayoutParams(CBUtility.m3108a(b, getContext()), -1);
                layoutParams.addRule(11);
                this.f3325b.m3338b(2);
                break;
        }
        setLayoutParams(layoutParams);
    }

    /* renamed from: a */
    private void m3637a(Context context) {
        Context context2 = getContext();
        setGravity(17);
        this.f3325b = new aa(context2);
        this.f3325b.m3337a(-1);
        this.f3325b.setBackgroundColor(-855638017);
        addView(this.f3325b, new RelativeLayout.LayoutParams(-1, -1));
        addView(mo4316a(), new RelativeLayout.LayoutParams(-1, -1));
    }

    /* renamed from: a */
    public void m3641a(boolean z) {
        m3638a(z, 500);
    }

    /* renamed from: a */
    private void m3638a(final boolean z, long j) {
        this.f3324a.f3367C = z;
        if (!z || getVisibility() != 0) {
            if (z || getVisibility() != 8) {
                Animation translateAnimation;
                boolean z2;
                Runnable c14741 = new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ C1456z f3412b;

                    public void run() {
                        if (!z) {
                            this.f3412b.setVisibility(8);
                            this.f3412b.clearAnimation();
                        }
                        synchronized (this.f3412b.f3324a.g) {
                            this.f3412b.f3324a.g.remove(this.f3412b);
                        }
                    }
                };
                if (z) {
                    setVisibility(0);
                }
                float a = CBUtility.m3105a((float) mo4317b(), getContext());
                float f;
                switch (this.f3326c) {
                    case 0:
                        if (z) {
                            f = -a;
                        } else {
                            f = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(0.0f, 0.0f, f, z ? 0.0f : -a);
                        break;
                    case 1:
                        if (z) {
                            f = a;
                        } else {
                            f = 0.0f;
                        }
                        if (z) {
                            a = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(0.0f, 0.0f, f, a);
                        break;
                    case 2:
                        if (z) {
                            f = -a;
                        } else {
                            f = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(f, z ? 0.0f : -a, 0.0f, 0.0f);
                        break;
                    case 3:
                        f = z ? a : 0.0f;
                        if (z) {
                            a = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(f, a, 0.0f, 0.0f);
                        break;
                    default:
                        translateAnimation = null;
                        break;
                }
                translateAnimation.setDuration(j);
                if (z) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                translateAnimation.setFillAfter(z2);
                startAnimation(translateAnimation);
                synchronized (this.f3324a.g) {
                    this.f3324a.g.put(this, c14741);
                }
                this.f3324a.a.postDelayed(c14741, j);
            }
        }
    }
}
