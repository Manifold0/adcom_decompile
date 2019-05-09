// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.g;

import com.kongregate.android.internal.util.StringUtils;
import java.net.URI;
import org.json.JSONObject;
import com.kongregate.android.internal.util.p;
import com.kongregate.android.internal.util.j;

public class d
{
    public static c a(final String s, final String s2) {
        final long currentTimeMillis = System.currentTimeMillis();
        int n = 0;
        while (n < 10 && System.currentTimeMillis() - currentTimeMillis < 20000L) {
            j.a("Requesting pollForResult status(" + s + ") try #" + (n + 1));
            final c e = a.b().e(s);
            j.a("pollForResult - result: " + e.h());
            j.c("pollForResult: " + e.b());
            if (e.f()) {
                final JSONObject c = e.c();
                if (c != null) {
                    if (!c.optBoolean("success", false)) {
                        j.c("pollForResult - error");
                        return new c(p.e);
                    }
                    if (c.optString(s2, (String)null) != null) {
                        j.b("pollForResult - success!");
                        return e;
                    }
                }
            }
            else {
                j.b("pollForResult: " + e.h().toString());
            }
            try {
                j.c("pollForResult try again in: 2000");
                Thread.sleep(2000L);
                ++n;
                continue;
            }
            catch (InterruptedException ex) {
                j.c("pollForResult polling canceled");
                Thread.currentThread().interrupt();
                return new c(p.h);
            }
            break;
        }
        j.c("pollForResult - timeout");
        return new c(p.l);
    }
    
    public static URI a(final long n, final String s, final String s2, final boolean b) {
        final StringBuilder sb = new StringBuilder("/games_redirect/" + n);
        if (!StringUtils.a((CharSequence)s)) {
            sb.append("?path=").append(StringUtils.h(s));
            if (!StringUtils.a((CharSequence)s2)) {
                sb.append("&query=").append(StringUtils.h(s2));
            }
        }
        return a.a(sb.toString(), b);
    }
}
