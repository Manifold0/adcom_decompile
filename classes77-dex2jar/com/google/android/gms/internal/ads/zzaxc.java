// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzaxc extends zzbbo<zzaxc, zza> implements zzbcw
{
    private static volatile zzbdf<zzaxc> zzakh;
    private static final zzaxc zzdkn;
    private int zzdih;
    private zzbah zzdip;
    private zzaxg zzdkm;
    
    static {
        zzbbo.zza((Class)zzaxc.class, (zzbbo)(zzdkn = new zzaxc()));
    }
    
    private zzaxc() {
        this.zzdip = zzbah.zzdpq;
    }
    
    private final void setVersion(final int zzdih) {
        this.zzdih = zzdih;
    }
    
    static /* synthetic */ void zza(final zzaxc zzaxc, final int n) {
        zzaxc.setVersion(0);
    }
    
    public static zzaxc zzae(final zzbah zzbah) throws zzbbu {
        return (zzaxc)zzbbo.zza((zzbbo)zzaxc.zzdkn, zzbah);
    }
    
    private final void zzb(final zzaxg zzdkm) {
        if (zzdkm == null) {
            throw new NullPointerException();
        }
        this.zzdkm = zzdkm;
    }
    
    private final void zzk(final zzbah zzdip) {
        if (zzdip == null) {
            throw new NullPointerException();
        }
        this.zzdip = zzdip;
    }
    
    public static zza zzyn() {
        return (zza)zzaxc.zzdkn.zza(zzbbo$zze.zzdue, null, null);
    }
    
    public static zzaxc zzyo() {
        return zzaxc.zzdkn;
    }
    
    public final int getVersion() {
        return this.zzdih;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzaxd.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzaxc();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzaxc.zzdkn, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[] { "zzdih", "zzdkm", "zzdip" });
            }
            case 4: {
                return zzaxc.zzdkn;
            }
            case 5: {
                if ((zzakh = zzaxc.zzakh) == null) {
                    synchronized (zzaxc.class) {
                        zzbdf<zzaxc> zzakh2;
                        if ((zzakh2 = zzaxc.zzakh) == null) {
                            zzakh2 = (zzaxc.zzakh = (zzbdf<zzaxc>)new zzbbo$zzb((zzbbo)zzaxc.zzdkn));
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
    
    public final zzbah zzwv() {
        return this.zzdip;
    }
    
    public final zzaxg zzym() {
        if (this.zzdkm == null) {
            return zzaxg.zzyu();
        }
        return this.zzdkm;
    }
    
    public static final class zza extends zzbbo$zza<zzaxc, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzaxc.zzdkn);
        }
        
        public final zza zzaf(final zzbah zzbah) {
            this.zzadh();
            ((zzaxc)this.zzdtx).zzk(zzbah);
            return this;
        }
        
        public final zza zzav(final int n) {
            this.zzadh();
            zzaxc.zza((zzaxc)this.zzdtx, 0);
            return this;
        }
        
        public final zza zzc(final zzaxg zzaxg) {
            this.zzadh();
            ((zzaxc)this.zzdtx).zzb(zzaxg);
            return this;
        }
    }
}
