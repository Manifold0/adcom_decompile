package com.tapjoy.internal;

import android.graphics.Bitmap;
import com.tapjoy.internal.au.C2839a;
import com.tapjoy.internal.gw.C29632;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public final class ha {
    /* renamed from: e */
    public static final bn f8032e = new C29691();
    /* renamed from: f */
    private static final as f8033f;
    /* renamed from: a */
    public URL f8034a;
    /* renamed from: b */
    public Bitmap f8035b;
    /* renamed from: c */
    public byte[] f8036c;
    /* renamed from: d */
    public hh f8037d;

    /* renamed from: com.tapjoy.internal.ha$1 */
    static class C29691 implements bn {
        C29691() {
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6185a(bs bsVar) {
            return new ha(bsVar);
        }
    }

    static {
        as awVar = new aw();
        if (!(awVar instanceof ax)) {
            Object c2839a = new C2839a((av) awVar);
        }
        f8033f = awVar;
    }

    public ha(URL url) {
        this.f8034a = url;
    }

    /* renamed from: a */
    public final boolean m8019a() {
        return (this.f8035b == null && this.f8036c == null) ? false : true;
    }

    /* renamed from: b */
    public final void m8020b() {
        Closeable fileInputStream;
        Throwable th;
        boolean a = fd.m7717b().m7708a("mm_external_cache_enabled", true);
        boolean z = !a;
        if (z) {
            this.f8035b = (Bitmap) f8033f.mo6179a(this.f8034a);
            if (this.f8035b != null) {
                return;
            }
        }
        if (a) {
            File a2 = gw.f7998a.m8005a(this.f8034a);
            if (a2 != null) {
                Closeable closeable = null;
                try {
                    fileInputStream = new FileInputStream(a2);
                    try {
                        m8018a(fileInputStream);
                        dc.m7357a(fileInputStream);
                    } catch (IOException e) {
                        dc.m7357a(fileInputStream);
                        if (this.f8035b != null) {
                        }
                        if (z) {
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        closeable = fileInputStream;
                        th = th3;
                        dc.m7357a(closeable);
                        throw th;
                    }
                } catch (IOException e2) {
                    fileInputStream = null;
                    dc.m7357a(fileInputStream);
                    if (this.f8035b != null) {
                    }
                    if (z) {
                        return;
                    }
                    return;
                } catch (Throwable th4) {
                    th = th4;
                    dc.m7357a(closeable);
                    throw th;
                }
                if (this.f8035b != null && this.f8036c == null) {
                    a2.delete();
                } else if (z && this.f8035b != null) {
                    f8033f.mo6180a(this.f8034a, this.f8035b);
                    return;
                } else {
                    return;
                }
            }
        }
        URLConnection a3 = em.m7652a(this.f8034a);
        long j = 0;
        String headerField = a3.getHeaderField("Cache-Control");
        if (!ct.m7339c(headerField)) {
            String[] split = headerField.split(",");
            int length = split.length;
            int i = 0;
            while (i < length) {
                String trim = split[i].trim();
                if (trim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(trim.substring(8));
                        break;
                    } catch (NumberFormatException e3) {
                    }
                } else {
                    i++;
                }
            }
        }
        fileInputStream = a3.getInputStream();
        InputStream a4 = m8018a(fileInputStream);
        dc.m7357a(fileInputStream);
        gw gwVar = gw.f7998a;
        if (gw.m8000a(j) && a && !(this.f8035b == null && this.f8036c == null)) {
            gw gwVar2 = gw.f7998a;
            URL url = this.f8034a;
            if (gwVar2.f7999b != null) {
                gwVar2.f8002e.submit(new C29632(gwVar2, url, a4, j));
            }
        }
        if (z && this.f8035b != null) {
            f8033f.mo6180a(this.f8034a, this.f8035b);
        }
    }

    /* renamed from: a */
    private ByteArrayInputStream m8018a(InputStream inputStream) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        da.m7354a(inputStream, byteArrayOutputStream);
        byteArrayOutputStream.close();
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        InputStream byteArrayInputStream = new ByteArrayInputStream(toByteArray);
        hi hiVar = new hi();
        hiVar.m8052a(toByteArray);
        hh a = hiVar.m8051a();
        if (a.f8100b == 0) {
            this.f8036c = toByteArray;
            this.f8037d = a;
        } else {
            C2993u c2993u = C2993u.f8232a;
            this.f8035b = C2993u.m8221a(byteArrayInputStream);
            byteArrayInputStream.reset();
        }
        return byteArrayInputStream;
    }

    ha(bs bsVar) {
        Object obj;
        if (bsVar.mo6196k() == bx.STRING) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            this.f8034a = bsVar.m7258e();
            return;
        }
        bsVar.mo6193h();
        String l = bsVar.mo6197l();
        while (bsVar.mo6195j()) {
            if ("url".equals(l)) {
                this.f8034a = bsVar.m7258e();
            } else {
                bsVar.mo6204s();
            }
        }
        bsVar.mo6194i();
    }
}
