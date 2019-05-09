// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzhu
{
    public static final class zza extends zzbbo<zzhu.zza, zzhu.zza.zza> implements zzbcw
    {
        private static final zzhu.zza zzakg;
        private static volatile zzbdf<zzhu.zza> zzakh;
        
        static {
            zzbbo.zza((Class)zzhu.zza.class, (zzbbo)(zzakg = new zzhu.zza()));
        }
        
        private zza() {
        }
        
        protected final Object zza(final int n, final Object o, final Object o2) {
            Object zzakh = null;
            switch (zzhv.zzakf[n - 1]) {
                default: {
                    throw new UnsupportedOperationException();
                }
                case 1: {
                    zzakh = new zzhu.zza();
                    break;
                }
                case 2: {
                    return new zzhu.zza.zza(null);
                }
                case 3: {
                    return zza((zzbcu)zzhu.zza.zzakg, "\u0001\u0000", (Object[])null);
                }
                case 4: {
                    return zzhu.zza.zzakg;
                }
                case 5: {
                    if ((zzakh = zzhu.zza.zzakh) == null) {
                        synchronized (zzhu.zza.class) {
                            zzbdf<zzhu.zza> zzakh2;
                            if ((zzakh2 = zzhu.zza.zzakh) == null) {
                                zzakh2 = (zzhu.zza.zzakh = (zzbdf<zzhu.zza>)new zzbbo$zzb((zzbbo)zzhu.zza.zzakg));
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
        
        public static final class zza extends zzbbo$zza<zzhu.zza, zza> implements zzbcw
        {
            private zza() {
                super((zzbbo)zzhu.zza.zzakg);
            }
        }
        
        public enum zzb implements zzbbr
        {
            zzaki(0), 
            zzakj(1), 
            zzakk(2), 
            zzakl(3), 
            zzakm(4), 
            zzakn(5), 
            zzako(6), 
            zzakp(7), 
            zzakq(8), 
            zzakr(9), 
            zzaks(10), 
            zzakt(11), 
            zzaku(12), 
            zzakv(13), 
            zzakw(14), 
            zzakx(15), 
            zzaky(16), 
            zzakz(17), 
            zzala(18), 
            zzalb(19), 
            zzalc(1000), 
            zzald(1001), 
            zzale(1002), 
            zzalf(1003), 
            zzalg(1004), 
            zzalh(1005), 
            zzali(1006), 
            zzalj(10000), 
            zzalk(10001);
            
            private static final zzbbs<zzb> zzall;
            private final int value;
            
            static {
                zzall = (zzbbs)new zzhw();
            }
            
            private zzb(final int value) {
                this.value = value;
            }
            
            public static zzb zzp(final int n) {
                switch (n) {
                    default: {
                        return null;
                    }
                    case 0: {
                        return zzb.zzaki;
                    }
                    case 1: {
                        return zzb.zzakj;
                    }
                    case 2: {
                        return zzb.zzakk;
                    }
                    case 3: {
                        return zzb.zzakl;
                    }
                    case 4: {
                        return zzb.zzakm;
                    }
                    case 5: {
                        return zzb.zzakn;
                    }
                    case 6: {
                        return zzb.zzako;
                    }
                    case 7: {
                        return zzb.zzakp;
                    }
                    case 8: {
                        return zzb.zzakq;
                    }
                    case 9: {
                        return zzb.zzakr;
                    }
                    case 10: {
                        return zzb.zzaks;
                    }
                    case 11: {
                        return zzb.zzakt;
                    }
                    case 12: {
                        return zzb.zzaku;
                    }
                    case 13: {
                        return zzb.zzakv;
                    }
                    case 14: {
                        return zzb.zzakw;
                    }
                    case 15: {
                        return zzb.zzakx;
                    }
                    case 16: {
                        return zzb.zzaky;
                    }
                    case 17: {
                        return zzb.zzakz;
                    }
                    case 18: {
                        return zzb.zzala;
                    }
                    case 19: {
                        return zzb.zzalb;
                    }
                    case 1000: {
                        return zzb.zzalc;
                    }
                    case 1001: {
                        return zzb.zzald;
                    }
                    case 1002: {
                        return zzb.zzale;
                    }
                    case 1003: {
                        return zzb.zzalf;
                    }
                    case 1004: {
                        return zzb.zzalg;
                    }
                    case 1005: {
                        return zzb.zzalh;
                    }
                    case 1006: {
                        return zzb.zzali;
                    }
                    case 10000: {
                        return zzb.zzalj;
                    }
                    case 10001: {
                        return zzb.zzalk;
                    }
                }
            }
            
            public final int zzhq() {
                return this.value;
            }
        }
    }
}
