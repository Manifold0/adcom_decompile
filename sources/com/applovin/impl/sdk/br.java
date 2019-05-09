package com.applovin.impl.sdk;

import android.app.AlertDialog.Builder;

class br implements Runnable {
    /* renamed from: a */
    final /* synthetic */ bm f2160a;

    br(bm bmVar) {
        this.f2160a = bmVar;
    }

    public void run() {
        Builder builder = new Builder(this.f2160a.f2154b);
        builder.setTitle((CharSequence) this.f2160a.f2153a.get(ea.al));
        builder.setMessage((CharSequence) this.f2160a.f2153a.get(ea.am));
        builder.setCancelable(false);
        builder.setPositiveButton((CharSequence) this.f2160a.f2153a.get(ea.ao), null);
        builder.setNegativeButton((CharSequence) this.f2160a.f2153a.get(ea.an), new bs(this));
        this.f2160a.f2155c = builder.show();
    }
}
