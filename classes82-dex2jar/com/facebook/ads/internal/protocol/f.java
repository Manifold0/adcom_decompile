// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.protocol;

import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View;
import android.util.DisplayMetrics;
import java.util.HashMap;
import java.util.Map;

public class f
{
    private static final Map<d, e> a;
    
    static {
        (a = new HashMap<d, e>()).put(d.e, e.e);
        f.a.put(d.d, e.d);
        f.a.put(d.c, e.c);
    }
    
    public static e a(final DisplayMetrics displayMetrics) {
        final int n = (int)(displayMetrics.widthPixels / displayMetrics.density);
        final int n2 = (int)(displayMetrics.heightPixels / displayMetrics.density);
        int n3;
        if (n >= 640 && n2 >= 640) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        if (n3 != 0) {
            return e.i;
        }
        if (n2 > n) {
            return e.h;
        }
        return e.g;
    }
    
    public static e a(final d d) {
        e b;
        if ((b = f.a.get(d)) == null) {
            b = e.b;
        }
        return b;
    }
    
    public static void a(final DisplayMetrics displayMetrics, final View view, final d d) {
        int widthPixels;
        if ((int)(displayMetrics.widthPixels / displayMetrics.density) >= d.a()) {
            widthPixels = displayMetrics.widthPixels;
        }
        else {
            widthPixels = (int)Math.ceil(d.a() * displayMetrics.density);
        }
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(widthPixels, (int)Math.ceil(d.b() * displayMetrics.density));
        layoutParams.addRule(14, -1);
        view.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
}
