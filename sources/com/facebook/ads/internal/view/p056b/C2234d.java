package com.facebook.ads.internal.view.p056b;

import android.text.TextUtils;

/* renamed from: com.facebook.ads.internal.view.b.d */
public class C2234d {
    /* renamed from: a */
    private final C2239f f5191a;
    /* renamed from: b */
    private boolean f5192b = true;

    public C2234d(C2239f c2239f) {
        this.f5191a = c2239f;
    }

    /* renamed from: a */
    private static long m5719a(String str, String str2) {
        long j = -1;
        Object substring = str.substring(str2.length());
        if (!TextUtils.isEmpty(substring)) {
            try {
                Long valueOf = Long.valueOf(Long.parseLong(substring));
                if (valueOf.longValue() >= 0) {
                    j = valueOf.longValue();
                }
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }

    /* renamed from: a */
    public void m5720a() {
        if (!this.f5192b) {
            return;
        }
        if (this.f5191a.canGoBack() || this.f5191a.canGoForward()) {
            this.f5192b = false;
        } else {
            this.f5191a.m5732a("void((function() {try {  if (!window.performance || !window.performance.timing || !document ||       !document.body || document.body.scrollHeight <= 0 ||       !document.body.children || document.body.children.length < 1) {    return;  }  var nvtiming__an_t = window.performance.timing;  if (nvtiming__an_t.responseEnd > 0) {    console.log('ANNavResponseEnd:'+nvtiming__an_t.responseEnd);  }  if (nvtiming__an_t.domContentLoadedEventStart > 0) {    console.log('ANNavDomContentLoaded:' + nvtiming__an_t.domContentLoadedEventStart);  }  if (nvtiming__an_t.loadEventEnd > 0) {    console.log('ANNavLoadEventEnd:' + nvtiming__an_t.loadEventEnd);  }} catch(err) {  console.log('an_navigation_timing_error:' + err.message);}})());");
        }
    }

    /* renamed from: a */
    public void m5721a(String str) {
        if (!this.f5192b) {
            return;
        }
        if (str.startsWith("ANNavResponseEnd:")) {
            this.f5191a.m5731a(C2234d.m5719a(str, "ANNavResponseEnd:"));
        } else if (str.startsWith("ANNavDomContentLoaded:")) {
            this.f5191a.m5734b(C2234d.m5719a(str, "ANNavDomContentLoaded:"));
        } else if (str.startsWith("ANNavLoadEventEnd:")) {
            this.f5191a.m5735c(C2234d.m5719a(str, "ANNavLoadEventEnd:"));
        }
    }

    /* renamed from: a */
    public void m5722a(boolean z) {
        this.f5192b = z;
    }
}
