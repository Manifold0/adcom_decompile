// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzaxp extends zzbbo<zzaxp, zza> implements zzbcw
{
    private static volatile zzbdf<zzaxp> zzakh;
    private static final zzaxp zzdlp;
    private String zzdks;
    private String zzdll;
    private int zzdlm;
    private boolean zzdln;
    private String zzdlo;
    
    static {
        zzbbo.zza((Class)zzaxp.class, (zzbbo)(zzdlp = new zzaxp()));
    }
    
    private zzaxp() {
        this.zzdll = "";
        this.zzdks = "";
        this.zzdlo = "";
    }
    
    private final void zzan(final boolean zzdln) {
        this.zzdln = zzdln;
    }
    
    private final void zzay(final int zzdlm) {
        this.zzdlm = zzdlm;
    }
    
    private final void zzea(final String zzdks) {
        if (zzdks == null) {
            throw new NullPointerException();
        }
        this.zzdks = zzdks;
    }
    
    private final void zzec(final String zzdll) {
        if (zzdll == null) {
            throw new NullPointerException();
        }
        this.zzdll = zzdll;
    }
    
    private final void zzed(final String zzdlo) {
        if (zzdlo == null) {
            throw new NullPointerException();
        }
        this.zzdlo = zzdlo;
    }
    
    public static zza zzzi() {
        return (zza)zzaxp.zzdlp.zza(zzbbo$zze.zzdue, null, null);
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzaxq.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzaxp();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzaxp.zzdlp, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0006\u0000\u0000\u0000\u0001\u0208\u0002\u0208\u0003\u000b\u0004\u0007\u0005\u0208", new Object[] { "zzdll", "zzdks", "zzdlm", "zzdln", "zzdlo" });
            }
            case 4: {
                return zzaxp.zzdlp;
            }
            case 5: {
                if ((zzakh = zzaxp.zzakh) == null) {
                    synchronized (zzaxp.class) {
                        zzbdf<zzaxp> zzakh2;
                        if ((zzakh2 = zzaxp.zzakh) == null) {
                            zzakh2 = (zzaxp.zzakh = (zzbdf<zzaxp>)new zzbbo$zzb((zzbbo)zzaxp.zzdlp));
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
    
    public final String zzyw() {
        return this.zzdks;
    }
    
    public final String zzze() {
        return this.zzdll;
    }
    
    public final int zzzf() {
        return this.zzdlm;
    }
    
    public final boolean zzzg() {
        return this.zzdln;
    }
    
    public final String zzzh() {
        return this.zzdlo;
    }
    
    public static final class zza extends zzbbo$zza<zzaxp, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzaxp.zzdlp);
        }
        
        public final zza zzao(final boolean b) {
            this.zzadh();
            ((zzaxp)this.zzdtx).zzan(true);
            return this;
        }
        
        public final zza zzaz(final int n) {
            this.zzadh();
            ((zzaxp)this.zzdtx).zzay(0);
            return this;
        }
        
        public final zza zzee(final String s) {
            this.zzadh();
            ((zzaxp)this.zzdtx).zzec(s);
            return this;
        }
        
        public final zza zzef(final String s) {
            this.zzadh();
            ((zzaxp)this.zzdtx).zzea(s);
            return this;
        }
        
        public final zza zzeg(final String s) {
            this.zzadh();
            ((zzaxp)this.zzdtx).zzed(s);
            return this;
        }
    }
}
