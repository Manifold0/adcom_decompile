// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.IOException;
import java.util.UUID;
import java.io.File;

public final class gg
{
    private final File a;
    
    public gg(final File a) {
        this.a = a;
    }
    
    public final boolean a() {
        boolean b = false;
        synchronized (this) {
            if (this.b() == null) {
                try {
                    bl.a(this.a, UUID.randomUUID().toString());
                    if (this.b() != null) {
                        b = true;
                        return b;
                    }
                    return b;
                }
                catch (IOException ex) {
                    try {
                        this.a.delete();
                        throw ex;
                    }
                    catch (IOException ex2) {}
                }
            }
            return b;
        }
    }
    
    final String b() {
        if (this.a.exists()) {
            try {
                final String a = bl.a(this.a, ap.c);
                if (a.length() > 0) {
                    return a;
                }
            }
            catch (IOException ex) {}
        }
        return null;
    }
}
