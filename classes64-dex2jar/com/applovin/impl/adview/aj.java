// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.content.res.Resources;

class aj
{
    public static float a(final Resources resources, final float n) {
        return resources.getDisplayMetrics().density * n + 0.5f;
    }
    
    public static float b(final Resources resources, final float n) {
        return resources.getDisplayMetrics().scaledDensity * n;
    }
}
