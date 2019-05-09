// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzawo extends zzbbo<zzawo, zza> implements zzbcw
{
    private static volatile zzbdf<zzawo> zzakh;
    private static final zzawo zzdjk;
    private zzawq zzdjj;
    
    static {
        zzbbo.zza((Class)zzawo.class, (zzbbo)(zzdjk = new zzawo()));
    }
    
    private zzawo() {
    }
    
    public static zzawo zzw(final zzbah zzbah) throws zzbbu {
        return (zzawo)zzbbo.zza((zzbbo)zzawo.zzdjk, zzbah);
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzawp.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzawo();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzawo.zzdjk, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0000\u0000\u0001\t", new Object[] { "zzdjj" });
            }
            case 4: {
                return zzawo.zzdjk;
            }
            case 5: {
                if ((zzakh = zzawo.zzakh) == null) {
                    synchronized (zzawo.class) {
                        zzbdf<zzawo> zzakh2;
                        if ((zzakh2 = zzawo.zzakh) == null) {
                            zzakh2 = (zzawo.zzakh = (zzbdf<zzawo>)new zzbbo$zzb((zzbbo)zzawo.zzdjk));
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
    
    public final zzawq zzxs() {
        if (this.zzdjj == null) {
            return zzawq.zzxx();
        }
        return this.zzdjj;
    }
    
    public static final class zza extends zzbbo$zza<zzawo, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzawo.zzdjk);
        }
    }
}
