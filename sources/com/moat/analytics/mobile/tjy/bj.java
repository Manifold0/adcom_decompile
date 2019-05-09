package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.exception.C2747a;
import com.moat.analytics.mobile.tjy.base.functional.C2749a;

class bj implements WebAdTracker {
    /* renamed from: a */
    private final C2749a f6701a;
    /* renamed from: b */
    private final ap f6702b;

    bj(WebView webView, C2742a c2742a, ap apVar) {
        this.f6702b = apVar;
        if (apVar.mo6105b()) {
            Log.d("MoatWebAdTracker", "In initialization method.");
        }
        if (webView == null) {
            if (apVar.mo6105b()) {
                Log.e("MoatWebAdTracker", "WebView is null. Will not track.");
            }
            this.f6701a = C2749a.m6883a();
            return;
        }
        this.f6701a = C2749a.m6884a(new bi(webView, webView, false, c2742a, apVar));
    }

    public boolean track() {
        boolean c;
        boolean b = this.f6702b.mo6105b();
        boolean z = false;
        if (b) {
            try {
                Log.d("MoatWebAdTracker", "In track method.");
            } catch (Exception e) {
                C2747a.m6882a(e);
            }
        }
        if (this.f6701a.m6888c()) {
            c = ((bh) this.f6701a.m6886b()).mo6121c();
        } else if (b) {
            Log.e("MoatWebAdTracker", "Internal tracker not available. Not tracking.");
            if (b) {
                Log.d("MoatWebAdTracker", "Attempt to start tracking ad was " + (z ? "" : "un") + "successful.");
            }
            return z;
        } else {
            c = false;
        }
        z = c;
        if (b) {
            if (z) {
            }
            Log.d("MoatWebAdTracker", "Attempt to start tracking ad was " + (z ? "" : "un") + "successful.");
        }
        return z;
    }
}
