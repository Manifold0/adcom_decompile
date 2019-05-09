// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import android.content.Intent;
import android.app.Activity;

public class f
{
    private b a;
    
    public f(final b a) {
        j.a("FacebookSDKHelper: use proxy auth method for FB connect");
        this.a = a;
    }
    
    public void a(final Activity activity, final String s) {
        final e.c a = e.a(activity, s);
        if (a != null) {
            this.a(null, a.b());
        }
    }
    
    public void a(final String s, final a a) {
        j.a("FacebookSDKHelper: result: " + a);
        if (this.a == null) {
            j.c("FacebookSDKHelper retrieved result but listener not set");
            return;
        }
        this.a.a(s, a);
    }
    
    public final boolean a(final Activity activity, final int n, final int n2, final Intent intent) {
        j.a("FacebookSDKHelper - onActivityResult(" + n + "," + n2 + "," + intent);
        Boolean b = false;
        final e.c a = e.a(n, n2, intent);
        if (a != null) {
            this.a(a.a(), a.b());
            b = true;
        }
        return b;
    }
    
    public enum a
    {
        a, 
        b, 
        c, 
        d, 
        e;
        
        public String a() {
            return this.toString().toLowerCase();
        }
    }
    
    public interface b
    {
        void a(final String p0, final a p1);
    }
}
