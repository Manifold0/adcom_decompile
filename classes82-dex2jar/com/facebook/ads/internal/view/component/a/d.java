// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.a.j;
import java.util.Map;
import java.util.HashMap;
import android.net.Uri;
import com.facebook.ads.internal.adapters.b.l;

public final class d
{
    public static c a(final e e) {
        h h;
        if (e.k() == 1) {
            h = e.g().b().a();
        }
        else {
            h = e.g().b().b();
        }
        final l l = e.g().d().get(0);
        final double n = a.a(l);
        final boolean d = e.g().d().get(0).d();
        final boolean a = com.facebook.ads.internal.view.component.a.a.a(e.k(), e.j(), n);
        final com.facebook.ads.internal.a.b a2 = com.facebook.ads.internal.a.c.a(e.a(), e.b(), "", Uri.parse(e.g().d().get(0).b().a()), new HashMap<String, String>());
        c c;
        if (d && a2 != null && a2 instanceof j) {
            if (e.k() == 1) {
                c = new com.facebook.ads.internal.view.component.a.j(e, h);
            }
            else {
                c = new i(e, h);
            }
        }
        else if (a) {
            c = new b(e, h, e.k() == 2);
        }
        else {
            c = new f(e, com.facebook.ads.internal.view.component.a.a.a(n), h);
        }
        c.a(l, e.g().c(), n);
        return c;
    }
}
