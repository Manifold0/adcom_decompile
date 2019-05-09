// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.IOException;
import java.util.logging.Level;
import java.io.Closeable;
import java.util.logging.Logger;

public final class dc
{
    static final Logger a;
    
    static {
        a = Logger.getLogger(dc.class.getName());
    }
    
    private dc() {
    }
    
    public static void a(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {
            try {
                dc.a.log(Level.WARNING, "IOException thrown while closing Closeable.", ex);
            }
            catch (IOException ex2) {
                dc.a.log(Level.SEVERE, "IOException should not have been thrown.", ex2);
            }
        }
    }
}
