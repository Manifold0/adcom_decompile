// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.util.concurrent.ThreadPoolExecutor;

public final class fk extends fj
{
    private final ThreadPoolExecutor b;
    
    public fk(final File file, final gb gb) {
        super(file, gb);
        this.b = new ThreadPoolExecutor(0, 1, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }
    
    @Override
    protected final void a() {
        try {
            this.b.execute(new a(2, 0L, null, null, null));
        }
        catch (Throwable t) {}
    }
    
    @Override
    protected final void a(final long n) {
        try {
            this.b.execute(new a(1, n, null, null, null));
        }
        catch (Throwable t) {}
    }
    
    @Override
    protected final void a(final long n, final String s, final String s2, final Map map) {
        try {
            final ThreadPoolExecutor b = this.b;
            HashMap hashMap;
            if (map != null) {
                hashMap = new HashMap(map);
            }
            else {
                hashMap = null;
            }
            b.execute(new a(3, n, s, s2, hashMap));
        }
        catch (Throwable t) {}
    }
    
    @Override
    protected final void finalize() {
        try {
            this.b.shutdown();
            this.b.awaitTermination(1L, TimeUnit.SECONDS);
        }
        finally {
            super.finalize();
        }
    }
    
    final class a implements Runnable
    {
        private int b;
        private long c;
        private String d;
        private String e;
        private Map f;
        
        a(final int b, final long c, final String d, final String e, final Map f) {
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
        }
        
        @Override
        public final void run() {
            Label_0061: {
                try {
                    switch (this.b) {
                        case 1: {
                            fj.this.a(this.c);
                            return;
                        }
                        case 2: {
                            break;
                        }
                        case 3: {
                            break Label_0061;
                        }
                        default: {
                            return;
                        }
                    }
                }
                catch (Throwable t) {
                    fj.this.a();
                    return;
                }
                fj.this.a();
                return;
            }
            fj.this.a(this.c, this.d, this.e, this.f);
        }
    }
}
