// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzaxv extends zzbbo<zzaxv, zza> implements zzbcw
{
    private static volatile zzbdf<zzaxv> zzakh;
    private static final zzaxv zzdmc;
    private int zzdih;
    private zzaxx zzdmb;
    
    static {
        zzbbo.zza((Class)zzaxv.class, (zzbbo)(zzdmc = new zzaxv()));
    }
    
    private zzaxv() {
    }
    
    private final void setVersion(final int zzdih) {
        this.zzdih = zzdih;
    }
    
    static /* synthetic */ void zza(final zzaxv zzaxv, final int n) {
        zzaxv.setVersion(0);
    }
    
    private final void zza(final zzaxx zzdmb) {
        if (zzdmb == null) {
            throw new NullPointerException();
        }
        this.zzdmb = zzdmb;
    }
    
    public static zzaxv zzaj(final zzbah zzbah) throws zzbbu {
        return (zzaxv)zzbbo.zza((zzbbo)zzaxv.zzdmc, zzbah);
    }
    
    public static zza zzzz() {
        return (zza)zzaxv.zzdmc.zza(zzbbo$zze.zzdue, null, null);
    }
    
    public final int getVersion() {
        return this.zzdih;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzaxw.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzaxv();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzaxv.zzdmc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[] { "zzdih", "zzdmb" });
            }
            case 4: {
                return zzaxv.zzdmc;
            }
            case 5: {
                if ((zzakh = zzaxv.zzakh) == null) {
                    synchronized (zzaxv.class) {
                        zzbdf<zzaxv> zzakh2;
                        if ((zzakh2 = zzaxv.zzakh) == null) {
                            zzakh2 = (zzaxv.zzakh = (zzbdf<zzaxv>)new zzbbo$zzb((zzbbo)zzaxv.zzdmc));
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
    
    public final zzaxx zzzy() {
        if (this.zzdmb == null) {
            return zzaxx.zzaac();
        }
        return this.zzdmb;
    }
    
    public static final class zza extends zzbbo$zza<zzaxv, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzaxv.zzdmc);
        }
        
        public final zza zzb(final zzaxx zzaxx) {
            this.zzadh();
            ((zzaxv)this.zzdtx).zza(zzaxx);
            return this;
        }
        
        public final zza zzbe(final int n) {
            this.zzadh();
            zzaxv.zza((zzaxv)this.zzdtx, 0);
            return this;
        }
    }
}
