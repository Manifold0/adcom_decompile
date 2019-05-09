package com.facebook.ads.internal.p046v.p053b.p054a;

import android.util.Log;
import com.facebook.ads.internal.p046v.p053b.p054a.C2159d.C2158a;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.facebook.ads.internal.v.b.a.e */
abstract class C2161e implements C2153a {
    /* renamed from: a */
    private final ExecutorService f4987a = Executors.newSingleThreadExecutor();

    /* renamed from: com.facebook.ads.internal.v.b.a.e$a */
    private class C2160a implements Callable<Void> {
        /* renamed from: a */
        final /* synthetic */ C2161e f4985a;
        /* renamed from: b */
        private final File f4986b;

        public C2160a(C2161e c2161e, File file) {
            this.f4985a = c2161e;
            this.f4986b = file;
        }

        /* renamed from: a */
        public Void m5530a() {
            C2161e.m5531a(this.f4985a, this.f4986b);
            return null;
        }

        public /* synthetic */ Object call() {
            return m5530a();
        }
    }

    C2161e() {
    }

    /* renamed from: a */
    static /* synthetic */ void m5531a(C2161e c2161e, File file) {
        if (file.exists()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (!file.setLastModified(currentTimeMillis)) {
                long length = file.length();
                if (length != 0) {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
                    randomAccessFile.seek(length - 1);
                    byte readByte = randomAccessFile.readByte();
                    randomAccessFile.seek(length - 1);
                    randomAccessFile.write(readByte);
                    randomAccessFile.close();
                } else if (!(file.delete() && file.createNewFile())) {
                    throw new IOException("Error recreate zero-size file " + file);
                }
                if (file.lastModified() < currentTimeMillis) {
                    throw new IOException("Error set last modified date to " + file);
                }
            }
        }
        File parentFile = file.getParentFile();
        List linkedList = new LinkedList();
        File[] listFiles = parentFile.listFiles();
        if (listFiles != null) {
            linkedList = Arrays.asList(listFiles);
            Collections.sort(linkedList, new C2158a());
        }
        c2161e.m5532a(linkedList);
    }

    /* renamed from: a */
    private void m5532a(List<File> list) {
        long j = 0;
        for (File length : list) {
            j = length.length() + j;
        }
        int size = list.size();
        int i = size;
        for (File length2 : list) {
            if (!mo5528a(length2, j, i)) {
                long length3 = length2.length();
                if (length2.delete()) {
                    i--;
                    j -= length3;
                    Log.i("ProxyCache", "Cache file " + length2 + " is deleted because it exceeds cache limit");
                    size = i;
                    i = size;
                } else {
                    Log.e("ProxyCache", "Error deleting file " + length2 + " for trimming cache");
                }
            }
            size = i;
            i = size;
        }
    }

    /* renamed from: a */
    public void mo5526a(File file) {
        this.f4987a.submit(new C2160a(this, file));
    }

    /* renamed from: a */
    protected abstract boolean mo5528a(File file, long j, int i);
}
