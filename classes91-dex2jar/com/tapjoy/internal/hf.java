// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Arrays;
import android.os.Build$VERSION;
import android.graphics.Bitmap$Config;
import java.util.Iterator;
import java.nio.ByteOrder;
import android.graphics.Bitmap;
import java.nio.ByteBuffer;

public class hf
{
    private static final String d;
    int a;
    int b;
    hh c;
    private int[] e;
    private final int[] f;
    private ByteBuffer g;
    private byte[] h;
    private byte[] i;
    private int j;
    private int k;
    private hi l;
    private short[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;
    private int[] q;
    private a r;
    private Bitmap s;
    private boolean t;
    private int u;
    private int v;
    private int w;
    private int x;
    private boolean y;
    
    static {
        d = hf.class.getSimpleName();
    }
    
    hf() {
        this((a)new hk());
    }
    
    private hf(final a r) {
        this.f = new int[256];
        this.j = 0;
        this.k = 0;
        this.r = r;
        this.c = new hh();
    }
    
    hf(final a a, final hh hh, final ByteBuffer byteBuffer) {
        this(a, hh, byteBuffer, (byte)0);
    }
    
    private hf(final a a, final hh hh, final ByteBuffer byteBuffer, final byte b) {
        this(a);
        this.b(hh, byteBuffer);
    }
    
    private void a(final hh hh, final ByteBuffer byteBuffer) {
        synchronized (this) {
            this.b(hh, byteBuffer);
        }
    }
    
    private void a(final hh hh, final byte[] array) {
        synchronized (this) {
            this.a(hh, ByteBuffer.wrap(array));
        }
    }
    
    private void a(final int[] array, final hg hg, final int n) {
        final int n2 = hg.d / this.v;
        final int n3 = hg.b / this.v;
        final int n4 = hg.c / this.v;
        for (int n5 = n3 * this.x + hg.a / this.v, x = this.x, i = n5; i < n5 + n2 * x; i += this.x) {
            for (int j = i; j < i + n4; ++j) {
                array[j] = n;
            }
        }
    }
    
    private void b() {
        if (this.j > this.k) {
            return;
        }
        if (this.i == null) {
            this.i = this.r.a(16384);
        }
        this.k = 0;
        this.j = Math.min(this.g.remaining(), 16384);
        this.g.get(this.i, 0, this.j);
    }
    
    private void b(final hh c, final ByteBuffer byteBuffer) {
        synchronized (this) {
            final int highestOneBit = Integer.highestOneBit(1);
            this.u = 0;
            this.c = c;
            this.y = false;
            this.a = -1;
            this.b = 0;
            (this.g = byteBuffer.asReadOnlyBuffer()).position(0);
            this.g.order(ByteOrder.LITTLE_ENDIAN);
            this.t = false;
            final Iterator<hg> iterator = c.e.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().g == 3) {
                    this.t = true;
                    break;
                }
            }
            this.v = highestOneBit;
            this.x = c.f / highestOneBit;
            this.w = c.g / highestOneBit;
            this.p = this.r.a(c.f * c.g);
            this.q = this.r.b(this.x * this.w);
        }
    }
    
    private int c() {
        try {
            this.b();
            return this.i[this.k++] & 0xFF;
        }
        catch (Exception ex) {
            this.u = 1;
            return 0;
        }
    }
    
    private int d() {
        final int c = this.c();
        if (c > 0) {
            try {
                if (this.h == null) {
                    this.h = this.r.a(255);
                }
                final int n = this.j - this.k;
                if (n >= c) {
                    System.arraycopy(this.i, this.k, this.h, 0, c);
                    this.k += c;
                    return c;
                }
                if (this.g.remaining() + n >= c) {
                    System.arraycopy(this.i, this.k, this.h, 0, n);
                    this.k = this.j;
                    this.b();
                    final int n2 = c - n;
                    System.arraycopy(this.i, 0, this.h, n, n2);
                    this.k += n2;
                    return c;
                }
            }
            catch (Exception ex) {
                this.u = 1;
                return c;
            }
            this.u = 1;
        }
        return c;
    }
    
    private Bitmap e() {
        Bitmap$Config bitmap$Config;
        if (this.y) {
            bitmap$Config = Bitmap$Config.ARGB_4444;
        }
        else {
            bitmap$Config = Bitmap$Config.RGB_565;
        }
        final Bitmap a = this.r.a(this.x, this.w, bitmap$Config);
        if (Build$VERSION.SDK_INT >= 12) {
            a.setHasAlpha(true);
        }
        return a;
    }
    
    final int a(final byte[] array) {
        synchronized (this) {
            if (this.l == null) {
                this.l = new hi();
            }
            this.c = this.l.a(array).a();
            if (array != null) {
                this.a(this.c, array);
            }
            return this.u;
        }
    }
    
    final Bitmap a() {
        while (true) {
            hg hg = null;
            int[] e2 = null;
            int n2 = 0;
            int n3 = 0;
            int c2 = 0;
            int n4 = 0;
            int n5 = 0;
        Label_0367_Outer:
            while (true) {
                Label_2049: {
                    while (true) {
                    Label_0630:
                        while (true) {
                            Label_0523: {
                                synchronized (this) {
                                    if (this.c.c <= 0 || this.a < 0) {
                                        final int c = this.c.c;
                                        final int a = this.a;
                                        this.u = 1;
                                    }
                                    Bitmap e;
                                    if (this.u == 1 || this.u == 2) {
                                        final int u = this.u;
                                        e = null;
                                    }
                                    else {
                                        this.u = 0;
                                        hg = this.c.e.get(this.a);
                                        final int n = this.a - 1;
                                        if (n < 0) {
                                            break Label_2049;
                                        }
                                        final hg hg2 = this.c.e.get(n);
                                        if (hg.k != null) {
                                            e2 = hg.k;
                                        }
                                        else {
                                            e2 = this.c.a;
                                        }
                                        this.e = e2;
                                        if (this.e == null) {
                                            final int a2 = this.a;
                                            this.u = 1;
                                            e = null;
                                        }
                                        else {
                                            if (hg.f) {
                                                System.arraycopy(this.e, 0, this.f, 0, this.e.length);
                                                (this.e = this.f)[hg.h] = 0;
                                            }
                                            e2 = this.q;
                                            if (hg2 == null) {
                                                Arrays.fill(e2, 0);
                                            }
                                            if (hg2 != null && hg2.g > 0) {
                                                if (hg2.g != 2) {
                                                    break Label_0523;
                                                }
                                                n2 = 0;
                                                int l;
                                                if (!hg.f) {
                                                    n2 = (l = this.c.l);
                                                    if (hg.k != null) {
                                                        l = n2;
                                                        if (this.c.j == hg.h) {
                                                            l = 0;
                                                        }
                                                    }
                                                }
                                                else {
                                                    l = n2;
                                                    if (this.a == 0) {
                                                        this.y = true;
                                                        l = n2;
                                                    }
                                                }
                                                this.a(e2, hg2, l);
                                            }
                                            this.j = 0;
                                            this.k = 0;
                                            if (hg != null) {
                                                this.g.position(hg.j);
                                            }
                                            if (hg == null) {
                                                n3 = this.c.f * this.c.g;
                                                if (this.p == null || this.p.length < n3) {
                                                    this.p = this.r.a(n3);
                                                }
                                                if (this.m == null) {
                                                    this.m = new short[4096];
                                                }
                                                if (this.n == null) {
                                                    this.n = new byte[4096];
                                                }
                                                if (this.o == null) {
                                                    this.o = new byte[4097];
                                                }
                                                c2 = this.c();
                                                n4 = 1 << c2;
                                                n5 = -1;
                                                n2 = c2 + 1;
                                                for (int i = 0; i < n4; ++i) {
                                                    this.m[i] = 0;
                                                    this.n[i] = (byte)i;
                                                }
                                                break Label_0367_Outer;
                                            }
                                            break Label_0630;
                                        }
                                    }
                                    return e;
                                }
                            }
                            final hg hg3;
                            if (hg3.g != 3) {
                                continue Label_0367_Outer;
                            }
                            if (this.s == null) {
                                this.a(e2, hg3, 0);
                                continue Label_0367_Outer;
                            }
                            final int n6 = hg3.d / this.v;
                            n2 = hg3.b / this.v;
                            final int n7 = hg3.c / this.v;
                            final int n8 = hg3.a / this.v;
                            this.s.getPixels(e2, this.x * n2 + n8, this.x, n8, n2, n7, n6);
                            continue Label_0367_Outer;
                        }
                        n3 = hg.c * hg.d;
                        continue;
                    }
                }
                final hg hg2 = null;
                continue;
            }
            int n9 = 0;
            int n10 = 0;
            int n11 = 0;
            int n12 = 0;
            int n13 = 0;
            int n14 = (1 << n2) - 1;
            int n15 = n4 + 2;
            int n16 = 0;
            int d = 0;
            int n17 = 0;
            int j = 0;
        Label_0646:
            while (true) {
                j = n17;
                if (n9 >= n3) {
                    break;
                }
                if (d == 0) {
                    d = this.d();
                    if (d <= 0) {
                        this.u = 3;
                        j = n17;
                        break;
                    }
                    n16 = 0;
                }
                final byte b = this.h[n16];
                final int n18 = n16 + 1;
                final int n19 = d - 1;
                final int n20 = n13 + 8;
                int n21 = n11;
                int n22 = n17;
                final int n23 = ((b & 0xFF) << n13) + n12;
                int n24 = n2;
                int n25 = n14;
                int n26 = n15;
                int n27 = n23;
                int n28 = n10;
                int n29 = n5;
                int k = n20;
                while (k >= n24) {
                    final int n30 = n27 & n25;
                    n27 >>= n24;
                    k -= n24;
                    if (n30 == n4) {
                        n24 = c2 + 1;
                        n25 = (1 << n24) - 1;
                        n26 = n4 + 2;
                        n29 = -1;
                    }
                    else {
                        if (n30 > n26) {
                            this.u = 3;
                            final int n31 = n21;
                            final int n32 = n25;
                            n16 = n18;
                            final int n33 = n26;
                            final int n34 = n19;
                            final int n35 = n27;
                            final int n36 = k;
                            final int n37 = n24;
                            n17 = n22;
                            d = n34;
                            n2 = n37;
                            n14 = n32;
                            n10 = n28;
                            n15 = n33;
                            n5 = n29;
                            n11 = n31;
                            n12 = n35;
                            n13 = n36;
                            continue Label_0646;
                        }
                        if (n30 == n4 + 1) {
                            final int n38 = n21;
                            final int n39 = n25;
                            n16 = n18;
                            final int n40 = n26;
                            final int n41 = n19;
                            final int n42 = n27;
                            final int n43 = k;
                            final int n44 = n24;
                            n17 = n22;
                            d = n41;
                            n2 = n44;
                            n14 = n39;
                            n10 = n28;
                            n15 = n40;
                            n5 = n29;
                            n11 = n38;
                            n12 = n42;
                            n13 = n43;
                            continue Label_0646;
                        }
                        if (n29 == -1) {
                            this.o[n28] = this.n[n30];
                            ++n28;
                            n21 = n30;
                            n29 = n30;
                        }
                        else {
                            int n45;
                            if (n30 >= n26) {
                                this.o[n28] = (byte)n21;
                                ++n28;
                                n45 = n29;
                            }
                            else {
                                n45 = n30;
                            }
                            while (n45 >= n4) {
                                this.o[n28] = this.n[n45];
                                n45 = this.m[n45];
                                ++n28;
                            }
                            final int n46 = this.n[n45] & 0xFF;
                            this.o[n28] = (byte)n46;
                            int n47 = n24;
                            int n48 = n25;
                            int n49;
                            if ((n49 = n26) < 4096) {
                                this.m[n26] = (short)n29;
                                this.n[n26] = (byte)n46;
                                final int n50 = n26 + 1;
                                n47 = n24;
                                n48 = n25;
                                n49 = n50;
                                if ((n50 & n25) == 0x0) {
                                    n47 = n24;
                                    n48 = n25;
                                    if ((n49 = n50) < 4096) {
                                        n47 = n24 + 1;
                                        n48 = n25 + n50;
                                        n49 = n50;
                                    }
                                }
                            }
                            int n51;
                            int n52;
                            byte[] p;
                            byte[] o;
                            for (n51 = n9, n52 = n28 + 1; n52 > 0; --n52, p[n22] = o[n52], ++n51, ++n22) {
                                p = this.p;
                                o = this.o;
                            }
                            n29 = n30;
                            n28 = n52;
                            n9 = n51;
                            n24 = n47;
                            n25 = n48;
                            n26 = n49;
                            n21 = n46;
                        }
                    }
                }
                final int n53 = n21;
                final int n54 = n26;
                final int n55 = n19;
                final int n56 = k;
                final int n57 = n24;
                n17 = n22;
                final int n58 = n25;
                n16 = n18;
                final int n59 = n27;
                d = n55;
                n2 = n57;
                n14 = n58;
                n10 = n28;
                n15 = n54;
                n5 = n29;
                n11 = n53;
                n12 = n59;
                n13 = n56;
            }
            while (j < n3) {
                this.p[j] = 0;
                ++j;
            }
            final int n60 = hg.d / this.v;
            final int n61 = hg.b / this.v;
            final int n62 = hg.c / this.v;
            final int n63 = hg.a / this.v;
            int n64 = 1;
            int n65 = 8;
            n2 = 0;
            boolean b2;
            if (this.a == 0) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            int n71;
            int n72;
            int n74;
            int n95;
            int n96;
            for (int n66 = 0; n66 < n60; ++n66, n95 = n71, n96 = n72, n2 = n74, n65 = n95, n64 = n96) {
                int n73;
                if (hg.e) {
                    int n67 = n2;
                    int n68 = n65;
                    int n69 = n64;
                    if (n2 >= n60) {
                        n69 = n64 + 1;
                        switch (n69) {
                            default: {
                                n68 = n65;
                                n67 = n2;
                                break;
                            }
                            case 2: {
                                n67 = 4;
                                n68 = n65;
                                break;
                            }
                            case 3: {
                                n67 = 2;
                                n68 = 4;
                                break;
                            }
                            case 4: {
                                n67 = 1;
                                n68 = 2;
                                break;
                            }
                        }
                    }
                    final int n70 = n67 + n68;
                    n71 = n68;
                    n72 = n69;
                    n73 = n67;
                    n74 = n70;
                }
                else {
                    n74 = n2;
                    n71 = n65;
                    n72 = n64;
                    n73 = n66;
                }
                final int n75 = n73 + n61;
                if (n75 < this.w) {
                    final int n76 = n75 * this.x;
                    int n77 = n76 + n63;
                    int n78 = n77 + n62;
                    if (this.x + n76 < n78) {
                        n78 = this.x + n76;
                    }
                    int n79 = this.v * n66 * hg.c;
                    final int n80 = n79 + (n78 - n77) * this.v;
                    while (n77 < n78) {
                        int n81;
                        if (this.v == 1) {
                            n81 = this.e[this.p[n79] & 0xFF];
                        }
                        else {
                            final int c3 = hg.c;
                            int n82 = 0;
                            int n83 = 0;
                            int n84 = 0;
                            int n85 = 0;
                            int n86 = 0;
                            int n89;
                            int n90;
                            for (int n87 = n79; n87 < this.v + n79 && n87 < this.p.length && n87 < n80; ++n87, n86 = n5, n85 = n89, n84 = n90, n83 = c2, n82 = n4) {
                                final int n88 = this.e[this.p[n87] & 0xFF];
                                n5 = n86;
                                n89 = n85;
                                n90 = n84;
                                c2 = n83;
                                n4 = n82;
                                if (n88 != 0) {
                                    n4 = n82 + (n88 >> 24 & 0xFF);
                                    c2 = n83 + (n88 >> 16 & 0xFF);
                                    n90 = n84 + (n88 >> 8 & 0xFF);
                                    n89 = n85 + (n88 & 0xFF);
                                    n5 = n86 + 1;
                                }
                            }
                            n3 = n79 + c3;
                            int n91 = n82;
                            int n94;
                            for (int n92 = n3; n92 < n79 + c3 + this.v && n92 < this.p.length && n92 < n80; ++n92, n86 = n3, n85 = n5, n84 = n94, n83 = c2, n91 = n4) {
                                final int n93 = this.e[this.p[n92] & 0xFF];
                                n3 = n86;
                                n5 = n85;
                                n94 = n84;
                                c2 = n83;
                                n4 = n91;
                                if (n93 != 0) {
                                    n4 = n91 + (n93 >> 24 & 0xFF);
                                    c2 = n83 + (n93 >> 16 & 0xFF);
                                    n94 = n84 + (n93 >> 8 & 0xFF);
                                    n5 = n85 + (n93 & 0xFF);
                                    n3 = n86 + 1;
                                }
                            }
                            if (n86 == 0) {
                                n81 = 0;
                            }
                            else {
                                n81 = (n85 / n86 | (n84 / n86 << 8 | (n83 / n86 << 16 | n91 / n86 << 24)));
                            }
                        }
                        if (n81 != 0) {
                            e2[n77] = n81;
                        }
                        else if (!this.y && b2) {
                            this.y = true;
                        }
                        n79 += this.v;
                        ++n77;
                    }
                }
            }
            if (this.t && (hg.g == 0 || hg.g == 1)) {
                if (this.s == null) {
                    this.s = this.e();
                }
                this.s.setPixels(e2, 0, this.x, 0, 0, this.x, this.w);
            }
            Bitmap e = this.e();
            e.setPixels(e2, 0, this.x, 0, 0, this.x, this.w);
            return e;
        }
    }
    
    interface a
    {
        Bitmap a(final int p0, final int p1, final Bitmap$Config p2);
        
        byte[] a(final int p0);
        
        int[] b(final int p0);
    }
}
