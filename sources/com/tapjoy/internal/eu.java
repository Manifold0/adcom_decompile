package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;

public final class eu {
    /* renamed from: a */
    private static final fc f7699a = new C29111();

    /* renamed from: com.tapjoy.internal.eu$1 */
    static class C29111 extends fc {
        C29111() {
        }

        /* renamed from: a */
        protected final /* bridge */ /* synthetic */ String mo6271a(Object obj) {
            return "InsufficientCurrency";
        }

        /* renamed from: a */
        protected final /* synthetic */ TJPlacement mo6270a(Context context, TJPlacementListener tJPlacementListener, Object obj) {
            return TJPlacementManager.createPlacement(context, "InsufficientCurrency", true, tJPlacementListener);
        }
    }

    /* renamed from: a */
    public static void m7688a() {
        f7699a.m7678c(null);
    }
}
