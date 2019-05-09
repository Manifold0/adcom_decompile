package com.tapjoy.internal;

import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.io.Serializable;
import java.util.Arrays;

public class hx implements Serializable, Comparable {
    /* renamed from: a */
    static final char[] f8184a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /* renamed from: b */
    public static final hx f8185b = new hx((byte[]) new byte[0].clone());
    /* renamed from: c */
    final byte[] f8186c;
    /* renamed from: d */
    transient int f8187d;
    /* renamed from: e */
    transient String f8188e;

    public /* synthetic */ int compareTo(Object obj) {
        hx hxVar = (hx) obj;
        int c = mo6363c();
        int c2 = hxVar.mo6363c();
        int min = Math.min(c, c2);
        int i = 0;
        while (i < min) {
            int a = mo6357a(i) & 255;
            int a2 = hxVar.mo6357a(i) & 255;
            if (a == a2) {
                i++;
            } else if (a < a2) {
                return -1;
            } else {
                return 1;
            }
        }
        if (c == c2) {
            return 0;
        }
        return c >= c2 ? 1 : -1;
    }

    public hx(byte[] bArr) {
        this.f8186c = bArr;
    }

    /* renamed from: a */
    public String mo6359a() {
        String str = this.f8188e;
        if (str != null) {
            return str;
        }
        str = new String(this.f8186c, ih.f8217a);
        this.f8188e = str;
        return str;
    }

    /* renamed from: b */
    public String mo6362b() {
        int i = 0;
        char[] cArr = new char[(this.f8186c.length * 2)];
        byte[] bArr = this.f8186c;
        int length = bArr.length;
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = i2 + 1;
            cArr[i2] = f8184a[(b >> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = f8184a[b & 15];
            i++;
        }
        return new String(cArr);
    }

    /* renamed from: a */
    public hx mo6358a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        } else if (i2 > this.f8186c.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.f8186c.length + ")");
        } else {
            int i3 = i2 - i;
            if (i3 < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex");
            } else if (i == 0 && i2 == this.f8186c.length) {
                return this;
            } else {
                Object obj = new byte[i3];
                System.arraycopy(this.f8186c, i, obj, 0, i3);
                this(obj);
                return this;
            }
        }
    }

    /* renamed from: a */
    public byte mo6357a(int i) {
        return this.f8186c[i];
    }

    /* renamed from: c */
    public int mo6363c() {
        return this.f8186c.length;
    }

    /* renamed from: d */
    public byte[] mo6364d() {
        return (byte[]) this.f8186c.clone();
    }

    /* renamed from: a */
    void mo6360a(hu huVar) {
        huVar.m8108a(this.f8186c, 0, this.f8186c.length);
    }

    /* renamed from: a */
    public boolean mo6361a(int i, byte[] bArr, int i2, int i3) {
        return i >= 0 && i <= this.f8186c.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && ih.m8188a(this.f8186c, i, bArr, i2, i3);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return (o instanceof hx) && ((hx) o).mo6363c() == this.f8186c.length && ((hx) o).mo6361a(0, this.f8186c, 0, this.f8186c.length);
    }

    public int hashCode() {
        int i = this.f8187d;
        if (i != 0) {
            return i;
        }
        i = Arrays.hashCode(this.f8186c);
        this.f8187d = i;
        return i;
    }

    public String toString() {
        if (this.f8186c.length == 0) {
            return "[size=0]";
        }
        String a = mo6359a();
        int length = a.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length) {
            if (i != 64) {
                int codePointAt = a.codePointAt(i2);
                if ((Character.isISOControl(codePointAt) && codePointAt != 10 && codePointAt != 13) || codePointAt == 65533) {
                    i2 = -1;
                    break;
                }
                i++;
                i2 += Character.charCount(codePointAt);
            } else {
                break;
            }
        }
        i2 = a.length();
        if (i2 != -1) {
            String replace = a.substring(0, i2).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            return i2 < a.length() ? "[size=" + this.f8186c.length + " text=" + replace + "…]" : "[text=" + replace + RequestParameters.RIGHT_BRACKETS;
        } else if (this.f8186c.length <= 64) {
            return "[hex=" + mo6362b() + RequestParameters.RIGHT_BRACKETS;
        } else {
            return "[size=" + this.f8186c.length + " hex=" + mo6358a(0, 64).mo6362b() + "…]";
        }
    }
}
