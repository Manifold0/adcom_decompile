// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.nio.ByteOrder;
import java.util.Arrays;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public final class hi
{
    private final byte[] a;
    private ByteBuffer b;
    private hh c;
    private int d;
    
    public hi() {
        this.a = new byte[256];
        this.d = 0;
    }
    
    private int[] a(final int n) {
        final byte[] array = new byte[n * 3];
        int[] array3;
        try {
            this.b.get(array);
            final int[] array2 = new int[256];
            int n2 = 0;
            int n3 = 0;
            while (true) {
                array3 = array2;
                if (n3 >= n) {
                    break;
                }
                final int n4 = n2 + 1;
                final byte b = array[n2];
                final int n5 = n4 + 1;
                final byte b2 = array[n4];
                n2 = n5 + 1;
                array2[n3] = ((b & 0xFF) << 16 | 0xFF000000 | (b2 & 0xFF) << 8 | (array[n5] & 0xFF));
                ++n3;
            }
        }
        catch (BufferUnderflowException ex) {
            array3 = null;
            this.c.b = 1;
        }
        return array3;
    }
    
    private void b() {
        int n = 0;
        while (n == 0 && !this.h() && this.c.c <= Integer.MAX_VALUE) {
            switch (this.g()) {
                default: {
                    this.c.b = 1;
                    continue;
                }
                case 44: {
                    if (this.c.d == null) {
                        this.c.d = new hg();
                    }
                    this.c.d.a = this.b.getShort();
                    this.c.d.b = this.b.getShort();
                    this.c.d.c = this.b.getShort();
                    this.c.d.d = this.b.getShort();
                    final int g = this.g();
                    int n2;
                    if ((g & 0x80) != 0x0) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    final int n3 = (int)Math.pow(2.0, (g & 0x7) + 1);
                    this.c.d.e = ((g & 0x40) != 0x0);
                    if (n2 != 0) {
                        this.c.d.k = this.a(n3);
                    }
                    else {
                        this.c.d.k = null;
                    }
                    this.c.d.j = this.b.position();
                    this.g();
                    this.e();
                    if (!this.h()) {
                        final hh c = this.c;
                        ++c.c;
                        this.c.e.add(this.c.d);
                        continue;
                    }
                    continue;
                }
                case 33: {
                    switch (this.g()) {
                        default: {
                            this.e();
                            continue;
                        }
                        case 249: {
                            this.c.d = new hg();
                            this.g();
                            final int g2 = this.g();
                            this.c.d.g = (g2 & 0x1C) >> 2;
                            if (this.c.d.g == 0) {
                                this.c.d.g = 1;
                            }
                            this.c.d.f = ((g2 & 0x1) != 0x0);
                            int short1;
                            if ((short1 = this.b.getShort()) < 2) {
                                short1 = 10;
                            }
                            this.c.d.i = short1 * 10;
                            this.c.d.h = this.g();
                            this.g();
                            continue;
                        }
                        case 255: {
                            this.f();
                            String string = "";
                            for (int i = 0; i < 11; ++i) {
                                string += (char)this.a[i];
                            }
                            if (string.equals("NETSCAPE2.0")) {
                                this.c();
                                continue;
                            }
                            this.e();
                            continue;
                        }
                        case 254: {
                            this.e();
                            continue;
                        }
                        case 1: {
                            this.e();
                            continue;
                        }
                    }
                    break;
                }
                case 59: {
                    n = 1;
                    continue;
                }
            }
        }
    }
    
    private void c() {
        do {
            this.f();
            if (this.a[0] == 1) {
                this.c.m = ((this.a[1] & 0xFF) | (this.a[2] & 0xFF) << 8);
                if (this.c.m != 0) {
                    continue;
                }
                this.c.m = -1;
            }
        } while (this.d > 0 && !this.h());
    }
    
    private void d() {
        boolean h = true;
        String string = "";
        for (int i = 0; i < 6; ++i) {
            string += (char)this.g();
        }
        if (!string.startsWith("GIF")) {
            this.c.b = 1;
        }
        else {
            this.c.f = this.b.getShort();
            this.c.g = this.b.getShort();
            final int g = this.g();
            final hh c = this.c;
            if ((g & 0x80) == 0x0) {
                h = false;
            }
            c.h = h;
            this.c.i = 2 << (g & 0x7);
            this.c.j = this.g();
            this.c.k = this.g();
            if (this.c.h && !this.h()) {
                this.c.a = this.a(this.c.i);
                this.c.l = this.c.a[this.c.j];
            }
        }
    }
    
    private void e() {
        try {
            int i;
            do {
                i = this.g();
                this.b.position(this.b.position() + i);
            } while (i > 0);
        }
        catch (IllegalArgumentException ex) {}
    }
    
    private int f() {
        this.d = this.g();
        if (this.d > 0) {
            int n = 0;
            int n2 = 0;
            while (true) {
                try {
                    if (n2 < this.d) {
                        n = this.d - n2;
                        this.b.get(this.a, n2, n);
                        n2 += n;
                        continue;
                    }
                }
                catch (Exception ex) {
                    final int d = this.d;
                    this.c.b = 1;
                }
                break;
            }
            return n2;
        }
        return 0;
    }
    
    private int g() {
        try {
            return this.b.get() & 0xFF;
        }
        catch (Exception ex) {
            this.c.b = 1;
            return 0;
        }
    }
    
    private boolean h() {
        return this.c.b != 0;
    }
    
    public final hh a() {
        if (this.b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (this.h()) {
            return this.c;
        }
        this.d();
        if (!this.h()) {
            this.b();
            if (this.c.c < 0) {
                this.c.b = 1;
            }
        }
        return this.c;
    }
    
    public final hi a(final byte[] array) {
        if (array != null) {
            final ByteBuffer wrap = ByteBuffer.wrap(array);
            this.b = null;
            Arrays.fill(this.a, (byte)0);
            this.c = new hh();
            this.d = 0;
            (this.b = wrap.asReadOnlyBuffer()).position(0);
            this.b.order(ByteOrder.LITTLE_ENDIAN);
            return this;
        }
        this.b = null;
        this.c.b = 2;
        return this;
    }
}
