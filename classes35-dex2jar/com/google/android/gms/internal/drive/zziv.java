// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.nio.charset.Charset;

public final class zziv
{
    private static final Charset ISO_8859_1;
    protected static final Charset UTF_8;
    private static final Object zzne;
    
    static {
        UTF_8 = Charset.forName("UTF-8");
        ISO_8859_1 = Charset.forName("ISO-8859-1");
        zzne = new Object();
    }
    
    public static void zza(final zzir zzir, final zzir zzir2) {
        if (zzir.zzmw != null) {
            zzir2.zzmw = (zzit)zzir.zzmw.clone();
        }
    }
}
