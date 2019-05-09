package com.applovin.impl.adview;

import android.content.Context;

class cd implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f1849a;
    /* renamed from: b */
    final /* synthetic */ cb f1850b;

    cd(cb cbVar, Context context) {
        this.f1850b = cbVar;
        this.f1849a = context;
    }

    public void run() {
        this.f1850b.m2051a(this.f1849a);
    }
}
