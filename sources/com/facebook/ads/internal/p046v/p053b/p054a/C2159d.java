package com.facebook.ads.internal.p046v.p053b.p054a;

import java.io.File;
import java.util.Comparator;

/* renamed from: com.facebook.ads.internal.v.b.a.d */
class C2159d {

    /* renamed from: com.facebook.ads.internal.v.b.a.d$a */
    private static final class C2158a implements Comparator<File> {
        private C2158a() {
        }

        /* renamed from: a */
        public int m5529a(File file, File file2) {
            long lastModified = file.lastModified();
            long lastModified2 = file2.lastModified();
            return lastModified < lastModified2 ? -1 : lastModified == lastModified2 ? 0 : 1;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m5529a((File) obj, (File) obj2);
        }
    }
}
