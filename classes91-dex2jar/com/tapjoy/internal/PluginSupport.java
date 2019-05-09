// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@ew
public final class PluginSupport
{
    private PluginSupport() {
    }
    
    @ew
    public static void trackUsage(final String s, String b, String b2) {
        Map<String, Long> map = null;
        try {
            Label_0117: {
                if (aq.a(b)) {
                    break Label_0117;
                }
                final Serializable s2 = new TreeMap<Object, Object>();
                b = (String)bs.b(b);
                while (true) {
                    Label_0110: {
                        try {
                            ((bs)b).a((Map)s2);
                            ((bu)b).close();
                            b = (String)s2;
                            if (aq.a(b2)) {
                                break Label_0110;
                            }
                            map = new HashMap<String, Long>();
                            b2 = (String)bs.b(b2);
                            try {
                                ((bu)b2).h();
                                while (((bu)b2).j()) {
                                    map.put(((bu)b2).l(), ((bu)b2).q());
                                }
                            }
                            finally {
                                ((bu)b2).close();
                            }
                        }
                        finally {
                            final String s3 = b;
                            ((bu)s3).close();
                        }
                        try {
                            final String s3 = b;
                            ((bs)s3).close();
                            b = null;
                            continue;
                            final String s4;
                            fi.a(s4, (TreeMap)b, map);
                            return;
                            ((bu)b2).i();
                            ((bu)b2).close();
                        }
                        catch (Exception ex) {}
                    }
                    break;
                }
            }
        }
        catch (Exception ex2) {}
    }
}
