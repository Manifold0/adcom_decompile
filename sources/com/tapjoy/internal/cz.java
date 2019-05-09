package com.tapjoy.internal;

import java.util.Iterator;

public abstract class cz implements Iterator {
    protected cz() {
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
