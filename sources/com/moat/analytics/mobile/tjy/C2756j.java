package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.C2747a;

/* renamed from: com.moat.analytics.mobile.tjy.j */
class C2756j implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C2744i f6714a;

    C2756j(C2744i c2744i) {
        this.f6714a = c2744i;
    }

    public void run() {
        try {
            if (this.f6714a.f.get() == null || this.f6714a.m6825e()) {
                this.f6714a.m6823c();
            } else if (Boolean.valueOf(this.f6714a.m6831i()).booleanValue()) {
                this.f6714a.d.postDelayed(this, 200);
            } else {
                this.f6714a.m6823c();
            }
        } catch (Exception e) {
            this.f6714a.m6823c();
            C2747a.m6882a(e);
        }
    }
}
