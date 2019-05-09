// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.StringReader;
import java.util.List;
import java.io.Reader;

public final class bt extends bs
{
    public static final a a;
    private final co b;
    private final Reader c;
    private boolean d;
    private final char[] e;
    private int f;
    private int g;
    private int h;
    private int i;
    private final List j;
    private bx k;
    private String l;
    private String m;
    private int n;
    private int o;
    private boolean p;
    
    static {
        a = new a() {
            @Override
            public final bs a(final Reader reader) {
                return new bt(reader);
            }
            
            @Override
            public final bs a(final String s) {
                return new bt(new StringReader(s));
            }
        };
    }
    
    public bt(final Reader c) {
        this.b = new co();
        this.d = false;
        this.e = new char[1024];
        this.f = 0;
        this.g = 0;
        this.h = 1;
        this.i = 1;
        this.j = new ArrayList();
        this.a(bv.f);
        this.p = false;
        if (c == null) {
            throw new NullPointerException("in == null");
        }
        this.c = c;
    }
    
    private void A() {
        while (this.f < this.g || this.a(1)) {
            final char c = this.e[this.f++];
            if (c == '\r' || c == '\n') {
                break;
            }
        }
    }
    
    private bx a(final boolean b) {
        if (b) {
            this.b(bv.b);
        }
        else {
            switch (this.y()) {
                case 59: {
                    this.z();
                }
                case 44: {
                    break;
                }
                default: {
                    throw this.d("Unterminated array");
                }
                case 93: {
                    this.u();
                    return this.k = bx.b;
                }
            }
        }
        switch (this.y()) {
            default: {
                --this.f;
                return this.v();
            }
            case 93: {
                if (b) {
                    this.u();
                    return this.k = bx.b;
                }
            }
            case 44:
            case 59: {
                this.z();
                --this.f;
                this.m = "null";
                return this.k = bx.i;
            }
        }
    }
    
    private String a(final char c) {
        StringBuilder sb = null;
        while (true) {
            int n = this.f;
            while (this.f < this.g) {
                final char c2 = this.e[this.f++];
                if (c2 == c) {
                    if (this.p) {
                        return "skipped!";
                    }
                    if (sb == null) {
                        return this.b.a(this.e, n, this.f - n - 1);
                    }
                    sb.append(this.e, n, this.f - n - 1);
                    return sb.toString();
                }
                else {
                    if (c2 != '\\') {
                        continue;
                    }
                    StringBuilder sb2;
                    if ((sb2 = sb) == null) {
                        sb2 = new StringBuilder();
                    }
                    sb2.append(this.e, n, this.f - n - 1);
                    if (this.f == this.g && !this.a(1)) {
                        throw this.d("Unterminated escape sequence");
                    }
                    char c3 = this.e[this.f++];
                    switch (c3) {
                        case 'u': {
                            if (this.f + 4 > this.g && !this.a(4)) {
                                throw this.d("Unterminated escape sequence");
                            }
                            final String a = this.b.a(this.e, this.f, 4);
                            this.f += 4;
                            c3 = (char)Integer.parseInt(a, 16);
                            break;
                        }
                        case 't': {
                            c3 = '\t';
                            break;
                        }
                        case 'b': {
                            c3 = '\b';
                            break;
                        }
                        case 'n': {
                            c3 = '\n';
                            break;
                        }
                        case 'r': {
                            c3 = '\r';
                            break;
                        }
                        case 'f': {
                            c3 = '\f';
                            break;
                        }
                    }
                    sb2.append(c3);
                    n = this.f;
                    sb = sb2;
                }
            }
            StringBuilder sb3;
            if ((sb3 = sb) == null) {
                sb3 = new StringBuilder();
            }
            sb3.append(this.e, n, this.f - n);
            sb = sb3;
            if (!this.a(1)) {
                throw this.d("Unterminated string");
            }
        }
    }
    
    private void a(final bv bv) {
        this.j.add(bv);
    }
    
    private void a(final bx bx) {
        this.k();
        if (this.k != bx) {
            throw new IllegalStateException("Expected " + bx + " but was " + this.k());
        }
        this.t();
    }
    
    private boolean a(final int n) {
        final boolean b = false;
        for (int i = 0; i < this.f; ++i) {
            if (this.e[i] == '\n') {
                ++this.h;
                this.i = 1;
            }
            else {
                ++this.i;
            }
        }
        if (this.g != this.f) {
            this.g -= this.f;
            System.arraycopy(this.e, this.f, this.e, 0, this.g);
        }
        else {
            this.g = 0;
        }
        this.f = 0;
        do {
            final int read = this.c.read(this.e, this.g, this.e.length - this.g);
            final boolean b2 = b;
            if (read == -1) {
                return b2;
            }
            this.g += read;
            if (this.h != 1 || this.i != 1 || this.g <= 0 || this.e[0] != '\ufeff') {
                continue;
            }
            ++this.f;
            --this.i;
        } while (this.g < n);
        return true;
    }
    
    private bx b(final boolean b) {
        if (b) {
            switch (this.y()) {
                default: {
                    --this.f;
                    break;
                }
                case 125: {
                    this.u();
                    return this.k = bx.d;
                }
            }
        }
        else {
            switch (this.y()) {
                case 44:
                case 59: {
                    break;
                }
                default: {
                    throw this.d("Unterminated object");
                }
                case 125: {
                    this.u();
                    return this.k = bx.d;
                }
            }
        }
        final int y = this.y();
        switch (y) {
            default: {
                this.z();
                --this.f;
                this.l = this.c(false);
                if (this.l.length() == 0) {
                    throw this.d("Expected name");
                }
                break;
            }
            case 39: {
                this.z();
            }
            case 34: {
                this.l = this.a((char)y);
                break;
            }
        }
        this.b(bv.d);
        return this.k = bx.e;
    }
    
    private void b(final bv bv) {
        this.j.set(this.j.size() - 1, bv);
    }
    
    private String c(final boolean b) {
        final String s = null;
        this.n = -1;
        this.o = 0;
        int n = 0;
        StringBuilder sb = null;
    Label_0205:
        while (true) {
            Block_7: {
                Block_5: {
                    StringBuilder sb2;
                    int n2;
                    while (true) {
                        if (this.f + n < this.g) {
                            sb2 = sb;
                            n2 = n;
                            switch (this.e[this.f + n]) {
                                default: {
                                    ++n;
                                    continue;
                                }
                                case '#':
                                case '/':
                                case ';':
                                case '=':
                                case '\\': {
                                    this.z();
                                    n2 = n;
                                    sb2 = sb;
                                }
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
                                case '}': {
                                    break Label_0205;
                                }
                            }
                        }
                        else if (n < this.e.length) {
                            if (!this.a(n + 1)) {
                                break Block_5;
                            }
                            continue;
                        }
                        else {
                            if ((sb2 = sb) == null) {
                                sb2 = new StringBuilder();
                            }
                            sb2.append(this.e, this.f, n);
                            this.o += n;
                            this.f += n;
                            if (!this.a(1)) {
                                break Block_7;
                            }
                            n = 0;
                            sb = sb2;
                        }
                    }
                    String s2;
                    if (b && sb2 == null) {
                        this.n = this.f;
                        s2 = s;
                    }
                    else if (this.p) {
                        s2 = "skipped!";
                    }
                    else if (sb2 == null) {
                        s2 = this.b.a(this.e, this.f, n2);
                    }
                    else {
                        sb2.append(this.e, this.f, n2);
                        s2 = sb2.toString();
                    }
                    this.o += n2;
                    this.f += n2;
                    return s2;
                }
                this.e[this.g] = '\0';
                StringBuilder sb2 = sb;
                int n2 = n;
                continue Label_0205;
            }
            int n2 = 0;
            continue Label_0205;
        }
    }
    
    private IOException d(final String s) {
        throw new bz(s + " at line " + this.w() + " column " + this.x());
    }
    
    private bx t() {
        this.k();
        final bx k = this.k;
        this.k = null;
        this.m = null;
        this.l = null;
        return k;
    }
    
    private bv u() {
        return this.j.remove(this.j.size() - 1);
    }
    
    private bx v() {
        final int y = this.y();
        switch (y) {
            default: {
                --this.f;
                this.m = this.c(true);
                if (this.o == 0) {
                    throw this.d("Expected literal value");
                }
                while (true) {
                    Label_0981: {
                        if (this.n == -1) {
                            break Label_0981;
                        }
                        bx k;
                        if (this.o == 4 && ('n' == this.e[this.n] || 'N' == this.e[this.n]) && ('u' == this.e[this.n + 1] || 'U' == this.e[this.n + 1]) && ('l' == this.e[this.n + 2] || 'L' == this.e[this.n + 2]) && ('l' == this.e[this.n + 3] || 'L' == this.e[this.n + 3])) {
                            this.m = "null";
                            k = bx.i;
                        }
                        else if (this.o == 4 && ('t' == this.e[this.n] || 'T' == this.e[this.n]) && ('r' == this.e[this.n + 1] || 'R' == this.e[this.n + 1]) && ('u' == this.e[this.n + 2] || 'U' == this.e[this.n + 2]) && ('e' == this.e[this.n + 3] || 'E' == this.e[this.n + 3])) {
                            this.m = "true";
                            k = bx.h;
                        }
                        else if (this.o == 5 && ('f' == this.e[this.n] || 'F' == this.e[this.n]) && ('a' == this.e[this.n + 1] || 'A' == this.e[this.n + 1]) && ('l' == this.e[this.n + 2] || 'L' == this.e[this.n + 2]) && ('s' == this.e[this.n + 3] || 'S' == this.e[this.n + 3]) && ('e' == this.e[this.n + 4] || 'E' == this.e[this.n + 4])) {
                            this.m = "false";
                            k = bx.h;
                        }
                        else {
                            this.m = this.b.a(this.e, this.n, this.o);
                            final char[] e = this.e;
                            final int n = this.n;
                            final int o = this.o;
                            char c = e[n];
                            int n2;
                            if (c == '-') {
                                n2 = n + 1;
                                c = e[n2];
                            }
                            else {
                                n2 = n;
                            }
                            int n3;
                            char c2;
                            if (c == '0') {
                                n3 = n2 + 1;
                                c2 = e[n3];
                            }
                            else {
                                if (c < '1' || c > '9') {
                                    k = bx.f;
                                    break Label_0302;
                                }
                                int n4 = n2 + 1;
                                char c3 = e[n4];
                                while (true) {
                                    c2 = c3;
                                    n3 = n4;
                                    if (c3 < '0') {
                                        break;
                                    }
                                    c2 = c3;
                                    n3 = n4;
                                    if (c3 > '9') {
                                        break;
                                    }
                                    ++n4;
                                    c3 = e[n4];
                                }
                            }
                            char c4 = c2;
                            int n5 = n3;
                            if (c2 == '.') {
                                int n6 = n3 + 1;
                                char c5 = e[n6];
                                while (true) {
                                    c4 = c5;
                                    n5 = n6;
                                    if (c5 < '0') {
                                        break;
                                    }
                                    c4 = c5;
                                    n5 = n6;
                                    if (c5 > '9') {
                                        break;
                                    }
                                    ++n6;
                                    c5 = e[n6];
                                }
                            }
                            int n7 = 0;
                            Label_0964: {
                                if (c4 != 'e') {
                                    n7 = n5;
                                    if (c4 != 'E') {
                                        break Label_0964;
                                    }
                                }
                                final int n8 = n5 + 1;
                                final char c6 = e[n8];
                                char c7 = '\0';
                                int n9 = 0;
                                Label_0907: {
                                    if (c6 != '+') {
                                        c7 = c6;
                                        n9 = n8;
                                        if (c6 != '-') {
                                            break Label_0907;
                                        }
                                    }
                                    n9 = n8 + 1;
                                    c7 = e[n9];
                                }
                                if (c7 < '0' || c7 > '9') {
                                    k = bx.f;
                                    break Label_0302;
                                }
                                int n10 = n9 + 1;
                                char c8 = e[n10];
                                while (true) {
                                    n7 = n10;
                                    if (c8 < '0') {
                                        break;
                                    }
                                    n7 = n10;
                                    if (c8 > '9') {
                                        break;
                                    }
                                    ++n10;
                                    c8 = e[n10];
                                }
                            }
                            if (n7 != n + o) {
                                break Label_0981;
                            }
                            k = bx.g;
                        }
                        this.k = k;
                        if (this.k == bx.f) {
                            this.z();
                        }
                        return this.k;
                    }
                    bx k = bx.f;
                    continue;
                }
            }
            case 123: {
                this.a(bv.c);
                return this.k = bx.c;
            }
            case 91: {
                this.a(bv.a);
                return this.k = bx.a;
            }
            case 39: {
                this.z();
            }
            case 34: {
                this.m = this.a((char)y);
                return this.k = bx.f;
            }
        }
    }
    
    private int w() {
        int h = this.h;
        int n;
        for (int i = 0; i < this.f; ++i, h = n) {
            n = h;
            if (this.e[i] == '\n') {
                n = h + 1;
            }
        }
        return h;
    }
    
    private int x() {
        int i = this.i;
        for (int j = 0; j < this.f; ++j) {
            if (this.e[j] == '\n') {
                i = 1;
            }
            else {
                ++i;
            }
        }
        return i;
    }
    
    private int y() {
    Label_0000:
        while (this.f < this.g || this.a(1)) {
            final char c = this.e[this.f++];
            switch (c) {
                case 35: {
                    this.z();
                    this.A();
                }
                case 9:
                case 10:
                case 13:
                case 32: {
                    continue;
                }
                case 47: {
                    if (this.f == this.g && !this.a(1)) {
                        break;
                    }
                    this.z();
                    switch (this.e[this.f]) {
                        default: {
                            return c;
                        }
                        case '*': {
                            ++this.f;
                        Label_0172:
                            while (true) {
                                while (this.f + "*/".length() <= this.g || this.a("*/".length())) {
                                    for (int i = 0; i < "*/".length(); ++i) {
                                        if (this.e[this.f + i] != "*/".charAt(i)) {
                                            ++this.f;
                                            continue Label_0172;
                                        }
                                    }
                                    final int n = 1;
                                    if (n == 0) {
                                        throw this.d("Unterminated comment");
                                    }
                                    this.f += 2;
                                    continue Label_0000;
                                }
                                final int n = 0;
                                continue;
                            }
                        }
                        case '/': {
                            ++this.f;
                            this.A();
                            continue;
                        }
                    }
                    break;
                }
            }
            return c;
        }
        throw new EOFException("End of input");
    }
    
    private void z() {
        if (!this.d) {
            throw this.d("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }
    
    @Override
    public final void close() {
        this.m = null;
        this.k = null;
        this.j.clear();
        this.j.add(bv.h);
        this.c.close();
    }
    
    @Override
    public final void f() {
        this.a(bx.a);
    }
    
    @Override
    public final void g() {
        this.a(bx.b);
    }
    
    @Override
    public final void h() {
        this.a(bx.c);
    }
    
    @Override
    public final void i() {
        this.a(bx.d);
    }
    
    @Override
    public final boolean j() {
        this.k();
        return this.k != bx.d && this.k != bx.b;
    }
    
    @Override
    public final bx k() {
        bx bx = null;
        if (this.k != null) {
            bx = this.k;
        }
        else {
            switch (bt$2.a[this.j.get(this.j.size() - 1).ordinal()]) {
                default: {
                    throw new AssertionError();
                }
                case 1: {
                    this.b(bv.g);
                    final bx bx2 = bx = this.v();
                    if (this.d) {
                        break;
                    }
                    bx = bx2;
                    if (this.k == com.tapjoy.internal.bx.a) {
                        break;
                    }
                    bx = bx2;
                    if (this.k != com.tapjoy.internal.bx.c) {
                        throw new IOException("Expected JSON document to start with '[' or '{' but was " + this.k);
                    }
                    break;
                }
                case 2: {
                    return this.a(true);
                }
                case 3: {
                    return this.a(false);
                }
                case 4: {
                    return this.b(true);
                }
                case 5: {
                    switch (this.y()) {
                        default: {
                            throw this.d("Expected ':'");
                        }
                        case 61: {
                            this.z();
                            if ((this.f < this.g || this.a(1)) && this.e[this.f] == '>') {
                                ++this.f;
                            }
                        }
                        case 58: {
                            this.b(bv.e);
                            return this.v();
                        }
                    }
                    break;
                }
                case 6: {
                    return this.b(false);
                }
                case 7: {
                    try {
                        bx = this.v();
                        if (!this.d) {
                            throw this.d("Expected EOF");
                        }
                        break;
                    }
                    catch (EOFException ex) {
                        return this.k = com.tapjoy.internal.bx.j;
                    }
                }
                case 8: {
                    throw new IllegalStateException("JsonReader is closed");
                }
            }
        }
        return bx;
    }
    
    @Override
    public final String l() {
        this.k();
        if (this.k != bx.e) {
            throw new IllegalStateException("Expected a name but was " + this.k());
        }
        final String l = this.l;
        this.t();
        return l;
    }
    
    @Override
    public final String m() {
        this.k();
        if (this.k != bx.f && this.k != bx.g) {
            throw new IllegalStateException("Expected a string but was " + this.k());
        }
        final String m = this.m;
        this.t();
        return m;
    }
    
    @Override
    public final boolean n() {
        this.k();
        if (this.k != bx.h) {
            throw new IllegalStateException("Expected a boolean but was " + this.k);
        }
        final boolean b = this.m == "true";
        this.t();
        return b;
    }
    
    @Override
    public final void o() {
        this.k();
        if (this.k != bx.i) {
            throw new IllegalStateException("Expected null but was " + this.k);
        }
        this.t();
    }
    
    @Override
    public final double p() {
        this.k();
        if (this.k != bx.f && this.k != bx.g) {
            throw new IllegalStateException("Expected a double but was " + this.k);
        }
        final double double1 = Double.parseDouble(this.m);
        this.t();
        return double1;
    }
    
    @Override
    public final long q() {
        this.k();
        if (this.k != bx.f && this.k != bx.g) {
            throw new IllegalStateException("Expected a long but was " + this.k);
        }
        while (true) {
            try {
                final long long1 = Long.parseLong(this.m);
                this.t();
                return long1;
            }
            catch (NumberFormatException ex) {
                final double double1 = Double.parseDouble(this.m);
                final long long1;
                if ((long1 = (long)double1) != double1) {
                    throw new NumberFormatException(this.m);
                }
                continue;
            }
            break;
        }
    }
    
    @Override
    public final int r() {
        this.k();
        if (this.k != bx.f && this.k != bx.g) {
            throw new IllegalStateException("Expected an int but was " + this.k);
        }
        while (true) {
            try {
                final int int1 = Integer.parseInt(this.m);
                this.t();
                return int1;
            }
            catch (NumberFormatException ex) {
                final double double1 = Double.parseDouble(this.m);
                final int int1;
                if ((int1 = (int)double1) != double1) {
                    throw new NumberFormatException(this.m);
                }
                continue;
            }
            break;
        }
    }
    
    @Override
    public final void s() {
        this.k();
        if (this.k == bx.b || this.k == bx.d) {
            throw new IllegalStateException("Expected a value but was " + this.k);
        }
        this.p = true;
        int n = 0;
        try {
            int n2;
            do {
                final bx t = this.t();
                if (t == bx.a || t == bx.c) {
                    n2 = n + 1;
                }
                else {
                    if (t != bx.b) {
                        final bx d = bx.d;
                        n2 = n;
                        if (t != d) {
                            continue;
                        }
                    }
                    n2 = n - 1;
                }
            } while ((n = n2) != 0);
        }
        finally {
            this.p = false;
        }
    }
    
    @Override
    public final String toString() {
        final StringBuilder append = new StringBuilder().append(this.getClass().getSimpleName()).append(" near ");
        final StringBuilder sb = new StringBuilder();
        final int min = Math.min(this.f, 20);
        sb.append(this.e, this.f - min, min);
        sb.append(this.e, this.f, Math.min(this.g - this.f, 20));
        return append.append((Object)sb).toString();
    }
}
