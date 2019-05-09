// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.functional.a;
import android.os.Handler;
import android.os.Looper;

class av implements Runnable
{
    private static final long b;
    private final aa a;
    private final String c;
    private final ax d;
    private ar e;
    
    static {
        b = 90000L;
    }
    
    private av(final String s, final aa a, final ax d) {
        this.e = ar.a;
        this.a = a;
        this.d = d;
        this.c = "https://z.moatads.com/" + s + "/android/" + "8ace5ca5da6b9adb3c0f055aad4a98c2aedf4bd7".substring(0, 7) + "/status.json";
    }
    
    private void a() {
        long currentTimeMillis = 0L;
        while (true) {
            final long n = System.currentTimeMillis() - currentTimeMillis;
            Label_0028: {
                if (n >= av.b) {
                    break Label_0028;
                }
                try {
                    Thread.sleep(10L + av.b - n);
                    currentTimeMillis = System.currentTimeMillis();
                    final ar b = this.b();
                    final Handler handler = new Handler(Looper.getMainLooper());
                    b.equals(this.e);
                    this.e = b;
                    handler.post((Runnable)new aw(this, b));
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    private ar b() {
        final a a = this.a.a(this.c + "?ts=" + System.currentTimeMillis() + "&v=1.7.10");
        if (!a.c()) {
            return ar.a;
        }
        final u u = new u((String)a.b());
        as.d = u.a();
        as.e = u.c();
        if (u.b()) {
            return ar.b;
        }
        return ar.a;
    }
    
    @Override
    public void run() {
        try {
            this.a();
        }
        catch (Exception ex) {
            this.e = ar.a;
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
        }
    }
}
