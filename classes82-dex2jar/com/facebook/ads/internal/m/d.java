// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.m;

import java.util.HashMap;
import org.json.JSONObject;
import java.util.Iterator;
import android.webkit.CookieSyncManager;
import android.os.Build$VERSION;
import android.util.Log;
import android.webkit.CookieManager;
import java.util.Map;
import java.util.List;
import com.facebook.ads.internal.protocol.AdPlacementType;

public class d
{
    private static final String c;
    private static final AdPlacementType d;
    public int a;
    public int b;
    private final long e;
    private AdPlacementType f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private List<b> q;
    
    static {
        c = d.class.getSimpleName();
        d = AdPlacementType.UNKNOWN;
    }
    
    private d(Map<String, String> iterator) {
        this.a = -1;
        this.b = -1;
        this.f = com.facebook.ads.internal.m.d.d;
        this.g = 1;
        this.h = 0;
        this.i = 0;
        this.j = 20;
        this.k = 0;
        this.l = 1000;
        this.m = 10000;
        this.n = 200;
        this.o = 3600;
        this.p = false;
        this.q = null;
        this.e = System.currentTimeMillis();
        iterator = ((Map<Object, Object>)iterator).entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<Object, Object> entry = iterator.next();
            final String s = entry.getKey();
            int n = 0;
            Label_0266: {
                switch (s.hashCode()) {
                    case 3575610: {
                        if (s.equals("type")) {
                            n = 0;
                            break Label_0266;
                        }
                        break;
                    }
                    case 700812481: {
                        if (s.equals("min_viewability_percentage")) {
                            n = 1;
                            break Label_0266;
                        }
                        break;
                    }
                    case 858630459: {
                        if (s.equals("viewability_check_ticker")) {
                            n = 2;
                            break Label_0266;
                        }
                        break;
                    }
                    case 1085444827: {
                        if (s.equals("refresh")) {
                            n = 3;
                            break Label_0266;
                        }
                        break;
                    }
                    case -1561601017: {
                        if (s.equals("refresh_threshold")) {
                            n = 4;
                            break Label_0266;
                        }
                        break;
                    }
                    case -634541425: {
                        if (s.equals("invalidation_duration_in_seconds")) {
                            n = 5;
                            break Label_0266;
                        }
                        break;
                    }
                    case -553208868: {
                        if (s.equals("cacheable")) {
                            n = 6;
                            break Label_0266;
                        }
                        break;
                    }
                    case 2002133996: {
                        if (s.equals("placement_width")) {
                            n = 7;
                            break Label_0266;
                        }
                        break;
                    }
                    case 1503616961: {
                        if (s.equals("placement_height")) {
                            n = 8;
                            break Label_0266;
                        }
                        break;
                    }
                    case 1183549815: {
                        if (s.equals("viewability_check_initial_delay")) {
                            n = 9;
                            break Label_0266;
                        }
                        break;
                    }
                    case -856794442: {
                        if (s.equals("viewability_check_interval")) {
                            n = 10;
                            break Label_0266;
                        }
                        break;
                    }
                    case -726276175: {
                        if (s.equals("request_timeout")) {
                            n = 11;
                            break Label_0266;
                        }
                        break;
                    }
                    case -1899431321: {
                        if (s.equals("conv_tracking_data")) {
                            n = 12;
                            break Label_0266;
                        }
                        break;
                    }
                    case 986744879: {
                        if (s.equals("video_time_polling_interval")) {
                            n = 13;
                            break Label_0266;
                        }
                        break;
                    }
                }
                n = -1;
            }
            switch (n) {
                default: {
                    continue;
                }
                case 0: {
                    this.f = AdPlacementType.fromString(entry.getValue());
                    continue;
                }
                case 1: {
                    this.g = Integer.parseInt(entry.getValue());
                    continue;
                }
                case 2: {
                    this.h = Integer.parseInt(entry.getValue());
                    continue;
                }
                case 3: {
                    this.i = Integer.parseInt(entry.getValue());
                    continue;
                }
                case 4: {
                    this.j = Integer.parseInt(entry.getValue());
                    continue;
                }
                case 5: {
                    this.o = Integer.parseInt(entry.getValue());
                    continue;
                }
                case 6: {
                    this.p = Boolean.valueOf(entry.getValue());
                    continue;
                }
                case 7: {
                    this.a = Integer.parseInt(entry.getValue());
                    continue;
                }
                case 8: {
                    this.b = Integer.parseInt(entry.getValue());
                    continue;
                }
                case 9: {
                    this.k = Integer.parseInt(entry.getValue());
                    continue;
                }
                case 10: {
                    this.l = Integer.parseInt(entry.getValue());
                    continue;
                }
                case 11: {
                    this.m = Integer.parseInt(entry.getValue());
                    continue;
                }
                case 12: {
                    this.q = com.facebook.ads.internal.m.b.a(entry.getValue());
                    CookieManager instance;
                    boolean acceptCookie;
                    try {
                        instance = CookieManager.getInstance();
                        acceptCookie = instance.acceptCookie();
                        instance.setAcceptCookie(true);
                        for (final b b : this.q) {
                            if (b.b()) {
                                instance.setCookie(b.a, b.b + "=" + b.c + ";Domain=" + b.a + ";Expires=" + b.a() + ";path=/");
                            }
                        }
                    }
                    catch (Exception ex) {
                        Log.w(com.facebook.ads.internal.m.d.c, "Failed to set cookie.", (Throwable)ex);
                        continue;
                    }
                    if (Build$VERSION.SDK_INT < 21) {
                        CookieSyncManager.getInstance().startSync();
                    }
                    instance.setAcceptCookie(acceptCookie);
                    continue;
                }
                case 13: {
                    try {
                        this.n = Integer.parseInt(entry.getValue());
                    }
                    catch (NumberFormatException ex2) {
                        this.n = 200;
                    }
                    continue;
                }
            }
        }
    }
    
    public static d a(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final Iterator keys = jsonObject.keys();
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        while (keys.hasNext()) {
            final String s = keys.next();
            hashMap.put(s, String.valueOf(jsonObject.opt(s)));
        }
        return new d(hashMap);
    }
    
    public long a() {
        return this.e;
    }
    
    public AdPlacementType b() {
        return this.f;
    }
    
    public long c() {
        return this.i * 1000;
    }
    
    public long d() {
        return this.j * 1000;
    }
    
    public boolean e() {
        return this.p;
    }
    
    public int f() {
        return this.g;
    }
    
    public int g() {
        return this.h;
    }
    
    public int h() {
        return this.k;
    }
    
    public int i() {
        return this.l;
    }
    
    public int j() {
        return this.m;
    }
    
    public int k() {
        return this.n;
    }
    
    public int l() {
        return this.o * 1000;
    }
}
