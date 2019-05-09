// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.security.GeneralSecurityException;
import java.util.concurrent.CopyOnWriteArrayList;

public final class zzauj
{
    private static final CopyOnWriteArrayList<zzaui> zzdhj;
    
    static {
        zzdhj = new CopyOnWriteArrayList<zzaui>();
    }
    
    public static zzaui zzdx(String o) throws GeneralSecurityException {
        for (final zzaui zzaui : zzauj.zzdhj) {
            if (zzaui.zzdv((String)o)) {
                return zzaui;
            }
        }
        o = String.valueOf(o);
        if (((String)o).length() != 0) {
            o = "No KMS client does support: ".concat((String)o);
        }
        else {
            o = new String("No KMS client does support: ");
        }
        throw new GeneralSecurityException((String)o);
    }
}
