package com.tapjoy.internal;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class fk extends fj {
    /* renamed from: b */
    private final ThreadPoolExecutor f7782b = new ThreadPoolExecutor(0, 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* renamed from: com.tapjoy.internal.fk$a */
    class C2927a implements Runnable {
        /* renamed from: a */
        final /* synthetic */ fk f7776a;
        /* renamed from: b */
        private int f7777b;
        /* renamed from: c */
        private long f7778c;
        /* renamed from: d */
        private String f7779d;
        /* renamed from: e */
        private String f7780e;
        /* renamed from: f */
        private Map f7781f;

        C2927a(fk fkVar, int i, long j, String str, String str2, Map map) {
            this.f7776a = fkVar;
            this.f7777b = i;
            this.f7778c = j;
            this.f7779d = str;
            this.f7780e = str2;
            this.f7781f = map;
        }

        public final void run() {
            try {
                switch (this.f7777b) {
                    case 1:
                        super.mo6284a(this.f7778c);
                        return;
                    case 2:
                        super.mo6283a();
                        return;
                    case 3:
                        super.mo6285a(this.f7778c, this.f7779d, this.f7780e, this.f7781f);
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
                super.mo6283a();
            }
            super.mo6283a();
        }
    }

    public fk(File file, gb gbVar) {
        super(file, gbVar);
    }

    protected final void finalize() {
        try {
            this.f7782b.shutdown();
            this.f7782b.awaitTermination(1, TimeUnit.SECONDS);
        } finally {
            super.finalize();
        }
    }

    /* renamed from: a */
    protected final void mo6284a(long j) {
        try {
            this.f7782b.execute(new C2927a(this, 1, j, null, null, null));
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    protected final void mo6283a() {
        try {
            this.f7782b.execute(new C2927a(this, 2, 0, null, null, null));
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    protected final void mo6285a(long j, String str, String str2, Map map) {
        try {
            this.f7782b.execute(new C2927a(this, 3, j, str, str2, map != null ? new HashMap(map) : null));
        } catch (Throwable th) {
        }
    }
}
