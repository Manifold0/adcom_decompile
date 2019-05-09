package com.applovin.impl.sdk;

import android.webkit.WebView;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

class ai implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AtomicReference f2027a;
    /* renamed from: b */
    final /* synthetic */ CountDownLatch f2028b;
    /* renamed from: c */
    final /* synthetic */ ah f2029c;

    ai(ah ahVar, AtomicReference atomicReference, CountDownLatch countDownLatch) {
        this.f2029c = ahVar;
        this.f2027a = atomicReference;
        this.f2028b = countDownLatch;
    }

    public void run() {
        try {
            this.f2027a.set(new WebView(this.f2029c.f2025c).getSettings().getUserAgentString());
        } catch (Throwable th) {
            this.f2029c.f2024b.mo4174e("DataCollector", "Unable to collect user agent string", th);
        } finally {
            this.f2028b.countDown();
        }
    }
}
