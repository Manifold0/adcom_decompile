package com.moat.analytics.mobile.tjy;

import android.view.ViewGroup;
import java.util.Iterator;

class bo implements Iterable {
    /* renamed from: a */
    private final ViewGroup f6704a;

    private bo(ViewGroup viewGroup) {
        this.f6704a = viewGroup;
    }

    public Iterator iterator() {
        return new bp();
    }
}
