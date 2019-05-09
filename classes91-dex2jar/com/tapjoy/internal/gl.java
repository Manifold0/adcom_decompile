// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import android.content.Context;
import android.content.SharedPreferences;

public final class gl
{
    final q a;
    public final q b;
    private final SharedPreferences c;
    
    public gl(final Context context) {
        this.c = context.getApplicationContext().getSharedPreferences("fiverocks", 0);
        this.a = new q(this.c, "noMoreToday.date");
        this.b = new q(this.c, "noMoreToday.actionIds");
        this.b();
    }
    
    static String a() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }
    
    public final void b() {
        final String a = this.a.a();
        if (a != null && !a().equals(a)) {
            this.a.a(null);
            this.b.a(null);
        }
    }
}
