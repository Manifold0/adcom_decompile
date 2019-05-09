// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzavq extends zzbbo<zzavq, zza> implements zzbcw
{
    private static volatile zzbdf<zzavq> zzakh;
    private static final zzavq zzdin;
    private zzavu zzdil;
    private zzaxe zzdim;
    
    static {
        zzbbo.zza((Class)zzavq.class, (zzbbo)(zzdin = new zzavq()));
    }
    
    private zzavq() {
    }
    
    public static zzavq zzj(final zzbah zzbah) throws zzbbu {
        return (zzavq)zzbbo.zza((zzbbo)zzavq.zzdin, zzbah);
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzavr.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzavq();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzavq.zzdin, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\t\u0002\t", new Object[] { "zzdil", "zzdim" });
            }
            case 4: {
                return zzavq.zzdin;
            }
            case 5: {
                if ((zzakh = zzavq.zzakh) == null) {
                    synchronized (zzavq.class) {
                        zzbdf<zzavq> zzakh2;
                        if ((zzakh2 = zzavq.zzakh) == null) {
                            zzakh2 = (zzavq.zzakh = (zzbdf<zzavq>)new zzbbo$zzb((zzbbo)zzavq.zzdin));
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
    
    public final zzavu zzwr() {
        if (this.zzdil == null) {
            return zzavu.zzwz();
        }
        return this.zzdil;
    }
    
    public final zzaxe zzws() {
        if (this.zzdim == null) {
            return zzaxe.zzyq();
        }
        return this.zzdim;
    }
    
    public static final class zza extends zzbbo$zza<zzavq, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzavq.zzdin);
        }
    }
}
