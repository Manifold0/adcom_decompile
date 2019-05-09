package com.tapjoy.internal;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;

public final class bm implements bq {
    /* renamed from: a */
    private final StringWriter f7226a = new StringWriter();
    /* renamed from: b */
    private final by f7227b = new by(this.f7226a);

    public final String toString() {
        try {
            this.f7227b.f7273a.flush();
            return this.f7226a.toString();
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: a */
    public final void mo6184a(Writer writer) {
        try {
            this.f7227b.f7273a.flush();
            writer.write(this.f7226a.toString());
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: a */
    public final bm m7206a() {
        try {
            this.f7227b.m7301a();
            return this;
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: b */
    public final bm m7214b() {
        try {
            this.f7227b.m7309b();
            return this;
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: c */
    public final bm m7216c() {
        try {
            this.f7227b.m7311c();
            return this;
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: d */
    public final bm m7217d() {
        try {
            this.f7227b.m7312d();
            return this;
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: a */
    public final bm m7210a(String str) {
        try {
            this.f7227b.m7306a(str);
            return this;
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: a */
    public final bm m7208a(bq bqVar) {
        try {
            this.f7227b.m7303a(bqVar);
            return this;
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: b */
    public final bm m7215b(String str) {
        try {
            this.f7227b.m7310b(str);
            return this;
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: a */
    public final bm m7207a(long j) {
        try {
            this.f7227b.m7302a(j);
            return this;
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: a */
    public final bm m7209a(Number number) {
        try {
            this.f7227b.m7304a(number);
            return this;
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: b */
    private bm m7205b(Object obj) {
        try {
            this.f7227b.m7305a(obj);
            return this;
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: a */
    public final bm m7211a(Collection collection) {
        try {
            this.f7227b.m7307a(collection);
            return this;
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: a */
    public final bm m7212a(Map map) {
        try {
            this.f7227b.m7308a(map);
            return this;
        } catch (Throwable e) {
            throw cu.m7340a(e);
        }
    }

    /* renamed from: a */
    public static String m7204a(Object obj) {
        return new bm().m7205b(obj).toString();
    }
}
