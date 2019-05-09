package com.tapjoy.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public abstract class gj {
    /* renamed from: c */
    long f7804c;
    /* renamed from: d */
    boolean f7805d;
    /* renamed from: e */
    public fv f7806e;
    /* renamed from: f */
    public String f7807f;
    /* renamed from: g */
    et f7808g;

    /* renamed from: a */
    public abstract void mo6290a(gd gdVar, ez ezVar);

    /* renamed from: b */
    public abstract void mo6291b();

    /* renamed from: c */
    public boolean mo6292c() {
        return true;
    }

    /* renamed from: a */
    static void m7800a(Context context, String str) {
        if (!ct.m7339c(str)) {
            try {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (Exception e) {
            }
        }
    }
}
