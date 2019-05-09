// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.Writer;

public final class br implements bq
{
    public final String a;
    
    public br(final String a) {
        this.a = a;
    }
    
    @Override
    public final void a(final Writer writer) {
        writer.write(this.a);
    }
    
    @Override
    public final boolean equals(final Object o) {
        return this == o || (o instanceof br && this.a.equals(((br)o).a));
    }
    
    @Override
    public final int hashCode() {
        return this.a.hashCode();
    }
    
    @Override
    public final String toString() {
        return this.a;
    }
}
