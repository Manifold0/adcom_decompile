// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.List;
import java.util.LinkedList;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.Flushable;

public final class go implements Flushable
{
    final Object a;
    bc b;
    private final File c;
    
    public go(final File c) {
        this.c = c;
        this.a = this;
        try {
            this.b = az.a(new i(c, new bi() {}));
        }
        catch (Exception ex) {
            this.a();
        }
    }
    
    final void a() {
        this.c.delete();
        while (true) {
            if (!(this.b instanceof Closeable)) {
                break Label_0030;
            }
            try {
                ((Closeable)this.b).close();
                this.b = new ba(new LinkedList());
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public final void a(final int n) {
        synchronized (this.a) {
            try {
                this.b.b(n);
            }
            catch (Exception ex) {
                this.a();
            }
        }
    }
    
    public final int b() {
        synchronized (this.a) {
            try {
                return this.b.size();
            }
            catch (Exception ex) {
                this.a();
                return 0;
            }
        }
    }
    
    public final dy b(final int n) {
        synchronized (this.a) {
            try {
                return (dy)this.b.a(n);
            }
            catch (Exception ex) {
                this.a();
                return null;
            }
        }
    }
    
    public final boolean c() {
        synchronized (this.a) {
            try {
                return this.b.isEmpty();
            }
            catch (Exception ex) {
                this.a();
                return true;
            }
        }
    }
    
    @Override
    public final void flush() {
        synchronized (this.a) {
            if (!(this.b instanceof Flushable)) {
                return;
            }
            try {
                ((Flushable)this.b).flush();
            }
            catch (Exception ex) {
                this.a();
            }
        }
    }
}
