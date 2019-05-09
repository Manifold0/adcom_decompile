// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.h;

import android.support.annotation.Nullable;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import android.content.Context;
import com.facebook.ads.internal.v.b.f;
import java.util.concurrent.Future;

public class e
{
    private static final String a;
    private static e b;
    private final Future<f> c;
    
    static {
        a = e.class.getSimpleName();
    }
    
    private e(final Context context) {
        this.c = Executors.newSingleThreadExecutor().submit((Callable<f>)new Callable<f>() {
            public f a() {
                return new f(context);
            }
        });
    }
    
    public static e a(Context applicationContext) {
        Label_0034: {
            if (e.b != null) {
                break Label_0034;
            }
            applicationContext = applicationContext.getApplicationContext();
            synchronized (e.class) {
                if (e.b == null) {
                    e.b = new e(applicationContext);
                }
                return e.b;
            }
        }
    }
    
    @Nullable
    private f a() {
        try {
            return this.c.get(500L, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException ex) {}
        catch (ExecutionException f) {
            goto Label_0022;
        }
        catch (TimeoutException f) {
            goto Label_0022;
        }
    }
    
    public boolean a(final String s) {
        final f a = this.a();
        return a != null && a.a(s);
    }
    
    @Nullable
    public String b(final String s) {
        final f a = this.a();
        if (a == null) {
            return null;
        }
        return a.b(s);
    }
}
