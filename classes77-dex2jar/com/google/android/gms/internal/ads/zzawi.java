// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzawi extends zzbbo<zzawi, zza> implements zzbcw
{
    private static volatile zzbdf<zzawi> zzakh;
    private static final zzawi zzdjb;
    private int zzdih;
    private zzbah zzdip;
    
    static {
        zzbbo.zza((Class)zzawi.class, (zzbbo)(zzdjb = new zzawi()));
    }
    
    private zzawi() {
        this.zzdip = zzbah.zzdpq;
    }
    
    private final void setVersion(final int zzdih) {
        this.zzdih = zzdih;
    }
    
    static /* synthetic */ void zza(final zzawi zzawi, final int n) {
        zzawi.setVersion(0);
    }
    
    private final void zzk(final zzbah zzdip) {
        if (zzdip == null) {
            throw new NullPointerException();
        }
        this.zzdip = zzdip;
    }
    
    public static zzawi zzu(final zzbah zzbah) throws zzbbu {
        return (zzawi)zzbbo.zza((zzbbo)zzawi.zzdjb, zzbah);
    }
    
    public static zza zzxn() {
        return (zza)zzawi.zzdjb.zza(zzbbo$zze.zzdue, null, null);
    }
    
    public final int getVersion() {
        return this.zzdih;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzawj.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzawi();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzawi.zzdjb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[] { "zzdih", "zzdip" });
            }
            case 4: {
                return zzawi.zzdjb;
            }
            case 5: {
                if ((zzakh = zzawi.zzakh) == null) {
                    synchronized (zzawi.class) {
                        zzbdf<zzawi> zzakh2;
                        if ((zzakh2 = zzawi.zzakh) == null) {
                            zzakh2 = (zzawi.zzakh = (zzbdf<zzawi>)new zzbbo$zzb((zzbbo)zzawi.zzdjb));
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
    
    public static final class zza extends zzbbo$zza<zzawi, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzawi.zzdjb);
        }
        
        public final zza zzap(final int n) {
            this.zzadh();
            zzawi.zza((zzawi)this.zzdtx, 0);
            return this;
        }
        
        public final zza zzv(final zzbah zzbah) {
            this.zzadh();
            ((zzawi)this.zzdtx).zzk(zzbah);
            return this;
        }
    }
}
