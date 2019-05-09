// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Map;
import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;

public abstract class cf
{
    public static ExecutorService a;
    public static ci b;
    private Future c;
    
    public abstract Object a(final URI p0, final InputStream p1);
    
    public Map a() {
        return Collections.emptyMap();
    }
    
    public final void a(final ck ck, final ExecutorService executorService) {
        while (true) {
            while (true) {
                Label_0073: {
                    Label_0046: {
                        synchronized (this) {
                            if (this.c != null && !this.c.isDone()) {
                                break Label_0046;
                            }
                            break Label_0073;
                            // iftrue(Label_0051:, n != 0)
                            throw new IllegalStateException(String.valueOf("Call has not completed"));
                        }
                    }
                    final int n = 0;
                    continue;
                }
                final int n = 1;
                continue;
            }
        }
        Label_0051: {
            final ck ck2;
            this.c = executorService.submit(new ch(this, ck2));
        }
    }
    // monitorexit(this)
    
    public abstract String b();
    
    public abstract String c();
    
    public String d() {
        return null;
    }
    
    public Map e() {
        return new LinkedHashMap();
    }
    
    public Object f() {
        return cf.b.a(this);
    }
}
