package com.applovin.impl.sdk;

import android.app.AlertDialog.Builder;
import com.applovin.sdk.AppLovinAd;

class bh implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AppLovinAd f2143a;
    /* renamed from: b */
    final /* synthetic */ bg f2144b;

    bh(bg bgVar, AppLovinAd appLovinAd) {
        this.f2144b = bgVar;
        this.f2143a = appLovinAd;
    }

    public void run() {
        Builder builder = new Builder(this.f2144b.f2139c);
        builder.setTitle((CharSequence) this.f2144b.f2137a.get(ea.f2398V));
        builder.setMessage((CharSequence) this.f2144b.f2137a.get(ea.f2399W));
        builder.setCancelable(false);
        builder.setPositiveButton((CharSequence) this.f2144b.f2137a.get(ea.f2400X), new bi(this));
        builder.setNegativeButton((CharSequence) this.f2144b.f2137a.get(ea.f2401Y), new bk(this));
        builder.show();
    }
}
