package com.tapjoy.internal;

import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public abstract class cf {
    /* renamed from: a */
    public static ExecutorService f7280a;
    /* renamed from: b */
    public static ci f7281b;
    /* renamed from: c */
    private Future f7282c;

    /* renamed from: a */
    public abstract Object mo6205a(URI uri, InputStream inputStream);

    /* renamed from: b */
    public abstract String mo6334b();

    /* renamed from: c */
    public abstract String mo6338c();

    /* renamed from: a */
    public Map mo6206a() {
        return Collections.emptyMap();
    }

    /* renamed from: d */
    public String mo6335d() {
        return null;
    }

    /* renamed from: e */
    public Map mo6336e() {
        return new LinkedHashMap();
    }

    /* renamed from: a */
    public final synchronized void m7319a(ck ckVar, ExecutorService executorService) {
        Object obj;
        if (this.f7282c == null || this.f7282c.isDone()) {
            obj = 1;
        } else {
            obj = null;
        }
        String str = "Call has not completed";
        if (obj == null) {
            throw new IllegalStateException(String.valueOf(str));
        }
        this.f7282c = executorService.submit(new ch(this, ckVar));
    }

    /* renamed from: f */
    public Object mo6337f() {
        return f7281b.mo6207a(this);
    }
}
