// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import android.content.Context;

final class fg extends ff
{
    @Override
    public final Object a(final Context context, final String s, final TJPlacementListener tjPlacementListener) {
        return new TJPlacement(context, s, tjPlacementListener);
    }
}
