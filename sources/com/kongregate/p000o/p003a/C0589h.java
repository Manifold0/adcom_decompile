package com.kongregate.p000o.p003a;

import android.content.Context;
import android.widget.Toast;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.C0566n;
import com.kongregate.p000o.p006c.C0626d;
import java.util.Map;

/* renamed from: com.kongregate.o.a.h */
public class C0589h {
    /* renamed from: a */
    private static final C0589h f837a = new C0589h();

    /* renamed from: a */
    public static C0589h m878a() {
        return f837a;
    }

    /* renamed from: b */
    public static void m880b() {
        C0566n.m779a("io.keen.client.android.KeenLogging", "enableLogging", null, new Object[0]);
    }

    /* renamed from: a */
    public static void m879a(final Context context, String str, String str2, String str3) {
        Object a;
        try {
            Class cls = Class.forName("io.keen.client.android.KeenClient");
            C0566n.m776a(cls, "initialize", new Class[]{Context.class, String.class, String.class, String.class}, context, str, str2, str3);
            a = C0566n.m776a(cls, "client", new Class[0], new Object[0]);
        } catch (Throwable e) {
            C0562j.m760c("KeenClient not found", e);
            a = null;
        }
        if (a == null) {
            C0626d.m968b(new Runnable() {
                public void run() {
                    CharSequence charSequence = "KeenClient enabled but class not found. Be sure the JAR is enabled and KeenClient class is not obfuscated";
                    C0562j.m759c("KONGREGATE CONFIGURATION WARNING: " + charSequence);
                    Toast.makeText(context, charSequence, 1).show();
                }
            });
        }
    }

    /* renamed from: a */
    public void m881a(String str, Map<String, Object> map) {
        if (map == null) {
            C0562j.m759c("Keen event map may not be null");
            return;
        }
        try {
            Class cls = Class.forName("io.keen.client.android.KeenClient");
            C0566n.m775a(cls, "addEvent", C0566n.m776a(cls, "client", new Class[0], new Object[0]), new Class[]{String.class, Map.class}, str, map);
        } catch (Throwable e) {
            C0562j.m760c("KeenClient class not found, not adding event", e);
        }
    }

    /* renamed from: c */
    public void m882c() {
        try {
            Class cls = Class.forName("io.keen.client.android.KeenClient");
            Class cls2 = Class.forName("io.keen.client.android.UploadFinishedCallback");
            C0566n.m775a(cls, "upload", C0566n.m776a(cls, "client", new Class[0], new Object[0]), new Class[]{cls2}, null);
        } catch (Throwable e) {
            C0562j.m760c("KeenClient class not found, not adding event", e);
        }
    }
}
