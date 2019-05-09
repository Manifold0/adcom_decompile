// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.webkit.WebView;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

class ai implements Runnable
{
    final /* synthetic */ AtomicReference a;
    final /* synthetic */ CountDownLatch b;
    final /* synthetic */ ah c;
    
    ai(final ah c, final AtomicReference a, final CountDownLatch b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void run() {
        try {
            this.a.set(new WebView(this.c.c).getSettings().getUserAgentString());
        }
        catch (Throwable t) {
            this.c.b.e("DataCollector", "Unable to collect user agent string", t);
        }
        finally {
            this.b.countDown();
        }
    }
}
