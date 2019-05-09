// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import com.tapjoy.internal.fy;
import com.tapjoy.internal.gh;
import android.content.Context;
import java.io.Serializable;
import com.tapjoy.internal.ct;
import com.tapjoy.internal.be;

public class TJPlacementManager
{
    private static final be a;
    private static int b;
    private static int c;
    private static int d;
    private static int e;
    
    static {
        a = be.a();
        TJPlacementManager.b = 0;
        TJPlacementManager.c = 0;
        TJPlacementManager.d = 3;
        TJPlacementManager.e = 3;
    }
    
    static TJCorePlacement a(final String s) {
        synchronized (TJPlacementManager.a) {
            return (TJCorePlacement)TJPlacementManager.a.get(s);
        }
    }
    
    static TJCorePlacement a(final String s, String a, String s2, final boolean b) {
        Serializable string = new StringBuilder();
        while (true) {
            String s3 = null;
            Label_0177_Outer:Label_0184_Outer:
            while (true) {
            Label_0050:
                while (true) {
                    Label_0035: {
                        while (true) {
                            Label_0017: {
                                if (b) {
                                    s3 = "!SYSTEM!";
                                    break Label_0017;
                                }
                                Label_0170: {
                                    break Label_0170;
                                    while (true) {
                                        ((StringBuilder)string).append(s2);
                                        string = ((StringBuilder)string).toString();
                                        TapjoyLog.d("TJPlacementManager", "TJCorePlacement key=" + (String)string);
                                        synchronized (TJPlacementManager.a) {
                                            s2 = (a = (String)a((String)string));
                                            if (s2 == null) {
                                                a = (String)new TJCorePlacement(s, (String)string);
                                                TJPlacementManager.a.put(string, a);
                                                TapjoyLog.d("TJPlacementManager", "Created TJCorePlacement with GUID: " + ((TJCorePlacement)a).d);
                                            }
                                            return (TJCorePlacement)a;
                                            s3 = "";
                                            break Label_0035;
                                            a = "";
                                            break Label_0050;
                                            s2 = "";
                                            continue Label_0177_Outer;
                                            s3 = "";
                                            break;
                                        }
                                    }
                                }
                            }
                            ((StringBuilder)string).append(s3);
                            if (ct.c(s)) {
                                continue Label_0184_Outer;
                            }
                            break;
                        }
                        s3 = s;
                    }
                    ((StringBuilder)string).append(s3);
                    if (ct.c(a)) {
                        continue;
                    }
                    break;
                }
                ((StringBuilder)string).append(a);
                if (!ct.c(s2)) {
                    continue Label_0177_Outer;
                }
                break;
            }
            continue;
        }
    }
    
    public static TJPlacement a(final String s, final String s2, final String s3, final TJPlacementListener tjPlacementListener) {
        synchronized (TJPlacementManager.a) {
            return new TJPlacement(a(s, s2, s3, false), tjPlacementListener);
        }
    }
    
    public static boolean canCachePlacement() {
        return getCachedPlacementCount() < getCachedPlacementLimit();
    }
    
    public static boolean canPreRenderPlacement() {
        return getPreRenderedPlacementCount() < getPreRenderedPlacementLimit();
    }
    
    public static TJPlacement createPlacement(final Context context, final String s, final boolean j, final TJPlacementListener tjPlacementListener) {
        final TJCorePlacement a = a(s, null, null, j);
        a.j = j;
        a.c.setPlacementType("sdk");
        a.setContext(context);
        return new TJPlacement(a, tjPlacementListener);
    }
    
    public static void decrementPlacementCacheCount() {
        if (--TJPlacementManager.b < 0) {
            TJPlacementManager.b = 0;
        }
        printPlacementCacheInformation();
    }
    
    public static void decrementPlacementPreRenderCount() {
        if (--TJPlacementManager.c < 0) {
            TJPlacementManager.c = 0;
        }
    }
    
    public static void dismissContentShowing(final boolean b) {
        if (b) {
            TJAdUnitActivity.a();
        }
        gh.a();
        fy.a();
    }
    
    public static int getCachedPlacementCount() {
        return TJPlacementManager.b;
    }
    
    public static int getCachedPlacementLimit() {
        return TJPlacementManager.d;
    }
    
    public static int getPreRenderedPlacementCount() {
        return TJPlacementManager.c;
    }
    
    public static int getPreRenderedPlacementLimit() {
        return TJPlacementManager.e;
    }
    
    public static void incrementPlacementCacheCount() {
        if (++TJPlacementManager.b > TJPlacementManager.d) {
            TJPlacementManager.b = TJPlacementManager.d;
        }
        printPlacementCacheInformation();
    }
    
    public static void incrementPlacementPreRenderCount() {
        if (++TJPlacementManager.c > TJPlacementManager.e) {
            TJPlacementManager.c = TJPlacementManager.e;
        }
    }
    
    public static void printPlacementCacheInformation() {
        TapjoyLog.i("TJPlacementManager", "Space available in placement cache: " + TJPlacementManager.b + " out of " + TJPlacementManager.d);
    }
    
    public static void printPlacementPreRenderInformation() {
        TapjoyLog.i("TJPlacementManager", "Space available for placement pre-render: " + TJPlacementManager.c + " out of " + TJPlacementManager.e);
    }
    
    public static void setCachedPlacementLimit(final int d) {
        TJPlacementManager.d = d;
    }
    
    public static void setPreRenderedPlacementLimit(final int e) {
        TJPlacementManager.e = e;
    }
}
