// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.e;

import android.text.TextUtils;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.l.b;
import com.facebook.ads.internal.v.a.a;
import android.content.Context;
import java.util.HashSet;
import java.util.Set;
import android.support.annotation.AnyThread;

@AnyThread
public class d
{
    private static String a;
    private static final Set<String> b;
    private static final Set<String> c;
    
    static {
        d.a = null;
        b = new HashSet<String>(1);
        c = new HashSet<String>(2);
        d.b.add("1ww8E0AYsR2oX5lndk2hwp2Uosk=\n");
        d.c.add("toZ2GRnRjC9P5VVUdCpOrFH8lfQ=\n");
        d.c.add("3lKvjNsfmrn+WmfDhvr2iVh/yRs=\n");
        d.c.add("B08QtE4yLCdli4rptyqAEczXOeA=\n");
        d.c.add("XZXI6anZbdKf+taURdnyUH5ipgM=\n");
    }
    
    public static a a(final Context context) {
        final a a = new a();
        a(context, a, true);
        return a;
    }
    
    public static a a(final Context context, final boolean b) {
        final a a = new a();
        a(context, a, b);
        if (!a()) {
            a.b(d.c);
            a.a(d.b);
        }
        return a;
    }
    
    private static void a(final Context context, final a a, final boolean b) {
        if (a()) {
            a.c(360000);
            a.d(120000);
        }
        else {
            a.c(30000);
        }
        a.b(3);
        a.a("user-agent", com.facebook.ads.internal.n.d.a(new b(context), context, b));
    }
    
    public static boolean a() {
        final String urlPrefix = AdInternalSettings.getUrlPrefix();
        return !TextUtils.isEmpty((CharSequence)urlPrefix) && urlPrefix.endsWith(".sb");
    }
    
    public static a b(final Context context) {
        return a(context, true);
    }
}
