// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.IOException;
import java.io.EOFException;
import java.net.ProtocolException;

public final class do
{
    final hw a;
    private long b;
    private long c;
    private int d;
    private int e;
    private int f;
    private long g;
    private dk h;
    
    public do(final hw a) {
        this.b = 0L;
        this.c = Long.MAX_VALUE;
        this.e = 2;
        this.f = -1;
        this.g = -1L;
        this.a = a;
    }
    
    private void a(final int n) {
        while (this.b < this.c && !this.a.b()) {
            final int i = this.i();
            if (i == 0) {
                throw new ProtocolException("Unexpected tag 0");
            }
            final int n2 = i >> 3;
            final int n3 = i & 0x7;
            switch (n3) {
                default: {
                    throw new ProtocolException("Unexpected field encoding: " + n3);
                }
                case 3: {
                    this.a(n2);
                    continue;
                }
                case 4: {
                    if (n2 == n) {
                        return;
                    }
                    throw new ProtocolException("Unexpected end group");
                }
                case 2: {
                    final int j = this.i();
                    this.b += j;
                    this.a.d(j);
                    continue;
                }
                case 0: {
                    this.e = 0;
                    this.e();
                    continue;
                }
                case 1: {
                    this.e = 1;
                    this.g();
                    continue;
                }
                case 5: {
                    this.e = 5;
                    this.f();
                    continue;
                }
            }
        }
        throw new EOFException();
    }
    
    private void b(final int n) {
        if (this.e == n) {
            this.e = 6;
            return;
        }
        if (this.b > this.c) {
            throw new IOException("Expected to end at " + this.c + " but was " + this.b);
        }
        if (this.b == this.c) {
            this.c = this.g;
            this.g = -1L;
            this.e = 6;
            return;
        }
        this.e = 7;
    }
    
    private int i() {
        ++this.b;
        int c = this.a.c();
        if (c < 0) {
            final int n = c & 0x7F;
            ++this.b;
            final byte c2 = this.a.c();
            if (c2 >= 0) {
                return n | c2 << 7;
            }
            final int n2 = n | (c2 & 0x7F) << 7;
            ++this.b;
            final byte c3 = this.a.c();
            if (c3 >= 0) {
                return n2 | c3 << 14;
            }
            final int n3 = n2 | (c3 & 0x7F) << 14;
            ++this.b;
            final byte c4 = this.a.c();
            if (c4 >= 0) {
                return n3 | c4 << 21;
            }
            ++this.b;
            final byte c5 = this.a.c();
            final int n4 = c = (n3 | (c4 & 0x7F) << 21 | c5 << 28);
            if (c5 < 0) {
                for (int i = 0; i < 5; ++i) {
                    ++this.b;
                    c = n4;
                    if (this.a.c() >= 0) {
                        return c;
                    }
                }
                throw new ProtocolException("Malformed VARINT");
            }
        }
        return c;
    }
    
    public final long a() {
        if (this.e != 2) {
            throw new IllegalStateException("Unexpected call to beginMessage()");
        }
        if (++this.d > 65) {
            throw new IOException("Wire recursion limit exceeded");
        }
        final long g = this.g;
        this.g = -1L;
        this.e = 6;
        return g;
    }
    
    public final void a(final long c) {
        if (this.e != 6) {
            throw new IllegalStateException("Unexpected call to endMessage()");
        }
        final int d = this.d - 1;
        this.d = d;
        if (d < 0 || this.g != -1L) {
            throw new IllegalStateException("No corresponding call to beginMessage()");
        }
        if (this.b != this.c && this.d != 0) {
            throw new IOException("Expected to end at " + this.c + " but was " + this.b);
        }
        this.c = c;
    }
    
    public final int b() {
        if (this.e == 7) {
            this.e = 2;
            return this.f;
        }
        if (this.e != 6) {
            throw new IllegalStateException("Unexpected call to nextTag()");
        }
        while (this.b < this.c && !this.a.b()) {
            final int i = this.i();
            if (i == 0) {
                throw new ProtocolException("Unexpected tag 0");
            }
            this.f = i >> 3;
            final int n = i & 0x7;
            switch (n) {
                case 3: {
                    this.a(this.f);
                    continue;
                }
                default: {
                    throw new ProtocolException("Unexpected field encoding: " + n);
                }
                case 4: {
                    throw new ProtocolException("Unexpected end group");
                }
                case 2: {
                    this.h = dk.c;
                    this.e = 2;
                    final int j = this.i();
                    if (j < 0) {
                        throw new ProtocolException("Negative length: " + j);
                    }
                    if (this.g != -1L) {
                        throw new IllegalStateException();
                    }
                    this.g = this.c;
                    this.c = j + this.b;
                    if (this.c > this.g) {
                        throw new EOFException();
                    }
                    return this.f;
                }
                case 0: {
                    this.h = dk.a;
                    this.e = 0;
                    return this.f;
                }
                case 1: {
                    this.h = dk.b;
                    this.e = 1;
                    return this.f;
                }
                case 5: {
                    this.h = dk.d;
                    this.e = 5;
                    return this.f;
                }
            }
        }
        return -1;
    }
    
    public final dk c() {
        return this.h;
    }
    
    public final int d() {
        if (this.e != 0 && this.e != 2) {
            throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.e);
        }
        final int i = this.i();
        this.b(0);
        return i;
    }
    
    public final long e() {
        if (this.e != 0 && this.e != 2) {
            throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.e);
        }
        long n = 0L;
        for (int i = 0; i < 64; i += 7) {
            ++this.b;
            final byte c = this.a.c();
            n |= (long)(c & 0x7F) << i;
            if ((c & 0x80) == 0x0) {
                this.b(0);
                return n;
            }
        }
        throw new ProtocolException("WireInput encountered a malformed varint");
    }
    
    public final int f() {
        if (this.e != 5 && this.e != 2) {
            throw new ProtocolException("Expected FIXED32 or LENGTH_DELIMITED but was " + this.e);
        }
        this.a.a(4L);
        this.b += 4L;
        final int e = this.a.e();
        this.b(5);
        return e;
    }
    
    public final long g() {
        if (this.e != 1 && this.e != 2) {
            throw new ProtocolException("Expected FIXED64 or LENGTH_DELIMITED but was " + this.e);
        }
        this.a.a(8L);
        this.b += 8L;
        final long f = this.a.f();
        this.b(1);
        return f;
    }
    
    final long h() {
        if (this.e != 2) {
            throw new ProtocolException("Expected LENGTH_DELIMITED but was " + this.e);
        }
        final long n = this.c - this.b;
        this.a.a(n);
        this.e = 6;
        this.b = this.c;
        this.c = this.g;
        this.g = -1L;
        return n;
    }
}
