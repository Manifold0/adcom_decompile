// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzaws extends zzbbo<zzaws, zza> implements zzbcw
{
    private static volatile zzbdf<zzaws> zzakh;
    private static final zzaws zzdjq;
    private int zzdih;
    private zzbah zzdip;
    private zzawu zzdjp;
    
    static {
        zzbbo.zza((Class)zzaws.class, (zzbbo)(zzdjq = new zzaws()));
    }
    
    private zzaws() {
        this.zzdip = zzbah.zzdpq;
    }
    
    private final void setVersion(final int zzdih) {
        this.zzdih = zzdih;
    }
    
    static /* synthetic */ void zza(final zzaws zzaws, final int n) {
        zzaws.setVersion(0);
    }
    
    private final void zza(final zzawu zzdjp) {
        if (zzdjp == null) {
            throw new NullPointerException();
        }
        this.zzdjp = zzdjp;
    }
    
    private final void zzk(final zzbah zzdip) {
        if (zzdip == null) {
            throw new NullPointerException();
        }
        this.zzdip = zzdip;
    }
    
    public static zzaws zzx(final zzbah zzbah) throws zzbbu {
        return (zzaws)zzbbo.zza((zzbbo)zzaws.zzdjq, zzbah);
    }
    
    public static zza zzya() {
        return (zza)zzaws.zzdjq.zza(zzbbo$zze.zzdue, null, null);
    }
    
    public final int getVersion() {
        return this.zzdih;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzawt.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzaws();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzaws.zzdjq, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[] { "zzdih", "zzdjp", "zzdip" });
            }
            case 4: {
                return zzaws.zzdjq;
            }
            case 5: {
                if ((zzakh = zzaws.zzakh) == null) {
                    synchronized (zzaws.class) {
                        zzbdf<zzaws> zzakh2;
                        if ((zzakh2 = zzaws.zzakh) == null) {
                            zzakh2 = (zzaws.zzakh = (zzbdf<zzaws>)new zzbbo$zzb((zzbbo)zzaws.zzdjq));
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
    
    public final zzawu zzxz() {
        if (this.zzdjp == null) {
            return zzawu.zzyf();
        }
        return this.zzdjp;
    }
    
    public static final class zza extends zzbbo$zza<zzaws, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzaws.zzdjq);
        }
        
        public final zza zzar(final int n) {
            this.zzadh();
            zzaws.zza((zzaws)this.zzdtx, 0);
            return this;
        }
        
        public final zza zzb(final zzawu zzawu) {
            this.zzadh();
            ((zzaws)this.zzdtx).zza(zzawu);
            return this;
        }
        
        public final zza zzy(final zzbah zzbah) {
            this.zzadh();
            ((zzaws)this.zzdtx).zzk(zzbah);
            return this;
        }
    }
}
