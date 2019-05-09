// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzayb extends zzbbo<zzayb, zza> implements zzbcw
{
    private static volatile zzbdf<zzayb> zzakh;
    private static final zzayb zzdmj;
    private String zzdmh;
    private zzaxn zzdmi;
    
    static {
        zzbbo.zza((Class)zzayb.class, (zzbbo)(zzdmj = new zzayb()));
    }
    
    private zzayb() {
        this.zzdmh = "";
    }
    
    public static zzayb zzaaj() {
        return zzayb.zzdmj;
    }
    
    public static zzayb zzam(final zzbah zzbah) throws zzbbu {
        return (zzayb)zzbbo.zza((zzbbo)zzayb.zzdmj, zzbah);
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzayc.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzayb();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzayb.zzdmj, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\u0208\u0002\t", new Object[] { "zzdmh", "zzdmi" });
            }
            case 4: {
                return zzayb.zzdmj;
            }
            case 5: {
                if ((zzakh = zzayb.zzakh) == null) {
                    synchronized (zzayb.class) {
                        zzbdf<zzayb> zzakh2;
                        if ((zzakh2 = zzayb.zzakh) == null) {
                            zzakh2 = (zzayb.zzakh = (zzbdf<zzayb>)new zzbbo$zzb((zzbbo)zzayb.zzdmj));
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
    
    public final String zzaah() {
        return this.zzdmh;
    }
    
    public final zzaxn zzaai() {
        if (this.zzdmi == null) {
            return zzaxn.zzzc();
        }
        return this.zzdmi;
    }
    
    public static final class zza extends zzbbo$zza<zzayb, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzayb.zzdmj);
        }
    }
}
