// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;

public final class hs extends an
{
    private final gx a;
    private final ht b;
    private af c;
    
    public hs(final Context context, final gx a, final ht b) {
        super(context);
        this.a = a;
        this.addView((View)(this.b = b), new ViewGroup$LayoutParams(-1, -1));
        this.c = null;
    }
    
    protected final void onMeasure(final int n, final int n2) {
        final af a = af.a(this.getContext());
        af c;
        if (this.a.a()) {
            if (this.a.b()) {
                if (a.a()) {
                    c = af.b;
                }
                else if (!a.b() && af.b(this.getContext()).a()) {
                    c = af.b;
                }
                else {
                    c = af.c;
                }
                this.setRotationCount(0);
            }
            else {
                c = af.b;
                if (a.b()) {
                    if (a.c() == 3) {
                        this.setRotationCount(1);
                    }
                    else {
                        this.setRotationCount(3);
                    }
                }
                else {
                    this.setRotationCount(0);
                }
            }
        }
        else {
            c = af.c;
            if (a.a()) {
                if (a.c() == 3) {
                    this.setRotationCount(1);
                }
                else {
                    this.setRotationCount(1);
                }
            }
            else {
                this.setRotationCount(0);
            }
        }
        if (this.c != c) {
            this.c = c;
            this.b.setLandscape(this.c.b());
        }
        super.onMeasure(n, n2);
    }
}
