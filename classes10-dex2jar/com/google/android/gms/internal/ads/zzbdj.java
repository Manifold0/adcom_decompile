// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.lang.reflect.Field;

final class zzbdj
{
    private final int flags;
    private final Object[] zzdwh;
    private final int zzdwi;
    private final int zzdwj;
    private final int zzdwk;
    private final int[] zzdwq;
    private final zzbdk zzdxf;
    private Class<?> zzdxg;
    private final int zzdxh;
    private final int zzdxi;
    private final int zzdxj;
    private final int zzdxk;
    private final int zzdxl;
    private final int zzdxm;
    private int zzdxn;
    private int zzdxo;
    private int zzdxp;
    private int zzdxq;
    private int zzdxr;
    private int zzdxs;
    private int zzdxt;
    private int zzdxu;
    private int zzdxv;
    private int zzdxw;
    private int zzdxx;
    private int zzdxy;
    private int zzdxz;
    private int zzdya;
    private Field zzdyb;
    private Object zzdyc;
    private Object zzdyd;
    private Object zzdye;
    
    zzbdj(final Class<?> zzdxg, final String s, final Object[] zzdwh) {
        final int[] array = null;
        this.zzdxp = Integer.MAX_VALUE;
        this.zzdxq = Integer.MIN_VALUE;
        this.zzdxr = 0;
        this.zzdxs = 0;
        this.zzdxt = 0;
        this.zzdxu = 0;
        this.zzdxv = 0;
        this.zzdxg = zzdxg;
        this.zzdxf = new zzbdk(s);
        this.zzdwh = zzdwh;
        this.flags = this.zzdxf.next();
        this.zzdxh = this.zzdxf.next();
        if (this.zzdxh == 0) {
            this.zzdxi = 0;
            this.zzdxj = 0;
            this.zzdwi = 0;
            this.zzdwj = 0;
            this.zzdxk = 0;
            this.zzdxl = 0;
            this.zzdwk = 0;
            this.zzdxm = 0;
            this.zzdwq = null;
            return;
        }
        this.zzdxi = this.zzdxf.next();
        this.zzdxj = this.zzdxf.next();
        this.zzdwi = this.zzdxf.next();
        this.zzdwj = this.zzdxf.next();
        this.zzdxl = this.zzdxf.next();
        this.zzdwk = this.zzdxf.next();
        this.zzdxk = this.zzdxf.next();
        this.zzdxm = this.zzdxf.next();
        final int next = this.zzdxf.next();
        int[] zzdwq;
        if (next == 0) {
            zzdwq = array;
        }
        else {
            zzdwq = new int[next];
        }
        this.zzdwq = zzdwq;
        this.zzdxn = (this.zzdxi << 1) + this.zzdxj;
    }
    
    private static Field zza(final Class<?> clazz, final String s) {
        try {
            return clazz.getDeclaredField(s);
        }
        catch (NoSuchFieldException ex) {
            final Field[] declaredFields = clazz.getDeclaredFields();
            for (int length = declaredFields.length, i = 0; i < length; ++i) {
                final Field declaredField;
                if (s.equals((declaredField = declaredFields[i]).getName())) {
                    return declaredField;
                }
            }
            final String name = clazz.getName();
            final String string = Arrays.toString(declaredFields);
            throw new RuntimeException(new StringBuilder(String.valueOf(s).length() + 40 + String.valueOf(name).length() + String.valueOf(string).length()).append("Field ").append(s).append(" for ").append(name).append(" not found. Known fields are ").append(string).toString());
        }
    }
    
    private final Object zzaey() {
        return this.zzdwh[this.zzdxn++];
    }
    
    private final boolean zzafa() {
        return (this.flags & 0x1) == 0x1;
    }
    
    final boolean next() {
        final int n = 0;
        if (!this.zzdxf.hasNext()) {
            return false;
        }
        this.zzdxw = this.zzdxf.next();
        this.zzdxx = this.zzdxf.next();
        this.zzdxy = (this.zzdxx & 0xFF);
        if (this.zzdxw < this.zzdxp) {
            this.zzdxp = this.zzdxw;
        }
        if (this.zzdxw > this.zzdxq) {
            this.zzdxq = this.zzdxw;
        }
        if (this.zzdxy == zzbbj.zzdta.id()) {
            ++this.zzdxr;
        }
        else if (this.zzdxy >= zzbbj.zzdru.id() && this.zzdxy <= zzbbj.zzdsz.id()) {
            ++this.zzdxs;
        }
        ++this.zzdxv;
        if (zzbdo.zze(this.zzdxp, this.zzdxw, this.zzdxv)) {
            this.zzdxu = this.zzdxw + 1;
            this.zzdxt = this.zzdxu - this.zzdxp;
        }
        else {
            ++this.zzdxt;
        }
        int n2;
        if ((this.zzdxx & 0x400) != 0x0) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (n2 != 0) {
            this.zzdwq[this.zzdxo++] = this.zzdxw;
        }
        this.zzdyc = null;
        this.zzdyd = null;
        this.zzdye = null;
        if (this.zzafb()) {
            this.zzdxz = this.zzdxf.next();
            if (this.zzdxy == zzbbj.zzdrl.id() + 51 || this.zzdxy == zzbbj.zzdrt.id() + 51) {
                this.zzdyc = this.zzaey();
            }
            else if (this.zzdxy == zzbbj.zzdro.id() + 51 && this.zzafa()) {
                this.zzdyd = this.zzaey();
            }
        }
        else {
            this.zzdyb = zza(this.zzdxg, (String)this.zzaey());
            if (this.zzaff()) {
                this.zzdya = this.zzdxf.next();
            }
            if (this.zzdxy == zzbbj.zzdrl.id() || this.zzdxy == zzbbj.zzdrt.id()) {
                this.zzdyc = this.zzdyb.getType();
            }
            else if (this.zzdxy == zzbbj.zzdsd.id() || this.zzdxy == zzbbj.zzdsz.id()) {
                this.zzdyc = this.zzaey();
            }
            else if (this.zzdxy == zzbbj.zzdro.id() || this.zzdxy == zzbbj.zzdsg.id() || this.zzdxy == zzbbj.zzdsu.id()) {
                if (this.zzafa()) {
                    this.zzdyd = this.zzaey();
                }
            }
            else if (this.zzdxy == zzbbj.zzdta.id()) {
                this.zzdye = this.zzaey();
                int n3 = n;
                if ((this.zzdxx & 0x800) != 0x0) {
                    n3 = 1;
                }
                if (n3 != 0) {
                    this.zzdyd = this.zzaey();
                }
            }
        }
        return true;
    }
    
    final int zzaci() {
        return this.zzdxw;
    }
    
    final int zzaez() {
        return this.zzdxy;
    }
    
    final boolean zzafb() {
        return this.zzdxy > zzbbj.zzdta.id();
    }
    
    final Field zzafc() {
        final int n = this.zzdxz << 1;
        final Object o = this.zzdwh[n];
        if (o instanceof Field) {
            return (Field)o;
        }
        return (Field)(this.zzdwh[n] = zza(this.zzdxg, (String)o));
    }
    
    final Field zzafd() {
        final int n = (this.zzdxz << 1) + 1;
        final Object o = this.zzdwh[n];
        if (o instanceof Field) {
            return (Field)o;
        }
        return (Field)(this.zzdwh[n] = zza(this.zzdxg, (String)o));
    }
    
    final Field zzafe() {
        return this.zzdyb;
    }
    
    final boolean zzaff() {
        return this.zzafa() && this.zzdxy <= zzbbj.zzdrt.id();
    }
    
    final Field zzafg() {
        final int n = this.zzdya / 32 + (this.zzdxi << 1);
        final Object o = this.zzdwh[n];
        if (o instanceof Field) {
            return (Field)o;
        }
        return (Field)(this.zzdwh[n] = zza(this.zzdxg, (String)o));
    }
    
    final int zzafh() {
        return this.zzdya % 32;
    }
    
    final boolean zzafi() {
        return (this.zzdxx & 0x100) != 0x0;
    }
    
    final boolean zzafj() {
        return (this.zzdxx & 0x200) != 0x0;
    }
    
    final Object zzafk() {
        return this.zzdyc;
    }
    
    final Object zzafl() {
        return this.zzdyd;
    }
    
    final Object zzafm() {
        return this.zzdye;
    }
}
