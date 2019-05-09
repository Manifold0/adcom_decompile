// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.a;

import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.s.c;
import android.content.Context;
import com.facebook.ads.internal.adapters.p;
import android.support.annotation.Nullable;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.internal.w.b.x;

public class d
{
    private static final int a;
    private static final int b;
    private static final int c;
    
    static {
        a = (int)(x.b * 200.0f);
        b = (int)(x.b * 200.0f);
        c = (int)(50.0f * x.b);
    }
    
    public static p.b a(@Nullable final NativeAdLayout nativeAdLayout) {
        if (nativeAdLayout == null) {
            return p.b.c;
        }
        final int width = nativeAdLayout.getWidth();
        final int height = nativeAdLayout.getHeight();
        int n;
        if ((width < d.a || height < d.a) && (width < d.b || height < d.c)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            return p.b.b;
        }
        return p.b.a;
    }
    
    @Nullable
    public static com.facebook.ads.internal.view.a.c a(final Context context, final c c, final String s, @Nullable final NativeAdLayout nativeAdLayout) {
        if (nativeAdLayout != null) {
            final int width = nativeAdLayout.getWidth();
            final int height = nativeAdLayout.getHeight();
            if (width >= d.a && height >= d.a) {
                return new k(context, c, s, width, height);
            }
            if (width >= d.b && height >= d.c) {
                return new h(context, c, s, width, height);
            }
        }
        return null;
    }
    
    public static com.facebook.ads.internal.view.a.c a(final Context context, final c c, final String s, final a a, final a.a a2) {
        return new g(context, c, s, a, a2);
    }
}
