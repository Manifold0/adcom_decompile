// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.ByteArrayOutputStream;

public final class zzau extends ByteArrayOutputStream
{
    private final zzak zzbq;
    
    public zzau(final zzak zzbq, final int n) {
        this.zzbq = zzbq;
        this.buf = this.zzbq.zzb(Math.max(n, 256));
    }
    
    private final void zzc(final int n) {
        if (this.count + n <= this.buf.length) {
            return;
        }
        final byte[] zzb = this.zzbq.zzb(this.count + n << 1);
        System.arraycopy(this.buf, 0, zzb, 0, this.count);
        this.zzbq.zza(this.buf);
        this.buf = zzb;
    }
    
    @Override
    public final void close() throws IOException {
        this.zzbq.zza(this.buf);
        this.buf = null;
        super.close();
    }
    
    public final void finalize() {
        this.zzbq.zza(this.buf);
    }
    
    @Override
    public final void write(final int n) {
        synchronized (this) {
            this.zzc(1);
            super.write(n);
        }
    }
    
    @Override
    public final void write(final byte[] array, final int n, final int n2) {
        synchronized (this) {
            this.zzc(n2);
            super.write(array, n, n2);
        }
    }
}
