// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.util.Log;

@zzadh
public final class zzakb extends zzane
{
    public static void v(final String s) {
        if (zzqp()) {
            Log.v("Ads", s);
        }
    }
    
    public static boolean zzqp() {
        return isLoggable(2) && (boolean)zzkb.zzik().zzd(zznk.zzazr);
    }
}
