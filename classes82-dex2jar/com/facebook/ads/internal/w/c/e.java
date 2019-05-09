// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.c;

import android.support.annotation.NonNull;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class e extends BufferedInputStream
{
    private int a;
    private boolean b;
    private int c;
    
    public e(final InputStream inputStream) {
        super(inputStream);
        this.c = Integer.MAX_VALUE;
    }
    
    public boolean a() {
        return this.b;
    }
    
    @Override
    public void mark(final int c) {
        synchronized (this) {
            super.mark(this.c = c);
        }
    }
    
    @Override
    public int read() {
        if (this.a + 1 > this.c) {
            this.b = true;
            return -1;
        }
        ++this.a;
        return super.read();
    }
    
    @Override
    public int read(@NonNull final byte[] array) {
        if (this.a + array.length > this.c) {
            this.b = true;
            return -1;
        }
        return super.read(array);
    }
    
    @Override
    public int read(final byte[] array, int read, final int n) {
        synchronized (this) {
            if (this.a + n > this.c) {
                this.b = true;
                read = -1;
            }
            else {
                read = super.read(array, read, n);
                this.a += read;
            }
            return read;
        }
    }
    
    @Override
    public void reset() {
        synchronized (this) {
            this.c = Integer.MAX_VALUE;
            super.reset();
        }
    }
    
    @Override
    public long skip(long skip) {
        synchronized (this) {
            if (this.a + skip > this.c) {
                this.b = true;
                skip = 0L;
            }
            else {
                this.a += (int)skip;
                skip = super.skip(skip);
            }
            return skip;
        }
    }
}
