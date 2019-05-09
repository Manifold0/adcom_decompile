// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.a;

import java.util.HashMap;
import android.text.TextUtils;
import java.util.Map;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.s.c;
import android.content.Context;

public abstract class h extends b
{
    protected final m d;
    
    public h(final Context context, final c c, final String s, @Nullable final m d) {
        super(context, c, s);
        this.d = d;
    }
    
    @Override
    public final void a() {
        if (this.d != null) {
            this.d.a(this.c);
        }
        this.e();
    }
    
    protected final void a(final Map<String, String> map, @Nullable final a a) {
        if (!TextUtils.isEmpty((CharSequence)this.c)) {
            if (this instanceof f) {
                this.b.h(this.c, map);
            }
            else {
                this.b.c(this.c, map);
            }
            final boolean a2 = a.a(a);
            if (this.d != null) {
                this.d.a(a);
                if (a2) {
                    this.d.a();
                }
            }
            else {
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("leave_time", Long.toString(-1L));
                hashMap.put("back_time", Long.toString(-1L));
                hashMap.put("outcome", a.b.name());
                this.b.m(this.c, hashMap);
            }
        }
        com.facebook.ads.internal.w.b.c.a(this.a, "Click logged");
    }
    
    abstract void e();
}
