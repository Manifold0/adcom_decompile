// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import com.applovin.sdk.AppLovinSdk;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Set;
import android.os.Handler;
import com.applovin.sdk.AppLovinLogger;

public final class am
{
    private final AppLovinLogger a;
    private final Handler b;
    private final Set<ap> c;
    private final AtomicInteger d;
    
    public am(final Handler b, final AppLovinSdk appLovinSdk) {
        this.c = new HashSet<ap>();
        this.d = new AtomicInteger();
        if (b == null) {
            throw new IllegalArgumentException("No handler specified.");
        }
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        this.b = b;
        this.a = appLovinSdk.getLogger();
    }
    
    private void a(final ap ap, final int n) {
        this.b.postDelayed((Runnable)new an(this, ap, n), ap.b());
    }
    
    public void a() {
        final HashSet<ap> set = (HashSet<ap>)new HashSet<Object>(this.c);
        this.a.d("CountdownManager", "Starting " + set.size() + " countdowns...");
        final int incrementAndGet = this.d.incrementAndGet();
        for (final ap ap : set) {
            this.a.d("CountdownManager", "Starting countdown: " + ap.a() + " for generation " + incrementAndGet + "...");
            this.a(ap, incrementAndGet);
        }
    }
    
    public void a(final String s, final long n, final ao ao) {
        if (n <= 0L) {
            throw new IllegalArgumentException("Invalid step specified.");
        }
        if (this.b == null) {
            throw new IllegalArgumentException("No handler specified.");
        }
        this.a.d("CountdownManager", "Adding countdown: " + s);
        this.c.add(new ap(s, n, ao, null));
    }
    
    public void b() {
        this.a.d("CountdownManager", "Removing all countdowns...");
        this.c();
        this.c.clear();
    }
    
    public void c() {
        this.a.d("CountdownManager", "Stopping countdowns...");
        this.d.incrementAndGet();
        this.b.removeCallbacksAndMessages((Object)null);
    }
}
