package com.tapjoy;

import android.content.Context;
import com.tapjoy.internal.be;
import com.tapjoy.internal.ct;
import com.tapjoy.internal.fy;
import com.tapjoy.internal.gh;

public class TJPlacementManager {
    /* renamed from: a */
    private static final be f6979a = be.m7193a();
    /* renamed from: b */
    private static int f6980b = 0;
    /* renamed from: c */
    private static int f6981c = 0;
    /* renamed from: d */
    private static int f6982d = 3;
    /* renamed from: e */
    private static int f6983e = 3;

    public static TJPlacement createPlacement(Context context, String placementName, boolean initiatedBySdk, TJPlacementListener listener) {
        TJCorePlacement a = m7080a(placementName, null, null, initiatedBySdk);
        a.f6921j = initiatedBySdk;
        a.f6914c.setPlacementType("sdk");
        a.setContext(context);
        return new TJPlacement(a, listener);
    }

    /* renamed from: a */
    public static TJPlacement m7081a(String str, String str2, String str3, TJPlacementListener tJPlacementListener) {
        TJPlacement tJPlacement;
        synchronized (f6979a) {
            tJPlacement = new TJPlacement(m7080a(str, str2, str3, false), tJPlacementListener);
        }
        return tJPlacement;
    }

    /* renamed from: a */
    static TJCorePlacement m7079a(String str) {
        TJCorePlacement tJCorePlacement;
        synchronized (f6979a) {
            tJCorePlacement = (TJCorePlacement) f6979a.get(str);
        }
        return tJCorePlacement;
    }

    public static void setCachedPlacementLimit(int limit) {
        f6982d = limit;
    }

    public static void setPreRenderedPlacementLimit(int limit) {
        f6983e = limit;
    }

    public static int getCachedPlacementLimit() {
        return f6982d;
    }

    public static int getPreRenderedPlacementLimit() {
        return f6983e;
    }

    public static int getCachedPlacementCount() {
        return f6980b;
    }

    public static int getPreRenderedPlacementCount() {
        return f6981c;
    }

    public static boolean canCachePlacement() {
        return getCachedPlacementCount() < getCachedPlacementLimit();
    }

    public static boolean canPreRenderPlacement() {
        return getPreRenderedPlacementCount() < getPreRenderedPlacementLimit();
    }

    public static void incrementPlacementCacheCount() {
        int i = f6980b + 1;
        f6980b = i;
        if (i > f6982d) {
            f6980b = f6982d;
        }
        printPlacementCacheInformation();
    }

    public static void decrementPlacementCacheCount() {
        int i = f6980b - 1;
        f6980b = i;
        if (i < 0) {
            f6980b = 0;
        }
        printPlacementCacheInformation();
    }

    public static void incrementPlacementPreRenderCount() {
        int i = f6981c + 1;
        f6981c = i;
        if (i > f6983e) {
            f6981c = f6983e;
        }
    }

    public static void decrementPlacementPreRenderCount() {
        int i = f6981c - 1;
        f6981c = i;
        if (i < 0) {
            f6981c = 0;
        }
    }

    public static void printPlacementCacheInformation() {
        TapjoyLog.m7129i("TJPlacementManager", "Space available in placement cache: " + f6980b + " out of " + f6982d);
    }

    public static void printPlacementPreRenderInformation() {
        TapjoyLog.m7129i("TJPlacementManager", "Space available for placement pre-render: " + f6981c + " out of " + f6983e);
    }

    public static void dismissContentShowing(boolean dismissAd) {
        if (dismissAd) {
            TJAdUnitActivity.m7023a();
        }
        gh.m7949a();
        fy.m7804a();
    }

    /* renamed from: a */
    static TJCorePlacement m7080a(String str, String str2, String str3, boolean z) {
        TJCorePlacement a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(z ? "!SYSTEM!" : "");
        stringBuilder.append(!ct.m7339c(str) ? str : "");
        if (ct.m7339c(str2)) {
            str2 = "";
        }
        stringBuilder.append(str2);
        if (ct.m7339c(str3)) {
            str3 = "";
        }
        stringBuilder.append(str3);
        String stringBuilder2 = stringBuilder.toString();
        TapjoyLog.m7126d("TJPlacementManager", "TJCorePlacement key=" + stringBuilder2);
        synchronized (f6979a) {
            a = m7079a(stringBuilder2);
            if (a == null) {
                a = new TJCorePlacement(str, stringBuilder2);
                f6979a.put(stringBuilder2, a);
                TapjoyLog.m7126d("TJPlacementManager", "Created TJCorePlacement with GUID: " + a.f6915d);
            }
        }
        return a;
    }
}
