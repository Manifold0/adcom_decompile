package com.tapjoy.internal;

import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class by implements Closeable {
    /* renamed from: a */
    final Writer f7273a;
    /* renamed from: b */
    private final List f7274b = new ArrayList();
    /* renamed from: c */
    private String f7275c;
    /* renamed from: d */
    private String f7276d;
    /* renamed from: e */
    private boolean f7277e;

    public by(Writer writer) {
        this.f7274b.add(bv.EMPTY_DOCUMENT);
        this.f7276d = ":";
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.f7273a = writer;
    }

    /* renamed from: a */
    public final by m7301a() {
        return m7293a(bv.EMPTY_ARRAY, RequestParameters.LEFT_BRACKETS);
    }

    /* renamed from: b */
    public final by m7309b() {
        return m7292a(bv.EMPTY_ARRAY, bv.NONEMPTY_ARRAY, RequestParameters.RIGHT_BRACKETS);
    }

    /* renamed from: c */
    public final by m7311c() {
        return m7293a(bv.EMPTY_OBJECT, "{");
    }

    /* renamed from: d */
    public final by m7312d() {
        return m7292a(bv.EMPTY_OBJECT, bv.NONEMPTY_OBJECT, "}");
    }

    /* renamed from: a */
    private by m7293a(bv bvVar, String str) {
        m7296a(true);
        this.f7274b.add(bvVar);
        this.f7273a.write(str);
        return this;
    }

    /* renamed from: a */
    private by m7292a(bv bvVar, bv bvVar2, String str) {
        bv e = m7298e();
        if (e == bvVar2 || e == bvVar) {
            this.f7274b.remove(this.f7274b.size() - 1);
            if (e == bvVar2) {
                m7300g();
            }
            this.f7273a.write(str);
            return this;
        }
        throw new IllegalStateException("Nesting problem: " + this.f7274b);
    }

    /* renamed from: e */
    private bv m7298e() {
        return (bv) this.f7274b.get(this.f7274b.size() - 1);
    }

    /* renamed from: a */
    private void m7295a(bv bvVar) {
        this.f7274b.set(this.f7274b.size() - 1, bvVar);
    }

    /* renamed from: a */
    public final by m7306a(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        bv e = m7298e();
        if (e == bv.NONEMPTY_OBJECT) {
            this.f7273a.write(44);
        } else if (e != bv.EMPTY_OBJECT) {
            throw new IllegalStateException("Nesting problem: " + this.f7274b);
        }
        m7300g();
        m7295a(bv.DANGLING_NAME);
        m7297c(str);
        return this;
    }

    /* renamed from: b */
    public final by m7310b(String str) {
        if (str == null) {
            return m7299f();
        }
        m7296a(false);
        m7297c(str);
        return this;
    }

    /* renamed from: f */
    private by m7299f() {
        m7296a(false);
        this.f7273a.write("null");
        return this;
    }

    /* renamed from: a */
    public final by m7302a(long j) {
        m7296a(false);
        this.f7273a.write(Long.toString(j));
        return this;
    }

    /* renamed from: a */
    public final by m7304a(Number number) {
        if (number == null) {
            return m7299f();
        }
        CharSequence obj = number.toString();
        if (this.f7277e || !(obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            m7296a(false);
            this.f7273a.append(obj);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public final void close() {
        this.f7273a.close();
        if (m7298e() != bv.NONEMPTY_DOCUMENT) {
            throw new IOException("Incomplete document");
        }
    }

    /* renamed from: c */
    private void m7297c(String str) {
        this.f7273a.write("\"");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\b':
                    this.f7273a.write("\\b");
                    continue;
                case '\t':
                    this.f7273a.write("\\t");
                    continue;
                case '\n':
                    this.f7273a.write("\\n");
                    continue;
                case '\f':
                    this.f7273a.write("\\f");
                    continue;
                case '\r':
                    this.f7273a.write("\\r");
                    continue;
                case '\"':
                case '\\':
                    this.f7273a.write(92);
                    break;
                case ' ':
                case ' ':
                    this.f7273a.write(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                    continue;
                default:
                    if (charAt <= '\u001f') {
                        this.f7273a.write(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                        continue;
                    }
                    break;
            }
            this.f7273a.write(charAt);
        }
        this.f7273a.write("\"");
    }

    /* renamed from: g */
    private void m7300g() {
        if (this.f7275c != null) {
            this.f7273a.write("\n");
            for (int i = 1; i < this.f7274b.size(); i++) {
                this.f7273a.write(this.f7275c);
            }
        }
    }

    /* renamed from: a */
    private void m7296a(boolean z) {
        switch (m7298e()) {
            case EMPTY_DOCUMENT:
                if (this.f7277e || z) {
                    m7295a(bv.NONEMPTY_DOCUMENT);
                    return;
                }
                throw new IllegalStateException("JSON must start with an array or an object.");
            case EMPTY_ARRAY:
                m7295a(bv.NONEMPTY_ARRAY);
                m7300g();
                return;
            case NONEMPTY_ARRAY:
                this.f7273a.append(',');
                m7300g();
                return;
            case DANGLING_NAME:
                this.f7273a.append(this.f7276d);
                m7295a(bv.NONEMPTY_OBJECT);
                return;
            case NONEMPTY_DOCUMENT:
                throw new IllegalStateException("JSON must have only one top-level value.");
            default:
                throw new IllegalStateException("Nesting problem: " + this.f7274b);
        }
    }

    /* renamed from: a */
    public final by m7305a(Object obj) {
        if (obj == null) {
            return m7299f();
        }
        if (obj instanceof bw) {
            if (this.f7274b.size() == this.f7274b.size()) {
                return this;
            }
            throw new IllegalStateException(obj.getClass().getName() + ".writeToJson(JsonWriter) wrote incomplete value");
        } else if (obj instanceof Boolean) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            m7296a(false);
            this.f7273a.write(booleanValue ? "true" : "false");
            return this;
        } else if (obj instanceof Number) {
            if (obj instanceof Long) {
                return m7302a(((Number) obj).longValue());
            }
            if (!(obj instanceof Double)) {
                return m7304a((Number) obj);
            }
            double doubleValue = ((Number) obj).doubleValue();
            if (this.f7277e || !(Double.isNaN(doubleValue) || Double.isInfinite(doubleValue))) {
                m7296a(false);
                this.f7273a.append(Double.toString(doubleValue));
                return this;
            }
            throw new IllegalArgumentException("Numeric values must be finite, but was " + doubleValue);
        } else if (obj instanceof String) {
            return m7310b((String) obj);
        } else {
            if (obj instanceof bq) {
                return m7303a((bq) obj);
            }
            if (obj instanceof Collection) {
                return m7307a((Collection) obj);
            }
            if (obj instanceof Map) {
                return m7308a((Map) obj);
            }
            if (obj instanceof Date) {
                Date date = (Date) obj;
                if (date == null) {
                    return m7299f();
                }
                return m7310b(C3002z.m8235a(date));
            } else if (obj instanceof Object[]) {
                return m7294a((Object[]) obj);
            } else {
                throw new IllegalArgumentException("Unknown type: " + obj.getClass().getName());
            }
        }
    }

    /* renamed from: a */
    private by m7294a(Object[] objArr) {
        if (objArr == null) {
            return m7299f();
        }
        m7301a();
        for (Object a : objArr) {
            m7305a(a);
        }
        m7309b();
        return this;
    }

    /* renamed from: a */
    public final by m7303a(bq bqVar) {
        m7296a(false);
        bqVar.mo6184a(this.f7273a);
        return this;
    }

    /* renamed from: a */
    public final by m7307a(Collection collection) {
        if (collection == null) {
            return m7299f();
        }
        m7301a();
        for (Object a : collection) {
            m7305a(a);
        }
        m7309b();
        return this;
    }

    /* renamed from: a */
    public final by m7308a(Map map) {
        if (map == null) {
            return m7299f();
        }
        m7311c();
        for (Entry entry : map.entrySet()) {
            m7306a(String.valueOf(entry.getKey()));
            m7305a(entry.getValue());
        }
        m7312d();
        return this;
    }
}
