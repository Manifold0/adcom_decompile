// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.b;

import android.text.TextUtils;

public class d
{
    private final f a;
    private boolean b;
    
    public d(final f a) {
        this.b = true;
        this.a = a;
    }
    
    private static long a(String substring, final String s) {
        substring = substring.substring(s.length());
        if (!TextUtils.isEmpty((CharSequence)substring)) {
            try {
                final Long value = Long.parseLong(substring);
                if (value >= 0L) {
                    return value;
                }
            }
            catch (NumberFormatException ex) {
                return -1L;
            }
        }
        return -1L;
    }
    
    public void a() {
        if (!this.b) {
            return;
        }
        if (this.a.canGoBack() || this.a.canGoForward()) {
            this.b = false;
            return;
        }
        this.a.a("void((function() {try {  if (!window.performance || !window.performance.timing || !document ||       !document.body || document.body.scrollHeight <= 0 ||       !document.body.children || document.body.children.length < 1) {    return;  }  var nvtiming__an_t = window.performance.timing;  if (nvtiming__an_t.responseEnd > 0) {    console.log('ANNavResponseEnd:'+nvtiming__an_t.responseEnd);  }  if (nvtiming__an_t.domContentLoadedEventStart > 0) {    console.log('ANNavDomContentLoaded:' + nvtiming__an_t.domContentLoadedEventStart);  }  if (nvtiming__an_t.loadEventEnd > 0) {    console.log('ANNavLoadEventEnd:' + nvtiming__an_t.loadEventEnd);  }} catch(err) {  console.log('an_navigation_timing_error:' + err.message);}})());");
    }
    
    public void a(final String s) {
        if (this.b) {
            if (s.startsWith("ANNavResponseEnd:")) {
                this.a.a(a(s, "ANNavResponseEnd:"));
                return;
            }
            if (s.startsWith("ANNavDomContentLoaded:")) {
                this.a.b(a(s, "ANNavDomContentLoaded:"));
                return;
            }
            if (s.startsWith("ANNavLoadEventEnd:")) {
                this.a.c(a(s, "ANNavLoadEventEnd:"));
            }
        }
    }
    
    public void a(final boolean b) {
        this.b = b;
    }
}
