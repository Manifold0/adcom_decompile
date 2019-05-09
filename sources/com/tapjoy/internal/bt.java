package com.tapjoy.internal;

import com.tapjoy.internal.bs.C2846a;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public final class bt extends bs {
    /* renamed from: a */
    public static final C2846a f7236a = new C28471();
    /* renamed from: b */
    private final co f7237b = new co();
    /* renamed from: c */
    private final Reader f7238c;
    /* renamed from: d */
    private boolean f7239d = false;
    /* renamed from: e */
    private final char[] f7240e = new char[1024];
    /* renamed from: f */
    private int f7241f = 0;
    /* renamed from: g */
    private int f7242g = 0;
    /* renamed from: h */
    private int f7243h = 1;
    /* renamed from: i */
    private int f7244i = 1;
    /* renamed from: j */
    private final List f7245j = new ArrayList();
    /* renamed from: k */
    private bx f7246k;
    /* renamed from: l */
    private String f7247l;
    /* renamed from: m */
    private String f7248m;
    /* renamed from: n */
    private int f7249n;
    /* renamed from: o */
    private int f7250o;
    /* renamed from: p */
    private boolean f7251p;

    /* renamed from: com.tapjoy.internal.bt$1 */
    static class C28471 extends C2846a {
        C28471() {
        }

        /* renamed from: a */
        public final bs mo6188a(Reader reader) {
            return new bt(reader);
        }

        /* renamed from: a */
        public final bs mo6189a(String str) {
            return new bt(new StringReader(str));
        }
    }

    public bt(Reader reader) {
        m7264a(bv.EMPTY_DOCUMENT);
        this.f7251p = false;
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.f7238c = reader;
    }

    /* renamed from: f */
    public final void mo6191f() {
        m7265a(bx.BEGIN_ARRAY);
    }

    /* renamed from: g */
    public final void mo6192g() {
        m7265a(bx.END_ARRAY);
    }

    /* renamed from: h */
    public final void mo6193h() {
        m7265a(bx.BEGIN_OBJECT);
    }

    /* renamed from: i */
    public final void mo6194i() {
        m7265a(bx.END_OBJECT);
    }

    /* renamed from: a */
    private void m7265a(bx bxVar) {
        mo6196k();
        if (this.f7246k != bxVar) {
            throw new IllegalStateException("Expected " + bxVar + " but was " + mo6196k());
        }
        m7271t();
    }

    /* renamed from: j */
    public final boolean mo6195j() {
        mo6196k();
        return (this.f7246k == bx.END_OBJECT || this.f7246k == bx.END_ARRAY) ? false : true;
    }

    /* renamed from: k */
    public final bx mo6196k() {
        if (this.f7246k != null) {
            return this.f7246k;
        }
        bx v;
        switch ((bv) this.f7245j.get(this.f7245j.size() - 1)) {
            case EMPTY_DOCUMENT:
                m7268b(bv.NONEMPTY_DOCUMENT);
                v = m7273v();
                if (this.f7239d || this.f7246k == bx.BEGIN_ARRAY || this.f7246k == bx.BEGIN_OBJECT) {
                    return v;
                }
                throw new IOException("Expected JSON document to start with '[' or '{' but was " + this.f7246k);
            case EMPTY_ARRAY:
                return m7262a(true);
            case NONEMPTY_ARRAY:
                return m7262a(false);
            case EMPTY_OBJECT:
                return m7267b(true);
            case DANGLING_NAME:
                switch (m7276y()) {
                    case 58:
                        break;
                    case 61:
                        m7277z();
                        if ((this.f7241f < this.f7242g || m7266a(1)) && this.f7240e[this.f7241f] == '>') {
                            this.f7241f++;
                            break;
                        }
                    default:
                        throw m7270d("Expected ':'");
                }
                m7268b(bv.NONEMPTY_OBJECT);
                return m7273v();
            case NONEMPTY_OBJECT:
                return m7267b(false);
            case NONEMPTY_DOCUMENT:
                try {
                    v = m7273v();
                    if (this.f7239d) {
                        return v;
                    }
                    throw m7270d("Expected EOF");
                } catch (EOFException e) {
                    v = bx.END_DOCUMENT;
                    this.f7246k = v;
                    return v;
                }
            case CLOSED:
                throw new IllegalStateException("JsonReader is closed");
            default:
                throw new AssertionError();
        }
    }

    /* renamed from: t */
    private bx m7271t() {
        mo6196k();
        bx bxVar = this.f7246k;
        this.f7246k = null;
        this.f7248m = null;
        this.f7247l = null;
        return bxVar;
    }

    /* renamed from: l */
    public final String mo6197l() {
        mo6196k();
        if (this.f7246k != bx.NAME) {
            throw new IllegalStateException("Expected a name but was " + mo6196k());
        }
        String str = this.f7247l;
        m7271t();
        return str;
    }

    /* renamed from: m */
    public final String mo6198m() {
        mo6196k();
        if (this.f7246k == bx.STRING || this.f7246k == bx.NUMBER) {
            String str = this.f7248m;
            m7271t();
            return str;
        }
        throw new IllegalStateException("Expected a string but was " + mo6196k());
    }

    /* renamed from: n */
    public final boolean mo6199n() {
        mo6196k();
        if (this.f7246k != bx.BOOLEAN) {
            throw new IllegalStateException("Expected a boolean but was " + this.f7246k);
        }
        boolean z = this.f7248m == "true";
        m7271t();
        return z;
    }

    /* renamed from: o */
    public final void mo6200o() {
        mo6196k();
        if (this.f7246k != bx.NULL) {
            throw new IllegalStateException("Expected null but was " + this.f7246k);
        }
        m7271t();
    }

    /* renamed from: p */
    public final double mo6201p() {
        mo6196k();
        if (this.f7246k == bx.STRING || this.f7246k == bx.NUMBER) {
            double parseDouble = Double.parseDouble(this.f7248m);
            m7271t();
            return parseDouble;
        }
        throw new IllegalStateException("Expected a double but was " + this.f7246k);
    }

    /* renamed from: q */
    public final long mo6202q() {
        mo6196k();
        if (this.f7246k == bx.STRING || this.f7246k == bx.NUMBER) {
            long parseLong;
            try {
                parseLong = Long.parseLong(this.f7248m);
            } catch (NumberFormatException e) {
                double parseDouble = Double.parseDouble(this.f7248m);
                parseLong = (long) parseDouble;
                if (((double) parseLong) != parseDouble) {
                    throw new NumberFormatException(this.f7248m);
                }
            }
            m7271t();
            return parseLong;
        }
        throw new IllegalStateException("Expected a long but was " + this.f7246k);
    }

    /* renamed from: r */
    public final int mo6203r() {
        int parseInt;
        mo6196k();
        if (this.f7246k == bx.STRING || this.f7246k == bx.NUMBER) {
            try {
                parseInt = Integer.parseInt(this.f7248m);
            } catch (NumberFormatException e) {
                double parseDouble = Double.parseDouble(this.f7248m);
                parseInt = (int) parseDouble;
                if (((double) parseInt) != parseDouble) {
                    throw new NumberFormatException(this.f7248m);
                }
            }
            m7271t();
            return parseInt;
        }
        throw new IllegalStateException("Expected an int but was " + this.f7246k);
    }

    public final void close() {
        this.f7248m = null;
        this.f7246k = null;
        this.f7245j.clear();
        this.f7245j.add(bv.CLOSED);
        this.f7238c.close();
    }

    /* renamed from: s */
    public final void mo6204s() {
        mo6196k();
        if (this.f7246k == bx.END_ARRAY || this.f7246k == bx.END_OBJECT) {
            throw new IllegalStateException("Expected a value but was " + this.f7246k);
        }
        this.f7251p = true;
        int i = 0;
        while (true) {
            try {
                bx t = m7271t();
                if (t == bx.BEGIN_ARRAY || t == bx.BEGIN_OBJECT) {
                    i++;
                    continue;
                } else if (t == bx.END_ARRAY || t == bx.END_OBJECT) {
                    i--;
                    continue;
                }
                if (i == 0) {
                    break;
                }
            } finally {
                this.f7251p = false;
            }
        }
    }

    /* renamed from: u */
    private bv m7272u() {
        return (bv) this.f7245j.remove(this.f7245j.size() - 1);
    }

    /* renamed from: a */
    private void m7264a(bv bvVar) {
        this.f7245j.add(bvVar);
    }

    /* renamed from: b */
    private void m7268b(bv bvVar) {
        this.f7245j.set(this.f7245j.size() - 1, bvVar);
    }

    /* renamed from: a */
    private bx m7262a(boolean z) {
        bx bxVar;
        if (z) {
            m7268b(bv.NONEMPTY_ARRAY);
        } else {
            switch (m7276y()) {
                case 44:
                    break;
                case 59:
                    m7277z();
                    break;
                case 93:
                    m7272u();
                    bxVar = bx.END_ARRAY;
                    this.f7246k = bxVar;
                    return bxVar;
                default:
                    throw m7270d("Unterminated array");
            }
        }
        switch (m7276y()) {
            case 44:
            case 59:
                break;
            case 93:
                if (z) {
                    m7272u();
                    bxVar = bx.END_ARRAY;
                    this.f7246k = bxVar;
                    return bxVar;
                }
                break;
            default:
                this.f7241f--;
                return m7273v();
        }
        m7277z();
        this.f7241f--;
        this.f7248m = "null";
        bxVar = bx.NULL;
        this.f7246k = bxVar;
        return bxVar;
    }

    /* renamed from: b */
    private bx m7267b(boolean z) {
        bx bxVar;
        if (z) {
            switch (m7276y()) {
                case 125:
                    m7272u();
                    bxVar = bx.END_OBJECT;
                    this.f7246k = bxVar;
                    return bxVar;
                default:
                    this.f7241f--;
                    break;
            }
        }
        switch (m7276y()) {
            case 44:
            case 59:
                break;
            case 125:
                m7272u();
                bxVar = bx.END_OBJECT;
                this.f7246k = bxVar;
                return bxVar;
            default:
                throw m7270d("Unterminated object");
        }
        int y = m7276y();
        switch (y) {
            case 34:
                break;
            case 39:
                m7277z();
                break;
            default:
                m7277z();
                this.f7241f--;
                this.f7247l = m7269c(false);
                if (this.f7247l.length() == 0) {
                    throw m7270d("Expected name");
                }
                break;
        }
        this.f7247l = m7263a((char) y);
        m7268b(bv.DANGLING_NAME);
        bxVar = bx.NAME;
        this.f7246k = bxVar;
        return bxVar;
    }

    /* renamed from: v */
    private bx m7273v() {
        bx bxVar;
        int y = m7276y();
        switch (y) {
            case 34:
                break;
            case 39:
                m7277z();
                break;
            case 91:
                m7264a(bv.EMPTY_ARRAY);
                bxVar = bx.BEGIN_ARRAY;
                this.f7246k = bxVar;
                return bxVar;
            case 123:
                m7264a(bv.EMPTY_OBJECT);
                bxVar = bx.BEGIN_OBJECT;
                this.f7246k = bxVar;
                return bxVar;
            default:
                this.f7241f--;
                this.f7248m = m7269c(true);
                if (this.f7250o == 0) {
                    throw m7270d("Expected literal value");
                }
                if (this.f7249n != -1) {
                    if (this.f7250o == 4 && (('n' == this.f7240e[this.f7249n] || 'N' == this.f7240e[this.f7249n]) && (('u' == this.f7240e[this.f7249n + 1] || 'U' == this.f7240e[this.f7249n + 1]) && (('l' == this.f7240e[this.f7249n + 2] || 'L' == this.f7240e[this.f7249n + 2]) && ('l' == this.f7240e[this.f7249n + 3] || 'L' == this.f7240e[this.f7249n + 3]))))) {
                        this.f7248m = "null";
                        bxVar = bx.NULL;
                        this.f7246k = bxVar;
                        if (this.f7246k == bx.STRING) {
                            m7277z();
                        }
                        return this.f7246k;
                    } else if (this.f7250o == 4 && (('t' == this.f7240e[this.f7249n] || 'T' == this.f7240e[this.f7249n]) && (('r' == this.f7240e[this.f7249n + 1] || 'R' == this.f7240e[this.f7249n + 1]) && (('u' == this.f7240e[this.f7249n + 2] || 'U' == this.f7240e[this.f7249n + 2]) && ('e' == this.f7240e[this.f7249n + 3] || 'E' == this.f7240e[this.f7249n + 3]))))) {
                        this.f7248m = "true";
                        bxVar = bx.BOOLEAN;
                        this.f7246k = bxVar;
                        if (this.f7246k == bx.STRING) {
                            m7277z();
                        }
                        return this.f7246k;
                    } else if (this.f7250o == 5 && (('f' == this.f7240e[this.f7249n] || 'F' == this.f7240e[this.f7249n]) && (('a' == this.f7240e[this.f7249n + 1] || 'A' == this.f7240e[this.f7249n + 1]) && (('l' == this.f7240e[this.f7249n + 2] || 'L' == this.f7240e[this.f7249n + 2]) && (('s' == this.f7240e[this.f7249n + 3] || 'S' == this.f7240e[this.f7249n + 3]) && ('e' == this.f7240e[this.f7249n + 4] || 'E' == this.f7240e[this.f7249n + 4])))))) {
                        this.f7248m = "false";
                        bxVar = bx.BOOLEAN;
                        this.f7246k = bxVar;
                        if (this.f7246k == bx.STRING) {
                            m7277z();
                        }
                        return this.f7246k;
                    } else {
                        int i;
                        this.f7248m = this.f7237b.m7333a(this.f7240e, this.f7249n, this.f7250o);
                        char[] cArr = this.f7240e;
                        int i2 = this.f7249n;
                        int i3 = this.f7250o;
                        char c = cArr[i2];
                        if (c == '-') {
                            i = i2 + 1;
                            c = cArr[i];
                        } else {
                            i = i2;
                        }
                        if (c == '0') {
                            i++;
                            c = cArr[i];
                        } else if (c < '1' || c > '9') {
                            bxVar = bx.STRING;
                            this.f7246k = bxVar;
                            if (this.f7246k == bx.STRING) {
                                m7277z();
                            }
                            return this.f7246k;
                        } else {
                            i++;
                            c = cArr[i];
                            while (c >= '0' && c <= '9') {
                                i++;
                                c = cArr[i];
                            }
                        }
                        if (c == '.') {
                            i++;
                            c = cArr[i];
                            while (c >= '0' && c <= '9') {
                                i++;
                                c = cArr[i];
                            }
                        }
                        char c2 = c;
                        y = i;
                        char c3 = c2;
                        if (c3 == 'e' || c3 == 'E') {
                            i = y + 1;
                            c = cArr[i];
                            if (c == '+' || c == '-') {
                                i++;
                                c = cArr[i];
                            }
                            if (c < '0' || c > '9') {
                                bxVar = bx.STRING;
                                this.f7246k = bxVar;
                                if (this.f7246k == bx.STRING) {
                                    m7277z();
                                }
                                return this.f7246k;
                            }
                            i++;
                            y = i;
                            c3 = cArr[i];
                            while (c3 >= '0' && c3 <= '9') {
                                i = y + 1;
                                y = i;
                                c3 = cArr[i];
                            }
                        }
                        if (y == i2 + i3) {
                            bxVar = bx.NUMBER;
                            this.f7246k = bxVar;
                            if (this.f7246k == bx.STRING) {
                                m7277z();
                            }
                            return this.f7246k;
                        }
                    }
                }
                bxVar = bx.STRING;
                this.f7246k = bxVar;
                if (this.f7246k == bx.STRING) {
                    m7277z();
                }
                return this.f7246k;
        }
        this.f7248m = m7263a((char) y);
        bxVar = bx.STRING;
        this.f7246k = bxVar;
        return bxVar;
    }

    /* renamed from: a */
    private boolean m7266a(int i) {
        int i2;
        for (i2 = 0; i2 < this.f7241f; i2++) {
            if (this.f7240e[i2] == '\n') {
                this.f7243h++;
                this.f7244i = 1;
            } else {
                this.f7244i++;
            }
        }
        if (this.f7242g != this.f7241f) {
            this.f7242g -= this.f7241f;
            System.arraycopy(this.f7240e, this.f7241f, this.f7240e, 0, this.f7242g);
        } else {
            this.f7242g = 0;
        }
        this.f7241f = 0;
        do {
            i2 = this.f7238c.read(this.f7240e, this.f7242g, this.f7240e.length - this.f7242g);
            if (i2 == -1) {
                return false;
            }
            this.f7242g = i2 + this.f7242g;
            if (this.f7243h == 1 && this.f7244i == 1 && this.f7242g > 0 && this.f7240e[0] == '﻿') {
                this.f7241f++;
                this.f7244i--;
            }
        } while (this.f7242g < i);
        return true;
    }

    /* renamed from: w */
    private int m7274w() {
        int i = this.f7243h;
        for (int i2 = 0; i2 < this.f7241f; i2++) {
            if (this.f7240e[i2] == '\n') {
                i++;
            }
        }
        return i;
    }

    /* renamed from: x */
    private int m7275x() {
        int i = this.f7244i;
        for (int i2 = 0; i2 < this.f7241f; i2++) {
            if (this.f7240e[i2] == '\n') {
                i = 1;
            } else {
                i++;
            }
        }
        return i;
    }

    /* renamed from: y */
    private int m7276y() {
        while (true) {
            if (this.f7241f < this.f7242g || m7266a(1)) {
                char[] cArr = this.f7240e;
                int i = this.f7241f;
                this.f7241f = i + 1;
                char c = cArr[i];
                switch (c) {
                    case '\t':
                    case '\n':
                    case '\r':
                    case ' ':
                        break;
                    case '#':
                        m7277z();
                        m7261A();
                        continue;
                    case '/':
                        if (this.f7241f == this.f7242g && !m7266a(1)) {
                            break;
                        }
                        m7277z();
                        switch (this.f7240e[this.f7241f]) {
                            case '*':
                                this.f7241f++;
                                String str = "*/";
                                while (true) {
                                    int i2;
                                    if (this.f7241f + str.length() <= this.f7242g || m7266a(str.length())) {
                                        i2 = 0;
                                        while (i2 < str.length()) {
                                            if (this.f7240e[this.f7241f + i2] == str.charAt(i2)) {
                                                i2++;
                                            } else {
                                                this.f7241f++;
                                            }
                                        }
                                        i2 = 1;
                                    } else {
                                        i2 = 0;
                                    }
                                    if (i2 == 0) {
                                        throw m7270d("Unterminated comment");
                                    }
                                    this.f7241f += 2;
                                    continue;
                                    continue;
                                }
                            case '/':
                                this.f7241f++;
                                m7261A();
                                continue;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
                return c;
            }
            throw new EOFException("End of input");
        }
    }

    /* renamed from: z */
    private void m7277z() {
        if (!this.f7239d) {
            throw m7270d("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    /* renamed from: A */
    private void m7261A() {
        char c;
        do {
            if (this.f7241f < this.f7242g || m7266a(1)) {
                char[] cArr = this.f7240e;
                int i = this.f7241f;
                this.f7241f = i + 1;
                c = cArr[i];
                if (c == '\r') {
                    return;
                }
            } else {
                return;
            }
        } while (c != '\n');
    }

    /* renamed from: a */
    private String m7263a(char c) {
        StringBuilder stringBuilder = null;
        do {
            int i = this.f7241f;
            while (this.f7241f < this.f7242g) {
                char[] cArr = this.f7240e;
                int i2 = this.f7241f;
                this.f7241f = i2 + 1;
                char c2 = cArr[i2];
                if (c2 != c) {
                    StringBuilder stringBuilder2;
                    int i3;
                    int i4;
                    if (c2 == '\\') {
                        if (stringBuilder == null) {
                            stringBuilder = new StringBuilder();
                        }
                        stringBuilder.append(this.f7240e, i, (this.f7241f - i) - 1);
                        if (this.f7241f != this.f7242g || m7266a(1)) {
                            char[] cArr2 = this.f7240e;
                            int i5 = this.f7241f;
                            this.f7241f = i5 + 1;
                            char c3 = cArr2[i5];
                            switch (c3) {
                                case 'b':
                                    c3 = '\b';
                                    break;
                                case 'f':
                                    c3 = '\f';
                                    break;
                                case 'n':
                                    c3 = '\n';
                                    break;
                                case 'r':
                                    c3 = '\r';
                                    break;
                                case 't':
                                    c3 = '\t';
                                    break;
                                case 'u':
                                    if (this.f7241f + 4 <= this.f7242g || m7266a(4)) {
                                        String a = this.f7237b.m7333a(this.f7240e, this.f7241f, 4);
                                        this.f7241f += 4;
                                        c3 = (char) Integer.parseInt(a, 16);
                                        break;
                                    }
                                    throw m7270d("Unterminated escape sequence");
                            }
                            stringBuilder.append(c3);
                            stringBuilder2 = stringBuilder;
                            i3 = this.f7241f;
                        } else {
                            throw m7270d("Unterminated escape sequence");
                        }
                    }
                    i4 = i;
                    stringBuilder2 = stringBuilder;
                    i3 = i4;
                    i4 = i3;
                    stringBuilder = stringBuilder2;
                    i = i4;
                } else if (this.f7251p) {
                    return "skipped!";
                } else {
                    if (stringBuilder == null) {
                        return this.f7237b.m7333a(this.f7240e, i, (this.f7241f - i) - 1);
                    }
                    stringBuilder.append(this.f7240e, i, (this.f7241f - i) - 1);
                    return stringBuilder.toString();
                }
            }
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder();
            }
            stringBuilder.append(this.f7240e, i, this.f7241f - i);
        } while (m7266a(1));
        throw m7270d("Unterminated string");
    }

    /* renamed from: c */
    private String m7269c(boolean z) {
        String str = null;
        this.f7249n = -1;
        this.f7250o = 0;
        int i = 0;
        StringBuilder stringBuilder = null;
        while (true) {
            if (this.f7241f + i < this.f7242g) {
                switch (this.f7240e[this.f7241f + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        m7277z();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i >= this.f7240e.length) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder();
                }
                stringBuilder.append(this.f7240e, this.f7241f, i);
                this.f7250o += i;
                this.f7241f = i + this.f7241f;
                if (m7266a(1)) {
                    i = 0;
                } else {
                    i = 0;
                }
            } else if (!m7266a(i + 1)) {
                this.f7240e[this.f7242g] = '\u0000';
            }
            if (z && stringBuilder == null) {
                this.f7249n = this.f7241f;
            } else if (this.f7251p) {
                str = "skipped!";
            } else if (stringBuilder == null) {
                str = this.f7237b.m7333a(this.f7240e, this.f7241f, i);
            } else {
                stringBuilder.append(this.f7240e, this.f7241f, i);
                str = stringBuilder.toString();
            }
            this.f7250o += i;
            this.f7241f += i;
            return str;
        }
    }

    public final String toString() {
        StringBuilder append = new StringBuilder().append(getClass().getSimpleName()).append(" near ");
        StringBuilder stringBuilder = new StringBuilder();
        int min = Math.min(this.f7241f, 20);
        stringBuilder.append(this.f7240e, this.f7241f - min, min);
        stringBuilder.append(this.f7240e, this.f7241f, Math.min(this.f7242g - this.f7241f, 20));
        return append.append(stringBuilder).toString();
    }

    /* renamed from: d */
    private IOException m7270d(String str) {
        throw new bz(str + " at line " + m7274w() + " column " + m7275x());
    }
}
