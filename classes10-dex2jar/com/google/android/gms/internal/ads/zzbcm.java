// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbcm<K, V>
{
    static <K, V> int zza(final zzbcn<K, V> zzbcn, final K k, final V v) {
        return zzbbg.zza(zzbcn.zzdvy, 1, k) + zzbbg.zza(zzbcn.zzdwa, 2, v);
    }
    
    static <K, V> void zza(final zzbav zzbav, final zzbcn<K, V> zzbcn, final K k, final V v) throws IOException {
        zzbbg.zza(zzbav, zzbcn.zzdvy, 1, k);
        zzbbg.zza(zzbav, zzbcn.zzdwa, 2, v);
    }
}
