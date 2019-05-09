// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzavw extends zzbbo<zzavw, zza> implements zzbcw
{
    private static volatile zzbdf<zzavw> zzakh;
    private static final zzavw zzdiu;
    private int zzdit;
    
    static {
        zzbbo.zza((Class)zzavw.class, (zzbbo)(zzdiu = new zzavw()));
    }
    
    private zzavw() {
    }
    
    public static zzavw zzxc() {
        return zzavw.zzdiu;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzavx.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzavw();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzavw.zzdiu, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0000\u0000\u0001\u000b", new Object[] { "zzdit" });
            }
            case 4: {
                return zzavw.zzdiu;
            }
            case 5: {
                if ((zzakh = zzavw.zzakh) == null) {
                    synchronized (zzavw.class) {
                        zzbdf<zzavw> zzakh2;
                        if ((zzakh2 = zzavw.zzakh) == null) {
                            zzakh2 = (zzavw.zzakh = (zzbdf<zzavw>)new zzbbo$zzb((zzbbo)zzavw.zzdiu));
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
    
    public final int zzxb() {
        return this.zzdit;
    }
    
    public static final class zza extends zzbbo$zza<zzavw, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzavw.zzdiu);
        }
    }
}
