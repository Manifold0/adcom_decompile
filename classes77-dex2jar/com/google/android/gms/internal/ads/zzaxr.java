// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.List;

public final class zzaxr extends zzbbo<zzaxr, zza> implements zzbcw
{
    private static volatile zzbdf<zzaxr> zzakh;
    private static final zzaxr zzdlt;
    private int zzdlq;
    private int zzdlr;
    private zzbbt<zzb> zzdls;
    
    static {
        zzbbo.zza((Class)zzaxr.class, (zzbbo)(zzdlt = new zzaxr()));
    }
    
    private zzaxr() {
        this.zzdls = (zzbbt<zzb>)zzadd();
    }
    
    public static zzaxr zzj(final byte[] array) throws zzbbu {
        return (zzaxr)zzbbo.zzb((zzbbo)zzaxr.zzdlt, array);
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzaxs.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzaxr();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzaxr.zzdlt, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[] { "zzdlq", "zzdlr", "zzdls", zzb.class });
            }
            case 4: {
                return zzaxr.zzdlt;
            }
            case 5: {
                if ((zzakh = zzaxr.zzakh) == null) {
                    synchronized (zzaxr.class) {
                        zzbdf<zzaxr> zzakh2;
                        if ((zzakh2 = zzaxr.zzakh) == null) {
                            zzakh2 = (zzaxr.zzakh = (zzbdf<zzaxr>)new zzbbo$zzb((zzbbo)zzaxr.zzdlt));
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
    
    public final int zzzk() {
        return this.zzdlr;
    }
    
    public final List<zzb> zzzl() {
        return (List<zzb>)this.zzdls;
    }
    
    public final int zzzm() {
        return this.zzdls.size();
    }
    
    public static final class zza extends zzbbo$zza<zzaxr, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzaxr.zzdlt);
        }
    }
    
    public static final class zzb extends zzbbo<zzb, zza> implements zzbcw
    {
        private static volatile zzbdf<zzb> zzakh;
        private static final zzb zzdlx;
        private int zzdlj;
        private zzaxi zzdlu;
        private int zzdlv;
        private int zzdlw;
        
        static {
            zzbbo.zza((Class)zzb.class, (zzbbo)(zzdlx = new zzb()));
        }
        
        private zzb() {
        }
        
        protected final Object zza(final int n, final Object o, final Object o2) {
            Object zzakh = null;
            switch (zzaxs.zzakf[n - 1]) {
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
                    return zza((zzbcu)zzb.zzdlx, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000b\u0004\f", new Object[] { "zzdlu", "zzdlv", "zzdlw", "zzdlj" });
                }
                case 4: {
                    return zzb.zzdlx;
                }
                case 5: {
                    if ((zzakh = zzb.zzakh) == null) {
                        synchronized (zzb.class) {
                            zzbdf<zzb> zzakh2;
                            if ((zzakh2 = zzb.zzakh) == null) {
                                zzakh2 = (zzb.zzakh = (zzbdf<zzb>)new zzbbo$zzb((zzbbo)zzb.zzdlx));
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
        
        public final boolean zzzo() {
            return this.zzdlu != null;
        }
        
        public final zzaxi zzzp() {
            if (this.zzdlu == null) {
                return zzaxi.zzza();
            }
            return this.zzdlu;
        }
        
        public final zzaxl zzzq() {
            zzaxl zzaxl;
            if ((zzaxl = com.google.android.gms.internal.ads.zzaxl.zzax(this.zzdlv)) == null) {
                zzaxl = com.google.android.gms.internal.ads.zzaxl.zzdlh;
            }
            return zzaxl;
        }
        
        public final int zzzr() {
            return this.zzdlw;
        }
        
        public final zzayd zzzs() {
            zzayd zzayd;
            if ((zzayd = com.google.android.gms.internal.ads.zzayd.zzbg(this.zzdlj)) == null) {
                zzayd = com.google.android.gms.internal.ads.zzayd.zzdmp;
            }
            return zzayd;
        }
        
        public static final class zza extends zzbbo$zza<zzb, zza> implements zzbcw
        {
            private zza() {
                super((zzbbo)zzb.zzdlx);
            }
        }
    }
}
