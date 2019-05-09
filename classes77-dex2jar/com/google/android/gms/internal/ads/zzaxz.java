// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzaxz extends zzbbo<zzaxz, zza> implements zzbcw
{
    private static volatile zzbdf<zzaxz> zzakh;
    private static final zzaxz zzdmg;
    private int zzdih;
    private zzayb zzdmf;
    
    static {
        zzbbo.zza((Class)zzaxz.class, (zzbbo)(zzdmg = new zzaxz()));
    }
    
    private zzaxz() {
    }
    
    private final void setVersion(final int zzdih) {
        this.zzdih = zzdih;
    }
    
    static /* synthetic */ void zza(final zzaxz zzaxz, final int n) {
        zzaxz.setVersion(0);
    }
    
    private final void zza(final zzayb zzdmf) {
        if (zzdmf == null) {
            throw new NullPointerException();
        }
        this.zzdmf = zzdmf;
    }
    
    public static zza zzaaf() {
        return (zza)zzaxz.zzdmg.zza(zzbbo$zze.zzdue, null, null);
    }
    
    public static zzaxz zzal(final zzbah zzbah) throws zzbbu {
        return (zzaxz)zzbbo.zza((zzbbo)zzaxz.zzdmg, zzbah);
    }
    
    public final int getVersion() {
        return this.zzdih;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzaya.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzaxz();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzaxz.zzdmg, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[] { "zzdih", "zzdmf" });
            }
            case 4: {
                return zzaxz.zzdmg;
            }
            case 5: {
                if ((zzakh = zzaxz.zzakh) == null) {
                    synchronized (zzaxz.class) {
                        zzbdf<zzaxz> zzakh2;
                        if ((zzakh2 = zzaxz.zzakh) == null) {
                            zzakh2 = (zzaxz.zzakh = (zzbdf<zzaxz>)new zzbbo$zzb((zzbbo)zzaxz.zzdmg));
                        }
                        return zzakh2;
                    }
                    return 1;
                }
                break;
            }
            case 6: {
                return 1;
            }
            case 7: {
                return null;
            }
        }
        return zzakh;
    }
    
    public final zzayb zzaae() {
        if (this.zzdmf == null) {
            return zzayb.zzaaj();
        }
        return this.zzdmf;
    }
    
    public static final class zza extends zzbbo$zza<zzaxz, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzaxz.zzdmg);
        }
        
        public final zza zzb(final zzayb zzayb) {
            this.zzadh();
            ((zzaxz)this.zzdtx).zza(zzayb);
            return this;
        }
        
        public final zza zzbf(final int n) {
            this.zzadh();
            zzaxz.zza((zzaxz)this.zzdtx, 0);
            return this;
        }
    }
}
