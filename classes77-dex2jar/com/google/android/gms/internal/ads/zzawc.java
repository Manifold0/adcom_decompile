// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzawc extends zzbbo<zzawc, zza> implements zzbcw
{
    private static volatile zzbdf<zzawc> zzakh;
    private static final zzawc zzdiy;
    private int zzdit;
    
    static {
        zzbbo.zza((Class)zzawc.class, (zzbbo)(zzdiy = new zzawc()));
    }
    
    private zzawc() {
    }
    
    public static zzawc zzxi() {
        return zzawc.zzdiy;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzawd.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzawc();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzawc.zzdiy, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0000\u0000\u0001\u000b", new Object[] { "zzdit" });
            }
            case 4: {
                return zzawc.zzdiy;
            }
            case 5: {
                if ((zzakh = zzawc.zzakh) == null) {
                    synchronized (zzawc.class) {
                        zzbdf<zzawc> zzakh2;
                        if ((zzakh2 = zzawc.zzakh) == null) {
                            zzakh2 = (zzawc.zzakh = (zzbdf<zzawc>)new zzbbo$zzb((zzbbo)zzawc.zzdiy));
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
    
    public static final class zza extends zzbbo$zza<zzawc, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzawc.zzdiy);
        }
    }
}
