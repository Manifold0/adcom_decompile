// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

abstract class zzbee<T, B>
{
    abstract void zza(final B p0, final int p1, final long p2);
    
    abstract void zza(final B p0, final int p1, final zzbah p2);
    
    abstract void zza(final B p0, final int p1, final T p2);
    
    abstract void zza(final T p0, final zzbey p1) throws IOException;
    
    abstract boolean zza(final zzbdl p0);
    
    final boolean zza(final B b, final zzbdl zzbdl) throws IOException {
        final int tag = zzbdl.getTag();
        final int n = tag >>> 3;
        switch (tag & 0x7) {
            default: {
                throw zzbbu.zzadq();
            }
            case 0: {
                this.zza(b, n, zzbdl.zzabm());
                return true;
            }
            case 5: {
                this.zzc(b, n, zzbdl.zzabp());
                return true;
            }
            case 1: {
                this.zzb(b, n, zzbdl.zzabo());
                return true;
            }
            case 2: {
                this.zza(b, n, zzbdl.zzabs());
                return true;
            }
            case 3: {
                final B zzagb = this.zzagb();
                while (zzbdl.zzaci() != Integer.MAX_VALUE && this.zza(zzagb, zzbdl)) {}
                if ((n << 3 | 0x4) != zzbdl.getTag()) {
                    throw zzbbu.zzadp();
                }
                this.zza(b, n, this.zzv(zzagb));
                return true;
            }
            case 4: {
                return false;
            }
        }
    }
    
    abstract T zzac(final Object p0);
    
    abstract B zzad(final Object p0);
    
    abstract int zzae(final T p0);
    
    abstract B zzagb();
    
    abstract void zzb(final B p0, final int p1, final long p2);
    
    abstract void zzc(final B p0, final int p1, final int p2);
    
    abstract void zzc(final T p0, final zzbey p1) throws IOException;
    
    abstract void zze(final Object p0, final T p1);
    
    abstract void zzf(final Object p0, final B p1);
    
    abstract T zzg(final T p0, final T p1);
    
    abstract void zzo(final Object p0);
    
    abstract T zzv(final B p0);
    
    abstract int zzy(final T p0);
}
