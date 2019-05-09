// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.HashSet;
import java.util.Iterator;

public final class cy
{
    public static HashSet a(final Iterator iterator) {
        final HashSet<Object> set = new HashSet<Object>();
        while (iterator.hasNext()) {
            set.add(iterator.next());
        }
        return set;
    }
}
