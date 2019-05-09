package com.tapjoy.internal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class bs implements bp, bu {
    /* renamed from: a */
    private HashMap f7234a;

    /* renamed from: com.tapjoy.internal.bs$a */
    public static abstract class C2846a {
        /* renamed from: a */
        private static C2846a f7233a;

        /* renamed from: a */
        public static C2846a m7224a() {
            C2846a c2846a = f7233a;
            if (c2846a != null) {
                return c2846a;
            }
            c2846a = bt.f7236a;
            f7233a = c2846a;
            return c2846a;
        }

        /* renamed from: a */
        public final bs m7225a(InputStream inputStream) {
            return mo6188a(new InputStreamReader(inputStream, cp.f7292c));
        }

        /* renamed from: a */
        public bs mo6188a(Reader reader) {
            return mo6189a(db.m7356a(reader));
        }

        /* renamed from: a */
        public bs mo6189a(String str) {
            return m7225a(new ByteArrayInputStream(str.getBytes(cp.f7292c.name())));
        }
    }

    /* renamed from: a */
    public static bs m7242a(InputStream inputStream) {
        return C2846a.m7224a().m7225a(inputStream);
    }

    /* renamed from: b */
    public static bs m7244b(String str) {
        return C2846a.m7224a().mo6189a(str);
    }

    /* renamed from: a */
    public final Object mo6186a(String str) {
        return this.f7234a != null ? this.f7234a.get(str) : null;
    }

    /* renamed from: a */
    public final void mo6187a(String str, Object obj) {
        if (this.f7234a == null) {
            this.f7234a = new HashMap();
        }
        this.f7234a.put(str, obj);
    }

    /* renamed from: a */
    public final boolean m7253a() {
        return mo6196k() == bx.BEGIN_OBJECT;
    }

    /* renamed from: t */
    private boolean m7246t() {
        if (mo6196k() != bx.NULL) {
            return false;
        }
        mo6200o();
        return true;
    }

    /* renamed from: b */
    public final String m7254b() {
        if (m7246t()) {
            return null;
        }
        return mo6198m();
    }

    /* renamed from: c */
    public final String m7255c(String str) {
        return m7246t() ? str : mo6198m();
    }

    /* renamed from: u */
    private Object m7247u() {
        bx k = mo6196k();
        switch (k) {
            case BEGIN_ARRAY:
                return m7256c();
            case BEGIN_OBJECT:
                return m7257d();
            case NULL:
                mo6200o();
                return null;
            case BOOLEAN:
                return Boolean.valueOf(mo6199n());
            case NUMBER:
                return new cn(mo6198m());
            case STRING:
                return mo6198m();
            default:
                throw new IllegalStateException("Expected a value but was " + k);
        }
    }

    /* renamed from: c */
    public final List m7256c() {
        List linkedList = new LinkedList();
        m7243a(linkedList);
        return linkedList;
    }

    /* renamed from: a */
    private void m7243a(List list) {
        mo6191f();
        while (mo6195j()) {
            list.add(m7247u());
        }
        mo6192g();
    }

    /* renamed from: d */
    public final Map m7257d() {
        Map linkedHashMap = new LinkedHashMap();
        m7252a(linkedHashMap);
        return linkedHashMap;
    }

    /* renamed from: a */
    public final void m7252a(Map map) {
        mo6193h();
        while (mo6195j()) {
            map.put(mo6197l(), m7247u());
        }
        mo6194i();
    }

    /* renamed from: a */
    public final Object m7248a(bn bnVar) {
        if (m7246t()) {
            return null;
        }
        return bnVar.mo6185a(this);
    }

    /* renamed from: a */
    public final void m7251a(List list, bn bnVar) {
        mo6191f();
        while (mo6195j()) {
            list.add(bnVar.mo6185a(this));
        }
        mo6192g();
    }

    /* renamed from: d */
    private static URI m7245d(String str) {
        try {
            return new URI(str);
        } catch (Throwable e) {
            throw new ca(e);
        }
    }

    /* renamed from: e */
    public final URL m7258e() {
        URI uri = (URI) mo6186a("BASE_URI");
        if (uri != null) {
            return uri.resolve(m7245d(mo6198m())).toURL();
        }
        return new URL(mo6198m());
    }
}
