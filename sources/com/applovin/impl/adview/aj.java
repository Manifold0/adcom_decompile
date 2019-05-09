package com.applovin.impl.adview;

import android.content.res.Resources;

class aj {
    /* renamed from: a */
    public static float m2005a(Resources resources, float f) {
        return (resources.getDisplayMetrics().density * f) + 0.5f;
    }

    /* renamed from: b */
    public static float m2006b(Resources resources, float f) {
        return resources.getDisplayMetrics().scaledDensity * f;
    }
}
