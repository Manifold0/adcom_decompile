// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamite.DynamiteModule;
import android.content.Context;

@zzadh
public final class zzadv
{
    private static boolean zzc(final Context context, final boolean b) {
        if (b) {
            final int remoteVersion = DynamiteModule.getRemoteVersion(context, "com.google.android.gms.ads.dynamite");
            if (remoteVersion == 0) {
                return false;
            }
            if (remoteVersion > DynamiteModule.getLocalVersion(context, "com.google.android.gms.ads.dynamite")) {
                return false;
            }
        }
        return true;
    }
}
