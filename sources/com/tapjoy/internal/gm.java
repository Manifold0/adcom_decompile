package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.adjust.sdk.Constants;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.model.Cookie;
import java.io.File;
import java.io.IOException;

public final class gm {
    /* renamed from: A */
    public final C2992q f7927A = new C2992q(this.f7932b, "idfa");
    /* renamed from: B */
    public final C2987j f7928B = new C2987j(this.f7932b, "idfa.optout");
    /* renamed from: C */
    final C2987j f7929C = new C2987j(this.f7932b, "push.optout");
    /* renamed from: D */
    final C2992q f7930D = new C2992q(this.f7932b, Cookie.APP_ID);
    /* renamed from: a */
    final Context f7931a;
    /* renamed from: b */
    final SharedPreferences f7932b;
    /* renamed from: c */
    final C2992q f7933c = new C2992q(this.f7932b, "sdk");
    /* renamed from: d */
    final C2992q f7934d = new C2992q(this.f7932b, "ir");
    /* renamed from: e */
    final C2989m f7935e = new C2989m(this.f7932b, "fql", 0);
    /* renamed from: f */
    final C2989m f7936f = new C2989m(this.f7932b, "fq", 0);
    /* renamed from: g */
    final C2992q f7937g = new C2992q(this.f7932b, Constants.PUSH);
    /* renamed from: h */
    final C2989m f7938h = new C2989m(this.f7932b, "ss", 0);
    /* renamed from: i */
    final C2990n f7939i = new C2990n(this.f7932b, "std");
    /* renamed from: j */
    final C2990n f7940j = new C2990n(this.f7932b, "slt");
    /* renamed from: k */
    final C2990n f7941k = new C2990n(this.f7932b, "sld");
    /* renamed from: l */
    final C2992q f7942l = new C2992q(this.f7932b, "ptc");
    /* renamed from: m */
    final C2989m f7943m = new C2989m(this.f7932b, "pc", 0);
    /* renamed from: n */
    final C2988k f7944n = new C2988k(this.f7932b, "ptp");
    /* renamed from: o */
    final C2990n f7945o = new C2990n(this.f7932b, "lpt");
    /* renamed from: p */
    final C2988k f7946p = new C2988k(this.f7932b, "plp");
    /* renamed from: q */
    final C2992q f7947q = new C2992q(this.f7932b, "adv");
    /* renamed from: r */
    final C2992q f7948r = new C2992q(this.f7932b, "ui");
    /* renamed from: s */
    final C2989m f7949s = new C2989m(this.f7932b, "ul", -1);
    /* renamed from: t */
    final C2989m f7950t = new C2989m(this.f7932b, "uf", -1);
    /* renamed from: u */
    final C2992q f7951u = new C2992q(this.f7932b, TapjoyConstants.TJC_USER_VARIABLE_1);
    /* renamed from: v */
    final C2992q f7952v = new C2992q(this.f7932b, TapjoyConstants.TJC_USER_VARIABLE_2);
    /* renamed from: w */
    final C2992q f7953w = new C2992q(this.f7932b, TapjoyConstants.TJC_USER_VARIABLE_3);
    /* renamed from: x */
    final C2992q f7954x = new C2992q(this.f7932b, TapjoyConstants.TJC_USER_VARIABLE_4);
    /* renamed from: y */
    final C2992q f7955y = new C2992q(this.f7932b, TapjoyConstants.TJC_USER_VARIABLE_5);
    /* renamed from: z */
    final C2992q f7956z = new C2992q(this.f7932b, "utags");

    /* renamed from: a */
    public static gm m7971a(Context context) {
        return new gm(context);
    }

    private gm(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f7931a = applicationContext;
        this.f7932b = applicationContext.getSharedPreferences("fiverocks", 0);
    }

    /* renamed from: a */
    final Editor m7972a() {
        return this.f7932b.edit();
    }

    /* renamed from: b */
    public final String m7974b() {
        String string = this.f7932b.getString("ir", null);
        if (string == null) {
            File file = new File(gc.m7836c(this.f7931a), "referrer");
            if (file.exists()) {
                try {
                    string = bl.m7200a(file, ap.f7205c);
                } catch (IOException e) {
                }
            }
            this.f7932b.edit().putString("ir", string != null ? string : "").commit();
            return (string == null || string.length() <= 0) ? null : string;
        } else if (string.length() > 0) {
            return string;
        } else {
            return null;
        }
    }

    /* renamed from: a */
    public final void m7973a(boolean z) {
        C2991p.m8217a(this.f7932b, "gcm.onServer", z);
    }
}
