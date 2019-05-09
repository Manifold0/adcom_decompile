// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b;

import java.util.Iterator;
import android.os.Message;
import java.io.File;
import android.os.Looper;
import android.os.Handler;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class g
{
    private final AtomicInteger a;
    private final String b;
    private volatile e c;
    private final List<b> d;
    private final b e;
    private final c f;
    
    public g(final String s, final c c) {
        this.a = new AtomicInteger(0);
        this.d = new CopyOnWriteArrayList<b>();
        this.b = j.a(s);
        this.f = j.a(c);
        this.e = new a(s, this.d);
    }
    
    private void c() {
        synchronized (this) {
            e c;
            if (this.c == null) {
                c = new e(new h(this.b), new com.facebook.ads.internal.v.b.a.b(this.f.a(this.b), this.f.c));
                c.a(this.e);
            }
            else {
                c = this.c;
            }
            this.c = c;
        }
    }
    
    private void d() {
        synchronized (this) {
            if (this.a.decrementAndGet() <= 0) {
                this.c.a();
                this.c = null;
            }
        }
    }
    
    public void a() {
        this.d.clear();
        if (this.c != null) {
            this.c.a((b)null);
            this.c.a();
            this.c = null;
        }
        this.a.set(0);
    }
    
    public void a(final d d, final Socket socket) {
        this.c();
        try {
            this.a.incrementAndGet();
            this.c.a(d, socket);
        }
        finally {
            this.d();
        }
    }
    
    public int b() {
        return this.a.get();
    }
    
    private static final class a extends Handler implements b
    {
        private final String a;
        private final List<b> b;
        
        public a(final String a, final List<b> b) {
            super(Looper.getMainLooper());
            this.a = a;
            this.b = b;
        }
        
        public void a(final File obj, final String s, final int arg1) {
            final Message obtainMessage = this.obtainMessage();
            obtainMessage.arg1 = arg1;
            obtainMessage.obj = obj;
            this.sendMessage(obtainMessage);
        }
        
        public void handleMessage(final Message message) {
            final Iterator<b> iterator = this.b.iterator();
            while (iterator.hasNext()) {
                iterator.next().a((File)message.obj, this.a, message.arg1);
            }
        }
    }
}
