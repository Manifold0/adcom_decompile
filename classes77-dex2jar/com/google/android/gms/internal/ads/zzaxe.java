// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzaxe extends zzbbo<zzaxe, zza> implements zzbcw
{
    private static volatile zzbdf<zzaxe> zzakh;
    private static final zzaxe zzdko;
    private int zzdir;
    private zzaxg zzdkm;
    
    static {
        zzbbo.zza((Class)zzaxe.class, (zzbbo)(zzdko = new zzaxe()));
    }
    
    private zzaxe() {
    }
    
    public static zzaxe zzag(final zzbah zzbah) throws zzbbu {
        return (zzaxe)zzbbo.zza((zzbbo)zzaxe.zzdko, zzbah);
    }
    
    public static zzaxe zzyq() {
        return zzaxe.zzdko;
    }
    
    public final int getKeySize() {
        return this.zzdir;
    }
    
    protected final Object zza(final int n, final Object o, final Object o2) {
        Object zzakh = null;
        switch (zzaxf.zzakf[n - 1]) {
            default: {
                throw new UnsupportedOperationException();
            }
            case 1: {
                zzakh = new zzaxe();
                break;
            }
            case 2: {
                return new zza(null);
            }
            case 3: {
                return zza((zzbcu)zzaxe.zzdko, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[] { "zzdkm", "zzdir" });
            }
            case 4: {
                return zzaxe.zzdko;
            }
            case 5: {
                if ((zzakh = zzaxe.zzakh) == null) {
                    synchronized (zzaxe.class) {
                        zzbdf<zzaxe> zzakh2;
                        if ((zzakh2 = zzaxe.zzakh) == null) {
                            zzakh2 = (zzaxe.zzakh = (zzbdf<zzaxe>)new zzbbo$zzb((zzbbo)zzaxe.zzdko));
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
    
    public final zzaxg zzym() {
        if (this.zzdkm == null) {
            return zzaxg.zzyu();
        }
        return this.zzdkm;
    }
    
    public static final class zza extends zzbbo$zza<zzaxe, zza> implements zzbcw
    {
        private zza() {
            super((zzbbo)zzaxe.zzdko);
        }
    }
}
