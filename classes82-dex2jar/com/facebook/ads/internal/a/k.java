// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.a;

import android.text.TextUtils;
import com.facebook.ads.internal.s.f;
import com.facebook.ads.internal.s.c;
import android.content.Context;
import java.util.Map;
import android.net.Uri;

class k extends b
{
    private static final String d;
    private final Uri e;
    private final Map<String, String> f;
    
    static {
        d = k.class.getSimpleName();
    }
    
    k(final Context context, final c c, final String s, final Uri e, final Map<String, String> f) {
        super(context, c, s);
        this.e = e;
        this.f = f;
    }
    
    @Override
    public void a() {
        final f a = com.facebook.ads.internal.s.f.a;
        final String queryParameter = this.e.getQueryParameter("priority");
        f f = a;
        while (true) {
            if (TextUtils.isEmpty((CharSequence)queryParameter)) {
                break Label_0035;
            }
            try {
                f = com.facebook.ads.internal.s.f.values()[Integer.valueOf(queryParameter)];
                this.b.a(this.c, this.f, this.e.getQueryParameter("type"), f);
            }
            catch (Exception ex) {
                f = a;
                continue;
            }
            break;
        }
    }
}
