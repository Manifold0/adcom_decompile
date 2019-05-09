// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.h;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Collection;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import android.content.Context;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.List;
import android.os.Handler;
import java.util.concurrent.ExecutorService;

public class b
{
    private static final String a;
    private static final ExecutorService b;
    private static final ExecutorService c;
    private final Handler d;
    private final d e;
    private final e f;
    private final com.facebook.ads.internal.h.c g;
    private final List<Callable<Boolean>> h;
    
    static {
        a = b.class.getSimpleName();
        b = Executors.newSingleThreadExecutor();
        c = Executors.newFixedThreadPool(5);
    }
    
    public b(final Context context) {
        this.d = new Handler();
        this.e = com.facebook.ads.internal.h.d.a(context);
        this.f = com.facebook.ads.internal.h.e.a(context);
        this.g = com.facebook.ads.internal.h.c.a(context);
        this.h = new ArrayList<Callable<Boolean>>();
    }
    
    public void a(@Nullable final com.facebook.ads.internal.h.a a) {
        com.facebook.ads.internal.h.b.b.execute(new Runnable() {
            final /* synthetic */ ArrayList a = new ArrayList((Collection<? extends E>)com.facebook.ads.internal.h.b.this.h);
            
            @Override
            public void run() {
                Object iterator = new ArrayList<Object>(this.a.size());
                final Iterator<Callable<T>> iterator2 = this.a.iterator();
                while (iterator2.hasNext()) {
                    ((List<Future<Boolean>>)iterator).add(com.facebook.ads.internal.h.b.c.submit((Callable<Boolean>)iterator2.next()));
                }
                final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                try {
                    iterator = ((List<Object>)iterator).iterator();
                    while (((Iterator)iterator).hasNext()) {
                        atomicBoolean.set(((Iterator<Future<Boolean>>)iterator).next().get() & atomicBoolean.get());
                    }
                    goto Label_0142;
                }
                catch (InterruptedException ex) {}
                catch (ExecutionException iterator) {
                    goto Label_0127;
                }
            }
        });
        this.h.clear();
    }
    
    public void a(final String s) {
        this.h.add(new c(s));
    }
    
    public void a(final String s, final int n, final int n2) {
        this.h.add(new b(s, n, n2));
    }
    
    public void b(final String s) {
        this.h.add(new a(s));
    }
    
    public String c(final String s) {
        return this.f.b(s);
    }
    
    public String d(final String s) {
        return this.g.b(s);
    }
    
    private class a implements Callable<Boolean>
    {
        private final String b;
        
        public a(final String b) {
            this.b = b;
        }
        
        public Boolean a() {
            return com.facebook.ads.internal.h.b.this.g.a(this.b);
        }
    }
    
    private class b implements Callable<Boolean>
    {
        private final String b;
        private final int c;
        private final int d;
        
        public b(final String b, final int c, final int d) {
            this.b = b;
            this.c = c;
            this.d = d;
        }
        
        public Boolean a() {
            return com.facebook.ads.internal.h.b.this.e.a(this.b, this.c, this.d) != null;
        }
    }
    
    private class c implements Callable<Boolean>
    {
        private final String b;
        
        public c(final String b) {
            this.b = b;
        }
        
        public Boolean a() {
            return com.facebook.ads.internal.h.b.this.f.a(this.b);
        }
    }
}
