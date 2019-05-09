// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzavu extends zzbbo<zzavu, zza> implements zzbcw
{
    private static volatile zzbdf<zzavu> zzakh;
    private static final zzavu zzdis;
    private zzavw zzdio;
    private int zzdir;
    
    static {
        zzbbo.zza((Class)zzavu.class, (zzbbo)(zzdis = new zzavu()));
    }
    
    private zzavu() {
    }
    
    public static zzavu zzn(final zzbah zzbah) throws zzbbu {
        return (zzavu)zzbbo.zza((zzbbo)zzavu.zzdis, zzbah);
    }
    
    public static zzavu zzwz() {
        return zzavu.zzdis;
    }
    
    public final int getKeySize() {
        return this.zzdir;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzavv.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzavu();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzavu.zzdis, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[] { "zzdio", "zzdir" });
            }
            case 4: {
                return zzavu.zzdis;
            }
            case 5: {
                if ((zzakh = zzavu.zzakh) == null) {
                    synchronized (zzavu.class) {
                        zzbdf<zzavu> zzakh2;
                        if ((zzakh2 = zzavu.zzakh) == null) {
                            zzakh2 = (zzavu.zzakh = (zzbdf<zzavu>)new zzbbo$zzb((zzbbo)zzavu.zzdis));
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
    
    public final zzavw zzwu() {
        if (this.zzdio == null) {
            return zzavw.zzxc();
        }
        return this.zzdio;
    }
    
    public static final class zza extends zzbbo$zza<zzavu, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzavu.zzdis);
        }
    }
}
