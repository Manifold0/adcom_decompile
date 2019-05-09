package com.tapjoy.internal;

import java.io.Closeable;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class dc {
    /* renamed from: a */
    static final Logger f7308a = Logger.getLogger(dc.class.getName());

    private dc() {
    }

    /* renamed from: a */
    public static void m7357a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                try {
                    f7308a.log(Level.WARNING, "IOException thrown while closing Closeable.", e);
                } catch (Throwable e2) {
                    f7308a.log(Level.SEVERE, "IOException should not have been thrown.", e2);
                }
            }
        }
    }
}
