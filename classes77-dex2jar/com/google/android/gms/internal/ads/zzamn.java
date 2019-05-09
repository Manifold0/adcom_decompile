// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;

@zzadh
@TargetApi(17)
public final class zzamn
{
    private static zzamn zzcua;
    String zzcpq;
    
    static {
        zzamn.zzcua = null;
    }
    
    private zzamn() {
    }
    
    public static zzamn zzsb() {
        if (zzamn.zzcua == null) {
            zzamn.zzcua = new zzamn();
        }
        return zzamn.zzcua;
    }
}
