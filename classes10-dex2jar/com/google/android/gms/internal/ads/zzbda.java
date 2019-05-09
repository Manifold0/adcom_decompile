// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;
import java.io.IOException;

final class zzbda<T> implements zzbdm<T>
{
    private final zzbcu zzdwl;
    private final boolean zzdwm;
    private final zzbee<?, ?> zzdwv;
    private final zzbbd<?> zzdww;
    
    private zzbda(final zzbee<?, ?> zzdwv, final zzbbd<?> zzdww, final zzbcu zzdwl) {
        this.zzdwv = zzdwv;
        this.zzdwm = zzdww.zzh(zzdwl);
        this.zzdww = zzdww;
        this.zzdwl = zzdwl;
    }
    
    static <T> zzbda<T> zza(final zzbee<?, ?> zzbee, final zzbbd<?> zzbbd, final zzbcu zzbcu) {
        return new zzbda<T>(zzbee, zzbbd, zzbcu);
    }
    
    @Override
    public final boolean equals(final T t, final T t2) {
        return this.zzdwv.zzac(t).equals(this.zzdwv.zzac(t2)) && (!this.zzdwm || this.zzdww.zzm(t).equals(this.zzdww.zzm(t2)));
    }
    
    @Override
    public final int hashCode(final T t) {
        int hashCode = this.zzdwv.zzac(t).hashCode();
        if (this.zzdwm) {
            hashCode = hashCode * 53 + this.zzdww.zzm(t).hashCode();
        }
        return hashCode;
    }
    
    @Override
    public final T newInstance() {
        return (T)this.zzdwl.zzadf().zzadj();
    }
    
    @Override
    public final void zza(final T t, final zzbdl zzbdl, final zzbbb zzbbb) throws IOException {
        final zzbee<?, ?> zzdwv = this.zzdwv;
        final zzbbd<?> zzdww = this.zzdww;
        final Object zzad = zzdwv.zzad(t);
        final zzbbg<?> zzn = zzdww.zzn(t);
    Label_0149_Outer:
        while (true) {
        Label_0149:
            while (true) {
                Label_0311: {
                    while (true) {
                        try {
                            Object o;
                            while (true) {
                                int n = zzbdl.zzaci();
                                if (n == Integer.MAX_VALUE) {
                                    return;
                                }
                                n = zzbdl.getTag();
                                if (n == 11) {
                                    break Label_0311;
                                }
                                boolean b;
                                if ((n & 0x7) == 0x2) {
                                    o = zzdww.zza(zzbbb, this.zzdwl, n >>> 3);
                                    if (o != null) {
                                        zzdww.zza(zzbdl, o, zzbbb, zzn);
                                        b = true;
                                    }
                                    else {
                                        b = zzdwv.zza(zzad, zzbdl);
                                    }
                                }
                                else {
                                    b = zzbdl.zzacj();
                                }
                                if (!b) {
                                    return;
                                }
                            }
                            // iftrue(Label_0200:, tag != 16)
                            // iftrue(Label_0248:, tag != 26)
                            // iftrue(Label_0257:, zzbdl.zzaci() == 2147483647)
                        Block_12:
                            while (true) {
                                while (true) {
                                    final int n = zzbdl.zzabt();
                                    o = zzdww.zza(zzbbb, this.zzdwl, n);
                                    break Label_0149;
                                    final int tag = zzbdl.getTag();
                                    continue Label_0149_Outer;
                                }
                                Label_0200: {
                                    break Block_12;
                                }
                                continue;
                            }
                            // iftrue(Label_0237:, o == null)
                            zzdww.zza(zzbdl, o, zzbbb, zzn);
                            continue Label_0149;
                        }
                        finally {
                            zzdwv.zzf(t, zzad);
                        }
                        Label_0237: {
                            final zzbah zzabs = zzbdl.zzabs();
                        }
                        continue Label_0149;
                        Label_0248:
                        if (zzbdl.zzacj()) {
                            continue Label_0149;
                        }
                        Label_0257:
                        if (zzbdl.getTag() != 12) {
                            break;
                        }
                        final zzbah zzabs;
                        if (zzabs == null) {
                            continue Label_0149_Outer;
                        }
                        Object o = null;
                        if (o != null) {
                            zzdww.zza(zzabs, o, zzbbb, zzn);
                            continue Label_0149_Outer;
                        }
                        int n = 0;
                        zzdwv.zza(zzad, n, zzabs);
                        continue Label_0149_Outer;
                    }
                }
                final zzbah zzabs = null;
                int n = 0;
                Object o = null;
                continue Label_0149;
            }
        }
        throw zzbbu.zzadp();
    }
    
    @Override
    public final void zza(final T t, final zzbey zzbey) throws IOException {
        for (final Map.Entry<zzbbi, V> entry : this.zzdww.zzm(t)) {
            final zzbbi zzbbi = entry.getKey();
            if (zzbbi.zzacz() != zzbex.zzebd || zzbbi.zzada() || zzbbi.zzadb()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof zzbbz) {
                zzbey.zza(zzbbi.zzhq(), (Object)((zzbbz)entry).zzadv().zzaav());
            }
            else {
                zzbey.zza(zzbbi.zzhq(), entry.getValue());
            }
        }
        final zzbee<?, ?> zzdwv = this.zzdwv;
        zzdwv.zzc(zzdwv.zzac(t), zzbey);
    }
    
    @Override
    public final void zza(final T t, final byte[] array, int n, final int n2, final zzbae zzbae) throws IOException {
        zzbef zzdtt;
        final zzbef zzbef = zzdtt = ((zzbbo)t).zzdtt;
        int i = n;
        if (zzbef == com.google.android.gms.internal.ads.zzbef.zzagc()) {
            zzdtt = com.google.android.gms.internal.ads.zzbef.zzagd();
            ((zzbbo)t).zzdtt = zzdtt;
            i = n;
        }
        while (i < n2) {
            n = zzbad.zza(array, i, zzbae);
            final int zzdpl = zzbae.zzdpl;
            if (zzdpl != 11) {
                if ((zzdpl & 0x7) == 0x2) {
                    i = zzbad.zza(zzdpl, array, n, n2, zzdtt, zzbae);
                }
                else {
                    i = zzbad.zza(zzdpl, array, n, n2, zzbae);
                }
            }
            else {
                int zzdpl2 = 0;
                Object o = null;
                while (true) {
                    i = n;
                    if (n >= n2) {
                        break;
                    }
                    n = zzbad.zza(array, n, zzbae);
                    final int zzdpl3 = zzbae.zzdpl;
                    final int n3 = zzdpl3 & 0x7;
                    switch (zzdpl3 >>> 3) {
                        case 2: {
                            if (n3 == 0) {
                                n = zzbad.zza(array, n, zzbae);
                                zzdpl2 = zzbae.zzdpl;
                                continue;
                            }
                            break;
                        }
                        case 3: {
                            if (n3 == 2) {
                                n = zzbad.zze(array, n, zzbae);
                                o = zzbae.zzdpn;
                                continue;
                            }
                            break;
                        }
                    }
                    i = n;
                    if (zzdpl3 == 12) {
                        break;
                    }
                    n = zzbad.zza(zzdpl3, array, n, n2, zzbae);
                }
                if (o == null) {
                    continue;
                }
                zzdtt.zzb(zzdpl2 << 3 | 0x2, o);
            }
        }
        if (i != n2) {
            throw zzbbu.zzadr();
        }
    }
    
    @Override
    public final boolean zzaa(final T t) {
        return this.zzdww.zzm(t).isInitialized();
    }
    
    @Override
    public final void zzc(final T t, final T t2) {
        zzbdo.zza(this.zzdwv, t, t2);
        if (this.zzdwm) {
            zzbdo.zza(this.zzdww, t, t2);
        }
    }
    
    @Override
    public final void zzo(final T t) {
        this.zzdwv.zzo(t);
        this.zzdww.zzo(t);
    }
    
    @Override
    public final int zzy(final T t) {
        final zzbee<?, ?> zzdwv = this.zzdwv;
        int n = zzdwv.zzae(zzdwv.zzac(t)) + 0;
        if (this.zzdwm) {
            n += this.zzdww.zzm(t).zzacx();
        }
        return n;
    }
}
