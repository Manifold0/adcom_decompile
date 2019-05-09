package com.facebook.ads.internal.view.p022i;

import android.database.ContentObserver;
import android.os.Handler;

/* renamed from: com.facebook.ads.internal.view.i.e */
class C2503e extends ContentObserver {
    /* renamed from: a */
    private final C2422c f6088a;

    C2503e(Handler handler, C2422c c2422c) {
        super(handler);
        this.f6088a = c2422c;
    }

    public boolean deliverSelfNotifications() {
        return false;
    }

    public void onChange(boolean z) {
        this.f6088a.m6224e();
    }
}
