package com.tapjoy.internal;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public final class gg {
    /* renamed from: a */
    private final File f7898a;

    public gg(File file) {
        this.f7898a = file;
    }

    /* renamed from: a */
    public final synchronized boolean m7940a() {
        boolean z = false;
        synchronized (this) {
            if (m7941b() == null) {
                try {
                    bl.m7201a(this.f7898a, UUID.randomUUID().toString());
                    if (m7941b() != null) {
                        z = true;
                    }
                } catch (IOException e) {
                    this.f7898a.delete();
                    throw e;
                } catch (IOException e2) {
                }
            }
        }
        return z;
    }

    /* renamed from: b */
    final String m7941b() {
        if (this.f7898a.exists()) {
            try {
                String a = bl.m7200a(this.f7898a, ap.f7205c);
                if (a.length() > 0) {
                    return a;
                }
            } catch (IOException e) {
            }
        }
        return null;
    }
}
