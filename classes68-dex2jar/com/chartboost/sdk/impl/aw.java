// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.a;
import android.view.animation.TranslateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.ViewTreeObserver;
import android.view.View;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.d;
import com.chartboost.sdk.Model.c;
import android.os.Handler;

public class aw
{
    private final Handler a;
    
    public aw(final Handler a) {
        this.a = a;
    }
    
    public static Integer a(final int n) {
        if (n >= 1 && n <= 9) {
            return n;
        }
        return null;
    }
    
    private void a(final int n, final c c, final Runnable runnable, final boolean b, final d d) {
        if (n == 7) {
            if (runnable != null) {
                runnable.run();
            }
        }
        else {
            if (c == null || c.s == null) {
                CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of container");
                return;
            }
            final View d2 = c.s.d();
            if (d2 == null) {
                d.d(c);
                CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of view");
                return;
            }
            final ViewTreeObserver viewTreeObserver = d2.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        d2.getViewTreeObserver().removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                        aw.this.a(n, c, runnable, b);
                    }
                });
            }
        }
    }
    
    public void a(final int n, final c c, final Runnable runnable) {
        this.a(n, c, runnable, false);
    }
    
    public void a(final int n, final c c, final Runnable runnable, final d d) {
        this.a(n, c, runnable, true, d);
    }
    
    void a(final int n, final c c, final Runnable runnable, final boolean b) {
        final AnimationSet set = new AnimationSet(true);
        set.addAnimation((Animation)new AlphaAnimation(1.0f, 1.0f));
        if (c == null || c.s == null) {
            CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of container");
            if (runnable != null) {
                runnable.run();
            }
        }
        else {
            Object o = c.s.d();
            if (o == null) {
                if (runnable != null) {
                    runnable.run();
                }
                CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of view");
                return;
            }
            if (c.n == 2 || c.n == 1) {
                o = c.s;
            }
            float n2 = (float)((View)o).getWidth();
            float n3 = (float)((View)o).getHeight();
            final float n4 = (1.0f - 0.4f) / 2.0f;
            if (c.p.b == 1) {}
            Object o2 = null;
            switch (n) {
                default: {
                    o2 = set;
                    break;
                }
                case 6: {
                    AlphaAnimation alphaAnimation;
                    if (b) {
                        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                    }
                    else {
                        alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                    }
                    ((Animation)alphaAnimation).setDuration(500L);
                    ((Animation)alphaAnimation).setFillAfter(true);
                    final AnimationSet set2 = new AnimationSet(true);
                    set2.addAnimation((Animation)alphaAnimation);
                    o2 = set2;
                    break;
                }
                case 3: {
                    ba ba;
                    if (b) {
                        ba = new ba(-60.0f, 0.0f, n2 / 2.0f, n3 / 2.0f, false);
                    }
                    else {
                        ba = new ba(0.0f, 60.0f, n2 / 2.0f, n3 / 2.0f, false);
                    }
                    ba.setDuration(500L);
                    ba.setFillAfter(true);
                    set.addAnimation((Animation)ba);
                    ScaleAnimation scaleAnimation;
                    if (b) {
                        scaleAnimation = new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f);
                    }
                    else {
                        scaleAnimation = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                    }
                    ((Animation)scaleAnimation).setDuration(500L);
                    ((Animation)scaleAnimation).setFillAfter(true);
                    set.addAnimation((Animation)scaleAnimation);
                    TranslateAnimation translateAnimation;
                    if (b) {
                        translateAnimation = new TranslateAnimation(n2 * n4, 0.0f, -n3 * 0.4f, 0.0f);
                    }
                    else {
                        translateAnimation = new TranslateAnimation(0.0f, n2 * n4, 0.0f, n3);
                    }
                    ((Animation)translateAnimation).setDuration(500L);
                    ((Animation)translateAnimation).setFillAfter(true);
                    set.addAnimation((Animation)translateAnimation);
                    o2 = set;
                    break;
                }
                case 1: {
                    ba ba2;
                    if (b) {
                        ba2 = new ba(-60.0f, 0.0f, n2 / 2.0f, n3 / 2.0f, true);
                    }
                    else {
                        ba2 = new ba(0.0f, 60.0f, n2 / 2.0f, n3 / 2.0f, true);
                    }
                    ba2.setDuration(500L);
                    ba2.setFillAfter(true);
                    set.addAnimation((Animation)ba2);
                    ScaleAnimation scaleAnimation2;
                    if (b) {
                        scaleAnimation2 = new ScaleAnimation(0.4f, 1.0f, 0.4f, 1.0f);
                    }
                    else {
                        scaleAnimation2 = new ScaleAnimation(1.0f, 0.4f, 1.0f, 0.4f);
                    }
                    ((Animation)scaleAnimation2).setDuration(500L);
                    ((Animation)scaleAnimation2).setFillAfter(true);
                    set.addAnimation((Animation)scaleAnimation2);
                    TranslateAnimation translateAnimation2;
                    if (b) {
                        translateAnimation2 = new TranslateAnimation(-n2 * 0.4f, 0.0f, n3 * n4, 0.0f);
                    }
                    else {
                        translateAnimation2 = new TranslateAnimation(0.0f, n2, 0.0f, n3 * n4);
                    }
                    ((Animation)translateAnimation2).setDuration(500L);
                    ((Animation)translateAnimation2).setFillAfter(true);
                    set.addAnimation((Animation)translateAnimation2);
                    o2 = set;
                    break;
                }
                case 5: {
                    float n5;
                    if (b) {
                        n5 = n3;
                    }
                    else {
                        n5 = 0.0f;
                    }
                    if (b) {
                        n3 = 0.0f;
                    }
                    final TranslateAnimation translateAnimation3 = new TranslateAnimation(0.0f, 0.0f, n5, n3);
                    ((Animation)translateAnimation3).setDuration(500L);
                    ((Animation)translateAnimation3).setFillAfter(true);
                    set.addAnimation((Animation)translateAnimation3);
                    o2 = set;
                    break;
                }
                case 4: {
                    float n6;
                    if (b) {
                        n6 = -n3;
                    }
                    else {
                        n6 = 0.0f;
                    }
                    float n7;
                    if (b) {
                        n7 = 0.0f;
                    }
                    else {
                        n7 = -n3;
                    }
                    final TranslateAnimation translateAnimation4 = new TranslateAnimation(0.0f, 0.0f, n6, n7);
                    ((Animation)translateAnimation4).setDuration(500L);
                    ((Animation)translateAnimation4).setFillAfter(true);
                    set.addAnimation((Animation)translateAnimation4);
                    o2 = set;
                    break;
                }
                case 8: {
                    float n8;
                    if (b) {
                        n8 = n2;
                    }
                    else {
                        n8 = 0.0f;
                    }
                    if (b) {
                        n2 = 0.0f;
                    }
                    final TranslateAnimation translateAnimation5 = new TranslateAnimation(n8, n2, 0.0f, 0.0f);
                    ((Animation)translateAnimation5).setDuration(500L);
                    ((Animation)translateAnimation5).setFillAfter(true);
                    set.addAnimation((Animation)translateAnimation5);
                    o2 = set;
                    break;
                }
                case 9: {
                    float n9;
                    if (b) {
                        n9 = -n2;
                    }
                    else {
                        n9 = 0.0f;
                    }
                    float n10;
                    if (b) {
                        n10 = 0.0f;
                    }
                    else {
                        n10 = -n2;
                    }
                    final TranslateAnimation translateAnimation6 = new TranslateAnimation(n9, n10, 0.0f, 0.0f);
                    ((Animation)translateAnimation6).setDuration(500L);
                    ((Animation)translateAnimation6).setFillAfter(true);
                    set.addAnimation((Animation)translateAnimation6);
                    o2 = set;
                    break;
                }
                case 2: {
                    if (b) {
                        final ScaleAnimation scaleAnimation3 = new ScaleAnimation(0.6f, 1.1f, 0.6f, 1.1f, 1, 0.5f, 1, 0.5f);
                        ((Animation)scaleAnimation3).setDuration((long)Math.round(500L * 0.6f));
                        ((Animation)scaleAnimation3).setStartOffset(0L);
                        ((Animation)scaleAnimation3).setFillAfter(true);
                        set.addAnimation((Animation)scaleAnimation3);
                        final ScaleAnimation scaleAnimation4 = new ScaleAnimation(1.0f, 0.81818175f, 1.0f, 0.81818175f, 1, 0.5f, 1, 0.5f);
                        ((Animation)scaleAnimation4).setDuration((long)Math.round(500L * 0.19999999f));
                        ((Animation)scaleAnimation4).setStartOffset((long)Math.round(500L * 0.6f));
                        ((Animation)scaleAnimation4).setFillAfter(true);
                        set.addAnimation((Animation)scaleAnimation4);
                        final ScaleAnimation scaleAnimation5 = new ScaleAnimation(1.0f, 1.1111112f, 1.0f, 1.1111112f, 1, 0.5f, 1, 0.5f);
                        ((Animation)scaleAnimation5).setDuration((long)Math.round(500L * 0.099999964f));
                        ((Animation)scaleAnimation5).setStartOffset((long)Math.round(500L * 0.8f));
                        ((Animation)scaleAnimation5).setFillAfter(true);
                        set.addAnimation((Animation)scaleAnimation5);
                        o2 = set;
                        break;
                    }
                    final ScaleAnimation scaleAnimation6 = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
                    ((Animation)scaleAnimation6).setDuration(500L);
                    ((Animation)scaleAnimation6).setStartOffset(0L);
                    ((Animation)scaleAnimation6).setFillAfter(true);
                    set.addAnimation((Animation)scaleAnimation6);
                    o2 = set;
                    break;
                }
            }
            if (n != 7) {
                if (runnable != null) {
                    this.a.postDelayed(runnable, 500L);
                }
                ((View)o).startAnimation((Animation)o2);
                return;
            }
            if (runnable != null) {
                runnable.run();
            }
        }
    }
    
    public void a(final boolean b, final View view, final long duration) {
        float n = 1.0f;
        view.clearAnimation();
        if (b) {
            view.setVisibility(0);
        }
        float n2;
        if (b) {
            n2 = 0.0f;
        }
        else {
            n2 = 1.0f;
        }
        if (!b) {
            n = 0.0f;
        }
        final AlphaAnimation alphaAnimation = new AlphaAnimation(n2, n);
        ((Animation)alphaAnimation).setDuration(duration);
        ((Animation)alphaAnimation).setFillBefore(true);
        view.startAnimation((Animation)alphaAnimation);
    }
    
    public void a(final boolean b, final View view, final a a) {
        if (a.b == 0) {}
        this.a(b, view, 500L);
    }
}
