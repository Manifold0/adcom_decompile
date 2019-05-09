// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzawg extends zzbbo<zzawg, zza> implements zzbcw
{
    private static volatile zzbdf<zzawg> zzakh;
    private static final zzawg zzdja;
    private int zzdir;
    
    static {
        zzbbo.zza((Class)zzawg.class, (zzbbo)(zzdja = new zzawg()));
    }
    
    private zzawg() {
    }
    
    public static zzawg zzt(final zzbah zzbah) throws zzbbu {
        return (zzawg)zzbbo.zza((zzbbo)zzawg.zzdja, zzbah);
    }
    
    public final int getKeySize() {
        return this.zzdir;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzawh.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzawg();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzawg.zzdja, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0003\u0000\u0000\u0000\u0002\u000b", new Object[] { "zzdir" });
            }
            case 4: {
                return zzawg.zzdja;
            }
            case 5: {
                if ((zzakh = zzawg.zzakh) == null) {
                    synchronized (zzawg.class) {
                        zzbdf<zzawg> zzakh2;
                        if ((zzakh2 = zzawg.zzakh) == null) {
                            zzakh2 = (zzawg.zzakh = (zzbdf<zzawg>)new zzbbo$zzb((zzbbo)zzawg.zzdja));
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
    
    public static final class zza extends zzbbo$zza<zzawg, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzawg.zzdja);
        }
    }
}
