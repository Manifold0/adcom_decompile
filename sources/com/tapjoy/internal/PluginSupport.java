package com.tapjoy.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@ew
public final class PluginSupport {
    private PluginSupport() {
    }

    @ew
    public static void trackUsage(String name, String dimensions, String values) {
        bs b;
        Map map = null;
        try {
            TreeMap treeMap;
            if (aq.m7169a(dimensions)) {
                treeMap = null;
            } else {
                treeMap = new TreeMap();
                b = bs.m7244b(dimensions);
                b.m7252a((Map) treeMap);
                b.close();
            }
            if (!aq.m7169a(values)) {
                map = new HashMap();
                b = bs.m7244b(values);
                b.mo6193h();
                while (b.mo6195j()) {
                    map.put(b.mo6197l(), Long.valueOf(b.mo6202q()));
                }
                b.mo6194i();
                b.close();
            }
            fi.m7747a(name, treeMap, map);
        } catch (Exception e) {
        } catch (Throwable th) {
            b.close();
        }
    }
}
