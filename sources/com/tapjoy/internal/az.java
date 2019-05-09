package com.tapjoy.internal;

import java.io.Closeable;
import java.io.Flushable;
import java.util.LinkedList;

public final class az extends ay implements bc, Closeable, Flushable {
    /* renamed from: a */
    private final bc f7216a;
    /* renamed from: b */
    private final LinkedList f7217b = new LinkedList();
    /* renamed from: c */
    private final LinkedList f7218c = new LinkedList();
    /* renamed from: d */
    private int f7219d;
    /* renamed from: e */
    private boolean f7220e;

    /* renamed from: a */
    public static az m7186a(bc bcVar) {
        return new az(bcVar);
    }

    private az(bc bcVar) {
        this.f7216a = bcVar;
        this.f7219d = bcVar.size();
        this.f7220e = this.f7219d == 0;
    }

    protected final void finalize() {
        close();
        super.finalize();
    }

    public final void close() {
        try {
            flush();
        } finally {
            if (this.f7216a instanceof Closeable) {
                ((Closeable) this.f7216a).close();
            }
        }
    }

    public final void flush() {
        if (!this.f7218c.isEmpty()) {
            this.f7216a.addAll(this.f7218c);
            if (this.f7220e) {
                this.f7217b.addAll(this.f7218c);
            }
            this.f7218c.clear();
        }
    }

    public final int size() {
        return this.f7219d;
    }

    public final boolean offer(Object e) {
        this.f7218c.add(e);
        this.f7219d++;
        return true;
    }

    public final Object poll() {
        if (this.f7219d <= 0) {
            return null;
        }
        Object remove;
        if (!this.f7217b.isEmpty()) {
            remove = this.f7217b.remove();
            this.f7216a.mo6183b(1);
        } else if (this.f7220e) {
            remove = this.f7218c.remove();
        } else {
            remove = this.f7216a.remove();
            if (this.f7219d == this.f7218c.size() + 1) {
                this.f7220e = true;
            }
        }
        this.f7219d--;
        return remove;
    }

    public final Object peek() {
        if (this.f7219d <= 0) {
            return null;
        }
        if (!this.f7217b.isEmpty()) {
            return this.f7217b.element();
        }
        if (this.f7220e) {
            return this.f7218c.element();
        }
        Object peek = this.f7216a.peek();
        this.f7217b.add(peek);
        if (this.f7219d != this.f7217b.size() + this.f7218c.size()) {
            return peek;
        }
        this.f7220e = true;
        return peek;
    }

    /* renamed from: a */
    public final Object mo6182a(int i) {
        if (i < 0 || i >= this.f7219d) {
            throw new IndexOutOfBoundsException();
        }
        int size = this.f7217b.size();
        if (i < size) {
            return this.f7217b.get(i);
        }
        if (this.f7220e) {
            return this.f7218c.get(i - size);
        }
        if (i >= this.f7216a.size()) {
            return this.f7218c.get(i - this.f7216a.size());
        }
        int i2 = size;
        Object obj = null;
        int i3 = i2;
        while (i3 <= i) {
            Object a = this.f7216a.mo6182a(i3);
            this.f7217b.add(a);
            i3++;
            obj = a;
        }
        if ((i + 1) + this.f7218c.size() != this.f7219d) {
            return obj;
        }
        this.f7220e = true;
        return obj;
    }

    /* renamed from: b */
    public final void mo6183b(int i) {
        if (i <= 0 || i > this.f7219d) {
            throw new IndexOutOfBoundsException();
        }
        if (i <= this.f7217b.size()) {
            bb.m7192a(this.f7217b, i);
            this.f7216a.mo6183b(i);
        } else {
            this.f7217b.clear();
            int size = (this.f7218c.size() + i) - this.f7219d;
            if (size < 0) {
                this.f7216a.mo6183b(i);
            } else {
                this.f7216a.clear();
                this.f7220e = true;
                if (size > 0) {
                    bb.m7192a(this.f7218c, size);
                }
            }
        }
        this.f7219d -= i;
    }
}
