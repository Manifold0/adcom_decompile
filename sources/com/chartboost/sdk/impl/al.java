package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.C1378f;
import com.chartboost.sdk.Libraries.C1381h.C1380a;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class al {
    /* renamed from: a */
    private final C1378f f3026a;
    /* renamed from: b */
    private final Map<String, C1380a> f3027b = new HashMap();

    public al(C1378f c1378f) {
        this.f3026a = c1378f;
    }

    /* renamed from: a */
    public C1380a m3399a(String str) {
        if (!m3398b(str)) {
            if (this.f3027b.containsKey(str)) {
                this.f3027b.remove(str);
            }
            return null;
        } else if (this.f3027b.containsKey(str)) {
            return (C1380a) this.f3027b.get(str);
        } else {
            C1380a c1380a = new C1380a(str, new File(this.f3026a.m3140d().f2699d, String.format("%s%s", new Object[]{str, ".png"})), this.f3026a);
            this.f3027b.put(str, c1380a);
            return c1380a;
        }
    }

    /* renamed from: b */
    private boolean m3398b(String str) {
        return this.f3026a.m3137b(String.format("%s%s", new Object[]{str, ".png"}));
    }
}
