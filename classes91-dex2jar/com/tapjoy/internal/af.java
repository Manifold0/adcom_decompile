// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.view.Display;
import android.os.Build$VERSION;
import android.graphics.Point;
import android.view.WindowManager;
import android.content.Context;

public enum af
{
    a("UNSPECIFIED", 0), 
    b("PORTRAIT", 1), 
    c("LANDSCAPE", 2), 
    d("SQUARE", 3), 
    e("NATURAL_PORTRAIT", 4, af.b), 
    f("RIGHT_LANDSCAPE", 5, af.c, af.e), 
    g("REVERSE_PORTRAIT", 6, af.b, af.e), 
    h("LEFT_LANDSCAPE", 7, af.c, af.e), 
    i("NATURAL_LANDSCAPE", 8, af.c), 
    j("RIGHT_PORTRAIT", 9, af.b, af.i), 
    k("REVERSE_LANDSCAPE", 10, af.c, af.i), 
    l("LEFT_PORTRAIT", 11, af.b, af.i), 
    m("NATURAL_SQUARE", 12, af.d), 
    n("RIGHT_SQUARE", 13, af.d, af.m), 
    o("REVERSE_SQUARE", 14, af.d, af.m), 
    p("LEFT_SQUARE", 15, af.d, af.m);
    
    private final af q;
    private final af r;
    
    private af(final String s, final int n) {
        this.q = this;
        this.r = null;
    }
    
    private af(final String s, final int n, final af q) {
        this.q = q;
        this.r = this;
    }
    
    private af(final String s, final int n, final af q, final af r) {
        this.q = q;
        this.r = r;
    }
    
    public static af a(final Context context) {
        final Display defaultDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        final int rotation = defaultDisplay.getRotation();
        final Point point = new Point();
        if (Build$VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(point);
        }
        else {
            point.x = defaultDisplay.getWidth();
            point.y = defaultDisplay.getHeight();
        }
        if (point.x < point.y) {
            switch (rotation & 0x3) {
                default: {
                    return af.e;
                }
                case 1: {
                    return af.j;
                }
                case 2: {
                    return af.g;
                }
                case 3: {
                    return af.l;
                }
            }
        }
        else if (point.x > point.y) {
            switch (rotation & 0x3) {
                default: {
                    return af.i;
                }
                case 1: {
                    return af.f;
                }
                case 2: {
                    return af.k;
                }
                case 3: {
                    return af.h;
                }
            }
        }
        else {
            switch (rotation & 0x3) {
                default: {
                    return af.m;
                }
                case 1: {
                    return af.n;
                }
                case 2: {
                    return af.o;
                }
                case 3: {
                    return af.p;
                }
            }
        }
    }
    
    public static af b(final Context context) {
        switch (context.getResources().getConfiguration().orientation) {
            default: {
                return af.a;
            }
            case 1: {
                return af.b;
            }
            case 2: {
                return af.c;
            }
        }
    }
    
    public final boolean a() {
        return this == af.b || this.q == af.b;
    }
    
    public final boolean b() {
        return this == af.c || this.q == af.c;
    }
    
    public final int c() {
        if (this.r != null) {
            return this.ordinal() - this.r.ordinal();
        }
        return 0;
    }
}
