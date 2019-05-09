// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzawu extends zzbbo<zzawu, zza> implements zzbcw
{
    private static volatile zzbdf<zzawu> zzakh;
    private static final zzawu zzdjt;
    private int zzdih;
    private zzawq zzdjj;
    private zzbah zzdjr;
    private zzbah zzdjs;
    
    static {
        zzbbo.zza((Class)zzawu.class, (zzbbo)(zzdjt = new zzawu()));
    }
    
    private zzawu() {
        this.zzdjr = zzbah.zzdpq;
        this.zzdjs = zzbah.zzdpq;
    }
    
    private final void setVersion(final int zzdih) {
        this.zzdih = zzdih;
    }
    
    static /* synthetic */ void zza(final zzawu zzawu, final int n) {
        zzawu.setVersion(0);
    }
    
    private final void zzaa(final zzbah zzdjs) {
        if (zzdjs == null) {
            throw new NullPointerException();
        }
        this.zzdjs = zzdjs;
    }
    
    public static zzawu zzab(final zzbah zzbah) throws zzbbu {
        return (zzawu)zzbbo.zza((zzbbo)zzawu.zzdjt, zzbah);
    }
    
    private final void zzb(final zzawq zzdjj) {
        if (zzdjj == null) {
            throw new NullPointerException();
        }
        this.zzdjj = zzdjj;
    }
    
    public static zza zzye() {
        return (zza)zzawu.zzdjt.zza(zzbbo$zze.zzdue, null, null);
    }
    
    public static zzawu zzyf() {
        return zzawu.zzdjt;
    }
    
    private final void zzz(final zzbah zzdjr) {
        if (zzdjr == null) {
            throw new NullPointerException();
        }
        this.zzdjr = zzdjr;
    }
    
    public final int getVersion() {
        return this.zzdih;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzawv.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzawu();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzawu.zzdjt, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n\u0004\n", new Object[] { "zzdih", "zzdjj", "zzdjr", "zzdjs" });
            }
            case 4: {
                return zzawu.zzdjt;
            }
            case 5: {
                if ((zzakh = zzawu.zzakh) == null) {
                    synchronized (zzawu.class) {
                        zzbdf<zzawu> zzakh2;
                        if ((zzakh2 = zzawu.zzakh) == null) {
                            zzakh2 = (zzawu.zzakh = (zzbdf<zzawu>)new zzbbo$zzb((zzbbo)zzawu.zzdjt));
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
    
    public final zzawq zzxs() {
        if (this.zzdjj == null) {
            return zzawq.zzxx();
        }
        return this.zzdjj;
    }
    
    public final zzbah zzyc() {
        return this.zzdjr;
    }
    
    public final zzbah zzyd() {
        return this.zzdjs;
    }
    
    public static final class zza extends zzbbo$zza<zzawu, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzawu.zzdjt);
        }
        
        public final zza zzac(final zzbah zzbah) {
            this.zzadh();
            ((zzawu)this.zzdtx).zzz(zzbah);
            return this;
        }
        
        public final zza zzad(final zzbah zzbah) {
            this.zzadh();
            ((zzawu)this.zzdtx).zzaa(zzbah);
            return this;
        }
        
        public final zza zzas(final int n) {
            this.zzadh();
            zzawu.zza((zzawu)this.zzdtx, 0);
            return this;
        }
        
        public final zza zzc(final zzawq zzawq) {
            this.zzadh();
            ((zzawu)this.zzdtx).zzb(zzawq);
            return this;
        }
    }
}
