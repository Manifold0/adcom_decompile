package com.facebook.ads.internal.p046v.p053b;

import android.text.TextUtils;
import com.facebook.ads.internal.p046v.p053b.p054a.C2155b;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Locale;

/* renamed from: com.facebook.ads.internal.v.b.e */
class C2168e extends C2167k {
    /* renamed from: a */
    private final C2179h f5005a;
    /* renamed from: b */
    private final C2155b f5006b;
    /* renamed from: c */
    private C2164b f5007c;

    public C2168e(C2179h c2179h, C2155b c2155b) {
        super(c2179h, c2155b);
        this.f5006b = c2155b;
        this.f5005a = c2179h;
    }

    /* renamed from: a */
    private void m5551a(OutputStream outputStream, long j) {
        byte[] bArr = new byte[8192];
        while (true) {
            int a = m5546a(bArr, j, bArr.length);
            if (a != -1) {
                outputStream.write(bArr, 0, a);
                j += (long) a;
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    /* renamed from: b */
    private void m5552b(OutputStream outputStream, long j) {
        try {
            C2179h c2179h = new C2179h(this.f5005a);
            c2179h.mo5533a((int) j);
            byte[] bArr = new byte[8192];
            while (true) {
                int a = c2179h.mo5532a(bArr);
                if (a == -1) {
                    break;
                }
                outputStream.write(bArr, 0, a);
                j += (long) a;
            }
            outputStream.flush();
        } finally {
            this.f5005a.mo5534b();
        }
    }

    /* renamed from: a */
    protected void mo5529a(int i) {
        if (this.f5007c != null) {
            this.f5007c.mo5530a(this.f5006b.f4982a, this.f5005a.f5034a, i);
        }
    }

    /* renamed from: a */
    public void m5554a(C2164b c2164b) {
        this.f5007c = c2164b;
    }

    /* renamed from: a */
    public void m5555a(C2166d c2166d, Socket socket) {
        OutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        Object obj = !TextUtils.isEmpty(this.f5005a.m5590c()) ? 1 : null;
        int a = this.f5006b.mo5525d() ? this.f5006b.mo5520a() : this.f5005a.mo5531a();
        Object obj2 = a >= 0 ? 1 : null;
        long j = c2166d.f4996c ? ((long) a) - c2166d.f4995b : (long) a;
        Object obj3 = (obj2 == null || !c2166d.f4996c) ? null : 1;
        bufferedOutputStream.write(((c2166d.f4996c ? "HTTP/1.1 206 PARTIAL CONTENT\n" : "HTTP/1.1 200 OK\n") + "Accept-Ranges: bytes\n" + (obj2 != null ? String.format(Locale.US, "Content-Length: %d\n", new Object[]{Long.valueOf(j)}) : "") + (obj3 != null ? String.format(Locale.US, "Content-Range: bytes %d-%d/%d\n", new Object[]{Long.valueOf(c2166d.f4995b), Integer.valueOf(a - 1), Integer.valueOf(a)}) : "") + (obj != null ? String.format(Locale.US, "Content-Type: %s\n", new Object[]{r8}) : "") + "\n").getBytes("UTF-8"));
        long j2 = c2166d.f4995b;
        int a2 = this.f5005a.mo5531a();
        obj = a2 > 0 ? 1 : null;
        int a3 = this.f5006b.mo5520a();
        if (obj != null && c2166d.f4996c) {
            if (((float) c2166d.f4995b) > (((float) a2) * 0.2f) + ((float) a3)) {
                obj = null;
                if (obj == null) {
                    m5551a(bufferedOutputStream, j2);
                } else {
                    m5552b(bufferedOutputStream, j2);
                }
            }
        }
        obj = 1;
        if (obj == null) {
            m5552b(bufferedOutputStream, j2);
        } else {
            m5551a(bufferedOutputStream, j2);
        }
    }
}
