package com.tapjoy.internal;

import com.tapjoy.TapjoyLog;
import com.tapjoy.internal.fi.C2926a;
import com.vungle.warren.ui.VungleActivity;
import java.util.HashMap;
import java.util.Map;

public abstract class et {
    /* renamed from: c */
    private static final String f7674c = et.class.getSimpleName();
    /* renamed from: a */
    public final Map f7675a = new HashMap();
    /* renamed from: b */
    public final Map f7676b = new HashMap();

    protected et(String str, String str2, String str3) {
        this.f7675a.put(VungleActivity.PLACEMENT_EXTRA, str);
        this.f7675a.put("placement_type", str2);
        this.f7675a.put("content_type", str3);
    }

    /* renamed from: a */
    protected final C2926a m7662a(String str, Map map, Map map2) {
        C2926a b = fi.m7754e(str).m7734a().m7738a(this.f7675a).m7738a(map).m7741b(map2);
        this.f7676b.put(str, b);
        return b;
    }

    /* renamed from: a */
    public final void m7663a(String str, Object obj) {
        this.f7675a.put(str, obj);
    }

    /* renamed from: a */
    public final C2926a m7661a() {
        return m7662a("Content.rendered", null, null);
    }

    /* renamed from: b */
    public final C2926a m7664b() {
        return m7665b("Content.rendered", null, null);
    }

    /* renamed from: b */
    protected final C2926a m7665b(String str, Map map, Map map2) {
        C2926a c2926a;
        if (aq.m7169a(str)) {
            c2926a = null;
        } else {
            c2926a = (C2926a) this.f7676b.remove(str);
        }
        if (c2926a == null) {
            TapjoyLog.m7128e(f7674c, "Error when calling endTrackingEvent -- " + str + " tracking has not been started.");
        } else {
            c2926a.m7738a(this.f7675a).m7738a(map).m7741b(map2).m7739b().m7742c();
        }
        return c2926a;
    }
}
