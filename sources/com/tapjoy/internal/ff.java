package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacementListener;

abstract class ff {
    /* renamed from: a */
    private static final ff f7754a;
    /* renamed from: b */
    private static ff f7755b;

    /* renamed from: a */
    public abstract Object mo6282a(Context context, String str, TJPlacementListener tJPlacementListener);

    ff() {
    }

    static {
        ff fgVar = new fg();
        f7754a = fgVar;
        f7755b = fgVar;
    }

    /* renamed from: a */
    static ff m7727a() {
        return f7755b;
    }
}
