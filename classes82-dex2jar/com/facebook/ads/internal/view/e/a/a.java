// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.e.a;

import android.support.v7.widget.RecyclerView;
import android.graphics.Rect;
import android.support.v7.widget.LinearSmoothScroller;
import android.view.View;
import java.util.HashSet;
import android.os.Bundle;
import com.facebook.ads.internal.view.d;
import android.support.annotation.Nullable;
import java.util.List;
import java.util.Set;
import android.support.v7.widget.RecyclerView$SmoothScroller;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView$OnScrollListener;

public class a extends RecyclerView$OnScrollListener
{
    private final LinearLayoutManager a;
    private final int b;
    private final RecyclerView$SmoothScroller c;
    private final Set<Integer> d;
    private List<b> e;
    private final com.facebook.ads.internal.x.a f;
    private boolean g;
    @Nullable
    private c.a h;
    private boolean i;
    private boolean j;
    private boolean k;
    private int l;
    private float m;
    private final com.facebook.ads.internal.view.component.a.a.b.e n;
    private final com.facebook.ads.internal.view.component.a.a.b.c o;
    private final com.facebook.ads.internal.view.component.a.a.b.d p;
    
    a(final d d, final int b, final List<b> e, final com.facebook.ads.internal.x.a f, @Nullable final Bundle bundle) {
        this.d = new HashSet<Integer>();
        this.g = true;
        this.i = true;
        this.j = true;
        this.l = -1;
        this.m = 0.0f;
        this.n = new com.facebook.ads.internal.view.component.a.a.b.e() {
            @Override
            public float a() {
                return com.facebook.ads.internal.view.e.a.a.this.m;
            }
            
            @Override
            public void a(final float n) {
                com.facebook.ads.internal.view.e.a.a.this.m = n;
            }
        };
        this.o = new com.facebook.ads.internal.view.component.a.a.b.c() {
            @Override
            public void a(final int n) {
                com.facebook.ads.internal.view.e.a.a.this.a(n, true);
                if (com.facebook.ads.internal.view.e.a.a.this.g()) {
                    com.facebook.ads.internal.view.e.a.a.c(com.facebook.ads.internal.view.e.a.a.this);
                    return;
                }
                com.facebook.ads.internal.view.e.a.a.a(com.facebook.ads.internal.view.e.a.a.this, n);
            }
        };
        this.p = new com.facebook.ads.internal.view.component.a.a.b.d() {
            @Override
            public void a(final View view) {
                final com.facebook.ads.internal.view.component.a.a.b b = (com.facebook.ads.internal.view.component.a.a.b)view;
                b.j();
                if (com.facebook.ads.internal.view.e.a.a.this.k) {
                    com.facebook.ads.internal.view.e.a.a.this.j = true;
                }
                if (com.facebook.ads.internal.view.e.a.a.this.f.b() && (int)b.getTag(-1593835536) == 0) {
                    com.facebook.ads.internal.view.e.a.a.this.f.a();
                }
            }
            
            @Override
            public void b(final View view) {
                if (com.facebook.ads.internal.view.e.a.a.this.k) {
                    com.facebook.ads.internal.view.e.a.a.this.j = false;
                }
            }
        };
        this.a = d.getLayoutManager();
        this.b = b;
        this.e = e;
        this.f = f;
        this.c = (RecyclerView$SmoothScroller)new LinearSmoothScroller(d.getContext());
        d.addOnScrollListener((RecyclerView$OnScrollListener)this);
        if (bundle == null) {
            return;
        }
        this.m = bundle.getFloat("VOLUME_LEVEL_PARAM", 0.0f);
        this.j = bundle.getBoolean("AUTO_PLAY_ENABLED_PARAM", true);
        this.g = bundle.getBoolean("IS_FIRST_VIDEO_PARAM", true);
    }
    
    @Nullable
    private com.facebook.ads.internal.view.component.a.a.b a(int n, final int n2, final boolean b) {
        com.facebook.ads.internal.view.component.a.a.b b2 = null;
        com.facebook.ads.internal.view.component.a.a.b b3;
        while (true) {
            b3 = b2;
            if (n > n2) {
                break;
            }
            final com.facebook.ads.internal.view.component.a.a.b b4 = (com.facebook.ads.internal.view.component.a.a.b)this.a.findViewByPosition(n);
            if (b4.g()) {
                b3 = null;
                break;
            }
            final boolean a = a((View)b4);
            com.facebook.ads.internal.view.component.a.a.b b5 = null;
            Label_0146: {
                if ((b5 = b2) == null) {
                    b5 = b2;
                    if (b4.f()) {
                        b5 = b2;
                        if (a) {
                            b5 = b2;
                            if (!this.d.contains(n)) {
                                if (b) {
                                    int n3;
                                    if ((int)(b4.getX() + b4.getWidth()) <= (int)(b4.getWidth() * 1.3f)) {
                                        n3 = 1;
                                    }
                                    else {
                                        n3 = 0;
                                    }
                                    b5 = b2;
                                    if (n3 == 0) {
                                        break Label_0146;
                                    }
                                }
                                b5 = b4;
                            }
                        }
                    }
                }
            }
            if (b4.f() && !a) {
                this.a(n, false);
            }
            ++n;
            b2 = b5;
        }
        return b3;
    }
    
    private void a(final int targetPosition) {
        this.c.setTargetPosition(targetPosition);
        this.a.startSmoothScroll(this.c);
    }
    
    private void a(final int n, final boolean b) {
        if (b) {
            this.d.add(n);
            return;
        }
        this.d.remove(n);
    }
    
    private void a(final com.facebook.ads.internal.view.component.a.a.b b, final boolean b2) {
        if (this.g()) {
            float alpha;
            if (b2) {
                alpha = 1.0f;
            }
            else {
                alpha = 0.5f;
            }
            ((View)b).setAlpha(alpha);
        }
        if (!b2 && b.g()) {
            b.i();
        }
    }
    
    static /* synthetic */ void a(final a a, final int n) {
        final com.facebook.ads.internal.view.component.a.a.b a2 = a.a(n + 1, a.a.findLastVisibleItemPosition(), false);
        if (a2 != null) {
            a2.h();
            a.a((int)a2.getTag(-1593835536));
        }
    }
    
    private static boolean a(final View view) {
        final Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect.width() / (float)view.getWidth() >= 0.15f;
    }
    
    private void b(final int n) {
        final com.facebook.ads.internal.view.component.a.a.b b = (com.facebook.ads.internal.view.component.a.a.b)this.a.findViewByPosition(n);
        if (!a((View)b)) {
            this.a(b, false);
        }
    }
    
    static /* synthetic */ void c(final a a) {
        final int firstCompletelyVisibleItemPosition = a.a.findFirstCompletelyVisibleItemPosition();
        if (firstCompletelyVisibleItemPosition != -1 && firstCompletelyVisibleItemPosition < a.e.size() - 1) {
            a.a(firstCompletelyVisibleItemPosition + 1);
        }
    }
    
    private void f() {
        if (this.j) {
            final com.facebook.ads.internal.view.component.a.a.b a = this.a(this.a.findFirstVisibleItemPosition(), this.a.findLastVisibleItemPosition(), true);
            if (a != null) {
                a.h();
            }
        }
    }
    
    private boolean g() {
        return this.b == 1;
    }
    
    public void a() {
        this.l = -1;
        for (int firstVisibleItemPosition = this.a.findFirstVisibleItemPosition(); firstVisibleItemPosition <= this.a.findLastVisibleItemPosition() && firstVisibleItemPosition >= 0; ++firstVisibleItemPosition) {
            final com.facebook.ads.internal.view.component.a.a.b b = (com.facebook.ads.internal.view.component.a.a.b)this.a.findViewByPosition(firstVisibleItemPosition);
            if (b != null && b.g()) {
                this.l = firstVisibleItemPosition;
                b.i();
                break;
            }
        }
    }
    
    void a(final Bundle bundle) {
        bundle.putFloat("VOLUME_LEVEL_PARAM", this.m);
        bundle.putBoolean("AUTO_PLAY_ENABLED_PARAM", this.j);
        bundle.putBoolean("IS_FIRST_VIDEO_PARAM", this.g);
    }
    
    void a(final c.a h) {
        this.h = h;
    }
    
    public void b() {
        final com.facebook.ads.internal.view.component.a.a.b b = (com.facebook.ads.internal.view.component.a.a.b)this.a.findViewByPosition(this.l);
        if (this.l >= 0) {
            b.h();
        }
    }
    
    public com.facebook.ads.internal.view.component.a.a.b.e c() {
        return this.n;
    }
    
    public com.facebook.ads.internal.view.component.a.a.b.c d() {
        return this.o;
    }
    
    public com.facebook.ads.internal.view.component.a.a.b.d e() {
        return this.p;
    }
    
    public void onScrollStateChanged(final RecyclerView recyclerView, final int n) {
        super.onScrollStateChanged(recyclerView, n);
        if (n == 0) {
            this.k = true;
            this.f();
        }
    }
    
    public void onScrolled(final RecyclerView recyclerView, int n, int firstVisibleItemPosition) {
        super.onScrolled(recyclerView, n, firstVisibleItemPosition);
        this.k = false;
        if (this.i) {
            this.k = true;
            this.f();
            this.i = false;
        }
        firstVisibleItemPosition = this.a.findFirstVisibleItemPosition();
        final int lastVisibleItemPosition = this.a.findLastVisibleItemPosition();
        this.b(firstVisibleItemPosition);
        this.b(lastVisibleItemPosition);
        for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; ++i) {
            final com.facebook.ads.internal.view.component.a.a.b b = (com.facebook.ads.internal.view.component.a.a.b)this.a.findViewByPosition(i);
            if (a((View)b)) {
                this.a(b, true);
            }
            int n2;
            if (this.g && b.f()) {
                this.g = false;
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            if (n2 != 0) {
                final b b2 = this.e.get((int)b.getTag(-1593835536));
                final com.facebook.ads.internal.view.component.a.a.b.e n3 = this.n;
                float n4;
                if (b2.c().c().f()) {
                    n4 = 0.0f;
                }
                else {
                    n4 = 1.0f;
                }
                n3.a(n4);
            }
        }
        if (!this.g() || this.h == null) {
            return;
        }
        final int firstCompletelyVisibleItemPosition = this.a.findFirstCompletelyVisibleItemPosition();
        if (firstCompletelyVisibleItemPosition != -1) {
            n = firstCompletelyVisibleItemPosition;
        }
        else if (n < 0) {
            n = firstVisibleItemPosition;
        }
        else {
            n = lastVisibleItemPosition;
        }
        this.h.a(n);
    }
}
