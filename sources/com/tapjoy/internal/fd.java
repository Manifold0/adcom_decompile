package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.internal.fm.C2928a;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public final class fd {
    /* renamed from: b */
    private static final fd f7729b;
    /* renamed from: c */
    private static fd f7730c;
    /* renamed from: a */
    public final fb f7731a = new fb();
    /* renamed from: d */
    private Context f7732d;

    /* renamed from: com.tapjoy.internal.fd$1 */
    class C29161 implements Observer {
        /* renamed from: a */
        final /* synthetic */ fd f7728a;

        C29161(fd fdVar) {
            this.f7728a = fdVar;
        }

        public final void update(Observable observable, Object data) {
            Object a;
            fi.m7749a(this.f7728a.f7731a.m7708a("usage_tracking_enabled", false));
            String str = "usage_tracking_exclude";
            Class cls = List.class;
            for (C2928a a2 : this.f7728a.f7731a.f7716b) {
                a = a2.m7769a(str);
                if (a != null && cls.isInstance(a)) {
                    a = cls.cast(a);
                    break;
                }
            }
            a = null;
            fi.m7748a((Collection) a);
        }
    }

    static {
        fd fdVar = new fd();
        f7729b = fdVar;
        f7730c = fdVar;
    }

    /* renamed from: a */
    public static fd m7716a() {
        return f7730c;
    }

    /* renamed from: b */
    public static fb m7717b() {
        return f7730c.f7731a;
    }

    fd() {
    }

    /* renamed from: a */
    public final synchronized void m7718a(Context context) {
        if (context != null) {
            if (this.f7732d == null) {
                this.f7732d = context;
                SharedPreferences c = m7719c();
                String string = m7719c().getString(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS, null);
                if (string != null) {
                    bs b;
                    try {
                        b = bs.m7244b(string);
                        Map d = b.m7257d();
                        b.close();
                        this.f7731a.m7713a(d);
                    } catch (Exception e) {
                        c.edit().remove(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS).commit();
                    } catch (Throwable th) {
                        b.close();
                    }
                }
                Observer c29161 = new C29161(this);
                this.f7731a.addObserver(c29161);
                c29161.update(this.f7731a, null);
            }
        }
    }

    /* renamed from: c */
    public final SharedPreferences m7719c() {
        return this.f7732d.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);
    }
}
