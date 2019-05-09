package com.tapjoy.internal;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

public final class go implements Flushable {
    /* renamed from: a */
    final Object f7962a = this;
    /* renamed from: b */
    bc f7963b;
    /* renamed from: c */
    private final File f7964c;

    /* renamed from: com.tapjoy.internal.go$1 */
    class C29551 implements bi {
        /* renamed from: a */
        final /* synthetic */ go f7961a;

        C29551(go goVar) {
            this.f7961a = goVar;
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo6321a(OutputStream outputStream, Object obj) {
            dy.f7438c.m7396a(outputStream, (dy) obj);
        }

        /* renamed from: b */
        public final /* synthetic */ Object mo6322b(InputStream inputStream) {
            return (dy) dy.f7438c.m7392a(inputStream);
        }
    }

    public go(File file) {
        this.f7964c = file;
        try {
            this.f7963b = az.m7186a(new C2984i(file, new C29551(this)));
        } catch (Exception e) {
            m7977a();
        }
    }

    /* renamed from: a */
    final void m7977a() {
        this.f7964c.delete();
        if (this.f7963b instanceof Closeable) {
            try {
                ((Closeable) this.f7963b).close();
            } catch (Exception e) {
            }
        }
        this.f7963b = new ba(new LinkedList());
    }

    public final void flush() {
        synchronized (this.f7962a) {
            if (this.f7963b instanceof Flushable) {
                try {
                    ((Flushable) this.f7963b).flush();
                } catch (Exception e) {
                    m7977a();
                }
            }
        }
    }

    /* renamed from: b */
    public final int m7979b() {
        int size;
        synchronized (this.f7962a) {
            try {
                size = this.f7963b.size();
            } catch (Exception e) {
                m7977a();
                size = 0;
            }
        }
        return size;
    }

    /* renamed from: c */
    public final boolean m7981c() {
        boolean isEmpty;
        synchronized (this.f7962a) {
            try {
                isEmpty = this.f7963b.isEmpty();
            } catch (Exception e) {
                m7977a();
                isEmpty = true;
            }
        }
        return isEmpty;
    }

    /* renamed from: a */
    public final void m7978a(int i) {
        synchronized (this.f7962a) {
            try {
                this.f7963b.mo6183b(i);
            } catch (Exception e) {
                m7977a();
            }
        }
    }

    /* renamed from: b */
    public final dy m7980b(int i) {
        dy dyVar;
        synchronized (this.f7962a) {
            try {
                dyVar = (dy) this.f7963b.mo6182a(i);
            } catch (Exception e) {
                m7977a();
                dyVar = null;
            }
        }
        return dyVar;
    }
}
