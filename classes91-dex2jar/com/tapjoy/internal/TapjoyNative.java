// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TJPlacementListener;
import android.content.Context;

@ew
public class TapjoyNative
{
    @ew
    public static Object createPlacement(final Context context, final String s, final TJPlacementListener tjPlacementListener) {
        return ff.a().a(context, s, tjPlacementListener);
    }
}
