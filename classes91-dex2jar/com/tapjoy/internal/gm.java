// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.IOException;
import java.io.File;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.content.Context;

public final class gm
{
    public final q A;
    public final j B;
    final j C;
    final q D;
    final Context a;
    final SharedPreferences b;
    final q c;
    final q d;
    final m e;
    final m f;
    final q g;
    final m h;
    final n i;
    final n j;
    final n k;
    final q l;
    final m m;
    final k n;
    final n o;
    final k p;
    final q q;
    final q r;
    final m s;
    final m t;
    final q u;
    final q v;
    final q w;
    final q x;
    final q y;
    final q z;
    
    private gm(Context applicationContext) {
        applicationContext = applicationContext.getApplicationContext();
        this.a = applicationContext;
        this.b = applicationContext.getSharedPreferences("fiverocks", 0);
        this.c = new q(this.b, "sdk");
        this.d = new q(this.b, "ir");
        this.e = new m(this.b, "fql", 0);
        this.f = new m(this.b, "fq", 0);
        this.g = new q(this.b, "push");
        this.h = new m(this.b, "ss", 0);
        this.i = new n(this.b, "std");
        this.j = new n(this.b, "slt");
        this.k = new n(this.b, "sld");
        this.l = new q(this.b, "ptc");
        this.m = new m(this.b, "pc", 0);
        this.n = new k(this.b, "ptp");
        this.o = new n(this.b, "lpt");
        this.p = new k(this.b, "plp");
        this.q = new q(this.b, "adv");
        this.r = new q(this.b, "ui");
        this.s = new m(this.b, "ul", -1);
        this.t = new m(this.b, "uf", -1);
        this.u = new q(this.b, "uv1");
        this.v = new q(this.b, "uv2");
        this.w = new q(this.b, "uv3");
        this.x = new q(this.b, "uv4");
        this.y = new q(this.b, "uv5");
        this.z = new q(this.b, "utags");
        this.A = new q(this.b, "idfa");
        this.B = new j(this.b, "idfa.optout");
        this.C = new j(this.b, "push.optout");
        this.D = new q(this.b, "appId");
    }
    
    public static gm a(final Context context) {
        return new gm(context);
    }
    
    final SharedPreferences$Editor a() {
        return this.b.edit();
    }
    
    public final void a(final boolean b) {
        com.tapjoy.internal.p.a(this.b, "gcm.onServer", b);
    }
    
    public final String b() {
        String string = this.b.getString("ir", (String)null);
        if (string != null) {
            if (string.length() <= 0) {
                return null;
            }
        }
        else {
            final File file = new File(gc.c(this.a), "referrer");
            String a = string;
            while (true) {
                if (!file.exists()) {
                    break Label_0062;
                }
                try {
                    a = bl.a(file, ap.c);
                    final SharedPreferences$Editor edit = this.b.edit();
                    if (a != null) {
                        string = a;
                    }
                    else {
                        string = "";
                    }
                    edit.putString("ir", string).commit();
                    if (a != null) {
                        string = a;
                        if (a.length() > 0) {
                            return string;
                        }
                    }
                    return null;
                }
                catch (IOException ex) {
                    a = string;
                    continue;
                }
                break;
            }
        }
        return string;
    }
}
