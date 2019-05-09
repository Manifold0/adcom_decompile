package com.tapjoy.internal;

import com.kongregate.p000o.p002g.C0640a;
import java.io.InputStream;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ce extends cf {
    /* renamed from: a */
    public abstract Object mo6333a(bs bsVar);

    /* renamed from: a */
    public final Map mo6206a() {
        Map linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Accept", C0640a.f1003a);
        return linkedHashMap;
    }

    /* renamed from: a */
    public final Object mo6205a(URI uri, InputStream inputStream) {
        Object obj = null;
        bs a = bs.m7242a(inputStream);
        a.mo6187a("BASE_URI", uri);
        try {
            a.mo6193h();
            int i = 0;
            String str = null;
            while (a.mo6195j()) {
                String l = a.mo6197l();
                if ("status".equals(l)) {
                    i = a.mo6203r();
                } else if ("message".equals(l)) {
                    str = a.mo6198m();
                } else if ("data".equals(l)) {
                    obj = mo6333a(a);
                } else {
                    a.mo6204s();
                }
            }
            a.mo6194i();
            if (i == 200) {
                return obj;
            }
            throw new cg(i, str);
        } finally {
            a.close();
        }
    }
}
