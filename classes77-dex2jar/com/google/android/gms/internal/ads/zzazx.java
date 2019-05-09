// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzazx
{
    private final byte[] zzdpc;
    private int zzdpd;
    private int zzdpe;
    
    public zzazx(final byte[] array) {
        this.zzdpc = new byte[256];
        for (int i = 0; i < 256; ++i) {
            this.zzdpc[i] = (byte)i;
        }
        int n = 0;
        for (int j = 0; j < 256; ++j) {
            n = (n + this.zzdpc[j] + array[j % array.length] & 0xFF);
            final byte b = this.zzdpc[j];
            this.zzdpc[j] = this.zzdpc[n];
            this.zzdpc[n] = b;
        }
        this.zzdpd = 0;
        this.zzdpe = 0;
    }
    
    public final void zzn(final byte[] array) {
        int zzdpd = this.zzdpd;
        int zzdpe = this.zzdpe;
        for (int i = 0; i < array.length; ++i) {
            zzdpd = (zzdpd + 1 & 0xFF);
            zzdpe = (zzdpe + this.zzdpc[zzdpd] & 0xFF);
            final byte b = this.zzdpc[zzdpd];
            this.zzdpc[zzdpd] = this.zzdpc[zzdpe];
            this.zzdpc[zzdpe] = b;
            array[i] ^= this.zzdpc[this.zzdpc[zzdpd] + this.zzdpc[zzdpe] & 0xFF];
        }
        this.zzdpd = zzdpd;
        this.zzdpe = zzdpe;
    }
}
