// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.content.Context;

public final class fh
{
    private static final fh d;
    private static fh e;
    public Boolean a;
    public String b;
    public boolean c;
    private Context f;
    
    static {
        fh.e = (d = new fh());
    }
    
    public fh() {
        this.a = null;
        this.b = null;
        this.c = false;
    }
    
    public static fh a() {
        return fh.e;
    }
    
    public final void a(final Context f) {
        final boolean b = true;
        // monitorenter(this)
        Label_0021: {
            if (f == null) {
                break Label_0021;
            }
        Label_0170_Outer:
            while (true) {
            Label_0157_Outer:
                while (true) {
                    while (true) {
                        int c = 0;
                        Label_0187: {
                            Label_0185: {
                                while (true) {
                                    Label_0180: {
                                        try {
                                            if (this.f == null) {
                                                this.f = f;
                                            }
                                            final fh e = fh.e;
                                            if (e.f != null) {
                                                final SharedPreferences sharedPreferences = e.f.getSharedPreferences("tjcPrefrences", 0);
                                                if (e.a == null && sharedPreferences.contains("gdpr")) {
                                                    e.a = sharedPreferences.getBoolean("gdpr", false);
                                                }
                                                if (e.b == null) {
                                                    e.b = sharedPreferences.getString("cgdpr", "");
                                                }
                                            }
                                            if (this.c) {
                                                final fh e2 = fh.e;
                                                if (e2.f == null) {
                                                    break Label_0185;
                                                }
                                                if (e2.a == null) {
                                                    break Label_0180;
                                                }
                                                final boolean b2 = e2.b();
                                                c = (b2 ? 1 : 0);
                                                if (e2.b != null) {
                                                    c = ((b2 && e2.c()) ? 1 : 0);
                                                }
                                                break Label_0187;
                                            }
                                            return;
                                            c = 0;
                                            this.c = (c != 0);
                                            return;
                                        }
                                        finally {
                                        }
                                        // monitorexit(this)
                                    }
                                    final boolean b2 = false;
                                    continue Label_0170_Outer;
                                }
                            }
                            c = (false ? 1 : 0);
                        }
                        if (c == 0) {
                            c = (b ? 1 : 0);
                            continue;
                        }
                        break;
                    }
                    continue Label_0157_Outer;
                }
            }
        }
    }
    
    public final boolean b() {
        boolean b = false;
        if (this.f != null) {
            final SharedPreferences$Editor edit = this.f.getSharedPreferences("tjcPrefrences", 0).edit();
            edit.putBoolean("gdpr", (boolean)this.a);
            edit.commit();
            b = true;
        }
        return b;
    }
    
    public final boolean c() {
        boolean b = false;
        if (this.f != null) {
            final SharedPreferences$Editor edit = this.f.getSharedPreferences("tjcPrefrences", 0).edit();
            edit.putString("cgdpr", this.b);
            edit.commit();
            b = true;
        }
        return b;
    }
}
