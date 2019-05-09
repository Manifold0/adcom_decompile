package com.tapjoy.internal;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;

/* renamed from: com.tapjoy.internal.do */
public final class C2874do {
    /* renamed from: a */
    final hw f7369a;
    /* renamed from: b */
    private long f7370b = 0;
    /* renamed from: c */
    private long f7371c = Long.MAX_VALUE;
    /* renamed from: d */
    private int f7372d;
    /* renamed from: e */
    private int f7373e = 2;
    /* renamed from: f */
    private int f7374f = -1;
    /* renamed from: g */
    private long f7375g = -1;
    /* renamed from: h */
    private dk f7376h;

    public C2874do(hw hwVar) {
        this.f7369a = hwVar;
    }

    /* renamed from: a */
    public final long m7455a() {
        if (this.f7373e != 2) {
            throw new IllegalStateException("Unexpected call to beginMessage()");
        }
        int i = this.f7372d + 1;
        this.f7372d = i;
        if (i > 65) {
            throw new IOException("Wire recursion limit exceeded");
        }
        long j = this.f7375g;
        this.f7375g = -1;
        this.f7373e = 6;
        return j;
    }

    /* renamed from: a */
    public final void m7456a(long j) {
        if (this.f7373e != 6) {
            throw new IllegalStateException("Unexpected call to endMessage()");
        }
        int i = this.f7372d - 1;
        this.f7372d = i;
        if (i < 0 || this.f7375g != -1) {
            throw new IllegalStateException("No corresponding call to beginMessage()");
        } else if (this.f7370b == this.f7371c || this.f7372d == 0) {
            this.f7371c = j;
        } else {
            throw new IOException("Expected to end at " + this.f7371c + " but was " + this.f7370b);
        }
    }

    /* renamed from: b */
    public final int m7457b() {
        if (this.f7373e == 7) {
            this.f7373e = 2;
            return this.f7374f;
        } else if (this.f7373e != 6) {
            throw new IllegalStateException("Unexpected call to nextTag()");
        } else {
            while (this.f7370b < this.f7371c && !this.f7369a.mo6346b()) {
                int i = m7454i();
                if (i == 0) {
                    throw new ProtocolException("Unexpected tag 0");
                }
                this.f7374f = i >> 3;
                i &= 7;
                switch (i) {
                    case 0:
                        this.f7376h = dk.VARINT;
                        this.f7373e = 0;
                        return this.f7374f;
                    case 1:
                        this.f7376h = dk.FIXED64;
                        this.f7373e = 1;
                        return this.f7374f;
                    case 2:
                        this.f7376h = dk.LENGTH_DELIMITED;
                        this.f7373e = 2;
                        i = m7454i();
                        if (i < 0) {
                            throw new ProtocolException("Negative length: " + i);
                        } else if (this.f7375g != -1) {
                            throw new IllegalStateException();
                        } else {
                            this.f7375g = this.f7371c;
                            this.f7371c = ((long) i) + this.f7370b;
                            if (this.f7371c <= this.f7375g) {
                                return this.f7374f;
                            }
                            throw new EOFException();
                        }
                    case 3:
                        m7452a(this.f7374f);
                    case 4:
                        throw new ProtocolException("Unexpected end group");
                    case 5:
                        this.f7376h = dk.FIXED32;
                        this.f7373e = 5;
                        return this.f7374f;
                    default:
                        throw new ProtocolException("Unexpected field encoding: " + i);
                }
            }
            return -1;
        }
    }

    /* renamed from: c */
    public final dk m7458c() {
        return this.f7376h;
    }

    /* renamed from: a */
    private void m7452a(int i) {
        while (this.f7370b < this.f7371c && !this.f7369a.mo6346b()) {
            int i2 = m7454i();
            if (i2 == 0) {
                throw new ProtocolException("Unexpected tag 0");
            }
            int i3 = i2 >> 3;
            i2 &= 7;
            switch (i2) {
                case 0:
                    this.f7373e = 0;
                    m7460e();
                    break;
                case 1:
                    this.f7373e = 1;
                    m7462g();
                    break;
                case 2:
                    i2 = m7454i();
                    this.f7370b += (long) i2;
                    this.f7369a.mo6351d((long) i2);
                    break;
                case 3:
                    m7452a(i3);
                    break;
                case 4:
                    if (i3 != i) {
                        throw new ProtocolException("Unexpected end group");
                    }
                    return;
                case 5:
                    this.f7373e = 5;
                    m7461f();
                    break;
                default:
                    throw new ProtocolException("Unexpected field encoding: " + i2);
            }
        }
        throw new EOFException();
    }

    /* renamed from: d */
    public final int m7459d() {
        if (this.f7373e == 0 || this.f7373e == 2) {
            int i = m7454i();
            m7453b(0);
            return i;
        }
        throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.f7373e);
    }

    /* renamed from: i */
    private int m7454i() {
        this.f7370b++;
        byte c = this.f7369a.mo6347c();
        if (c >= (byte) 0) {
            return c;
        }
        int i = c & 127;
        this.f7370b++;
        byte c2 = this.f7369a.mo6347c();
        if (c2 >= (byte) 0) {
            return i | (c2 << 7);
        }
        i |= (c2 & 127) << 7;
        this.f7370b++;
        c2 = this.f7369a.mo6347c();
        if (c2 >= (byte) 0) {
            return i | (c2 << 14);
        }
        i |= (c2 & 127) << 14;
        this.f7370b++;
        c2 = this.f7369a.mo6347c();
        if (c2 >= (byte) 0) {
            return i | (c2 << 21);
        }
        i |= (c2 & 127) << 21;
        this.f7370b++;
        c2 = this.f7369a.mo6347c();
        i |= c2 << 28;
        if (c2 >= (byte) 0) {
            return i;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            this.f7370b++;
            if (this.f7369a.mo6347c() >= (byte) 0) {
                return i;
            }
        }
        throw new ProtocolException("Malformed VARINT");
    }

    /* renamed from: e */
    public final long m7460e() {
        if (this.f7373e == 0 || this.f7373e == 2) {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                this.f7370b++;
                byte c = this.f7369a.mo6347c();
                j |= ((long) (c & 127)) << i;
                if ((c & 128) == 0) {
                    m7453b(0);
                    return j;
                }
            }
            throw new ProtocolException("WireInput encountered a malformed varint");
        }
        throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.f7373e);
    }

    /* renamed from: f */
    public final int m7461f() {
        if (this.f7373e == 5 || this.f7373e == 2) {
            this.f7369a.mo6340a(4);
            this.f7370b += 4;
            int e = this.f7369a.mo6352e();
            m7453b(5);
            return e;
        }
        throw new ProtocolException("Expected FIXED32 or LENGTH_DELIMITED but was " + this.f7373e);
    }

    /* renamed from: g */
    public final long m7462g() {
        if (this.f7373e == 1 || this.f7373e == 2) {
            this.f7369a.mo6340a(8);
            this.f7370b += 8;
            long f = this.f7369a.mo6354f();
            m7453b(1);
            return f;
        }
        throw new ProtocolException("Expected FIXED64 or LENGTH_DELIMITED but was " + this.f7373e);
    }

    /* renamed from: b */
    private void m7453b(int i) {
        if (this.f7373e == i) {
            this.f7373e = 6;
        } else if (this.f7370b > this.f7371c) {
            throw new IOException("Expected to end at " + this.f7371c + " but was " + this.f7370b);
        } else if (this.f7370b == this.f7371c) {
            this.f7371c = this.f7375g;
            this.f7375g = -1;
            this.f7373e = 6;
        } else {
            this.f7373e = 7;
        }
    }

    /* renamed from: h */
    final long m7463h() {
        if (this.f7373e != 2) {
            throw new ProtocolException("Expected LENGTH_DELIMITED but was " + this.f7373e);
        }
        long j = this.f7371c - this.f7370b;
        this.f7369a.mo6340a(j);
        this.f7373e = 6;
        this.f7370b = this.f7371c;
        this.f7371c = this.f7375g;
        this.f7375g = -1;
        return j;
    }
}
