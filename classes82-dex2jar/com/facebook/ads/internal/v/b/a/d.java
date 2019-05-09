// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.b.a;

import java.io.File;
import java.util.Comparator;

class d
{
    private static final class a implements Comparator<File>
    {
        public int a(final File file, final File file2) {
            final long lastModified = file.lastModified();
            final long lastModified2 = file2.lastModified();
            if (lastModified < lastModified2) {
                return -1;
            }
            if (lastModified == lastModified2) {
                return 0;
            }
            return 1;
        }
    }
}
