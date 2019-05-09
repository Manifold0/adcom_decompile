package com.chartboost.sdk.impl;

import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.chartboost.sdk.C1399d;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.C1386a;
import com.chartboost.sdk.Model.C1388c;

public class aw {
    /* renamed from: a */
    private final Handler f3107a;

    /* renamed from: a */
    public static Integer m3462a(int i) {
        if (i < 1 || i > 9) {
            return null;
        }
        return Integer.valueOf(i);
    }

    public aw(Handler handler) {
        this.f3107a = handler;
    }

    /* renamed from: a */
    public void m3465a(int i, C1388c c1388c, Runnable runnable, C1399d c1399d) {
        m3463a(i, c1388c, runnable, true, c1399d);
    }

    /* renamed from: a */
    public void m3464a(int i, C1388c c1388c, Runnable runnable) {
        m3466a(i, c1388c, runnable, false);
    }

    /* renamed from: a */
    private void m3463a(int i, C1388c c1388c, Runnable runnable, boolean z, C1399d c1399d) {
        if (i == 7) {
            if (runnable != null) {
                runnable.run();
            }
        } else if (c1388c == null || c1388c.f2773s == null) {
            CBLogging.m3097a("AnimationManager", "Transition of impression canceled due to lack of container");
        } else {
            final View d = c1388c.f2773s.m3481d();
            if (d == null) {
                c1399d.m3286d(c1388c);
                CBLogging.m3097a("AnimationManager", "Transition of impression canceled due to lack of view");
                return;
            }
            ViewTreeObserver viewTreeObserver = d.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                final int i2 = i;
                final C1388c c1388c2 = c1388c;
                final Runnable runnable2 = runnable;
                final boolean z2 = z;
                viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
                    /* renamed from: f */
                    final /* synthetic */ aw f3106f;

                    public void onGlobalLayout() {
                        d.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        this.f3106f.m3466a(i2, c1388c2, runnable2, z2);
                    }
                });
            }
        }
    }

    /* renamed from: a */
    void m3466a(int i, C1388c c1388c, Runnable runnable, boolean z) {
        Animation animationSet = new AnimationSet(true);
        animationSet.addAnimation(new AlphaAnimation(1.0f, 1.0f));
        if (c1388c == null || c1388c.f2773s == null) {
            CBLogging.m3097a("AnimationManager", "Transition of impression canceled due to lack of container");
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        View d = c1388c.f2773s.m3481d();
        if (d == null) {
            if (runnable != null) {
                runnable.run();
            }
            CBLogging.m3097a("AnimationManager", "Transition of impression canceled due to lack of view");
            return;
        }
        View view;
        long j;
        Animation baVar;
        if (c1388c.f2768n == 2 || c1388c.f2768n == 1) {
            view = c1388c.f2773s;
        } else {
            view = d;
        }
        float width = (float) view.getWidth();
        float height = (float) view.getHeight();
        float f = (1.0f - 0.4f) / 2.0f;
        if (c1388c.f2770p.f2730b == 1) {
            j = 500;
        } else {
            j = 500;
        }
        Animation translateAnimation;
        float f2;
        float f3;
        switch (i) {
            case 1:
                if (z) {
                    baVar = new ba(-1114636288, 0.0f, width / 2.0f, height / 2.0f, true);
                } else {
                    baVar = new ba(0.0f, 60.0f, width / 2.0f, height / 2.0f, true);
                }
                baVar.setDuration(j);
                baVar.setFillAfter(true);
                animationSet.addAnimation(baVar);
                if (z) {
                    baVar = new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f);
                } else {
                    baVar = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                }
                baVar.setDuration(j);
                baVar.setFillAfter(true);
                animationSet.addAnimation(baVar);
                if (z) {
                    baVar = new TranslateAnimation((-width) * 0.4f, 0.0f, height * f, 0.0f);
                } else {
                    baVar = new TranslateAnimation(0.0f, width, 0.0f, height * f);
                }
                baVar.setDuration(j);
                baVar.setFillAfter(true);
                animationSet.addAnimation(baVar);
                baVar = animationSet;
                break;
            case 2:
                if (!z) {
                    baVar = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
                    baVar.setDuration(j);
                    baVar.setStartOffset(0);
                    baVar.setFillAfter(true);
                    animationSet.addAnimation(baVar);
                    baVar = animationSet;
                    break;
                }
                baVar = new ScaleAnimation(0.6f, 1.1f, 0.6f, 1.1f, 1, 0.5f, 1, 0.5f);
                baVar.setDuration((long) Math.round(((float) j) * 0.6f));
                baVar.setStartOffset(0);
                baVar.setFillAfter(true);
                animationSet.addAnimation(baVar);
                baVar = new ScaleAnimation(1.0f, 0.81818175f, 1.0f, 0.81818175f, 1, 0.5f, 1, 0.5f);
                baVar.setDuration((long) Math.round(((float) j) * 0.19999999f));
                baVar.setStartOffset((long) Math.round(((float) j) * 0.6f));
                baVar.setFillAfter(true);
                animationSet.addAnimation(baVar);
                baVar = new ScaleAnimation(1.0f, 1.1111112f, 1.0f, 1.1111112f, 1, 0.5f, 1, 0.5f);
                baVar.setDuration((long) Math.round(((float) j) * 0.099999964f));
                baVar.setStartOffset((long) Math.round(((float) j) * 0.8f));
                baVar.setFillAfter(true);
                animationSet.addAnimation(baVar);
                baVar = animationSet;
                break;
            case 3:
                if (z) {
                    baVar = new ba(-1114636288, 0.0f, width / 2.0f, height / 2.0f, false);
                } else {
                    baVar = new ba(0.0f, 60.0f, width / 2.0f, height / 2.0f, false);
                }
                baVar.setDuration(j);
                baVar.setFillAfter(true);
                animationSet.addAnimation(baVar);
                if (z) {
                    baVar = new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f);
                } else {
                    baVar = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                }
                baVar.setDuration(j);
                baVar.setFillAfter(true);
                animationSet.addAnimation(baVar);
                if (z) {
                    baVar = new TranslateAnimation(width * f, 0.0f, (-height) * 0.4f, 0.0f);
                } else {
                    baVar = new TranslateAnimation(0.0f, width * f, 0.0f, height);
                }
                baVar.setDuration(j);
                baVar.setFillAfter(true);
                animationSet.addAnimation(baVar);
                baVar = animationSet;
                break;
            case 4:
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, z ? -height : 0.0f, z ? 0.0f : -height);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                baVar = animationSet;
                break;
            case 5:
                f2 = z ? height : 0.0f;
                if (z) {
                    f3 = 0.0f;
                } else {
                    f3 = height;
                }
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, f2, f3);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                baVar = animationSet;
                break;
            case 6:
                if (z) {
                    baVar = new AlphaAnimation(0.0f, 1.0f);
                } else {
                    baVar = new AlphaAnimation(1.0f, 0.0f);
                }
                baVar.setDuration(j);
                baVar.setFillAfter(true);
                Animation animationSet2 = new AnimationSet(true);
                animationSet2.addAnimation(baVar);
                baVar = animationSet2;
                break;
            case 8:
                f2 = z ? width : 0.0f;
                if (z) {
                    f3 = 0.0f;
                } else {
                    f3 = width;
                }
                translateAnimation = new TranslateAnimation(f2, f3, 0.0f, 0.0f);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                baVar = animationSet;
                break;
            case 9:
                translateAnimation = new TranslateAnimation(z ? -width : 0.0f, z ? 0.0f : -width, 0.0f, 0.0f);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                baVar = animationSet;
                break;
            default:
                baVar = animationSet;
                break;
        }
        if (i != 7) {
            if (runnable != null) {
                this.f3107a.postDelayed(runnable, j);
            }
            view.startAnimation(baVar);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    /* renamed from: a */
    public void m3468a(boolean z, View view, C1386a c1386a) {
        if (c1386a.f2730b == 0) {
            m3467a(z, view, 500);
        } else {
            m3467a(z, view, 500);
        }
    }

    /* renamed from: a */
    public void m3467a(boolean z, View view, long j) {
        float f;
        float f2 = 1.0f;
        view.clearAnimation();
        if (z) {
            view.setVisibility(0);
        }
        if (z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        if (!z) {
            f2 = 0.0f;
        }
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setDuration(j);
        alphaAnimation.setFillBefore(true);
        view.startAnimation(alphaAnimation);
    }
}
