// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;

public final class zzbfg
{
    private static final Charset ISO_8859_1;
    protected static final Charset UTF_8;
    public static final Object zzebs;
    
    static {
        UTF_8 = Charset.forName("UTF-8");
        ISO_8859_1 = Charset.forName("ISO-8859-1");
        zzebs = new Object();
    }
    
    public static void zza(final zzbfc zzbfc, final zzbfc zzbfc2) {
        if (zzbfc.zzebk != null) {
            zzbfc2.zzebk = (zzbfe)zzbfc.zzebk.clone();
        }
    }
}
