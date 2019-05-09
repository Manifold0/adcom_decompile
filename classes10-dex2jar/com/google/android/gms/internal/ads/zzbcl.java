// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbcl implements zzbct
{
    private zzbct[] zzdvx;
    
    zzbcl(final zzbct... zzdvx) {
        this.zzdvx = zzdvx;
    }
    
    @Override
    public final boolean zza(final Class<?> clazz) {
        final boolean b = false;
        final zzbct[] zzdvx = this.zzdvx;
        final int length = zzdvx.length;
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= length) {
                break;
            }
            if (zzdvx[n].zza(clazz)) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    @Override
    public final zzbcs zzb(final Class<?> clazz) {
        final zzbct[] zzdvx = this.zzdvx;
        for (int length = zzdvx.length, i = 0; i < length; ++i) {
            final zzbct zzbct = zzdvx[i];
            if (zzbct.zza(clazz)) {
                return zzbct.zzb(clazz);
            }
        }
        final String value = String.valueOf(clazz.getName());
        String concat;
        if (value.length() != 0) {
            concat = "No factory is available for message type: ".concat(value);
        }
        else {
            concat = new String("No factory is available for message type: ");
        }
        throw new UnsupportedOperationException(concat);
    }
}
