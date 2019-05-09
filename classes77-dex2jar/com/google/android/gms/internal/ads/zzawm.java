// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzawm extends zzbbo<zzawm, zza> implements zzbcw
{
    private static volatile zzbdf<zzawm> zzakh;
    private static final zzawm zzdji;
    private zzaxn zzdjh;
    
    static {
        zzbbo.zza((Class)zzawm.class, (zzbbo)(zzdji = new zzawm()));
    }
    
    private zzawm() {
    }
    
    public static zzawm zzxq() {
        return zzawm.zzdji;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzawn.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzawm();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzawm.zzdji, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0003\u0000\u0000\u0000\u0002\t", new Object[] { "zzdjh" });
            }
            case 4: {
                return zzawm.zzdji;
            }
            case 5: {
                if ((zzakh = zzawm.zzakh) == null) {
                    synchronized (zzawm.class) {
                        zzbdf<zzawm> zzakh2;
                        if ((zzakh2 = zzawm.zzakh) == null) {
                            zzakh2 = (zzawm.zzakh = (zzbdf<zzawm>)new zzbbo$zzb((zzbbo)zzawm.zzdji));
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
    
    public final zzaxn zzxp() {
        if (this.zzdjh == null) {
            return zzaxn.zzzc();
        }
        return this.zzdjh;
    }
    
    public static final class zza extends zzbbo$zza<zzawm, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzawm.zzdji);
        }
    }
}
