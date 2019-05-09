// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzavo extends zzbbo<zzavo, zza> implements zzbcw
{
    private static volatile zzbdf<zzavo> zzakh;
    private static final zzavo zzdik;
    private int zzdih;
    private zzavs zzdii;
    private zzaxc zzdij;
    
    static {
        zzbbo.zza((Class)zzavo.class, (zzbbo)(zzdik = new zzavo()));
    }
    
    private zzavo() {
    }
    
    private final void setVersion(final int zzdih) {
        this.zzdih = zzdih;
    }
    
    private final void zza(final zzavs zzdii) {
        if (zzdii == null) {
            throw new NullPointerException();
        }
        this.zzdii = zzdii;
    }
    
    private final void zza(final zzaxc zzdij) {
        if (zzdij == null) {
            throw new NullPointerException();
        }
        this.zzdij = zzdij;
    }
    
    public static zzavo zzi(final zzbah zzbah) throws zzbbu {
        return (zzavo)zzbbo.zza((zzbbo)zzavo.zzdik, zzbah);
    }
    
    public static zza zzwp() {
        return (zza)zzavo.zzdik.zza(zzbbo$zze.zzdue, null, null);
    }
    
    public final int getVersion() {
        return this.zzdih;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzavp.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzavo();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzavo.zzdik, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\t", new Object[] { "zzdih", "zzdii", "zzdij" });
            }
            case 4: {
                return zzavo.zzdik;
            }
            case 5: {
                if ((zzakh = zzavo.zzakh) == null) {
                    synchronized (zzavo.class) {
                        zzbdf<zzavo> zzakh2;
                        if ((zzakh2 = zzavo.zzakh) == null) {
                            zzakh2 = (zzavo.zzakh = (zzbdf<zzavo>)new zzbbo$zzb((zzbbo)zzavo.zzdik));
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
    
    public final zzavs zzwn() {
        if (this.zzdii == null) {
            return zzavs.zzwx();
        }
        return this.zzdii;
    }
    
    public final zzaxc zzwo() {
        if (this.zzdij == null) {
            return zzaxc.zzyo();
        }
        return this.zzdij;
    }
    
    public static final class zza extends zzbbo$zza<zzavo, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzavo.zzdik);
        }
        
        public final zza zzal(final int n) {
            this.zzadh();
            ((zzavo)this.zzdtx).setVersion(n);
            return this;
        }
        
        public final zza zzb(final zzavs zzavs) {
            this.zzadh();
            ((zzavo)this.zzdtx).zza(zzavs);
            return this;
        }
        
        public final zza zzb(final zzaxc zzaxc) {
            this.zzadh();
            ((zzavo)this.zzdtx).zza(zzaxc);
            return this;
        }
    }
}
