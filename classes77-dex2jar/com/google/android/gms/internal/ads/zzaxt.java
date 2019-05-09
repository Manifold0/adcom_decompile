// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzaxt extends zzbbo<zzaxt, zza> implements zzbcw
{
    private static volatile zzbdf<zzaxt> zzakh;
    private static final zzaxt zzdlz;
    private int zzdlq;
    private int zzdlr;
    private zzbbt<zzb> zzdly;
    
    static {
        zzbbo.zza((Class)zzaxt.class, (zzbbo)(zzdlz = new zzaxt()));
    }
    
    private zzaxt() {
        this.zzdly = (zzbbt<zzb>)zzadd();
    }
    
    private final void zza(final zzb zzb) {
        if (zzb == null) {
            throw new NullPointerException();
        }
        if (!this.zzdly.zzaay()) {
            final zzbbt<zzb> zzdly = this.zzdly;
            final int size = zzdly.size();
            int n;
            if (size == 0) {
                n = 10;
            }
            else {
                n = size << 1;
            }
            this.zzdly = (zzbbt<zzb>)zzdly.zzbm(n);
        }
        this.zzdly.add((Object)zzb);
    }
    
    private final void zzba(final int zzdlr) {
        this.zzdlr = zzdlr;
    }
    
    public static zza zzzu() {
        return (zza)zzaxt.zzdlz.zza(zzbbo$zze.zzdue, null, null);
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzaxu.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzaxt();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzaxt.zzdlz, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[] { "zzdlq", "zzdlr", "zzdly", zzb.class });
            }
            case 4: {
                return zzaxt.zzdlz;
            }
            case 5: {
                if ((zzakh = zzaxt.zzakh) == null) {
                    synchronized (zzaxt.class) {
                        zzbdf<zzaxt> zzakh2;
                        if ((zzakh2 = zzaxt.zzakh) == null) {
                            zzakh2 = (zzaxt.zzakh = (zzbdf<zzaxt>)new zzbbo$zzb((zzbbo)zzaxt.zzdlz));
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
    
    public static final class zza extends zzbbo$zza<zzaxt, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzaxt.zzdlz);
        }
        
        public final zza zzb(final zzb zzb) {
            this.zzadh();
            ((zzaxt)this.zzdtx).zza(zzb);
            return this;
        }
        
        public final zza zzbb(final int n) {
            this.zzadh();
            ((zzaxt)this.zzdtx).zzba(n);
            return this;
        }
    }
    
    public static final class zzb extends zzbbo<zzb, zza> implements zzbcw
    {
        private static volatile zzbdf<zzb> zzakh;
        private static final zzb zzdma;
        private String zzdks;
        private int zzdlj;
        private int zzdlv;
        private int zzdlw;
        
        static {
            zzbbo.zza((Class)zzb.class, (zzbbo)(zzdma = new zzb()));
        }
        
        private zzb() {
            this.zzdks = "";
        }
        
        private final void zza(final zzaxl zzaxl) {
            if (zzaxl == null) {
                throw new NullPointerException();
            }
            this.zzdlv = zzaxl.zzhq();
        }
        
        private final void zza(final zzayd zzayd) {
            if (zzayd == null) {
                throw new NullPointerException();
            }
            this.zzdlj = zzayd.zzhq();
        }
        
        private final void zzbc(final int zzdlw) {
            this.zzdlw = zzdlw;
        }
        
        private final void zzea(final String zzdks) {
            if (zzdks == null) {
                throw new NullPointerException();
            }
            this.zzdks = zzdks;
        }
        
        public static zza zzzw() {
            return (zza)zzb.zzdma.zza(zzbbo$zze.zzdue, null, null);
        }
        
        protected final Object zza(final int n, final Object o, final Object o2) {
            Object zzakh = null;
            switch (zzaxu.zzakf[n - 1]) {
                default: {
                    throw new UnsupportedOperationException();
                }
                case 1: {
                    zzakh = new zzb();
                    break;
                }
                case 2: {
                    return new zza(null);
                }
                case 3: {
                    return zza((zzbcu)zzb.zzdma, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\u0208\u0002\f\u0003\u000b\u0004\f", new Object[] { "zzdks", "zzdlv", "zzdlw", "zzdlj" });
                }
                case 4: {
                    return zzb.zzdma;
                }
                case 5: {
                    if ((zzakh = zzb.zzakh) == null) {
                        synchronized (zzb.class) {
                            zzbdf<zzb> zzakh2;
                            if ((zzakh2 = zzb.zzakh) == null) {
                                zzakh2 = (zzb.zzakh = (zzbdf<zzb>)new zzbbo$zzb((zzbbo)zzb.zzdma));
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
        
        public static final class zza extends zzbbo$zza<zzb, zza> implements zzbcw
        {
            private zza() {
                super((zzbbo)zzb.zzdma);
            }
            
            public final zza zzb(final zzaxl zzaxl) {
                this.zzadh();
                ((zzb)this.zzdtx).zza(zzaxl);
                return this;
            }
            
            public final zza zzb(final zzayd zzayd) {
                this.zzadh();
                ((zzb)this.zzdtx).zza(zzayd);
                return this;
            }
            
            public final zza zzbd(final int n) {
                this.zzadh();
                ((zzb)this.zzdtx).zzbc(n);
                return this;
            }
            
            public final zza zzeh(final String s) {
                this.zzadh();
                ((zzb)this.zzdtx).zzea(s);
                return this;
            }
        }
    }
}
