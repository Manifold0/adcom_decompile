// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzavs extends zzbbo<zzavs, zza> implements zzbcw
{
    private static volatile zzbdf<zzavs> zzakh;
    private static final zzavs zzdiq;
    private int zzdih;
    private zzavw zzdio;
    private zzbah zzdip;
    
    static {
        zzbbo.zza((Class)zzavs.class, (zzbbo)(zzdiq = new zzavs()));
    }
    
    private zzavs() {
        this.zzdip = zzbah.zzdpq;
    }
    
    private final void setVersion(final int zzdih) {
        this.zzdih = zzdih;
    }
    
    static /* synthetic */ void zza(final zzavs zzavs, final int n) {
        zzavs.setVersion(0);
    }
    
    private final void zzb(final zzavw zzdio) {
        if (zzdio == null) {
            throw new NullPointerException();
        }
        this.zzdio = zzdio;
    }
    
    private final void zzk(final zzbah zzdip) {
        if (zzdip == null) {
            throw new NullPointerException();
        }
        this.zzdip = zzdip;
    }
    
    public static zzavs zzl(final zzbah zzbah) throws zzbbu {
        return (zzavs)zzbbo.zza((zzbbo)zzavs.zzdiq, zzbah);
    }
    
    public static zza zzww() {
        return (zza)zzavs.zzdiq.zza(zzbbo$zze.zzdue, null, null);
    }
    
    public static zzavs zzwx() {
        return zzavs.zzdiq;
    }
    
    public final int getVersion() {
        return this.zzdih;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzavt.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzavs();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzavs.zzdiq, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[] { "zzdih", "zzdio", "zzdip" });
            }
            case 4: {
                return zzavs.zzdiq;
            }
            case 5: {
                if ((zzakh = zzavs.zzakh) == null) {
                    synchronized (zzavs.class) {
                        zzbdf<zzavs> zzakh2;
                        if ((zzakh2 = zzavs.zzakh) == null) {
                            zzakh2 = (zzavs.zzakh = (zzbdf<zzavs>)new zzbbo$zzb((zzbbo)zzavs.zzdiq));
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
    
    public final zzavw zzwu() {
        if (this.zzdio == null) {
            return zzavw.zzxc();
        }
        return this.zzdio;
    }
    
    public final zzbah zzwv() {
        return this.zzdip;
    }
    
    public static final class zza extends zzbbo$zza<zzavs, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzavs.zzdiq);
        }
        
        public final zza zzam(final int n) {
            this.zzadh();
            zzavs.zza((zzavs)this.zzdtx, 0);
            return this;
        }
        
        public final zza zzc(final zzavw zzavw) {
            this.zzadh();
            ((zzavs)this.zzdtx).zzb(zzavw);
            return this;
        }
        
        public final zza zzm(final zzbah zzbah) {
            this.zzadh();
            ((zzavs)this.zzdtx).zzk(zzbah);
            return this;
        }
    }
}
