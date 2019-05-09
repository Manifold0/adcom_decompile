// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b;

import java.util.Locale;
import android.text.TextUtils;
import java.io.BufferedOutputStream;
import java.net.Socket;
import java.io.OutputStream;
import com.facebook.ads.internal.v.b.a.b;

class e extends k
{
    private final h a;
    private final b b;
    private com.facebook.ads.internal.v.b.b c;
    
    public e(final h a, final b b) {
        super(a, b);
        this.b = b;
        this.a = a;
    }
    
    private void a(final OutputStream outputStream, long n) {
        final byte[] array = new byte[8192];
        while (true) {
            final int a = this.a(array, n, array.length);
            if (a == -1) {
                break;
            }
            outputStream.write(array, 0, a);
            n += a;
        }
        outputStream.flush();
    }
    
    private void b(final OutputStream outputStream, long n) {
        try {
            final h h = new h(this.a);
            h.a((int)n);
            final byte[] array = new byte[8192];
            while (true) {
                final int a = h.a(array);
                if (a == -1) {
                    break;
                }
                outputStream.write(array, 0, a);
                n += a;
            }
            outputStream.flush();
        }
        finally {
            this.a.b();
        }
    }
    
    @Override
    protected void a(final int n) {
        if (this.c != null) {
            this.c.a(this.b.a, this.a.a, n);
        }
    }
    
    public void a(final com.facebook.ads.internal.v.b.b c) {
        this.c = c;
    }
    
    public void a(final d d, final Socket socket) {
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        final String c = this.a.c();
        int n;
        if (!TextUtils.isEmpty((CharSequence)c)) {
            n = 1;
        }
        else {
            n = 0;
        }
        int n2;
        if (this.b.d()) {
            n2 = this.b.a();
        }
        else {
            n2 = this.a.a();
        }
        boolean b;
        if (n2 >= 0) {
            b = true;
        }
        else {
            b = false;
        }
        long n3;
        if (d.c) {
            n3 = n2 - d.b;
        }
        else {
            n3 = n2;
        }
        int n4;
        if (b && d.c) {
            n4 = 1;
        }
        else {
            n4 = 0;
        }
        final StringBuilder sb = new StringBuilder();
        String s;
        if (d.c) {
            s = "HTTP/1.1 206 PARTIAL CONTENT\n";
        }
        else {
            s = "HTTP/1.1 200 OK\n";
        }
        final StringBuilder append = sb.append(s).append("Accept-Ranges: bytes\n");
        String format;
        if (b) {
            format = String.format(Locale.US, "Content-Length: %d\n", n3);
        }
        else {
            format = "";
        }
        final StringBuilder append2 = append.append(format);
        String format2;
        if (n4 != 0) {
            format2 = String.format(Locale.US, "Content-Range: bytes %d-%d/%d\n", d.b, n2 - 1, n2);
        }
        else {
            format2 = "";
        }
        final StringBuilder append3 = append2.append(format2);
        String format3;
        if (n != 0) {
            format3 = String.format(Locale.US, "Content-Type: %s\n", c);
        }
        else {
            format3 = "";
        }
        bufferedOutputStream.write(append3.append(format3).append("\n").toString().getBytes("UTF-8"));
        final long b2 = d.b;
        final int a = this.a.a();
        boolean b3;
        if (a > 0) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        final int a2 = this.b.a();
        int n5;
        if (!b3 || !d.c || d.b <= a * 0.2f + a2) {
            n5 = 1;
        }
        else {
            n5 = 0;
        }
        if (n5 != 0) {
            this.a(bufferedOutputStream, b2);
            return;
        }
        this.b(bufferedOutputStream, b2);
    }
}
