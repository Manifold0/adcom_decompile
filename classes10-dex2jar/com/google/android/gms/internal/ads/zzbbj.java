// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.Type;

public enum zzbbj
{
    zzdrc(0, zzbbl.zzdtk, zzbbw.zzduy), 
    zzdrd(1, zzbbl.zzdtk, zzbbw.zzdux), 
    zzdre(2, zzbbl.zzdtk, zzbbw.zzduw), 
    zzdrf(3, zzbbl.zzdtk, zzbbw.zzduw), 
    zzdrg(4, zzbbl.zzdtk, zzbbw.zzduv), 
    zzdrh(5, zzbbl.zzdtk, zzbbw.zzduw), 
    zzdri(6, zzbbl.zzdtk, zzbbw.zzduv), 
    zzdrj(7, zzbbl.zzdtk, zzbbw.zzduz), 
    zzdrk(8, zzbbl.zzdtk, zzbbw.zzdva), 
    zzdrl(9, zzbbl.zzdtk, zzbbw.zzdvd), 
    zzdrm(10, zzbbl.zzdtk, zzbbw.zzdvb), 
    zzdrn(11, zzbbl.zzdtk, zzbbw.zzduv), 
    zzdro(12, zzbbl.zzdtk, zzbbw.zzdvc), 
    zzdrp(13, zzbbl.zzdtk, zzbbw.zzduv), 
    zzdrq(14, zzbbl.zzdtk, zzbbw.zzduw), 
    zzdrr(15, zzbbl.zzdtk, zzbbw.zzduv), 
    zzdrs(16, zzbbl.zzdtk, zzbbw.zzduw), 
    zzdrt(17, zzbbl.zzdtk, zzbbw.zzdvd), 
    zzdru(18, zzbbl.zzdtl, zzbbw.zzduy), 
    zzdrv(19, zzbbl.zzdtl, zzbbw.zzdux), 
    zzdrw(20, zzbbl.zzdtl, zzbbw.zzduw), 
    zzdrx(21, zzbbl.zzdtl, zzbbw.zzduw), 
    zzdry(22, zzbbl.zzdtl, zzbbw.zzduv), 
    zzdrz(23, zzbbl.zzdtl, zzbbw.zzduw), 
    zzdsa(24, zzbbl.zzdtl, zzbbw.zzduv), 
    zzdsb(25, zzbbl.zzdtl, zzbbw.zzduz), 
    zzdsc(26, zzbbl.zzdtl, zzbbw.zzdva), 
    zzdsd(27, zzbbl.zzdtl, zzbbw.zzdvd), 
    zzdse(28, zzbbl.zzdtl, zzbbw.zzdvb), 
    zzdsf(29, zzbbl.zzdtl, zzbbw.zzduv), 
    zzdsg(30, zzbbl.zzdtl, zzbbw.zzdvc), 
    zzdsh(31, zzbbl.zzdtl, zzbbw.zzduv), 
    zzdsi(32, zzbbl.zzdtl, zzbbw.zzduw), 
    zzdsj(33, zzbbl.zzdtl, zzbbw.zzduv), 
    zzdsk(34, zzbbl.zzdtl, zzbbw.zzduw), 
    zzdsl(35, zzbbl.zzdtm, zzbbw.zzduy), 
    zzdsm(36, zzbbl.zzdtm, zzbbw.zzdux), 
    zzdsn(37, zzbbl.zzdtm, zzbbw.zzduw), 
    zzdso(38, zzbbl.zzdtm, zzbbw.zzduw), 
    zzdsp(39, zzbbl.zzdtm, zzbbw.zzduv), 
    zzdsq(40, zzbbl.zzdtm, zzbbw.zzduw), 
    zzdsr(41, zzbbl.zzdtm, zzbbw.zzduv), 
    zzdss(42, zzbbl.zzdtm, zzbbw.zzduz), 
    zzdst(43, zzbbl.zzdtm, zzbbw.zzduv), 
    zzdsu(44, zzbbl.zzdtm, zzbbw.zzdvc), 
    zzdsv(45, zzbbl.zzdtm, zzbbw.zzduv), 
    zzdsw(46, zzbbl.zzdtm, zzbbw.zzduw), 
    zzdsx(47, zzbbl.zzdtm, zzbbw.zzduv), 
    zzdsy(48, zzbbl.zzdtm, zzbbw.zzduw), 
    zzdsz(49, zzbbl.zzdtl, zzbbw.zzdvd), 
    zzdta(50, zzbbl.zzdtn, zzbbw.zzduu);
    
    private static final zzbbj[] zzdtf;
    private static final Type[] zzdtg;
    private final int id;
    private final zzbbw zzdtb;
    private final zzbbl zzdtc;
    private final Class<?> zzdtd;
    private final boolean zzdte;
    
    static {
        int i = 0;
        zzdtg = new Type[0];
        final zzbbj[] values = values();
        zzdtf = new zzbbj[values.length];
        while (i < values.length) {
            final zzbbj zzbbj = values[i];
            com.google.android.gms.internal.ads.zzbbj.zzdtf[zzbbj.id] = zzbbj;
            ++i;
        }
    }
    
    private zzbbj(final int id, final zzbbl zzdtc, final zzbbw zzdtb) {
        this.id = id;
        this.zzdtc = zzdtc;
        this.zzdtb = zzdtb;
        switch (zzbbk.zzdti[zzdtc.ordinal()]) {
            default: {
                this.zzdtd = null;
                break;
            }
            case 1: {
                this.zzdtd = zzdtb.zzadt();
                break;
            }
            case 2: {
                this.zzdtd = zzdtb.zzadt();
                break;
            }
        }
        boolean zzdte = false;
        if (zzdtc == zzbbl.zzdtk) {
            zzdte = zzdte;
            switch (zzbbk.zzdtj[zzdtb.ordinal()]) {
                default: {
                    zzdte = true;
                    break;
                }
                case 1:
                case 2:
                case 3: {
                    break;
                }
            }
        }
        this.zzdte = zzdte;
    }
    
    public final int id() {
        return this.id;
    }
}
