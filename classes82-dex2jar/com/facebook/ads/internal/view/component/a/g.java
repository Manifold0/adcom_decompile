// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.a.i;
import java.util.Map;
import com.facebook.ads.internal.a.c;
import java.util.HashMap;
import android.net.Uri;

public class g
{
    public static l a(final e e, final int n, final int n2, final boolean b) {
        com.facebook.ads.internal.adapters.b.h h;
        if (e.k() == 1) {
            h = e.g().b().a();
        }
        else {
            h = e.g().b().b();
        }
        final com.facebook.ads.internal.adapters.b.l l = e.g().d().get(0);
        final b a = c.a(e.a(), e.b(), e.g().c(), Uri.parse(e.g().d().get(0).b().a()), new HashMap<String, String>(), false, true);
        if (e.g().d().get(0).d() && a instanceof i) {
            final h h2 = new h(e, h, n, n2, (i)a, b);
            h2.a(l);
            return h2;
        }
        return null;
    }
}
