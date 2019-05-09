// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzawe extends zzbbo<zzawe, zza> implements zzbcw
{
    private static volatile zzbdf<zzawe> zzakh;
    private static final zzawe zzdiz;
    private int zzdih;
    private zzbah zzdip;
    
    static {
        zzbbo.zza((Class)zzawe.class, (zzbbo)(zzdiz = new zzawe()));
    }
    
    private zzawe() {
        this.zzdip = zzbah.zzdpq;
    }
    
    private final void setVersion(final int zzdih) {
        this.zzdih = zzdih;
    }
    
    static /* synthetic */ void zza(final zzawe zzawe, final int n) {
        zzawe.setVersion(0);
    }
    
    private final void zzk(final zzbah zzdip) {
        if (zzdip == null) {
            throw new NullPointerException();
        }
        this.zzdip = zzdip;
    }
    
    public static zzawe zzr(final zzbah zzbah) throws zzbbu {
        return (zzawe)zzbbo.zza((zzbbo)zzawe.zzdiz, zzbah);
    }
    
    public static zza zzxk() {
        return (zza)zzawe.zzdiz.zza(zzbbo$zze.zzdue, null, null);
    }
    
    public final int getVersion() {
        return this.zzdih;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzawf.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzawe();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzawe.zzdiz, "\u0000\u0002\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[] { "zzdih", "zzdip" });
            }
            case 4: {
                return zzawe.zzdiz;
            }
            case 5: {
                if ((zzakh = zzawe.zzakh) == null) {
                    synchronized (zzawe.class) {
                        zzbdf<zzawe> zzakh2;
                        if ((zzakh2 = zzawe.zzakh) == null) {
                            zzakh2 = (zzawe.zzakh = (zzbdf<zzawe>)new zzbbo$zzb((zzbbo)zzawe.zzdiz));
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
    
    public static final class zza extends zzbbo$zza<zzawe, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzawe.zzdiz);
        }
        
        public final zza zzao(final int n) {
            this.zzadh();
            zzawe.zza((zzawe)this.zzdtx, 0);
            return this;
        }
        
        public final zza zzs(final zzbah zzbah) {
            this.zzadh();
            ((zzawe)this.zzdtx).zzk(zzbah);
            return this;
        }
    }
}
