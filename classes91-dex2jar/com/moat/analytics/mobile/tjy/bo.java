// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import java.util.Iterator;
import android.view.ViewGroup;

class bo implements Iterable
{
    private final ViewGroup a;
    
    private bo(final ViewGroup a) {
        this.a = a;
    }
    
    @Override
    public Iterator iterator() {
        return new bp(this, null);
    }
}
