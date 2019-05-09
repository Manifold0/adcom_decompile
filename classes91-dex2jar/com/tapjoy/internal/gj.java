// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.Intent;
import android.net.Uri;
import android.content.Context;

public abstract class gj
{
    long c;
    boolean d;
    public fv e;
    public String f;
    et g;
    
    static void a(final Context context, final String s) {
        if (ct.c(s)) {
            return;
        }
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
        }
        catch (Exception ex) {}
    }
    
    public abstract void a(final gd p0, final ez p1);
    
    public abstract void b();
    
    public boolean c() {
        return true;
    }
}
