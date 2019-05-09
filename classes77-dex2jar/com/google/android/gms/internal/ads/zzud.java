// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
final class zzud
{
    private static final zzua zzbpe;
    private static final float zzbpf;
    private static final long zzbpg;
    private static final float zzbph;
    private static final long zzbpi;
    
    static {
        zzbpe = zzua.zzlk();
        zzbpf = (float)zzkb.zzik().zzd(zznk.zzazk);
        zzbpg = (long)zzkb.zzik().zzd(zznk.zzazi);
        zzbph = (float)zzkb.zzik().zzd(zznk.zzazl);
        zzbpi = (long)zzkb.zzik().zzd(zznk.zzazj);
    }
    
    @VisibleForTesting
    private static int zzb(final long n, final int n2) {
        return (int)(n >>> n2 % 16 * 4 & 0xFL);
    }
    
    static boolean zzlv() {
        final int n = Integer.MAX_VALUE;
        final int zzlr = zzud.zzbpe.zzlr();
        final int zzls = zzud.zzbpe.zzls();
        final int zzlq = zzud.zzbpe.zzlq();
        final int zzlp = zzud.zzbpe.zzlp();
        int zzb;
        if (zzlr < 16 && zzud.zzbpi != 0L) {
            zzb = zzb(zzud.zzbpi, zzlr);
        }
        else if (zzud.zzbph != 0.0f) {
            zzb = (int)(zzud.zzbph * zzlr) + 1;
        }
        else {
            zzb = Integer.MAX_VALUE;
        }
        if (zzls <= zzb) {
            int zzb2;
            if (zzlr < 16 && zzud.zzbpg != 0L) {
                zzb2 = zzb(zzud.zzbpg, zzlr);
            }
            else {
                zzb2 = n;
                if (zzud.zzbpf != 0.0f) {
                    zzb2 = (int)(zzud.zzbpf * zzlr);
                }
            }
            if (zzlp + zzlq <= zzb2) {
                return true;
            }
        }
        return false;
    }
}
