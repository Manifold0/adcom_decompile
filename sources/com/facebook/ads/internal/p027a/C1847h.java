package com.facebook.ads.internal.p027a;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.p025w.p026b.C2567c;
import com.facebook.ads.internal.p051s.C2085c;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.a.h */
public abstract class C1847h extends C1842b {
    /* renamed from: d */
    protected final C1855m f3844d;

    public C1847h(Context context, C2085c c2085c, String str, @Nullable C1855m c1855m) {
        super(context, c2085c, str);
        this.f3844d = c1855m;
    }

    /* renamed from: a */
    public final void mo5376a() {
        if (this.f3844d != null) {
            this.f3844d.m4177a(this.c);
        }
        mo5378e();
    }

    /* renamed from: a */
    protected final void m4152a(Map<String, String> map, @Nullable C1841a c1841a) {
        if (!TextUtils.isEmpty(this.c)) {
            if (this instanceof C1848f) {
                this.b.mo5479h(this.c, map);
            } else {
                this.b.mo5474c(this.c, map);
            }
            boolean a = C1841a.m4139a(c1841a);
            if (this.f3844d != null) {
                this.f3844d.m4176a(c1841a);
                if (a) {
                    this.f3844d.m4175a();
                }
            } else {
                Map hashMap = new HashMap();
                hashMap.put("leave_time", Long.toString(-1));
                hashMap.put("back_time", Long.toString(-1));
                hashMap.put("outcome", C1841a.CANNOT_TRACK.name());
                this.b.mo5484m(this.c, hashMap);
            }
        }
        C2567c.m6621a(this.a, "Click logged");
    }

    /* renamed from: e */
    abstract void mo5378e();
}
