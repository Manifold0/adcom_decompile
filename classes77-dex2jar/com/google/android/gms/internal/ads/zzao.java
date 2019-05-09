// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

final class zzao extends FilterInputStream
{
    private final long zzcc;
    private long zzcd;
    
    zzao(final InputStream inputStream, final long zzcc) {
        super(inputStream);
        this.zzcc = zzcc;
    }
    
    @Override
    public final int read() throws IOException {
        final int read = super.read();
        if (read != -1) {
            ++this.zzcd;
        }
        return read;
    }
    
    @Override
    public final int read(final byte[] array, int read, final int n) throws IOException {
        read = super.read(array, read, n);
        if (read != -1) {
            this.zzcd += read;
        }
        return read;
    }
    
    final long zzo() {
        return this.zzcc - this.zzcd;
    }
}
