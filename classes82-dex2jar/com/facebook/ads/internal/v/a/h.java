// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.a;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executor;
import android.os.AsyncTask;

public class h extends AsyncTask<l, Void, n> implements c
{
    private static Executor a;
    private a b;
    private b c;
    private Exception d;
    
    static {
        h.a = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }
    
    public h(final a b, final b c) {
        this.b = b;
        this.c = c;
    }
    
    protected n a(final l... array) {
        Label_0022: {
            if (array == null) {
                break Label_0022;
            }
            try {
                if (array.length > 0) {
                    return this.b.a(array[0]);
                }
                throw new IllegalArgumentException("DoHttpRequestTask takes exactly one argument of type HttpRequest");
            }
            catch (Exception d) {
                this.d = d;
                this.cancel(true);
                return null;
            }
        }
    }
    
    public void a(final l l) {
        super.executeOnExecutor(h.a, (Object[])new l[] { l });
    }
    
    protected void a(final n n) {
        this.c.a(n);
    }
    
    protected void onCancelled() {
        this.c.a(this.d);
    }
}
