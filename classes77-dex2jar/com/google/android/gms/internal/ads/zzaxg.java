// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzaxg extends zzbbo<zzaxg, zza> implements zzbcw
{
    private static volatile zzbdf<zzaxg> zzakh;
    private static final zzaxg zzdkr;
    private int zzdkp;
    private int zzdkq;
    
    static {
        zzbbo.zza((Class)zzaxg.class, (zzbbo)(zzdkr = new zzaxg()));
    }
    
    private zzaxg() {
    }
    
    public static zzaxg zzyu() {
        return zzaxg.zzdkr;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzaxh.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzaxg();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzaxg.zzdkr, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[] { "zzdkp", "zzdkq" });
            }
            case 4: {
                return zzaxg.zzdkr;
            }
            case 5: {
                if ((zzakh = zzaxg.zzakh) == null) {
                    synchronized (zzaxg.class) {
                        zzbdf<zzaxg> zzakh2;
                        if ((zzakh2 = zzaxg.zzakh) == null) {
                            zzakh2 = (zzaxg.zzakh = (zzbdf<zzaxg>)new zzbbo$zzb((zzbbo)zzaxg.zzdkr));
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
    
    public final zzaxa zzys() {
        zzaxa zzaxa;
        if ((zzaxa = com.google.android.gms.internal.ads.zzaxa.zzau(this.zzdkp)) == null) {
            zzaxa = com.google.android.gms.internal.ads.zzaxa.zzdkk;
        }
        return zzaxa;
    }
    
    public final int zzyt() {
        return this.zzdkq;
    }
    
    public static final class zza extends zzbbo$zza<zzaxg, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzaxg.zzdkr);
        }
    }
}
