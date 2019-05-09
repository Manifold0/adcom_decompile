// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import android.graphics.Color;
import java.util.List;

@zzadh
public final class zzoj extends zzpt
{
    private static final int zzbha;
    private static final int zzbhb;
    private static final int zzbhc;
    private static final int zzbhd;
    private final int mTextColor;
    private final String zzbhe;
    private final List<zzon> zzbhf;
    private final List<zzpw> zzbhg;
    private final int zzbhh;
    private final int zzbhi;
    private final int zzbhj;
    private final int zzbhk;
    private final boolean zzbhl;
    
    static {
        zzbha = Color.rgb(12, 174, 206);
        zzbhc = (zzbhb = Color.rgb(204, 204, 204));
        zzbhd = zzoj.zzbha;
    }
    
    public zzoj(final String zzbhe, final List<zzon> list, final Integer n, final Integer n2, final Integer n3, final int zzbhj, final int zzbhk, final boolean zzbhl) {
        this.zzbhf = new ArrayList<zzon>();
        this.zzbhg = new ArrayList<zzpw>();
        this.zzbhe = zzbhe;
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                final zzon zzon = list.get(i);
                this.zzbhf.add(zzon);
                this.zzbhg.add(zzon);
            }
        }
        int zzbhh;
        if (n != null) {
            zzbhh = n;
        }
        else {
            zzbhh = zzoj.zzbhc;
        }
        this.zzbhh = zzbhh;
        int mTextColor;
        if (n2 != null) {
            mTextColor = n2;
        }
        else {
            mTextColor = zzoj.zzbhd;
        }
        this.mTextColor = mTextColor;
        int intValue;
        if (n3 != null) {
            intValue = n3;
        }
        else {
            intValue = 12;
        }
        this.zzbhi = intValue;
        this.zzbhj = zzbhj;
        this.zzbhk = zzbhk;
        this.zzbhl = zzbhl;
    }
    
    public final int getBackgroundColor() {
        return this.zzbhh;
    }
    
    public final String getText() {
        return this.zzbhe;
    }
    
    public final int getTextColor() {
        return this.mTextColor;
    }
    
    public final int getTextSize() {
        return this.zzbhi;
    }
    
    public final List<zzpw> zzjr() {
        return this.zzbhg;
    }
    
    public final List<zzon> zzjs() {
        return this.zzbhf;
    }
    
    public final int zzjt() {
        return this.zzbhj;
    }
    
    public final int zzju() {
        return this.zzbhk;
    }
    
    public final boolean zzjv() {
        return this.zzbhl;
    }
}
