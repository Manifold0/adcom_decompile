// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import com.facebook.ads.internal.o.f;
import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.facebook.ads.internal.view.i.b.u;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.a;
import android.support.annotation.Nullable;
import android.view.View;
import android.os.Handler;
import com.facebook.ads.internal.view.i.b.v;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.b.j;
import android.annotation.TargetApi;
import com.facebook.ads.internal.view.i.a.b;

@TargetApi(12)
public class d implements b
{
    private final j a;
    private final l b;
    private final com.facebook.ads.internal.view.i.b.d c;
    private final v d;
    private final Handler e;
    private final boolean f;
    private final boolean g;
    private View h;
    @Nullable
    private a i;
    @Nullable
    private com.facebook.ads.internal.view.i.a j;
    private boolean k;
    
    public d(final View view, final a a) {
        this(view, a, false);
    }
    
    public d(final View view, final a a, final boolean b) {
        this(view, a, b, false);
    }
    
    public d(final View view, @Nullable final a a, final boolean f, final boolean g) {
        this.a = new j() {
            @Override
            public void a(final i i) {
                com.facebook.ads.internal.view.i.c.d.this.a(1, 0);
            }
        };
        this.b = new l() {
            @Override
            public void a(final k k) {
                if (!com.facebook.ads.internal.view.i.c.d.this.k) {
                    return;
                }
                if (com.facebook.ads.internal.view.i.c.d.this.i == com.facebook.ads.internal.view.i.c.d.a.c || com.facebook.ads.internal.view.i.c.d.this.f) {
                    com.facebook.ads.internal.view.i.c.d.this.i = null;
                    com.facebook.ads.internal.view.i.c.d.d(com.facebook.ads.internal.view.i.c.d.this);
                    return;
                }
                com.facebook.ads.internal.view.i.c.d.this.a(0, 8);
            }
        };
        this.c = new com.facebook.ads.internal.view.i.b.d() {
            @Override
            public void a(final c c) {
                if (d.this.i != com.facebook.ads.internal.view.i.c.d.a.b) {
                    d.this.h.setAlpha(1.0f);
                    d.this.h.setVisibility(0);
                }
            }
        };
        this.d = new v() {
            @Override
            public void a(final u u) {
                if (com.facebook.ads.internal.view.i.c.d.this.j != null && u.a().getAction() == 0) {
                    com.facebook.ads.internal.view.i.c.d.this.e.removeCallbacksAndMessages((Object)null);
                    com.facebook.ads.internal.view.i.c.d.this.a(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(final Animator animator) {
                            com.facebook.ads.internal.view.i.c.d.this.e.postDelayed((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    if (!com.facebook.ads.internal.view.i.c.d.this.g && com.facebook.ads.internal.view.i.c.d.this.k) {
                                        com.facebook.ads.internal.view.i.c.d.d(com.facebook.ads.internal.view.i.c.d.this);
                                    }
                                }
                            }, 2000L);
                        }
                    });
                }
            }
        };
        this.k = true;
        this.e = new Handler();
        this.f = f;
        this.g = g;
        this.a(view, a);
    }
    
    private void a(final int n, final int visibility) {
        this.e.removeCallbacksAndMessages((Object)null);
        this.h.clearAnimation();
        this.h.setAlpha((float)n);
        this.h.setVisibility(visibility);
    }
    
    private void a(final AnimatorListenerAdapter listener) {
        this.h.setVisibility(0);
        this.h.animate().alpha(1.0f).setDuration(500L).setListener((Animator$AnimatorListener)listener);
    }
    
    static /* synthetic */ void d(final d d) {
        d.h.animate().alpha(0.0f).setDuration(500L).setListener((Animator$AnimatorListener)new AnimatorListenerAdapter() {
            public void onAnimationEnd(final Animator animator) {
                com.facebook.ads.internal.view.i.c.d.this.h.setVisibility(8);
            }
        });
    }
    
    public void a(final View h, final a i) {
        this.i = i;
        (this.h = h).clearAnimation();
        if (i == com.facebook.ads.internal.view.i.c.d.a.b) {
            this.h.setAlpha(0.0f);
            this.h.setVisibility(8);
            return;
        }
        this.h.setAlpha(1.0f);
        this.h.setVisibility(0);
    }
    
    @Override
    public void a(final com.facebook.ads.internal.view.i.a j) {
        this.j = j;
        j.getEventBus().a(this.a, this.b, this.d, this.c);
    }
    
    public boolean a() {
        return this.k;
    }
    
    public void b() {
        this.k = false;
        this.a((AnimatorListenerAdapter)null);
    }
    
    @Override
    public void b(final com.facebook.ads.internal.view.i.a a) {
        this.a(1, 0);
        a.getEventBus().b(this.c, this.d, this.b, this.a);
        this.j = null;
    }
    
    public enum a
    {
        a, 
        b, 
        c;
    }
}
