package com.applovin.impl.sdk;

import android.app.AlertDialog.Builder;

class bo implements Runnable {
    /* renamed from: a */
    final /* synthetic */ bm f2157a;

    bo(bm bmVar) {
        this.f2157a = bmVar;
    }

    public void run() {
        Builder builder = new Builder(this.f2157a.f2154b);
        builder.setTitle((CharSequence) this.f2157a.f2153a.get(ea.ag));
        builder.setMessage((CharSequence) this.f2157a.f2153a.get(ea.ah));
        builder.setCancelable(false);
        builder.setPositiveButton((CharSequence) this.f2157a.f2153a.get(ea.aj), new bp(this));
        builder.setNegativeButton((CharSequence) this.f2157a.f2153a.get(ea.ai), new bq(this));
        this.f2157a.f2155c = builder.show();
    }
}
