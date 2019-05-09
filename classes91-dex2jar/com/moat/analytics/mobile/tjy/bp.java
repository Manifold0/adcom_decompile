// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.view.View;
import java.util.Iterator;

class bp implements Iterator
{
    final /* synthetic */ bo a;
    private int b;
    
    private bp(final bo a) {
        this.a = a;
        this.b = -1;
    }
    
    public View a() {
        ++this.b;
        return this.a.a.getChildAt(this.b);
    }
    
    @Override
    public boolean hasNext() {
        return this.b + 1 < this.a.a.getChildCount();
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not implemented. Under development.");
    }
}
