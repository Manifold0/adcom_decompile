package com.moat.analytics.mobile.tjy;

import android.view.View;
import java.util.Iterator;

class bp implements Iterator {
    /* renamed from: a */
    final /* synthetic */ bo f6705a;
    /* renamed from: b */
    private int f6706b;

    private bp(bo boVar) {
        this.f6705a = boVar;
        this.f6706b = -1;
    }

    /* renamed from: a */
    public View m6923a() {
        this.f6706b++;
        return this.f6705a.f6704a.getChildAt(this.f6706b);
    }

    public boolean hasNext() {
        return this.f6706b + 1 < this.f6705a.f6704a.getChildCount();
    }

    public /* synthetic */ Object next() {
        return m6923a();
    }

    public void remove() {
        throw new UnsupportedOperationException("Not implemented. Under development.");
    }
}
