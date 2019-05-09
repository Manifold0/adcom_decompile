package com.tapjoy.internal;

import java.util.Observable;

public final class ev {
    /* renamed from: a */
    public static final C2912a f7700a = new C2912a();
    /* renamed from: b */
    public static final C2912a f7701b = new C2912a();
    /* renamed from: c */
    public static final C2912a f7702c = new C2912a();
    /* renamed from: d */
    public static final C2912a f7703d = new C2912a();
    /* renamed from: e */
    public static final C2912a f7704e = new C2912a();

    /* renamed from: com.tapjoy.internal.ev$a */
    public static class C2912a extends Observable {
        public final void notifyObservers() {
            setChanged();
            super.notifyObservers();
        }

        public final void notifyObservers(Object data) {
            setChanged();
            super.notifyObservers(data);
        }
    }
}
