package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tapjoy.TapjoyConstants;

public final class fh {
    /* renamed from: d */
    private static final fh f7756d;
    /* renamed from: e */
    private static fh f7757e;
    /* renamed from: a */
    public Boolean f7758a = null;
    /* renamed from: b */
    public String f7759b = null;
    /* renamed from: c */
    public boolean f7760c = false;
    /* renamed from: f */
    private Context f7761f;

    static {
        fh fhVar = new fh();
        f7756d = fhVar;
        f7757e = fhVar;
    }

    /* renamed from: a */
    public static fh m7730a() {
        return f7757e;
    }

    /* renamed from: a */
    public final synchronized void m7731a(Context context) {
        boolean z = true;
        synchronized (this) {
            if (context != null) {
                if (this.f7761f == null) {
                    this.f7761f = context;
                }
            }
            fh fhVar = f7757e;
            if (fhVar.f7761f != null) {
                SharedPreferences sharedPreferences = fhVar.f7761f.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);
                if (fhVar.f7758a == null && sharedPreferences.contains("gdpr")) {
                    fhVar.f7758a = Boolean.valueOf(sharedPreferences.getBoolean("gdpr", false));
                }
                if (fhVar.f7759b == null) {
                    fhVar.f7759b = sharedPreferences.getString("cgdpr", "");
                }
            }
            if (this.f7760c) {
                boolean b;
                fh fhVar2 = f7757e;
                if (fhVar2.f7761f != null) {
                    if (fhVar2.f7758a != null) {
                        b = fhVar2.m7732b();
                    } else {
                        b = false;
                    }
                    if (fhVar2.f7759b != null) {
                        if (b && fhVar2.m7733c()) {
                            b = true;
                        } else {
                            b = false;
                        }
                    }
                } else {
                    b = false;
                }
                if (b) {
                    z = false;
                }
                this.f7760c = z;
            }
        }
    }

    /* renamed from: b */
    public final boolean m7732b() {
        if (this.f7761f == null) {
            return false;
        }
        Editor edit = this.f7761f.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).edit();
        edit.putBoolean("gdpr", this.f7758a.booleanValue());
        edit.commit();
        return true;
    }

    /* renamed from: c */
    public final boolean m7733c() {
        if (this.f7761f == null) {
            return false;
        }
        Editor edit = this.f7761f.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0).edit();
        edit.putString("cgdpr", this.f7759b);
        edit.commit();
        return true;
    }
}
