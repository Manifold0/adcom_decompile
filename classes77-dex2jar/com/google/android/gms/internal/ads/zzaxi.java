// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzaxi extends zzbbo<zzaxi, zza> implements zzbcw
{
    private static volatile zzbdf<zzaxi> zzakh;
    private static final zzaxi zzdkv;
    private String zzdks;
    private zzbah zzdkt;
    private int zzdku;
    
    static {
        zzbbo.zza((Class)zzaxi.class, (zzbbo)(zzdkv = new zzaxi()));
    }
    
    private zzaxi() {
        this.zzdks = "";
        this.zzdkt = zzbah.zzdpq;
    }
    
    private final void zza(final zzb zzb) {
        if (zzb == null) {
            throw new NullPointerException();
        }
        this.zzdku = zzb.zzhq();
    }
    
    private final void zzah(final zzbah zzdkt) {
        if (zzdkt == null) {
            throw new NullPointerException();
        }
        this.zzdkt = zzdkt;
    }
    
    private final void zzea(final String zzdks) {
        if (zzdks == null) {
            throw new NullPointerException();
        }
        this.zzdks = zzdks;
    }
    
    public static zza zzyz() {
        return (zza)zzaxi.zzdkv.zza(zzbbo$zze.zzdue, null, null);
    }
    
    public static zzaxi zzza() {
        return zzaxi.zzdkv;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzaxj.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzaxi();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzaxi.zzdkv, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u0208\u0002\n\u0003\f", new Object[] { "zzdks", "zzdkt", "zzdku" });
            }
            case 4: {
                return zzaxi.zzdkv;
            }
            case 5: {
                if ((zzakh = zzaxi.zzakh) == null) {
                    synchronized (zzaxi.class) {
                        zzbdf<zzaxi> zzakh2;
                        if ((zzakh2 = zzaxi.zzakh) == null) {
                            zzakh2 = (zzaxi.zzakh = (zzbdf<zzaxi>)new zzbbo$zzb((zzbbo)zzaxi.zzdkv));
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
    
    public final zzbah zzyx() {
        return this.zzdkt;
    }
    
    public final zzb zzyy() {
        zzb zzb;
        if ((zzb = zzaxi.zzb.zzaw(this.zzdku)) == null) {
            zzb = zzaxi.zzb.zzdlb;
        }
        return zzb;
    }
    
    public static final class zza extends zzbbo$zza<zzaxi, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzaxi.zzdkv);
        }
        
        public final zza zzai(final zzbah zzbah) {
            this.zzadh();
            ((zzaxi)this.zzdtx).zzah(zzbah);
            return this;
        }
        
        public final zza zzb(final zzb zzb) {
            this.zzadh();
            ((zzaxi)this.zzdtx).zza(zzb);
            return this;
        }
        
        public final zza zzeb(final String s) {
            this.zzadh();
            ((zzaxi)this.zzdtx).zzea(s);
            return this;
        }
    }
    
    public enum zzb implements zzbbr
    {
        private static final zzbbs<zzb> zzall;
        
        zzdkw(0), 
        zzdkx(1), 
        zzdky(2), 
        zzdkz(3), 
        zzdla(4), 
        zzdlb(-1);
        
        private final int value;
        
        static {
            zzall = (zzbbs)new zzaxk();
        }
        
        private zzb(final int value) {
            this.value = value;
        }
        
        public static zzb zzaw(final int n) {
            switch (n) {
                default: {
                    return null;
                }
                case 0: {
                    return zzb.zzdkw;
                }
                case 1: {
                    return zzb.zzdkx;
                }
                case 2: {
                    return zzb.zzdky;
                }
                case 3: {
                    return zzb.zzdkz;
                }
                case 4: {
                    return zzb.zzdla;
                }
            }
        }
        
        public final int zzhq() {
            if (this == zzb.zzdlb) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }
    }
}
