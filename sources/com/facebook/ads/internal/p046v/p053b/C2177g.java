package com.facebook.ads.internal.p046v.p053b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.facebook.ads.internal.p046v.p053b.p054a.C2155b;
import java.io.File;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.facebook.ads.internal.v.b.g */
final class C2177g {
    /* renamed from: a */
    private final AtomicInteger f5028a = new AtomicInteger(0);
    /* renamed from: b */
    private final String f5029b;
    /* renamed from: c */
    private volatile C2168e f5030c;
    /* renamed from: d */
    private final List<C2164b> f5031d = new CopyOnWriteArrayList();
    /* renamed from: e */
    private final C2164b f5032e;
    /* renamed from: f */
    private final C2165c f5033f;

    /* renamed from: com.facebook.ads.internal.v.b.g$a */
    private static final class C2176a extends Handler implements C2164b {
        /* renamed from: a */
        private final String f5026a;
        /* renamed from: b */
        private final List<C2164b> f5027b;

        public C2176a(String str, List<C2164b> list) {
            super(Looper.getMainLooper());
            this.f5026a = str;
            this.f5027b = list;
        }

        /* renamed from: a */
        public void mo5530a(File file, String str, int i) {
            Message obtainMessage = obtainMessage();
            obtainMessage.arg1 = i;
            obtainMessage.obj = file;
            sendMessage(obtainMessage);
        }

        public void handleMessage(Message message) {
            for (C2164b a : this.f5027b) {
                a.mo5530a((File) message.obj, this.f5026a, message.arg1);
            }
        }
    }

    public C2177g(String str, C2165c c2165c) {
        this.f5029b = (String) C2182j.m5591a(str);
        this.f5033f = (C2165c) C2182j.m5591a(c2165c);
        this.f5032e = new C2176a(str, this.f5031d);
    }

    /* renamed from: c */
    private synchronized void m5575c() {
        C2168e c2168e;
        if (this.f5030c == null) {
            c2168e = new C2168e(new C2179h(this.f5029b), new C2155b(this.f5033f.m5539a(this.f5029b), this.f5033f.f4991c));
            c2168e.m5554a(this.f5032e);
        } else {
            c2168e = this.f5030c;
        }
        this.f5030c = c2168e;
    }

    /* renamed from: d */
    private synchronized void m5576d() {
        if (this.f5028a.decrementAndGet() <= 0) {
            this.f5030c.m5547a();
            this.f5030c = null;
        }
    }

    /* renamed from: a */
    public void m5577a() {
        this.f5031d.clear();
        if (this.f5030c != null) {
            this.f5030c.m5554a(null);
            this.f5030c.m5547a();
            this.f5030c = null;
        }
        this.f5028a.set(0);
    }

    /* renamed from: a */
    public void m5578a(C2166d c2166d, Socket socket) {
        m5575c();
        try {
            this.f5028a.incrementAndGet();
            this.f5030c.m5555a(c2166d, socket);
        } finally {
            m5576d();
        }
    }

    /* renamed from: b */
    public int m5579b() {
        return this.f5028a.get();
    }
}
