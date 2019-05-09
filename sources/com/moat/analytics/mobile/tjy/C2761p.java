package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.webkit.ValueCallback;

/* renamed from: com.moat.analytics.mobile.tjy.p */
class C2761p implements ValueCallback {
    /* renamed from: a */
    final /* synthetic */ C2759n f6731a;

    C2761p(C2759n c2759n) {
        this.f6731a = c2759n;
    }

    /* renamed from: a */
    public void m6950a(String str) {
        if (str == null || str.equalsIgnoreCase("null") || str.equalsIgnoreCase("false")) {
            if (this.f6731a.f6724d.mo6105b()) {
                Log.d("MoatJavaScriptBridge", "Received value is:" + (str == null ? "null" : "(String)" + str));
            }
            if (this.f6731a.f6725e == -1 || this.f6731a.f6725e == 50) {
                this.f6731a.m6946g();
            }
            this.f6731a.f6725e = this.f6731a.f6725e + 1;
        } else if (str.equalsIgnoreCase("true")) {
            this.f6731a.f6725e = -1;
            this.f6731a.m6943e();
        } else if (this.f6731a.f6724d.mo6105b()) {
            Log.d("MoatJavaScriptBridge", "Received unusual value from Javascript:" + str);
        }
    }

    public /* synthetic */ void onReceiveValue(Object obj) {
        m6950a((String) obj);
    }
}
