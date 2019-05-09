// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.Date;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.io.Writer;
import java.io.Closeable;

public final class by implements Closeable
{
    final Writer a;
    private final List b;
    private String c;
    private String d;
    private boolean e;
    
    public by(final Writer a) {
        (this.b = new ArrayList()).add(bv.f);
        this.d = ":";
        if (a == null) {
            throw new NullPointerException("out == null");
        }
        this.a = a;
    }
    
    private by a(final bv bv, final bv bv2, final String s) {
        final bv e = this.e();
        if (e != bv2 && e != bv) {
            throw new IllegalStateException("Nesting problem: " + this.b);
        }
        this.b.remove(this.b.size() - 1);
        if (e == bv2) {
            this.g();
        }
        this.a.write(s);
        return this;
    }
    
    private by a(final bv bv, final String s) {
        this.a(true);
        this.b.add(bv);
        this.a.write(s);
        return this;
    }
    
    private by a(final Object[] array) {
        if (array == null) {
            return this.f();
        }
        this.a();
        for (int length = array.length, i = 0; i < length; ++i) {
            this.a(array[i]);
        }
        this.b();
        return this;
    }
    
    private void a(final bv bv) {
        this.b.set(this.b.size() - 1, bv);
    }
    
    private void a(final boolean b) {
        switch (by$1.a[this.e().ordinal()]) {
            default: {
                throw new IllegalStateException("Nesting problem: " + this.b);
            }
            case 1: {
                if (!this.e && !b) {
                    throw new IllegalStateException("JSON must start with an array or an object.");
                }
                this.a(bv.g);
            }
            case 2: {
                this.a(bv.b);
                this.g();
            }
            case 3: {
                this.a.append(',');
                this.g();
            }
            case 4: {
                this.a.append((CharSequence)this.d);
                this.a(bv.e);
            }
            case 5: {
                throw new IllegalStateException("JSON must have only one top-level value.");
            }
        }
    }
    
    private void c(final String s) {
        this.a.write("\"");
        final int length = s.length();
        int i = 0;
    Label_0143_Outer:
        while (i < length) {
            final char char1 = s.charAt(i);
            while (true) {
                Label_0159: {
                    switch (char1) {
                        default: {
                            if (char1 <= '\u001f') {
                                this.a.write(String.format("\\u%04x", (int)char1));
                                break;
                            }
                            break Label_0159;
                        }
                        case 34:
                        case 92: {
                            this.a.write(92);
                            break Label_0159;
                        }
                        case 9: {
                            this.a.write("\\t");
                            break;
                        }
                        case 8: {
                            this.a.write("\\b");
                            break;
                        }
                        case 10: {
                            this.a.write("\\n");
                            break;
                        }
                        case 13: {
                            this.a.write("\\r");
                            break;
                        }
                        case 12: {
                            this.a.write("\\f");
                            break;
                        }
                        case 8232:
                        case 8233: {
                            this.a.write(String.format("\\u%04x", (int)char1));
                            break;
                        }
                    }
                    ++i;
                    continue Label_0143_Outer;
                }
                this.a.write(char1);
                continue;
            }
        }
        this.a.write("\"");
    }
    
    private bv e() {
        return this.b.get(this.b.size() - 1);
    }
    
    private by f() {
        this.a(false);
        this.a.write("null");
        return this;
    }
    
    private void g() {
        if (this.c != null) {
            this.a.write("\n");
            for (int i = 1; i < this.b.size(); ++i) {
                this.a.write(this.c);
            }
        }
    }
    
    public final by a() {
        return this.a(bv.a, "[");
    }
    
    public final by a(final long n) {
        this.a(false);
        this.a.write(Long.toString(n));
        return this;
    }
    
    public final by a(final bq bq) {
        this.a(false);
        bq.a(this.a);
        return this;
    }
    
    public final by a(final Number n) {
        if (n == null) {
            return this.f();
        }
        final String string = n.toString();
        if (!this.e && (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + n);
        }
        this.a(false);
        this.a.append((CharSequence)string);
        return this;
    }
    
    public final by a(final Object o) {
        by f;
        if (o == null) {
            f = this.f();
        }
        else if (o instanceof bw) {
            final int size = this.b.size();
            f = this;
            if (this.b.size() != size) {
                throw new IllegalStateException(o.getClass().getName() + ".writeToJson(JsonWriter) wrote incomplete value");
            }
        }
        else {
            if (o instanceof Boolean) {
                final boolean booleanValue = (boolean)o;
                this.a(false);
                final Writer a = this.a;
                String s;
                if (booleanValue) {
                    s = "true";
                }
                else {
                    s = "false";
                }
                a.write(s);
                return this;
            }
            if (o instanceof Number) {
                if (o instanceof Long) {
                    return this.a(((Number)o).longValue());
                }
                if (!(o instanceof Double)) {
                    return this.a((Number)o);
                }
                final double doubleValue = ((Number)o).doubleValue();
                if (!this.e && (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue))) {
                    throw new IllegalArgumentException("Numeric values must be finite, but was " + doubleValue);
                }
                this.a(false);
                this.a.append((CharSequence)Double.toString(doubleValue));
                return this;
            }
            else {
                if (o instanceof String) {
                    return this.b((String)o);
                }
                if (o instanceof bq) {
                    return this.a((bq)o);
                }
                if (o instanceof Collection) {
                    return this.a((Collection)o);
                }
                if (o instanceof Map) {
                    return this.a((Map)o);
                }
                if (o instanceof Date) {
                    final Date date = (Date)o;
                    if (date == null) {
                        return this.f();
                    }
                    return this.b(z.a(date));
                }
                else {
                    if (o instanceof Object[]) {
                        return this.a((Object[])o);
                    }
                    throw new IllegalArgumentException("Unknown type: " + o.getClass().getName());
                }
            }
        }
        return f;
    }
    
    public final by a(final String s) {
        if (s == null) {
            throw new NullPointerException("name == null");
        }
        final bv e = this.e();
        if (e == bv.e) {
            this.a.write(44);
        }
        else if (e != bv.c) {
            throw new IllegalStateException("Nesting problem: " + this.b);
        }
        this.g();
        this.a(bv.d);
        this.c(s);
        return this;
    }
    
    public final by a(final Collection collection) {
        if (collection == null) {
            return this.f();
        }
        this.a();
        final Iterator<Object> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.a(iterator.next());
        }
        this.b();
        return this;
    }
    
    public final by a(final Map map) {
        if (map == null) {
            return this.f();
        }
        this.c();
        for (final Map.Entry<Object, V> entry : map.entrySet()) {
            this.a(String.valueOf(entry.getKey()));
            this.a(entry.getValue());
        }
        this.d();
        return this;
    }
    
    public final by b() {
        return this.a(bv.a, bv.b, "]");
    }
    
    public final by b(final String s) {
        if (s == null) {
            return this.f();
        }
        this.a(false);
        this.c(s);
        return this;
    }
    
    public final by c() {
        return this.a(bv.c, "{");
    }
    
    @Override
    public final void close() {
        this.a.close();
        if (this.e() != bv.g) {
            throw new IOException("Incomplete document");
        }
    }
    
    public final by d() {
        return this.a(bv.c, bv.e, "}");
    }
}
