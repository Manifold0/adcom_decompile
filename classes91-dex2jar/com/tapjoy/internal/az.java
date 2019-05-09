// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.io.Flushable;
import java.io.Closeable;

public final class az extends ay implements bc, Closeable, Flushable
{
    private final bc a;
    private final LinkedList b;
    private final LinkedList c;
    private int d;
    private boolean e;
    
    private az(final bc a) {
        this.a = a;
        this.b = new LinkedList();
        this.c = new LinkedList();
        this.d = a.size();
        this.e = (this.d == 0);
    }
    
    public static az a(final bc bc) {
        return new az(bc);
    }
    
    @Override
    public final Object a(final int n) {
        if (n < 0 || n >= this.d) {
            throw new IndexOutOfBoundsException();
        }
        int i = this.b.size();
        Object value;
        if (n < i) {
            value = this.b.get(n);
        }
        else {
            if (this.e) {
                return this.c.get(n - i);
            }
            if (n >= this.a.size()) {
                return this.c.get(n - this.a.size());
            }
            Object a = null;
            while (i <= n) {
                a = this.a.a(i);
                this.b.add(a);
                ++i;
            }
            value = a;
            if (n + 1 + this.c.size() == this.d) {
                this.e = true;
                return a;
            }
        }
        return value;
    }
    
    @Override
    public final void b(final int n) {
        if (n <= 0 || n > this.d) {
            throw new IndexOutOfBoundsException();
        }
        if (n <= this.b.size()) {
            bb.a(this.b, n);
            this.a.b(n);
        }
        else {
            this.b.clear();
            final int n2 = this.c.size() + n - this.d;
            if (n2 < 0) {
                this.a.b(n);
            }
            else {
                this.a.clear();
                this.e = true;
                if (n2 > 0) {
                    bb.a(this.c, n2);
                }
            }
        }
        this.d -= n;
    }
    
    @Override
    public final void close() {
        try {
            this.flush();
        }
        finally {
            if (this.a instanceof Closeable) {
                ((Closeable)this.a).close();
            }
        }
    }
    
    @Override
    protected final void finalize() {
        this.close();
        super.finalize();
    }
    
    @Override
    public final void flush() {
        if (!this.c.isEmpty()) {
            this.a.addAll(this.c);
            if (this.e) {
                this.b.addAll(this.c);
            }
            this.c.clear();
        }
    }
    
    @Override
    public final boolean offer(final Object o) {
        this.c.add(o);
        ++this.d;
        return true;
    }
    
    @Override
    public final Object peek() {
        Object o;
        if (this.d <= 0) {
            o = null;
        }
        else {
            if (!this.b.isEmpty()) {
                return this.b.element();
            }
            if (this.e) {
                return this.c.element();
            }
            final Object peek = this.a.peek();
            this.b.add(peek);
            o = peek;
            if (this.d == this.b.size() + this.c.size()) {
                this.e = true;
                return peek;
            }
        }
        return o;
    }
    
    @Override
    public final Object poll() {
        if (this.d <= 0) {
            return null;
        }
        Object o;
        if (!this.b.isEmpty()) {
            o = this.b.remove();
            this.a.b(1);
        }
        else if (this.e) {
            o = this.c.remove();
        }
        else {
            o = this.a.remove();
            if (this.d == this.c.size() + 1) {
                this.e = true;
                o = o;
            }
        }
        --this.d;
        return o;
    }
    
    @Override
    public final int size() {
        return this.d;
    }
}
