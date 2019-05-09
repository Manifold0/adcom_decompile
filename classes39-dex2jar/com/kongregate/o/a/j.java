// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.a;

import java.util.Date;
import com.kongregate.android.internal.util.l;
import android.content.Context;
import android.content.SharedPreferences;

public class j
{
    public static final String a = "kongregate_local_gdpr";
    private volatile SharedPreferences b;
    
    public j(final Context context) {
        this.b = context.getSharedPreferences("kongregate_local_gdpr", 0);
    }
    
    private String a(final long n, final String s) {
        return s + "." + n;
    }
    
    public void a(final long n, final int n2) {
        this.b.edit().putInt(this.a(n, "accepted_policy_version"), n2).putLong(this.a(n, "accepted_at"), System.currentTimeMillis()).apply();
    }
    
    public void a(final long n, final boolean b) {
        this.b.edit().putBoolean(this.a(n, "show_alert"), b).apply();
    }
    
    public boolean a(final long n) {
        return l.a(this.b, this.a(n, "show_alert"), false);
    }
    
    public int b(final long n) {
        return l.a(this.b, this.a(n, "accepted_policy_version"), 0);
    }
    
    public Date c(long a) {
        a = l.a(this.b, this.a(a, "accepted_at"), 0L);
        if (a > 0L) {
            return new Date(a);
        }
        return null;
    }
}
