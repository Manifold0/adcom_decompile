// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzavy extends zzbbo<zzavy, zza> implements zzbcw
{
    private static volatile zzbdf<zzavy> zzakh;
    private static final zzavy zzdiw;
    private int zzdih;
    private zzbah zzdip;
    private zzawc zzdiv;
    
    static {
        zzbbo.zza((Class)zzavy.class, (zzbbo)(zzdiw = new zzavy()));
    }
    
    private zzavy() {
        this.zzdip = zzbah.zzdpq;
    }
    
    private final void setVersion(final int zzdih) {
        this.zzdih = zzdih;
    }
    
    static /* synthetic */ void zza(final zzavy zzavy, final int n) {
        zzavy.setVersion(0);
    }
    
    private final void zza(final zzawc zzdiv) {
        if (zzdiv == null) {
            throw new NullPointerException();
        }
        this.zzdiv = zzdiv;
    }
    
    private final void zzk(final zzbah zzdip) {
        if (zzdip == null) {
            throw new NullPointerException();
        }
        this.zzdip = zzdip;
    }
    
    public static zzavy zzo(final zzbah zzbah) throws zzbbu {
        return (zzavy)zzbbo.zza((zzbbo)zzavy.zzdiw, zzbah);
    }
    
    public static zza zzxf() {
        return (zza)zzavy.zzdiw.zza(zzbbo$zze.zzdue, null, null);
    }
    
    public final int getVersion() {
        return this.zzdih;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzavz.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzavy();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzavy.zzdiw, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[] { "zzdih", "zzdiv", "zzdip" });
            }
            case 4: {
                return zzavy.zzdiw;
            }
            case 5: {
                if ((zzakh = zzavy.zzakh) == null) {
                    synchronized (zzavy.class) {
                        zzbdf<zzavy> zzakh2;
                        if ((zzakh2 = zzavy.zzakh) == null) {
                            zzakh2 = (zzavy.zzakh = (zzbdf<zzavy>)new zzbbo$zzb((zzbbo)zzavy.zzdiw));
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
    
    public final zzawc zzxe() {
        if (this.zzdiv == null) {
            return zzawc.zzxi();
        }
        return this.zzdiv;
    }
    
    public static final class zza extends zzbbo$zza<zzavy, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzavy.zzdiw);
        }
        
        public final zza zzan(final int n) {
            this.zzadh();
            zzavy.zza((zzavy)this.zzdtx, 0);
            return this;
        }
        
        public final zza zzb(final zzawc zzawc) {
            this.zzadh();
            ((zzavy)this.zzdtx).zza(zzawc);
            return this;
        }
        
        public final zza zzp(final zzbah zzbah) {
            this.zzadh();
            ((zzavy)this.zzdtx).zzk(zzbah);
            return this;
        }
    }
}
