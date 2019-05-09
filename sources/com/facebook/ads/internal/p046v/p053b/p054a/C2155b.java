package com.facebook.ads.internal.p046v.p053b.p054a;

import com.facebook.ads.internal.p046v.p053b.C2154a;
import com.facebook.ads.internal.p046v.p053b.C2180l;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Locale;

/* renamed from: com.facebook.ads.internal.v.b.a.b */
public class C2155b implements C2154a {
    /* renamed from: a */
    public File f4982a;
    /* renamed from: b */
    private final C2153a f4983b;
    /* renamed from: c */
    private RandomAccessFile f4984c;

    public C2155b(File file, C2153a c2153a) {
        if (c2153a == null) {
            try {
                throw new NullPointerException();
            } catch (Throwable e) {
                throw new C2180l("Error using file " + file + " as disc cache", e);
            }
        }
        this.f4983b = c2153a;
        File parentFile = file.getParentFile();
        if (parentFile.exists()) {
            if (!parentFile.isDirectory()) {
                throw new IOException("File " + parentFile + " is not directory!");
            }
        } else if (!parentFile.mkdirs()) {
            throw new IOException(String.format(Locale.US, "Directory %s can't be created", new Object[]{parentFile.getAbsolutePath()}));
        }
        boolean exists = file.exists();
        this.f4982a = exists ? file : new File(file.getParentFile(), file.getName() + ".download");
        this.f4984c = new RandomAccessFile(this.f4982a, exists ? "r" : "rw");
    }

    /* renamed from: a */
    public synchronized int mo5520a() {
        try {
        } catch (Throwable e) {
            throw new C2180l("Error reading length of file " + this.f4982a, e);
        }
        return (int) this.f4984c.length();
    }

    /* renamed from: a */
    public synchronized int mo5521a(byte[] bArr, long j, int i) {
        try {
            this.f4984c.seek(j);
        } catch (Throwable e) {
            throw new C2180l(String.format(Locale.US, "Error reading %d bytes with offset %d from file[%d bytes] to buffer[%d bytes]", new Object[]{Integer.valueOf(i), Long.valueOf(j), Integer.valueOf(mo5520a()), Integer.valueOf(bArr.length)}), e);
        }
        return this.f4984c.read(bArr, 0, i);
    }

    /* renamed from: a */
    public synchronized void mo5522a(byte[] bArr, int i) {
        try {
            if (mo5525d()) {
                throw new C2180l("Error append cache: cache file " + this.f4982a + " is completed!");
            }
            this.f4984c.seek((long) mo5520a());
            this.f4984c.write(bArr, 0, i);
        } catch (Throwable e) {
            throw new C2180l(String.format(Locale.US, "Error writing %d bytes to %s from buffer with size %d", new Object[]{Integer.valueOf(i), this.f4984c, Integer.valueOf(bArr.length)}), e);
        }
    }

    /* renamed from: b */
    public synchronized void mo5523b() {
        try {
            this.f4984c.close();
            this.f4983b.mo5526a(this.f4982a);
        } catch (Throwable e) {
            throw new C2180l("Error closing file " + this.f4982a, e);
        }
    }

    /* renamed from: c */
    public synchronized void mo5524c() {
        if (!mo5525d()) {
            mo5523b();
            File file = new File(this.f4982a.getParentFile(), this.f4982a.getName().substring(0, this.f4982a.getName().length() - ".download".length()));
            if (this.f4982a.renameTo(file)) {
                this.f4982a = file;
                try {
                    this.f4984c = new RandomAccessFile(this.f4982a, "r");
                } catch (Throwable e) {
                    throw new C2180l("Error opening " + this.f4982a + " as disc cache", e);
                }
            }
            throw new C2180l("Error renaming file " + this.f4982a + " to " + file + " for completion!");
        }
    }

    /* renamed from: d */
    public synchronized boolean mo5525d() {
        return !this.f4982a.getName().endsWith(".download");
    }
}
