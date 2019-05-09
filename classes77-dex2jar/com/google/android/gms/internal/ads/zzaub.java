// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzaub
{
    public static zzaxp zza(final String s, String s2, final String s3, final int n, final boolean b) {
        final zzaxp.zza zzee = zzaxp.zzzi().zzee(s2);
        s2 = String.valueOf(s3);
        if (s2.length() != 0) {
            s2 = "type.googleapis.com/google.crypto.tink.".concat(s2);
        }
        else {
            s2 = new String("type.googleapis.com/google.crypto.tink.");
        }
        return (zzaxp)zzee.zzef(s2).zzaz(0).zzao(true).zzeg(s).zzadi();
    }
}
