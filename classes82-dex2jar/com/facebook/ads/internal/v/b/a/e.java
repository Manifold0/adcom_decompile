// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b.a;

import java.util.concurrent.Callable;
import java.util.Iterator;
import android.util.Log;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.LinkedList;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

abstract class e implements com.facebook.ads.internal.v.b.a.a
{
    private final ExecutorService a;
    
    e() {
        this.a = Executors.newSingleThreadExecutor();
    }
    
    static /* synthetic */ void a(final e e, final File file) {
        if (file.exists()) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (!file.setLastModified(currentTimeMillis)) {
                final long length = file.length();
                if (length == 0L) {
                    if (!file.delete() || !file.createNewFile()) {
                        throw new IOException("Error recreate zero-size file " + file);
                    }
                }
                else {
                    final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
                    randomAccessFile.seek(length - 1L);
                    final byte byte1 = randomAccessFile.readByte();
                    randomAccessFile.seek(length - 1L);
                    randomAccessFile.write(byte1);
                    randomAccessFile.close();
                }
                if (file.lastModified() < currentTimeMillis) {
                    throw new IOException("Error set last modified date to " + file);
                }
            }
        }
        final File parentFile = file.getParentFile();
        List<File> list = new LinkedList<File>();
        final File[] listFiles = parentFile.listFiles();
        if (listFiles != null) {
            list = Arrays.asList(listFiles);
            Collections.sort((List<Object>)list, (Comparator<? super Object>)new d.a());
        }
        e.a(list);
    }
    
    private void a(final List<File> list) {
        final Iterator<File> iterator = list.iterator();
        long n = 0L;
        while (iterator.hasNext()) {
            n += iterator.next().length();
        }
        int size = list.size();
    Label_0139_Outer:
        for (final File file : list) {
            if (this.a(file, n, size)) {
                continue Label_0139_Outer;
            }
            final long length = file.length();
            if (file.delete()) {
                n -= length;
                Log.i("ProxyCache", "Cache file " + file + " is deleted because it exceeds cache limit");
                --size;
            }
            else {
                Log.e("ProxyCache", "Error deleting file " + file + " for trimming cache");
            }
            while (true) {
                continue Label_0139_Outer;
                continue;
            }
        }
    }
    
    @Override
    public void a(final File file) {
        this.a.submit((Callable<Object>)new a(file));
    }
    
    protected abstract boolean a(final File p0, final long p1, final int p2);
    
    private class a implements Callable<Void>
    {
        private final File b;
        
        public a(final File b) {
            this.b = b;
        }
        
        public Void a() {
            e.a(e.this, this.b);
            return null;
        }
    }
}
