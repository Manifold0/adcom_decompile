// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b.a;

import java.util.Locale;
import java.io.IOException;
import com.facebook.ads.internal.v.b.l;
import java.io.RandomAccessFile;
import java.io.File;
import com.facebook.ads.internal.v.b.a;

public class b implements a
{
    public File a;
    private final com.facebook.ads.internal.v.b.a.a b;
    private RandomAccessFile c;
    
    public b(final File file, final com.facebook.ads.internal.v.b.a.a b) {
        if (b == null) {
            try {
                throw new NullPointerException();
            }
            catch (IOException ex) {
                throw new l("Error using file " + file + " as disc cache", ex);
            }
        }
        this.b = b;
        final File parentFile = file.getParentFile();
        if (parentFile.exists()) {
            if (!parentFile.isDirectory()) {
                throw new IOException("File " + parentFile + " is not directory!");
            }
        }
        else if (!parentFile.mkdirs()) {
            throw new IOException(String.format(Locale.US, "Directory %s can't be created", parentFile.getAbsolutePath()));
        }
        final boolean exists = file.exists();
        File a;
        if (exists) {
            a = file;
        }
        else {
            a = new File(file.getParentFile(), file.getName() + ".download");
        }
        this.a = a;
        final File a2 = this.a;
        String s;
        if (exists) {
            s = "r";
        }
        else {
            s = "rw";
        }
        this.c = new RandomAccessFile(a2, s);
    }
    
    @Override
    public int a() {
        synchronized (this) {
            try {
                return (int)this.c.length();
            }
            catch (IOException ex) {
                throw new l("Error reading length of file " + this.a, ex);
            }
        }
    }
    
    @Override
    public int a(final byte[] array, final long n, final int n2) {
        synchronized (this) {
            try {
                this.c.seek(n);
                return this.c.read(array, 0, n2);
            }
            catch (IOException ex) {
                throw new l(String.format(Locale.US, "Error reading %d bytes with offset %d from file[%d bytes] to buffer[%d bytes]", n2, n, this.a(), array.length), ex);
            }
        }
    }
    
    @Override
    public void a(final byte[] array, final int n) {
        // monitorenter(this)
        try {
            if (this.d()) {
                throw new l("Error append cache: cache file " + this.a + " is completed!");
            }
        }
        catch (IOException ex) {
            try {
                throw new l(String.format(Locale.US, "Error writing %d bytes to %s from buffer with size %d", n, this.c, array.length), ex);
            }
            finally {
            }
            // monitorexit(this)
        }
        this.c.seek(this.a());
        final byte[] array2;
        this.c.write(array2, 0, n);
    }
    // monitorexit(this)
    
    @Override
    public void b() {
        synchronized (this) {
            try {
                this.c.close();
                this.b.a(this.a);
            }
            catch (IOException ex) {
                throw new l("Error closing file " + this.a, ex);
            }
        }
    }
    
    @Override
    public void c() {
        while (true) {
            synchronized (this) {
                if (this.d()) {
                    return;
                }
                this.b();
                final File file = new File(this.a.getParentFile(), this.a.getName().substring(0, this.a.getName().length() - ".download".length()));
                if (!this.a.renameTo(file)) {
                    throw new l("Error renaming file " + this.a + " to " + file + " for completion!");
                }
            }
            final File a;
            this.a = a;
            try {
                this.c = new RandomAccessFile(this.a, "r");
            }
            catch (IOException ex) {
                throw new l("Error opening " + this.a + " as disc cache", ex);
            }
        }
    }
    
    @Override
    public boolean d() {
        synchronized (this) {
            return !this.a.getName().endsWith(".download");
        }
    }
}
