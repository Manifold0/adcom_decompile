// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbbn implements zzbct
{
    private static final zzbbn zzdts;
    
    static {
        zzdts = new zzbbn();
    }
    
    private zzbbn() {
    }
    
    public static zzbbn zzadc() {
        return zzbbn.zzdts;
    }
    
    @Override
    public final boolean zza(final Class<?> clazz) {
        return zzbbo.class.isAssignableFrom(clazz);
    }
    
    @Override
    public final zzbcs zzb(final Class<?> clazz) {
        if (!zzbbo.class.isAssignableFrom(clazz)) {
            final String value = String.valueOf(clazz.getName());
            String concat;
            if (value.length() != 0) {
                concat = "Unsupported message type: ".concat(value);
            }
            else {
                concat = new String("Unsupported message type: ");
            }
            throw new IllegalArgumentException(concat);
        }
        try {
            return (zzbcs)((zzbbo)zzbbo.zzc(clazz.asSubclass(zzbbo.class))).zza(zzbbo.zze.zzduc, null, (Object)null);
        }
        catch (Exception ex) {
            final String value2 = String.valueOf(clazz.getName());
            String concat2;
            if (value2.length() != 0) {
                concat2 = "Unable to get message info for ".concat(value2);
            }
            else {
                concat2 = new String("Unable to get message info for ");
            }
            throw new RuntimeException(concat2, ex);
        }
    }
}
